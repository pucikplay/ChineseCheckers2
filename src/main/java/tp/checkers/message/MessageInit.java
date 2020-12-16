package tp.checkers.message;

import java.io.Serializable;

public class MessageInit implements Serializable {
    private final int playersNumber;
    private final int baseSide;

    public MessageInit(int playersNumber, int baseSide) {
        this.playersNumber = playersNumber;
        this.baseSide = baseSide;
    }

    public int getPlayersNumber() {
        return playersNumber;
    }

    public int getBaseSide() {
        return baseSide;
    }
}
