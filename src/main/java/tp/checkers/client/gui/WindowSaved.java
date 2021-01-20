package tp.checkers.client.gui;

import tp.checkers.client.GameService;
import tp.checkers.client.GameServicePlayed;
import tp.checkers.client.GameServiceSaved;
import tp.checkers.client.gui.button.ButtonCommit;
import tp.checkers.client.gui.button.ButtonNext;
import tp.checkers.client.gui.button.ButtonReset;

import javax.swing.*;
import java.awt.*;

/**
 * Class of the client's window.
 */
public class WindowSaved extends Window {
    /**
     * Reference to client's game service.
     */
    private GameService gameService;

    /**
     * Default constructor.
     */
    public WindowSaved() {
        initUI();
    }

    /**
     * Method responsible for receiving the data from client connector,
     * creating a panel and passing the data to game service.
     *
     * @param gameService reference to client's game service
     * @param color       color of the player
     * @param arraySide   length of one side of Fields array
     */
    @Override
    public void initBoard(GameService gameService, Color color, int arraySide) {
        System.out.println("Initialisation of the board and panel");

        this.gameService = gameService;
        this.panel = new Panel(gameService, windowSide, arraySide, color);
        this.add(panel);
        this.setVisible(true);

        initButtons();

        gameService.startGame(panel);
    }

    /**
     * Method responsible for initialisation of the Commit and Reset buttons.
     */
    @Override
    protected void initButtons() {
        ButtonNext buttonNext = new ButtonNext(gameService);
        panel.add(buttonNext);
    }

    /**
     * Method responsible for initialisation of the Color and Turn labels.
     */
    @Override
    protected void initLabels() {

    }
}
