package tp.checkers.client.window;

import tp.checkers.client.Panel;
import tp.checkers.client.button.ButtonNext;
import tp.checkers.client.gameservice.GameService;
import tp.checkers.client.gameservice.GameServiceSaved;

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
        ButtonNext buttonNext = new ButtonNext((GameServiceSaved) gameService);
        panel.add(buttonNext);
    }

    /**
     * Method responsible for initialisation of the Color and Turn labels.
     */
    @Override
    protected void initLabels() {
        JLabel label = new JLabel("Displaying a saved game");
        label.setBounds(70, 70, 300, 40);
        label.setFont(new Font(label.getName(), Font.BOLD, 22));
    }
}
