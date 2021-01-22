package tp.checkers.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import tp.checkers.client.dialog.DialogInitPlayed;
import tp.checkers.client.window.Window;
import tp.checkers.client.window.WindowPlayed;

import javax.swing.*;

import static org.junit.Assert.*;

/**
 * Class that tests dialog boxes.
 */
@RunWith(MockitoJUnitRunner.class)
public class DialogTest {
    /**
     * Method responsible for testing Init dialog box.
     */
    @Test
    public void DialogInitTest() {
        Window window = new WindowPlayed();

        DialogInitPlayed dialogInit = new DialogInitPlayed();
        assertEquals(dialogInit.getDefaultCloseOperation(), JDialog.DISPOSE_ON_CLOSE);
        assertTrue(dialogInit.isModal());
        while (!dialogInit.isReady());
        assertEquals(dialogInit.getPlayersNumber(), 2);
        assertEquals(dialogInit.getBaseSide(), 4);
        assertTrue(dialogInit.getCanJump());
        assertFalse(dialogInit.getCanLeaveBase());
    }
}