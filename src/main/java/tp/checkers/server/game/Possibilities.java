package tp.checkers.server.game;

import java.awt.*;
import java.util.ArrayList;

/**
 * Class consisting of methods used to calculate the fields to which a player can move
 */
public class Possibilities {

    /**
     * Method responsible for providing a list of fields available to move to
     *
     * @param field field in which the piece to move is located
     * @param enemyColor color of a player's enemy's base
     * @return list of available fields
     */
    public static Coordinates[] getMoves(Field field, Color enemyColor) {
        ArrayList<Coordinates> possibilitiesList = new ArrayList<>();

        possibilitiesList.addAll(simpleMove(field, enemyColor));
        possibilitiesList.addAll(jumpMove(field, new ArrayList<Coordinates>(), enemyColor));

        return possibilitiesList.toArray(new Coordinates[0]);
    }

    /**
     * Method used to calculate to which fields can a piece move by a simple move
     * (not jumping)
     *
     * @param field field in which the piece to move is located
     * @param enemyColor color of a player's enemy's base
     * @return list of available fields
     */
    private static ArrayList<Coordinates> simpleMove(Field field, Color enemyColor) {
        ArrayList<Coordinates> list = new ArrayList<>();

        for (Field neighbor : field.getNeighbors()) {
            if (neighbor != null && neighbor.getPiece() == null) {
                if (!(field.getBase() == enemyColor && neighbor.getBase() != enemyColor)){
                    list.add(neighbor.getCoordinates());
                }
            }
        }

        return list;
    }

    /**
     * Method used to calculate to which fields ca a piece move by jumping over others
     *
     * @param field field in which the piece to move is located
     * @param checked list of fields that were already checked; avoids recursive loops
     * @param enemyColor color of a player's enemy's base
     * @return list of available fields
     */
    private static ArrayList<Coordinates> jumpMove(Field field, ArrayList<Coordinates> checked, Color enemyColor) {
        ArrayList<Coordinates> list = new ArrayList<>();
        boolean beenThere = false;
        for (Coordinates coordinates : checked) {
            if (coordinates.compare(field.getCoordinates())) {
                beenThere = true;
                break;
            }
        }
        if (!beenThere) {
            list.add(field.getCoordinates());
            checked.add(field.getCoordinates());

            for (int a = 0; a < 6; a++) {
                if (field.getNeighbor(a) != null
                        && field.getNeighborsPiece(a) != null
                        && field.getSecondNeighbor(a) != null
                        && field.getSecondNeighborsPiece(a) == null) {
                    if (!(field.getBase() == enemyColor && field.getSecondNeighborsBase(a) != enemyColor)){
                        list.addAll(jumpMove(field.getSecondNeighbor(a), checked, enemyColor));
                    }
                }
            }
        }
        return list;
    }

}
