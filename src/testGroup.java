import java.util.ArrayList;
import java.util.Arrays;

// TODO: remove this from final submission
public class testGroup {
    public static void main(String[] args) {
        ArrayList<Team> teams = new ArrayList<>(Arrays.asList(
                new Team(1, "ARG", 0, "ARG", "SA"),
                new Team(1, "ARG", 0, "ARG", "SA"),
                new Team(1, "ARG", 0, "ARG", "SA"),
                new Team(1, "ARG", 0, "ARG", "SA")
        ));
        Group group = new Group('A', teams);
        Team testTeam = teams.get(0);
        System.out.println(testTeam + "\n" +
                "Wins: " + testTeam.groupWins() + "\n" +
                "Draws: " + testTeam.groupDraws() + "\n" +
                "Losses: " + testTeam.groupLosses() + "\n" +
                "Points: " + testTeam.groupPoints());
    }
}
