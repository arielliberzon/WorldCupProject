import java.util.ArrayList;
import java.util.Arrays;

public class testGroup {
    public static void main(String[] args) {
        ArrayList<Team> teams = new ArrayList<>(Arrays.asList(
                new Team("ARG", 1),
                new Team("BRA", 1),
                new Team("COL", 1),
                new Team("DEN", 1)
        ));
        Group group = new Group('A', teams);
        System.out.println();
    }
}
