package tp.checkers.server;

import tp.checkers.message.*;
import tp.checkers.server.game.Field;
import tp.checkers.server.game.Coordinates;

import java.awt.*;
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
            System.out.println("You are not a host");
            objectOutputStream.flush();
            objectOutputStream.writeObject(new MessageIfHost(false));
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
/*
        try {
            MessageMove msg = (MessageMove) objectInputStream.readObject();
            // System.out.println(msg.x + " " + msg.y);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

 */

    }

    public void sendFields(Field[][] fields) {
        try {
            objectOutputStream.writeObject(new MessageFields(fields));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendColor(Color color) {
        try {
            objectOutputStream.writeObject(new MessageColor(color));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MessageClickedField pieceSelect() {
        try {
            return (MessageClickedField) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void sendPossibilities(Coordinates[] possibilities) {
        try {
            objectOutputStream.writeObject(new MessagePossibilities(possibilities));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MessageMove pieceMove() {
        try {
            return (MessageMove) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateBoard(MessageClickedField messageClickedField, MessageMove messageMove) {
        try {
            objectOutputStream.writeObject(new MessageUpdate(messageClickedField.i, messageClickedField.j, messageMove.chosenFields[0].i, messageMove.chosenFields[0].j));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
