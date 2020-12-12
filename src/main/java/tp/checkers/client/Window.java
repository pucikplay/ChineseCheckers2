package tp.checkers.client;

import tp.checkers.client.button.ButtonCommit;
import tp.checkers.client.button.ButtonReset;
import tp.checkers.message.MessageInit;
import tp.checkers.server.game.Field;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private final int width = 1000;
    private final int height = 1000;
    private final Client client;
    private Panel panel;
    private Color color;
    private JLabel labelMove;
    private JLabel labelColor;

    public Window(Client client) {
        this.client = client;

        initUI();
    }

    private void initUI() {
        setTitle("Chinese checkers client");
        setSize(new Dimension(width, height));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initButtons() {
        ButtonCommit buttonCommit = new ButtonCommit(panel);
        panel.add(buttonCommit);

        ButtonReset buttonReset = new ButtonReset(panel);
        panel.add(buttonReset);
    }

    private void initLabels() {
        labelColor = new JLabel("This is your color.");
        labelColor.setBounds(70, 20, 300, 40);
        labelColor.setFont(new Font(labelColor.getName(), Font.BOLD, 22));
        labelColor.setForeground(color.darker());


        labelMove = new JLabel("Wait for your move.");
        labelMove.setBounds(70, 70, 300, 40);
        labelMove.setFont(new Font(labelColor.getName(), Font.BOLD, 22));

    }

    public MessageInit initGameData() {
        DialogInitGame dialog = new DialogInitGame(this);
        while (dialog.playersNumber == 0);
        return new MessageInit(dialog.playersNumber);
    }

    public void initBoard(Field[][] fields, Color color) {
        this.color = color;
        initLabels();
        this.panel = new Panel(client, width, height, fields, color, labelMove);
        panel.add(labelColor);
        panel.add(labelMove);
        this.add(panel);

        initButtons();

        this.setVisible(true);
    }
}
