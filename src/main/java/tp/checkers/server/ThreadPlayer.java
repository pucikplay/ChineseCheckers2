package tp.checkers.server;

import tp.checkers.Coordinates;
import tp.checkers.message.*;
import tp.checkers.Field;

import java.awt.*;
import java.io.*;
import java.net.Socket;

/**
 * Thread class; each tread is assigned to one player and one client;
 * Responsible for handling the communication, receiving and sending messages to and from a client.
 */
public class ThreadPlayer extends Thread {

    /**
     * Server's socket.
     */
    protected Socket socket;

    /**
     * Input stream from socket.
     */
    protected InputStream inputStream = null;

    /**
     * Input stream through which we send messages/objects to client.
     */
    protected ObjectInputStream objectInputStream = null;

    /**
     * Output stream from socket.
     */
    protected OutputStream outputStream = null;

    /**
     * Output stream through which we receive messages/objects from client.
     */
    protected ObjectOutputStream objectOutputStream = null;

    /**
     * Constructor; assigns socket and object streams.
     *
     * @param clientSocket socket of a client
     */
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

    /**
     * Method activated when starting the thread.
     */
    public void run() {
        System.out.println("SERVER: player got connected");

        try {
            objectOutputStream.writeBoolean(false);
            objectOutputStream.flush();
        } catch (IOException e) {
            close();
        }
    }

    /**
     * Method used to send game's board when the game starts.
     *
     * @param baseSide length of a side of a base
     * @param fields   board's fields
     * @param color    color of a player
     */
    public void sendBoard(int baseSide, Field[][] fields, Color color) {
        try {
            objectOutputStream.writeObject(new MessageBoard(baseSide, fields, color));
        } catch (IOException e) {
            close();
        }
    }

    /**
     * Method used to get the coordinates of a selected field form client.
     *
     * @return coordinates of the selected field
     */
    public Coordinates pieceSelect() {
        Coordinates msg = null;

        try {
            msg = (Coordinates) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            close();
        }

        return msg;
    }

    /**
     * Method used to send the array of coordinates of fields to which player is able to move.
     *
     * @param possibilities array of coordinates of field
     */
    public void sendPossibilities(Coordinates[] possibilities) {
        try {
            objectOutputStream.writeObject(possibilities);
        } catch (IOException e) {
            close();
        }
    }

    /**
     * Method used to get the message containing info about the move.
     *
     * @return MessageMove received from client
     */
    public MessageMove pieceMove() {
        MessageMove msg = null;

        try {
            msg = (MessageMove) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            close();
        }

        return msg;
    }

    /**
     * Method used to send the update for the board to client.
     *
     * @param chosenFields coordinates of fields to be updated
     * @param yourMove     boolean value if it is client's turn
     */
    public void updateBoard(Coordinates[] chosenFields, boolean yourMove) {
        try {
            objectOutputStream.writeObject(new MessageUpdate(chosenFields[0], chosenFields[1], yourMove));
        } catch (IOException e) {
            close();
        }
    }

    /**
     * Method used to send the update for the board to client when the game ends.
     *
     * @param chosenFields coordinated of fields to be updated
     * @param endGame      boolean value if game ended
     * @param youWon       boolean value if client won
     */
    public void updateBoard(Coordinates[] chosenFields, boolean endGame, boolean youWon) {
        try {
            objectOutputStream.writeObject(new MessageUpdate(chosenFields[0], chosenFields[1], endGame, youWon));
        } catch (IOException e) {
            close();
        }
    }

    /**
     * Close.
     */
    protected void close() {
        try {
            socket.close();
            objectOutputStream.close();
            objectInputStream.close();
        } catch (IOException e) {
            System.out.println("Could not close.");
            System.exit(-1);
        }
    }
}
