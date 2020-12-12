package tp.checkers.client.gui;

import tp.checkers.client.Client;
import tp.checkers.client.GameService;
import tp.checkers.message.MessageUpdate;

import javax.swing.*;
import java.awt.*;

public class BoardUpdater extends SwingWorker<Void, Void>  {
    private final Client client;
    private final GameService gameService;
    private final Window window;
    private final Panel panel;

    public BoardUpdater(Client client, GameService gameService, Window window, Panel panel) {
        this.client = client;
        this.gameService = gameService;
        this.window = window;
        this.panel = panel;
    }

    @Override
    protected Void doInBackground() {
        System.out.println("SwingWorker has started working.");
        receiveUpdates();
        return null;
    }

    private void receiveUpdates() {
        client.receiveUpdates(this);
    }

    public void updateFields(MessageUpdate msg) {
        System.out.println("Updating board.");

        if (msg.origin.i != msg.destination.i || msg.origin.j != msg.destination.j) {
            Color colorBefore = gameService.getPieceColor(msg.origin.i, msg.origin.j);
            System.out.println("Color of origin piece before the update:" + colorBefore);

            gameService.setPieceColor(msg.destination.i, msg.destination.j, colorBefore);
            gameService.setPieceColor(msg.origin.i, msg.origin.j, null);

            Color colorAfter = gameService.getPieceColor(msg.destination.i, msg.destination.j);
            System.out.println("Color of destination piece after the update:" + colorAfter);

            panel.repaint();
            System.out.println("Board repainted.");
        }

        checkIfMyTurn(msg.currPlayer);
    }

    private void checkIfMyTurn(boolean currPlayer) {
        gameService.setIsMyTurn(currPlayer);
        System.out.println();
        System.out.println("My turn now: " + gameService.getIsMyTurn());

        if (! gameService.getIsMyTurn()) {
            window.setLabelMoveText("Not your move");
            receiveUpdates();
        } else {
            window.setLabelMoveText("Your move.");
        }
    }
}
