package tp.checkers.client;

import tp.checkers.message.MessageInit;
import tp.checkers.server.game.Field;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private Client client;
    private int width = 1000;
    private int height = 1000;
    private Field[][] fields;
    private Panel panel;

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

    public MessageInit initGameData() {
        DialogInitGame dialog = new DialogInitGame(this);
        int num;
        while (dialog.playersNumber == 0);
        return new MessageInit(dialog.playersNumber);
    }

    public void initBoard(Field[][] fields) {
        panel = new Panel(fields, width, height, client.objectOutputStream, client.objectInputStream);
        add(panel, BorderLayout.CENTER);
        this.setVisible(true);
    }
}
