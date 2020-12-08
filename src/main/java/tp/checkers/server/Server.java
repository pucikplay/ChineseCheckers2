package tp.checkers.server;

import tp.checkers.server.game.Game;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket = null;
    ThreadPlayer[] players = null;

    public Server() {
        try {
            serverSocket = new ServerSocket(4444);
        } catch (IOException e) {
            System.out.println("Could not listen on port 4444");
            System.exit(-1);
        }
    }

    public void createConnection() {
        int clientsNumber = 1;

        try {
            Socket client = serverSocket.accept();
            ThreadHost host = new ThreadHost(client);
            host.start();
            clientsNumber = host.getClientsNumber();
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

        startGame(clientsNumber);

    }

    private void startGame(int playerNumber) {

        Game game = new Game(4, playerNumber);

        for(int i = 0; i < playerNumber; i++) {
            players[i].sendFields(game.getFields());
        }
        while(true);
        
    }

    public static void main(String[] args) {
        Server handshake = new Server();
        handshake.createConnection();
    }
}
