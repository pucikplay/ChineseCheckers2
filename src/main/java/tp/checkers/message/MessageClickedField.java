package tp.checkers.message;

import java.io.Serializable;

public class MessageClickedField implements Serializable {
    public int i;
    public int j;

    public MessageClickedField(int i, int j) {
        this.i = i;
        this.j = j;
    }
}
