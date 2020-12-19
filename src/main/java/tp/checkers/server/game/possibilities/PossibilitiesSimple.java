package tp.checkers.server.game.possibilities;

import tp.checkers.Coordinates;
import tp.checkers.Field;

import java.awt.*;
import java.util.ArrayList;

/**
 * Class consisting of methods used to calculate the fields to which a player can move
 * (only around the chosen field).
 */
public class PossibilitiesSimple implements Possibilities {
    /**
     * Setting whether the piece can leave the enemy's base after entering it.
     */
    public boolean canLeaveBase;

    /**
     * Default constructor of the class.
     *
     * @param canLeaveBase setting whether the piece can leave the enemy's base after entering it.
     */
    public PossibilitiesSimple(boolean canLeaveBase) {
        this.canLeaveBase = canLeaveBase;
    }

    /**
     * Method responsible for providing a list of fields available to move to.
     *
     * @param field field in which the piece to move is located
     * @param enemyColor color of a player's enemy's base
     * @return list of available fields
     */
    @Override
    public ArrayList<Coordinates> getMoves(Field field, Color enemyColor) {
        return new ArrayList<>(simpleMove(field, enemyColor));
    }

    /**
     * Method used to calculate to which fields can a piece move by a simple move.
     *
     * @param field field in which the piece to move is located
     * @param enemyColor color of a player's enemy's base
     * @return list of available fields
     */
    private ArrayList<Coordinates> simpleMove(Field field, Color enemyColor) {
        ArrayList<Coordinates> list = new ArrayList<>();

        for (Field neighbor : field.getNeighbors()) {
            if (neighbor != null && neighbor.getPiece() == null) {
                if (canLeaveBase || !(field.getBase() == enemyColor && neighbor.getBase() != enemyColor)) {
                    list.add(neighbor.getCoordinates());
                }
            }
        }

        return list;
    }
}
