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
     */
    public DialogFinish(Window window) {
        super(window, "Choose the parameters of the game");

        initDialogBox();

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
     */
    private void initLabels() {
        JLabel labelEndGame = new JLabel("End of the game!");
        labelEndGame.setBounds(130, 30, 200, 20);
        labelEndGame.setFont(new Font(labelEndGame.getName(), Font.BOLD, 14));
        this.add(labelEndGame);
    }

    /**
     * Method responsible for initialisation of the "OK" button
     * that closes the dialog box.
     */
    private void initButton() {
        JButton button = new JButton("OK");
        button.setBounds(150, 250, 100, 40);
        this.add(button);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
