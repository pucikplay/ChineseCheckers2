package tp.checkers.message;

import tp.checkers.server.game.Coordinates;

import java.io.Serializable;

public class MessageUpdate implements Serializable {
    public Coordinates origin, destination;

    public MessageUpdate(int origin_i, int origin_j, int destination_i, int destination_j) {
        this.origin.i = origin_i;
        this.origin.j = origin_j;
        this.destination.i = destination_i;
        this.destination.j = destination_j;
    }
}