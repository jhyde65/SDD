package view;

import controller.GameState;
import controller.Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;

import model.GameFigure;
import model.Shooter;
import controller.actions.gameactions.DownArrowAction;
import controller.actions.gameactions.LeftArrowAction;
import controller.actions.gameactions.RightArrowAction;
import controller.actions.gameactions.UpArrowAction;
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

        actionMap.put("LEFT", new LeftArrowAction());
        actionMap.put("RIGHT", new RightArrowAction());
        actionMap.put("UP", new UpArrowAction());
        actionMap.put("DOWN", new DownArrowAction());
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
            
            // TESTING ONLY
            //renderBordersDebug();
        }
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
