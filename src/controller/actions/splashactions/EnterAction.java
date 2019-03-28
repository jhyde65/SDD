package controller.actions.splashactions;

import controller.GameStaticState;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EnterAction extends AbstractAction
{
    @Override
    public void actionPerformed(ActionEvent e) {
        GameStaticState.setPaused();
    }
}
