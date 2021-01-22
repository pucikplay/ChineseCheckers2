package tp.checkers.client.dialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogGameMode extends JDialog {
    private boolean ready = false;
    private boolean play = false;

    public DialogGameMode() {
        super();
        setTitle("Choose the mode of the game");

        initDialogBox();
        initLabel();
        initButtons();

        this.setVisible(true);
    }

    /**
     * Method responsible for initialisation of the dialog box.
     */
    private void initDialogBox() {
        this.setSize(300, 320);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setModal(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    private void initLabel() {
        JLabel label = new JLabel("Choose the game mode:");
        label.setBounds(70, 30, 150, 40);
        this.add(label);
    }

    private void initButtons() {
        JButton buttonPlay = new JButton("Play a new game");
        buttonPlay.setBounds(40, 100, 200, 40);
        this.add(buttonPlay);

        JButton buttonDisplay = new JButton("Display a saved game");
        buttonDisplay.setBounds(40, 180, 200, 40);
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
