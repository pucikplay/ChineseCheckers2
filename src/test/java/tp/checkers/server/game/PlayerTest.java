package tp.checkers.server.game;

import org.junit.Test;
import tp.checkers.Coordinates;
import tp.checkers.server.ThreadPlayer;

import java.awt.*;

import static org.junit.Assert.*;

public class PlayerTest {

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

        player.setColor(Color.BLUE);
        assertEquals(player.getColor(), Color.BLUE);
        player.setEnemyColor(Color.GREEN);
        assertEquals(player.getEnemyColor(), Color.GREEN);

        assertTrue(player.isActive());
        player.setActive(false);
        assertFalse(player.isActive());


    }
}
