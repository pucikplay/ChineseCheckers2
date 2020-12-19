package tp.checkers.server.game;

import org.junit.Test;
import tp.checkers.Field;
import tp.checkers.server.game.possibilities.Possibilities;
import tp.checkers.server.game.possibilities.PossibilitiesJump;
import tp.checkers.server.game.possibilities.PossibilitiesSimple;

import tp.checkers.Coordinates;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class PossibilitiesTest {

    @Test
    public void movePossibilitiesTest() {
        Board board = new Board(4,2);
        Field[][] fields = board.getFields();
        Possibilities possibilitiesGetter = new PossibilitiesSimple(false);
        ArrayList<Coordinates> expected = new ArrayList<>(Arrays.asList(new Coordinates(5, 11), new Coordinates(5, 10)));
        ArrayList<Coordinates> real = possibilitiesGetter.getMoves(fields[4][11], Color.GREEN);
        assertEquals(real.get(0).i, expected.get(0).i);
        assertEquals(real.get(0).j, expected.get(0).j);
        assertEquals(real.get(1).i, expected.get(1).i);
        assertEquals(real.get(1).j, expected.get(1).j);

        expected.clear();
        real.clear();
        real = possibilitiesGetter.getMoves(fields[3][11], Color.GREEN);
        assertEquals(real, expected);

        possibilitiesGetter = new PossibilitiesJump(possibilitiesGetter, true);
        expected = new ArrayList<>(Arrays.asList(new Coordinates(5, 11), new Coordinates(5, 10)));
        real = possibilitiesGetter.getMoves(fields[4][11], Color.GREEN);
        assertEquals(real.get(0).i, expected.get(0).i);
        assertEquals(real.get(0).j, expected.get(0).j);
        assertEquals(real.get(1).i, expected.get(1).i);
        assertEquals(real.get(1).j, expected.get(1).j);

        expected.clear();
        real.clear();
        expected = new ArrayList<>(Arrays.asList(new Coordinates(3, 11), new Coordinates(5, 11), new Coordinates(5, 9)));
        real = possibilitiesGetter.getMoves(fields[3][11], Color.GREEN);
        assertEquals(real.get(0).i, expected.get(0).i);
        assertEquals(real.get(0).j, expected.get(0).j);
        assertEquals(real.get(1).i, expected.get(1).i);
        assertEquals(real.get(1).j, expected.get(1).j);

    }
}
