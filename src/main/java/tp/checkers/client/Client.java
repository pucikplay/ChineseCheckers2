package tp.checkers.client;

import tp.checkers.message.MessageFields;
import tp.checkers.message.MessageIfHost;
import tp.checkers.server.game.Field;

import java.io.*;
import java.net.Socket;

public class Client {

    private Window window;
    private Socket socket = null;
    private InputStream inputStream = null;
    private OutputStream outputStream = null;
    private ObjectInputStream objectInputStream = null;
    private ObjectOutputStream objectOutputStream = null;
    private Field[][] fields = null;

    Client() {
        this.window = new Window(this);

        System.out.println("CLIENT: started");
        try {
            socket = new Socket("localhost", 4444);

            outputStream = socket.getOutputStream();
            objectOutputStream = new ObjectOutputStream(outputStream);

            inputStream = socket.getInputStream();
            objectInputStream = new ObjectInputStream(inputStream);

            System.out.println("CLIENT: connected");
        } catch (IOException e) {
            System.out.println("Error");
        }

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
            MessageFields msg = (MessageFields) objectInputStream.readObject();
            fields = msg.fields;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        window.initBoard(fields);
    }

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
