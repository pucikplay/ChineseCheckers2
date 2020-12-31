package tp.checkers.connection;

import com.github.stefanbirkner.systemlambda.SystemLambda;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tp.checkers.client.Client;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * Class that tests connection issues.
 */
public class ConnectionTest {
    /**
     * Stream to which we redirect system output.
     */
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    /**
     * Original system output stream.
     */
    private final PrintStream originalOut = System.out;

    /**
     * Method responsible for setting the output stream.
     */
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    /**
     * Method responsible for testing a situation when the client
     * is launched and there's no working server.
     *
     * @throws Exception exception during catching system exit
     */
    @Test
    public void noServerTest() throws Exception {
        int status = SystemLambda.catchSystemExit(() -> Client.main(null));

        assertEquals("Error: can't connect to the server." + System.lineSeparator() +
                "Make sure the server is working and it's not full." + System.lineSeparator() +
                "Could not close correctly.", outContent.toString().trim());

        assertEquals(status, -1);
    }

    /**
     * Method responsible for restoring the default output stream.
     */
    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
}
