package tp.checkers.client.gui;

import tp.checkers.Coordinates;
import tp.checkers.client.ClientConnector;
import tp.checkers.client.GameService;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Class responsible for handling the mouse's clicks.
 */
public class MouseHandler implements MouseListener {
    /**
     * Reference to the game service of the client.
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
     * Color of the player.
     */
    private final Color color;

    /**
     * Default constructor of the class.
     *
     * @param gameService reference to the game service of the client
     * @param windowSide size of the window's side
     * @param arraySide length of the Fields array's side
     * @param color color of the player
     */
    public MouseHandler(GameService gameService, int windowSide, int arraySide, Color color) {
        this.gameService = gameService;
        this.windowSide = windowSide;
        this.color = color;
        this.arraySide = arraySide;
    }

    /**
     * Method responsible for handling the pressed mouse if it's the player's turn.
     * It gets the click's coordinates, checks what place was pressed
     * and if it's needed calls the further method.
     *
     * @param e mouse event
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (gameService.isMyTurn()) {
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

    /**
     * Method responsible for marking the clicked field as one
     * of the active fields, and calling the panel's repaint method.
     *
     * @param i i-coordinate of the field in the array
     * @param j j-coordinate of the field in the array
     */
    private void markActive(int i, int j) {
        if (gameService.getChosenField(0).i == 0 && gameService.getChosenField(0).j == 0) {
            if (gameService.getPieceColor(i, j) != null && gameService.getPieceColor(i, j).getRGB() == this.color.darker().getRGB()) {
                gameService.setChosenField(0, i, j);
                gameService.setPossibilities(new Coordinates(i, j));
            }
        } else {
            for (Coordinates movePossibility : gameService.getPossibilities()) {
                if (movePossibility.i == i && movePossibility.j == j) {
                    gameService.setChosenField(1, i, j);
                    break;
                }
            }
        }

        gameService.repaintPanel();
        System.out.println("Clicked at element of array: fields[" + i + "][" + j + "]");
    }

    /**
     * Unused method of clicked mouse.
     * @param e mouse event
     */
    @Override
    public void mouseClicked(MouseEvent e) {}

    /**
     * Unused method of released mouse.
     * @param e mouse event
     */
    @Override
    public void mouseReleased(MouseEvent e) {}

    /**
     * Unused method of entered mouse.
     * @param e mouse event
     */
    @Override
    public void mouseEntered(MouseEvent e) {}

    /**
     * Unused method of exited mouse.
     * @param e mouse event
     */
    @Override
    public void mouseExited(MouseEvent e) {}
}