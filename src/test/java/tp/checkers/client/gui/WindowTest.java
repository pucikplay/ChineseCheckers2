package tp.checkers.client.gui;

import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;

public class WindowTest {

    @Test
    public void windowTest() {
        Window window = new Window();
        assertEquals(window.getTitle(), "Chinese checkers client");
        assertFalse(window.isResizable());
        assertEquals(window.getDefaultCloseOperation(), JFrame.EXIT_ON_CLOSE);
    }
}
