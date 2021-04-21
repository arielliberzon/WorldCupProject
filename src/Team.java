public class Team {

    private int id;
    private String name;
    private int fifaRank;
    private int points;


    public Team(int id, String name, int fifaRank, int points) {
        this.id = id;
        this.name = name;
        this.fifaRank = fifaRank;
        this.points = points;
    }

    public int getFifaRank() {
        return fifaRank;
    }

    public int getPoints(){ return points; }

    @Override
    public String toString() {
        return "Team name: " + name;
    }

    public String getName() {
        return name;
    }
}