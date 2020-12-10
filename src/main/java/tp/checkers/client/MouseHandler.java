package tp.checkers.client;

import tp.checkers.message.MessageClickedField;
import tp.checkers.server.game.Field;
import tp.checkers.server.game.Coordinates;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {
    private final int width;
    private final Field[][] fields;
    private final int[] count;
    private final Coordinates[] chosenFields;
    private final int baseSide = 4; //to be passed from server!
    private final Client client;
    private final Panel panel;
    private Coordinates[] movePossibilities;
    private Color color;

    public MouseHandler(Client client, Panel panel, int width, Field[][] fields, int[] count, Coordinates[] chosenFields, Color color) {
        this.client = client;
        this.panel = panel;
        this.width = width;
        this.fields = fields;
        this.count = count;
        this.chosenFields = chosenFields;
        this.color = color;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (panel.getIsMyTurn()) {
            double x = e.getX();
            double y = e.getY();
            int arraySide = baseSide * 4 + 3;
            int rectSide = width / arraySide;

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
        if (chosenFields[0].i == 0 && chosenFields[0].j == 0) {
            if (fields[i][j].getPiece().equals(this.color.darker())) {

                chosenFields[0].i = i;
                chosenFields[0].j = j;

                movePossibilities = client.receiveMovePossibilities(new MessageClickedField(i, j));
            }
        } else {
            for (Coordinates movePossibility : movePossibilities) {
                if (movePossibility.i == i && movePossibility.j == j) {
                    chosenFields[1].i = i;
                    chosenFields[1].j = j;
                    break;
                }
            }
        }

        panel.setMovePossibilities(movePossibilities);
        panel.repaint();

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