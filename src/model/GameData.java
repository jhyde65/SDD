package model;

import Inventory.Inventory;
import Inventory.ItemPotion;
import Inventory.ItemSlot;
import Inventory.Potion;
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
    public final List<GameFigureWithHealth> enemyFiguresWithHealth;
    public final List<GameFigure> friendFigures;
    public final List<GameFigure> itemFigures;
    public final List<GameFigure> weaponAttackFigures;
    public final List<Border> borders;
    public final List<Inventory> inventory;
    public final HealthBar health;
    public Pause pauseScreen;
    public static Shooter shooter;

    public GameData() {
        enemyFigures = new CopyOnWriteArrayList<>();
        friendFigures = new CopyOnWriteArrayList<>();
        borders = new CopyOnWriteArrayList<>();
        health = new HealthBar();
        pauseScreen = new Pause();
        inventory = new CopyOnWriteArrayList<>();
        itemFigures = new CopyOnWriteArrayList<>();
        weaponAttackFigures = new CopyOnWriteArrayList<>();
        enemyFiguresWithHealth = new CopyOnWriteArrayList<>();
        // GamePanel.width, height are known when rendered. 
        // Thus, at this moment,
        // we cannot use GamePanel.width and height.
        shooter = new Shooter(Main.WIN_WIDTH / 2, Main.WIN_HEIGHT - 150);

        //friendFigures.add(shooter);

        borders.add(new Border(-51, 0, 50, Main.WIN_HEIGHT)); // left border
        borders.add(new Border(0, -51, Main.WIN_WIDTH, 50)); // top border
        borders.add(new Border(Main.WIN_WIDTH-5, 0, 50, Main.WIN_HEIGHT)); // right border
        borders.add(new Border(0, Main.WIN_HEIGHT-28, Main.WIN_WIDTH, 50)); //bottom border
        generateBorders();
        

        enemyFiguresWithHealth.add(new SpikeyEnemy(500,500));
        itemFigures.add(new ItemPotion(600,600));
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
        enemyFiguresWithHealth.add(new SpikeyEnemy(x,y));
    }
    
    public void addMonsterEnemy()
    {
        Random rand = new Random();
        int x = rand.nextInt(500) + 100;
        int y = rand.nextInt(500) + 100;
        enemyFigures.add(new MonsterEnemy(x, y));
    }
    
    public void addGoblinEnemy()
    {
        Random rand = new Random();
        int x = rand.nextInt(500) + 100;
        int y = rand.nextInt(500) + 100;
        enemyFigures.add(new GoblinEnemy(x, y));
    }
    
    public void addPotion(float x, float y){
        itemFigures.add(new ItemPotion(x,y));
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
        updateFriends();
        updateEnemies();
        updateEnemiesWithHealth();
        updateItems();
        shooter.update();
    }
    
    private void updateItems(){
        ArrayList<GameFigure> removeItems = new ArrayList<>();
        GameFigure f;
        for (int i = 0; i < itemFigures.size(); i++) {
            f = itemFigures.get(i);
            if (f.state instanceof DoneFigureState) {
                removeItems.add(f);
            }
        }
        
        itemFigures.removeAll(removeItems);

        for (GameFigure g : itemFigures) {
            g.update();
        }
    }
    
    private void updateFriends(){
        GameFigure f;
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
    
    private void updateEnemiesWithHealth(){
        ArrayList<GameFigure> removeEnemiesWithHealth = new ArrayList<>();
        GameFigure f;
        for (int i = 0; i < enemyFiguresWithHealth.size(); i++) {
            f = enemyFiguresWithHealth.get(i);
            if (f.state instanceof DoneFigureState) {
                if(f instanceof ItemPotion){
                }
                else{
                    addPotion(f.getX(),f.getY());
                }
                removeEnemiesWithHealth.add(f);
            }
        }
        
        enemyFiguresWithHealth.removeAll(removeEnemiesWithHealth);

        for (GameFigure g : enemyFiguresWithHealth) {
            g.update();
        }
    }
    
    private void updateEnemies(){
        ArrayList<GameFigure> removeEnemies = new ArrayList<>();
        GameFigure f;
        for (int i = 0; i < enemyFigures.size(); i++) {
            f = enemyFigures.get(i);
            if (f.state instanceof DoneFigureState) {
                if(f instanceof ItemPotion){
                }
                else{
                    //addPotion(f.getX(),f.getY());
                }
                removeEnemies.add(f);
            }
        }
        
        enemyFigures.removeAll(removeEnemies);

        for (GameFigure g : enemyFigures) {
            g.update();
        }
    }
    
    private void generateBorders(){
        borders.add(new Border(400, 0, 100, 50));
        borders.add(new Border(700, Main.WIN_HEIGHT-28 - 50, 35, 50));
    }
}