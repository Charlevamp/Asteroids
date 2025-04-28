
/**
 * Write a description of class StarPoly2D here.
 *
 * @author Antoine Brown
 * @version 4-23-2025 
 */
public class StarPoly2D extends Polygon2D
{
    // instance variables - replace the example below with your own
    private static int[] xCoords = {0, 3, 10, 2, 0, -2, -10, -2};
    private static int[] yCoords = {10, -3, 0, 2, 10, 2, 0, -2};
    

    /**
     * Constructor for objects of class StarPoly2D
     */
    public StarPoly2D(int fillColorIndex, int xPos, int yPos)
    {
        super(fillColorIndex, xPos, yPos, xCoords, yCoords);
        
        
        
        
    }

    
}
