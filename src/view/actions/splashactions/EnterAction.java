package view.actions.splashactions;

import controller.GameState;
import controller.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EnterAction extends AbstractAction
{
    @Override
    public void actionPerformed(ActionEvent e) {
        GameState.setPaused();
    }
}
