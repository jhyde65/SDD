/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import view.GamePanel;

/**
 *
 * @author Brandy
 */
public class SpikeyEnemy extends GameFigure{
    // TODO: Delete these when observer collision is finished
    // and when refactoring is done
    private final int HEIGHT = 34;
    private final int WIDTH = 35;
    private final int UNIT_TRAVEL = 10;
    private enum Direction{
        NORTH, SOUTH, EAST, WEST;
    }
    private Direction direction = Direction.WEST;
    // TODO END
    
    public int health = 2;
    private Image image;

    private final String imagePath = "..//resources//images//spikey1.png";

    
    private Strategy strategy;
    
    public SpikeyEnemy(float x, float y){
        super(x,y);
        super.state = GameFigureState.STATE_ACTIVE;
        strategy = new RollOnBorderStrategy();
        image = null;
        
        try{
            image = ImageIO.read(getClass().getResource(imagePath));
        } catch(IOException ex){
            JOptionPane.showMessageDialog(null, "Error: Cannot open " + imagePath);
            System.exit(-1);
        }
    }
    
    @Override
    public void render(Graphics2D g) {
        g.drawImage(image, (int) super.x, (int) super.y,
            WIDTH, HEIGHT, null);

        // DEBUG ONLY:
        //g.setStroke(new BasicStroke(2)); // thickness of the line
        //g.draw(getCollisionBox());
        
        
    }

    @Override
    public void update() {
        if(state == GameFigureState.STATE_ACTIVE){
            strategy.move(super.x, super.y, this);
            /* TODO: Delete when observer collision is finished
            if(GamePanel.width <= 0)
                return;
            
            if(direction != null)
            switch (direction) {
                case EAST:
                    super.x += UNIT_TRAVEL;
                    if(super.x + WIDTH > GamePanel.width){
                        super.x = GamePanel.width - WIDTH; // readjust position
                        direction = Direction.NORTH;
                    }   break;
                case NORTH:
                    super.y -= UNIT_TRAVEL;
                    if(super.y < 0){
                        super.y = 0;
                        direction = Direction.WEST;
                    }   break;
                case SOUTH:
                    super.y += UNIT_TRAVEL;
                    if(super.y + HEIGHT > GamePanel.height){
                        super.y = GamePanel.height - HEIGHT;
                        direction = Direction.EAST;
                    }   break;
                case WEST:
                    // west
                    super.x -= UNIT_TRAVEL;
                    if(super.x < 0){
                        super.x = 0;
                        direction = Direction.SOUTH;
                    }   break;
                default:
                    break;
            }

            */
        }
    }

    @Override
    public Rectangle2D getCollisionBox() {
        return new Rectangle2D.Float(x + (WIDTH*.1F)/2, y + (HEIGHT*.1F)/2, 
                WIDTH*.9F, HEIGHT*.9F);
    }
    
}
