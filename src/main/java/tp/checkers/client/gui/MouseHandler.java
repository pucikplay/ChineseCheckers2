package tp.checkers.client.gui;

import tp.checkers.client.ClientConnector;
import tp.checkers.client.GameService;
import tp.checkers.message.MessageClickedField;
import tp.checkers.server.game.Coordinates;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {
    private final ClientConnector client;
    private final GameService gameService;
    private final Panel panel;
    private final int windowSide;
    private final int arraySide;
    private final Color color;

    public MouseHandler(ClientConnector client, GameService gameService, Panel panel, int windowSide, int arraySide, Color color) {
        this.client = client;
        this.gameService = gameService;
        this.panel = panel;
        this.windowSide = windowSide;
        this.color = color;
        this.arraySide = arraySide;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (gameService.getIsMyTurn()) {
            double x = e.getX();
            double y = e.getY();
            int rectSide = windowSide / arraySide;

            for (int i = 1; i < arraySide; i++) {
                int cnt = 0;

                for (int j = 1; j < arraySide; j++) {
                    if (gameService.getField(i, j) != null) {
                        int locationMinX = rectSide * arraySide / 2 - gameService.getFieldsNumber(i) * rectSide / 2 + cnt * rectSide;
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
        if (gameService.getChosenField(0).i == 0 && gameService.getChosenField(0).j == 0) {
            if (gameService.getPieceColor(i, j) != null && gameService.getPieceColor(i, j).getRGB() == this.color.darker().getRGB()) {
                gameService.setChosenField(0, i, j);
                gameService.setPossibilities(client.receiveMovePossibilities(new MessageClickedField(i, j)));
            }
        } else {
            for (Coordinates movePossibility : gameService.getPossibilities()) {
                if (movePossibility.i == i && movePossibility.j == j) {
                    gameService.setChosenField(1, i, j);
                    break;
                }
            }
        }

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