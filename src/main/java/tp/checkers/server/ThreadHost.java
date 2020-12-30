package tp.checkers.server;

import tp.checkers.message.MessageInit;

import java.io.IOException;
import java.net.Socket;

/**
 * ThreadHost is an variant of ThreadPlayer with implemented
 * communication necessary for initialising the game.
 */
public class ThreadHost extends ThreadPlayer {

    /**
     * Constructor; sets the socket in extending class.
     *
     * @param clientSocket socket to be set
     */
    public ThreadHost(Socket clientSocket) {
        super(clientSocket);
    }

    /**
     * Method activated when starting the thread.
     */
    @Override
    public void run() {
        System.out.println("SERVER: host got connected");

        try {
            objectOutputStream.writeBoolean(true);
            objectOutputStream.flush();
        } catch (IOException e) {
            close();
        }
    }

    /**
     * Method used to receive game initialising data.
     *
     * @return MessageInit with game data
     */
    public MessageInit getInitialData() {
        MessageInit msg = null;

        try {
            msg = (MessageInit) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return msg;
    }
}
