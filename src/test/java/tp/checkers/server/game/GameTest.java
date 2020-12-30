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

@RunWith(MockitoJUnitRunner.class)
public class GameTest {

    @Mock
    Player player1, player2;

    @Test
    public void GameTestMethod() throws IOException {

        Game game = new Game(3, 2, new ThreadPlayer[]{null, null}, false, true);
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
