package tp.checkers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for Coordinates class
 */
public class CoordinatesTest {

    /**
     * Testing methods from Coordinates
     */
    @Test
    public void CoordinatesTest() {
        Coordinates[] coordinates = Coordinates.newSimpleCoords(3);
        assertEquals(coordinates.length, 2);
        Coordinates test = new Coordinates(3,3);
        assertEquals(test.i, 3);
        assertEquals(test.j, 3);
        for (int i = 0; i < 2; i++) {
            assertEquals(test.compare(coordinates[i]), true);
        }
    }
}
