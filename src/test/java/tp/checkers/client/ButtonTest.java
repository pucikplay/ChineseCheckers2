package tp.checkers.client;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tp.checkers.client.gui.button.ButtonCommit;
import tp.checkers.client.gui.button.ButtonReset;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * Class that tests if the buttons work.
 */
@RunWith(MockitoJUnitRunner.class)
public class ButtonTest {
    /**
     * Mocked client's game service.
     */
    @Mock
    GameServicePlayed gameService;

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
     * Method responsible for testing the Commit button.
     */
    @Test
    public void buttonCommitTest() {
        ButtonCommit buttonCommit = new ButtonCommit(gameService);
        buttonCommit.doClick();
        assertEquals("Commit button has been clicked", outContent.toString().trim());
    }

    /**
     * Method responsible for testing the Reset button.
     */
    @Test
    public void buttonResetTest() {
        ButtonReset buttonReset = new ButtonReset(gameService);
        buttonReset.doClick();
        assertEquals("Commit button has been clicked", outContent.toString().trim());
    }

    /**
     * Method responsible for restoring the default output stream.
     */
    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
}
