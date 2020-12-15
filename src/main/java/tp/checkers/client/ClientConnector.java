package tp.checkers.client;

import tp.checkers.client.gui.BoardUpdater;
import tp.checkers.client.gui.Window;
import tp.checkers.message.*;
import tp.checkers.server.game.Field;
import tp.checkers.server.game.Coordinates;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.Socket;

public class ClientConnector {

    private final Window window;
    private Socket socket = null;
    private ObjectInputStream objectInputStream = null;
    private ObjectOutputStream objectOutputStream = null;

    public ClientConnector() {
        connect();

        this.window = new Window(this);
        addWindowListener();
        window.setVisible(true);

        initGame();
    }


    //Initial methods:

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
        }
    }

    private void addWindowListener() {
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                //objectOutputStream.writeObject(new MessageUpdate());
                System.out.println("I'm closing. I should inform the server about it.");
                close();
            }
        });
    }

    private void initGame() {
        try {
            MessageIfHost msgHost = (MessageIfHost) objectInputStream.readObject();
            if (msgHost.host) {
                MessageInit msgInit = window.initGameData();
                objectOutputStream.writeObject(msgInit);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        initBoard();
    }

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
            e.printStackTrace();
        }

        GameService gameService = new GameService(this, window, fields, color, baseSide);
    }


    //Methods of client-server communication:

    public Coordinates[] receiveMovePossibilities(Coordinates clickedField) {
        Coordinates[] movePossibilities = null;

        try {
            objectOutputStream.writeObject(clickedField);
            movePossibilities = (Coordinates[]) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
        }

        return movePossibilities;
    }

    public void sendMove(MessageMove msg) {
        try {
            objectOutputStream.reset();
            objectOutputStream.writeObject(msg);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void receiveUpdates(BoardUpdater updater) {
        try {
            System.out.println("Receiving board updates from server.");
            updater.updateFields((MessageUpdate) objectInputStream.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    //Closing method:

    private void close() {
        try {
            objectInputStream.close();
            objectOutputStream.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Could not close.");
            System.exit(-1);
        }
    }
}
