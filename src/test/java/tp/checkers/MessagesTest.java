package tp.checkers;

import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import tp.checkers.client.ClientConnector;
import tp.checkers.client.gui.Window;
import tp.checkers.message.MessageInit;

public class MessagesTest
{

    @Mock
    ClientConnector clientConnector;

    @Test
    public void messageInitTest() {
        Window window = new Window(clientConnector);
        MessageInit msg = window.initGameData();
        assertEquals(msg.num, 2);
    }
}
