package tp.checkers.message;

import tp.checkers.server.game.Coordinates;

import java.io.Serializable;

public class MessageUpdate implements Serializable {
    public Coordinates origin;
    public Coordinates destination;
    public boolean currPlayer = false;
    public boolean endGame = false;
    public boolean youWon = false;

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
}