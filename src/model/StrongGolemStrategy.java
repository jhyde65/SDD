/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Random;
import view.GamePanel;

/**
 * Example code for movement strategy for now
 * controls the movement of the GameFigure, called from 
 * @author j
 */
public class StrongGolemStrategy implements Strategy
{
    private int dx = 5;
    private int dy = 5;
    private int counter;
    private int time;
    private int top;
    Random rand;
    private final int SIZE = 40;
    private float sx, sy;
    
    
    StrongGolemStrategy()
    {
        counter = -1;
        rand = new Random();
        time = rand.nextInt(200) +1;
        sx = 0;
        sy = 0;
        
    }
    
    @Override
    public void move(float x, float y, GameFigure context)
    {
        sx = x;
        sy = y;
        
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
        
        //set the new position of the GameFigure
      //  context.setPosition(sx, sy);
        counter++;
        
    }
}
