package tp.checkers.client.gui;

import tp.checkers.client.ClientConnector;
import tp.checkers.client.GameService;
import tp.checkers.message.MessageUpdate;

import javax.swing.*;
import java.awt.*;

/**
 * Class responsible for working in the background of the client
 * and updating the board when the data changes.
 */
public class BoardUpdater extends SwingWorker<Void, Void> {
    /**
     * The reference to the client connector.
     */
    private final ClientConnector client;

    /**
     * The reference to the game service of the client.
     */
    private final GameService gameService;

    /**
     * The reference to the client's window.
     */
    private final Window window;

    /**
     * The reference to the client's panel.
     */
    private final Panel panel;

    /**
     * Default constructor of the class.
     *
     * @param client reference to the client connector
     * @param gameService reference to the game service of the client
     * @param window reference to the client's window
     * @param panel reference to the client's panel
     */
    public BoardUpdater(ClientConnector client, GameService gameService, Window window, Panel panel) {
        this.client = client;
        this.gameService = gameService;
        this.window = window;
        this.panel = panel;
    }

    /**
     * Method responsible for initialisation of background work.
     *
     * @return null
     */
    @Override
    protected Void doInBackground() {
        System.out.println("SwingWorker has started working.");
        receiveUpdates();
        return null;
    }

    /**
     * Method responsible for receiving the updates from the client.
     */
    private void receiveUpdates() {
        client.receiveUpdates(this);
    }

    /**
     * Method responsible for updating the changed fields in the board
     * by swapping the origin and destination's fields' piece color.
     *
     * @param msg message update from the client
     */
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

    /**
     * Method responsible for checking if it's the player's turn now
     * and changing the label that tells about it.
     *
     * @param currPlayer information whether it's a turn of the player
     */
    private void checkIfMyTurn(boolean currPlayer) {
        gameService.setMyTurn(currPlayer);
        System.out.println();
        System.out.println("My turn now: " + gameService.isMyTurn());

        if (! gameService.isMyTurn()) {
            window.setLabelTurnText("Wait for your turn.");
            receiveUpdates();
        } else {
            window.setLabelTurnText("Your turn!");
        }
    }
}
