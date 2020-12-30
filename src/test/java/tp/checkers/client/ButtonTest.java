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

@RunWith(MockitoJUnitRunner.class)
public class ButtonTest {
    @Mock
    GameService gameService;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void buttonCommitTest() {
        ButtonCommit buttonCommit = new ButtonCommit(gameService);
        buttonCommit.doClick();
        assertEquals("Commit button has been clicked", outContent.toString().trim());
    }

    @Test
    public void buttonResetTest() {
        ButtonReset buttonReset = new ButtonReset(gameService);
        buttonReset.doClick();
        assertEquals("Commit button has been clicked", outContent.toString().trim());
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
}
