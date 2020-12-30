package tp.checkers.client.gui;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tp.checkers.client.GameService;

import javax.swing.*;

import java.awt.*;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class WindowTest {

    @Test
    public void windowTest() {
        Window window = new Window();
        assertEquals(window.getTitle(), "Chinese checkers client");
        assertFalse(window.isResizable());
        assertEquals(window.getDefaultCloseOperation(), JFrame.EXIT_ON_CLOSE);
    }

    @Test(expected = NullPointerException.class)
    public void noLabelInitialisedTest() {
        Window window = new Window();
        window.setLabelTurnText("Test text");
    }

    @Mock
    GameService gameService;

    @Test
    public void initGameTest() {
        Window window = new Window();
        window.initBoard(gameService, Color.GREEN, 19);
    }
}
