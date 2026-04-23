package Pong_Source.Models;
import Pong_Source.Controller.*;


    /**
     * Implementation of the Builder Design Pattern for the Game model.
     * <p>
     * This class provides a flexible way to construct a {@link Game} configuration
     * by allowing optional parameters to be set individually before instantiation.
     * </p>
     * @author Jakub Orlowski
     * @version 1.0
     */
public class GameBuilder {
    private int targetScore = 1;
    private int speedIncrease = 5;

        /**
         * Sets the maximum score required to win the match.
         * @param targetScore The winning score limit.
         * @return The current builder instance.
         */
        public GameBuilder setTargetScore(int targetScore) {
            this.targetScore = targetScore;
            return this;
        }

        /**
         * Sets how many bounces are required before the ball increases speed.
         * @param speedIncrease Number of bounces.
         * @return The current builder instance.
         */
        public GameBuilder setSpeedIncrease(int speedIncrease) {
            this.speedIncrease = speedIncrease;
            return this;
        }

        /**
         * Finalizes the construction and returns a configured Game object.
         * @return A fully initialized {@link Game} instance.
         */
        public Game build(){
            Game game = new Game();
            game.setGame_target(this.targetScore);
            game.setBall_speed_increase(this.speedIncrease);
            return game;
        }
}
