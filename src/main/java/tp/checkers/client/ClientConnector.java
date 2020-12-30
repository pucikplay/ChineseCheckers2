package tp.checkers.client;

import tp.checkers.Coordinates;
import tp.checkers.client.gui.Window;
import tp.checkers.message.*;
import tp.checkers.Field;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.Socket;

/**
 * Client's class responsible for connecting to server
 * and communication between other client's classes and server.
 */
public class ClientConnector {
    /**
     * Reference to client's window.
     */
    private final Window window;

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

    /**
     * Default constructor of the class.
     */
    public ClientConnector() {
        connect();

        this.window = new Window();
        addWindowListener();
        window.setVisible(true);

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
     * Method responsible for receiving information whether the player is host.
     * If so, it calls a Window's method for receiving game data
     * and sends that data to server.
     */
    private void initGame() {
        try {
            boolean host = objectInputStream.readBoolean();
            if (host) {
                MessageInit msgInit = window.runDialogInit();
                objectOutputStream.writeObject(msgInit);
            }
        } catch (IOException e) {
            close();
        }

        initBoard();
    }

    /**
     * Method responsible for receiving game data from server
     * and creating a game service.
     */
    private void initBoard() {
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

        GameService gameService = new GameService(this, window, fields, color, baseSide);
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
     * Method responsible for receiving update data from server
     * and calling the board updater to change the fields.
     * If the message contains data about end of the game, it calls closing method.
     */
    public MessageUpdate receiveUpdates() {
        MessageUpdate msg = null;

        try {
            System.out.println("Receiving board updates from server.");
            msg = (MessageUpdate) objectInputStream.readObject();

            if (msg.isEndGame()) {
                window.runDialogFinish(msg.isYouWon());
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
        } catch (IOException | NullPointerException e) {
            System.out.println("Could not close correctly.");
            System.exit(-1);
        }
    }
}
