package controller.actions.gameactions;

import controller.Main;
import java.awt.Color;
import javax.swing.*;
import java.awt.event.ActionEvent;
import model.Gunshot;
import model.Missile;
import model.Shooter;
import model.Sword;

public class CKeyAction extends AbstractAction
{
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //this is a statically got variable 
        //TODO: change this to get shooter gun location
        
        int x = 800, y = 550;
        Shooter shooter = (Shooter) Main.gameData.friendFigures.get(0);

        Gunshot m = new Gunshot( x, y, x - 100, y - 100, Color.RED);
        Gunshot m2 = new Gunshot( x, y, x - 100, y - 50, Color.RED);
        Gunshot m3 = new Gunshot( x, y, x - 100, y, Color.RED);
        Gunshot m4 = new Gunshot( x, y, x - 100, y + 50, Color.RED);
        Gunshot m5 = new Gunshot( x, y, x - 100, y + 100, Color.RED);

        Main.gameData.friendFigures.add(m);
        Main.gameData.friendFigures.add(m2);
        Main.gameData.friendFigures.add(m3);
        Main.gameData.friendFigures.add(m4);
        Main.gameData.friendFigures.add(m5);
        
        
        Main.sa.theme.stop();
    }
}
