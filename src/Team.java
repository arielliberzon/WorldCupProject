import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team)) return false;
        Team team = (Team) o;
        return id == team.id &&
                fifaRank == team.fifaRank &&
                points == team.points &&
                Objects.equals(name, team.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, fifaRank, points);
    }
}