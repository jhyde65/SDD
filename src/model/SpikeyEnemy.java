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

/**
 *
 * @author Brandy
 */
public class SpikeyEnemy extends GameFigure{
    private final int HEIGHT = 34;
    private final int WIDTH = 35;
    private final int UNIT_TRAVEL = 5;
    public int health = 2;
    private Image image;

    private final String imagePath = "..//resources//images//spikey1.png";
    
    
    public SpikeyEnemy(float x, float y){
        super(x,y);
        super.state = GameFigureState.STATE_ACTIVE;

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
    }

    @Override
    public Rectangle2D getCollisionBox() {
        return new Rectangle2D.Float(x + (WIDTH*.1F)/2, y + (HEIGHT*.1F)/2, 
                WIDTH*.9F, HEIGHT*.9F);
    }
    
    
}
