/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.awt.geom.Rectangle2D;
import model.GameFigure;
import model.GameData;

/**
 *
 * @author Brandy
 */
public class CollisionManager {
    public void processMeleeCollisions(){
        for(GameFigure enemy : Main.gameData.enemyFigures){
            Rectangle2D gamerCollisionBox = GameData.shooter.getCollisionBox();
            
            if(enemy.getCollisionBox().intersects(gamerCollisionBox)){
                enemy.goNextState();
            }
        }
    }
    public void processItemCollisions(){
        for(GameFigure item : Main.gameData.itemFigures){
            Rectangle2D gamerCollisionBox = GameData.shooter.getCollisionBox();
            
            if(item.getCollisionBox().intersects(gamerCollisionBox)){
                item.goNextState();
            }
        }
    }
    public void processBorderCollisions(){
        // will be implemented when borders are implemented
    }
    
    public void processAllyWeaponCollisions(){
        
        //for(GameFigure weaponAttackAlly : Main.gameData.weaponAttackFigures){ 
        for(GameFigure weaponAttackAlly : Main.gameData.friendFigures){ 
            Rectangle2D weaponCollisionBox = weaponAttackAlly.getCollisionBox();

            for(GameFigure enemy: Main.gameData.enemyFigures){
                if(weaponCollisionBox.intersects(enemy.getCollisionBox())){
                    enemy.goNextState();
                    weaponAttackAlly.goNextState();
                }
            }
        }
    }
}
