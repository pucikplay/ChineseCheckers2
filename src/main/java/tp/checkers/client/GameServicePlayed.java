package tp.checkers.client;

import tp.checkers.Coordinates;
import tp.checkers.client.gui.BoardUpdater;
import tp.checkers.client.gui.Panel;
import tp.checkers.client.gui.Window;
import tp.checkers.client.gui.WindowPlayed;
import tp.checkers.message.MessageMove;
import tp.checkers.Field;

import javax.swing.*;
import java.awt.*;

/**
 * Class handling the game in the side of client.
 */
public class GameServicePlayed extends GameService {

    /**
     * Default constructor of the class.
     *
     * @param client   reference to the client connector
     * @param window   reference to client's window
     * @param fields   array of fields of the game
     * @param color    color of the player
     * @param baseSide number of pieces in one side of the base
     */
    public GameServicePlayed(ClientConnector client, Window window, Field[][] fields, Color color, int baseSide) {
        super(client, window, fields, baseSide);

        countBoard();
        window.initBoard(this, color, arraySide);
    }

    /**
     * Method responsible for starting the game, creating new
     * background board updater and calling it.
     *
     * @param panel reference to panel
     */
    @Override
    public void startGame(Panel panel) {
        this.panel = panel;
        SwingWorker<Void, Void> updater = new BoardUpdater(this);
        updater.execute();
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
            SwingWorker<Void, Void> updater = new BoardUpdater(this);
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
     * Method responsible for setting the Turn label in window.
     *
     * @param text text we want to set there
     */
    public void setLabelTurnText(String text) {
        ((WindowPlayed) window).setLabelTurnText(text);
    }

    /**
     * Getter of myTurn.
     *
     * @return myTurn boolean
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
     * Setter of Possibilities array.
     *
     * @param clickedField field clicked by the user
     */
    public void setPossibilities(Coordinates clickedField) {
        this.possibilities = client.receiveMovePossibilities(clickedField);
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
