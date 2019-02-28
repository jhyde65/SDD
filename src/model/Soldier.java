/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author kufre
 */

public class Soldier extends GameFigure {
    
    BufferedImage image = null;
    private boolean forward = false;
    private boolean strick = false;
    private int frameCount = 0;
    public Soldier(float x, float y) {
        super(x, y);
        //super.state = GameFigureState.STATE_ACTIVE;
        super.state = new ActiveFigureState();
        
        
        
    //image of sword        
        try {
            image = ImageIO.read(getClass().getResource("..//resources//images//soldier.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open sword.png");
            System.exit(-1);
        }
    }


    
    //implement abstract class render
    @Override
    public void render(Graphics2D g){
      g.drawImage(image, (int) super.x, (int) super.y,
              200, 200, null);
    }
    
    //implement abstract class update
    @Override
    public void update(){
        
        //for animation, 
        
        if(strick){
            frameCount++;
         if(forward){
             x += 4;
         }else{
             x -= 4;
             
             if(frameCount == 10){
                 strick = false;
             }
         }
         
         //Change direction every 10 frames
         
         if(frameCount == 10){
             forward = false;
             frameCount = 0;
             
         }
         
        }
         
    }

    @Override
    public Rectangle2D getCollisionBox() {
        //throw new UnsupportedOperationException("Not supported yet."); 
        return new Rectangle2D.Float(0,0,0,0);
    }
    
    public void strick(){
        //strick by making forward true
        strick = true;
        forward = true;
    }
    
    
    @Override
    public void setState(GameFigureState state) {
        this.state = state;
    }
    
    @Override
    public void goNextState() {
        
    }

    
    @Override
    public void setPosition(float x, float y) {
        super.x = x;
        super.y = y;
    }

}
