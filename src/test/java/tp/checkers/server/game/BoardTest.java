package tp.checkers.server.game;

import org.junit.Test;
import org.mockito.Mock;
import tp.checkers.Coordinates;
import tp.checkers.Field;
import tp.checkers.message.MessageMove;
import tp.checkers.server.ThreadPlayer;

import java.awt.*;
import java.net.Socket;

import static org.junit.Assert.assertEquals;

public class BoardTest {

    @Test
    public void BoardSetupTest() {
        Board board = new Board(4, 2);
        Board board3 = new Board(3, 3);
        Board board4 = new Board(4, 4);
        Board board6 = new Board(5, 6);
        Field[][] fields = board.getFields();
        StringBuilder out = new StringBuilder();
        for(int i = 0; i < 4*4 + 3; i++) {
            for(int j = 0; j < 4*4 + 3; j++) {
                if(fields[i][j] == null) out.append("O");
                else if(fields[i][j].getBase() == Color.GREEN) out.append("G");
                else if(fields[i][j].getBase() == Color.BLUE) out.append("B");
                else if(fields[i][j].getBase() == Color.YELLOW) out.append("Y");
                else if(fields[i][j].getBase() == Color.RED) out.append("R");
                else if(fields[i][j].getBase() == Color.MAGENTA) out.append("M");
                else if(fields[i][j].getBase() == Color.GRAY) out.append("W");
                else out.append("E");
            }
            out.append("\n");
        }
        assertEquals("OOOOOOOOOOOOOOOOOOO\n" +
                "OOOOOOOOOOOOOROOOOO\n" +
                "OOOOOOOOOOOORROOOOO\n" +
                "OOOOOOOOOOORRROOOOO\n" +
                "OOOOOOOOOORRRROOOOO\n" +
                "OOOOOYYYYEEEEEMMMMO\n" +
                "OOOOOYYYEEEEEEMMMOO\n" +
                "OOOOOYYEEEEEEEMMOOO\n" +
                "OOOOOYEEEEEEEEMOOOO\n" +
                "OOOOOEEEEEEEEEOOOOO\n" +
                "OOOOBEEEEEEEEWOOOOO\n" +
                "OOOBBEEEEEEEWWOOOOO\n" +
                "OOBBBEEEEEEWWWOOOOO\n" +
                "OBBBBEEEEEWWWWOOOOO\n" +
                "OOOOOGGGGOOOOOOOOOO\n" +
                "OOOOOGGGOOOOOOOOOOO\n" +
                "OOOOOGGOOOOOOOOOOOO\n" +
                "OOOOOGOOOOOOOOOOOOO\n" +
                "OOOOOOOOOOOOOOOOOOO\n", out.toString());

        assertEquals(board.getBaseSide(), 4);
        assertEquals(board.getField(new Coordinates(6,6)), board.getFields()[6][6]);
        board.updateFields(new MessageMove(Coordinates.newSimpleCoords(9)));

    }
}
