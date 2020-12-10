package tp.checkers.message;

import tp.checkers.server.game.Coordinates;

import java.io.Serializable;

public class MessageMove implements Serializable {
    public boolean isReset;
    public Coordinates[] chosenFields;

    public MessageMove(boolean isReset) {
        this.isReset = isReset;
    }

    public MessageMove(Coordinates[] chosenFields) {
        this.isReset = false;
        this.chosenFields = chosenFields;
    }
}
