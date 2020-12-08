package tp.checkers.server.game;

import org.junit.Test;

public class BoardTest {

    @Test
    public void BoardSetupTest() {
        Board board = new Board(4, 0);
        Pieces[][] pieces = board.getPieces();
        String out = "";
        for(int i = 0; i < 19; i++) {
            for(int j = 0; j < 19; j++) {
                if(pieces[i][j] == null) out += "O";
                else out += "X";
            }
            System.out.println(out);
            out = "";
        }
    }
}
