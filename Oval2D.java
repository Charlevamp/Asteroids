import java.awt.Graphics;

/**
 * Subclass of Shape2D for ovals
 *
 * @author Antoine Brown
 * @version 4-17-2025
 */
public class Oval2D extends Shape2D
{
    // instance variables - replace the example below with your own
    private int diameter1;
    private int diameter2;


    /**
     * Constructor for objects of class Oval2D
     */
    public Oval2D(int fillColorIndex, int xPos, int yPos, int diameter1, int diameter2)
    {
        super(fillColorIndex, xPos, yPos);
        this.diameter1 = diameter1;
        this.diameter2 = diameter2;
    }

    /**
     *  Draw method
     *
     * @param  g graphics object
     * @return   void
     */
    @Override
    public void Draw(Graphics g){
        g.setColor(fillColor);
        g.fillOval(xPos, yPos, diameter1, diameter2);
    
    
    }
}
