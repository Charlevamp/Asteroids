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
    
    
    public static void main(String[] args){
        
    }

    public CanvasPanel_Le11()
    {   
        shapesList = new ArrayList<Shape2D>();
        action = false;
    
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

    public void Simulate()
    { 
           
            Shape2D shape = shapesList.get(0); // Only one shape now: the ship
    
            // Handle rotation
            if (rotateLeft) {
                shape.angleVel = -5;  
                shape.Animate();
            }
            else if (rotateRight) {
                shape.angleVel = 5;   
                shape.Animate();
            }
            else {
                shape.angleVel = 0;
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
            
            shape.Move(dx, dy);
    
            
        
        
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
        for (Shape2D shape : shapesList)
        {
            shape.Draw(g);
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
            
        }
    }
    }
}
