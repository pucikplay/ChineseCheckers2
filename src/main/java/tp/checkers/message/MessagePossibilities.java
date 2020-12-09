package tp.checkers.message;

import tp.checkers.server.game.MovePossibility;

import java.io.Serializable;

public class MessagePossibilities implements Serializable {
    public MovePossibility[] possibilities;

    public MessagePossibilities(MovePossibility[] possibilities) {
        this.possibilities = possibilities;
    }
}
