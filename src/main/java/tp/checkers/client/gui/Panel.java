package tp.checkers.client.gui;

import tp.checkers.client.Client;
import tp.checkers.client.GameService;
import tp.checkers.server.game.Coordinates;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    private final GameService gameService;
    private final int width;
    private final int baseSide = 4; //to be passed from server!
    private final int arraySide = baseSide * 4 + 3;

    public Panel(Client client, GameService gameService, int width, Color color) {
        this.gameService = gameService;
        this.width = width;

        this.setBackground(new Color(194, 187, 169));
        this.setLayout(null);

        MouseHandler handler = new MouseHandler(client, gameService, this, width, color);
        this.addMouseListener(handler);
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
                if (gameService.getField(i, j) != null) {
                    paintField(g2d, i, j, cnt);

                    cnt++;
                }
            }
        }
    }

    private void paintField(Graphics2D g2d, int i, int j, int cnt) {
        int rectSide = width / arraySide;
        int x = width / 2 - gameService.getFieldsNumber(i) * rectSide / 2 + cnt * rectSide;

        g2d.setColor(Color.WHITE);

        if (gameService.getPieceColor(i, j) != null) {
            g2d.setColor(gameService.getPieceColor(i, j));
        } else if (gameService.getBaseColor(i, j) != null) {
            g2d.setColor(gameService.getBaseColor(i, j));
        }

        if ((gameService.getChosenField(0).i == i && gameService.getChosenField(0).j == j) || (gameService.getChosenField(1).i == i && gameService.getChosenField(1).j == j)) {
            g2d.setColor(new Color(134, 64, 0));
        } else if (gameService.getPossibilities() != null) {
            for (Coordinates possibility : gameService.getPossibilities()) {
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
}
