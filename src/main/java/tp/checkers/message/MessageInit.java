package tp.checkers.message;

import java.io.Serializable;

public class MessageInit implements Serializable {
    private int playersNumber;
    private int baseSide;

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
