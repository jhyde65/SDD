package model;

import controller.Main;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Kufre
 */
public class Shooter extends GameFigureWithHealth
{
    private final int HEIGHT = 100;
    private final int WIDTH = 75;
    private final String PATH = "..//resources//images//hero//";
    public int health;
    public float dx;
    public float dy;
    public SpriteAnimation animation, moveDown, moveLeft, moveRight, moveUp, idle;
    
    public Shooter(float x, float y)
    {
        super(x, y);
        super.state = new StrongFigureState();
        movement = new CannotPassBorderStrategy();
        BufferedImage[] movingUp = {Sprite.getSprite(PATH, 0), Sprite.getSprite(PATH, 1), Sprite.getSprite(PATH, 2), 
                                    Sprite.getSprite(PATH, 3), Sprite.getSprite(PATH, 4) };
        BufferedImage[] movingDown = {Sprite.getSprite(PATH, 5), Sprite.getSprite(PATH, 6), Sprite.getSprite(PATH, 7),
                                      Sprite.getSprite(PATH, 8), Sprite.getSprite(PATH, 9)};
        BufferedImage[] movingRight = {Sprite.getSprite(PATH, 10), Sprite.getSprite(PATH, 11), Sprite.getSprite(PATH, 12),
                                       Sprite.getSprite(PATH, 13), Sprite.getSprite(PATH, 14),Sprite.getSprite(PATH, 15), 
                                       Sprite.getSprite(PATH, 16), Sprite.getSprite(PATH, 17)};
        BufferedImage[] movingLeft = {Sprite.getSprite(PATH, 18), Sprite.getSprite(PATH, 19), Sprite.getSprite(PATH, 20),
                                       Sprite.getSprite(PATH, 21), Sprite.getSprite(PATH, 22),Sprite.getSprite(PATH, 23), 
                                       Sprite.getSprite(PATH, 24), Sprite.getSprite(PATH, 25)};
        BufferedImage[] idling = {Sprite.getSprite(PATH, 0), Sprite.getSprite(PATH, 1)};
            
        
        this.moveDown = new SpriteAnimation(movingDown, 5);
        this.moveLeft = new SpriteAnimation(movingLeft, 5);
        this.moveRight = new SpriteAnimation(movingRight, 5);
        this.moveUp = new SpriteAnimation(movingUp, 5);
        this.idle = new SpriteAnimation(idling, 5);
        this.animation = idle;
        animation.start();
        currentHealth = 100;
    }
    
    public void translate(int x, int y){
        movement.move(x, y, this);
    }
    
    public void shoot(int targetX, int targetY){
       Gunshot m = new Gunshot( this.x + WIDTH/2, this.y + HEIGHT/2, targetX, targetY, Color.RED);
       Main.gameData.friendFigures.add(m);
    }

    @Override
    public void render(Graphics2D g)
    {
        g.drawImage(animation.getSprite(), (int)super.x, (int)super.y, WIDTH, HEIGHT, null);
    }

    @Override
    public void update()
    {
        
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
    public Rectangle2D.Float getCollisionBox()
    {
        return new Rectangle2D.Float(x, y, WIDTH, HEIGHT);
        //return new Rectangle2D.Float(0, 0, 1, 1);
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
    
    @Override
    public void takeDamage(int damage)
    {
        System.out.println("+++++++++++++++++++++ booiiiiiiii");
        currentHealth -= damage;
        Main.gameData.health.setHealth(currentHealth);
    }

    public void heal(int health)
    {
        currentHealth = (currentHealth + health) % maxHealth;
        Main.gameData.health.setHealth(currentHealth);
    }

    @Override
    public void setState(GameFigureState state)
    {
        super.takeDamage(damage);
        Main.gameData.health.setHealth(currentHealth);
    }
    
    @Override
    public void heal(int health)
    {
        super.heal(health);
        Main.gameData.health.setHealth(currentHealth);
    }
    
}
