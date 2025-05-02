
/**
 * Write a description of class Sprite2D here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
public class Sprite2D extends Shape2D
{
    // instance variables - replace the example below with your own
    private BufferedImage[] imageFrames;
    private int frame;

    /**
    * Constructor for objects of class Sprite2D
    */    
     // Parametric constructor
     public Sprite2D(int xPos, int yPos, BufferedImage[] imageFrames)
    {
        super(0, xPos, yPos);
        this.imageFrames = new BufferedImage[imageFrames.length];
        for (int i = 0; i < imageFrames.length; i++) {
        this.imageFrames[i] = imageFrames[i];
        }
        frame = 0;
    }


    /**
     * Draw method for Sprite2D
     *
     * @param  g Graphics object
     * @return void
     */
    // Parametric constructor
     public void Draw(Graphics g) {
        // Draw the next Sprite frame
        g.drawImage(imageFrames[frame], getXPos(), getYPos(), null);
        frame++;
        if (frame == imageFrames.length)
        {
        frame = 0;
        }
    }
}
