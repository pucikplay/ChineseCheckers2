package tp.checkers.message;

import tp.checkers.server.game.Field;

import java.io.Serializable;

public class MessageFields implements Serializable {
    public Field[][] fields;

    public MessageFields(Field[][] fields) { this.fields = fields; }
}
