/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.Color;

/**
 *
 * @author j
 */
public class Smash extends GameFigure implements Weapon
{
    private float dx, dy;
    private int smashSize = 20;
    private static final int MAX_SIZE = 150;
    public Color smashColor = Color.GRAY;
    private boolean done;
    private DamageStrategy damageStrategy;

    public Smash(float x, float y, float tx, float ty, double distance) {
        super(x, y);
        super.state = new ActiveFigureState();
        double angle = Math.atan2(ty - y, tx - x);
        dx = (float) ((distance/2) * Math.cos(angle));
        dy = (float) ((distance/2) * Math.sin(angle));
        super.x += dx;
        super.y += dy;
        done = false;
        damageStrategy = new DamageStrategyOncePerTarget(5);
    }
    

    @Override
    public void render(Graphics2D g) {
        g.setColor(smashColor);
        g.setStroke(new BasicStroke(2));
        
       g.drawOval((int)(super.x - smashSize/2), (int)(super.y - smashSize/2), smashSize, smashSize);
       g.drawOval((int)(super.x - smashSize/4), (int)(super.y - smashSize/4), smashSize/2, smashSize/2);
       g.drawOval((int)(super.x - smashSize/8), (int)(super.y - smashSize/8), smashSize/4, smashSize/4);
       
    }

    @Override
    public void update() {
        updateState();
        if(state instanceof ActiveFigureState)
        {
            updateSize();
        }
    }
    
    public void updateSize()
    {
        smashSize += 4;
    }
    
    public void updateState()
    {
        if(state instanceof ActiveFigureState) {
            if(smashSize >= MAX_SIZE) {
                done = true;
                goNextState();
                goNextState();
            }
        }
    }

    @Override
    public void setState(GameFigureState state) {
        this.state = state;
    }

    @Override
    public void goNextState() {
        if(done == true)
            state.goNext(this);
    }

    @Override
    public void setPosition(float x, float y) {
        super.x = x;
        super.y = y;
    }

    @Override
    public Rectangle2D getCollisionBox() {
        return new Rectangle2D.Float((super.x - smashSize/2), (super.y - smashSize/2), (float)(0.9* smashSize), (float)(0.9 * smashSize)); //set box out of screen for testing right now
    }
    
    @Override
    public void doDamageTo(GameFigureWithHealth target)
    {
        damageStrategy.doDamageTo(target);
    }
    
    @Override
    public void setDamage(int newDamage)
    {
        damageStrategy.setDamage(newDamage);
    }
    
}
