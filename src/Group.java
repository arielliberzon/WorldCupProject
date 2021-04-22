import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class to represent groups (A,B,C,...) in group stage.
 */
public class Group {

    private Character name;
    private ArrayList<Team> teams;

    public Group(Character name, ArrayList<Team> teams) {
        this.name = name;
        this.teams = teams;
        simulate();
    }


    /**
     * Method simulates the games for all members in the group
     *
     */
    //TODO: Figure out correct id to pass
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

    public Character getName() {
        return name;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }
}
