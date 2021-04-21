import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Team {

    private String name;
    private int fifaRank;
    private ArrayList<Game> games;


    public Team(String name, int fifaRank) {
        this.name = name;
        this.fifaRank = fifaRank;
        games = new ArrayList<>();
    }

    public int getFifaRank() {
        return fifaRank;
    }

    public void addGame(Game game) {
        games.add(game);
    }


    @Override
    public String toString() {
        return "Team name: " + name;
    }

    public String getName() {
        return name;
    }
}