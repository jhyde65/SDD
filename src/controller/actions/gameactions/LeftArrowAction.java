package controller.actions.gameactions;

import controller.Main;
import model.Shooter;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class LeftArrowAction extends AbstractAction
{
    @Override
    public void actionPerformed(ActionEvent e) {
        Shooter sh = Main.gameData.shooter;
        sh.translate(-10, 0);
    }
}
