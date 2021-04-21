import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Stores game data and simulates an individual match.
 */
public class Game {
    private int id;
    private Team t1;
    private Team t2;
    private boolean canBeDraw;
    private int[] score;
    private static AtomicInteger idCounter = new AtomicInteger(); // generates IDs.

    public Game(int id, Team t1, Team t2, boolean canBeDraw) {
        this.id = id;
        this.t1 = t1;
        this.t2 = t2;
        this.canBeDraw = canBeDraw;
        simGame();
    }

    public FinishType getFinishType() {
        return FinishType.NORMAL_TIME;
    }

    private void simGame() {
        // TODO: Simulate game result. (Consider if it canBeDraw or not.)
        score = new int[]{2, 0};
    }

    public static Game dummyGame() {
        Team t1 = new Team(0, "TeamA", 0);
        Team t2 = new Team(0, "TeamB", 0);
        return new Game(idCounter.getAndIncrement(), t1, t2, false);
    }

    enum FinishType {
        EXTRA_TIME, PENALTIES, NORMAL_TIME
    }
}

