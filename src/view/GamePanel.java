package view;

import Inventory.Inventory;
import controller.Main;

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;

import controller.actions.gameactions.*;
import model.GameFigure;
import model.Shooter;
import controller.actions.gameactions.DownArrowAction;
import controller.actions.gameactions.LeftArrowAction;
import controller.actions.gameactions.RightArrowAction;
import controller.actions.gameactions.UpArrowAction;
import controller.actions.gameactions.BButtonAction;
import controller.actions.gameactions.CKeyAction;
import controller.actions.gameactions.inventoryAction;
import controller.actions.gameactions.SpaceKeyAction;
import controller.actions.gameactions.ZKeyAction;
import controller.actions.gameactions.KButtonAction;
import controller.actions.gameactions.MButtonAction;
import controller.actions.gameactions.XKeyAction;
import model.Border;

public class GamePanel extends JPanel {

    // size of the canvas - determined at runtime once rendered
    public static int width;
    public static int height;

    // off screen rendering
    private Graphics2D g2;
    private Image dbImage = null; // double buffer image

    public GamePanel()
    {
        // Key bindings for Game Panel.
        // All Actions contained in controller.actions.gameactions
        InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "LEFT");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "RIGHT");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "UP");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), "DOWN");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_B, 0, false), "Boss");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_K, 0, false), "SpawnSpikeyEnemy");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_I, 0, false), "Inventory");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false), "PAUSE");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "ENTER");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_M, 0, false), "MonsterEnemy");


        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "SPACE");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, 0, false), "Z");

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_X, 0, false), "X");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, 0, false), "C");
        
        actionMap.put("LEFT", new LeftArrowAction());
        actionMap.put("RIGHT", new RightArrowAction());
        actionMap.put("UP", new UpArrowAction());
        actionMap.put("DOWN", new DownArrowAction());
        actionMap.put("Inventory", new inventoryAction());
        //BButton for dev of GolemBoss.  Hotkey shortcut
        actionMap.put("Boss", new BButtonAction());
        actionMap.put("SpawnSpikeyEnemy", new KButtonAction());
        actionMap.put("SPACE", new SpaceKeyAction());
        actionMap.put("Z", new ZKeyAction());
        actionMap.put("PAUSE", new PauseAction());
        actionMap.put("ENTER", new PauseEnterAction());
        actionMap.put("MonsterEnemy", new MButtonAction());
        actionMap.put("X", new XKeyAction());
        actionMap.put("C", new CKeyAction());
    }


    public void gameRender() {
        width = getSize().width;
        height = getSize().height;
        if (dbImage == null) {
            // Creates an off-screen drawable image to be used for double buffering
            dbImage = createImage(width, height);
            if (dbImage == null) {
                System.out.println("Critical Error: dbImage is null");
                System.exit(1);
            } else {
                g2 = (Graphics2D) dbImage.getGraphics();
            }
        }

        g2.clearRect(0, 0, width, height);
        g2.setBackground(Color.BLACK);

        if (Main.animator.running) {

            for (GameFigure f : Main.gameData.enemyFigures) {
                f.render(g2);
            }

            for (GameFigure f : Main.gameData.friendFigures) {
                f.render(g2);
            }

            for (Inventory f : Main.gameData.inventory){
                f.render(g2);
            }

        }
        
        // TESTING ONLY
        //renderBordersDebug();
    }

    public void renderPauseScreen()
    {
        Main.gameData.pauseScreen.render(g2);
    }

    // use active rendering to put the buffered image on-screen
    public void printScreen() {
        Graphics g;
        try {
            g = this.getGraphics();
            if ((g != null) && (dbImage != null)) {
                g.drawImage(dbImage, 0, 0, this);
            }
            Toolkit.getDefaultToolkit().sync();  // sync the display on some systems
            if (g != null) {
                g.dispose();
            }
        } catch (Exception e) {
            System.out.println("Graphics error: " + e);
        }
    }
    // TESTING PURPOSES ONLY
    public void renderBordersDebug(){
        for(Border b: Main.gameData.borders){
            b.render(g2);
        }
    }
}