package tp.checkers.server.game;

import java.io.Serializable;

/**
 * Data class used to store coordinates of a field in format of two numbers
 */
public class Coordinates implements Serializable {

    /**
     * Y component
     */
    public int i;

    /**
     * X component
     */
    public int j;

    /**
     * Constructor;
     * Assigns provided values to X and Y components
     *
     * @param i provided Y component
     * @param j provided X component
     */
    public Coordinates(int i, int j) {
        this.i = i;
        this.j = j;
    }

    /**
     * Method responsible for creating a coordinates array of size 2
     * filled with only one value
     *
     * @param i value to be put in array
     * @return created array
     */
    public static Coordinates[] newSimpleCoords(int i) {
        return new Coordinates[]{new Coordinates(i,i), new Coordinates(i,i)};
    }

    /**
     * Method responsible for comparing two coordinate instances
     *
     * @param coordinates coordinates to compare to
     * @return boolean true if the same; false otherwise
     */
    public boolean compare(Coordinates coordinates) {
        return (this.i == coordinates.i && this.j == coordinates.j);
    }
}
