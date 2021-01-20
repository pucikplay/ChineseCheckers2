package tp.checkers.client.gui;

import tp.checkers.client.GameService;
import tp.checkers.client.gui.dialog.DialogFinish;
import tp.checkers.client.gui.dialog.DialogGameMode;
import tp.checkers.client.gui.dialog.DialogInit;
import tp.checkers.client.gui.dialog.DialogSaved;
import tp.checkers.entities.EntityGames;
import tp.checkers.message.MessageInit;

import javax.swing.*;
import java.awt.*;

public abstract class Window extends JFrame {
    /**
     * Size of the window's side.
     */
    protected final int windowSide = 1000;
    /**
     * Reference to panel.
     */
    protected Panel panel;

    /**
     * Color of the player
     */
    protected Color color;

    public static boolean runDialogGameMode() {
        DialogGameMode dialog = new DialogGameMode();
        while (!dialog.isReady());
        return dialog.isPlay();
    }

    /**
     * Method responsible for calling the Init dialog box by the host
     * and getting the data of the game from them.
     *
     * @return message with init data that will be sent to the server
     */
    public static MessageInit runDialogInit() {
        DialogInit dialog = new DialogInit();
        while (!dialog.isReady());
        return new MessageInit(dialog.getPlayersNumber(), dialog.getBaseSide(), dialog.getCanLeaveBase(), dialog.getCanJump());
    }

    public static int runDialogSaved(EntityGames[] games) {
        DialogSaved dialog = new DialogSaved(games);
        while (!dialog.isReady());
        return dialog.getGame();
    }

    /**
     * Method responsible for initialisation of the window.
     */
    protected void initUI() {
        setTitle("Chinese checkers client");
        setSize(new Dimension(windowSide, windowSide));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public abstract void initBoard(GameService gameService, Color color, int arraySide);

    protected abstract void initButtons();

    protected abstract void initLabels();

    /**
     * Method responsible for calling the Finish dialog box.
     *
     * @param youWon the you won
     */
    public void runDialogFinish(boolean youWon) {
        DialogFinish dialogFinish = new DialogFinish(this, youWon, color);
    }
}
