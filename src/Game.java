/**
 * Stores game data and simulates an individual match.
 */
public class Game {
    private int id;
    private Team t1;
    private Team t2;
    private boolean canBeDraw;
    private int[] score;

    public Game(int id, Team t1, Team t2, boolean canBeDraw) {
        this.id = id;
        this.t1 = t1;
        this.t2 = t2;
        this.canBeDraw = canBeDraw;
        // TODO: Simulate game result. (Consider if it canBeDraw or not.)
    }

    public FinishType getFinishType() {
        return FinishType.NORMAL_TIME;
    }

    enum FinishType {
        EXTRA_TIME, PENALTIES, NORMAL_TIME
    }
}
