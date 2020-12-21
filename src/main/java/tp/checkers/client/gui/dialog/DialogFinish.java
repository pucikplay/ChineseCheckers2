package tp.checkers.client.gui.dialog;

import tp.checkers.client.gui.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class of dialog box displayed after the end of the game.
 */
public class DialogFinish extends JDialog {
    /**
     * Default constructor of the class.
     *
     * @param window reference to the window of the client
     * @param youWon information whether the player won
     */
    public DialogFinish(Window window, boolean youWon) {
        super(window, "Choose the parameters of the game");

        initDialogBox();
        initLabels(youWon);
        initButton();

        this.setVisible(true);
    }

    /**
     * Method responsible for initialisation
     * of the dialog box.
     */
    private void initDialogBox() {
        this.setSize(400, 250);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setModal(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    /**
     * Method responsible for initialisation of the labels
     * displaying the information about the end of the game.
     *
     * @param youWon information whether the player won
     */
    private void initLabels(boolean youWon) {
        JLabel labelEndGame = new JLabel("End of the game!");
        labelEndGame.setBounds(130, 30, 200, 20);
        labelEndGame.setFont(new Font(labelEndGame.getName(), Font.BOLD, 14));
        this.add(labelEndGame);

        JLabel labelYourScore = new JLabel();
        labelYourScore.setBounds(130, 70, 200, 20);
        labelYourScore.setFont(new Font(labelYourScore.getName(), Font.BOLD, 14));
        this.add(labelYourScore);

        if (youWon) {
            labelYourScore.setText("You won.");
        } else {
            labelYourScore.setText("You lost.");
        }
    }

    /**
     * Method responsible for initialisation of the "OK" button
     * that closes the dialog box.
     */
    private void initButton() {
        JButton button = new JButton("OK");
        button.setBounds(150, 150, 100, 40);
        this.add(button);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
