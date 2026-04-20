package Pong_Source.Controller;

import Pong_Source.Models.*;
import Pong_Source.View.Pong_Canvas;
import javafx.animation.AnimationTimer;

/**
 * The Controller of the Pong application.
 * <p>
 * This class manages the Game Loop (via {@link AnimationTimer}), handles user input
 * translations to model movements, detects collisions, and manages game states
 * such as scoring, pausing, and game over conditions.
 * </p>
 * * @author Jakub Orlowski
 * @version 1.0
 */

public class Controller {

    // Fields
    private Game game;
    private Ball ball;
    private Pong_Canvas canvas;
    private Player P1;
    private Player P2;
    private AnimationTimer timer;

    /** Movement flags for Player 1 and Player 2 paddles. */
    public boolean p1UP, p1DOWN, p2UP, p2DOWN;

    /** Indicates if the ball is currently in play. */
    public boolean gameStart;

    private String Status_Message = "";
    private String Pause_Message = "";

    private boolean is_game_over =  false;
    private int BounceCount = 0;
    private boolean isPaused = false;


    //------------------------------
    // Constructor
    //------------------------------
    /**
     * Initializes a new Game session.
     * Sets up the Game settings, creates two Players with Rackets at their
     * starting positions, and initializes the Ball.
     */
    public Controller(){
        this.game = new Game();

        Racket r1 = new Racket(20, 250, 20, 100);
        Racket r2 = new Racket(560, 250, 20, 100);

        this.P1 = new Player(r1, "Player 1", 0);
        this.P2 = new Player(r2, "Player 2", 0);

        this.ball = new Ball(300, 320, 20, 20);
    }

    //------------------------------
    // Additional Methods
    //------------------------------
    /**
     * Starts the main game loop.
     * <p>
     * The loop handles:
     * <ul>
     * <li>Paddle movement based on boolean flags.</li>
     * <li>Ball physics and collision detection with walls and rackets.</li>
     * <li>Automatic speed scaling based on bounce count.</li>
     * <li>Scoring detection when the ball leaves the horizontal bounds.</li>
     * </ul>
     * </p>
     * @param canvas The {@link Pong_Canvas} where the game state is rendered.
     */

    public void startFramerate(Pong_Canvas canvas){
        this.canvas = canvas;
        this.timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // ---------------------
                // Racket movement logic
                // ---------------------
                if (p1UP){
                    getP1().getRacket().moveUp();
                }
                if (p1DOWN){
                    getP1().getRacket().moveDown(canvas.getHeight());
                }
                if (p2UP){
                    getP2().getRacket().moveUp();
                }
                if (p2DOWN){
                    getP2().getRacket().moveDown(canvas.getHeight());
                }

                // -------------------------------------------------------------------------
                // Live size correction to rackets dont go underground when shrinking window
                // -------------------------------------------------------------------------

                if (P1.getRacket().getRacketY() + P1.getRacket().getRacketHeight() > canvas.getHeight()){
                    P1.getRacket().setRacketY(canvas.getHeight() - P1.getRacket().getRacketHeight());
                }
                if (P2.getRacket().getRacketY() + P2.getRacket().getRacketHeight() > canvas.getHeight()){
                    P2.getRacket().setRacketY(canvas.getHeight() - P2.getRacket().getRacketHeight());
                }
                // --------------------------------------------------------
                // Game starts, ball is a center otherwise + ball movement
                // --------------------------------------------------------
                if (gameStart) {
                    ball.Move();
                    // ---------------------
                    // Racket - Ball Collision logic
                    // ---------------------
                    // Left Racket
                    if (CollisionHandler.checkHit(ball, P1.getRacket())){
                        ball.wallBounce();

                        BounceCount++;
                        if (BounceCount >= game.getBall_speed_increase()) {
                            ball.increaseSpeed(1.3); // Increase speed by 30%
                            BounceCount = 0;         // Reset the counter
                        }

                        // Such that the ball will be at the surface of the racket to not get stuck
                        ball.setBallX(P1.getRacket().getRacketX() + P1.getRacket().getRacketWidth() + 1);
                    }

                    // Right Racket
                    if (CollisionHandler.checkHit(ball, P2.getRacket())){
                        ball.wallBounce();

                        BounceCount++;
                        if (BounceCount >= game.getBall_speed_increase()) {
                            ball.increaseSpeed(1.3);
                            BounceCount = 0;
                        }

                        ball.setBallX(P2.getRacket().getRacketX() - P2.getRacket().getRacketWidth() - 1);
                    }

                // ---------------------
                // Ball ceiling bouncing
                // ---------------------
                    if (ball.getBallY() <= 0 || (ball.getBallY() + ball.getBallHeight() >= canvas.getHeight())) {
                        ball.ceilingBounce();

                        BounceCount++;
                        if (BounceCount >= game.getBall_speed_increase()) {
                            ball.increaseSpeed(1.3);
                            BounceCount = 0;
                        }

                    }
                }else {
                    ball.setBallX((canvas.getWidth()/2) - (ball.getBallWidth()/2));
                    ball.setBallY((canvas.getHeight()/2) - (ball.getBallHeight()/2));
                }
                // ---------------------
                // Goal Logic
                // ---------------------
                // Ball hits left goal
                if (ball.getBallX() < 0){
                    handleScoring(P2, canvas);
                    resetBall(canvas);
                }
                else if (ball.getBallX() > canvas.getWidth()){
                    handleScoring(P1, canvas);
                    resetBall(canvas);
                }

                canvas.Draw(P1,P2,ball);
            }
        };
        this.timer.start();
    }


    /**
     * Resets the ball to the center of the screen and pauses movement.
     * @param canvas The current game canvas to calculate center coordinates.
     */
    private void resetBall(Pong_Canvas canvas){
        gameStart = false;

        double centerX = (canvas.getWidth()/2) - (P1.getRacket().getRacketWidth()/2);
        double centerY = (canvas.getHeight()/2) - (ball.getBallHeight()/2);

        ball.setBallY(centerY);
        ball.setBallX(centerX);

        ball.wallBounce();
    }

    /**
     * Updates the score for the winning player and checks if the game has ended.
     * @param scoring_player The player who just scored a point.
     * @param canvas The canvas used for resetting the ball position.
     */
    private void handleScoring(Player scoring_player, Pong_Canvas canvas){

        scoring_player.incrementScore();

        if (scoring_player.getScore() >= game.getGame_target()) {
            Status_Message = scoring_player.getName() + " WINS THE GAME !";
            is_game_over = true;
        } else{
            Status_Message = scoring_player.getName() + " SCORES !";
        }

        resetBall(canvas);
    }

    //------------------------------
    // Getters / Setters
    //------------------------------

    /** @return The current Game configuration model. */
    public Game getGame() {
        return game;
    }

    /** @return Player 1 object. */
    public Player getP1(){
        return P1;
    }

    /** @return Player 2 object. */
    public Player getP2(){
        return P2;

    }

    /** @return The ball model. */
    public Ball getBall(){
        return ball;
    }

    /** @return The current status notification (e.g., "Player 1 Scores!"). */
    public String getStatus_Message(){
        return Status_Message;
    }
    /** @return True if a player has reached the game target score. */
    public boolean isGameOver(){
        return is_game_over;
    }

    /** @param is_game_over Sets the game over state. */
    public void setis_game_over(boolean is_game_over){
        this.is_game_over = is_game_over;
    }

    /** @param status_message Updates the text displayed on the HUD. */
    public void setStatus_Message(String status_message){
        this.Status_Message = status_message;
    }

    /** Resets the bounce counter used for speed increases. */
    public void resetBounceCount(){
        this.BounceCount = 0;
    }

    /**
     * Toggles the paused state of the game.
     * Stops or starts the {@link AnimationTimer} and updates the pause message.
     */
    public void togglePause(){
        if (is_game_over) return;

        if (isPaused){
            isPaused = false;
            this.Pause_Message = "";
            timer.start();
        }else{
            isPaused = true;
            this.Pause_Message = "Paused - ESC/P: resume | Q: save | L: Load";
            if(canvas != null){
                canvas.Draw(P1,P2,ball);
            }
            timer.stop();
        }
    }

    /**
     * Forces the canvas to redraw the current state once.
     * Useful for updating visuals while the {@link AnimationTimer} is stopped.
     */
    public void requestManualRedraw() {
        if (canvas != null) {
            // We manually tell the canvas to paint the current state
            // even though the AnimationTimer is stopped!
            canvas.Draw(P1, P2, ball);
        }
    }

    /** @return The message displayed when the game is paused. */
    public String getPauseMessage(){
        return this.Pause_Message;
    }

    /** @return The flag for if the game is currently paused or not. */
    public boolean isPaused(){
        return isPaused;
    }

    /**
     * Takes the data from the loaded GameState and applies it to the
     * live game objects currently on the screen.
     * @param state The serialized GameState object retrieved from a file.
     */
    public void applyLoadedState(GameState state) {
        // Restore Ball position and velocity
        getBall().setBallX(state.ballX);
        ball.setBallY(state.ballY);
        ball.setVelValues(state.ballXSpeed, state.ballYSpeed);

        // Restore Racket positions
        getP1().getRacket().setRacketY(state.leftRacketY);
        getP2().getRacket().setRacketY(state.rightRacketY);

        // Restore Scores and Player Data
        getP1().setScore(state.scoreP1);
        getP2().setScore(state.scoreP2);
        getP1().setName(state.p1Name);
        getP2().setName(state.p2Name);

        // Update Game Settings
        getGame().setGame_target(state.scoreLimit);


        // Ensure game remains paused and stop the game timer
        this.isPaused = true;
        this.gameStart = false;

        //  Ensure message carries over after load
        this.Pause_Message = "Game Loaded - ESC/P: Resume | Q: Save | L: Load";
        //  Force the view to redraw with the new positions
        requestManualRedraw();

        System.out.println("Success: Game state restored from file.");
    }
}
