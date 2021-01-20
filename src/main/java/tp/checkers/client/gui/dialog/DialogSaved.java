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

    public DialogSaved(EntityGames[] games) {
        super();
        this.setTitle("Choose the saved game to display");

        initDialogBox(games.length);
        initGameList(games);

        this.setVisible(true);
    }

    /**
     * Method responsible for initialisation of the dialog box.
     */
    private void initDialogBox(int length) {
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setModal(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    private void initGameList(EntityGames[] games) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0,1));
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JButton[] buttons = new JButton[games.length];

        for (int i = 0; i < games.length; i++) {
            EntityGames entGame = games[i];
            String text = "GAME ID:   " + entGame.getGameId() + "    |    "
                    + "DATE OF START:   " + entGame.getStartDate().toString() + "    |    "
                    + "NUMBER OF PLAYERS:   " + entGame.getNumberOfPlayers() + "    |    "
                    + "SIZE OF BASE SIDE:   " + entGame.getSizeOfBase();
            buttons[i] = new JButton(text);
            panel.add(buttons[i]);

            buttons[i].addActionListener(e -> {
                game = entGame.getGameId();
                ready = true;
                dispose();
            });
        }

        this.add(scrollPane);
    }
    public boolean isReady() {
        return ready;
    }

    public int getGame() {
        return game;
    }
}
