package tp.checkers.client.gui.dialog;

import tp.checkers.client.gui.Window;
import tp.checkers.entities.EntityGames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogSaved extends JDialog {
    private boolean ready = false;
    private int game = 0;

    public DialogSaved(Window window, EntityGames[] games) {
        super(window, "Choose the saved game to display");

        initDialogBox(games.length);
        initGameList(games);

        this.setVisible(true);
    }

    /**
     * Method responsible for initialisation of the dialog box.
     */
    private void initDialogBox(int length) {
        this.setSize(800, 800);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(length, 1));
        this.setModal(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    private void initGameList(EntityGames[] games) {
        JButton[] buttons = new JButton[games.length];

        for (int i = 0; i < games.length; i++) {
            EntityGames entGame = games[i];
            String text = entGame.getGameId() + "    date of start:" + entGame.getStartDate().toString()
                    + "    number of players: " + entGame.getNumberOfPlayers() + "      size of base side: "
                    + entGame.getSizeOfBase();
            buttons[i] = new JButton(text);
            this.add(buttons[i]);

            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    game = entGame.getGameId();
                    ready = true;
                    dispose();
                }
            });
        }
    }
    public boolean isReady() {
        return ready;
    }

    public int getGame() {
        return game;
    }
}
