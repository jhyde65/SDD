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
public class RollOnBorderStrategy implements Strategy {

    private final int UNIT_TRAVEL = 10;

    // starting direction
    private Direction direction = Direction.EAST;

    // TODO: Add this to GameFigure instead of a class-by-class basis
    private final int HEIGHT = 34;
    private final int WIDTH = 35;

    @Override
    public void move(float x, float y, GameFigure context) {
        move2(x, y, context);
//        if(GamePanel.width <= 0)
//            return;
//
//        if(direction != null)
//        switch (direction) {
//            case EAST:
//                context.x += UNIT_TRAVEL;
//                if(context.x + WIDTH > GamePanel.width){
//                    context.x = GamePanel.width - WIDTH; // readjust position
//                    direction = Direction.NORTH;
//                }   break;
//            case NORTH:
//                context.y -= UNIT_TRAVEL;
//                if(context.y < 0){
//                    context.y = 0;
//                    direction = Direction.WEST;
//                }   break;
//            case SOUTH:
//                context.y += UNIT_TRAVEL;
//                if(context.y + HEIGHT > GamePanel.height){
//                    context.y = GamePanel.height - HEIGHT;
//                    direction = Direction.EAST;
//                }   break;
//            case WEST:
//                // west
//                context.x -= UNIT_TRAVEL;
//                if(context.x < 0){
//                    context.x = 0;
//                    direction = Direction.SOUTH;
//                }   break;
//            default:
//                break;
//        }
    }

    private void move2(float x, float y, GameFigure context) {
        if (GamePanel.width <= 0) {
            return;
        }

        for (Border border : Main.gameData.borders) {
            Rectangle2D borderBox = border.getCollisionBox();
            switch (direction) {
                case EAST:
                    float futureXEast = x + UNIT_TRAVEL;
                    System.out.println();
                    if (borderBox.intersects(futureXEast, context.y, WIDTH, HEIGHT)) {
                        context.x = (float) (borderBox.getX() - WIDTH);
                        direction = Direction.NORTH;
                        return;
                    } else {
                        context.x = futureXEast;
                    }
                    break;
                case NORTH:
                    float futureYNorth = y - UNIT_TRAVEL;
                    if (borderBox.intersects(context.x, futureYNorth, WIDTH, HEIGHT)) {
                        context.y = (float) (borderBox.getY() + borderBox.getHeight());
                        direction = Direction.WEST;
                        return;
                    } else {
                        context.y = futureYNorth;
                    }
                    break;
                case SOUTH:
                    float futureYSouth = y + UNIT_TRAVEL;
                    if (borderBox.intersects(context.x, futureYSouth, WIDTH, HEIGHT)) {
                        context.y = (float) (borderBox.getY() - HEIGHT);
                        direction = Direction.EAST;
                        return;
                    } else {
                        context.y = futureYSouth;
                    }
                    break;
                case WEST:
                    float futureXWest = x - UNIT_TRAVEL;
                    if (borderBox.intersects(futureXWest, context.y, WIDTH, HEIGHT)) {
                        context.x = (float) (borderBox.getX() + borderBox.getWidth());
                        direction = Direction.SOUTH;
                        return;
                    } else {
                        context.x = futureXWest;
                    }
                    break;
                default:
                    break;
            }
        }
    }

}
