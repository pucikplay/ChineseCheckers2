package tp.checkers.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tp.checkers.Coordinates;
import tp.checkers.Field;
import tp.checkers.client.gui.Panel;
import tp.checkers.client.gui.Window;
import tp.checkers.message.MessageUpdate;
import tp.checkers.server.game.Board;

import java.awt.Color;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * The type Game service test.
 */
@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {
    /**
     * Mocked client connector.
     */
    @Mock
    ClientConnector client;

    /**
     * Mocked panel.
     */
    @Mock
    Panel panel;

    /**
     * Method responsible for testing the game service's behavior.
     *
     * @throws InterruptedException exception thrown after sleep is interrupted
     */
    @Test
    public void gameServiceTest() throws InterruptedException {
        MessageUpdate msg = new MessageUpdate(new Coordinates(8, 8), new Coordinates(9, 9), true);
        when(client.receiveUpdates()).thenReturn(msg);

        Board board = new Board(4, 2);
        Field[][] fields = board.getFields();

        GameService gameService = new GameService(client, new Window(), fields, Color.GREEN, 4);
        assertFalse(gameService.isMyTurn());

        gameService.startGame(panel);
        Thread.sleep(1000);

        assertTrue(gameService.isMyTurn());

        gameService.commit();

        gameService.setChosenField(0, 3, 4);
        assertEquals(gameService.getChosenField(0).j, 4);

        gameService.reset();

        gameService.setPossibilities(new Coordinates(1, 1));
        assertNull(gameService.getPossibilities());
    }
}
