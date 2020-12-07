package tp.checkers.server;

import java.net.Socket;

public class ThreadHost extends ThreadPlayer {
    public ThreadHost(Socket clientSocket) {
        super(clientSocket);
    }

    @Override
    protected void init() {
        System.out.println("SERVER: host got connected");
        //send a message to client that it should enable the choosing menu
        //get data from client with the number of other clients to accept
        //pass the number to ServerHandshake
    }
}
