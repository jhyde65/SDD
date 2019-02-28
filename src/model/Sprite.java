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
    //public static BufferedImage image;
    
    public static BufferedImage getSprite(String folder, String fileNumber)
    {
//        if (image == null)
//        {
//            image = loadSprite(folder, fileNumber);
//        }
//        return image;
        BufferedImage sprite = null;
        sprite = loadSprite(folder, fileNumber);
        return sprite;
    }
    
    private static BufferedImage loadSprite(String folder, String num)
    {
        BufferedImage sprite = null;
        String fileName = folder + num + ".png";
        
        try {
            //sprite = ImageIO.read(new File("../" + folder + num + ".png"));
            //sprite = ImageIO.read(new File(fileName));
            sprite = ImageIO.read(Sprite.class.getResource(fileName));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: could not open image #" + num);
            System.exit(-1);
        }
        
        return sprite;
        
    }
    
}
