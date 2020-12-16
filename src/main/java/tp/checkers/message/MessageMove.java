package tp.checkers.message;

import tp.checkers.server.game.Coordinates;

import java.io.Serializable;

public class MessageMove implements Serializable {
    private boolean reset = false;
    private Coordinates[] chosenFields;

    public MessageMove(boolean reset) {
        this.reset = reset;
    }

    public MessageMove(Coordinates[] chosenFields) {
        this.chosenFields = chosenFields;
    }

    public boolean isReset() {
        return reset;
    }

    public Coordinates[] getChosenFields() {
        return chosenFields;
    }
}
