package tp.checkers.message;

import tp.checkers.Coordinates;

import java.io.Serializable;

/**
 * Class of message sent from server to all clients
 * that updates the clients' copies of fields array.
 */
public class MessageUpdate implements Serializable {
    /**
     * Coordinates of the original field.
     */
    private final Coordinates origin;

    /**
     * Coordinates of the destination field.
     */
    private final Coordinates destination;

    /**
     * Information whether it's this player's turn.
     */
    private boolean currPlayer = false;

    /**
     * Information whether it's the end of the game.
     */
    private boolean endGame = false;

    /**
     * Information whether the user won the game.
     */
    private boolean youWon = false;

    /**
     * Constructor of the class used during the game.
     *
     * @param origin coordinates of the original field
     * @param destination coordinates of the destination field
     * @param currPlayer information whether it's this player's turn
     */
    public MessageUpdate(Coordinates origin, Coordinates destination, boolean currPlayer) {
        this.origin = origin;
        this.destination = destination;
        this.currPlayer = currPlayer;
    }

    /**
     * Constructor of the class after the game has ended.
     *
     * @param origin coordinates of the original field
     * @param destination coordinates of the destination field
     * @param endGame information whether it's the end of the game
     * @param youWon information whether the user won the game
     */
    public MessageUpdate(Coordinates origin, Coordinates destination, boolean endGame, boolean youWon) {
        this.origin = origin;
        this.destination = destination;
        this.endGame = endGame;
        this.youWon = youWon;
    }

    /**
     * Getter of the original field's coordinates.
     *
     * @return original field's coordinates
     */
    public Coordinates getOrigin() {
        return origin;
    }

    /**
     * Getter of the destination field's coordinates.
     *
     * @return destination field's coordinates
     */
    public Coordinates getDestination() {
        return destination;
    }

    /**
     * Getter of the information whether it's this player's turn
     *
     * @return this player's turn
     */
    public boolean isCurrPlayer() {
        return currPlayer;
    }

    /**
     * Getter of the information whether it's the end of the game
     *
     * @return the end of the game
     */
    public boolean isEndGame() {
        return endGame;
    }

    /**
     * Getter of the information whether the user won the game.
     *
     * @return the user has won the game
     */
    public boolean isYouWon() {
        return youWon;
    }
}