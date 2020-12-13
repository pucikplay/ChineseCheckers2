package tp.checkers.client;

import tp.checkers.client.gui.BoardUpdater;
import tp.checkers.client.gui.Window;
import tp.checkers.message.*;
import tp.checkers.server.game.Field;
import tp.checkers.server.game.Coordinates;

import java.awt.*;
import java.io.*;
import java.net.Socket;

public class ClientConnector {

    private final tp.checkers.client.gui.Window window;
    private Socket socket = null;
    private ObjectInputStream objectInputStream = null;
    private ObjectOutputStream objectOutputStream = null;
    private Field[][] fields = null;
    private Color color = null;

    public ClientConnector() {
        connect();

        this.window = new Window(this);
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

    private void initGame() {
        try {
            MessageIfHost msg = (MessageIfHost) objectInputStream.readObject();
            boolean host = msg.host;
            if(host) {
                objectOutputStream.writeObject(window.initGameData());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        initBoard();
    }

    private void initBoard() {
        try {
            MessageFields msgFields = (MessageFields) objectInputStream.readObject();
            fields = msgFields.fields;
            MessageColor msgColor = (MessageColor) objectInputStream.readObject();
            color = msgColor.color;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        int baseSide = 4;

        GameService gameService = new GameService(this, window, fields, color, baseSide);
    }


    //Methods of client-server communication:

    public Coordinates[] receiveMovePossibilities(MessageClickedField msg) {
        Coordinates[] movePossibilities = null;

        try {
            objectOutputStream.writeObject(msg);
            MessagePossibilities msgp = (MessagePossibilities) objectInputStream.readObject();
            movePossibilities = msgp.possibilities;
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
