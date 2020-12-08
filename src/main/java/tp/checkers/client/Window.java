package tp.checkers.client;

import tp.checkers.message.MessageInit;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private Client client;
    private int width = 1000;
    private int height = 1000;

    public Window(Client client) {
        this.client = client;

        initUI();
        addComponents();

        this.setVisible(true);
    }

    private void initUI() {
        setTitle("Chinese checkers client");
        setSize(new Dimension(width, height));
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void addComponents() {
        System.out.println("Adding components");
    }

    public MessageInit initGameData() {
        DialogInitGame dialog = new DialogInitGame(this);
        int num;
        while (dialog.playersNumber == 0) {
            num = dialog.playersNumber;
        }
        return new MessageInit(dialog.playersNumber);
    }
}
