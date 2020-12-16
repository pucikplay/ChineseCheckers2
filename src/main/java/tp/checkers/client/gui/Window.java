package tp.checkers.client.gui;

import tp.checkers.client.ClientConnector;
import tp.checkers.client.GameService;
import tp.checkers.client.gui.button.ButtonCommit;
import tp.checkers.client.gui.button.ButtonReset;
import tp.checkers.client.gui.dialog.DialogInit;
import tp.checkers.message.MessageInit;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private final ClientConnector client;
    private GameService gameService;
    private Panel panel;
    private final int windowSide = 1000;
    private Color color;
    private JLabel labelMove;
    private JLabel labelColor;

    public Window(ClientConnector client) {
        this.client = client;

        initUI();
    }

    private void initUI() {
        setTitle("Chinese checkers client");
        setSize(new Dimension(windowSide, windowSide));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void initBoard(GameService gameService, Color color, int arraySide) {
        this.gameService = gameService;
        this.color = color;

        initPanelLabels();

        this.panel = new Panel(client, gameService, windowSide, arraySide, color);
        panel.add(labelColor);
        panel.add(labelMove);
        this.add(panel);

        initButtons();

        this.setVisible(true);

        gameService.startGame(panel);
    }

    private void initButtons() {
        ButtonCommit buttonCommit = new ButtonCommit(gameService);
        panel.add(buttonCommit);

        ButtonReset buttonReset = new ButtonReset(gameService);
        panel.add(buttonReset);
    }

    private void initPanelLabels() {
        labelColor = new JLabel("This is your color.");
        labelColor.setBounds(70, 20, 300, 40);
        labelColor.setFont(new Font(labelColor.getName(), Font.BOLD, 22));
        labelColor.setForeground(color.darker());

        labelMove = new JLabel("Wait for your turn.");
        labelMove.setBounds(70, 70, 300, 40);
        labelMove.setFont(new Font(labelColor.getName(), Font.BOLD, 22));
    }

    public MessageInit initGameData() {
        DialogInit dialog = new DialogInit(this);
        while (dialog.playersNumber == 0);
        return new MessageInit(dialog.playersNumber, dialog.baseSide);
    }

    public void setLabelMoveText(String text) {
        labelMove.setText(text);
    }
}
