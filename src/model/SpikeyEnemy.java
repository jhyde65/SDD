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
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import view.GamePanel;

/**
 *
 * @author Brandy
 */
public class SpikeyEnemy extends GameFigure{

    private final int HEIGHT = 34;
    private final int WIDTH = 35;    
    
    public int health = 2;
    
    private int ticksBeforeChangingAnimation = 0;
    private final int TICKS_TO_CHANGE = 8;
    
    private HashMap<Integer, Image> animationImages = new HashMap<Integer, Image>();
    
    private Image currentImage;
    
    private final String imagePath1 = "..//resources//images//spikey1.png";
    private final String imagePath2 = "..//resources//images//spikey2.png";
    private final String imagePath3 = "..//resources//images//spikey3.png";
    private final String imagePath4 = "..//resources//images//spikey4.png";
    private int currentAnimationPointer = 0;

    private Strategy strategy;
    
    public SpikeyEnemy(float x, float y){
        super(x,y);
        super.state = GameFigureState.STATE_ACTIVE;
        strategy = new RollOnBorderStrategy();
        currentImage = null;
        
        try{
            animationImages.put(0, ImageIO.read(getClass().getResource(imagePath1)));
            animationImages.put(1, ImageIO.read(getClass().getResource(imagePath2)));
            animationImages.put(2, ImageIO.read(getClass().getResource(imagePath3)));
            animationImages.put(3, ImageIO.read(getClass().getResource(imagePath4)));
        } catch(IOException ex){
            JOptionPane.showMessageDialog(null, "Error: Cannot open one of the Spikey images");
            System.exit(-1);
        }
        currentImage = animationImages.get(currentAnimationPointer);
    }
    
    @Override
    public void render(Graphics2D g) {
        g.drawImage(currentImage, (int) super.x, (int) super.y,
            WIDTH, HEIGHT, null);

        // DEBUG ONLY:
        //g.setStroke(new BasicStroke(2)); // thickness of the line
        //g.draw(getCollisionBox());
    }

    @Override
    public void update() {
        if(state == GameFigureState.STATE_ACTIVE){
            strategy.move(super.x, super.y, this);
            ticksBeforeChangingAnimation++;
            if(ticksBeforeChangingAnimation == TICKS_TO_CHANGE){
                changeImage();
                ticksBeforeChangingAnimation = 0;
            }
        }
        else{
            state = GameFigureState.STATE_DONE;
        }
    }

    private void changeImage(){
        currentImage = animationImages.get(currentAnimationPointer);
        currentAnimationPointer = ((currentAnimationPointer + 1)% 4);
    }
    
    @Override
    public Rectangle2D getCollisionBox() {
        return new Rectangle2D.Float(x + (WIDTH*.1F)/2, y + (HEIGHT*.1F)/2, 
                WIDTH*.9F, HEIGHT*.9F);
    }
    
}
