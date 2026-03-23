package Pong_Source.Models;

/**
 * Utility class for handling collision detection physics.
 * <p>
 * This class provides static methods to determine if game objects are intersecting.
 * It implements <b>Axis-Aligned Bounding Box (AABB)</b> collision detection,
 * which is a computationally efficient way to check for overlaps between
 * rectangular objects that are not rotated.
 * </p>
 * @author Jakub Orlowski
 * @version 1.0
 */
public class CollisionHandler {


    /**
     * Detects if a collision has occurred between a {@link Ball} and a {@link Racket}.
     * <p>
     * The logic checks if the bounding box of the ball overlaps with the
     * bounding box of the racket across four boundaries:
     * <ul>
     * <li>Ball's left edge is to the left of the racket's right edge.</li>
     * <li>Ball's right edge is to the right of the racket's left edge.</li>
     * <li>Ball's top edge is above the racket's bottom edge.</li>
     * <li>Ball's bottom edge is below the racket's top edge.</li>
     * </ul>
     * If all four conditions are true, an intersection exists.
     * </p>
     * * @param b The ball object to check.
     * @param r The racket object to check.
     * @return {@code true} if the ball and racket are overlapping; {@code false} otherwise.
     */
    public static boolean checkHit(Ball b, Racket r){
        // Checking if:
        // 1. LEFT side of BALL is to the LEFT of  RIGHT Racket
        // 2. RIGHT side of BALL is to the RIGHT of LEFT Racket
        // 3. TOP side of BALL is to the BOTTOM of Racket
        // 4. BOTTOM side of BALL is to the TOP of Racket
        return b.getBallX() < r.getRacketX() + r.getRacketWidth() &&
                b.getBallX() + b.getBallWidth() > r.getRacketX() &&
                b.getBallY() < r.getRacketY() + r.getRacketHeight() &&
                b.getBallY() + b.getBallHeight() > r.getRacketY();

    }


}
