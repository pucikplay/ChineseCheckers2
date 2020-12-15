package tp.checkers.client.gui.dialog;

import tp.checkers.client.gui.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogInit extends JDialog {
    private JComboBox<Integer> comboBoxPlayers = null;
    private JComboBox<Integer> comboBoxSide = null;
    public int playersNumber;
    public int baseSide = 4;

    public DialogInit(Window window) {
        super(window, "Choose the parameters of the game");

        initDialogBox();
        initLabel();
        initOptions();
        initButton();

        this.setVisible(true);
    }

    private void initDialogBox() {
        this.setSize(400, 350);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setModal(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    private void initLabel() {
        JLabel labelHost = new JLabel("You are the host");
        labelHost.setBounds(130, 30, 200, 20);
        labelHost.setFont(new Font(labelHost.getName(), Font.BOLD, 14));
        this.add(labelHost);

        JLabel labelChoose = new JLabel("Choose the parameters of the game");
        labelChoose.setBounds(65, 50, 300, 20);
        labelChoose.setFont(new Font(labelChoose.getName(), Font.BOLD, 14));
        this.add(labelChoose);

        JLabel labelPlayers = new JLabel("Number of players");
        labelPlayers.setBounds(30, 120, 300, 20);
        labelPlayers.setFont(new Font(labelPlayers.getName(), Font.PLAIN, 14));
        this.add(labelPlayers);

        JLabel labelSide = new JLabel("Side of the base");
        labelSide.setBounds(30, 180, 300, 20);
        labelSide.setFont(new Font(labelSide.getName(), Font.PLAIN, 14));
        this.add(labelSide);

    }

    private void initOptions() {
        Integer[] playersNumbers = {2, 3, 4, 6};
        Integer[] baseSides = {4, 2, 3, 5};

        comboBoxPlayers = new JComboBox<>(playersNumbers);
        comboBoxPlayers.setBounds(190, 110, 150, 40);
        this.add(comboBoxPlayers);

        comboBoxSide = new JComboBox<>(baseSides);
        comboBoxSide.setBounds(190, 170, 150, 40);
        this.add(comboBoxSide);
    }

    private void initButton() {
        JButton button = new JButton("OK");
        button.setBounds(150, 250, 100, 40);
        this.add(button);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playersNumber = (int) comboBoxPlayers.getSelectedItem();
                baseSide = (int) comboBoxSide.getSelectedItem();
                dispose();
            }
        });
    }
}
