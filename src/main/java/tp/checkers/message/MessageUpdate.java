package tp.checkers.message;

import tp.checkers.server.game.Coordinates;

import java.io.Serializable;

public class MessageUpdate implements Serializable {
    public Coordinates origin, destination;
    public boolean currPlayer = false;

    public MessageUpdate(int origin_i, int origin_j, int destination_i, int destination_j, boolean b) {
        this.origin = new Coordinates(origin_i, origin_j);
        this.destination = new Coordinates(destination_i, destination_j);
        this.currPlayer = b;
    }
}