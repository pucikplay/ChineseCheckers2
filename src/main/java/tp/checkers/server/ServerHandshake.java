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
        int clientsNumber = 1;
        ThreadPlayer[] players = null;

        try {
            Socket client = serverSocket.accept();
            ThreadHost host = new ThreadHost(client);
            host.start();
            clientsNumber = host.init();

            players = new ThreadPlayer[clientsNumber];
            players[0] = host;
        } catch (IOException e) {
            System.out.println("Accept failed: 4444");
            System.exit(-1);
        }

        for (int i = 1; i < clientsNumber; i++) {
            try {
                Socket client = serverSocket.accept();
                ThreadPlayer player = new ThreadPlayer(client);
                player.start();
                players[i] = player;
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
