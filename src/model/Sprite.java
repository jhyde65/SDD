package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author david
 * This class is for image retrieval for use in animations
 */
public class Sprite
{
    private static BufferedImage image;
    //private static final String DIR = "monster//";
    
    public static BufferedImage getSprite(String folder, int fileNumber)
    {
        if (image == null)
        {
            image = loadSprite(folder, fileNumber);
        }
        return image;
    }
    
    private static BufferedImage loadSprite(String folder, int num)
    {
        BufferedImage sprite = null;
        
        try {
            sprite = ImageIO.read(new File("..//resources//images//" + folder + num + ".png"));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: could not open image #" + num);
            System.exit(-1);
        }
        
        return sprite;
        
    }
    
}
