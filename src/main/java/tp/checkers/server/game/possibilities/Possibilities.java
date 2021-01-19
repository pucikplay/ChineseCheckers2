package tp.checkers.server.game.possibilities;

import tp.checkers.Coordinates;
import tp.checkers.Field;

import java.awt.*;
import java.util.ArrayList;

/**
 * The interface Possibilities.
 */
public interface Possibilities {
    /**
     * Method responsible for providing a list of fields available to move to.
     *
     * @param field      field in which the piece to move is located
     * @param enemyColor color of a player's enemy's base
     * @return list of available fields
     */
    ArrayList<Coordinates> getMoves(Field field, Color enemyColor);
}
