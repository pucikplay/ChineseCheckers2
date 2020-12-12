package tp.checkers.client;

import tp.checkers.message.MessageMove;
import tp.checkers.message.MessageUpdate;
import tp.checkers.server.game.Field;
import tp.checkers.server.game.Coordinates;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

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
    private final JLabel labelMove;
    private SwingWorker<Void, Void> swingWorker;

    public Panel(Client client, int width, int height, Field[][] fields, Color color, JLabel labelMove) {
        this.client = client;
        this.width = width;
        this.fields = fields;
        this.labelMove = labelMove;

        setBackground(new Color(194, 187, 169));
        setLayout(null);

        countBoard();

        MouseHandler handler = new MouseHandler(client, this, width, fields, count, chosenFields, color);
        addMouseListener(handler);

        initSwingWorker();

        //swingWorker.execute();
    }

    private void initSwingWorker() {
        this.swingWorker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
                System.out.println("I'm in swing workah!");
                client.receiveUpdates(Panel.this);
                return null;
            }

            @Override
            protected void done() {
                System.out.println("Leaving swing workah");
            }
        };

        swingWorker.execute();
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
            if (chosenFields[1].i == 0) {
                chosenFields[1].i = chosenFields[0].i;
                chosenFields[1].j = chosenFields[0].j;
            }

            System.out.println("Sending commit message");
            client.sendMove(new MessageMove(chosenFields));
            clearActiveFields();
            isMyTurn = false;
            System.out.println("Sent commit message");
            //swingWorker.execute();
            initSwingWorker();
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
        System.out.println("Updating fields");

        if (msg.origin.i != msg.destination.i && msg.origin.j != msg.destination.j) {
            Color color = fields[msg.origin.i][msg.origin.j].getPiece();
            fields[msg.origin.i][msg.origin.j].setPiece(null);
            fields[msg.destination.i][msg.destination.j].setPiece(color);

            repaint();
        }

        isMyTurn = msg.currPlayer;
        System.out.println(isMyTurn);
        if (! isMyTurn) {
            labelMove.setText("Not your move");
            System.out.println("Calling receive updates");
            //swingWorker.execute();
            initSwingWorker();
        } else {
            labelMove.setText("Your move.");
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
            for (Coordinates possibility : possibilities) {
                System.out.println(possibility.i + " " + possibility.j);
                if (possibility.i == i && possibility.j == j) {
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
