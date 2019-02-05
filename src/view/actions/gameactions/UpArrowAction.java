package view.actions.gameactions;

import controller.Main;
import model.Shooter;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class UpArrowAction extends AbstractAction
{
    @Override
    public void actionPerformed(ActionEvent e) {
        Shooter sh = (Shooter) Main.gameData.friendFigures.get(0);
        sh.translate(0, -10);
    }
}
