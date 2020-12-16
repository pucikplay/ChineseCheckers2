package tp.checkers.server.game;

import tp.checkers.message.MessageMove;
import tp.checkers.server.ThreadPlayer;

import java.awt.*;

public class Game {

    private final Board board;
    private final Player[] players;
    private final int playerNumber;
    private int currPlayer;

    public Game(int baseSide, int playerNumber, ThreadPlayer[] threads) {
        this.board = new Board(baseSide, playerNumber);
        this.players = new Player[playerNumber];
        this.playerNumber = playerNumber;
        this.currPlayer = 0;

        for (int i = 0; i < playerNumber; i++) {
            players[i] = new Player(threads[i], baseSide * (baseSide + 1)/2);
        }

    }


    public void play() {

        setup();
        Coordinates[] chosenFields = null;

        while (players.length > 1) {
            boolean pass = false;
            boolean reset;
            MessageMove messageMove;
            do {
                Coordinates clickedField = players[currPlayer].pieceSelect();

                //player passed
                if(clickedField.i == 0) {
                    pass = true;
                    players[currPlayer].sendPossibilities(new Coordinates[]{new Coordinates(0,0), new Coordinates(0,0)});
                    players[currPlayer].pieceMove();
                    chosenFields = Coordinates.newSimpleCoords(board.getBaseSide()/2);
                    messageMove = new MessageMove(chosenFields);
                    break;
                }

                Coordinates[] possibilities = Possibilities.getMoves(board.getField(clickedField), players[currPlayer].getEnemyColor());
                players[currPlayer].sendPossibilities(possibilities);

                messageMove = players[currPlayer].pieceMove();
                reset = messageMove.isReset();
                chosenFields = messageMove.getChosenFields();
            } while (reset);

            //check if piece moved into enemy base
            if (!pass && board.getField(chosenFields[0]).getBase() != players[currPlayer].getEnemyColor() &&
            board.getField(chosenFields[1]).getBase() == players[currPlayer].getEnemyColor()) {

                //check if all the pieces are in enemy base
                if(players[currPlayer].checkIfWon()){
                    players[currPlayer].setActive(false);
                    break;
                    //currently game ends if one player wins
                }
            }

            nextPlayer();

            for (int i = 0; i < players.length; i++) {
                players[i].updateBoard(chosenFields, currPlayer == i);
            }
            board.updateFields(messageMove);
            board.updateBoard();
        }

        //game ended
        for (int i = 0; i < players.length; i++) {
            players[i].updateBoard(chosenFields, true, currPlayer == i);
        }

    }

    private void nextPlayer() {
        currPlayer = (currPlayer + 1) % players.length;
        if(!players[currPlayer].isActive()) {
            nextPlayer();
        }
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
            players[i].updateBoard(Coordinates.newSimpleCoords(0), currPlayer == i);
        }
    }

}
