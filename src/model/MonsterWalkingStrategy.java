package model;

import controller.Main;

/**
 *
 * @author david
 */
public class MonsterWalkingStrategy implements Strategy
{
    private static final int UNIT_TRAVEL_DISTANCE = 2;

    @Override
    public void move(float x, float y, GameFigure context)
    {
        MonsterEnemy monster = (MonsterEnemy) context;
        double angle = Math.atan2(GameData.shooter.y - monster.y, GameData.shooter.x - monster.x);
        monster.dx = (float) (UNIT_TRAVEL_DISTANCE * Math.cos(angle));
        monster.dy = (float) (UNIT_TRAVEL_DISTANCE * Math.sin(angle));
        monster.x += monster.dx;
        monster.y += monster.dy;
        updateAnimationOnPositionChange(monster, monster.x, monster.y);
        monster.fireball = new MonsterFireball(monster.x - 5, monster.y - 20);
        monster.fireball.setAnimation(monster.fireball.animation, monster.fireball.downShot);
        Main.gameData.enemyFigures.add(monster.fireball);
    }
    
    private void updateAnimationOnPositionChange(MonsterEnemy monster, float x, float y)
    {
        if ((GameData.shooter.x > x && GameData.shooter.y > y && Math.abs(GameData.shooter.y - y) > Math.abs(GameData.shooter.x - x))
           || (GameData.shooter.x < x && GameData.shooter.y > y && Math.abs(GameData.shooter.y - y) > Math.abs(GameData.shooter.x - x)))
        {
            monster.setAnimation(monster.animation, monster.moveDown);
            monster.animation.update();
        }
        if ((GameData.shooter.x > x && GameData.shooter.y > y && Math.abs(GameData.shooter.y - y) < Math.abs(GameData.shooter.x - x))
           || (GameData.shooter.x > x && GameData.shooter.y < y && Math.abs(GameData.shooter.y - y) < Math.abs(GameData.shooter.x - x)))
        {
            monster.setAnimation(monster.animation, monster.moveRight);
            monster.animation.update();
        }
        if ((GameData.shooter.x < x && GameData.shooter.y < y && Math.abs(GameData.shooter.y - y) > Math.abs(GameData.shooter.x - x))
           || (GameData.shooter.x > x && GameData.shooter.y < y && Math.abs(GameData.shooter.y - y) > Math.abs(GameData.shooter.x - x)))
        {
            monster.setAnimation(monster.animation, monster.moveUp);
            monster.animation.update();
        }
        if ((GameData.shooter.x < x && GameData.shooter.y > y && Math.abs(GameData.shooter.y - y) < Math.abs(GameData.shooter.x - x))
           || (GameData.shooter.x < x && GameData.shooter.y < y && Math.abs(GameData.shooter.y - y) < Math.abs(GameData.shooter.x - x)))
        {
            monster.setAnimation(monster.animation, monster.moveLeft);
            monster.animation.update();
        }
    }
    
}
