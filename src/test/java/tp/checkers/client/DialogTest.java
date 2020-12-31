package tp.checkers.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import tp.checkers.client.gui.Window;
import tp.checkers.client.gui.dialog.DialogFinish;
import tp.checkers.client.gui.dialog.DialogInit;

import javax.swing.*;

import static org.junit.Assert.*;

/**
 * Class that tests dialog boxes.
 */
@RunWith(MockitoJUnitRunner.class)
public class DialogTest {
    /**
     * Method responsible for testing Finish dialog box.
     */
    @Test
    public void DialogFinishTest() {
        Window window = new Window();

        DialogFinish dialogFinish = new DialogFinish(window, true);
        assertEquals(dialogFinish.getDefaultCloseOperation(), JDialog.DISPOSE_ON_CLOSE);
        assertTrue(dialogFinish.isModal());
    }

    /**
     * Method responsible for testing Init dialog box.
     */
    @Test
    public void DialogInitTest() {
        Window window = new Window();

        DialogInit dialogInit = new DialogInit(window);
        assertEquals(dialogInit.getDefaultCloseOperation(), JDialog.DISPOSE_ON_CLOSE);
        assertTrue(dialogInit.isModal());
        while (!dialogInit.isReady());
        assertEquals(dialogInit.playersNumber, 2);
        assertEquals(dialogInit.baseSide, 4);
        assertTrue(dialogInit.canJump);
        assertFalse(dialogInit.canLeaveBase);
    }
}