package tp.checkers.client;

import tp.checkers.message.MessageClickedField;
import tp.checkers.message.MessageMove;
import tp.checkers.message.MessagePossibilities;
import tp.checkers.server.game.Field;
import tp.checkers.server.game.MovePossibility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Panel extends JPanel {
    private Field[][] fields;
    private int width;
    private int height;
    private int[] count;
    private int baseSide = 4; //to be passed from server!
    private int arraySide = baseSide * 4 + 3;
    private MouseHandler handler = null;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private int[] moveFields = new int[4];
    private MovePossibility[] movePossibilities;

    public Panel(Field[][] fields, int width, int height, ObjectOutputStream objectOutputStream, ObjectInputStream objectInputStream) {
        this.fields = fields;
        this.width = width;
        this.height = height;
        this.objectOutputStream = objectOutputStream;
        this.objectInputStream = objectInputStream;
        setBackground(new Color(194, 187, 169));
        setLayout(null);

        countBoard();
        initButtons();

        handler = new MouseHandler();
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

    private void initButtons() {
        JButton buttonCommit = new JButton("Commit your move");
        buttonCommit.setBounds(655, 850, 150, 60);
        add(buttonCommit);

        buttonCommit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Sending commit message");
                MessageMove msg = new MessageMove(false);
                msg.addMoveFields(moveFields);

                try {
                    objectOutputStream.writeObject(msg);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                clearActiveFields();
            }
        });

        JButton buttonReset = new JButton("Reset your move");
        buttonReset.setBounds(815, 850, 150, 60);
        add(buttonReset);

        buttonReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Sending reset message");
                MessageMove msg = new MessageMove(true);

                try {
                    objectOutputStream.writeObject(msg);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                clearActiveFields();
            }
        });
    }

    private void clearActiveFields() {
        for (int i = 0; i < moveFields.length; i++) {
            moveFields[i] = 0;
        }
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
        int rectSide = width/arraySide;

        for (int i = 1; i < arraySide; i++) {
            int cnt = 0;
            for (int j = 1; j < arraySide; j++) {
                if (fields[i][j] != null) {
                    g2d.setColor(new Color(11, 23, 11));

                    int x = rectSide * arraySide / 2 - count[i] * rectSide / 2 + cnt * rectSide;

                    g2d.draw(new Rectangle(x, i * rectSide, rectSide, rectSide));

                    if (movePossibilities != null) {
                        for (int k = 0; k < movePossibilities.length; k++) {
                            if (movePossibilities[k].i == i && movePossibilities[k].j == j) {
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

                    cnt++;
                }
            }
        }
    }

    private class MouseHandler implements MouseListener {
        @Override
        public void mousePressed(MouseEvent e) {
            if (moveFields[2] == 0 && moveFields[3] == 0) {
                double x = e.getX();
                double y = e.getY();

                int rectSide = width/arraySide;

                for (int i = 1; i < arraySide; i++) {
                    int cnt = 0;

                    for (int j = 1; j < arraySide; j++) {
                        if (fields[i][j] != null) {
                            int locationMinX = rectSide * arraySide / 2 - count[i] * rectSide / 2 + cnt * rectSide;
                            int locationMaxX = locationMinX + rectSide;
                            int locationMinY = i * rectSide;
                            int locationMaxY = locationMinY + rectSide;

                            if (x > locationMinX && x < locationMaxX && y > locationMinY && y < locationMaxY) {
                                markActive(i, j);
                            }

                            cnt++;
                        }
                    }
                }
            }
        }

        private void markActive(int i, int j) {
            if (moveFields[0] == 0 && moveFields[1] == 0) {
                moveFields[0] = i;
                moveFields[1] = j;

                MessageClickedField msg = new MessageClickedField(i, j);

                try {
                    objectOutputStream.writeObject(msg);
                    MessagePossibilities msgp = (MessagePossibilities) objectInputStream.readObject();
                    movePossibilities = msgp.possibilities;

                } catch (IOException | ClassNotFoundException ioException) {
                    ioException.printStackTrace();
                }
            } else {
                for (int k = 0; k < movePossibilities.length; k++) {
                    if (movePossibilities[k].i == i && movePossibilities[k].j == j) {
                        moveFields[2] = i;
                        moveFields[3] = j;
                        movePossibilities = null;
                        break;
                    }
                }
            }

            repaint();

            System.out.println("Clicked at element of array: fields[" + i + "][" + j + "]");
        }

        @Override
        public void mouseClicked(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    }

}
