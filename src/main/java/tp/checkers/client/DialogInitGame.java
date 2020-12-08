package tp.checkers.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogInitGame extends JDialog {
    private JComboBox comboBox = null;
    public int playersNumber = 0;

    DialogInitGame(Window window) {
        super(window, "Choose the parameters of the game");

        initDialogBox();
        setLabel();
        setOptions();
        setButton();

        this.setVisible(true);
    }

    private void initDialogBox() {
        this.setSize(400, 250);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setModal(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    private void setLabel() {
        JLabel label = new JLabel("You are the host. Choose the number of players:");
        label.setBounds(70, 20, 300, 50);
        label.setFont(new Font(label.getName(), Font.BOLD, 12));
        this.add(label);
    }

    private void setOptions() {
        String[] playersNumbers = {"2", "3", "4", "6"};

        comboBox = new JComboBox(playersNumbers);
        comboBox.setBounds(150, 100, 100, 40);
        this.add(comboBox);
    }

    private void setButton() {
        JButton button = new JButton("OK");
        button.setBounds(150, 150, 100, 40);
        this.add(button);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int[] playersNumbersInt = {2, 3, 4, 6};
                System.out.println(playersNumbersInt[comboBox.getSelectedIndex()]);
                playersNumber = playersNumbersInt[comboBox.getSelectedIndex()];
                dispose();
            }
        });
    }
}
