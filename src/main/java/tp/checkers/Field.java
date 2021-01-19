package tp.checkers;

import java.awt.*;
import java.io.Serializable;

/**
 * Field class; used to store and get information about a field in a board
 */
public class Field implements Serializable {

    /**
     * Color of a piece sitting on the field
     */
    private Color piece = null;

    /**
     * Color of a base that the field is in
     */
    private Color base = null;

    /**
     * Array of field's neighbors
     */
    private Field[] neighbors;

    /**
     * Coordinates of a field
     */
    private Coordinates coordinates;

    /**
     * Constructor; creates a neighbors array
     */
    public Field() {
        neighbors = new Field[6];
    }

    /**
     * Method used to set the color of a piece sitting on the field
     *
     * @param piece color of a piece
     */
    public void setPiece(Color piece) {
        this.piece = piece;
    }

    /**
     * Method used to set the color of a field's base
     *
     * @param base color of a base
     */
    public void setBase(Color base) {
        this.base = base;
    }

    /**
     * Method used to get the color of a base that the field is in
     *
     * @return color of a base
     */
    public Color getBase() {
        return base;
    }

    /**
     * Method used to get the color of a piece that is sitting on the field
     *
     * @return color of a piece
     */
    public Color getPiece() {
        return piece;
    }

    /**
     * Method used to set the neighboring fields
     *
     * @param neighbors array of fields that neighbor the field
     */
    public void setNeighbors(Field[] neighbors) {
        this.neighbors = neighbors;
    }

    /**
     * Method used to get field's neighbors
     *
     * @return array of neighbors
     */
    public Field[] getNeighbors() {
        return neighbors;
    }

    /**
     * Method used to get the field's neighbor with given index
     *
     * @param i index of a neighbor
     * @return neighbor under a given index
     */
    public Field getNeighbor(int i) { return neighbors[i]; }

    /**
     * Method used to get the field's neighbor piece color
     *
     * @param i index of a neighbor
     * @return color of a piece that is sitting on that neighbor
     */
    public Color getNeighborsPiece(int i) { return neighbors[i].getPiece(); }

    /**
     * Method used to get a neighbor's neighbor under a index i
     *
     * @param i index of a neighbor and the neighbor's neighbor
     * @return neighbor 's neighbor
     */
    public Field getSecondNeighbor(int i) {
        return neighbors[i].getNeighbor(i);
    }

    /**
     * Method used to get the neighbor's neighbor piece color
     *
     * @param i index of a neighbor and the neighbor's neighbor
     * @return neighbor 's neighbor piece color
     */
    public Color getSecondNeighborsPiece(int i) { return getSecondNeighbor(i).getPiece(); }

    /**
     * Method used to get the neighbor's neighbor base color
     *
     * @param i index of a neighbor and the neighbor's neighbor
     * @return neighbor 's neighbor base color
     */
    public Color getSecondNeighborsBase(int i) { return getSecondNeighbor(i).getBase(); }

    /**
     * Method used to set the coordinated of a field
     *
     * @param coordinates coordinates to be set
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Method used to get the coordinates of a field
     *
     * @return coordinates of the field
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

}
