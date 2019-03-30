/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Main;
import static model.GameData.shooter;

/**
 *
 * @author Brandy
 */
public class LevelDataManager {
    
    private GameData gameData;
    private int currentLevel = 1;
    
    public LevelDataManager(GameData context){
        gameData = context;
    }
    
    public void goNextLevel(){
        switch (currentLevel) {
            case 1:
                generateLevelTwo();
                break;
            case 2:
                generateLevelThree();
                break;
            case 3:
                generateBossLevel();
                break;
            default:
                break;
        }
        currentLevel++;
    }
    
    public void generateLevelOne(){
        gameData.resetGameArea();
        gameData.borders.add(new Border(400, 0, 100, 50));
        gameData.borders.add(new Border(700, 150, 35, Main.WIN_HEIGHT - 28));
        gameData.addMonsterEnemy(0, 0);
        gameData.addMonsterEnemy(50, 50);
        gameData.addMonsterEnemy(Main.WIN_WIDTH - 200, Main.WIN_HEIGHT - 200);
        gameData.addSpikeyEnemy();
        gameData.addSpikeyEnemy();
        gameData.addSpikeyEnemy();
        gameData.addSpikeyEnemy();
    }
    
    public void generateLevelTwo(){
        gameData.resetGameArea();
        gameData.borders.add(new Border(Main.WIN_WIDTH/2 - 250, Main.WIN_HEIGHT/2 - 50, 20, 135));
        gameData.borders.add(new Border(Main.WIN_WIDTH/2 - 250, Main.WIN_HEIGHT/2 - (20 + 50), 40, 20));
        gameData.borders.add(new Border(Main.WIN_WIDTH/2 - 250, Main.WIN_HEIGHT/2 - 50 + 135, 40, 20));

        gameData.borders.add(new Border(Main.WIN_WIDTH/2 + 250 + 34 - 20, Main.WIN_HEIGHT/2 - 50, 20, 135));
        gameData.borders.add(new Border(Main.WIN_WIDTH/2 + 250 + 34 - 20 - 20, Main.WIN_HEIGHT/2 - (20 + 50), 40, 20));
        gameData.borders.add(new Border(Main.WIN_WIDTH/2 + 250 + 34 - 20 - 20, Main.WIN_HEIGHT/2 - 50 + 135, 40, 20));

        gameData.shooter.setPosition(Main.WIN_WIDTH/2, Main.WIN_HEIGHT/2);
        
        SpikeyEnemy s1 = new SpikeyEnemy(Main.WIN_WIDTH/2 - 200, Main.WIN_HEIGHT/2 - 50);
        s1.movement = new BounceStraightOffBorderStrategy(Direction.EAST);
        s1.setDamage(20);
        s1.setInvulnerability(true);
        gameData.invulnerableEnemies.add(s1);
        
        SpikeyEnemy s2 = new SpikeyEnemy(Main.WIN_WIDTH/2 - 200, Main.WIN_HEIGHT/2 + 50);
        s2.movement = new BounceStraightOffBorderStrategy(Direction.EAST);
        s2.setDamage(20);
        s2.setInvulnerability(true);
        gameData.invulnerableEnemies.add(s2);
        
        SpikeyEnemy s3 = new SpikeyEnemy(Main.WIN_WIDTH/2 - 200, Main.WIN_HEIGHT/2);
        s3.movement = new BounceStraightOffBorderStrategy(Direction.EAST);
        s3.setDamage(20);
        s3.setInvulnerability(true);
        gameData.invulnerableEnemies.add(s3);
        
        SpikeyEnemy s4 = new SpikeyEnemy(Main.WIN_WIDTH/2 + 200 + 34, Main.WIN_HEIGHT/2 - 50);
        s4.movement = new BounceStraightOffBorderStrategy(Direction.WEST);
        s4.setDamage(20);
        s4.setInvulnerability(true);
        gameData.invulnerableEnemies.add(s4);
        
        SpikeyEnemy s5 = new SpikeyEnemy(Main.WIN_WIDTH/2 + 200 + 34, Main.WIN_HEIGHT/2 + 50);
        s5.movement = new BounceStraightOffBorderStrategy(Direction.WEST);
        s5.setDamage(20);
        s5.setInvulnerability(true);
        gameData.invulnerableEnemies.add(s5);
        
        SpikeyEnemy s6 = new SpikeyEnemy(Main.WIN_WIDTH/2 + 200 + 34, Main.WIN_HEIGHT/2);
        s6.movement = new BounceStraightOffBorderStrategy(Direction.WEST);
        s6.setDamage(20);
        s6.setInvulnerability(true);
        gameData.invulnerableEnemies.add(s6); 
        
        gameData.addMonsterEnemy(Main.WIN_WIDTH, 0);
        gameData.addMonsterEnemy(Main.WIN_WIDTH, Main.WIN_HEIGHT-100);
        gameData.addMonsterEnemy(0, 0);
        gameData.addMonsterEnemy(0, Main.WIN_HEIGHT-100);
        
        gameData.stairs.setPosition(Main.WIN_WIDTH/2, Main.WIN_HEIGHT/2);
    }
    
    public void generateLevelThree(){
        gameData.addGolem();
    }
    
    public void generateBossLevel(){
    }
}
