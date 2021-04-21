import java.util.ArrayList;

public class ConfederationQualifier {

    private int numberOfTeams;
    private String name;
    private double numberOfSpots;
    private ArrayList<Team> members;
    private ArrayList<Team> qualifiedMembers;

    public ConfederationQualifier(int numberOfTeams, String name, double numberOfSpots) {
        this.numberOfTeams = numberOfTeams;
        this.name = name;
        this.numberOfSpots = numberOfSpots;
    }

    public ConfederationQualifier(int numberOfTeams, String name, double numberOfSpots, ArrayList<Team> members) {
        this.numberOfTeams = numberOfTeams;
        this.name = name;
        this.numberOfSpots = numberOfSpots;
        this.members = members;
    }

    public void setMembers(ArrayList<Team> members){
        this.members = members;
    }

    public void simulateGetQualifiedSpots(){
        for(int i = 0; i < members.size(); i++){

        }
    }


}
