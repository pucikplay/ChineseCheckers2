package tp.checkers.server;

import tp.checkers.message.MessageIfHost;
import tp.checkers.message.MessageInit;

import java.io.IOException;
import java.net.Socket;

public class ThreadHost extends ThreadPlayer {
    public ThreadHost(Socket clientSocket) { super(clientSocket); }

    @Override
    public void run() {
        System.out.println("SERVER: host got connected");

        try {
            objectOutputStream.flush();
            objectOutputStream.writeObject(new MessageIfHost(true));
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MessageInit getInitialData() {
        MessageInit msg = null;

        //to improve Messages; to improve reading the Message in Client (it should read a message from both Host and Player)
        try {
            msg = (MessageInit) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return msg;
    }
}
