package tp.checkers.server;

import tp.checkers.message.MessageInit;

import java.io.IOException;
import java.net.Socket;

public class ThreadHost extends ThreadPlayer {
    public ThreadHost(Socket clientSocket) { super(clientSocket); }

    @Override
    public void run() {
        System.out.println("SERVER: host got connected");

        try {
            objectOutputStream.writeBoolean(true);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
