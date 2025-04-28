import java.awt.Graphics;

/**
 * Subclass of Shape2D for circles
 *
 * @author Antoine Brown
 * @version 4-17-2025
 */
public class Circle2D extends Shape2D
{
    // instance variables - replace the example below with your own
    private int     diameter;

    /**
     * Constructor for objects of class Circle2D
     */
    public Circle2D(int fillColorIndex, int xPos, int yPos, int diameter)
    {
        super(fillColorIndex, xPos, yPos);
        this.diameter = diameter;
    
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
        g.fillOval(xPos, yPos, diameter, diameter);
    
    
    }
    
    
    
}
