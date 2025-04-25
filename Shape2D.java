import java.awt.Color;
import java.awt.Graphics;

/**
 * Super class for circle, rectangle, and oval classes
 *
 * @author Antoine Brown
 * @version 4-23-2025
 */
public abstract class Shape2D
{
    public final static int RED = 0;
    public final static int GREEN = 1;
    public final static int BLUE = 2;
    public final static int BLACK = 3;
    public final static int GREY = 4;
    public final static int WHITE = 5;
    public final static int YELLOW = 6;
    public final static int CYAN = 7;
    public final static int MAGENTA = 8;
    public final static int BROWN = 9;
    
    // RGB color table
    public static final Color[] COLORS = {
            //         R     G    B
            new Color(255,   0,   0),  // Red     0
            new Color(  0, 255,   0),  // Green   1
            new Color(  0,   0, 255),  // Blue    2
            new Color(  0,   0,   0),  // Black   3
            new Color(128, 128, 128),  // Grey    4
            new Color(255, 255, 255),  // White   5
            new Color(255, 255,   0),  // Yellow  6
            new Color(  0, 255, 255),  // Cyan    7
            new Color(255,   0, 255),  // Magenta 8 
            new Color(165,  42,  42),  // Brown   9
            new Color(255,  38,  38),
            new Color(255, 168,  38),
            new Color(212, 255,  38),
            new Color( 82, 255,  38),
            new Color( 38, 255, 125),
            new Color( 38, 255, 255),
            new Color( 38, 125, 255),
            new Color( 82,  38, 255),
            new Color(212,  38, 255),
            new Color(255,  38, 168),
        }; 

    // instance variables - replace the example below with your own
    public int     xPos;               // xPos
    public int     yPos;               // yPos
    public Color   fillColor;          // the color of the shape
    public int     fillColorIndex;    // the index of the color of the shape in the COLORS array
    private int     xVel;              // velocity in x direction
    private int     yVel;              // velocity in y direction 
    private Color   outlineColor;      // Color of shape outline
    private int     outlineColorIndex; // Color index of shape outline color
    private boolean fill = true;       // Whether the shape is filled
    private boolean outline = false;   // Wether the shape is outlined
    
    private double sX = 1; // Scale X
    private double sY = 1; // Scale Y
    private double rotAngleZ = 0; // Rotation about the Z axis
    

    /**
     * Constructor for objects of class Shape2D
     */
    public Shape2D(int fillColorIndex, int xPos, int yPos)
    {
        this.fillColor = COLORS[fillColorIndex];
        this.xPos = xPos;
        this.yPos = yPos;
        
        
    }
    
    /**
     * Moves the shape by an amount (xDelta, yDelta)
     *
     * Move - translates the shape by an amount (xDelta, yDelta)
     *
     * @param  xDelta - amount to translate along the x axis
     *         yDelta - amount to translate along the y axis
     * @return None
     */
    public void Move(int xDelta, int yDelta)
    {
        //move the shape
        this.xPos += xDelta;
        this.yPos += yDelta;
    }
    
    /**
     * Moves the shape using velocity values
     *
     * Move - translates the shape in accordance with velocity values
     * @return void
     */
        public void Move() {
        this.xPos += xVel;
        this.yPos += yVel;
    }

    /**
     * Draw method
     *
     * @param  g graphics object
     * @return   void
     */
    public abstract void Draw(Graphics g);
    
    /**
     * Getter method for xPos
     * @return xPos
     */
    public int getXPos(){
        return this.xPos;
    
    }
    
    /**
     * Setter method for xPos
     * @param  xPos the new xPos vaue
     * @return void
     */
    public void setXPos(int xPos){
        this.xPos = xPos;
    
    }
    
    /**
     * Getter method for yPos
     *
     * @return yPos
     */
    public int getYPos(){
        return this.yPos;
    
    }
     
    /**
     * Setter method for yPos
     *
     * @param  yPos the new yPos vaue
     * @return void
     */
    public void setYPos(int yPos){
        this.yPos = yPos;
    
    }
    
    /**
     * Getter method for xVel
     *
     * @return xVel
     */
        public int getXVel() {
        return xVel;
    }
    
    /**
     * Setter method for xVel
     *
     * @param  xVel the new xVel vaue
     * @return void
     */
    public void setXVel(int xVel) {
        this.xVel = xVel;
    }
    
    /**
     * Getter method for yVel
     *
     * @return yVel
     */
    public int getYVel() {
        return yVel;
    }
    
    /**
     * Setter method for yVel
     *
     * @param  yVel the new yVel vaue
     * @return void
     */public void setYVel(int yVel) {
        this.yVel = yVel;
    }
    
    /**
     * Setter method for Speed
     *
     * @param  yVel the new yVel value
     * @param  xVel the new xVel value
     * @return void
     */public void SetSpeed(int xVel, int yVel) {
     this.xVel = xVel;
     this.yVel = yVel;
    }
    
    
    /**
     * Getter method for fillColor
     *
     * @return fillColor
     */
    public Color getFillColor() {
        return this.fillColor;
        }
        
    /**
     * Setter method for fillColor
     *@param fillcolor the new fillColor value
     * @return void
     */
    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
        
        }
        
    /**
     * Setter method for fillColorIndex
     * @return fillColorIndex value
     */
        public int getFillColorIndex() {
        return fillColorIndex;
    }
    
    /**
     * Setter method for fillColor
     * @param new fillColorIndex
     * @return void
     */
    public void setFillColorIndex(int fillColorIndex) {
        if (fillColorIndex >= 0 && fillColorIndex < COLORS.length) {
            this.fillColorIndex = fillColorIndex;
            this.fillColor = COLORS[fillColorIndex];
        } else {
            System.out.println("Invalid fillColorIndex: " + fillColorIndex);
        }
    }
    
    /**
     * Getter method for fillColor
     * @return Color
     */
        public Color getOutlineColor() {
        return outlineColor;
    }
    
    /**
     * Setter method for fillColor
     * @param new outlineColor
     * @return void
     */
    public void setOutlineColor(int outlineColorIndex) {
        this.outlineColor = COLORS[outlineColorIndex];
        this.outlineColorIndex = -1; // use -1 for custom color
    }
    
    /**
     * Getter method for colorIndex
     * @return int
     */
    public int getOutlineColorIndex() {
        return outlineColorIndex;
    }
    
    /**
     * Setter method for outlineColorIndex
     * @param new outlineColorIndex
     * @return void
     */
    public void setOutlineColorIndex(int outlineColorIndex) {
        if (outlineColorIndex >= 0 && outlineColorIndex < COLORS.length) {
            this.outlineColorIndex = outlineColorIndex;
            this.outlineColor = COLORS[outlineColorIndex];
        } else {
            System.out.println("Invalid outlineColorIndex: " + outlineColorIndex);
        }
    }
    
    /**
     * Getter method for fillColor
     * @return boolean
     */
    public boolean getFill() {
        return fill;
    }
    
    /**
     * Setter method for fill
     * @param new fill status as a boolean
     * @return void
     */
    public void setFill(boolean fill) {
        this.fill = fill;
    }
    
    /**
     * Getter method for outline status as a boolean
     * @return boolean
     */
    public boolean getOutline() {
        return outline;
    }
    
    /**
     * Getter method for outline status as a boolean
     * @param new outline boolean
     * @return void
     */
    public void setOutline(boolean outline) {
        this.outline = outline;
    }
    
    /**
     * Getter method for outline status as a boolean
     * @param new xPos
     * @param new yPos
     * @return void
     */
    public void SetPos(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }
    
    /**
     * Animate method 
     * @return void
     */
    public void Animate(){
        this.xPos += xVel;
        this.yPos += yVel;
    
    }
    
    /**
     * Getter method for X Scale
     * @return double
     */
    public double GetScaleX(){
        return this.sX;
    
    }
    
    /**
     * Setter method for X Scale
     * @param sY - new sY value
     * @return void
     */
    public void SetScaleX(double sY){
        this.sY = sY;
    
    }
    
    /**
     * Getter method for UY Scale
     * @return double
     */
    public double GetScaleY(){
        return this.sX;
    
    }
    
    /**
     * Setter method for X Scale
     * @param sX - new sX value
     * @return double
     */
    public void SetScaleY(double sY){
        this.sY = sY;
    
    }
    
    /**
     * Getter method for rotAngleZ
     * @return double
     */
    public double GetZRotate(){
        return this.rotAngleZ;
    
    
    }
    
    /**
     * Setter method for X Scale
     * @param rotAngleZ new rotAngleZ value
     * @return void
     */
    public void SetZRotate(double rotAngleZ){
        this.rotAngleZ = rotAngleZ;
    
    
    }
    
    
}
