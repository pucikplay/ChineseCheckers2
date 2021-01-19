package tp.checkers.server.game;

import tp.checkers.Coordinates;
import tp.checkers.Field;
import tp.checkers.message.MessageMove;

import java.awt.*;
import java.util.Arrays;

/**
 * Board class is used to store and get information about the hexed board
 * from a game.
 */
public class Board {

    /**
     * Array of fields.
     */
    private Field[][] fields = null;

    /**
     * Length of the side of the base.
     */
    private final int baseSide;

    /**
     * Number of players.
     */
    private final int playerNumber;

    /**
     * Length of a whole array of fields.
     */
    private final int end;

    /**
     * Default constructor.
     *
     * @param baseSide     determining the size of a board
     * @param playerNumber number of players
     */
    public Board(int baseSide, int playerNumber) {
        this.baseSide = baseSide;
        this.playerNumber = playerNumber;
        this.end = 4 * baseSide + 2;

        createBoard();
        placePlayers();
        updateNeighbors();
    }

    /**
     * Method used to get fields from a board.
     *
     * @return array of fields
     */
    public Field[][] getFields(){
        return this.fields;
    }

    /**
     * Method used to get a single field.
     *
     * @param coordinates coordinates of a field
     * @return field at given coordinates
     */
    public Field getField(Coordinates coordinates) {
        return fields[coordinates.i][coordinates.j];
    }

    /**
     * Method used to get the length of a side of a base from a board.
     *
     * @return length of a side of a base
     */
    public int getBaseSide() {
        return baseSide;
    }

    /**
     * Method responsible for updating neighbors list of a field.
     */
    public void updateNeighbors() {
        for (int i = 1; i < end; i++) {
            for (int j = 1; j < end; j++) {
                if (fields[i][j] != null) {
                    fields[i][j].setNeighbors(new Field[]{
                            fields[i + 1][j],
                            fields[i + 1][j - 1],
                            fields[i][j - 1],
                            fields[i - 1][j],
                            fields[i - 1][j + 1],
                            fields[i][j + 1]
                    });
                }
            }
        }
    }

    /**
     * Method responsible for updating the board based on received message.
     *
     * @param messageMove message with information which fields to switch
     */
    public void updateFields(MessageMove messageMove) {
        Color color = fields[messageMove.getChosenFields()[0].i][messageMove.getChosenFields()[0].j].getPiece();
        fields[messageMove.getChosenFields()[0].i][messageMove.getChosenFields()[0].j].setPiece(null);
        fields[messageMove.getChosenFields()[1].i][messageMove.getChosenFields()[1].j].setPiece(color);
    }

    /**
     * Method used to initialise a new board.
     */
    private void createBoard() {
        fields = new Field[end + 1][end + 1];

        for (int i = baseSide + 1; i < end - baseSide; i++) {
            for (int j = baseSide + 1; j < end - baseSide; j++) {
                fields[i][j] = new Field();
            }
        }

        for (int i = end - baseSide; i < end; i++) {
            for (int j = baseSide + 1; j <= 2 * baseSide; j++) {
                if (i + j < baseSide + 1 + end){
                    fields[i][j] = new Field();
                    fields[j][i] = new Field();

                    fields[i][j].setBase(Color.GREEN);
                    fields[j][i].setBase(Color.MAGENTA);
                }
            }
        }

        for (int i = end - 2 * baseSide; i < end - baseSide; i++) {
            for (int j = 1; j <= baseSide; j++) {
                if (i + j >= end - baseSide){
                    fields[i][j] = new Field();
                    fields[j][i] = new Field();

                    fields[i][j].setBase(Color.BLUE);
                    fields[j][i].setBase(Color.RED);
                }
            }
        }

        for (int i = baseSide + 1; i <= 2 * baseSide; i++) {
            for (int j = baseSide + 1; j <= 2 * baseSide; j++) {
                if (i + j < 3 * baseSide + 2){
                    fields[i][j] = new Field();

                    fields[i][j].setBase(Color.YELLOW);
                }
            }
        }

        for (int i = end - 2 * baseSide; i < end - baseSide; i++) {
            for (int j = end - 2 * baseSide; j < end - baseSide; j++) {
                if (i + j >= 2 * end - 3 * baseSide - 1){
                    fields[i][j] = new Field();

                    fields[i][j].setBase(Color.GRAY);
                }
            }
        }
        for (int i = 1; i < end; i++) {
            for (int j = 1; j < end; j++) {
                if (fields[i][j] != null) {
                    fields[i][j].setCoordinates(new Coordinates(i, j));
                }
            }
        }
    }

    /**
     * Method responsible for placing players' pieces on the board.
     * Player arrangement is dependent on the number of players.
     */
    private void placePlayers() {
        if (playerNumber == 2) {
            placePlayers(new Color[]{Color.GREEN, Color.RED});
        }
        else if (playerNumber == 3) {
            placePlayers(new Color[]{Color.GREEN, Color.YELLOW, Color.MAGENTA});
        }
        else if (playerNumber == 4) {
            placePlayers(new Color[]{Color.GREEN, Color.BLUE, Color.RED, Color.MAGENTA});
        }
        else if (playerNumber == 6) {
            placePlayers(new Color[]{Color.GREEN, Color.BLUE, Color.YELLOW,Color.RED, Color.MAGENTA, Color.GRAY});
        }
    }

    /**
     * Method responsible for placing players' pieces in bases.
     *
     * @param colors list of colors of bases in which to place pieces
     */
    private void placePlayers(Color[] colors) {
        for (int i = 1; i < end; i++) {
            for (int j = 1; j < end; j++) {
                if (fields[i][j] != null && Arrays.asList(colors).contains(fields[i][j].getBase())) {
                    fields[i][j].setPiece(fields[i][j].getBase().darker());
                }
            }
        }
    }
}