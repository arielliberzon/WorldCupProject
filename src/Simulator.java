import java.util.ArrayList;

public class Simulator {
    private ArrayList<Group> groups; // 8 groups with 4 teams each.
    private int curGameId; // Incremented after every game? Used to access each specific game.

    public ArrayList<Game> RoundOfSixteen() {
        // TODO:
        ArrayList<Game> games = new ArrayList<>();
        for (int i=0; i<16; i++) {
            games.add(Game.dummyGame());
        }
        return games;
    }
    public ArrayList<Game> Quarters() {
        // TODO:
        ArrayList<Game> games = new ArrayList<>();
        for (int i=0; i<8; i++) {
            games.add(Game.dummyGame());
        }
        return games;
    }
    public ArrayList<Game> Semis() {
        // TODO:
        ArrayList<Game> games = new ArrayList<>();
        for (int i=0; i<4; i++) {
            games.add(Game.dummyGame());
        }
        return games;
    }
    public ArrayList<Game> FinalAndThirdPlace() {
        // TODO:
        ArrayList<Game> games = new ArrayList<>();
        for (int i=0; i<2; i++) {
            games.add(Game.dummyGame());
        }
        return games;
    }
    public ArrayList<Group> getGroups() {
        return groups;
    }
}
