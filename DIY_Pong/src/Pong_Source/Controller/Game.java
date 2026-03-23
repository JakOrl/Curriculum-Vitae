package Pong_Source.Controller;


/**
 * Represents the configuration and rules for a Pong match.
 * <p>
 * This class stores the win conditions and difficulty scaling settings,
 * such as the score required to win and how often the ball increases speed.
 * </p>
 * * @author Jakub Orlowski
 * @version 1.0
 */
public class Game {

    //------------------------------
    // Fields
    //------------------------------
    /** The number of points a player needs to win the match. */
    private int game_target;
    /** The number of successful paddle/wall bounces required to trigger a ball speed increase. */
    private int ball_speed_increase;
    //------------------------------
    // Constructor
    //------------------------------
    /**
     * Constructs a new Game configuration with default values.
     * Default target is 1 point; default speed increase interval is 5 bounces.
     */
    public Game(){
        this.game_target = 1;
        this.ball_speed_increase = 5;
    }
    //------------------------------
    // Getters / Setters
    //------------------------------

    /**
     * Sets the score required to end the game.
     * @param user_target The winning score threshold.
     */
    public void setGame_target(int user_target) {
        this.game_target = user_target;
    }

    /**
     * Sets the frequency of ball speed increases.
     * @param ball_speed_increase The number of bounces before the ball accelerates.
     */
    public void setBall_speed_increase(int ball_speed_increase) {
        this.ball_speed_increase = ball_speed_increase;
    }

    /**
     * @return The current winning score threshold.
     */
    public int getGame_target() {
        return this.game_target;
    }

    /**
     * @return The number of bounces required to increase ball speed.
     */
    public int getBall_speed_increase() {
        return this.ball_speed_increase;
    }

}
