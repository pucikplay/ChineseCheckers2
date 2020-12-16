package tp.checkers.client.gui;

import tp.checkers.client.ClientConnector;
import tp.checkers.client.GameService;
import tp.checkers.message.MessageUpdate;

import javax.swing.*;
import java.awt.*;

public class BoardUpdater extends SwingWorker<Void, Void>  {
    private final ClientConnector client;
    private final GameService gameService;
    private final Window window;
    private final Panel panel;

    public BoardUpdater(ClientConnector client, GameService gameService, Window window, Panel panel) {
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

        if (msg.getOrigin().i != msg.getDestination().i || msg.getOrigin().j != msg.getDestination().j) {
            Color color = gameService.getPieceColor(msg.getOrigin().i, msg.getOrigin().j);

            gameService.setPieceColor(msg.getDestination().i, msg.getDestination().j, color);
            gameService.setPieceColor(msg.getOrigin().i, msg.getOrigin().j, null);

            panel.repaint();
            System.out.println("Board repainted.");
        }

        checkIfMyTurn(msg.isCurrPlayer());
    }

    private void checkIfMyTurn(boolean currPlayer) {
        gameService.setIsMyTurn(currPlayer);
        System.out.println();
        System.out.println("My turn now: " + gameService.getIsMyTurn());

        if (! gameService.getIsMyTurn()) {
            window.setLabelMoveText("Wait for your turn.");
            receiveUpdates();
        } else {
            window.setLabelMoveText("Your turn!");
        }
    }
}
