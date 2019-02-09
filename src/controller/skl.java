package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import model.Shooter;
import view.MainWindow;

public class skl extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {

        // horizontal move only
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                GameState.setPaused();
                break;
        }
    }

}
