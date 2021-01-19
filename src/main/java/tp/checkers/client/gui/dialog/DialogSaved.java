package tp.checkers.client.gui.dialog;

import tp.checkers.client.gui.Window;
import tp.checkers.entities.EntityGames;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogSaved extends JDialog {
    private boolean ready = false;
    private int game = 0;

    public DialogSaved(Window window, EntityGames[] games) {
        super(window, "Choose the saved game to display");

        initDialogBox();
        initButtons();

        this.setVisible(true);
    }

    /**
     * Method responsible for initialisation of the dialog box.
     */
    private void initDialogBox() {
        this.setSize(800, 800);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setModal(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    private void initButtons() {
        JButton button = new JButton("OK");
        button.setBounds(340, 685, 100, 40);
        this.add(button);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ready = true;
                dispose();
            }
        });
    }

    public boolean isReady() {
        return ready;
    }

    public int getGame() {
        return game;
    }
}
