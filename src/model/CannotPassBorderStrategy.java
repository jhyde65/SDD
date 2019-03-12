/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Main;
import java.awt.geom.Rectangle2D;
import view.GamePanel;

/**
 *
 * @author Brandy
 */
public class CannotPassBorderStrategy implements Strategy {

    // TODO: Add this to GameFigure instead of a class-by-class basis
    private final int HEIGHT = 20;
    private final int WIDTH = 20;
    
    public CannotPassBorderStrategy(){
        
    }
    
    // NOTE ------------------------------------------------------
    // This strategy takes the future positional increment
    // then determines which direction it object is going
    // (THIS DOESN'T WORK WITH DIAGONAL DIRECTIONS!!!!!).
    // After determining the direction, it will check if any borders
    // are in the way. If the GameFigure is moving towards a border, it will
    // only let it move right up against the border. It will be stopped right
    // before merging with the border.
    @Override
    public void move(float dx, float dy, GameFigure context) {
        Direction direction;
        if(dx > 0 && dy == 0){
            direction = Direction.EAST;
        }
        else if(dx < 0 && dy == 0){
            direction = Direction.WEST;
        }
        else if(dx == 0 && dy < 0){
            direction = Direction.NORTH;
        }
        else if(dx == 0 && dy > 0){
            direction = Direction.SOUTH;
        }
        else{
            return;
        }
        if(GamePanel.width <= 0)
            return;
        
        float futureX = dx + context.x;
        float futureY = dy + context.y;
        
        for(Border border: Main.gameData.borders){
            Rectangle2D borderBox = border.getCollisionBox();
            switch(direction){
                case EAST:
                    System.out.println();
                    if(borderBox.intersects(futureX, context.y, WIDTH, HEIGHT)){
                        context.x = (float)(borderBox.getX() - WIDTH);
                        return;
                    }
                    else{
                        context.x = futureX;
                    }
                    break;
                case NORTH:
                    if(borderBox.intersects(context.x, futureY, WIDTH, HEIGHT)){
                        context.y = (float)(borderBox.getY() + borderBox.getHeight());
                        return;
                    }
                    else{
                        context.y = futureY;
                    }
                    break;
                case SOUTH:
                    if(borderBox.intersects(context.x, futureY, WIDTH, HEIGHT)){
                        context.y = (float)(borderBox.getY() - HEIGHT);
                        return;
                    }
                    else{
                        context.y = futureY;
                    }
                    break;
                case WEST:
                    if(borderBox.intersects(futureX, context.y, WIDTH, HEIGHT)){
                        context.x = (float)(borderBox.getX() + borderBox.getWidth());
                        return;
                    }
                    else{
                        context.x = futureX;
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
