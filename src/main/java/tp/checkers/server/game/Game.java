package tp.checkers.server.game;

public class Game {

    private Board board;

    public Game(int baseSide, int playerNumber) {
        this.board = new Board(baseSide, playerNumber);
    }

    public Board getBoard() {
        return board;
    }

    public Field[][] getFields(){
        return board.getFields();
    }
}
