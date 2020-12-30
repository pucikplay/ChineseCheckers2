package tp.checkers.server.game;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tp.checkers.server.Server;
import tp.checkers.server.ThreadPlayer;

import java.io.*;
import java.net.Socket;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ThreadPlayerTest {

    @Mock
    Socket socket;
    OutputStream outputStream;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;

    @Test
    public void ThreadTest() throws Exception {
        //doNothing().when(thread).makeObjectInputStream(any(InputStream.class));
        //ThreadPlayer thread = new ThreadPlayer(socket);
    }
}
