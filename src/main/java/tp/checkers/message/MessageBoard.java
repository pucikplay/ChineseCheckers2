package tp.checkers.message;

import tp.checkers.server.game.Field;

import java.awt.*;
import java.io.Serializable;

public class MessageBoard implements Serializable {
    private int baseSide;
    private Field[][] fields;
    private Color color;

    public MessageBoard(int baseSide, Field[][] fields, Color color) {
        this.baseSide = baseSide;
        this.fields = fields;
        this.color = color;
    }

    public int getBaseSide() {
        return baseSide;
    }

    public Field[][] getFields() {
        return fields;
    }

    public Color getColor() {
        return color;
    }
}
