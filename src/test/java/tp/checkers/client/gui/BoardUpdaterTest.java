package tp.checkers.client.gui;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tp.checkers.client.GameService;

@RunWith(MockitoJUnitRunner.class)
public class BoardUpdaterTest {
    @Mock
    GameService gameService;

    @Test
    public void boardUpdaterLoadTest() {
        BoardUpdater updater = new BoardUpdater(gameService);
    }
}
