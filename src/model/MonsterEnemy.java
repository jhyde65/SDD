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
    public final int HEIGHT = 90;
    public final int WIDTH = 60;
    private final String PATH = "..//resources//images//monster//";
    public int health;
    public float dx;
    public float dy;
    public MonsterAnimation animation, moveDown, moveLeft, moveRight, moveUp, idle;
    
    public MonsterEnemy(float x, float y)
    {
        super(x, y);
        super.state = GameFigureState.STATE_ACTIVE;
        this.health = 2;
        movement = new MonsterWalkingStrategy();
        BufferedImage[] movingDown = {Sprite.getSprite(PATH, "0"), Sprite.getSprite(PATH, "1"), Sprite.getSprite(PATH, "2")};
        BufferedImage[] movingLeft = {Sprite.getSprite(PATH, "3"), Sprite.getSprite(PATH, "4"), Sprite.getSprite(PATH, "5")};
        BufferedImage[] movingRight = {Sprite.getSprite(PATH, "6"), Sprite.getSprite(PATH, "7"), Sprite.getSprite(PATH, "8")};
        BufferedImage[] movingUp = {Sprite.getSprite(PATH, "9"), Sprite.getSprite(PATH, "10"), Sprite.getSprite(PATH, "11")};
        BufferedImage[] idling = {Sprite.getSprite(PATH, "0"), Sprite.getSprite(PATH, "1")};
        this.moveDown = new MonsterAnimation(movingDown, 5);
        this.moveLeft = new MonsterAnimation(movingLeft, 5);
        this.moveRight = new MonsterAnimation(movingRight, 5);
        this.moveUp = new MonsterAnimation(movingUp, 5);
        this.idle = new MonsterAnimation(idling, 5);
        this.animation = idle;
        animation.start();
    }

    @Override
    public void render(Graphics2D g)
    {
        g.drawImage(animation.getSprite(), (int)super.x, (int)super.y, WIDTH, HEIGHT, null);
    }

    @Override
    public void update()
    {
        movement.move(super.x, super.y, this);
    }
    
    public void setAnimation(MonsterAnimation animation, MonsterAnimation newAnimation)
    {
        if (animation.equals(newAnimation)) return;
        this.animation.stop();
        this.animation.reset();
        this.animation = newAnimation;
        this.animation.restart();
    }

    @Override
    public Rectangle2D getCollisionBox()
    {
        return new Rectangle2D.Float(x, y, WIDTH, HEIGHT);
    }
    
}
