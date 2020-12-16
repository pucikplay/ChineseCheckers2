package tp.checkers.client.gui;

import tp.checkers.client.ClientConnector;
import tp.checkers.client.GameService;
import tp.checkers.server.game.Coordinates;

import javax.swing.*;
import java.awt.*;

/**
 * Class of tne client's panel.
 */
public class Panel extends JPanel {
    /**
     * Reference to game service of the client.
     */
    private final GameService gameService;

    /**
     * Size of the window's side.
     */
    private final int windowSide;

    /**
     * Length of the Fields array's side.
     */
    private final int arraySide;

    /**
     * Default constructor of the class.
     *
     * @param client reference to the client connector
     * @param gameService reference to game service of the client
     * @param windowSide size of the window's side
     * @param arraySide length of the Fields array's side
     * @param color color of the player
     */
    public Panel(ClientConnector client, GameService gameService, int windowSide, int arraySide, Color color) {
        this.gameService = gameService;
        this.windowSide = windowSide;
        this.arraySide = arraySide;

        this.setBackground(new Color(194, 187, 169));
        this.setLayout(null);

        MouseHandler handler = new MouseHandler(client, gameService, this, windowSide, arraySide, color);
        this.addMouseListener(handler);
    }

    /**
     * The main method responsible for painting,
     * calls the specific one for painting the board.
     *
     * @param g graphics
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        paintBoard(g2d);
    }

    /**
     * Method responsible for iterating through the Fields array
     * and calling the paintField method for every field.
     *
     * @param g2d graphics
     */
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

    /**
     * A method responsible for painting fields on the screen.
     * It counts the position of the field on the screen
     * and checks what filling color it should have.
     * Then it paints the field.
     *
     * @param g2d graphics
     * @param i i-coordinate in Fields array
     * @param j j-coordinate in Fields array
     * @param cnt counter of how many fields at this level
     *            have already been painted.
     */
    private void paintField(Graphics2D g2d, int i, int j, int cnt) {
        int rectSide = windowSide / arraySide;
        int x = windowSide / 2 - gameService.getFieldsNumber(i) * rectSide / 2 + cnt * rectSide;

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
