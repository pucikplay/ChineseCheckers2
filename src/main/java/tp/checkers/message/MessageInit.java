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
     * Default constructor of the class.
     *
     * @param playersNumber number of players participating in the game
     * @param baseSide
     */
    public MessageInit(int playersNumber, int baseSide) {
        this.playersNumber = playersNumber;
        this.baseSide = baseSide;
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
}
