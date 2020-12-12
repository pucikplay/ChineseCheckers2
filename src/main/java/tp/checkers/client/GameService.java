package tp.checkers.client;

import tp.checkers.client.gui.BoardUpdater;
import tp.checkers.client.gui.Panel;
import tp.checkers.client.gui.Window;
import tp.checkers.message.MessageMove;
import tp.checkers.server.game.Coordinates;
import tp.checkers.server.game.Field;

import javax.swing.*;
import java.awt.*;

public class GameService {
    private final Client client;
    private final Window window;
    private Panel panel;
    private Field[][] fields;
    private final int baseSide = 4; //to be passed from server!
    private final int arraySide = baseSide * 4 + 3;
    private int[] count;
    private final Coordinates[] chosenFields = {new Coordinates(0,0), new Coordinates(0,0)};
    private Coordinates[] possibilities;
    private boolean isMyTurn = false;

    public GameService(Client client, Window window, Field[][] fields, Color color) {
        this.client = client;
        this.window = window;
        this.fields = fields;

        countBoard();
        window.initBoard(this, fields, color);
    }

    public void startGame(Panel panel) {
        this.panel = panel;
        SwingWorker<Void, Void> updater = new BoardUpdater(client, this, window, panel);
        updater.execute();
    }

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

    public void commit() {
        if (isMyTurn) {
            if (chosenFields[1].i == 0) {
                chosenFields[1].i = chosenFields[0].i;
                chosenFields[1].j = chosenFields[0].j;
            }

            System.out.println("Sending commit message to server.");
            client.sendMove(new MessageMove(chosenFields));
            clearActiveFields();
            isMyTurn = false;
            SwingWorker<Void, Void> updater = new BoardUpdater(client, this, window, panel);
            updater.execute();
        }
    }

    public void reset() {
        if (isMyTurn) {
            System.out.println("Sending reset message to server.");
            client.sendMove(new MessageMove(true));
            clearActiveFields();
        }
    }

    private void clearActiveFields() {
        for (Coordinates c : chosenFields) {
            c.i = 0;
            c.j = 0;
        }

        possibilities = null;
        panel.repaint();
    }

    public boolean getIsMyTurn() {
        return isMyTurn;
    }

    public void setIsMyTurn(boolean b) {
        this.isMyTurn = b;
    }

    public int getFieldsNumber(int i) {
        return count[i];
    }

    public void setPossibilities(Coordinates[] possibilities) {
        this.possibilities = possibilities;
    }

    public Coordinates[] getPossibilities() {
        return possibilities;
    }

    public Field getField(int i, int j) {
        return fields[i][j];
    }

    public Color getPieceColor(int i, int j) {
        return getField(i, j).getPiece();
    }

    public void setPieceColor(int i, int j, Color color) {
        getField(i, j).setPiece(color);
    }

    public Color getBaseColor(int i, int j) {
        return getField(i, j).getBase();
    }

    public Coordinates getChosenField(int k) {
        return chosenFields[k];
    }

    public void setChosenField(int k, int i, int j) {
        chosenFields[k].i = i;
        chosenFields[k].j = j;
    }
}
