package tp.checkers.message;

import tp.checkers.server.game.Coordinates;

import java.io.Serializable;

public class MessageUpdate implements Serializable {
    private Coordinates origin;
    private Coordinates destination;
    private boolean currPlayer = false;
    private boolean endGame = false;
    private boolean youWon = false;

    public MessageUpdate(int origin_i, int origin_j, int destination_i, int destination_j, boolean currPlayer) {
        this.origin = new Coordinates(origin_i, origin_j);
        this.destination = new Coordinates(destination_i, destination_j);
        this.currPlayer = currPlayer;
    }

    // MessageUpdate when the game has ended.
    public MessageUpdate(int origin_i, int origin_j, int destination_i, int destination_j, boolean endGame, boolean youWon) {
        this.origin = new Coordinates(origin_i, origin_j);
        this.destination = new Coordinates(destination_i, destination_j);
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