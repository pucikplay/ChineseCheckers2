package tp.checkers.client;

import tp.checkers.server.game.Field;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    private Field[][] fields;
    private int width;
    private int height;
    private int[] count;
    private int baseSide = 4; //to be passed from server!
    private int arraySide = baseSide * 4 + 3;

    public Panel(Field[][] fields, int width, int height) {
        this.fields = fields;
        this.width = width;
        this.height = height;
        setBackground(new Color(194, 187, 169));

        countBoard();
    }

    private void countBoard() {
        count = new int[arraySide];

        for (int i = 1; i < arraySide; i++) {
            for (int j = 1; j < arraySide; j++) {
                if (fields[i][j] != null) {
                    count[i]++;
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        paintBoard(g2d);
    }

    private void paintBoard(Graphics2D g2d) {
        int rectSide = width/arraySide;

        for (int i = 1; i < arraySide; i++) {
            int cnt = 0;
            for (int j = 1; j < arraySide; j++) {
                if (fields[i][j] != null) {
                    g2d.setColor(new Color(11, 23, 11));

                    int x = rectSide * arraySide / 2 - count[i] * rectSide / 2 + cnt * rectSide;

                    g2d.draw(new Rectangle(x, i*rectSide, rectSide, rectSide));
                    cnt++;
                }
            }
        }
    }
}
