package tp.checkers.client.gui.dialog;

import tp.checkers.client.gui.Window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogGameMode extends JDialog {
    private boolean ready = false;
    private boolean play = false;

    public DialogGameMode(Window window) {
        super(window, "Choose the mode of the game");

        initDialogBox();
        initButtons();

        this.setVisible(true);
    }

    /**
     * Method responsible for initialisation of the dialog box.
     */
    private void initDialogBox() {
        this.setSize(300, 270);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setModal(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    private void initButtons() {
        JButton buttonPlay = new JButton("Play a new game");
        buttonPlay.setBounds(40, 50, 200, 40);
        this.add(buttonPlay);

        JButton buttonDisplay = new JButton("Display a saved game");
        buttonDisplay.setBounds(40, 130, 200, 40);
        this.add(buttonDisplay);

        buttonPlay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                play = true;
                ready = true;
                dispose();
            }
        });

        buttonDisplay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ready = true;
                dispose();
            }
        });
    }

    public boolean isReady() {
        return ready;
    }

    public boolean isPlay() {
        return play;
    }
}
