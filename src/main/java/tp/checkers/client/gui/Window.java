package tp.checkers.client.gui;

import tp.checkers.client.GameService;
import tp.checkers.client.gui.button.ButtonCommit;
import tp.checkers.client.gui.button.ButtonReset;
import tp.checkers.client.gui.dialog.DialogFinish;
import tp.checkers.client.gui.dialog.DialogGameMode;
import tp.checkers.client.gui.dialog.DialogInit;
import tp.checkers.client.gui.dialog.DialogSaved;
import tp.checkers.entities.EntityGames;
import tp.checkers.message.MessageInit;

import javax.swing.*;
import java.awt.*;

/**
 * Class of the client's window.
 */
public class Window extends JFrame {
    /**
     * Reference to client's game service.
     */
    private GameService gameService;

    /**
     * Reference to panel.
     */
    private Panel panel;

    /**
     * Size of the window's side.
     */
    private final int windowSide = 1000;

    /**
     * Color of the player
     */
    private Color color;

    /**
     * Label showing the player's color.
     */
    private JLabel labelColor;

    /**
     * Label showing if it's the player's turn.
     */
    private JLabel labelTurn;

    /**
     * Default constructor.
     */
    public Window() {
        initUI();
    }

    /**
     * Method responsible for initialisation of the window.
     */
    private void initUI() {
        setTitle("Chinese checkers client");
        setSize(new Dimension(windowSide, windowSide));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    /**
     * Method responsible for receiving the data from client connector,
     * creating a panel and passing the data to game service.
     *
     * @param gameService reference to client's game service
     * @param color       color of the player
     * @param arraySide   length of one side of Fields array
     */
    public void initBoard(GameService gameService, Color color, int arraySide) {
        System.out.println("Initialisation of the board and panel");

        this.gameService = gameService;
        this.color = color;

        initLabels();

        this.panel = new Panel(gameService, windowSide, arraySide, color);
        panel.add(labelColor);
        panel.add(labelTurn);
        this.add(panel);

        initButtons();

        this.setVisible(true);

        gameService.startGame(panel);
    }

    /**
     * Method responsible for initialisation of the Commit and Reset buttons.
     */
    private void initButtons() {
        ButtonCommit buttonCommit = new ButtonCommit(gameService);
        panel.add(buttonCommit);

        ButtonReset buttonReset = new ButtonReset(gameService);
        panel.add(buttonReset);
    }

    /**
     * Method responsible for initialisation of the Color and Turn labels.
     */
    private void initLabels() {
        labelColor = new JLabel("This is your color.");
        labelColor.setBounds(70, 20, 300, 40);
        labelColor.setFont(new Font(labelColor.getName(), Font.BOLD, 22));
        labelColor.setForeground(color.darker());

        labelTurn = new JLabel("Wait for your turn.");
        labelTurn.setBounds(70, 70, 300, 40);
        labelTurn.setFont(new Font(labelColor.getName(), Font.BOLD, 22));
    }

    public boolean runDialogGameMode() {
        DialogGameMode dialog = new DialogGameMode(this);
        while (!dialog.isReady());
        return dialog.isPlay();
    }

    /**
     * Method responsible for calling the Init dialog box by the host
     * and getting the data of the game from them.
     *
     * @return message with init data that will be sent to the server
     */
    public MessageInit runDialogInit() {
        DialogInit dialog = new DialogInit(this);
        while (!dialog.isReady());
        return new MessageInit(dialog.getPlayersNumber(), dialog.getBaseSide(), dialog.getCanLeaveBase(), dialog.getCanJump());
    }

    public int runDialogSaved(EntityGames[] games) {
        DialogSaved dialog = new DialogSaved(this, games);
        while (!dialog.isReady());
        return dialog.getGame();
    }

    /**
     * Method responsible for calling the Finish dialog box.
     *
     * @param youWon the you won
     */
    public void runDialogFinish(boolean youWon) {
        DialogFinish dialogFinish = new DialogFinish(this, youWon, color);
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
