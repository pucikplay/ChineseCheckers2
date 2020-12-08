package tp.checkers.server;

import tp.checkers.message.MessageFields;
import tp.checkers.message.MessageIfHost;
import tp.checkers.message.MessagePair;
import tp.checkers.server.game.Field;

import java.io.*;
import java.net.Socket;

public class ThreadPlayer extends Thread {
    protected Socket socket;
    protected InputStream inputStream = null;
    protected ObjectInputStream objectInputStream = null;
    protected OutputStream outputStream = null;
    protected ObjectOutputStream objectOutputStream = null;

    public ThreadPlayer(Socket clientSocket) {
        this.socket = clientSocket;

        try {
            inputStream = socket.getInputStream();
            objectInputStream = new ObjectInputStream(inputStream);
            outputStream = socket.getOutputStream();
            objectOutputStream = new ObjectOutputStream(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        System.out.println("SERVER: player got connected");

        try {
            objectOutputStream.writeObject(new MessageIfHost(false));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            MessagePair msg = (MessagePair) objectInputStream.readObject();
            System.out.println(msg.x + " " + msg.y);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void sendFields(Field[][] fields) {
        try {
            objectOutputStream.writeObject(new MessageFields(fields));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
