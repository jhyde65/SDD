/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import view.GamePanel;

/**
 *
 * @author Brandy
 */
public class RollOnBorderStrategy implements Strategy {

    private enum Direction{
        NORTH, SOUTH, EAST, WEST;
    }
    private final int UNIT_TRAVEL = 10;

    // starting direction
    private Direction direction = Direction.EAST;
    
    // TODO: Add this to GameFigure instead of a class-by-class basis
    private final int HEIGHT = 34;
    private final int WIDTH = 35;
    
    @Override
    public void move(float x, float y, GameFigure context) {
        if(GamePanel.width <= 0)
            return;

        if(direction != null)
        switch (direction) {
            case EAST:
                context.x += UNIT_TRAVEL;
                if(context.x + WIDTH > GamePanel.width){
                    context.x = GamePanel.width - WIDTH; // readjust position
                    direction = Direction.NORTH;
                }   break;
            case NORTH:
                context.y -= UNIT_TRAVEL;
                if(context.y < 0){
                    context.y = 0;
                    direction = Direction.WEST;
                }   break;
            case SOUTH:
                context.y += UNIT_TRAVEL;
                if(context.y + HEIGHT > GamePanel.height){
                    context.y = GamePanel.height - HEIGHT;
                    direction = Direction.EAST;
                }   break;
            case WEST:
                // west
                context.x -= UNIT_TRAVEL;
                if(context.x < 0){
                    context.x = 0;
                    direction = Direction.SOUTH;
                }   break;
            default:
                break;
        }
    }
    
}