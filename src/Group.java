import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class to represent groups (A,B,C,...) in group stage.
 */
public class Group {

    private Character name;
    private ArrayList<Team> teams;

    //This map will keep track of the points each team have.
    private HashMap<Team, Integer> pointKeeping;

    public Group(Character name) {
        this.name = name;
    }

    public Group(Character name, ArrayList<Team> teams) {
        this.name = name;
        this.teams = teams;
    }

    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }

    public void addTeam(Team team){
        teams.add(team);
    }


    /**
     * Method simulates the games for all members in the group
     * @param twoGames if true all plays will be played twice, as the home team and the visitor team
     *
     */
    //TODO: Figure out correct id to pass
    public void simulate(boolean twoGames) {
        populateMap();
        //For all teams
        for(int i = 0; i < teams.size(); i++){
            Team currentTeam = teams.get(i);                    //Play this team against all the rest

            //Play every other team in the group but not self
            for(int j = 0; j < teams.size(); j++){
                Team rival = teams.get(j);
                if(! currentTeam.equals(rival) ){                //Skip current Team
                    Game game = new Game(0, currentTeam, rival, true);
                }
            }
        }
    }

    /**
     * Initializes the map with all points for all teams = 0
     */
    private void populateMap() {
        for(Team team: teams){
            pointKeeping.put(team, 0);
        }
    }
}
