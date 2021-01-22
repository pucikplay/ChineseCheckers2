package tp.checkers.client.gameservice;

import tp.checkers.Coordinates;
import tp.checkers.Field;
import tp.checkers.client.Client;
import tp.checkers.client.Panel;
import tp.checkers.client.window.Window;
import tp.checkers.message.MessageUpdate;

import java.awt.*;

public class GameServiceSaved extends GameService {
    public GameServiceSaved(Client client, Window window, Field[][] fields, int baseSide) {
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

    public void nextSavedMove() {
        MessageUpdate msg = client.nextSavedMove();

        if (msg.getOrigin() == null) {
            return;
        }

        if (msg.getOrigin().i != msg.getDestination().i || msg.getOrigin().j != msg.getDestination().j) {
            Color color = getPieceColor(msg.getOrigin().i, msg.getOrigin().j);

            setPieceColor(msg.getDestination().i, msg.getDestination().j, color);
            setPieceColor(msg.getOrigin().i, msg.getOrigin().j, null);

            repaintPanel();
            System.out.println("Board repainted.");
        }
    }
}
