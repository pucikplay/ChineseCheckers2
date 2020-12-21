package tp.checkers.client.gui;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tp.checkers.Coordinates;
import tp.checkers.Field;
import tp.checkers.client.GameService;

import javax.swing.*;

import java.awt.*;
import java.awt.event.InputEvent;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

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
