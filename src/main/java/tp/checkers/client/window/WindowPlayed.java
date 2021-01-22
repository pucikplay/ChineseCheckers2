package tp.checkers.client.window;

import tp.checkers.client.Panel;
import tp.checkers.client.button.ButtonCommit;
import tp.checkers.client.button.ButtonReset;
import tp.checkers.client.gameservice.GameService;
import tp.checkers.client.gameservice.GameServicePlayed;

import javax.swing.*;
import java.awt.*;

/**
 * Class of the client's window.
 */
public class WindowPlayed extends Window {
    /**
     * Reference to client's game service.
     */
    private GameService gameService;

    /**
     * Label showing if it's the player's turn.
     */
    private JLabel labelTurn;

    /**
     * Default constructor.
     */
    public WindowPlayed() {
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
        this.color = color;
        this.panel = new Panel(gameService, windowSide, arraySide, color);

        initLabels();
        initButtons();

        this.add(panel);
        this.setVisible(true);

        gameService.startGame(panel);
    }

    /**
     * Method responsible for initialisation of the Commit and Reset buttons.
     */
    @Override
    protected void initButtons() {
        ButtonCommit buttonCommit = new ButtonCommit((GameServicePlayed) gameService);
        panel.add(buttonCommit);

        ButtonReset buttonReset = new ButtonReset((GameServicePlayed) gameService);
        panel.add(buttonReset);
    }

    /**
     * Method responsible for initialisation of the Color and Turn labels.
     */
    @Override
    protected void initLabels() {
        JLabel labelColor = new JLabel("This is your color.");
        labelColor.setBounds(70, 20, 300, 40);
        labelColor.setFont(new Font(labelColor.getName(), Font.BOLD, 22));
        labelColor.setForeground(color.darker());

        labelTurn = new JLabel("Wait for your turn.");
        labelTurn.setBounds(70, 70, 300, 40);
        labelTurn.setFont(new Font(labelTurn.getName(), Font.BOLD, 22));

        panel.add(labelColor);
        panel.add(labelTurn);
    }

    /**
     * Setter for Turn label's text.
     *
     * @param text text to be set
     */
    public void setLabelTurnText(String text) {
        labelTurn.setText(text);
    }
}
