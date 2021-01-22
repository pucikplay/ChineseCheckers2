package tp.checkers.client.gameservice;

import tp.checkers.Coordinates;
import tp.checkers.Field;
import tp.checkers.client.Client;
import tp.checkers.client.Panel;
import tp.checkers.message.MessageUpdate;

import java.awt.*;

public abstract class GameService {
    /**
     * Reference to the client connector.
     */
    protected final Client client;
    /**
     * Reference to client's window.
     */
    protected final Window window;
    /**
     * Array of fields of the game.
     */
    protected final Field[][] fields;
    /**
     * Length of the Fields array's side.
     */
    protected final int arraySide;
    /**
     * Array with coordinates of fields chosen by the user
     * - original field and destination field.
     */
    protected final Coordinates[] chosenFields = {new Coordinates(0,0), new Coordinates(0,0)};
    /**
     * Reference to panel.
     */
    protected Panel panel;
    /**
     * Array with coordinates of possible fields
     * where the player can move from chosen field.
     */
    protected Coordinates[] possibilities;
    /**
     * Information whether it's the player's turn now.
     */
    protected boolean myTurn = false;
    /**
     * Array with counted number of active fields on each level.
     */
    private int[] count;

    public GameService(Client client, Window window, Field[][] fields, int baseSide) {
        this.client = client;
        this.window = window;
        this.fields = fields;
        this.arraySide = baseSide * 4 + 3;
    }

    public abstract void startGame(Panel panel);

    /**
     * Initial method responsible for counting
     * how many active boards is there on each level.
     */
    protected void countBoard() {
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
     * Method responsible for calling the panel to repaint.
     */
    public void repaintPanel() {
        panel.repaint();
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
     * Getter of a field with given coordinates.
     *
     * @param i i-coordinate in Fields array
     * @param j j-coordinate in Fields array
     * @return field field
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
     * @param i     i-coordinate in Fields array
     * @param j     j-coordinate in Fields array
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

    public abstract Coordinates getChosenField(int i);

    public abstract Coordinates[] getPossibilities();
}
