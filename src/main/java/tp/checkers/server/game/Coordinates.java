package tp.checkers.server.game;

import java.io.Serializable;

public class Coordinates implements Serializable {
    public int i;
    public int j;

    public Coordinates(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public static Coordinates[] newSimpleCoords(int i) {
        return new Coordinates[]{new Coordinates(i,i), new Coordinates(i,i)};
    }

    public boolean compare(Coordinates coordinates) {
        return (this.i == coordinates.i && this.j == coordinates.j);
    }
}
