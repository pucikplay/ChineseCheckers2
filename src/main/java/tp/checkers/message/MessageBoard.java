package tp.checkers.message;

import tp.checkers.Field;

import java.awt.*;
import java.io.Serializable;

/**
 * Class of the message sent from server to all clients
 * that initialises the board and game settings.
 */
public class MessageBoard implements Serializable {
    /**
     * Number of pieces in one side of the base.
     */
    private final int baseSide;

    /**
     * Array of fields of the game.
     */
    private final Field[][] fields;

    /**
     * Color of the player.
     */
    private final Color color;

    /**
     * Default constructor of the class.
     *
     * @param baseSide number of pieces in one side of the base
     * @param fields   array of fields of the game
     * @param color    color of the player
     */
    public MessageBoard(int baseSide, Field[][] fields, Color color) {
        this.baseSide = baseSide;
        this.fields = fields;
        this.color = color;
    }

    /**
     * Getter of base side.
     *
     * @return base side
     */
    public int getBaseSide() {
        return baseSide;
    }

    /**
     * Getter of Fields array.
     *
     * @return Fields array
     */
    public Field[][] getFields() {
        return fields;
    }

    /**
     * Getter of player's color.
     *
     * @return player 's color
     */
    public Color getColor() {
        return color;
    }
}
