package tp.checkers.client.gui.button;

import tp.checkers.client.GameService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonReset extends JButton {
    public ButtonReset(final GameService gameService) {
        initButton(gameService);
    }

    private void initButton(final GameService gameService) {
        this.setText("Reset your move");
        this.setBounds(815, 850, 150, 60);

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameService.reset();
            }
        });
    }
}
