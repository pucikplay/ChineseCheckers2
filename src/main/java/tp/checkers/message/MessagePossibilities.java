package tp.checkers.message;

import tp.checkers.server.game.Coordinates;

import java.io.Serializable;

public class MessagePossibilities implements Serializable {
    public Coordinates[] possibilities;

    public MessagePossibilities(Coordinates[] possibilities) {
        this.possibilities = possibilities;
    }
}
