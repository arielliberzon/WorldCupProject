import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
 * @author TODO: Add authors! (first and last name add the top of the method)
 * Groups class is used to represent groups (A,B,C,...) in group stage.
 * Each group has a list of teams that are going to be matched against each other in this stage.
 * Group class has the capability to simulate the group stage for this group.
 */
public class Group {

    //The letter representing the group's name (A, B, C... etc)
    private char groupName;

    //The list of teams in the group
    private ArrayList<Team> teams;

    /**
     * Constructs a group with given teams and simulates the group stage.
     * After simulating teams are sorted in descending order (team with higher points at index 0).
     * @param groupName the letter that represents the name of the group
     * @param teams the teams in the group
     */
    public Group(char groupName, ArrayList<Team> teams) {
        this.groupName = groupName;
        this.teams = teams;
        simulate();
        Collections.sort(teams, Team.TeamGroupComparator);
    }

    /**
     * Default constructor: constructs a group with no parameters.
     */
    public Group() {
    }

    /** @author Saif Masoud
     * Method simulates the games for all members in the group
     */
    private void simulate() {
        //For all teams, play all the others once.
        for (int i = 0; i < teams.size(); i++) {
            Team currentTeam = teams.get(i);
            for (int j = i + 1; j < teams.size(); j++) {
                Team rival = teams.get(j);
                new Game(currentTeam, rival, true); // This simulates the game.
            }
        }
    }

    /**
     * Gets the name of the group
     * @return the char representing the name of the group
     */
    public char getGroupName() {
        return groupName;
    }

    /**
     * Sets the name of the group
     * @param groupName the char representing the name of the group
     */
    public void setGroupName(char groupName) {
        this.groupName = groupName;
    }

    /**
     * Gets the teams in the group
     * @return the teams in the group
     */
    public ArrayList<Team> getTeams() {
        return teams;
    }

    /**
     * Sets the teams in the group
     * @param teams the teams in the group
     */
    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }

    /**
     * String representation of group
     * @return the name of the groups plus group. Example: "Group A"
     */
    @Override
    public String toString() {
        return "Group " + groupName;

    }

    /**
     * Method checks whether or not an object is equal to this
     * @param o the object to test for equality
     * @return true if they are equal, else false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Group)) return false;
        Group group = (Group) o;
        return groupName == group.groupName &&
                Objects.equals(teams, group.teams);
    }

    /**
     * Gets the hashcode for the team
     * @return the hash code of team
     */
    @Override
    public int hashCode() {
        return Objects.hash(groupName, teams);
    }
}
