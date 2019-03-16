/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Example code for movement strategy for now
 * controls the movement of the GameFigure, called from 
 * @author j
 */
public class GolemDieingStrategy implements Strategy
{
    private int counter;
    private int time;

    
    
    GolemDieingStrategy()
    {
        counter = 0;
        time = 45;

    }
    
    @Override
    public void move(float x, float y, GameFigure context)
    {
        
        GolemBoss golem = (GolemBoss) context;
        
        if(counter == 1)
        {
            golem.setAnimation(golem.animation, golem.dieing);
        }
        if(counter >= time)
        {
            context.goNextState();
        }
       
        golem.animation.update();
        counter++;
        
    }
}
