package view;

import controller.ButtonListener;
import controller.KeyController;
import controller.Main;
import controller.MouseController;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JFrame {

    public static JButton addButton;
    public static JButton quitButton;
    public static JButton ufoButton;
    public static JPanel cards;
    public static MouseController mouseController;
    public static Container container;

    public MainWindow() {

        container = getContentPane();

        // Cards is a layout which allows for easier
        // access to different panels. This helps eliminate
        // focus issues.
        cards = new JPanel(new CardLayout());
        cards.add(Main.gamePanel, "GamePanel");
        cards.add(Main.splashPanel, "SplashPanel");

        container.add(cards, "Center");

        mouseController = new MouseController();
        Main.gamePanel.addMouseListener(mouseController);
        Main.gamePanel.addMouseMotionListener(mouseController);

        Main.gamePanel.setFocusable(true);
    }

    // This function gets the SplashPanel
    // from the card layout and sets focus
    // to that panel.
    public void splashLayout()
    {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, "SplashPanel");
    }

    // This function gets the GamePanel
    // from the card layout and sets focus
    // to that panel.
    public void gameLayout()
    {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, "GamePanel");

        Main.gamePanel.revalidate();
    }
}
