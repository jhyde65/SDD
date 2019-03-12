/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.awt.geom.Rectangle2D;
import model.GameFigure;
import model.GameData;
import model.GameFigureWithHealth;

/**
 *
 * @author Brandy
 */
public class CollisionManager {

    // Manages enemy body attacks
    public void processMeleeCollisions() {
        Rectangle2D gamerCollisionBox = GameData.shooter.getCollisionBox();
        for (GameFigureWithHealth enemyWithHealth : Main.gameData.enemyFiguresWithHealth) {
            if (enemyWithHealth.getCollisionBox().intersects(gamerCollisionBox)) {
                GameData.shooter.takeDamage(4);
            }
        }
    }

    // Manages close-range or long-range enemy attacks
    public void processEnemyAttackCollisions() {
        Rectangle2D gamerCollisionBox = GameData.shooter.getCollisionBox();
        for (GameFigure enemy : Main.gameData.enemyFigures) {
            if (enemy.getCollisionBox().intersects(gamerCollisionBox)) {
                GameData.shooter.takeDamage(2);
                enemy.goNextState();
            }
        }
    }

    // Manages item collisions
    public void processItemCollisions() {
        Rectangle2D gamerCollisionBox = GameData.shooter.getCollisionBox();
        for (GameFigure item : Main.gameData.itemFigures) {
            if (item.getCollisionBox().intersects(gamerCollisionBox)) {
                item.goNextState();
                Animator.counter++;
            }
        }
    }

    // Manages ally short-ranged and long-ranged weapon attack
    public void processAllyWeaponCollisions() {
        for (GameFigure weaponAttackAlly : Main.gameData.friendFigures) { // friend figures are the weapons atm (missiles)
            Rectangle2D weaponCollisionBox = weaponAttackAlly.getCollisionBox();

            // Will be removed once all health enemies are migrated to enemyFiguresWithHealth
            for (GameFigure enemy : Main.gameData.enemyFigures) {
                if (weaponCollisionBox.intersects(enemy.getCollisionBox())) {
                    enemy.goNextState();
                    weaponAttackAlly.goNextState();
                }
            }

            for (GameFigureWithHealth enemyWithHealth : Main.gameData.enemyFiguresWithHealth) {
                if (weaponCollisionBox.intersects(enemyWithHealth.getCollisionBox())) {
                    enemyWithHealth.takeDamage(1);
                    if (!enemyWithHealth.stillHasHealth()) {
                        enemyWithHealth.goNextState();
                    }
                    weaponAttackAlly.goNextState();
                }
            }

        }
    }

}
