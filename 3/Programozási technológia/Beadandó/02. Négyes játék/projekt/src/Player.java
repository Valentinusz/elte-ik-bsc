/**
 * @author Valentinusz / KDPHNI / Boda Bálint
 */

public class Player {
    /**
     * Current score of the player. Natural number.
     */
    private int score;

    /**
     * Constructor for class.
     */
    public Player() {
        this.score = 0;
    }

    /**
     * @return score of the player
     */
    public int getScore() {
        return score;
    }

    /**
     * Increments the score field by one.
     */
    public void incrementScore() {
        this.score++;
    }
}
