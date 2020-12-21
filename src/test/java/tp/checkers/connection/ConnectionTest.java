package tp.checkers.connection;

import com.github.stefanbirkner.systemlambda.SystemLambda;
import org.junit.Test;
import tp.checkers.client.Client;

import static org.junit.Assert.assertEquals;

public class ConnectionTest {

    @Test
    public void noServerTest() throws Exception {
        int status = SystemLambda.catchSystemExit(() -> {
            Client.main(null);
        });

        assertEquals(status, -1);
    }

    @Test
    public void idkTest() {
        //ClientConnector client = new ClientConnector();
    }
}
