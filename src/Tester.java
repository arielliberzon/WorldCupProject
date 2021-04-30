import java.io.IOException;
import java.util.ArrayList;

// TODO: remove this from final submission
public class Tester {
    public static void main(String[] args)  {
        Simulator simulator = new Simulator();
        ArrayList<Team> teamList = simulator.getQualifiedTeams();
        for(int i = 0; i < teamList.size(); i++){
            System.out.print(i + " ");
            System.out.println(teamList.get(i).getCountry());
        }
    }
}