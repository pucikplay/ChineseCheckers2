package tp.checkers.client.gui;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tp.checkers.Coordinates;
import tp.checkers.Field;
import tp.checkers.client.gameservice.GameServicePlayed;
import tp.checkers.client.window.Window;
import tp.checkers.client.window.WindowPlayed;

import java.awt.*;
import java.awt.event.InputEvent;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

/**
 * Class that tests if a click is executed without problems.
 */
@RunWith(MockitoJUnitRunner.class)
public class ClickTest {
    /**
     * Mocked client's game service.
     */
    @Mock
    GameServicePlayed gameService;

    /**
     * Method responsible for testing whether a click on panel
     * is executed without problems, null pointer exceptions, etc.
     *
     * @throws AWTException exception thrown while problems with robot
     */
    @Test
    public void clickTest() throws AWTException {
        Field field = new Field();
        field.setBase(Color.RED);
        field.setPiece(Color.RED.darker());

        when(gameService.isMyTurn()).thenReturn(true);
        when(gameService.getField(anyInt(), anyInt())).thenReturn(new Field());
        when(gameService.getFieldsNumber(anyInt())).thenReturn(5);
        when(gameService.getField(anyInt(), anyInt())).thenReturn(field);
        when(gameService.getFieldsNumber(anyInt())).thenReturn(19);
        when(gameService.getBaseColor(anyInt(), anyInt())).thenReturn(field.getBase());
        when(gameService.getPieceColor(anyInt(), anyInt())).thenReturn(field.getPiece());
        when(gameService.getChosenField(anyInt())).thenReturn(new Coordinates(0, 0));
        when(gameService.getPossibilities()).thenReturn(null);

        Window window = new WindowPlayed();
        window.initBoard(gameService, Color.RED, 19);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();

        Robot robot = new Robot();
        robot.mouseMove(width / 2,height / 2);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);

        try {
            Thread.sleep(250);
        } catch (InterruptedException ignored) { }

        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
}
