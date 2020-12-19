package tp.checkers.server.game;

import tp.checkers.Coordinates;
import tp.checkers.message.MessageMove;
import tp.checkers.server.ThreadPlayer;

import java.awt.*;

/**
 * Class used to store and get information about players in the game
 */
public class Player {

    /**
     * Thread to which a player is assigned.
     */
    private final ThreadPlayer thread;

    /**
     * Color of a player's base; this color .darker() is a color of player's pieces.
     */
    private Color color;

    /**
     * Color of enemy's base.
     */
    private Color enemyColor;

    /**
     * Boolean storing info if player won, and thus shouldn't make other moves, just watch others.
     */
    private boolean active;

    /**
     * Number of pieces left to be put in enemy's base.
     */
    private int leftToWin;



    /**
     * Constructor; Assigns a given thread to the player,
     * sets state to active and sets the number of pieces left to win.
     *
     * @param thread thread to be assigned
     * @param leftToWin number of pieces to be put in enemy's base in order to win
     */
    public Player(ThreadPlayer thread, int leftToWin) {
        this.thread = thread;
        this.active = true;
        this.leftToWin = leftToWin;
    }

    /**
     * Method used to get the thread assigned previously to the player.
     *
     * @return a thread
     */
    public ThreadPlayer getThread() {
        return thread;
    }

    /**
     * Method used to set player's color.
     *
     * @param color color to be assigned
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Method used to get player's color.
     *
     * @return player's color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Method used to set player's enemy's color.
     *
     * @param enemyColor color to be assigned
     */
    public void setEnemyColor(Color enemyColor) {
        this.enemyColor = enemyColor;
    }

    /**
     * Method used to get player's enemy's color.
     *
     * @return player's enemy's color
     */
    public Color getEnemyColor() {
        return enemyColor;
    }

    /**
     * Method used to check if a player is active.
     *
     * @return players state; true if active; false otherwise
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Method used to pass coordinated received by the thread.
     *
     * @return coordinates received by thread
     */
    public Coordinates pieceSelect() {
        return thread.pieceSelect();
    }

    /**
     * Method used to send array of coordinated of field to which a selected piece can move.
     *
     * @param possibilities array of coordinates of possible fields
     */
    public void sendPossibilities(Coordinates[] possibilities) {
        thread.sendPossibilities(possibilities);
    }

    /**
     * Method used to pass a MessageMove received by a thread.
     *
     * @return MessageMove received by thread
     */
    public MessageMove pieceMove() {
        return thread.pieceMove();
    }

    /**
     * Method used to send update messages through a thread.
     *
     * @param chosenFields array of coordinates of fields to be swapped
     * @param yourMove boolean value if it is player's turn now
     */
    public void updateBoard(Coordinates[] chosenFields, boolean yourMove) {
        thread.updateBoard(chosenFields, yourMove);
    }

    /**
     * Method used to send update messages about ended game through a thread.
     *
     * @param chosenFields array of coordinates of fields to be swapped
     * @param endGame boolean value if the game has ended
     * @param youWon boolean value if player won
     */
    public void updateBoard(Coordinates[] chosenFields, boolean endGame, boolean youWon) {
        thread.updateBoard(chosenFields, endGame, youWon);
    }

    /**
     * Method used to check if all the pieces are in enemy's base.
     * Called when a new piece moves into enemy's base, so also decreases leftToWin.
     *
     * @return boolean value if all the pieces are in the enemy's base
     */
    public boolean checkIfWon() {
        leftToWin--;
        return leftToWin == 0;
    }

    /**
     * Method used to increase leftToWin when piece leaves enemy's base.
     */
    public void pieceLeftBase() {
        leftToWin++;
    }

    /**
     * Method used to get the number of pieces remaining to be placed in enemy's base.
     *
     * @return amount of pieces to be placed in enemy's base
     */
    public int getLeftToWin() {
        return leftToWin;
    }

    /**
     * Method responsible for setting the player state.
     *
     * @param state boolean value of desired state
     */
    public void setActive(boolean state) {
        this.active = state;
    }
}
