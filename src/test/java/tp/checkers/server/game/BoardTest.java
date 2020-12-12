package tp.checkers.server.game;

import org.junit.Test;

import java.awt.*;

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
                else if(fields[i][j].getBase() == Color.ORANGE) out += "O";
                else if(fields[i][j].getBase() == Color.GRAY) out += "W";
                else out += "E";
            }
            System.out.println(out);
            out = "";
        }

        for(int i = 0; i < 4*4 + 3; i++) {
            for(int j = 0; j < 4*4 + 3; j++) {
                if(fields[i][j] == null) out += "O";
                else if(fields[i][j].getPiece() == Color.GREEN.darker()) out += "G";
                else if(fields[i][j].getPiece() == Color.BLUE.darker()) out += "B";
                else if(fields[i][j].getPiece() == Color.YELLOW.darker()) out += "Y";
                else if(fields[i][j].getPiece() == Color.RED.darker()) out += "R";
                else if(fields[i][j].getPiece() == Color.ORANGE.darker()) out += "O";
                else if(fields[i][j].getPiece() == Color.GRAY.darker()) out += "W";
                else if(fields[i][j].getPiece() == null) out += "E";
            }
            System.out.println(out);
            out = "";
        }
    }
}
