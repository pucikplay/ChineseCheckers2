package tp.checkers.server.game;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class PossibilitiesTest {

    @Test
    public void movePossibilitiesTest() {
        Board board = new Board(3,2);
        Field[][] fields = board.getFields();
        Coordinates[] list = Possibilities.getMoves(board, 11, 5);
        //for (Coordinates coordinates : list) {
        //    System.out.println(coordinates.i + " " + coordinates.j);
        //}
    }
}
