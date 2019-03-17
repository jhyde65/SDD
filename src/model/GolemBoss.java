/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author jon
 */
public class GolemBoss extends GameFigure
{
    private final int HEIGHT = 50;
    private final int WIDTH = 50;
    private int delay;
    public int health = 30;
    private boolean hit;
    
    private final String imagePath = "..//resources//images//golem//";
    public SpriteAnimation animation, moveDown, moveLeft, moveRight, moveUp, idle, dieing;
    
    
    public GolemBoss(float x, float y)
    {
        super(x,y);
        super.state = new StrongFigureState();
        movement = new StrongGolemStrategy();
        hit = false;
        
        BufferedImage[] movingDown = {Sprite.getSprite(imagePath, 0), Sprite.getSprite(imagePath, 1), Sprite.getSprite(imagePath, 8), Sprite.getSprite(imagePath, 10), Sprite.getSprite(imagePath, 16)};
        BufferedImage[] movingUp = {Sprite.getSprite(imagePath, 2), Sprite.getSprite(imagePath, 5), Sprite.getSprite(imagePath, 9), Sprite.getSprite(imagePath, 13), Sprite.getSprite(imagePath, 17)};
        BufferedImage[] movingLeft = {Sprite.getSprite(imagePath, 4), Sprite.getSprite(imagePath, 7), Sprite.getSprite(imagePath, 12), Sprite.getSprite(imagePath, 15), Sprite.getSprite(imagePath, 19)};
        BufferedImage[] movingRight = {Sprite.getSprite(imagePath, 3), Sprite.getSprite(imagePath, 6), Sprite.getSprite(imagePath, 11), Sprite.getSprite(imagePath, 14), Sprite.getSprite(imagePath, 18)};
        BufferedImage[] idling = {Sprite.getSprite(imagePath, 0), Sprite.getSprite(imagePath, 1)};
        BufferedImage[] dieing = {Sprite.getSprite(imagePath, 44), Sprite.getSprite(imagePath, 47), Sprite.getSprite(imagePath, 49), Sprite.getSprite(imagePath, 48), Sprite.getSprite(imagePath, 50), 
            Sprite.getSprite(imagePath, 52), Sprite.getSprite(imagePath, 56), Sprite.getSprite(imagePath, 57), Sprite.getSprite(imagePath, 61), Sprite.getSprite(imagePath, 62)};
        
        this.moveDown = new SpriteAnimation(movingDown, 5);
        this.moveUp = new SpriteAnimation(movingUp, 5);
        this.moveLeft = new SpriteAnimation(movingLeft, 5);
        this.moveRight = new SpriteAnimation(movingRight, 5);
        this.idle = new SpriteAnimation(idling, 5);
        this.dieing = new SpriteAnimation(dieing, 5);
        this.animation = idle;
        animation.start();
        
    }
        
    @Override
    public void update()
    {
        movement.move(super.x, super.y, this);
        delay++;
    }
    
    @Override
    public void render(Graphics2D g)
    {
        g.drawImage(animation.getSprite(), (int) super.x, (int) super.y, WIDTH, HEIGHT, null);
    }
    
    public void setAnimation(SpriteAnimation animation, SpriteAnimation newAnimation)
    {
        if(animation.equals(newAnimation)) return;
        this.animation.stop();
        this.animation.reset();
        this.animation = newAnimation;
        this.animation.restart();
    }
    
    @Override
    public Rectangle2D getCollisionBox()
    {
        
        //If figure is dying, set collision box out of game screen
//        if(state instanceof DieingFigureState)
//        {
//            return new Rectangle2D.Float(-50, -50, 0, 0);
//        }
        
        if(state instanceof DieingFigureState)
        {
            return new Rectangle2D.Float(-50, -50, 0, 0);
        }
        else if(hit == true && delay < 100)
        {
            return new Rectangle2D.Float(-50, -50, 0, 0);
        }
        else
        {
            return new Rectangle2D.Float(x, y, WIDTH, HEIGHT);
        }
    }
    
    
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
            movement = new GolemStrategy();
            state.goNext(this);
            hit = true;
            delay = 0;
        }
        else if(state instanceof ActiveFigureState)
        {  
            state.goNext(this);
            movement = new GolemDieingStrategy();
        }
        else if(state instanceof DieingFigureState)
        {
            state.goNext(this);
        }
    }    
    
    @Override
    public void setPosition(float x, float y)
    {
        super.x = x;
        super.y = y;
    }
    
    
}
