import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Team {

    private String name;
    private int fifaRank;

    // The order of games matters, first 3 are group stage, followed by 16,quarters,semis,final/3rdPlace.
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

    /**
     * @return Gets the number of wins in the group stage.
     */
    public int groupWins() {
        int wins=0;
        for (int i=0; i<3; i++) {
            Game curGroupGame = games.get(i);
            if (curGroupGame.getWinner() == this) wins += 1;
        }
        return wins;
    }
    /**
     * @return Gets the number of wins in the group stage.
     */
    public int groupDraws() {
        int draws=0;
        for (int i=0; i<3; i++) {
            Game curGroupGame = games.get(i);
            if (curGroupGame.getWinner() == null) draws += 1;
        }
        return draws;
    }
    /**
     * @return Gets the number of wins in the group stage.
     */
    public int groupLosses() {
        int losses=0;
        for (int i=0; i<3; i++) {
            Game curGroupGame = games.get(i);
            Team winner = curGroupGame.getWinner();
            if (winner != this && winner != null) losses += 1;
        }
        return losses;
    }
    public int groupPoints() {
        return 3*groupWins() + 1*groupDraws();
    }


    @Override
    public String toString() {
        return "Team name: " + name;
    }

    public String getName() {
        return name;
    }
}