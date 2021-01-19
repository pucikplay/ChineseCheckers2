package tp.checkers.server.game.possibilities;

import tp.checkers.Coordinates;
import tp.checkers.Field;

import java.awt.*;
import java.util.ArrayList;

/**
 * Concrete decorator class consisting of methods used to calculate
 * the fields to which a player can move by jumping over another pieces.
 */
public class PossibilitiesJump extends PossibilitiesDecorator {
    /**
     * Default constructor of the class.
     *
     * @param wrappee      the decorated object of Possibilities
     * @param canLeaveBase setting whether the piece can leave the enemy's base after entering it
     */
    public PossibilitiesJump(Possibilities wrappee, boolean canLeaveBase) {
        super(wrappee, canLeaveBase);
    }

    /**
     * Method used to calculate to which fields ca a piece move by jumping over other pieces.
     *
     * @param field field in which the piece to move is located
     * @param checked list of fields that were already checked; avoids recursive loops
     * @param enemyColor color of a player's enemy's base
     * @return list of available fields
     */
    protected ArrayList<Coordinates> addMoves(Field field, ArrayList<Coordinates> checked, Color enemyColor) {
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
                    if (canLeaveBase || !(field.getBase() == enemyColor && field.getSecondNeighborsBase(a) != enemyColor)){
                        list.addAll(addMoves(field.getSecondNeighbor(a), checked, enemyColor));
                    }
                }
            }
        }
        return list;
    }
}
