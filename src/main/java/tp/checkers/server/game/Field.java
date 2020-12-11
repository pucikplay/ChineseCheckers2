package tp.checkers.server.game;

import java.awt.*;
import java.io.Serializable;

public class Field implements Serializable {
    private Color piece = null;
    private Color base = null;
    private Field[] neighbors;
    private Coordinates coordinates;

    public Field() {
        neighbors = new Field[6];
    }

    public void setPiece(Color piece) {
        this.piece = piece;
    }

    public void setBase(Color base) {
        this.base = base;
    }

    public Color getBase() {
        return base;
    }

    public Color getPiece() {
        return piece;
    }

    public void setNeighbors(Field[] neighbors) {
        this.neighbors = neighbors;
    }

    public Field[] getNeighbors() {
        return neighbors;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
