package tp.checkers.message;

import java.io.Serializable;

public class MessageInit implements Serializable {
    public int num;

    public MessageInit(int num) {
        this.num = num;
    }
}
