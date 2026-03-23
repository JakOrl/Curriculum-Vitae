package Pong_Source.View;

import Pong_Source.Models.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import Pong_Source.Controller.*;


/**
 * The primary rendering engine for the Pong game.
 * <p>
 * This class extends the JavaFX {@link Canvas} to provide custom drawing
 * capabilities. It handles the visualization of the game field, rackets,
 * ball, and the Heads-Up Display (HUD) including scores and game-state messages.
 * </p>
 * @author Jakub Orlowski
 * @version 1.0
 */
public class Pong_Canvas extends Canvas {

    //--------------------
    // Fields
    //--------------------
    /** The graphics tool used to issue draw commands to the canvas. */
    private GraphicsContext gc;

    /** Reference to the controller to access game state and status messages. */
    private Controller controller;
    //--------------------
    // Constructor
    //--------------------

    /**
     * Constructs the game canvas with default dimensions.
     * @param controller The {@link Controller} that provides the state for rendering.
     */
    public Pong_Canvas(Controller controller) {
        super(600,640);
        this.controller = controller;
        this.gc = getGraphicsContext2D();
    }

    //--------------------
    // Additionl Functions
    //--------------------

    /**
     * The main rendering method called by the game loop.
     * <p>
     * This method clears the screen and redraws all components in a specific order:
     * <ol>
     * <li>Background (Navy fill)</li>
     * <li>Scores and Player Names</li>
     * <li>Rackets (left and right)</li>
     * <li>The Ball</li>
     * <li>Overlay messages (Pause, Goal, Game Over)</li>
     * </ol>
     * </p>
     * @param P1   The object for Player 1 (Left).
     * @param P2   The object for Player 2 (Right).
     * @param ball The Ball object currently in play.
     */
    public void Draw(Player P1, Player P2, Ball ball) {

        // Clearing canvas
        gc.clearRect(0,0,getWidth(),getHeight());

        // Clearing and making background navy
        gc.setFill(Color.NAVY);
        gc.fillRect(0,0,getWidth(),getHeight());

        // forcing alignment (bug fix)
        gc.setTextAlign(javafx.scene.text.TextAlignment.LEFT);

        // Drawing the HUD
        double rightScoreX = getWidth() - 220;
        gc.setFill(Color.WHITE);
        gc.setFont(Font.font("Impact", FontWeight.BOLD, 30));
        gc.fillText(P1.getScore() + " -- " + P1.getName(), 50, 50);
        gc.fillText(P2.getScore() + " -- " + P2.getName(), rightScoreX, 50);

        // Left racket
        gc.setFill(Color.WHITE);
        gc.fillRect(
                P1.getRacket().getRacketX(),
                P1.getRacket().getRacketY(),
                P1.getRacket().getRacketWidth(),
                P1.getRacket().getRacketHeight()
        );

        // Right racket
        gc.setFill(Color.WHITE);
        double r2_width = P2.getRacket().getRacketWidth();
        double r2X = getWidth() - r2_width - 20;

        P2.getRacket().setRacketX(r2X);

        gc.fillRect(
                r2X,
                P2.getRacket().getRacketY(),
                r2_width,
                P2.getRacket().getRacketHeight()
        );

        // Drawing Ball
        gc.setFill(Color.WHITE);

        gc.fillOval(
                ball.getBallX(),
                ball.getBallY(),
                ball.getBallWidth(),
                ball.getBallHeight()
        );

        String msg = controller.getStatus_Message();
        // -------------------------------------------------------
        // UI AND MESSAGING LOGIC
        // -------------------------------------------------------
        gc.setTextAlign(javafx.scene.text.TextAlignment.CENTER);

        // 1. PRIORITIZE PAUSE MESSAGE
        if (controller.isPaused()) {
            gc.setFill(Color.YELLOW);
            gc.setFont(new Font("Impact", 40));
            gc.fillText(controller.getPauseMessage(), getWidth() / 2, getHeight() / 2);


            if (!controller.getStatus_Message().isEmpty()) {
                gc.setFont(new Font("Arial", 15));
                gc.fillText(controller.getStatus_Message(), getWidth() / 2, getHeight() / 2 + 40);
            }
        }
        // 2. OTHERWISE, DRAW THE SCORE/SERVE POPUP
        else if (!controller.gameStart || controller.isGameOver()) {
            // Score/Win message
            gc.setFill(Color.WHITE);
            gc.setFont(new Font("Impact", 30));
            gc.fillText(controller.getStatus_Message(), getWidth() / 2, getHeight() / 2);

            // Sub-instructions
            gc.setFont(new Font("Arial", 15));
            if(!controller.isGameOver()) {
                gc.fillText("Press Space to serve!", getWidth() / 2, getHeight() / 2 + 30);
            } else {
                gc.fillText("Press N for a new game!", getWidth() / 2, getHeight() / 2 + 30);
            }
        }
    }
}

