package tp.checkers.client.dialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class of dialog box displayed by the host of the game,
 * responsible for setting the game's parameters.
 */
public class DialogInitPlayed extends JDialog {
    /**
     * Combo box with the possible numbers of players.
     */
    private JComboBox<Integer> comboBoxPlayers = null;

    /**
     * Combo box with the possible lengths of base sides.
     */
    private JComboBox<Integer> comboBoxSide = null;

    /**
     * Check box with the information whether the player
     * can leave the opponent's base after entering it.
     */
    private JCheckBox cbLeaveBase = null;

    /**
     * Check box with the information whether the player
     * can jump over other pieces.
     */
    private JCheckBox cbJump = null;

    /**
     * Information to window whether the player has clicked the button.
     */
    private boolean ready = false;

    /**
     * Number of players that is chosen by the host.
     */
    private int playersNumber;

    /**
     * Number of pieces in one side of the base.
     */
    private int baseSide = 4;

    /**
     * Information whether the player can leave enemy's base.
     */
    private boolean canLeaveBase = false;

    /**
     * Information whether the player can jump over other pieces.
     */
    private boolean canJump = true;

    /**
     * Default constructor of the class.
     */
    public DialogInitPlayed() {
        super();
        this.setTitle("Choose the parameters of the new game");

        initDialogBox();
        initLabels();
        initCheckBoxes();
        initOptions();
        initButton();

        this.setVisible(true);
    }

    /**
     * Method responsible for initialisation of the dialog box.
     */
    private void initDialogBox() {
        this.setSize(400, 430);
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
     * Method responsible for initialisation of check boxes.
     */
    private void initCheckBoxes() {
        cbLeaveBase = new JCheckBox("Player can leave the opponent's base");
        cbLeaveBase.setBounds(30, 240, 300, 20);
        cbLeaveBase.setFont(new Font(cbLeaveBase.getName(), Font.PLAIN, 14));
        this.add(cbLeaveBase);

        cbJump = new JCheckBox("Player can move by jumping over one piece");
        cbJump.setBounds(30, 280, 300, 20);
        cbJump.setFont(new Font(cbJump.getName(), Font.PLAIN, 14));
        cbJump.setSelected(true);
        this.add(cbJump);
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
     * and setting the chooseable parameters as the ones
     * chosen by the user.
     */
    private void initButton() {
        JButton button = new JButton("OK");
        button.setBounds(150, 330, 100, 40);
        this.add(button);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playersNumber = (int) comboBoxPlayers.getSelectedItem();
                baseSide = (int) comboBoxSide.getSelectedItem();
                canLeaveBase = cbLeaveBase.isSelected();
                canJump = cbJump.isSelected();
                ready = true;
                dispose();
            }
        });
    }

    /**
     * Getter of ready boolean.
     *
     * @return information whether the player has clicked the button
     */
    public boolean isReady() {
        return ready;
    }

    /**
     * Getter of base side.
     *
     * @return base side
     */
    public int getBaseSide() {
        return baseSide;
    }

    /**
     * Getter of players number.
     *
     * @return number of players
     */
    public int getPlayersNumber() {
        return playersNumber;
    }

    /**
     * Getter of information whether jumping is allowed.
     *
     * @return data about jumping
     */
    public boolean getCanJump() {
        return canJump;
    }

    /**
     * Getter of information whether leaving of enemy's base is allowed.
     *
     * @return data about leaving enemy's base
     */
    public boolean getCanLeaveBase() {
        return canLeaveBase;
    }
}
