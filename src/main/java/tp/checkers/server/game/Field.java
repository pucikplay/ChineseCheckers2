package tp.checkers.server.game;

public class Field {
    private Color piece;
    private Color base;
    private Field[] neighbors;

    public Field() {
        neighbors = new Field[6];
    }

    public void setPiece(Color piece) {
        this.piece = piece;
    }

    public void setBase(Color base) {
        this.base = base;
    }

    public Color getBase() {
        return base;
    }

    public Color getPiece() {
        return piece;
    }

    public void setNeighbors(Field[] neighbors) {
        this.neighbors = neighbors;
    }

}
