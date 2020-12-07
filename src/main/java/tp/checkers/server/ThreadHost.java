package tp.checkers.server;

import tp.checkers.message.MessageInit;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ThreadHost extends ThreadPlayer {
    public ThreadHost(Socket clientSocket) {
        super(clientSocket);
    }

    public int init() {
        System.out.println("SERVER: host got connected");

        int clientsNumber = 1;

        //to improve Messages; to improve reading the Message in Client (it should read a message from both Host and Player)
        try {
            objectOutputStream.writeObject(new MessageInit(2));
            MessageInit init = (MessageInit) objectInputStream.readObject();
            clientsNumber = init.num;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return clientsNumber;
    }
}
