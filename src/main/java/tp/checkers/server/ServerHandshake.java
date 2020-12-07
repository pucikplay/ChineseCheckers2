package tp.checkers.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerHandshake {

    private ServerSocket serverSocket = null;
    private Socket client = null;
    private InputStream inputStream = null;
    private ObjectInputStream objectInputStream = null;
    private OutputStream outputStream = null;
    private ObjectOutputStream objectOutputStream = null;

    public ServerHandshake() {
        try {
            serverSocket = new ServerSocket(4444);
        } catch (IOException e) {
            System.out.println("Could not listen on port 4444");
            System.exit(-1);
        }
    }

    public void createConnection() {
        try {
            client = serverSocket.accept();

            System.out.println("SERVER: connected");

            inputStream = client.getInputStream();
            objectInputStream = new ObjectInputStream(inputStream);

            outputStream = client.getOutputStream();
            objectOutputStream = new ObjectOutputStream(outputStream);

        } catch (IOException e) {
            System.out.println("Accept failed: 4444");
            System.exit(-1);
        }
    }

    public static void main(String[] args) {
        ServerHandshake handshake = new ServerHandshake();
        handshake.createConnection();
    }
}
