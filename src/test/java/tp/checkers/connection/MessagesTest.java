package tp.checkers.connection;

import org.junit.Test;
import static org.junit.Assert.*;

import tp.checkers.Coordinates;
import tp.checkers.message.MessageBoard;
import tp.checkers.message.MessageInit;
import tp.checkers.message.MessageMove;
import tp.checkers.message.MessageUpdate;
import tp.checkers.Field;

import java.awt.*;

/**
 * Class that tests messages.
 */
public class MessagesTest
{
    /**
     * Method responsible for initialisation of a MessageBoard.
     *
     * @return MessageBoard
     */
    private MessageBoard messageBoardTestInit() {
        Field[][] fields = new Field[2][2];

        fields[0][0] = new Field();
        fields[0][1] = new Field();
        fields[1][0] = new Field();
        fields[1][1] = new Field();

        fields[0][0].setBase(Color.MAGENTA);
        fields[1][1].setPiece(Color.BLUE);

        return new MessageBoard(3, fields, Color.CYAN);
    }

    /**
     * Method responsible for testing the MessageBoard.
     */
    @Test
    public void messageBoardTest() {
        MessageBoard msg = messageBoardTestInit();

        int baseSide = msg.getBaseSide();
        Field[][] fields = msg.getFields();
        Color color = msg.getColor();

        assertEquals(baseSide, 3);
        assertNull(fields[1][0].getPiece());
        assertNotNull(fields[1][1].getPiece());
        assertEquals(color, Color.CYAN);
    }

    /**
     * Method responsible for testing the MessageInit.
     */
    @Test
    public void messageInitTest() {
        MessageInit msg = new MessageInit(4, 2, false, false);

        assertEquals(msg.getPlayersNumber(), 4);
        assertEquals(msg.getBaseSide(), 2);
        assertFalse(msg.getCanLeaveBase());
        assertFalse(msg.getCanJump());
    }

    /**
     * Method responsible for testing the MessageMove
     * when the message contains reset data.
     */
    @Test
    public void messageMoveTestReset() {
        MessageMove msg = new MessageMove(true);

        assertTrue(msg.isReset());
        assertNull(msg.getChosenFields());
    }

    /**
     * Method responsible for testing the MessageMove
     * when the message contains move data.
     */
    @Test
    public void messageMoveTestMove() {
        Coordinates[] chosenFields = {new Coordinates(2, 3), new Coordinates(5, 6)};
        MessageMove msg = new MessageMove(chosenFields);

        assertFalse(msg.isReset());
        assertNotNull(msg.getChosenFields());
        assertEquals(msg.getChosenFields()[0].i, 2);
    }

    /**
     * Method responsible for testing the MessageUpdate
     * when it contains update data.
     */
    @Test
    public void messageUpdateTestUpdate() {
        MessageUpdate msg = new MessageUpdate(new Coordinates(2, 3), new Coordinates(6, 7), true);

        assertEquals(msg.getOrigin().i, 2);
        assertEquals(msg.getOrigin().j, 3);
        assertEquals(msg.getDestination().i, 6);
        assertEquals(msg.getDestination().j, 7);
        assertTrue(msg.isCurrPlayer());
        assertFalse(msg.isEndGame());
        assertFalse(msg.isYouWon());
    }

    /**
     * Method responsible for testing the MessageMove
     * when it contains end game data.
     */
    @Test
    public void messageUpdateTestEnd() {
        MessageUpdate msg = new MessageUpdate(new Coordinates(8, 9), new Coordinates(1, 1), true, true);

        assertEquals(msg.getOrigin().i, 8);
        assertEquals(msg.getOrigin().j, 9);
        assertEquals(msg.getDestination().i, 1);
        assertEquals(msg.getDestination().j, 1);
        assertFalse(msg.isCurrPlayer());
        assertTrue(msg.isEndGame());
        assertTrue(msg.isYouWon());
    }
}
