package model;

import Inventory.Inventory;
import controller.Main;
import view.GamePanel;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameData {

    private final int RADIUS = 6;
    public final List<GameFigure> enemyFigures;
    public final List<GameFigure> friendFigures;
    public final List<Border> borders;
    public final List<Inventory> inventory;
    public static Shooter shooter;

    public GameData() {
        enemyFigures = new CopyOnWriteArrayList<>();
        friendFigures = new CopyOnWriteArrayList<>();
        borders = new CopyOnWriteArrayList<>();
        
        inventory = new CopyOnWriteArrayList<>();
        // GamePanel.width, height are known when rendered. 
        // Thus, at this moment,
        // we cannot use GamePanel.width and height.
        shooter = new Shooter(Main.WIN_WIDTH / 2, Main.WIN_HEIGHT - 80);

        friendFigures.add(shooter);

        borders.add(new Border(0, 0, Main.WIN_WIDTH, 0));
        borders.add(new Border(0, 0, 0, Main.WIN_HEIGHT));
        borders.add(new Border(0, Main.WIN_HEIGHT, Main.WIN_WIDTH, 0));
        borders.add(new Border(Main.WIN_WIDTH, 0, Main.WIN_WIDTH, Main.WIN_HEIGHT));
        
        enemyFigures.add(new FlyingSaucer(50, 60));
        enemyFigures.add(new FlyingSaucer(400, 20));
        enemyFigures.add(new SpikeyEnemy(500,500));

    }

    public void add(int n) {
        for (int i = 0; i < n; i++) {
            float red = (float) Math.random();
            float green = (float) Math.random();
            float blue = (float) Math.random();
            // adjust if too dark since the background is black
            if (red < 0.5) {
                red += 0.2;
            }
            if (green < 0.5) {
                green += 0.2;
            }
            if (blue < 0.5) {
                blue += 0.2;
            }
            enemyFigures.add(new Bomb(
                    (int) (Math.random() * GamePanel.width),
                    (int) (Math.random() * GamePanel.height),
                    RADIUS,
                    new Color(red, green, blue)));
        }
    }
    
    public void addUFO() 
    {
        enemyFigures.add(new FlyingSaucer((int)(Math.random() * GamePanel.width), (int)(Math.random() * GamePanel.height)));
    }
    
    //
    public void addGolem()
    {
        enemyFigures.add(new GolemBoss(350, 150));
    }
    
    public void addSpikeyEnemy(){
        Random rand = new Random();
        int x = rand.nextInt(500) + 100;
        int y = rand.nextInt(500) + 100;
        enemyFigures.add(new SpikeyEnemy(x,y));
    }
    
    
    public void addInventory()
    {
        inventory.add(new Inventory(100,100));
    }

    public void removeInventory()
    {
        inventory.removeAll(inventory);
    }
    public void update() {

        ArrayList<GameFigure> removeEnemies = new ArrayList<>();
        GameFigure f;
        for (int i = 0; i < enemyFigures.size(); i++) {
            f = enemyFigures.get(i);
            if (f.state instanceof DoneFigureState) {
                removeEnemies.add(f);
            }
        }
//        for (int i = 0; i < enemyFigures.size(); i++) {
//            f = enemyFigures.get(i);
//            if (f.state == GameFigureState.STATE_DONE) {
//                removeEnemies.add(f);
//            }
//        }
        enemyFigures.removeAll(removeEnemies);

        for (GameFigure g : enemyFigures) {
            g.update();
        }

        // missiles are removed if explosion is done
        ArrayList<GameFigure> removeFriends = new ArrayList<>();
        for (int i = 0; i < friendFigures.size(); i++) {
            f = friendFigures.get(i);
            if (f.state instanceof DoneFigureState) {
            //if (f.state == GameFigureState.STATE_DONE) {
                removeFriends.add(f);
            }
        }
        friendFigures.removeAll(removeFriends);

        for (GameFigure g : friendFigures) {
            g.update();
        }
    }
}