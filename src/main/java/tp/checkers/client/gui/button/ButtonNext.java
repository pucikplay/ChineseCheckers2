package tp.checkers.client.gui.button;

import tp.checkers.client.GameService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class of the commit button of client's GUI.
 */
public class ButtonNext extends JButton {
    /**
     * Default constructor of the class.
     *
     * @param gameService reference to game service object of the client
     */
    public ButtonNext(final GameService gameService) {
        initButton(gameService);
    }

    /**
     * Method responsible for initialising the button
     * and adding an action listener to it.
     *
     * @param gameService reference to game service object of the client
     */
    private void initButton(final GameService gameService) {
        this.setText("Next move");
        this.setBounds(655, 850, 150, 60);

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameService.receiveUpdates();
            }
        });
    }
}
