package tp.checkers.client.gui.dialog;

import tp.checkers.client.gui.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogFinish extends JDialog {

    public DialogFinish(Window window) {
        super(window, "Choose the parameters of the game");

        initDialogBox();

        this.setVisible(true);
    }

    private void initDialogBox() {
        this.setSize(400, 250);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setModal(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    private void initLabel() {
        JLabel labelEndGame = new JLabel("End of the game!");
        labelEndGame.setBounds(130, 30, 200, 20);
        labelEndGame.setFont(new Font(labelEndGame.getName(), Font.BOLD, 14));
        this.add(labelEndGame);
    }

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
