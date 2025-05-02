import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.awt.geom.AffineTransform;

public class Asteroid {
    private int xPos, yPos;
    private int dx, dy; // velocities as integers
    private BufferedImage[] frames;
    private int currentFrame = 0;

    private double scale = 1.0;
    private double rotation = 0.0;

    public Asteroid(int x, int y, double dxd, double dyd, BufferedImage[] frames) {
        this.xPos = x;
        this.yPos = y;

        // Round velocities to keep dx/dy as ints
        this.dx = (int) Math.round(dxd);
        this.dy = (int) Math.round(dyd);

        this.frames = frames;
        
        // Assign a fixed scale once, between 0.8 and 1.2
        this.scale = 0.8 + 0.4 * new Random().nextDouble();
    }

    // Update position and velocity
    public void update() {
        xPos += dx;
        yPos += dy;

        // Rotate and pulse scale a bit
        rotation += 2; // degrees
        if (rotation >= 360) rotation -= 360;
    }

    // This will calculate the bounds (hitbox) based on the position and scaling
    public Rectangle getBounds() {
        if (frames == null || frames.length == 0 || frames[currentFrame] == null) {
            return new Rectangle(xPos, yPos, 0, 0); // avoid null exceptions if image is missing
        }

        // Get the original dimensions of the image (before scaling)
        int originalWidth = frames[currentFrame].getWidth();
        int originalHeight = frames[currentFrame].getHeight();

        // Apply scaling to the width and height of the image
        int width = (int) (originalWidth * scale);
        int height = (int) (originalHeight * scale);

        // Adjust the x and y position so that the bounding box is centered around the asteroid
        int topLeftX = xPos - width / 2;
        int topLeftY = yPos - height / 2;

        return new Rectangle(topLeftX, topLeftY, width, height);
    }

    // Draw method for rendering the asteroid and hitbox for debugging
    public void Draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        AffineTransform old = g2.getTransform();

        int width = frames[currentFrame].getWidth();
        int height = frames[currentFrame].getHeight();

        g2.translate(xPos, yPos);
        g2.rotate(Math.toRadians(rotation));
        g2.scale(scale, scale);

        // Draw centered image
        g2.drawImage(frames[currentFrame], -width / 2, -height / 2, null);

        g2.setTransform(old); // restore

        // Optional: Draw the hitbox for debugging (remove this in production)
        g.setColor(Color.RED);  // red color for hitbox
        g.drawRect(xPos - width / 2, yPos - height / 2, width, height);
    }

    // Check if asteroid has gone off the screen
    public boolean isOffScreen(int screenWidth, int screenHeight) {
        Rectangle bounds = getBounds();
        return bounds.x + bounds.width < 0 || bounds.x > screenWidth || bounds.y + bounds.height < 0 || bounds.y > screenHeight;
    }
}
