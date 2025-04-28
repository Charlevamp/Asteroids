import java.awt.Graphics;

/**
 * ubclass of Shape2D for rectangles
 *
 * @author Antoine Brown
 * @version 4-17-2025
 */
public class Rectangle2D extends Shape2D
{
    // instance variables - replace the example below with your own
    private int width;
    private int height;

    /**
     * Constructor for objects of class Rectangle2D
     */
    public Rectangle2D(int fillColorIndex, int xPos, int yPos, int width, int height)
    {
        super(fillColorIndex, xPos, yPos);
        this.width = width;
        this.height = height;
    }

    /**
     * Draw method
     *
     * @param  g graphics object
     * @return   void
     */
    @Override
    public void Draw(Graphics g){
        g.setColor(fillColor);
        g.fillRect(xPos, yPos, width, height);
    
    
    }
}
