package tp.checkers.server.game;

import tp.checkers.message.MessageMove;
import tp.checkers.server.ThreadPlayer;

import java.awt.*;

public class Game {

    private Board board;
    private Player[] players;
    private int playerNumber;
    private int currPlayer;
    private Coordinates clickedField;
    private MessageMove messageMove = null;
    private Coordinates[] possibilities = null;
    private boolean reset;

    public Game(int baseSide, int playerNumber, ThreadPlayer[] threads) {
        this.board = new Board(baseSide, playerNumber);
        this.players = new Player[playerNumber];
        this.playerNumber = playerNumber;
        this.currPlayer = 0;
        for (int i = 0; i < playerNumber; i++) {
            players[i] = new Player();
            players[i].setThread(threads[i]);
            players[i].setActive(true);
            players[i].setLeftToWin(baseSide * (baseSide + 1)/2);
        }
    }

    public void play() {

        setup();

        while (players.length > 1) {
            do {
                clickedField = players[currPlayer].pieceSelect();

                possibilities = Possibilities.getMoves(board, clickedField.i, clickedField.j);
                players[currPlayer].sendPossibilities(possibilities);

                messageMove = players[currPlayer].pieceMove();
                reset = messageMove.isReset;
            } while (reset);
            reset = true;

            if (board.getFields()[messageMove.chosenFields[0].i][messageMove.chosenFields[0].j].getBase() != players[currPlayer].getEnemyColor() &&
            board.getFields()[messageMove.chosenFields[1].i][messageMove.chosenFields[1].j].getBase() == players[currPlayer].getEnemyColor()) {
                players[currPlayer].setActive(!players[currPlayer].checkIfWon());
                //can send a message of winning
            }

            nextPlayer();

            for (int i = 0; i < players.length; i++) {
                players[i].updateBoard(messageMove, currPlayer == i);
            }
            board.updateFields(messageMove);
            board.updateBoard();
        }
        for (int i = 0; i < players.length; i++) {
            //players[i].updateBoard();
            //notify all of the end
        }
        System.out.println("Game ended");

    }

    private void setup() {
        if (playerNumber == 2) {
            players[0].setColor(Color.GREEN);
            players[0].setEnemyColor(Color.RED);
            players[1].setColor(Color.RED);
            players[1].setEnemyColor(Color.GREEN);
        }
        else if (playerNumber == 3) {
            players[0].setColor(Color.GREEN);
            players[0].setEnemyColor(Color.RED);
            players[1].setColor(Color.YELLOW);
            players[1].setEnemyColor(Color.GRAY);
            players[2].setColor(Color.MAGENTA);
            players[2].setEnemyColor(Color.BLUE);
        }
        else if (playerNumber == 4) {
            players[0].setColor(Color.GREEN);
            players[0].setEnemyColor(Color.RED);
            players[1].setColor(Color.BLUE);
            players[1].setEnemyColor(Color.MAGENTA);
            players[2].setColor(Color.RED);
            players[2].setEnemyColor(Color.GREEN);
            players[3].setColor(Color.MAGENTA);
            players[3].setEnemyColor(Color.BLUE);
        }
        else if (playerNumber == 6) {
            players[0].setColor(Color.GREEN);
            players[0].setEnemyColor(Color.RED);
            players[1].setColor(Color.BLUE);
            players[1].setEnemyColor(Color.MAGENTA);
            players[2].setColor(Color.YELLOW);
            players[2].setEnemyColor(Color.GRAY);
            players[3].setColor(Color.RED);
            players[3].setEnemyColor(Color.GREEN);
            players[4].setColor(Color.MAGENTA);
            players[4].setEnemyColor(Color.BLUE);
            players[5].setColor(Color.GRAY);
            players[5].setEnemyColor(Color.YELLOW);
        }

        for (int i = 0; i < playerNumber; i++) {
            players[i].getThread().sendBoard(board.getBaseSide(), board.getFields(), players[i].getColor());
            players[i].updateBoard(new MessageMove(new Coordinates[]{new Coordinates(0, 0), new Coordinates(0, 0)}), currPlayer == i);
        }
    }

    private void nextPlayer() {
        currPlayer = (currPlayer + 1) % players.length;
        if(!players[currPlayer].isActive()) {
            nextPlayer();
        }
    }

}
