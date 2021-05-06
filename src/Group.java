import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;


/**
 * @author TODO: Add authors! (first and last name add the top of the method)
 * Class to represent groups (A,B,C,...) in group stage.
 */
public class Group {

    private char groupChar;
    private ArrayList<Team> teams;

    public Group(char groupChar, ArrayList<Team> teams) {
        this.groupChar = groupChar;
        this.teams = teams;
        simulate();
        // TODO: Handle tie-breakers when points are equal.
        Collections.sort(teams, Team.TeamGroupComparator);
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

    public char getGroupChar() {
        return groupChar;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    /**
     * Gets the game information as a string
     * @return Full group name (ex: "Group A")
     * @author Samuel Hernandez
     */
    @Override
    public String toString() {
        return "Group " + groupChar;

    }
}
