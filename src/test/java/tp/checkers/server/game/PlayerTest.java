package tp.checkers.server.game;

import org.junit.Test;
import org.mockito.Mock;
import tp.checkers.server.ThreadPlayer;

import java.awt.*;

import static org.junit.Assert.assertEquals;

public class PlayerTest {

    @Mock
    ThreadPlayer threadPlayer;

    @Test
    public void PlayerTest() {
        Player player = new Player(threadPlayer, 10);

        assertEquals(player.getLeftToWin(), 10);
        player.checkIfWon();
        assertEquals(player.getLeftToWin(), 9);

        assertEquals(player.getThread(), threadPlayer);

        player.setColor(Color.BLUE);
        assertEquals(player.getColor(), Color.BLUE);
        player.setEnemyColor(Color.GREEN);
        assertEquals(player.getEnemyColor(), Color.GREEN);

        assertEquals(player.isActive(), true);
        player.setActive(false);
        assertEquals(player.isActive(), false);


    }
}
