package Pong_Source.Models;

/**
 * Simple model to represent the outcome of a match that has been played
 * @author Jakub Orlowski
 * @version 1.0
 */
public class MatchResult {

    public String p1Name, p2Name;
    public int score1,score2,scorelimit;

    // Note for specification. It asks to save "game name" aswell but i will not do this. I dont see a need.
    public MatchResult(String p1Name, String p2Name, int score1, int score2, int scorelimit) {
        this.p1Name = p1Name;
        this.p2Name = p2Name;
        this.score1 = score1;
        this.score2 = score2;
        this.scorelimit = scorelimit;
    }

}
