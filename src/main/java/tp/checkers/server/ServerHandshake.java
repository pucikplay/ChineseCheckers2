package tp.checkers.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerHandshake {

    private ServerSocket serverSocket = null;

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
            Socket client = serverSocket.accept();
            ThreadPlayer host = new ThreadHost(client);
            host.start();
            //get the number of other clients from the host
        } catch (IOException e) {
            System.out.println("Accept failed: 4444");
            System.exit(-1);
        }

        //accept [number given by the host] new clients and put them into an array
        for (int i = 0; i < 2; i++) {
            try {
                Socket client = serverSocket.accept();
                ThreadPlayer player = new ThreadPlayer(client);
                player.start();
            } catch (IOException e) {
                System.out.println("Accept failed: 4444");
                System.exit(-1);
            }
        }

        //pass the server socket and the array of clients to the Server class
    }

    public static void main(String[] args) {
        ServerHandshake handshake = new ServerHandshake();
        handshake.createConnection();
    }
}
