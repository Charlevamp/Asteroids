import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * Write a description of class Ship2D here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.awt.image.BufferedImage;
public class Ship2D extends Shape2D
{
    // instance variables - replace the example below with your own
    private BufferedImage[] imageFrames;
    private int frame;
    

    /**
     * Constructor for objects of class Ship2D
     */
    public Ship2D(int xPos, int yPos, BufferedImage[] imageFrames)
    {
        super(0, xPos, yPos);
        SetScaleX(0.01);
        SetScaleY(0.01);
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
        
        
        Graphics2D g2d = (Graphics2D) g.create();
        
        //centerX and centerY the x and y center of the sprite
        int centerX = getXPos() + 13;
        int centerY = getYPos() + 13;
        
        //Moves origin to center of ship
        g2d.translate(centerX, centerY);
        
        //Rotate
        g2d.rotate(Math.toRadians(GetZRotate()));
        
        //Draws the image centered
        g2d.drawImage(imageFrames[frame], -13, -13, 26, 26, null);
        
        frame++;
        if(frame == imageFrames.length){
            frame = 0;
        
        }
        
        g2d.dispose();
        
        
        
        
        
    }  
    
    
    
    
    /**
     * Getter method for Sprite2D
     *
     * @param  g Graphics object
     * @return void
     */
    
    
    /**
     * Draw method for Sprite2D
     *
     * @param  g Graphics object
     * @return void
     */
    
    
}


