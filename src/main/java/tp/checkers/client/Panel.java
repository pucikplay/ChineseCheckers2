package tp.checkers.client;

import tp.checkers.message.MessageMove;
import tp.checkers.server.game.Field;
import tp.checkers.server.game.MovePossibility;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Panel extends JPanel {
    private Field[][] fields;
    private int width;
    private int height;
    private int[] count;
    private int baseSide = 4; //to be passed from server!
    private int arraySide = baseSide * 4 + 3;
    private MouseHandler handler = null;
    private int[] moveFields = new int[4];
    private MovePossibility[] movePossibilities;
    private Client client;
    private boolean isMyTurn = true;

    public Panel(Client client, int width, int height) {
        this.client = client;
        this.width = width;
        this.height = height;
        setBackground(new Color(194, 187, 169));
        setLayout(null);
    }

    public void addFields(Field[][] fields) {
        this.fields = fields;
        countBoard();

        this.handler = new MouseHandler(client, this, width, height, fields, count, moveFields);
        addMouseListener(handler);
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

    public void commit() {
        if (isMyTurn) {
            System.out.println("Sending commit message");
            client.sendMove(new MessageMove(moveFields));
            clearActiveFields();
        }
    }

    public void reset() {
        if (isMyTurn) {
            System.out.println("Sending reset message");
            client.sendMove(new MessageMove(true));
            clearActiveFields();
        }
    }

    private void clearActiveFields() {
        Arrays.fill(moveFields, 0);

        movePossibilities = null;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        paintBoard(g2d);
    }

    private void paintBoard(Graphics2D g2d) {
        for (int i = 1; i < arraySide; i++) {
            int cnt = 0;

            for (int j = 1; j < arraySide; j++) {
                if (fields[i][j] != null) {
                    paintField(g2d, i, j, cnt);

                    cnt++;
                }
            }
        }
    }

    private void paintField(Graphics2D g2d, int i, int j, int cnt) {
        int rectSide = width/arraySide;
        int x = rectSide * arraySide / 2 - count[i] * rectSide / 2 + cnt * rectSide;

        if (fields[i][j].getBase() != null) {
            g2d.setColor(fields[i][j].getBase());
            g2d.fill(new Rectangle(x, i * rectSide, rectSide, rectSide));
        }

        if (fields[i][j].getPiece() != null) {
            g2d.setColor(fields[i][j].getPiece());
            g2d.fill(new Rectangle(x, i * rectSide, rectSide, rectSide));
        }

        g2d.setColor(new Color(11, 23, 11));

        if (movePossibilities != null) {
            for (MovePossibility movePossibility : movePossibilities) {
                if (movePossibility.i == i && movePossibility.j == j) {
                    g2d.setColor(new Color(92, 82, 92));
                    g2d.fill(new Rectangle(x, i * rectSide, rectSide, rectSide));
                    break;
                }
            }
        }

        if ((moveFields[0] == i && moveFields[1] == j) || (moveFields[2] == i && moveFields[3] == j)) {
            g2d.setColor(new Color(255, 0, 250));
            g2d.fill(new Rectangle(x, i * rectSide, rectSide, rectSide));
        }

        g2d.draw(new Rectangle(x, i * rectSide, rectSide, rectSide));
    }


    public void setMovePossibilities(MovePossibility[] movePossibilities) {
        this.movePossibilities = movePossibilities;
    }

    public boolean getIsMyTurn() {
        return isMyTurn;
    }

}
