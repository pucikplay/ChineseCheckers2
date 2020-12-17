package tp.checkers.server.game.possibilities;

import tp.checkers.server.game.Coordinates;
import tp.checkers.server.game.Field;

import java.awt.*;
import java.util.ArrayList;

/**
 * Abstract decorator class consisting of methods used to calculate
 * the additive fields to which a player can move.
 */
public abstract class PossibilitiesDecorator implements Possibilities {
    /**
     * The decorated object of Possibilities.
     */
    protected final Possibilities wrappee;

    /**
     * Setting whether the piece can leave the enemy's base after entering it.
     */
    protected final boolean canLeaveBase;

    /**
     * Default constructor of the class.
     *
     * @param wrappee the decorated object of Possibilities
     * @param canLeaveBase setting whether the piece can leave the enemy's base after entering it
     */
    public PossibilitiesDecorator(Possibilities wrappee, boolean canLeaveBase) {
        this.wrappee = wrappee;
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
        ArrayList<Coordinates> possibilitiesList = wrappee.getMoves(field, enemyColor);

        possibilitiesList.addAll(addMoves(field, new ArrayList<Coordinates>(), enemyColor));
        return possibilitiesList;
    }

    /**
     * Method used to calculate to which fields can a piece move by a given move.
     *
     * @param field field in which the piece to move is located
     * @param checked list of fields that were already checked; avoids recursive loops
     * @param enemyColor color of a player's enemy's base
     * @return list of available fields by a given move
     */
    protected ArrayList<Coordinates> addMoves(Field field, ArrayList<Coordinates> checked, Color enemyColor) {
        return null;
    }
}
