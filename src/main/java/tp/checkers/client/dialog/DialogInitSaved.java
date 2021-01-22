package tp.checkers.client.dialog;

import tp.checkers.entities.EntityGames;

import javax.swing.*;
import java.awt.*;

public class DialogInitSaved extends JDialog {
    private int game = 0;

    public DialogInitSaved(EntityGames[] games) {
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

        for (EntityGames entity : games) {
            String text = "GAME ID:   " + entity.getGameId() + "    |    "
                    + "DATE OF START:   " + entity.getStartDate().toString() + "    |    "
                    + "NUMBER OF PLAYERS:   " + entity.getNumberOfPlayers() + "    |    "
                    + "SIZE OF BASE SIDE:   " + entity.getSizeOfBase();
            JButton button = new JButton(text);
            panel.add(button);

            button.addActionListener(e -> {
                game = entity.getGameId();
                dispose();
            });
        }

        this.add(scrollPane);
    }

    public int getGame() {
        return game;
    }
}
