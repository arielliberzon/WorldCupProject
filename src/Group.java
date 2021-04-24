import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Class to represent groups (A,B,C,...) in group stage.
 */
public class Group {

    private char name;
    private ArrayList<Team> teams;

    public Group(char name, ArrayList<Team> teams) {
        this.name = name;
        this.teams = teams;
        simulate();
        // TODO: Handle tie-breakers when points are equal.
        teams.sort(Comparator.comparing(Team::groupPoints).reversed());
    }


    /**
     * Method simulates the games for all members in the group
     *
     */
    private void simulate() {
        //For all teams, play all the others once.
        for (int i = 0; i < teams.size(); i++) {
            Team currentTeam = teams.get(i);
            for (int j = i + 1; j < teams.size(); j++) {
                Team rival = teams.get(j);
                Game game = new Game(currentTeam, rival, true); // This simulates the game.
            }
        }
    }

    public char getName() {
        return name;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    /**
     * Gets the game information as a string
     * @return string representation of the group
     * @author Samuel Hernandez
     */
    @Override
    public String toString() {
        return "Group: "+name+"\n" +teams.toString()+"\n";

    }
}
