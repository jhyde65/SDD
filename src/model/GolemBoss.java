/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import view.GamePanel;


/**
 *
 * @author jon
 */
public class GolemBoss extends GameFigure
{
    private final int HEIGHT = 50;
    private final int WIDTH = 50;
    private final int UNIT_TRAVEL = 5;
    private int time, counter, top;
    Random rand;
    private Image image;
    public int health = 30;
    private float bx, by, tx, ty, sx, sy;
    private int dx = 5;
    private int dy = 5;
    private boolean attack;
    private double angle;
    
    private final String imagePath = "..//resources//images//golem_test.png";
    
    public GolemBoss(float x, float y)
    {
        super(x,y);
        super.state = GameFigureState.STATE_ACTIVE;
        //super.state = new StrongFigureState();
        movement = new StrongGolemStrategy();
        counter = -1;
        rand = new Random();
        time = rand.nextInt(200) + 1;
        attack = false;
        top = rand.nextInt(750);
        
        
        
        image = null;
        
        try{
            image = ImageIO.read(getClass().getResource(imagePath));
        } catch(IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open " + imagePath);
            System.exit(-1);
        }
        
    }
        
    @Override
    public void update()
    {
//        movement.move(super.x, super.y, this);
        bx = super.x;
        by = super.y;
        
        if(state == GameFigureState.STATE_ACTIVE)
        {
            if(counter < time)
            {
                bx += dx;
                if(bx + WIDTH > GamePanel.width)
                {
                    dx = -dx;
                    bx = GamePanel.width - WIDTH;
                }
                else if(bx < 0)
                {
                    dx = -dx;
                    bx = 0;
                }
            }
            
            else if(attack == false)
            {
                attack = true;
                Rectangle2D target = model.GameData.shooter.getCollisionBox();
                Rectangle2D golemStart = this.getCollisionBox();
                //Get center of shooter for attack
                tx = (float) target.getCenterX();
                ty = (float) target.getCenterY();
                sx = (float) golemStart.getCenterX();
                sy = (float) golemStart.getCenterY();
                
                angle = Math.atan2(ty - sy, tx - sx);
                bx += (float) (UNIT_TRAVEL * Math.cos(angle));
                by += (float) (UNIT_TRAVEL * Math.sin(angle));                
            }
            
            else
            {
                bx += (float) (UNIT_TRAVEL * Math.cos(angle));
                by += (float) (UNIT_TRAVEL * Math.sin(angle));
                
                if(by + HEIGHT > GamePanel.height)
                {
                    by = GamePanel.height - HEIGHT;
                    dy = -dy;
                    top = rand.nextInt(GamePanel.height - HEIGHT);
                    attack = false;
                    counter = -1;
                    time = rand.nextInt(200) + 1;
                }
                else if(bx + WIDTH > GamePanel.width)
                {
                    dx = -dx;
                    bx = GamePanel.width - WIDTH;
                    attack = false;
                    counter = -1;
                    time = rand.nextInt(200) + 1;
                    top = rand.nextInt(GamePanel.height - HEIGHT);
                }
                else if(bx < 0)
                {
                    dx = -dx;
                    bx = 0;
                    attack = false;
                    counter = -1;
                    time = rand.nextInt(200) + 1;
                    top = rand.nextInt(GamePanel.height - HEIGHT);
                }
                else if(by <= top)
                {
                    by = top;
                    dy = -dy;
                    counter = -1;
                    attack = false;
                    time = rand.nextInt(200) + 1;
                    top = rand.nextInt(GamePanel.height - HEIGHT);
                }
            }
        }
        
        super.x = bx;
        super.y = by;
        counter++;

    }
    
    @Override
    public void render(Graphics2D g)
    {
        g.drawImage(image, (int) super.x, (int) super.y, WIDTH, HEIGHT, null);
    }
    
    @Override
    public Rectangle2D getCollisionBox()
    {
        
        //If figure is dying, set collision box out of game screen
//        if(state instanceof DieingFigureState)
//        {
//            return new Rectangle2D.Float(-50, -50, 0, 0);
//        }
        
        
        return new Rectangle2D.Float(x, y, WIDTH, HEIGHT);
    }
    
    
    //Just throwing out some thoughts here, havent tried to implement this yet
    
//    public void healthUpdate(int damage)
//    {
//        health -= damage;
//
//    }
//    
//
//// code to implement State design pattern    
//    @Override
//    public void setState(GameFigureState state)
//    {
//        this.state = state;
//        
//    }
//    
//    @Override
//    public void goNextState()
//    {
//        
//        if(state instanceof StrongFigureState && health > 10)
//        {
//            movement = new GolemStrategy();
//        }
//        else 
//        {  
//            state.goNext(this);
//            movement = new GolemDieingMove();
//        }
//    }    
//    
//    @Override
//    public void setPosition(float x, float y)
//    {
//        super.x = x;
//        super.y = y;
//    }
    
    
}
