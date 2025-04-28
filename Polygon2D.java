import java.awt.Graphics;

/**
 * Write a description of class Polygon2D here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Polygon2D extends Shape2D
{
    // instance variables - replace the example below with your own
    private int[] xCoords = null;
    private int[] yCoords = null;
    private int[] txCoords = null;
    private int[] tyCoords = null;

        /**
         * Constructor for objects of class Polygon2D
         */
        Polygon2D(int fillColorIndex, int xPos, int yPos, int[] xCoords, int[] yCoords)
     {
         super(fillColorIndex, xPos, yPos);
         this.xCoords = new int[xCoords.length]; // construct (allocate memory)
         this.yCoords = new int[yCoords.length]; // construct (allocate memory)
         this.txCoords = new int[xCoords.length]; // construct (allocate memory)
         this.tyCoords = new int[yCoords.length]; // construct (allocate memory)
         // Deep Copy
         for (int i = 0; i < xCoords.length; i++)
         {
         this.xCoords[i] = xCoords[i];
         this.txCoords[i] = xCoords[i] + xPos;
         }
         // Deep Copy
         for (int i = 0; i < yCoords.length; i++)
         {
         this.yCoords[i] = yCoords[i];
         this.tyCoords[i] = yCoords[i] + yPos;
         }
     }

    @Override
        /**
         * Draw method for Polygon2D
         * @param g Graphics object
         * @return void
         */
        public void Draw(Graphics g)
        {
         Transform(); // Scale, Rotate, Translate
         if (getFill())
         {
         g.setColor(super.getFillColor());
         g.fillPolygon(txCoords, tyCoords, xCoords.length);
         }
         if (getOutline())
         {
         g.setColor(super.getOutlineColor());
         g.drawPolygon(txCoords, tyCoords, xCoords.length);
         }
        }
        
        /**
         * Transform method for Polygon2D
         * @return void
         */
        private void Transform()
    {
        double degs = super.GetZRotate();
        double rads = Math.toRadians(degs);
        double Sx = super.GetScaleX();
        double Sy = super.GetScaleY();
            for (int i = 0; i < xCoords.length; i++)
            {
            double x = Sx * this.xCoords[i];
            double y = Sy * this.yCoords[i];
            this.txCoords[i] = (int)(((x * Math.cos(rads) - y * Math.sin(rads)) +
            super.getXPos()) + 0.5);
            this.tyCoords[i] = (int)(((x * Math.sin(rads) + y * Math.cos(rads)) +
            super.getYPos()) + 0.5);
        }
    }

     
     
}
