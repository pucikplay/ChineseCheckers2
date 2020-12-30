package tp.checkers.connection;

import com.github.stefanbirkner.systemlambda.SystemLambda;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tp.checkers.client.Client;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ConnectionTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void noServerTest() throws Exception {
        int status = SystemLambda.catchSystemExit(() -> Client.main(null));

        assertEquals("Error: can't connect to the server." + System.lineSeparator() +
                "Make sure the server is working and it's not full." + System.lineSeparator() +
                "Could not close correctly.", outContent.toString().trim());

        assertEquals(status, -1);
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
}
