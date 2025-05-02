import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class Ship2D extends Shape2D {
    private BufferedImage[] imageFrames;
    private int frame;

    private double scaleX = 1.0; // Default scale for X axis
    private double scaleY = 1.0; // Default scale for Y axis

    /**
     * Constructor for objects of class Ship2D
     */
    public Ship2D(int xPos, int yPos, BufferedImage[] imageFrames) {
        super(0, xPos, yPos);
        SetScaleX(0.01); // Default scale on X
        SetScaleY(0.01); // Default scale on Y
        this.imageFrames = new BufferedImage[imageFrames.length];
        for (int i = 0; i < imageFrames.length; i++) {
            this.imageFrames[i] = imageFrames[i];
        }
        frame = 0;
    }

    public void Draw(Graphics g) {
    Graphics2D g2d = (Graphics2D) g.create();

    int centerX = getXPos();
    int centerY = getYPos();

    // Apply transformations to draw the ship
    g2d.translate(centerX, centerY);
    g2d.rotate(Math.toRadians(GetZRotate()));
    g2d.scale(scaleX, scaleY);
    g2d.drawImage(imageFrames[frame], -13, -13, 26, 26, null);

    g2d.dispose(); // Dispose transformed context
    
    
    // Frame cycling
    frame++;
    if (frame == imageFrames.length) {
        frame = 0;
    }
}

    /**
     * Get the bounds of the ship for collision detection
     * @return Rectangle representing the bounding box of the ship
     */
    public Rectangle getBounds() {
         int width = (int)(26 * scaleX);
        int height = (int)(26 * scaleY);

        int topLeftX = getXPos() - width / 2;
        int topLeftY = getYPos() - height / 2;

        return new Rectangle(topLeftX, topLeftY, width, height);
    }
}
