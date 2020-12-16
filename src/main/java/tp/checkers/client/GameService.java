package tp.checkers.client;

import tp.checkers.client.gui.BoardUpdater;
import tp.checkers.client.gui.Panel;
import tp.checkers.client.gui.Window;
import tp.checkers.message.MessageMove;
import tp.checkers.server.game.Coordinates;
import tp.checkers.server.game.Field;

import javax.swing.*;
import java.awt.*;

/**
 * Class handling the game in the side of client.
 */
public class GameService {
    /**
     * Reference to the client connector.
     */
    private final ClientConnector client;

    /**
     * Reference to client's window.
     */
    private final Window window;

    /**
     * Reference to panel.
     */
    private Panel panel;

    /**
     * Array of fields of the game.
     */
    private final Field[][] fields;

    /**
     * Length of the Fields array's side.
     */
    private final int arraySide;

    /**
     * Array with counted number of active fields on each level.
     */
    private int[] count;

    /**
     * Array with coordinates of fields chosen by the user
     * - original field and destination field.
     */
    private final Coordinates[] chosenFields = {new Coordinates(0,0), new Coordinates(0,0)};

    /**
     * Array with coordinates of possible fields
     * where the player can move from chosen field.
     */
    private Coordinates[] possibilities;

    /**
     * Information whether it's the player's turn now.
     */
    private boolean myTurn = false;

    /**
     * Default constructor of the class.
     *
     * @param client reference to the client connector
     * @param window reference to client's window
     * @param fields array of fields of the game
     * @param color color of the player
     * @param baseSide number of pieces in one side of the base
     */
    public GameService(ClientConnector client, Window window, Field[][] fields, Color color, int baseSide) {
        this.client = client;
        this.window = window;
        this.fields = fields;
        this.arraySide = baseSide * 4 + 3;

        countBoard();
        window.initBoard(this, color, arraySide);
    }

    /**
     * Method responsible for starting the game, creating new
     * background board updater and calling it.
     *
     * @param panel reference to panel
     */
    public void startGame(Panel panel) {
        this.panel = panel;
        SwingWorker<Void, Void> updater = new BoardUpdater(client, this, window, panel);
        updater.execute();
    }

    /**
     * Initial method responsible for counting
     * how many active boards is there on each level.
     */
    private void countBoard() {
        count = new int[arraySide];

        for (int i = 1; i < arraySide; i++) {
            for (int j = 1; j < arraySide; j++) {
                if (fields[i][j] != null) {
                    count[i]++;
                }
            }
        }
    }

    /**
     * Method responsible for handling the Commit button,
     * passing the message with move to client connector
     * and calling the background board updater.
     */
    public void commit() {
        if (myTurn) {
            if (chosenFields[0].i == 0 && chosenFields[0].j == 0) {
                client.receiveMovePossibilities(new Coordinates(0, 0));
            }

            if (chosenFields[1].i == 0 && chosenFields[1].j == 0) {
                chosenFields[1].i = chosenFields[0].i;
                chosenFields[1].j = chosenFields[0].j;
            }

            System.out.println("Sending commit message to server.");
            client.sendMove(new MessageMove(chosenFields));
            clearActiveFields();
            myTurn = false;
            SwingWorker<Void, Void> updater = new BoardUpdater(client, this, window, panel);
            updater.execute();
        }
    }

    /**
     * Method responsible for handling the Reset button
     * and passing the reset message to client connector.
     */
    public void reset() {
        if (myTurn && chosenFields[0].i != 0 && chosenFields[0].j != 0) {
            System.out.println("Sending reset message to server.");
            client.sendMove(new MessageMove(true));
            clearActiveFields();
        }
    }

    /**
     * Method responsible for clearing the active fields
     * and possibilities arrays, then calling panel's repaint.
     */
    private void clearActiveFields() {
        for (Coordinates c : chosenFields) {
            c.i = 0;
            c.j = 0;
        }

        possibilities = null;
        panel.repaint();
    }

    /**
     * Getter of myTurn.
     *
     * @return myTurn
     */
    public boolean isMyTurn() {
        return myTurn;
    }

    /**
     * Setter of myTurn.
     *
     * @param newMyTurn new myTurn
     */
    public void setMyTurn(boolean newMyTurn) {
        this.myTurn = newMyTurn;
    }

    /**
     * Getter of number of active fields on given level.
     *
     * @param i the level
     * @return number of active fields
     */
    public int getFieldsNumber(int i) {
        return count[i];
    }

    /**
     * Setter of Possibilities array.
     *
     * @param possibilities new array of move possibilities
     */
    public void setPossibilities(Coordinates[] possibilities) {
        this.possibilities = possibilities;
    }

    /**
     * Getter of Possibilities array.
     *
     * @return array of move possibilities
     */
    public Coordinates[] getPossibilities() {
        return possibilities;
    }

    /**
     * Getter of a field with given coordinates.
     *
     * @param i i-coordinate in Fields array
     * @param j j-coordinate in Fields array
     * @return field
     */
    public Field getField(int i, int j) {
        return fields[i][j];
    }

    /**
     * Getter of a given field's piece color.
     *
     * @param i i-coordinate in Fields array
     * @param j j-coordinate in Fields array
     * @return color of field's piece
     */
    public Color getPieceColor(int i, int j) {
        return getField(i, j).getPiece();
    }

    /**
     * Setter of a given field's piece color.
     *
     * @param i i-coordinate in Fields array
     * @param j j-coordinate in Fields array
     * @param color new color of field's piece
     */
    public void setPieceColor(int i, int j, Color color) {
        getField(i, j).setPiece(color);
    }

    /**
     * Getter of a given field's base color.
     *
     * @param i i-coordinate in Fields array
     * @param j j-coordinate in Fields array
     * @return color of field
     */
    public Color getBaseColor(int i, int j) {
        return getField(i, j).getBase();
    }

    /**
     * Getter of first or second chosen field.
     *
     * @param k number 0 or 1
     * @return chosen field
     */
    public Coordinates getChosenField(int k) {
        return chosenFields[k];
    }

    /**
     * Setter of first or second chosen field.
     *
     * @param k number 0 or 1
     * @param i i-coordinate of the field
     * @param j j-coordinate of the field
     */
    public void setChosenField(int k, int i, int j) {
        chosenFields[k].i = i;
        chosenFields[k].j = j;
    }
}
