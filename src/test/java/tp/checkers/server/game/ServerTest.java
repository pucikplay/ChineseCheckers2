package tp.checkers.server.game;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tp.checkers.server.Server;
import tp.checkers.server.ThreadHost;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ServerTest {

    @Mock
    ServerSocket serverSocket;
    Socket client;
    ThreadHost threadHost;


    @Test
    public void ServerTests() throws IOException {

        doReturn(client).when(serverSocket).accept();
        Server server = new Server();
        server.setServerSocket(serverSocket);

        //when(ThreadHost.makeNew(any(Socket.class))).thenReturn(threadHost);

        //server.createConnection();

    }
}
