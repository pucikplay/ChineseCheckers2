package tp.checkers.message;

import tp.checkers.server.game.Coordinates;

import java.io.Serializable;

/**
 * Class of message passed from clients to server
 * with information about the move or reset of the move.
 */
public class MessageMove implements Serializable {
    /**
     * Information whether the user wants to reset
     * the first chosen field.
     */
    private boolean reset = false;

    /**
     * Fields chosen by the player.
     */
    private Coordinates[] chosenFields;

    /**
     * Constructor of the class used for resetting.
     *
     * @param reset information whether the user wants to reset
     */
    public MessageMove(boolean reset) {
        this.reset = reset;
    }

    /**
     * Constructor of the class used for comitting the move.
     *
     * @param chosenFields fields chosen by the player
     */
    public MessageMove(Coordinates[] chosenFields) {
        this.chosenFields = chosenFields;
    }

    /**
     * Getter of the reset parameter.
     *
     * @return reset
     */
    public boolean isReset() {
        return reset;
    }

    /**
     * Getter of chosen fields array.
     *
     * @return chosen fields
     */
    public Coordinates[] getChosenFields() {
        return chosenFields;
    }
}
