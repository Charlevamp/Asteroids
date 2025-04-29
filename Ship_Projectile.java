import java.awt.Graphics;

/**
 * Write a description of class Ship_Projectile here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Ship_Projectile extends Shape2D
{
    // instance variables - replace the example below with your own
    private int size = 2;
    private int speed = 10;

    /**
     * Constructor for objects of class Ship_Projectile
     */
    public Ship_Projectile(int xPos, int yPos, double angleDegrees)
    {
        super(WHITE, xPos, yPos);
        
        double angleRad = Math.toRadians(angleDegrees);
        int dx = (int) (Math.cos(angleRad) * speed);
        int dy = (int) (Math.sin(angleRad) * speed);
        
        
        this.setXVel(dx);
        this.setYVel(dy);
        
        
        
        
    }

    /**
     * Draw method
     *
     * @param  g graphics object
     * @return   void
     */
    public void Draw(Graphics g){
        g.setColor(COLORS[WHITE]);
        g.fillOval(xPos, yPos, size, size);
    
    
    }
    
    /**
     * Animate method 
     * @return void
     */
    @Override
    public void Animate(){
        Move();
    
    }
    
    /**
     * Animate method 
     * @return void
     */
    public boolean isOffScreen() {
        return (xPos < -size || xPos > CanvasPanel_Le11.getCanvasWidth() + size ||
                yPos < -size || yPos > CanvasPanel_Le11.getCanvasHeight() + size);
    }
}
