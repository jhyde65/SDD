/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Main;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author kufre
 */
public class ShooterShield extends GameFigure{
    //Time the space shield is activated
    long startTime;
    //Length of time in ms that the shield remains active
    int duration;
    double width, height;
    final int sizeChange = 2;
    int stateSizeChangedAmount;
    private final int SPACE_SHIELD_EXPAND = 100;
    private final int SPACE_SHIELD_DEXPAND = 101;
    private final int SPACE_SHIELD_DYING = 102;
    
    int localState;

    public ShooterShield(float sx, float sy) {
        // Only important part of this super call is the last parameter
        // which sets the fire rate to every 30 seconds.
        super(sx, sy);
        super.state = new ActiveFigureState();
        startTime = System.currentTimeMillis();
        duration = 10 * 1000; // convert seconds to ms
        width = Main.gameData.shooter.getWidth() + 10;
        height = Main.gameData.shooter.getHeight() + 10;
        this.localState = SPACE_SHIELD_EXPAND;
        stateSizeChangedAmount = 0;
        
        Main.gameData.shooter.setShieldActive(true);
 
    }
    
    private void setPosition(){
        this.x = Main.gameData.shooter.getX();
        this.y = Main.gameData.shooter.getY();
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.BLUE);
        g.setStroke(new BasicStroke(3));
        g.drawOval((int) (super.x),
                (int) (super.y),
                (int)width, (int)height);
    }

    @Override
    public void update() {
        setPosition();
        //Check to see if the duration has passed
        if (System.currentTimeMillis()-startTime >= duration){
            this.localState = SPACE_SHIELD_DYING;
        }
        
        if (this.localState == SPACE_SHIELD_EXPAND){
            this.x -= sizeChange*stateSizeChangedAmount;
            this.y -= sizeChange*stateSizeChangedAmount;
            this.width += sizeChange*2;
            this.height += sizeChange*2;
            stateSizeChangedAmount += 1;
            if (stateSizeChangedAmount==10){
                this.localState = SPACE_SHIELD_DEXPAND;
            }
        } else if (this.localState == SPACE_SHIELD_DEXPAND){
            this.x -= sizeChange*stateSizeChangedAmount;
            this.y -= sizeChange*stateSizeChangedAmount;
            this.width -= sizeChange*2;
            this.height -= sizeChange*2;
            stateSizeChangedAmount -= 1;
            if (stateSizeChangedAmount==0){
                this.localState = SPACE_SHIELD_EXPAND;
            }
        }
        else if (this.localState == SPACE_SHIELD_DYING){
            
            // Make shooter vulnerable to damage again!
            Main.gameData.shooter.setShieldActive(false);
            this.state = new DoneFigureState();
        }
    }

    @Override
    public Rectangle2D.Float getCollisionBox() {
        return new Rectangle2D.Float (0,0,0,0);
    }

    @Override
    public void setState(GameFigureState state) {
        this.state = state;
    }

    @Override
    public void goNextState() {
        state.goNext(this);
    }

    @Override
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
}
