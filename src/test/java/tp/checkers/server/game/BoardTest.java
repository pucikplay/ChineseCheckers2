package tp.checkers.server.game;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;

public class BoardTest {

    @Test
    public void BoardSetupTest() {
        Board board = new Board(4, 2);
        Field[][] fields = board.getFields();
        String out = "";
        for(int i = 0; i < 4*4 + 3; i++) {
            for(int j = 0; j < 4*4 + 3; j++) {
                if(fields[i][j] == null) out += "O";
                else if(fields[i][j].getBase() == Color.GREEN) out += "G";
                else if(fields[i][j].getBase() == Color.BLUE) out += "B";
                else if(fields[i][j].getBase() == Color.YELLOW) out += "Y";
                else if(fields[i][j].getBase() == Color.RED) out += "R";
                else if(fields[i][j].getBase() == Color.MAGENTA) out += "M";
                else if(fields[i][j].getBase() == Color.GRAY) out += "W";
                else out += "E";
            }
            out += "\n";
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
                "OOOOOOOOOOOOOOOOOOO\n", out);
    }
}
