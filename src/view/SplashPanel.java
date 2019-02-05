package view;

import controller.GameState;
import controller.Main;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import model.GameFigure;
import view.actions.splashactions.EnterAction;

/**
 * Class SplashPanel contains the logic for the splash screen panel.
 * Animation is run in SplashAnimator.java.
 */

public class SplashPanel extends JPanel {

    private Image background;
    private Image mover;
    private Image mover2;
    private Image mover3;
    private int x = -300;
    private int y = -300;
    private static int width;
    private static int height;
    private Image dbImage = null;
    private Graphics2D g2;

    public SplashPanel() {

        // Create a key binding for this JPanel.
        InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getActionMap();

        // Enter key binding is created and action is mapped to
        // the EnterAction defined in view.actions.splashactions.
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "ENTER");
        actionMap.put("ENTER", new EnterAction());
    }

    // Animating logic for the splash screen.
    public void gameRender()
    {
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
        g2.drawImage(background, 0,0, Main.WIN_WIDTH, Main.WIN_HEIGHT, null);
        g2.drawImage(mover, 220, y, 300, 200, null);

        if (y < 40) y += 5;
        else
        {
            g2.drawImage(mover2, x, 190, 300, 200, null);
            if (x < 220)
                x += 5;
            else
                g2.drawImage(mover3, 260, 335, 200, 100, null);
        }

    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        background = null;

        try {
            background = ImageIO.read(getClass().getResource("resources/splashscreen/splashbg.png"));
            mover = ImageIO.read(getClass().getResource("resources/splashscreen/dungeontitle.png"));
            mover2 = ImageIO.read(getClass().getResource("resources/splashscreen/plungintitle.png"));
            mover3 = ImageIO.read(getClass().getResource("resources/splashscreen/starts.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open shooter.png");
            System.exit(-1);
        }
    }

    public void printScreen() {
        Graphics g;
        try {
            g = this.getGraphics();
            if ((g != null) && (dbImage != null)) {
                g.drawImage(dbImage, 0, 0, null);
            }
            Toolkit.getDefaultToolkit().sync();  // sync the display on some systems
            if (g != null) {
                g.dispose();
            }
        } catch (Exception e) {
            System.out.println("Graphics error: " + e);
        }
    }
}
