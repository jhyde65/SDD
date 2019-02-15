package model;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author david
 */
public class MonsterEnemy extends GameFigure
{
    private final int height = 50;
    private final int width = 30;
    private final String path = "monster";
    private static final int UNIT_TRAVEL_DISTANCE = 10;
    private BufferedImage[] movingDown = {Sprite.getSprite(path, 0), Sprite.getSprite(path, 1), Sprite.getSprite(path, 2)};
    private BufferedImage[] movingLeft = {Sprite.getSprite(path, 3), Sprite.getSprite(path, 4), Sprite.getSprite(path, 5)};
    private BufferedImage[] movingRight = {Sprite.getSprite(path, 6), Sprite.getSprite(path, 7), Sprite.getSprite(path, 8)};
    private BufferedImage[] movingUp = {Sprite.getSprite(path, 9), Sprite.getSprite(path, 10), Sprite.getSprite(path, 11)};
    private BufferedImage[] idling = {Sprite.getSprite(path, 0), Sprite.getSprite(path, 1)};
    private MonsterAnimation moveDown, moveLeft, moveRight, moveUp, idle;
    
    public MonsterEnemy(float x, float y)
    {
        super(x, y);
        
    }

    @Override
    public void render(Graphics2D g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rectangle2D getCollisionBox() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
