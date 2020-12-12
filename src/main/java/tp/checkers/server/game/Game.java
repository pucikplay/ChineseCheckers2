package tp.checkers.server.game;

import tp.checkers.message.MessageClickedField;
import tp.checkers.message.MessageMove;
import tp.checkers.server.ThreadPlayer;

import java.awt.*;

public class Game {

    private Board board;
    private Player[] players;
    private int playerNumber;
    private int currPlayer;

    public Game(int baseSide, int playerNumber, ThreadPlayer[] threads) {
        this.board = new Board(baseSide, playerNumber);
        this.players = new Player[playerNumber];
        this.playerNumber = playerNumber;
        this.currPlayer = 0;
        for (int i = 0; i < playerNumber; i++) {
            players[i] = new Player();
            players[i].setThread(threads[i]);
        }
    }

    public Board getBoard() {
        return board;
    }

    public Field[][] getFields(){
        return board.getFields();
    }

    public void play() {

        setup();
        MessageClickedField messageClickedField;
        MessageMove messageMove = null;
        Coordinates[] possibilities = null;
        boolean reset;

        while (players.length > 1) {
            do {
                messageClickedField = players[currPlayer].pieceSelect();

                possibilities = Possibilities.getMoves(board, messageClickedField.i, messageClickedField.j);
                players[currPlayer].sendPossibilities(possibilities);

                messageMove = players[currPlayer].pieceMove();
                reset = messageMove.isReset;
            } while (reset);
            reset = true;

            nextPlayer();
            for (int i = 0; i < players.length; i++) {
                players[i].updateBoard(messageMove, currPlayer == i);
            }
            board.updateFields(messageMove);
            board.updateBoard();
        }

    }

    private void setup() {
        if (playerNumber == 2) {
            players[0].setColor(Color.GREEN);
            players[1].setColor(Color.RED);
        }
        else if (playerNumber == 3) {
            players[0].setColor(Color.GREEN);
            players[1].setColor(Color.YELLOW);
            players[2].setColor(Color.ORANGE);
        }
        else if (playerNumber == 4) {
            players[0].setColor(Color.GREEN);
            players[1].setColor(Color.BLUE);
            players[2].setColor(Color.RED);
            players[3].setColor(Color.ORANGE);
        }
        else if (playerNumber == 6) {
            players[0].setColor(Color.GREEN);
            players[1].setColor(Color.BLUE);
            players[2].setColor(Color.YELLOW);
            players[3].setColor(Color.RED);
            players[4].setColor(Color.ORANGE);
            players[5].setColor(Color.GRAY);
        }

        for (int i = 0; i < playerNumber; i++) {
            players[i].getThread().sendFields(board.getFields());
            players[i].getThread().sendColor(players[i].getColor());
            players[i].updateBoard(new MessageMove(new Coordinates[]{new Coordinates(0, 0), new Coordinates(0, 0)}), currPlayer == i);
        }
    }

    private void nextPlayer() {
        currPlayer = (currPlayer + 1) % players.length;
    }

}
