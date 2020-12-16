package tp.checkers.message;

import tp.checkers.server.game.Coordinates;

import java.io.Serializable;

public class MessageUpdate implements Serializable {
    private final Coordinates origin;
    private final Coordinates destination;
    private boolean currPlayer = false;
    private boolean endGame = false;
    private boolean youWon = false;

    public MessageUpdate(Coordinates origin, Coordinates destination, boolean currPlayer) {
        this.origin = origin;
        this.destination = destination;
        this.currPlayer = currPlayer;
    }

    // MessageUpdate when the game has ended.
    public MessageUpdate(Coordinates origin, Coordinates destination, boolean endGame, boolean youWon) {
        this.origin = origin;
        this.destination = destination;
        this.endGame = endGame;
        this.youWon = youWon;
    }

    public Coordinates getOrigin() {
        return origin;
    }

    public Coordinates getDestination() {
        return destination;
    }

    public boolean isCurrPlayer() {
        return currPlayer;
    }

    public boolean isEndGame() {
        return endGame;
    }

    public boolean isYouWon() {
        return youWon;
    }
}