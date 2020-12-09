package tp.checkers.message;

import java.io.Serializable;

public class MessageMove implements Serializable {
    public int[] activeFields;

    public MessageMove(int[] activeFields) {
        this.activeFields = activeFields;
    }
}
