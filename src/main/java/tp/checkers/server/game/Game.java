package tp.checkers.server.game;

import tp.checkers.Coordinates;
import tp.checkers.message.MessageMove;
import tp.checkers.server.ThreadPlayer;
import tp.checkers.server.game.possibilities.Possibilities;
import tp.checkers.server.game.possibilities.PossibilitiesJump;
import tp.checkers.server.game.possibilities.PossibilitiesSimple;

import java.awt.*;

/**
 * Game class is used to store and get information about the current game.
 */
public class Game {

    /**
     * Game's board.
     */
    private final Board board;

    /**
     * Class that searches for move possibilities.
     */
    private Possibilities possibilitiesGetter;

    /**
     * Array of players in the game.
     */
    private final Player[] players;

    /**
     * Number of players in the game.
     */
    private final int playerNumber;

    /**
     * Index of a current player.
     */
    private int currPlayer;

    /**
     * Constructor; Creates game with given parameters.
     *
     * @param baseSide length of a side of a base
     * @param playerNumber number of players
     * @param threads array of threads to which the players will be assigned
     * @param canLeaveBase information whether the player can leave enemy's base
     * @param canJump information whether the player can jump over other pieces
     */
    public Game(int baseSide, int playerNumber, ThreadPlayer[] threads, boolean canLeaveBase, boolean canJump) {
        this.board = new Board(baseSide, playerNumber);
        this.players = new Player[playerNumber];
        this.playerNumber = playerNumber;
        this.currPlayer = (int) (Math.random() * playerNumber);

        this.possibilitiesGetter = new PossibilitiesSimple(canLeaveBase);
        if (canJump) {
            this.possibilitiesGetter = new PossibilitiesJump(possibilitiesGetter, canLeaveBase);
        }

        for (int i = 0; i < playerNumber; i++) {
            players[i] = new Player(threads[i], baseSide * (baseSide + 1)/2);
        }
    }

    /**
     * Method executing the actions needed to play the game.
     */
    public void play() {
        setup();
        Coordinates[] chosenFields = null;

        while (players.length > 1) {
            boolean pass = false;
            boolean reset;
            MessageMove messageMove;

            do {
                Coordinates clickedField = players[currPlayer].pieceSelect();
                if (clickedField == null) {
                    return;
                }

                    //player passed
                if (clickedField.i == 0) {
                    pass = true;
                    players[currPlayer].sendPossibilities(Coordinates.newSimpleCoords(0));
                    players[currPlayer].pieceMove();
                    chosenFields = Coordinates.newSimpleCoords((4 * board.getBaseSide() + 3 )/2);
                    messageMove = new MessageMove(chosenFields);
                    break;
                }

                Coordinates[] possibilities = possibilitiesGetter.getMoves(board.getField(clickedField), players[currPlayer].getEnemyColor()).toArray(new Coordinates[0]);
                players[currPlayer].sendPossibilities(possibilities);

                messageMove = players[currPlayer].pieceMove();
                reset = messageMove.isReset();
                chosenFields = messageMove.getChosenFields();

            } while (reset);

            //check if the player didn't leave the opponent's base
            if (!pass && board.getField(chosenFields[0]).getBase() == players[currPlayer].getEnemyColor() &&
                    board.getField(chosenFields[1]).getBase() != players[currPlayer].getEnemyColor()) {
                players[currPlayer].pieceLeftBase();
            }
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
            board.updateNeighbors();
        }

        //game ended
        for (int i = 0; i < players.length; i++) {
            players[i].updateBoard(chosenFields, true, currPlayer == i);
        }

    }

    /**
     * Method used to get the index of the next player
     */
    private void nextPlayer() {
        currPlayer = (currPlayer + 1) % players.length;
        if(!players[currPlayer].isActive()) {
            nextPlayer();
        }
    }

    /**
     * Method used to initialise the game
     * Sets player's base colors according to the number of players
     */
    private void setup() {

        Color[] colors = new Color[]{Color.GREEN, Color.BLUE, Color.YELLOW, Color.RED, Color.MAGENTA, Color.GRAY};

        if (playerNumber == 4) {
            players[0].setColor(0, colors);
            players[1].setColor(1, colors);
            players[2].setColor(3, colors);
            players[3].setColor(4, colors);
        }
        else {
            for (int i = 0; i < playerNumber; i++) {
                players[i].setColor(i * 6 / playerNumber, colors);
            }
        }

        for (int i = 0; i < playerNumber; i++) {
            players[i].getThread().sendBoard(board.getBaseSide(), board.getFields(), players[i].getColor());
            players[i].updateBoard(Coordinates.newSimpleCoords(0), currPlayer == i);
        }
    }

}
