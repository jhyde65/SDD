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
    private float bx, by;
    private int dx = 5;
    private int dy = 5;
    
    private final String imagePath = "..//resources//images//golem_test.png";
    
    public GolemBoss(float x, float y)
    {
        super(x,y);
        super.state = GameFigureState.STATE_ACTIVE;
        counter = -1;
        rand = new Random();
        time = rand.nextInt(200) + 1;
        
        //for use when Strategy is implemented
        //super.state = new GolemStrongMove();
        
        
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
            
            else
            {
                bx += dx;
                by += dy;
                if(by + HEIGHT > GamePanel.height)
                {
                    by = GamePanel.height - HEIGHT;
                    dy = -dy;
                    top = rand.nextInt(GamePanel.height - HEIGHT);
                }
                else if(bx + WIDTH > GamePanel.width)
                {
                    dx = -dx;
                    bx = GamePanel.width - WIDTH;
                }
                else if(bx < 0)
                {
                    dx = -dx;
                    bx = 0;
                }
                else if(by <= top)
                {
                    by = top;
                    dy = -dy;
                    counter = -1;
                    time = rand.nextInt(200) + 1;
                }
            }
        }
        
        super.x = bx;
        super.y = by;
        counter++;
    //for use when Strategy fully implemented    
    //    context.setPosition(bx, by);
    }
    
    @Override
    public void render(Graphics2D g)
    {
        g.drawImage(image, (int) super.x, (int) super.y, WIDTH, HEIGHT, null);
    }
    
    @Override
    public Rectangle2D getCollisionBox()
    {
        /*
        //If figure is dying, set collision box out of game screen
        if(state instanceof DyingFigureState)
        {
            return new Rectangle2D.Float(-50, -50, 0, 0);
        }
        */
        
        return new Rectangle2D.Float(x, y, WIDTH, HEIGHT);
    }
    
    
    
    public void healthUpdate(int damage)
    {
        health -= damage;
        
        //need to add logic test 
    }
    
/*
// code to implement State design pattern    
    @Override
    public void setState(GameFigureState state)
    {
        this.state = state;
        
    }
    
    @Override
    public void goNextState()
    {
        
        if(state instanceof StrongFigureState)
        {
            hit = true;
            strategy = new GolemMove();
        }
        else 
        {  
            state.goNext(this);
            strategy = new GolemDieingMove();
        }
    }    
    
    @Override
    public void setPosition(float x, float y)
    {
        super.x = x;
        super.y = y;
    }
    
    */
}
