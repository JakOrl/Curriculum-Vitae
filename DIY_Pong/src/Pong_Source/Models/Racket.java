package Pong_Source.Models;


/**
 * Represents a playable paddle (racket) in the Pong game.
 * <p>
 * This class defines the position, dimensions, and movement speed of a racket.
 * it includes boundary-aware movement methods to ensure the racket stays
 * within the vertical limits of the game window.
 * </p>
 * @author Jakub Orlowski
 * @version 1.0
 */
public class Racket {

    //------------------------------
    // Fields
    //------------------------------
    private double x, y;
    private double width, height;

    /**
     * The vertical distance the racket moves per frame when a key is held.
     */
    private double speed = 12.0;

    //------------------------------
    // Constructor
    //------------------------------

    /**
     * Constructs a new Racket with specific coordinates and size.
     *
     * @param x      The initial X-coordinate (horizontal position).
     * @param y      The initial Y-coordinate (vertical position).
     * @param width  The thickness of the racket.
     * @param height The vertical length of the racket.
     */
    public Racket(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    //------------------------------
    // Additional Methods
    //------------------------------

    /**
     * Moves the racket upwards by its current speed.
     * The method includes a safety check to prevent the racket from
     * moving above the top boundary (y < 0).
     */
    public void moveUp() {
        if (this.y > 0) {
            this.y -= speed;
        }
    }

    /**
     * Moves the racket downwards by its current speed.
     * The method includes a safety check to ensure the bottom edge of the
     * racket does not exceed the canvas height.
     *
     * @param canvasHeight The total height of the drawing area used for boundary checking.
     */
    public void moveDown(double canvasHeight) {
        if (this.y + this.height < canvasHeight) {
            this.y += speed;
        }
    }
    //------------------------------
    // Getters / Setters
    //------------------------------

    // X =========================

    /**
     * @return The current X-coordinate of the racket.
     */
    public double getRacketX() {
        return x;
    }

    /**
     * @param x Sets a new X-coordinate for the racket.
     */
    public void setRacketX(double x) {
        this.x = x;
    }


    // Y =========================

    /**
     * @return The current Y-coordinate of the racket's top edge.
     */
    public double getRacketY() {
        return y;
    }

    /**
     * @param y Sets a new Y-coordinate for the racket.
     */
    public void setRacketY(double y) {
        this.y = y;
    }


    // Width & Height ============

    /**
     * @return The thickness (width) of the racket.
     */
    public double getRacketWidth() {
        return width;
    }

    /**
     * @param width Sets a new width for the racket.
     */
    public void setRacketWidth(double width) {
        this.width = width;
    }

    /**
     * @return The vertical length (height) of the racket.
     */
    public double getRacketHeight() {
        return height;
    }

    /**
     * @param height Sets a new height for the racket.
     */
    public void setRacketHeight(double height) {
        this.height = height;
    }
}