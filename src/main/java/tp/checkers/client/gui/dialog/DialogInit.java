package tp.checkers.client.gui.dialog;

import tp.checkers.client.gui.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class of dialog box displayed by the host of the game,
 * responsible for setting the game's parameters.
 */
public class DialogInit extends JDialog {
    /**
     * Combo box with the possible numbers of players.
     */
    private JComboBox<Integer> comboBoxPlayers = null;

    /**
     * Combo box with the possible lengths of base sides.
     */
    private JComboBox<Integer> comboBoxSide = null;

    /**
     * Number of players that is chosen by the host.
     */
    public int playersNumber;

    /**
     * Number of pieces in one side of the base.
     */
    public int baseSide = 4;

    /**
     * Default constructor of the class.
     *
     * @param window reference to the client's window
     */
    public DialogInit(Window window) {
        super(window, "Choose the parameters of the game");

        initDialogBox();
        initLabels();
        initOptions();
        initButton();

        this.setVisible(true);
    }

    /**
     * Method responsible for initialisation of the dialog box.
     */
    private void initDialogBox() {
        this.setSize(400, 350);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setModal(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    /**
     * Method responsible for initialisation of the labels
     * showing the needed information about things to choose.
     */
    private void initLabels() {
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

    /**
     * Method responsible for initialisation of combo boxes.
     */
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

    /**
     * Method responsible for initialisation of the "OK" button
     * and setting the players number and base side as the ones
     * chosen by the user.
     */
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
