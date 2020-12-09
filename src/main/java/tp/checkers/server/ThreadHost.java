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
            objectOutputStream.writeObject(new MessageIfHost(true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getClientsNumber() {
        int clientsNumber = 1;

        //to improve Messages; to improve reading the Message in Client (it should read a message from both Host and Player)
        try {
            MessageInit init = (MessageInit) objectInputStream.readObject();
            clientsNumber = init.num;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return clientsNumber;
    }
}
