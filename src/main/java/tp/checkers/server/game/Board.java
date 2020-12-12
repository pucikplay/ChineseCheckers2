package tp.checkers.server.game;

import tp.checkers.message.MessageClickedField;
import tp.checkers.message.MessageMove;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Board {

    private Field[][] fields = null;
    private static int baseSide;
    private static int playerNumber;
    private static int end;

    public Board(int baseSide, int playerNumber) {
        this.baseSide = baseSide;
        this.playerNumber = playerNumber;
        this.end = 4*baseSide + 2;

        createBoard();
        placePlayers();
        updateBoard();
    }

    public Field[][] getFields(){
        return this.fields;
    }

    public void updateBoard() {
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


    public void updateFields(MessageMove messageMove) {
        Color color = fields[messageMove.chosenFields[0].i][messageMove.chosenFields[0].j].getPiece();
        fields[messageMove.chosenFields[0].i][messageMove.chosenFields[0].j].setPiece(null);
        fields[messageMove.chosenFields[1].i][messageMove.chosenFields[1].j].setPiece(color);
    }

    private void createBoard() {
        fields = new Field[end + 1][end + 1];

        for (int i = baseSide + 1; i < end - baseSide; i++) {
            for (int j = baseSide + 1; j < end - baseSide; j++) {
                fields[i][j] = new Field();
            }
        }

        for (int i = end - baseSide; i < end; i++) {
            for (int j = baseSide + 1; j <= 2*baseSide; j++) {
                if (i + j < baseSide + 1 + end){
                    fields[i][j] = new Field();
                    fields[j][i] = new Field();

                    fields[i][j].setBase(Color.GREEN);
                    fields[j][i].setBase(Color.MAGENTA);
                }
            }
        }

        for (int i = end - 2*baseSide; i < end - baseSide; i++) {
            for (int j = 1; j <= baseSide; j++) {
                if (i + j >= end - baseSide){
                    fields[i][j] = new Field();
                    fields[j][i] = new Field();

                    fields[i][j].setBase(Color.BLUE);
                    fields[j][i].setBase(Color.RED);
                }
            }
        }

        for (int i = baseSide + 1; i <= 2*baseSide; i++) {
            for (int j = baseSide + 1; j <= 2*baseSide; j++) {
                if (i + j < 3*baseSide + 2){
                    fields[i][j] = new Field();

                    fields[i][j].setBase(Color.YELLOW);
                }
            }
        }

        for (int i = end - 2*baseSide; i < end - baseSide; i++) {
            for (int j = end - 2*baseSide; j < end - baseSide; j++) {
                if (i + j >= 2*end - 3*baseSide - 1){
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
