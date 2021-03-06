package tp.checkers.client.button;

import tp.checkers.client.gameservice.GameServicePlayed;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class of the reset button of client's GUI.
 */
public class ButtonReset extends JButton {
    /**
     * Default constructor of the class.
     *
     * @param gameService reference to game service object of the client
     */
    public ButtonReset(final GameServicePlayed gameService) {
        initButton(gameService);
    }

    /**
     * Method responsible for initialising the button
     * and adding an action listener to it.
     *
     * @param gameService reference to game service object of the client
     */
    private void initButton(final GameServicePlayed gameService) {
        this.setText("Reset your move");
        this.setBounds(815, 850, 150, 60);

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Commit button has been clicked");
                gameService.reset();
            }
        });
    }
}
