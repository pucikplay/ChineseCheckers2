package tp.checkers.client;

import tp.checkers.message.MessageMove;
import tp.checkers.message.MessageUpdate;
import tp.checkers.server.game.Field;
import tp.checkers.server.game.Coordinates;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    private final Field[][] fields;
    private final int width;
    private int[] count;
    private final int baseSide = 4; //to be passed from server!
    private final int arraySide = baseSide * 4 + 3;
    private final Coordinates[] chosenFields = {new Coordinates(0,0), new Coordinates(0,0)};
    private Coordinates[] possibilities;
    private final Client client;
    private boolean isMyTurn = false;

    public Panel(Client client, int width, int height, Field[][] fields, Color color) {
        this.client = client;
        this.width = width;
        this.fields = fields;

        setBackground(new Color(194, 187, 169));
        setLayout(null);

        countBoard();

        MouseHandler handler = new MouseHandler(client, this, width, fields, count, chosenFields, color);
        addMouseListener(handler);

        client.receiveUpdates(this);
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
            client.sendMove(new MessageMove(chosenFields));
            clearActiveFields();
            isMyTurn = false;
            client.receiveUpdates(this);
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
        for (Coordinates c : chosenFields) {
            c.i = 0;
            c.j = 0;
        }

        possibilities = null;
        repaint();
    }

    public void updateFields(MessageUpdate msg) {
        isMyTurn = msg.currPlayer;

        if (msg.origin.i != msg.destination.i && msg.origin.j != msg.destination.j) {
            Color color = fields[msg.origin.i][msg.origin.j].getPiece();
            fields[msg.origin.i][msg.origin.j].setPiece(null);
            fields[msg.destination.i][msg.destination.j].setPiece(color);

            repaint();
        }

    }


    //Painting methods:

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
        int rectSide = width / arraySide;
        int x = width / 2 - count[i] * rectSide / 2 + cnt * rectSide;

        g2d.setColor(Color.WHITE);

        if (fields[i][j].getPiece() != null) {
            g2d.setColor(fields[i][j].getPiece());
        } else if (fields[i][j].getBase() != null) {
            g2d.setColor(fields[i][j].getBase());
        }

        if ((chosenFields[0].i == i && chosenFields[0].j == j) || (chosenFields[1].i == i && chosenFields[1].j == j)) {
            g2d.setColor(new Color(134, 64, 0));
        } else if (possibilities != null) {
            for (Coordinates c : possibilities) {
                if (c.i == i && c.j == j) {
                    g2d.setColor(new Color(92, 82, 92));
                    break;
                }
            }
        }

        g2d.fill(new Rectangle(x, i * rectSide, rectSide, rectSide));

        g2d.setColor(new Color(11, 23, 11));
        g2d.draw(new Rectangle(x, i * rectSide, rectSide, rectSide));
    }


    //Setters and getters:

    public void setMovePossibilities(Coordinates[] movePossibilities) {
        this.possibilities = movePossibilities;
    }

    public boolean getIsMyTurn() {
        return isMyTurn;
    }

}
