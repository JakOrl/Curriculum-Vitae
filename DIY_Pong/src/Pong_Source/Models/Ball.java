package Pong_Source.Models;

/**
 * Represents the ball in the Pong game.
 * <p>
 * This class handles the ball's position, dimensions, and velocity.
 * It includes logic for basic movement and physics-based state changes
 * such as bouncing and speed acceleration.
 * </p>
 * @author Jakub Orlowski
 * @version 1.0
 */
public class Ball {

    //------------------------------
    // Fields
    //------------------------------
    private double x;
    private double y;
    private double height;
    private double width;

    /** The horizontal velocity component. Positive moves right, negative moves left. */
    private double xVel = 3.5;

    /** The vertical velocity component. Positive moves down, negative moves up. */
    private double yVel = 3.5;
    //------------------------------
    // Constructor
    //------------------------------
    /**
     * Constructs a new Ball with specified initial position and size.
     * @param x      Initial X-coordinate.
     * @param y      Initial Y-coordinate.
     * @param width  The diameter/width of the ball.
     * @param height The height of the ball.
     */
    public Ball(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    //-----------------------------
    //  Extra methods
    //-----------------------------

    /**
     * Updates the ball's current position based on its velocity values.
     * This should be called once per frame by the controller.
     */
    public void Move(){
        this.x += xVel;
        this.y += yVel;
    }

    /**
     * Updates the ball's current position based on its velocity values.
     * This should be called once per frame by the controller.
     */
    public void wallBounce(){
        this.xVel *= -1;
    }

    /**
     * Reverses the vertical velocity.
     * Typically called when hitting the top or bottom of the screen.
     */
    public void ceilingBounce(){
        this.yVel *= -1;
    }

    /**
     * Increases the ball's speed by a specified multiplier.
     * @param speedup The multiplier to apply to both x and y velocity (e.g., 1.3 for 30% increase).
     */
    public void increaseSpeed(double speedup){
        this.xVel *= speedup;
        this.yVel *= speedup;
    }
    //------------------------------
    // Getters / Setters
    //------------------------------

    // X =========================
    /** @return Current X-coordinate of the ball's top-left corner. */
    public double getBallX() {return x;}

    /** @param x New X-coordinate. */
    public void setBallX(double x) {
        this.x = x;
    }

    // Y =========================
    /** @return Current Y-coordinate of the ball's top-left corner. */
    public double getBallY() {
        return y;
    }

    /** @param y New Y-coordinate. */
    public void setBallY(double y) {
        this.y = y;
    }

    /** @return The width of the ball. */
    public double getBallWidth() {return width;}

    /** @return The height of the ball. */
    public double getBallHeight() {
        return height;
    }

    /**
     * Manually sets the velocity components of the ball.
     * Useful for resetting the serve or custom physics events.
     * @param xspeed The new horizontal velocity.
     * @param yspeed The new vertical velocity.
     */
    public void setVelValues(double xspeed, double yspeed){
        this.xVel = xspeed;
        this.yVel = yspeed;
    }
}
