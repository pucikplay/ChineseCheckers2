package tp.checkers.connection;

import org.junit.Test;
import tp.checkers.client.ClientConnector;

public class ConnectionTest {

    @Test(expected=NullPointerException.class)
    public void NoServerTest() {
        ClientConnector client = new ClientConnector();
    }
}
