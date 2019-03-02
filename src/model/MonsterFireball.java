package model;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author david
 */
public class MonsterFireball extends GameFigure
{
    
    private final int HEIGHT = 15;
    private final int WIDTH = 20;
    private final String PATH = "..//resources//images//fireball//";
    public float dx;
    public float dy;
    public SpriteAnimation animation, downShot, leftShot, rightShot, upShot;
    
    public MonsterFireball(float x, float y)
    {
        super(x, y);
        BufferedImage[] shootingDown = {Sprite.getSprite(PATH, 3), Sprite.getSprite(PATH, 4), Sprite.getSprite(PATH, 5)};
        BufferedImage[] shootingLeft = {Sprite.getSprite(PATH, 6), Sprite.getSprite(PATH, 7), Sprite.getSprite(PATH, 8)};
        BufferedImage[] shootingRight = {Sprite.getSprite(PATH, 0), Sprite.getSprite(PATH, 1), Sprite.getSprite(PATH, 2)};
        BufferedImage[] shootingUp = {Sprite.getSprite(PATH, 9), Sprite.getSprite(PATH, 10), Sprite.getSprite(PATH, 11)};
        this.downShot = new SpriteAnimation(shootingDown, 3);
        this.leftShot = new SpriteAnimation(shootingLeft, 3);
        this.rightShot = new SpriteAnimation(shootingRight, 3);
        this.upShot = new SpriteAnimation(shootingUp, 3);
        
    }

    @Override
    public void render(Graphics2D g)
    {
        g.drawImage(animation.getSprite(), (int)super.x, (int)super.y, WIDTH, HEIGHT, null);
    }

    @Override
    public void update()
    {
        this.animation.update();
    }
    
    public void setAnimation(SpriteAnimation animation, SpriteAnimation newAnimation)
    {
        //if (animation.equals(newAnimation)) return;
        //this.animation.stop();
        //this.animation.reset();
        this.animation = newAnimation;
        this.animation.start();
        //this.animation.restart();
    }

    @Override
    public void setState(GameFigureState state) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void goNextState() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPosition(float x, float y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rectangle2D getCollisionBox() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
