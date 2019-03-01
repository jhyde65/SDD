/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Main;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import view.GamePanel;

/**
 * Example code for movement strategy for now
 * controls the movement of the GameFigure, called from 
 * @author j
 */
public class GolemStrategy implements Strategy
{
    private int dx = 3;
    private int dy = 3;
    private int counter, rangeCounter;
    private int time, ranged;
    private int top;
    Random rand;
    private final int SIZE = 40;
    private float sx, sy;
    
    
    GolemStrategy()
    {
        counter = -1;
        rangeCounter = -1;
        rand = new Random();
        time = rand.nextInt(200) + 1;
        ranged = rand.nextInt(80) + 30;
        sx = 0;
        sy = 0;
        
    }
    
    @Override
    public void move(float x, float y, GameFigure context)
    {
        sx = x;
        sy = y;
        Rectangle2D hero = model.GameData.shooter.getCollisionBox();
        float heroX = (float) hero.getCenterX();
        float heroY = (float) hero.getCenterY();
        Point2D.Float golemCenter = new Point2D.Float(sx + 20, sy + 20);

        double distance = golemCenter.distance(heroX, heroY);
        
        if(rangeCounter > 40 && distance <= 150)
        {            
            Smash s = new Smash(golemCenter.x, golemCenter.y, heroX, heroY, distance);
            Main.gameData.enemyFigures.add(s);
            rangeCounter = -1;
            ranged = rand.nextInt(80) + 30;
        }
        else if(rangeCounter == ranged)
        {
            Rock r = new Rock(sx, sy, heroX, heroY);
            Main.gameData.enemyFigures.add(r);
            rangeCounter = -1;
            ranged = rand.nextInt(80) + 30;
        }
        
        if(counter < time)
        {
            sx += dx;
            if(sx + SIZE > GamePanel.width)
            {
                dx = -dx;
                sx = GamePanel.width - SIZE;
            }
            else if(sx < 0)
            {
                dx = -dx;
                sx = 0;
            }
        }
        
        else
        {
            sx += dx;
            sy += dy;
            if(sy + SIZE > GamePanel.height)
            {
                sy = GamePanel.height - SIZE;
                dy = -dy;
                top = rand.nextInt(GamePanel.height - SIZE);
                //if(top > GamePanel.height - SIZE)
            }
            else if(sx + SIZE > GamePanel.width)
            {
                dx = -dx;
                sx = GamePanel.width - SIZE;
            }
            else if(sx < 0)
            {
                dx = -dx;
                sx = 0;
            }
            else if(sy <= top)
            {
                sy = top;
                dy = -dy;
                counter = -1;
                time = rand.nextInt(200) + 1;
            }
        }
        

        context.setPosition(sx, sy);
        counter++;
        rangeCounter++;
        
    }
}
