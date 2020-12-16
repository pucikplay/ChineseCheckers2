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
            objectOutputStream.writeBoolean(false);
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

    public void sendBoard(int baseSide, Field[][] fields, Color color) {
        try {
            objectOutputStream.writeObject(new MessageBoard(baseSide, fields, color));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Coordinates pieceSelect() {
        try {
            return (Coordinates) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void sendPossibilities(Coordinates[] possibilities) {
        try {
            objectOutputStream.writeObject(possibilities);
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

    public void updateBoard(Coordinates[] chosenFields, boolean yourMove) {
        try {
            objectOutputStream.writeObject(new MessageUpdate(chosenFields[0], chosenFields[1], yourMove));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateBoard(Coordinates[] chosenFields, boolean endGame, boolean youWon) {
        try {
            objectOutputStream.writeObject(new MessageUpdate(chosenFields[0], chosenFields[1], endGame, youWon));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
