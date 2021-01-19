package tp.checkers.message;

import java.io.Serializable;

/**
 * Class of message sent from the host to server
 * that includes the basic game settings chosen by the host.
 */
public class MessageInit implements Serializable {
    /**
     * Number of players participating in the game.
     */
    private final int playersNumber;

    /**
     * Number of pieces in one side of the base.
     */
    private final int baseSide;

    /**
     * Information whether the player can leave enemy's base.
     */
    private final boolean canLeaveBase;

    /**
     * Information whether the player can jump over other pieces.
     */
    private final boolean canJump;

    /**
     * Default constructor of the class.
     *
     * @param playersNumber number of players participating in the game
     * @param baseSide      base side
     * @param canLeaveBase  information whether the player can leave enemy's base
     * @param canJump       information whether the player can jump over other pieces
     */
    public MessageInit(int playersNumber, int baseSide, boolean canLeaveBase, boolean canJump) {
        this.playersNumber = playersNumber;
        this.baseSide = baseSide;
        this.canLeaveBase = canLeaveBase;
        this.canJump = canJump;
    }

    /**
     * Getter of players' number.
     *
     * @return number of players in game
     */
    public int getPlayersNumber() {
        return playersNumber;
    }

    /**
     * Getter of base side.
     *
     * @return base side.
     */
    public int getBaseSide() {
        return baseSide;
    }

    /**
     * Getter of base side.
     *
     * @return base side.
     */
    public boolean getCanLeaveBase() {
        return canLeaveBase;
    }

    /**
     * Getter of base side.
     *
     * @return base side.
     */
    public boolean getCanJump() {
        return canJump;
    }
}
