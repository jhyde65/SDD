package model;

import controller.Main;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import view.MainWindow;

public class Shooter extends GameFigureWithHealth {
    
    Line2D.Float barrel;
    Rectangle2D.Float base;
    private final int BARREL_LEN = 20;
    private final int BASE_SIZE = 20;
    private int currentHealth;

    public Shooter(int x, int y) {
        super(x, y);
        super.state = new StrongFigureState();
        //super.state = GameFigureState.SHOOTER_STATE_HEALTH_LEVEL_5;
        barrel = new Line2D.Float(super.x, super.y, super.x, super.y-BARREL_LEN);
        base = new Rectangle2D.Float(super.x - BASE_SIZE /2 , super.y - BASE_SIZE / 2,
                BASE_SIZE, BASE_SIZE);
        movement = new CannotPassBorderStrategy();
        currentHealth = 100;
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.YELLOW);
        int tx = MainWindow.mouseController.x;
        int ty = MainWindow.mouseController.y;
        double rad = Math.atan2(ty - super.y, tx - super.x);
        int bendy = (int)(BARREL_LEN * Math.sin(rad));
        int bendx = (int)(BARREL_LEN * Math.cos(rad));
        barrel.x1 = super.x;
        barrel.y1 = super.y;
        barrel.x2 = super.x + bendx;
        barrel.y2 = super.y + bendy;
        g.setStroke(new BasicStroke(7)); // thickness of the line
        g.draw(barrel);
        g.draw(base);
        
        // TESTING ONLY -- shows the collision box
        //g.setColor(Color.RED);
        //g.setStroke(new BasicStroke(2)); // thickness of the line
        //g.draw(getCollisionBox());
    }

    @Override
    public void update() {
        // no periodic update is required (not animated)
        // if health level is implemented, update level
        // update is done via 'translate' when a key is pressed
    }

    public void translate(int dx, int dy) {
        //movement.move(dx, dy, this);
        barrel.x1 += dx;
        barrel.x2 += dx;
        barrel.y1 += dy;
        barrel.y2 += dy;
        super.x = barrel.x1;
        super.y = barrel.y1;
        base.x += dx;
        base.y += dy;
       
        
        //check if translation is going to take 
        //actor out of the scene       
        if(
           super.x < Main.gamePanel.getLocation().x ||
           (super.x + BASE_SIZE) > (Main.gamePanel.getLocation().x + Main.gamePanel.getSize().width)||
           super.y < Main.gamePanel.getLocation().y||
           (super.y + BASE_SIZE) > (Main.gamePanel.getLocation().y + Main.gamePanel.getSize().height)

          ){
            //revert the translation
            super.x -= dx;
            super.y -= dy;
            base.x -= dx;
            base.y -= dy;
        }
    }
    
    // Missile shoot location: adjut x and y to the image
    public float getXofMissileShoot() {
        return barrel.x2;
    }
    
    public float getYofMissileShoot() {
        return barrel.y2;
    }
    
        @Override
    public Rectangle2D.Float getCollisionBox()
    {
        return base;
    }
    
    @Override
    public void goNextState()
    {
        // Will be modified later
        //state.goNext(this);
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
        this.state = state;
    }
    
    @Override
    public void setPosition(float x, float y) { }
    

}
