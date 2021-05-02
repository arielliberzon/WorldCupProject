import java.util.ArrayList;
import java.util.Arrays;

// TODO: remove this from final submission
public class testGroup {
    public static void main(String[] args) {
        // Will need to comment out setImage in "Team"
        ArrayList<Team> teams = new ArrayList<>(Arrays.asList(
                new Team(1, "ARG", 0, "ARG", "SA"),
                new Team(1, "ARG", 0, "ARG", "SA"),
                new Team(1, "ARG", 0, "ARG", "SA"),
                new Team(1, "ARG", 0, "ARG", "SA")
        ));
        Group group = new Group('A', teams);
        Team testTeam = teams.get(0);
        System.out.println(testTeam + "\n" +
                "Wins: " + testTeam.getGroupWins() + "\n" +
                "Draws: " + testTeam.getGroupDraws() + "\n" +
                "Losses: " + testTeam.getGroupLosses() + "\n" +
                "Points: " + testTeam.groupPoints());
    }
}
