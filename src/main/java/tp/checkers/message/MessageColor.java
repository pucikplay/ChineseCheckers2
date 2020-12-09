package tp.checkers.message;

import tp.checkers.server.game.Field;

import java.awt.*;
import java.io.Serializable;

public class MessageColor implements Serializable {
    public Color color;

    public MessageColor(Color color) { this.color = color; }
}
