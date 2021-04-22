//import java.util.ArrayList;
//
///**
// * Confederation qualifier determines which teams are pre qualified to the world cup.
// * Additional games might be needed for confederations that have half spots.
// *
// * Confederation qualifier must receive the members ranked in order so that it can distribute them in a fair
// * manner. {@link #getPreQualifiedTeams()} must be called to simulate what happened and get the qualified teams.
// */
//public class ConfederationQualifier {
//
//    private int numberOfTeams;
//    private String name;
//    private double numberOfSpots;
//
//    //Members must be entered in order according to FIFA's rankings
//    //TODO: Make sure they arrive ordered.
//    private ArrayList<Team> membersRanked;
//
//    private ArrayList<Team> qualifiedMembers;
//    private int numberOfGroups;
//    private ArrayList<Group> groups;
//
//    public ConfederationQualifier(String name, int numberOfTeams, double numberOfSpots, ArrayList<Team> members, int numberOfGroups) {
//        this.numberOfTeams = numberOfTeams;
//        this.name = name;
//        this.numberOfSpots = numberOfSpots;
//        this.membersRanked = members;
//        this.numberOfGroups = numberOfGroups;
//    }
//
//    public ConfederationQualifier(String name, int numberOfTeams, double numberOfSpots, int numberOfGroups) {
//        this.numberOfTeams = numberOfTeams;
//        this.name = name;
//        this.numberOfSpots = numberOfSpots;
//        this.numberOfGroups = numberOfGroups;
//    }
//
//    public void setMembersRanked(ArrayList<Team> membersRanked){
//        this.membersRanked = membersRanked;
//    }
//
//    private void createGroups(){
//        Character groupName = 'A';
//        for(int i = 0; i < numberOfGroups; i++){
//            groups.add(new Group(groupName));
//            groupName++;
//        }
//    }
//
//    //2) Add all teams to groups in order so that groups are as even as possible
//    private void addTeamsToGroups(){
//        int currentGroup = 0;
//        for(int i = 0; i < numberOfTeams; i++){
//            Group current = groups.get(currentGroup);
//            current.addTeam(membersRanked.get(i));
//            currentGroup++;
//            if(currentGroup == numberOfGroups){
//                currentGroup = 0;
//            }
//        }
//    }
//
//    public ArrayList<Team> getPreQualifiedTeams(){
//        //Create the groups given by numberOfGroups A, B, C etc. Example 4: A, B, C, D
//        createGroups();
//
//        //Distribute the list of ranked teams into the groups. Example 16 teams -> Each group 4 teams
//        addTeamsToGroups();
//
//        //Make the groups have games against each other twice (Home and Away)
//        simulateGroups();
//
////        //If further games needed simulate them
////        simulateRestOfGames();
////
////        //Assign spots depending the number of spots available
////        assignSpots();
//
//        return qualifiedMembers;
//    }
//
//    private void simulateGroups() {
//        for(int i = 0; i < groups.size(); i++){
//            groups.get(i).simulate(true);
//        }
//    }
//
//
//}
