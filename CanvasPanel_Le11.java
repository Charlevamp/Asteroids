/**
 * 2D CanvasPanel
 * 
 *
 * @author (Prof R)
 * @version (v1.0 11-17-22)
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
// For Sprites 
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Random;

public class CanvasPanel_Le11 extends JPanel
{
    private final static int X_CORNER = 25;
    private final static int Y_CORNER = 25;
    private final static int CANVAS_WIDTH = 600;
    private final static int CANVAS_HEIGHT = 600;
    List<Shape2D>   shapesList;
    private int     frameNumber;

    // Indices of dynamic shapes
    private final static int Star_Shape_1 = 5;
    private final static int RED_CIRCLE = 6;
    private final static int BLUE_CIRCLE = 7;
    private final static int BLUE_RECT = 8;
    private final static int SONIC_SPRITE_1 = 10;
    private final static int SHIP_SPRITE = 11;

    
    boolean         action;
    private boolean jumpUp;    // for Sprite
    private boolean fallDown;  // for Sprite
    
    private boolean rotateLeft; //for Ship
    private boolean rotateRight; //for Ship
    private boolean moveUp;
    private boolean moveDown;
    private boolean moveLeft;
    private boolean moveRight; 
    
    //asteroid
    private ArrayList<Asteroid> asteroids = new ArrayList<>();
    private BufferedImage[] asteroidFrames; // load your sprite frames elsewhere
    private Random rand = new Random();
    private int spawnTimer = 0;
    public static void main(String[] args){
        
    }

    public CanvasPanel_Le11()
    {   
        shapesList = new ArrayList<Shape2D>();
        action = false;
        
        //asteroids
        asteroidFrames = new BufferedImage[1];
        try{
            asteroidFrames[0] = ImageIO.read(new File("Asteroid1.png"));
        } catch (IOException ie){
            ie.printStackTrace();
        }
        // Ship
        BufferedImage[] Ship_Sprite = new BufferedImage[1];
        try {
            Ship_Sprite[0] = ImageIO.read(new File("ship.png")); // your ship image
        }
        catch (IOException ie) {
            ie.printStackTrace();
        }
        shapesList.add(new Ship2D(300, 300, Ship_Sprite)); // starting around center of canvas
    
        // Callback from keyboard events
        this.setFocusable(true);
        this.addKeyListener(new myActionListener());
        System.out.println("keyboard event registered");
       
        // Create a render loop
        Timer renderLoop = new Timer(30, (ActionEvent ev) -> {
            frameNumber++;
            Simulate();
            repaint();
        });
        renderLoop.start();
    }
    
    private void spawnAsteroid(){
        int x,y;
        int place = rand.nextInt(4); //pick random int for random spawn placement
        switch(place){
            case 0: // Top (above canvas)
                x = rand.nextInt(CANVAS_WIDTH) + X_CORNER;
                y = -100; // offscreen above
                break;
            case 1: // Bottom (below canvas)
                x = rand.nextInt(CANVAS_WIDTH) + X_CORNER;
                y = CANVAS_HEIGHT + Y_CORNER + 100; // offscreen below
                break;
            case 2: // Left (left of canvas)
                x = -100;
                y = rand.nextInt(CANVAS_HEIGHT) + Y_CORNER;
                break;
            case 3: // Right (right of canvas)
                x = CANVAS_WIDTH + X_CORNER + 100;
                y = rand.nextInt(CANVAS_HEIGHT) + Y_CORNER;
                break;
            default:
                x = 0;
                y = 0;
        }
        
        double centerX = X_CORNER + CANVAS_WIDTH / 2.0;
        double centerY = Y_CORNER + CANVAS_HEIGHT / 2.0;
        double diffX = centerX - x + rand.nextInt(201) - 100; // offset -100 to +100
        double diffY = centerY - y + rand.nextInt(201) - 100; // offset -100 to +100
        double length = Math.sqrt(diffX * diffX + diffY * diffY);

        double speed = 2.0;
        double dx = (diffX / length) * speed;
        double dy = (diffY / length) * speed;
        
        asteroids.add(new Asteroid(x, y,dx, dy, asteroidFrames));
    }

    public void Simulate()
    { 
        spawnTimer++; //timer for asteroid spawns
        if(spawnTimer % 35 == 0){ 
            spawnAsteroid();
            }
        
        for(Asteroid a : asteroids){
            a.update();
        }
        Shape2D ship = shapesList.get(0); // Only one shape now: the ship
    
            // Handle rotation
            if (rotateLeft) {
                ship.angleVel = -5;  
                ship.Animate();
            }
            else if (rotateRight) {
                ship.angleVel = 5;   
                ship.Animate();
            }
            else {
                ship.angleVel = 0;
            }
    
                // Handle movement
            int dx = 0;
            int dy = 0;
            int moveSpeed = 5; // adjust if needed
        
            if (moveUp) {
                dy -= moveSpeed;
            }
            if (moveDown) {
                dy += moveSpeed;
            }
            if (moveLeft) {
                dx -= moveSpeed;
            }
            if (moveRight) {
                dx += moveSpeed;
            }
            
            ship.Move(dx, dy);
            
            for(int i = 1; i < shapesList.size(); i++){
                Shape2D bullet = shapesList.get(i);
                bullet.Animate();
                
            
            }
    
            
            shapesList.removeIf(shape -> (shape instanceof Ship_Projectile) && ((Ship_Projectile) shape).isOffScreen());
            //
            /*
            for(int i = 1; i < shapesList.size(); i++){
                if(((Ship_Projectile)shapesList.get(i)).isOffScreen()){
                    shapesList.remove(shapesList.get(i));
                
                }
            
            }
            */

        Rectangle shipBounds = ((Ship2D) ship).getBounds(); // cast to access getBounds

        for (Asteroid a : asteroids) {
            Rectangle asteroidBounds = a.getBounds();

            if (shipBounds.intersects(asteroidBounds)) {
                System.out.println("COLLISION DETECTED!");
                // Handle collision - e.g., end game, remove ship, show explosion, etc.
                // Example:
                shapesList.remove(ship); // remove the ship
                break; // optional: stop checking further
            }
            
        }
    }

    // This method is called by renderloop
    public void paintComponent(Graphics g)
    {
        Shape2D shapeRef = null;
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Set window background to black
        g.setColor(Color.BLACK);
        g.fillRect(0,0,CANVAS_WIDTH + 2 * X_CORNER, CANVAS_HEIGHT + 2 * Y_CORNER); //draw the black border

        // Set canvas background to grey
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(X_CORNER, Y_CORNER, CANVAS_WIDTH, CANVAS_HEIGHT); //make the canvas white

        // Display frame number
        g.setColor(Color.white);   
        g.setFont(new Font("Consolas", Font.PLAIN, 30));
        g.drawString(Integer.toString(frameNumber), 300, 70);

        // Render all the shapes in the shapes list
        for (Shape2D shape : shapesList){
            shape.Draw(g);
        }
        for(Asteroid a : asteroids){
            a.Draw(g);
        }
    }
    

    public static int getCanvasWidth()
    {
        return CANVAS_WIDTH;
    }

    public static int getCanvasHeight()
    {
        return CANVAS_HEIGHT;
    }

    public static int getCanvasXBorder()
    {
        return X_CORNER;
    }

    public static int getCanvasYBorder()
    {
        return Y_CORNER;
    }
public class myActionListener extends KeyAdapter 
    {
        public void keyPressed(KeyEvent e)
        {
            switch (e.getKeyCode())
            {
                
                
                //Turning
                case KeyEvent.VK_LEFT: //TURN LEFT FOR SHIP
                    System.out.println("press left arrow");
                    rotateLeft = true;
                    break;
                case KeyEvent.VK_RIGHT: //TURN RIGHT FOR SHIP
                    System.out.println("press left arrow");
                    rotateRight = true;
                    break;
                
                //Moving a single direction  
                case KeyEvent.VK_W:
                    moveUp = true;
                    break;
                case KeyEvent.VK_A:
                    moveLeft = true;
                    break;
                case KeyEvent.VK_S:
                    moveDown = true;
                    break;
                case KeyEvent.VK_D:
                    moveRight = true;
                    break;
                default:
                    System.out.println("press some other key besides the arrow keys");
            }
        }

        public void keyReleased(KeyEvent e)
        {
            switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                rotateLeft = false;
                break;
            case KeyEvent.VK_RIGHT:
                rotateRight = false;
                break;
            
            case KeyEvent.VK_W:
                    //action = true;
                    moveUp = false;;
                    break;
            case KeyEvent.VK_A:
                    //action = true;
                    moveLeft = false;;
                    break;
            case KeyEvent.VK_S:
                    moveDown = false;
                    break;
            case KeyEvent.VK_D:
                    //action = true;
                    moveRight = false;
                    break;
            case KeyEvent.VK_SPACE:
                shootProjectile();
                break;
                
            
        }
    }
    
    /**
     * Constructor for objects of class Ship_Projectile
     */
    public void shootProjectile(){
        Shape2D ship = shapesList.get(0);
        
        int shipCenterX = ship.getXPos() + 13;
        int shipCenterY = ship.getYPos() + 13;
        double angle = ship.GetZRotate();
        
        Ship_Projectile bullet = new Ship_Projectile(shipCenterX, shipCenterY, angle);
        shapesList.add(bullet);
    }
    }
}
