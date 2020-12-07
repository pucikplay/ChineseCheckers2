package tp.checkers.server;

import java.io.*;
import java.net.Socket;

public class ThreadPlayer extends Thread {
    protected Socket socket;
    private InputStream inputStream = null;
    private ObjectInputStream objectInputStream = null;
    private OutputStream outputStream = null;
    private ObjectOutputStream objectOutputStream = null;

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
        init();
    }

    protected void init() {
        System.out.println("SERVER: player got connected");
    }
}
