package Pong_Source.Models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the {@link CollisionHandler} logic.
 * <p>
 * This suite verifies that the AABB (Axis-Aligned Bounding Box) intersection
 * logic correctly identifies when the ball is overlapping a racket
 * and when it is clearly outside the racket's bounds.
 * </p>
 * @author Jakub Orlowski
 */
public class CollissionHandlerTest {

    /**
     * Verifies that a hit is detected when the ball and racket coordinates overlap.
     * <p>
     * Setup:
     * <ul>
     * <li>Ball at (100, 100) with size 20x20</li>
     * <li>Racket at (110, 100) with size 20x100</li>
     * </ul>
     * Expected: {@link CollisionHandler#checkHit} returns {@code true}.
     */
    @Test
    public void test_Ball_hits_Racket() {
        Ball ball = new Ball(100,100,20,20);
        Racket racket = new Racket(110,100,20,100);

        assertTrue(CollisionHandler.checkHit(ball,racket), "The ball should be hitting racket");
    }

    /**
     * Verifies that no hit is detected when objects are in completely different screen areas.
     * <p>
     * Setup:
     * <ul>
     * <li>Ball at (0, 0)</li>
     * <li>Racket at (200, 200)</li>
     * </ul>
     * Expected: {@link CollisionHandler#checkHit} returns {@code false}.
     */
    @Test
    public void test_Ball_miss_Racket() {
        Ball ball = new Ball(0,0,20,20);
        Racket racket = new Racket(200,200,20,100);

        assertFalse(CollisionHandler.checkHit(ball,racket), "The ball is too far to be hitting racket");
    }

}