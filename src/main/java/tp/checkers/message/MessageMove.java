package tp.checkers.message;

import java.io.Serializable;

public class MessageMove implements Serializable {
    public boolean isReset;
    public int[] moveFields;

    public MessageMove(boolean isReset) {
        this.isReset = isReset;
    }

    public void addMoveFields(int[] moveFields) {
        this.moveFields = moveFields;
    }
}
