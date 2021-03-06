package tp.checkers.client;

import tp.checkers.Coordinates;
import tp.checkers.client.gameservice.GameService;
import tp.checkers.client.gameservice.GameServicePlayed;
import tp.checkers.client.gameservice.GameServiceSaved;
import tp.checkers.client.window.Window;
import tp.checkers.client.window.WindowPlayed;
import tp.checkers.client.window.WindowSaved;
import tp.checkers.entities.EntityGames;
import tp.checkers.message.*;
import tp.checkers.Field;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.Socket;

/**
 * Main client's class responsible for connecting to server
 * and communication between other client's classes and server.
 */
public class Client {
    /**
     * Reference to client's window.
     */
    private tp.checkers.client.window.Window window;

    /**
     * Client's socket.
     */
    private Socket socket = null;

    /**
     * Input stream through which we send messages/objects to server.
     */
    private ObjectInputStream objectInputStream = null;

    /**
     * Output stream through which we get messages/objects from server.
     */
    private ObjectOutputStream objectOutputStream = null;

    private boolean play = true;

    /**
     * Default constructor of the class.
     */
    public Client() {
        connect();
        initGame();
    }

    /**
     * Method responsble for establishing connection with the server.
     */
    private void connect() {
        try {
            socket = new Socket("localhost", 4444);

            OutputStream outputStream = socket.getOutputStream();
            objectOutputStream = new ObjectOutputStream(outputStream);

            InputStream inputStream = socket.getInputStream();
            objectInputStream = new ObjectInputStream(inputStream);

            System.out.println("Client has connected to the server.");
        } catch (IOException e) {
            System.out.println("Error: can't connect to the server.");
            System.out.println("Make sure the server is working and it's not full.");
            close();
        }
    }

    /**
     * Method responsible for receiving information whether the player is host.
     * If so, it calls a Window's method for receiving game data
     * and sends that data to server.
     */
    private void initGame() {
        try {
            boolean host = objectInputStream.readBoolean();
            if (host) {
                play = tp.checkers.client.window.Window.runDialogGameMode();
                objectOutputStream.writeBoolean(play);
                objectOutputStream.flush();

                if (play) {
                    MessageInit msgInit = tp.checkers.client.window.Window.runDialogInit();
                    objectOutputStream.writeObject(msgInit);
                } else {
                    EntityGames[] games = (EntityGames[]) objectInputStream.readObject();
                    System.out.println("Got it!");
                    int savedGame = Window.runDialogSaved(games);
                    objectOutputStream.writeInt(savedGame);
                    objectOutputStream.flush();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            close();
        }

        initBoard();
    }

    /**
     * Method responsible for receiving game data from server
     * and creating a game service.
     */
    private void initBoard() {
        if (play) {
            this.window = new WindowPlayed();
        } else {
            this.window = new WindowSaved();
        }

        addWindowListener();
        window.setVisible(true);

        int baseSide = 4;
        Color color = null;
        Field[][] fields = null;

        try {
            MessageBoard msg = (MessageBoard) objectInputStream.readObject();
            baseSide = msg.getBaseSide();
            fields = msg.getFields();
            color = msg.getColor();
        } catch (IOException | ClassNotFoundException e) {
            close();
        }

        GameService gameService;
        if (play) {
            gameService = new GameServicePlayed(this, window, fields, color, baseSide);
        } else {
            gameService = new GameServiceSaved(this, window, fields, baseSide);
        }
    }

    /**
     * Method responsible for adding a window listener
     * to handle clicking the window's "X" button.
     */
    private void addWindowListener() {
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                System.out.println("I'm closing.");
                close();
            }
        });
    }

    /**
     * Method responsible for receiving move possibilities
     * for clicked field from server.
     *
     * @param clickedField coordinates of a clicked field
     * @return move possibilities
     */
    public Coordinates[] receiveMovePossibilities(Coordinates clickedField) {
        Coordinates[] movePossibilities = null;

        try {
            objectOutputStream.writeObject(clickedField);
            movePossibilities = (Coordinates[]) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            close();
        }

        return movePossibilities;
    }

    /**
     * Method responsible for sending move data to server.
     *
     * @param msg message with move data
     */
    public void sendMove(MessageMove msg) {
        try {
            objectOutputStream.reset();
            objectOutputStream.writeObject(msg);
        } catch (IOException e) {
            close();
        }
    }

    /**
     * Method used to request updates from the server when spectating.
     */
    public MessageUpdate nextSavedMove() {
        try {
            objectOutputStream.writeBoolean(true);
            objectOutputStream.flush();
        } catch (IOException e) {
            close();
        }

        return receiveUpdates();
    }

    /**
     * Method responsible for receiving update data from server
     * and calling the board updater to change the fields.
     * If the message contains data about end of the game, it calls closing method.
     *
     * @return the message update
     */
    public MessageUpdate receiveUpdates() {
        MessageUpdate msg = null;

        try {
            System.out.println("Receiving board updates from server.");
            msg = (MessageUpdate) objectInputStream.readObject();

            if (msg.isEndGame()) {
                if (!play) {
                    JOptionPane.showMessageDialog(window, "The saved game has ended.");
                } else if (msg.isYouWon()) {
                    JOptionPane.showMessageDialog(window, "The game has ended. You won! Congratulations.");
                } else {
                    JOptionPane.showMessageDialog(window, "The game has ended. You lost. Maybe the next time...");
                }
                close();
            }
        } catch (IOException | ClassNotFoundException e) {
            close();
        }

        return msg;
    }

    /**
     * Method responsible for closing connection.
     */
    private void close() {
        try {
            objectInputStream.close();
            objectOutputStream.close();
            socket.close();
            System.exit(0);
        } catch (IOException | NullPointerException e) {
            System.out.println("Could not close correctly.");
            System.exit(-1);
        }
    }

    /**
     * Main method of the client, calls the client connector.
     *
     * @param args unused inline arguments.
     */
    public static void main(String[] args) {
        Client client = new Client();
    }
}
