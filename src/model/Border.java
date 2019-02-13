/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Brandy
 */
public class Border implements CollisionBox{

    private Rectangle2D.Float border;
    
    public Border(float startX, float startY, float endX, float endY){
        border = new Rectangle2D.Float(startX, startY, endX, endY);
    }
    
    @Override
    public Rectangle2D getCollisionBox() {
        return border;
    }
    
    public void render(Graphics2D g) {
        g.setColor(Color.RED);
        g.setStroke(new BasicStroke(2)); // thickness of the line
        g.drawRect((int)border.x, (int)border.y, (int)border.width, (int)border.height);
    }
    
}
