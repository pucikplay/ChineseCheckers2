package tp.checkers.message;

import java.io.Serializable;

public class MessageIfHost implements Serializable {
    public boolean host;

    public MessageIfHost(boolean host) { this.host = host; }
}
