package controller.actions.gameactions;

import controller.Main;
import javax.swing.*;
import java.awt.event.ActionEvent;
import model.Sword;

public class ZKeyAction extends AbstractAction
{
    @Override
    public void actionPerformed(ActionEvent e) {
        
          Sword sword = (Sword)Main.gameData.friendFigures.get(1);
          sword.strick();
    }
}
