package tp.checkers.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import tp.checkers.entities.EntityGames;
import tp.checkers.message.MessageInit;
import tp.checkers.server.game.Game;
import tp.checkers.server.springtest.DatabaseConnector;
import tp.checkers.server.springtest.SpringTest;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The type Server.
 */
public class Server {

    /**
     * Socket through which the thread communicates with the client.
     */
    private ServerSocket serverSocket = null;

    /**
     * Array of threads; each communicates with a single client.
     */
    ThreadPlayer[] players = null;

    boolean play;

    int gameNo;

    /**
     * Constructor, establishes connection on socket.
     */
    public Server() {
        try {
            serverSocket = new ServerSocket(4444);
            System.out.println("Server is working");
        } catch (IOException e) {
            System.out.println("Could not listen on port 4444");
            System.exit(-1);
        }
    }

    /**
     * Method responsible for accepting connections from clients.
     */
    public void createConnection() {
        int clientsNumber = 1;
        int baseSide = 4;
        boolean canLeaveBase = false;
        boolean canJump = true;
        try {
            Socket client = serverSocket.accept();
            ThreadHost host = new ThreadHost(client);
            host.start();

            ObjectOutputStream objectOutputStream = host.getOutputStream();
            ObjectInputStream objectInputStream = host.getInputStream();

            play = objectInputStream.readBoolean();

            if (play) {
                MessageInit msg = host.getInitialData();
                clientsNumber = msg.getPlayersNumber();
                baseSide = msg.getBaseSide();
                canLeaveBase = msg.getCanLeaveBase();
                canJump = msg.getCanJump();

                players = new ThreadPlayer[clientsNumber];
                players[0] = host;
            } else {
                ApplicationContext appContext = new ClassPathXmlApplicationContext("spring-configuration.xml");
                DatabaseConnector connector = (DatabaseConnector) appContext.getBean("connector");
                System.out.println("0");
                EntityGames[] games = connector.getGames();
                System.out.println(games[0].getStartDate());
                System.out.println("1");
                objectOutputStream.writeObject(games);
                System.out.println("2");
                gameNo = objectInputStream.readInt();
                System.out.println("3");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Accept failed: 4444");
            System.exit(-1);
        }

        if (play) {
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

            startGame(baseSide, clientsNumber, players, canLeaveBase, canJump);
        }

    }

    /**
     * Method responsible for starting the game described by those parameters.
     *
     * @param baseSide length of a base
     * @param playerNumber number of players in game
     * @param threads array of client threads
     * @param canLeaveBase setting whether player can leave opponent's base
     * @param canJump setting whether player can jump over other pieces
     */
    private void startGame(int baseSide, int playerNumber, ThreadPlayer[] threads, boolean canLeaveBase, boolean canJump) {

        Game game = new Game(baseSide, playerNumber, threads, canLeaveBase, canJump);

        game.play();
    }


    /**
     * Main function, lunches server.
     *
     * @param args unused inline arguments
     */
    public static void main(String[] args) {
        Server handshake = new Server();
        handshake.createConnection();
    }

    /**
     * Sets server socket.
     *
     * @param serverSocket the server socket
     */
    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

}
