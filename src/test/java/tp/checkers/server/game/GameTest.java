package tp.checkers.server.game;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tp.checkers.Field;
import tp.checkers.server.ThreadPlayer;

import java.awt.*;
import java.io.*;
import java.net.Socket;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * Tests for Game class
 */
@RunWith(MockitoJUnitRunner.class)
public class GameTest {

    /**
     * Mocks of players for a game
     */
    @Mock
    Player player1, /**
     * The Player 2.
     */
    player2;

    /**
     * Testing game with mocked players
     */
    @Test
    public void GameTestMethod() {

        Game game = new Game(3, 2, new ThreadPlayer[]{null, null}, false, true, null);
        game.setPlayers(new Player[]{player1, player2});


        doNothing().when(player1).sendBoard(anyInt(), any(Field[][].class), any(Color.class));
        doNothing().when(player2).sendBoard(anyInt(), any(Field[][].class), any(Color.class));
        doNothing().when(player1).setColor(anyInt(), any(Color[].class));
        doNothing().when(player2).setColor(anyInt(), any(Color[].class));
        doReturn(true).when(player1).isActive();
        doReturn(true).when(player2).isActive();
        game.play();
        game.nextPlayer();

    }
}
