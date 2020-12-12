package tp.checkers.client;

import tp.checkers.message.*;
import tp.checkers.server.game.Field;
import tp.checkers.server.game.Coordinates;

import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.Arrays;

public class Client {

    private final Window window;
    private Socket socket = null;
    private InputStream inputStream = null;
    private OutputStream outputStream = null;
    private ObjectInputStream objectInputStream = null;
    private ObjectOutputStream objectOutputStream = null;
    private Field[][] fields = null;
    private Color color = null;

    Client() {
        connect();

        this.window = new Window(this);
        window.setVisible(true);

        initGame();
    }


    //Initial methods:

    private void connect() {
        System.out.println("CLIENT: started");
        try {
            socket = new Socket("localhost", 4444);

            outputStream = socket.getOutputStream();
            objectOutputStream = new ObjectOutputStream(outputStream);

            inputStream = socket.getInputStream();
            objectInputStream = new ObjectInputStream(inputStream);

            System.out.println("CLIENT: connected");
        } catch (IOException e) {
            System.out.println("Error: can't connect to the server.");
            System.out.println("Make sure the server is working and it's not full");
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

        window.initBoard(fields, color);
    }


    //Methods of client-server communication:

    public Coordinates[] receiveMovePossibilities(MessageClickedField msg) {
        Coordinates[] movePossibilities = null;

        try {
            objectOutputStream.writeObject(msg);
            MessagePossibilities msgp = (MessagePossibilities) objectInputStream.readObject();
            movePossibilities = msgp.possibilities;
            if (movePossibilities.length > 0) System.out.println(movePossibilities[0].i + " " + movePossibilities[0].j);
        } catch (IOException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
        }

        return movePossibilities;
    }

    public void sendMove(MessageMove msg) {
        try {
            objectOutputStream.writeObject(msg);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    public void receiveUpdates(Panel panel) {
        try {
            System.out.println("receiving updates");
            panel.updateFields((MessageUpdate) objectInputStream.readObject());
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

    public static void main(String[] args) {
        Client client = new Client();
    }
}
