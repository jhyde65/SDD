package controller.actions.gameactions;

import controller.Main;
import java.awt.Color;
import javax.swing.*;
import java.awt.event.ActionEvent;
import model.Fireball;
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

        Fireball m = new Fireball( x, y, x - 100, y - 100, Color.RED);
        Fireball m2 = new Fireball( x, y, x - 100, y - 50, Color.RED);
        Fireball m3 = new Fireball( x, y, x - 100, y, Color.RED);
        Fireball m4 = new Fireball( x, y, x - 100, y + 50, Color.RED);
        Fireball m5 = new Fireball( x, y, x - 100, y + 100, Color.RED);

        Main.gameData.friendFigures.add(m);
        Main.gameData.friendFigures.add(m2);
        Main.gameData.friendFigures.add(m3);
        Main.gameData.friendFigures.add(m4);
        Main.gameData.friendFigures.add(m5);
    }
}
