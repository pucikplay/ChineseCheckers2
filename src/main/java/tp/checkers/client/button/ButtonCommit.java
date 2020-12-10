package tp.checkers.client.button;

import tp.checkers.client.Panel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonCommit extends JButton {
    public ButtonCommit(final Panel panel) {
        initButton(panel);
    }

    private void initButton(final Panel panel) {
        this.setText("Commit your move");
        this.setBounds(655, 850, 150, 60);

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.commit();
            }
        });
    }
}
