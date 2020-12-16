package tp.checkers.server.game;

import tp.checkers.message.MessageMove;
import tp.checkers.server.ThreadPlayer;

import java.awt.*;

public class Player {
    private ThreadPlayer thread;
    private Color color;
    private Color enemyColor;
    private boolean active;
    private int leftToWin;

    public Player(ThreadPlayer thread, int leftToWin) {
        this.thread = thread;
        this.active = true;
        this.leftToWin = leftToWin;
    }

    public ThreadPlayer getThread() {
        return thread;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public Color getColor() {
        return color;
    }
    public void setEnemyColor(Color enemyColor) {
        this.enemyColor = enemyColor;
    }
    public Color getEnemyColor() {
        return enemyColor;
    }
    public boolean isActive() {
        return active;
    }
    public Coordinates pieceSelect() {
        return thread.pieceSelect();
    }

    public void sendPossibilities(Coordinates[] possibilities) {
        thread.sendPossibilities(possibilities);
    }

    public MessageMove pieceMove() {
        return thread.pieceMove();
    }

    public void updateBoard(Coordinates[] chosenFields, boolean yourMove) {
        thread.updateBoard(chosenFields, yourMove);
    }
    public void updateBoard(Coordinates[] chosenFields, boolean endGame, boolean youWon) {
        thread.updateBoard(chosenFields, endGame, youWon);
    }
    public boolean checkIfWon() {
        leftToWin--;
        return leftToWin == 0;
    }

    public int getLeftToWin() {
        return leftToWin;
    }

    public void setActive(boolean state) {
        this.active = state;
    }
}
