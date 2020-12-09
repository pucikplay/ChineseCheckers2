package tp.checkers.server.game;

import tp.checkers.message.MessageClickedField;
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

        while (players.length > 1) {

            players[currPlayer].pieceSelect();
            MovePossibility[] possibilities = new MovePossibility[2];
            possibilities[0] = new MovePossibility();
            possibilities[1] = new MovePossibility();
            possibilities[0].i = 9;
            possibilities[0].j = 9;
            possibilities[1].i = 10;
            possibilities[1].j = 10;
            players[currPlayer].sendPossibilities(possibilities);
            players[currPlayer].pieceMove();
            nextPlayer();
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
        }
    }

    private void nextPlayer() {
        currPlayer = (currPlayer + 1) % playerNumber;
    }

}
