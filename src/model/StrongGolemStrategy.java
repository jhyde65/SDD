/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.geom.Rectangle2D;
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
      context.x = sx;
      context.y = sy;
        counter++;
        
    }
}
//public class StrongGolemStrategy implements Strategy
//{
//    //private int dx = 5;
//    //private int dy = 5;
//    private int counter;
//    private int time;
//    private int top;
//    Random rand;
//    private final int SIZE = 50;
//    private float bx, by, tx, ty, dx, dy;
//    private double angle;
//    private final int UNIT_TRAVEL = 5;
//    
//    
//    StrongGolemStrategy()
//    {
//        counter = -1;
//        rand = new Random();
//        time = rand.nextInt(200) +1;
//        bx = 0;
//        by = 0;
//        
//    }
//    
//    @Override
//    public void move(float x, float y, GameFigure context)
//    {
//        //set x & y location of GolemBoss
//        bx = x;
//        by = y;
////        
////        if(counter < time)
////        {
////            sx += dx;
////            if(sx + SIZE > GamePanel.width)
////            {
////                dx = -dx;
////                sx = GamePanel.width - SIZE;
////            }
////            else if(sx < 0)
////            {
////                dx = -dx;
////                sx = 0;
////            }
////        }
////        
////        else
////        {
////            sx += dx;
////            sy += dy;
////            if(sy + SIZE > GamePanel.height)
////            {
////                sy = GamePanel.height - SIZE;
////                dy = -dy;
////                top = rand.nextInt(GamePanel.height - SIZE);
////                //if(top > GamePanel.height - SIZE)
////            }
////            else if(sx + SIZE > GamePanel.width)
////            {
////                dx = -dx;
////                sx = GamePanel.width - SIZE;
////            }
////            else if(sx < 0)
////            {
////                dx = -dx;
////                sx = 0;
////            }
////            else if(sy <= top)
////            {
////                sy = top;
////                dy = -dy;
////                counter = -1;
////                time = rand.nextInt(200) + 1;
////            }
////        }
//        
////        bx = super.x;
////        by = super.y;
//        
//
//            if(counter < time)
//            {
//                dx = UNIT_TRAVEL;
//                bx += dx;
//                if(bx + SIZE > GamePanel.width)
//                {
//                    dx = -dx;
//                    bx = GamePanel.width - SIZE;
//                }
//                else if(bx < 0)
//                {
//                    dx = -dx;
//                    bx = 0;
//                }
//            }
//            
//            else if(counter == time)
//            {
//                Rectangle2D target = model.GameData.shooter.getCollisionBox();
//                Rectangle2D golemStart = context.getCollisionBox();
//                //Get center of shooter and GolemBoss for attack angle
//                tx = (float) target.getCenterX();
//                ty = (float) target.getCenterY();
//                bx = (float) golemStart.getCenterX();
//                by = (float) golemStart.getCenterY();
//                
//                angle = Math.atan2(ty - by, tx - bx);
//                dx = (float) (UNIT_TRAVEL * Math.cos(angle));
//                dy = (float) (UNIT_TRAVEL * Math.sin(angle));
////                if(ty - by < 0)
////                {
////                    dy = -dy;
////                }
////                if(tx - dx < 0)
////                {
////                    dx = -dx;
////                }
//            }
//            
//            else if(counter > time)
//            {
//                //bx += (float) (dx * Math.cos(angle));
//                //by += (float) (dy * Math.sin(angle));
//                bx += dx;
//                by += dy;
//                
//                if(by + SIZE > GamePanel.height)
//                {
//                    by = GamePanel.height - SIZE;
//                    dy = -dy;
//                    top = rand.nextInt(GamePanel.height - SIZE);
//                    //time = rand.nextInt(200) + 1;
//                }
//                else if(by < top)
//                {
//                    by = top;
//                    dy = -dy;
//                    time = rand.nextInt(200) + 1;
//                }
//                else if(bx + SIZE > GamePanel.width)
//                {
//                    dx = -dx;
//                    bx = GamePanel.width - SIZE;
//                    //attack = false;
//                    counter = -1;
//                    time = rand.nextInt(200) + 1;
//                    top = rand.nextInt(GamePanel.height - SIZE);
//                }
//                else if(bx < 0)
//                {
//                    dx = -dx;
//                    bx = 0;
//                    //attack = false;
//                    counter = -1;
//                    time = rand.nextInt(200) + 1;
//                    top = rand.nextInt(GamePanel.height - SIZE);
//                }
//                else if(by <= top)
//                {
//                    by = top;
//                    dy = -dy;
//                    counter = -1;
//                    //attack = false;
//                    time = rand.nextInt(200) + 1;
//                    top = rand.nextInt(GamePanel.height - SIZE);
//                }
//        }
//        counter++;
//        context.setPosition(bx, by);
//               
//    }
//}
