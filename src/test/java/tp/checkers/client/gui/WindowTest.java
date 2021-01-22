package tp.checkers.client.gui;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tp.checkers.client.gameservice.GameServicePlayed;
import tp.checkers.client.window.Window;
import tp.checkers.client.window.WindowPlayed;

import javax.swing.*;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * Class that tests the window class.
 */
@RunWith(MockitoJUnitRunner.class)
public class WindowTest {
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
     * Method responsible for testing whether the window's
     * parameters are initialised correctly.
     */
    @Test
    public void windowParametersTest() {
        Window window = new WindowPlayed();
        assertEquals(window.getTitle(), "Chinese checkers client");
        assertFalse(window.isResizable());
        assertEquals(window.getDefaultCloseOperation(), JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Method responsible for testing whether the label Turn
     * can be set before initialisation of it.
     */
    @Test(expected = NullPointerException.class)
    public void noLabelInitialisedTest() {
        WindowPlayed window = new WindowPlayed();
        window.setLabelTurnText("Test text");
    }

    /**
     * Method responsible for testing the initBoard method,
     * whether it executes without problems.
     */
    @Test
    public void initBoardTest() {
        Window window = new WindowPlayed();
        window.initBoard(gameService, Color.GREEN, 19);
        assertEquals("Initialisation of the board and panel", outContent.toString().trim());
    }

    /**
     * Method responsible for restoring the default output stream.
     */
    @After
    public void restoreStream() {
        System.setOut(originalOut);
    }
}
