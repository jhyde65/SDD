/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import controller.Main;

/**
 *
 * @author Brandy
 */
public abstract class GameFigureWithHealth extends GameFigure{
    protected int currentHealth;
    protected int maxHealth;
    
    public GameFigureWithHealth(float x, float y, int currentH, int maxH) {
        super(x, y);
        currentHealth = currentH;
        maxHealth = maxH;
    }
    public GameFigureWithHealth(float x, float y) {
        super(x, y);
        currentHealth = 100;
        maxHealth = 100;
    }
    
    public int getCurrentHealth(){
        return currentHealth;
    }
    
    public void takeDamage(int damage)
    {
        currentHealth -= damage;
    }
    
    public void heal(int health)
    {
        currentHealth = (currentHealth + health) % maxHealth;
    }
    
    public boolean stillHasHealth(){
        return currentHealth >= 1;
    }
    
}
