package tp.checkers.server.game;

import java.io.Serializable;

public class Coordinates implements Serializable {
    public int i;
    public int j;

    public Coordinates(int i, int j) {
        this.i = i;
        this.j = j;
    }
}
