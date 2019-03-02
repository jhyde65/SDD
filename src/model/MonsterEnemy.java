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
    private final int HEIGHT = 100;
    private final int WIDTH = 75;
    private final String PATH = "..//resources//images//monster//";
    public int health;
    private Strategy strategy;
    public float dx;
    public float dy;
    public SpriteAnimation animation, moveDown, moveLeft, moveRight, moveUp, idle;
    public MonsterFireball fireball;
    
    public MonsterEnemy(float x, float y)
    {
        super(x, y);
        super.state = new StrongFigureState();
        health = 2;
        strategy = new MonsterWalkingStrategy();
        BufferedImage[] movingDown = {Sprite.getSprite(PATH, 0), Sprite.getSprite(PATH, 1), Sprite.getSprite(PATH, 2)};
        BufferedImage[] movingLeft = {Sprite.getSprite(PATH, 3), Sprite.getSprite(PATH, 4), Sprite.getSprite(PATH, 5)};
        BufferedImage[] movingRight = {Sprite.getSprite(PATH, 6), Sprite.getSprite(PATH, 7), Sprite.getSprite(PATH, 8)};
        BufferedImage[] movingUp = {Sprite.getSprite(PATH, 9), Sprite.getSprite(PATH, 10), Sprite.getSprite(PATH, 11)};
        BufferedImage[] idling = {Sprite.getSprite(PATH, 0), Sprite.getSprite(PATH, 1)};
        this.moveDown = new SpriteAnimation(movingDown, 5);
        this.moveLeft = new SpriteAnimation(movingLeft, 5);
        this.moveRight = new SpriteAnimation(movingRight, 5);
        this.moveUp = new SpriteAnimation(movingUp, 5);
        this.idle = new SpriteAnimation(idling, 5);
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
        strategy.move(super.x, super.y, this);
    }
    
    public void setAnimation(SpriteAnimation animation, SpriteAnimation newAnimation)
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
        //return new Rectangle2D.Float(x, y, WIDTH, HEIGHT);
        return new Rectangle2D.Float(0, 0, 0, 0);
    }

    @Override
    public void setState(GameFigureState state)
    {
        this.state = state;
    }

    @Override
    public void goNextState()
    {
        state.goNext(this);
    }

    @Override
    public void setPosition(float x, float y)
    {
        this.x = x;
        this.y = y;
    }
    
}
