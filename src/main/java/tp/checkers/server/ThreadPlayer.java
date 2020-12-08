package tp.checkers.server;

import java.io.*;
import java.net.Socket;

public class ThreadPlayer extends Thread {
    protected Socket socket;
    protected InputStream inputStream = null;
    protected ObjectInputStream objectInputStream = null;
    protected OutputStream outputStream = null;
    protected ObjectOutputStream objectOutputStream = null;

    public ThreadPlayer(Socket clientSocket) {
        this.socket = clientSocket;

        try {
            inputStream = socket.getInputStream();
            objectInputStream = new ObjectInputStream(inputStream);
            outputStream = socket.getOutputStream();
            objectOutputStream = new ObjectOutputStream(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        System.out.println("SERVER: player got connected");

        try {
            objectOutputStream.writeBoolean(false);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
