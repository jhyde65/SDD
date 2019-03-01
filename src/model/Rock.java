/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author j
 */
public class Rock extends GameFigure 
{
    public Point2D.Float hero;
    private float dx, dy;
    private static final int MOVE_DISTANCE = 7;
    private int rockSize = 15;
    private static final int MAX_SIZE = 50;
    private Image image;
    private final String imagePath = "..//resources//images//RockAttack.png";

    public Rock(float x, float y, float tx, float ty) {
        super(x, y);
        super.state = new ActiveFigureState();
        double angle = Math.atan2(ty - y, tx - x);
        dx = (float) (MOVE_DISTANCE * Math.cos(angle));
        dy = (float) (MOVE_DISTANCE * Math.sin(angle));
        hero = new Point2D.Float(tx, ty);
        //System.out.println("Rock attack");
        
        image = null;
        
        try {
            image = ImageIO.read(getClass().getResource(imagePath));
        } catch(IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open " + imagePath);
            System.exit(-1);
        }
    }
    
    public void updateLocation() {
        super.x += dx;
        super.y += dy;
        
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(image, (int) super.x, (int) super.y, rockSize, rockSize, null);//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update() {
        updateState();
        if(state instanceof ActiveFigureState)
        {
            updateLocation();
        }
        else if (state instanceof DieingFigureState)
        {
            updateSize();
        }
    }
    
    public void updateSize()
    {
        rockSize += 2;
    }
    
    public void updateState()
    {
        if(state instanceof ActiveFigureState)
        {
            double distance = hero.distance(super.x, super.y);
            if(distance <= 5) {
                goNextState();
               // System.out.println("Target reached, go Next state");
            }
        } else if(state instanceof DieingFigureState) {
            if(rockSize >= MAX_SIZE) {
                goNextState();
               // System.out.println("Max rockSize reached, go Next state");
            }
        }
    }

    @Override
    public void setState(GameFigureState state) {
        this.state = state;
    }

    @Override
    public void goNextState() {
        state.goNext(this);
    }

    @Override
    public void setPosition(float x, float y) {
        super.x = x;
        super.y = y;
    }

    @Override
    public Rectangle2D getCollisionBox() {
        return new Rectangle2D.Float(x, y, rockSize, rockSize); //set box out of screen for testing right now
    }
    
    
}
