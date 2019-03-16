package controller;

import Inventory.Inventory;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import model.Missile;
import model.Shooter;

public class MouseController extends MouseAdapter {

    public int x;
    public int y;

    @Override
    public void mousePressed(MouseEvent me) {

        int px = me.getX();
        int py = me.getY();

        Shooter shooter = Main.gameData.shooter;

        Missile m = new Missile(
                shooter.getXofMissileShoot(),
                shooter.getYofMissileShoot(),
                px, py, // target location where the missile explodes
                Color.RED);

        Main.gameData.friendFigures.add(m);
        
        if(Inventory.isOpen){
            Inventory.update();
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

}
