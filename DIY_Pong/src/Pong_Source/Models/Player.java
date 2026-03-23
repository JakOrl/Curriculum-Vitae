package Pong_Source.Models;


/**
 * Represents a participant in the Pong game.
 * <p>
 * This class maintains the state of a player, including their identity,
 * current score, and the {@link Racket} object they control.
 * </p>
 * @author Jakub Orlowski
 * @version 1.0
 */
public class Player {
    //------------------------------
    // Fields
    //------------------------------
    /** The paddle object associated with this player. */
    private Racket racket;

    /** The display name of the player. */
    private String name;

    /** The current number of points earned by the player. */
    private int score;

    //------------------------------
    // Constructor
    //------------------------------
    /**
     * Constructs a new Player with a specific racket, name, and starting score.
     * @param racket The {@link Racket} instance this player will control.
     * @param name   The name to be displayed on the scoreboard.
     * @param score  The initial score (usually 0).
     */
    public Player(Racket racket, String name, int score) {
        this.racket = racket;
        this.name = name;
        this.score = score;
    }

    //------------------------------
    // Getters / Setters
    //------------------------------

    /**
     * Increases the player's current score by exactly one point.
     */
    public void incrementScore() {
        this.score++;
    }

    /** @param name The new display name for the player. */
    public void setName(String name) {
        this.name = name;
    }

    /** @return The player's current total score. */
    public int getScore() {
        return this.score;
    }

    /** @return The player's display name. */
    public String getName() {
        return this.name;
    }

    /** @return The {@link Racket} object assigned to this player. */
    public Racket getRacket() {
        return racket;
    }

    /**
     * Manually sets the player's score.
     * @param score The new score value (useful for game resets).
     */
    public void setScore(int score){
        this.score = score;
    }
}
