package tp.checkers.client;

import tp.checkers.Coordinates;
import tp.checkers.Field;
import tp.checkers.client.gui.Panel;
import tp.checkers.client.gui.Window;
import tp.checkers.client.gui.WindowPlayed;
import tp.checkers.client.gui.WindowSaved;

public class GameServiceSaved extends GameService {
    public GameServiceSaved(ClientConnector client, Window window, Field[][] fields, int baseSide) {
        super(client, window, fields, baseSide);

        countBoard();
        window.initBoard(this, null, arraySide);
    }

    @Override
    public void startGame(Panel panel) {
        this.panel = panel;
    }

    @Override
    public Coordinates getChosenField(int i) {
        return new Coordinates(0, 0);
    }

    @Override
    public Coordinates[] getPossibilities() {
        return null;
    }
}
