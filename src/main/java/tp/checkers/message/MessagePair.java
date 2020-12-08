package tp.checkers.message;

import java.io.Serializable;

public class MessagePair implements Serializable {
    public int x;
    public int y;

    public MessagePair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
