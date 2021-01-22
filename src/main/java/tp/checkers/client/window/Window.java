package tp.checkers.client.window;

import tp.checkers.client.dialog.DialogGameMode;
import tp.checkers.client.dialog.DialogInitPlayed;
import tp.checkers.client.dialog.DialogInitSaved;
import tp.checkers.client.Panel;
import tp.checkers.client.gameservice.GameService;
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
        DialogInitPlayed dialog = new DialogInitPlayed();
        while (!dialog.isReady());
        return new MessageInit(dialog.getPlayersNumber(), dialog.getBaseSide(), dialog.getCanLeaveBase(), dialog.getCanJump());
    }

    public static int runDialogSaved(EntityGames[] games) {
        DialogInitSaved dialog = new DialogInitSaved(games);
        while (dialog.getGame() == 0);
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
}
