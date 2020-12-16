package tp.checkers.client.gui;

import org.junit.Test;
import org.mockito.Mock;
import tp.checkers.client.ClientConnector;

import javax.swing.*;

import static org.junit.Assert.*;

public class WindowTest {
    @Mock
    ClientConnector clientConnector;

    @Test
    public void windowTest() {
        Window window = new Window(clientConnector);
        assertEquals(window.getTitle(), "Chinese checkers client");
        assertFalse(window.isResizable());
        assertEquals(window.getDefaultCloseOperation(), JFrame.EXIT_ON_CLOSE);
    }
}
