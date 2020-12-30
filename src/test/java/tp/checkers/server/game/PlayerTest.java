package tp.checkers.server.game;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tp.checkers.Coordinates;
import tp.checkers.Field;
import tp.checkers.message.MessageMove;
import tp.checkers.server.ThreadPlayer;

import java.awt.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PlayerTest {

    @Mock
    ThreadPlayer threadPlayer;

    @Test
    public void PlayerTest() {
        Player player = new Player(threadPlayer, 10);

        assertEquals(player.getLeftToWin(), 10);
        player.checkIfWon();
        assertEquals(player.getLeftToWin(), 9);
        player.pieceLeftBase();
        assertEquals(player.getLeftToWin(), 10);

        assertEquals(player.getThread(), threadPlayer);
        //player.updateBoard(Coordinates.newSimpleCoords(1), true);

        player.setColor(0, new Color[]{Color.BLUE, Color.GREEN});
        assertEquals(player.getColor(), Color.BLUE);
        assertEquals(player.getEnemyColor(), Color.GREEN);

        assertTrue(player.isActive());
        player.setActive(false);
        assertFalse(player.isActive());

        doReturn(new Coordinates(1,2)).when(threadPlayer).pieceSelect();
        assertEquals(player.pieceSelect().i, 1);
        assertEquals(player.pieceSelect().j, 2);

        doNothing().when(threadPlayer).sendPossibilities(any(Coordinates[].class));
        player.sendPossibilities(Coordinates.newSimpleCoords(0));

        doReturn(new MessageMove(true)).when(threadPlayer).pieceMove();
        assertTrue(player.pieceMove().isReset());

        doNothing().when(threadPlayer).sendBoard(anyInt(), any(Field[][].class), any(Color.class));
        player.sendBoard(3, null, null);

        doNothing().when(threadPlayer).updateBoard(any(Coordinates[].class), anyBoolean());
        player.updateBoard(Coordinates.newSimpleCoords(0), true);

        doNothing().when(threadPlayer).updateBoard(any(Coordinates[].class), anyBoolean(), anyBoolean());
        player.updateBoard(Coordinates.newSimpleCoords(0), true, true);

    }
}
