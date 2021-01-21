package tp.checkers.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import tp.checkers.Coordinates;
import tp.checkers.entities.EntityGames;
import tp.checkers.entities.EntityMoves;
import tp.checkers.message.MessageInit;
import tp.checkers.message.MessageMove;
import tp.checkers.message.MessageUpdate;
import tp.checkers.server.game.Board;
import tp.checkers.server.game.Game;
import tp.checkers.server.springtest.DatabaseConnector;
import tp.checkers.server.springtest.SpringTest;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.Date;

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

    ObjectOutputStream objectOutputStream = null;

    ObjectInputStream objectInputStream = null;

    ApplicationContext appContext = new ClassPathXmlApplicationContext("spring-configuration.xml");
    DatabaseConnector connector = (DatabaseConnector) appContext.getBean("connector");

    ThreadHost host;

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
            host = new ThreadHost(client);
            host.start();

            objectOutputStream = host.getOutputStream();
            objectInputStream = host.getInputStream();

            play = objectInputStream.readBoolean();

            if (play) {
                MessageInit msg = host.getInitialData();
                clientsNumber = msg.getPlayersNumber();
                baseSide = msg.getBaseSide();
                canLeaveBase = msg.getCanLeaveBase();
                canJump = msg.getCanJump();

                long date = new Date().getTime();
                connector.storeGame(baseSide, clientsNumber, new Timestamp(date));

                players = new ThreadPlayer[clientsNumber];
                players[0] = host;
            } else {

                EntityGames[] games = connector.getGames();
                System.out.println(games[0].getStartDate());
                objectOutputStream.writeObject(games);
                gameNo = objectInputStream.readInt();

                spectate(gameNo);
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

            startGame(baseSide, clientsNumber, players, canLeaveBase, canJump, connector);
        }

    }

    /**
     * Method used when spectating
     *
     * @param gameNo Id of game to spectate
     * @throws IOException
     */
    private void spectate(int gameNo) throws IOException {

        EntityGames game = connector.getGame(gameNo);

        Board board = new Board(game.getSizeOfBase(), game.getNumberOfPlayers());
        host.sendBoard(board.getBaseSide(), board.getFields(), null);

        EntityMoves[] moves = connector.getMoves(gameNo);

        for (int i = 0; i < moves.length; i++) {
            objectInputStream.readBoolean();
            objectOutputStream.writeObject(new MessageUpdate(new Coordinates(moves[i].getiOrigin(), moves[i].getjOrigin()), new Coordinates(moves[i].getiDestination(), moves[i].getjDestination()), false));
        }
        objectInputStream.readBoolean();
        objectOutputStream.writeObject(new MessageUpdate(null, null, true, false));

    }

    /**
     * Method responsible for starting the game described by those parameters.
     *
     * @param baseSide length of a base
     * @param playerNumber number of players in game
     * @param threads array of client threads
     * @param canLeaveBase setting whether player can leave opponent's base
     * @param canJump setting whether player can jump over other pieces
     * @param connector
     */
    private void startGame(int baseSide, int playerNumber, ThreadPlayer[] threads, boolean canLeaveBase, boolean canJump, DatabaseConnector connector) {

        Game game = new Game(baseSide, playerNumber, threads, canLeaveBase, canJump, connector);

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
