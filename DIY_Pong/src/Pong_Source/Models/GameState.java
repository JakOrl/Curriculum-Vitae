package Pong_Source.Models;

import java.io.Serializable;

/**
 * A Data Transfer Object (DTO) that represents a "Snapshot" of the game state.
 * <p>
 * This class implements {@link Serializable} to allow the current positions,
 * speeds, and scores of a game session to be persisted to a local file or
 * database. It acts as a bridge between the active game objects and
 * long-term storage.
 * </p>
 * * @author Jakub Orlowski
 * @version 1.0
 */
public class GameState implements Serializable {
    private static final long serialVersionUID = 1L;

    /** Ball positons and speeds */
    public double ballX, ballY, ballXSpeed, ballYSpeed;
    public double leftRacketY, rightRacketY;

    /** Player scores, names, and score limit */
    public int scoreP1, scoreP2;
    public int scoreLimit;
    public String p1Name, p2Name;


    /**
     * Constructs a new GameState by capturing data from live game components.
     *
     * @param ball  The active {@link Ball} model.
     * @param left  The left {@link Racket} model.
     * @param right The right {@link Racket} model.
     * @param p1    The {@link Player} 1 model.
     * @param p2    The {@link Player} 2 model.
     * @param limit The current winning score limit for the session.
     */
    public GameState(Ball ball, Racket left, Racket right, Player p1, Player p2, int limit) {
        this.ballX = ball.getBallX();
        this.ballY = ball.getBallY();
        this.ballXSpeed = ball.getBallXSpeed();
        this.ballYSpeed = ball.getBallYSpeed();
        this.leftRacketY = left.getRacketY();
        this.rightRacketY = right.getRacketY();
        this.scoreP1 = p1.getScore();
        this.scoreP2 = p2.getScore();
        this.p1Name = p1.getName();
        this.p2Name = p2.getName();
        this.scoreLimit = limit;
    }
}
