package tp.checkers.client.button;

import tp.checkers.client.gameservice.GameServiceSaved;

import javax.swing.*;

/**
 * Class of the button of client's GUI that requests for the next saved move.
 */
public class ButtonNext extends JButton {
    /**
     * Default constructor of the class.
     *
     * @param gameService reference to game service object of the client
     */
    public ButtonNext(final GameServiceSaved gameService) {
        initButton(gameService);
    }

    /**
     * Method responsible for initialising the button
     * and adding an action listener to it.
     *
     * @param gameService reference to game service object of the client
     */
    private void initButton(final GameServiceSaved gameService) {
        this.setText("Next move");
        this.setBounds(695, 850, 200, 60);

        this.addActionListener(e -> gameService.nextSavedMove());
    }
}
