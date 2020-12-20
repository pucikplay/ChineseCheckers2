package tp.checkers;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;

public class FieldTest {

    @Test
    public void FieldTest() {

        Field field = new Field();
        Field neighbor = new Field();
        Field secondNeighbor = new Field();
        field.setNeighbors(new Field[]{neighbor});
        neighbor.setNeighbors(new Field[]{secondNeighbor});
        secondNeighbor.setBase(Color.GREEN);
        field.setCoordinates(new Coordinates(4,6));

        field.setBase(Color.BLACK);
        field.setPiece(Color.BLUE);
        assertEquals(field.getCoordinates().compare(new Coordinates(4,6)), true);
        assertEquals(field.getBase(), Color.BLACK);
        assertEquals(field.getPiece(), Color.BLUE);
        assertEquals(field.getSecondNeighborsBase(0), Color.GREEN);
    }
}
