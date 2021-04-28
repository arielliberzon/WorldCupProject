import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Simulator class is designed to simulate the world cup in phases so that they can be simulated by steps by the GUI
 * Upon simulator being constructed the group stage is simulated. After that, individuals methods should be called to
 * simulate each round in this order:
 * {@link #simulateRoundOfSixteen()}
 * {@link #simulateQuarters()}
 * {@link #simulateSemis()}
 * {@link #simulateFinalAndThirdPlace()}
 * @author Saif Masoud, and Samuel Hernandez
 * TODO: Delete print statments when done with coding
 */
public class Simulator {

    // 8 groups with 4 teams each.
    private ArrayList<Group> groups;

    // The 32 participating teams.
    private ArrayList<Team> worldCupTeams;

    //TeamInfo object to get the teams info
    private TeamInfo teamInfo;

    //List of games played in the round of 16
    private ArrayList<Game> roundOf16Games;

    //List of games played in quarters
    private ArrayList<Game> quartersGames;

    //List of games played in semis
    private ArrayList<Game> semisGames;

    //First element is the final, second element is the game for the third place
    private ArrayList<Game> finalAndThirdGames;

    /**
     * TODO: When exception of team is figured out remove exception from constructor
     * Default constructor: Constructs a simulator, gets the world cup teams, and simulates the group stage
     */
    public Simulator() {
        //teamInfo = new TeamInfo();
        worldCupTeams = new ArrayList<>();
        //WorldCupTeams();
        //simulateGroups();
    }

    private void loadWorldCupTeams() {
        // This loads an arbitrary selection of 32 teams.
        // TODO: consider a method to select the 32 teams more realistically.
        int i=0;
        for (Team team : teamInfo.getTeamMap().values()) {
            worldCupTeams.add(team);
            i += 1;
            if (i==32) break;
        }
    }

    /**
     * Creates & simulates the 8 groups of the group-stage.
     */
    public void simulateGroups() {
        groups = new ArrayList<>();
        int nGroups = 8;
        int nTeams = worldCupTeams.size();
        int teamsPerGroup = nTeams / nGroups;

        // Dividing the teams over the groups.
        int teamIndex = 0;
        char curGroupChar = 'A';
        for (int i=0; i<nGroups; i++) {
            ArrayList<Team> curGroupTeams = new ArrayList<>();
            for (int j=0; j<teamsPerGroup; j++) {
                curGroupTeams.add(worldCupTeams.get(teamIndex));
                teamIndex += 1;
            }
            // Creating the group
            Group group = new Group(curGroupChar, curGroupTeams);
            groups.add(group);
            curGroupChar += 1;
        }
    }

    /**
     * Sets up the 8 games for round of sixteen.
     * Top team from group A plays against runner-up from group B.
     * Runner-up from group A plays against top team of group B.
     * Similarly C vs D, E vs F, G vs H
     * @return the games to be played
     * @author Samuel Hernandez and Saif Masoud
     */
    public ArrayList<Game> simulateRoundOfSixteen() {
        roundOf16Games = new ArrayList<Game>();
        for(int i = 0; i < 8; i++){

            //Get top ranking team in current group
            Team top = groups.get(i).getTeams().get(0);

            //Get runner up team from next group for A, C, E, G. OR from previous group for B, D, F, H
            Team runnerUp;
            if(i % 2 == 0)
                runnerUp = groups.get(i+1).getTeams().get(1);
            else
                runnerUp = groups.get(i-1).getTeams().get(1);

            roundOf16Games.add(new Game(top, runnerUp, false));
        }

        System.out.println("\nRounds of sixteen results");
        roundOf16Games.forEach(game -> System.out.println(game.getFinalScoreString()));
        return roundOf16Games;
    }

    /**
     * Upon being called simulates the quarter games and returns the games that were played.
     * Sets up the games by taking the winners of the first two round of sixteen winners, then the next two
     * and so on. (0 vs 1. 2 vs 3, 4 vs 5, 6 vs 7)
     * @return the array list of games that were played
     * @author Samuel Hernandez and Saif Masoud
     */
    public ArrayList<Game> simulateQuarters() {
        quartersGames = new ArrayList<>();
        int next = 0;
        for (int i = 0; i < 4; i++) {
            Team a = roundOf16Games.get(next).getWinner();
            Team b = roundOf16Games.get(next+1).getWinner();
            quartersGames.add(new Game(a, b, false));
            next += 2;
        }
        System.out.println("\nQuarters results");
        quartersGames.forEach(game -> System.out.println(game.toString()));
        return quartersGames;
    }

    /**
     * Upon being called simulates the semi final games and returns the games that were played.
     * Winner of quarters game 0 vs game 1, 2 vs 3.
     * @return the array list of games that were played
     * @author Samuel Hernandez and Saif Masoud
     */
    public ArrayList<Game> simulateSemis() {
        semisGames = new ArrayList<>();
        Game game1 = new Game(quartersGames.get(0).getWinner(), quartersGames.get(1).getWinner(), false);
        Game game2 = new Game(quartersGames.get(2).getWinner(), quartersGames.get(3).getWinner(), false);
        semisGames.add(game1);
        semisGames.add(game2);
        System.out.println("\nSemis results");
        semisGames.forEach(game -> System.out.println(game.toString()));
        return semisGames;
    }

    /**
     * Upon being called simulates the final and third place games and returns the games that were played.
     * Winner of quarters game 0 vs game 1. Loser of game 0 vs game 1.
     * @return the array list of games that were played
     * @author Samuel Hernandez and Saif Masoud
     */
    public ArrayList<Game> simulateFinalAndThirdPlace() {
        finalAndThirdGames = new ArrayList<>();
        Game finalGame = new Game(semisGames.get(0).getWinner(), semisGames.get(1).getWinner(), false);
        Game third = new Game(semisGames.get(0).getLoser(), semisGames.get(1).getLoser(), false);
        finalAndThirdGames.add(finalGame);
        finalAndThirdGames.add(third);
        System.out.println("\nFinal and third results");
        finalAndThirdGames.forEach(game -> System.out.println(game.toString()));
        return finalAndThirdGames;
    }

    /**
     * Per requested by front end:
     * Method returns the teams in the order they should appear in the GUI:
     * Groups from left to right, top to bottom.
     * @return the ordered list of teams that made it into the round of 16
     * @author Samuel Hernandez
     */
    public ArrayList<Team> getTeamsInOrderInRoundOfSixteen(){
        ArrayList<Team> teamsOnRoundOf16 = new ArrayList<>();
        for(int i = 0; i < 8; i++){

            //Add the team on top  of ranking team in current group
            Team leader = groups.get(i).getTeams().get(0);
            System.out.println(leader.getCountry() + " as group leader");

            //Get runner up team from next group for A, C, E, G. OR from previous group for B, D, F, H
            Team runnerUp;
            if(i % 2 == 0)
                runnerUp = groups.get(i+1).getTeams().get(1);
            else
                runnerUp = groups.get(i-1).getTeams().get(1);

            System.out.println(runnerUp.getCountry() + " as group runner up");
            teamsOnRoundOf16.add(leader);
            teamsOnRoundOf16.add(runnerUp);
        };
        return teamsOnRoundOf16;
    }

    /**
     * Gets the array with the 8 groups in the world cup A, B ... H
     * @return the list of groups
     */
    public ArrayList<Group> getGroups() {
        return groups;
    }

    /**
     * Gets the teams that qualified to the world cup
     * @return the list of qualified teams
     */
    public ArrayList<Team> getQualifiedTeams() {
        return worldCupTeams;
    }

    @Override
    public String toString() {
        return "Simulator{" +
                "groups=" + groups +
                ", roundOf16Games=" + roundOf16Games +
                ", quartersGames=" + quartersGames +
                ", semisGames=" + semisGames +
                ", finalAndThirdGames=" + finalAndThirdGames +
                '}';
    }

    //Equals and setters do not make sense so they are not implemented on purpose

    /**
     * @author Alexander and Michael
     * This method is used to simulate the Qualifiers. Each confederation will have their respective number of group
     * stage slots. Each arraylist is filled up by the method getQualified().
     * @return An ArrayList<Team> that contains 32 teams that will move on to the group stage.
     */
    public ArrayList<Team> getQualifiedTeams(TeamInfo teamInfo) {

        ArrayList<Team> output = new ArrayList<>();

        //Create a list for each confederation
        ArrayList<Team> UEFA = new ArrayList<Team>();
        ArrayList<Team> CONMEBOL = new ArrayList<Team>();
        ArrayList<Team> CONCACAF = new ArrayList<Team>();
        ArrayList<Team> CAF = new ArrayList<>();
        ArrayList<Team> AFC = new ArrayList<>();
        ArrayList<Team> OFC = new ArrayList<>();

        /* For the confederations that are allocated a different number of teams every year, they are added to a
         * list that 'simulate' games between those teams in order to determine who makes it out of qualifiers
         */
        ArrayList<Team> extras = new ArrayList<>();

        //Looping through the teams and assigning them to their respective confederation team lists
        for(Team t : teamInfo.getTeamMap().values()){
            //Since England is the host country, they are later added in automatically
            if(t.getConfederation().equals("UEFA") && !t.getCountry().equals("England")) //12 Spots
                UEFA.add(t);
            if(t.getConfederation().equals("CONMEBOL")) //4.5 Spots
                CONMEBOL.add(t);
            if(t.getConfederation().equals("CONCACAF")) //3.5 Spots
                CONCACAF.add(t);
            if(t.getConfederation().equals("CAF")) //5 Spots
                CAF.add(t);
            if(t.getConfederation().equals("AFC")) //4.5 Spots
                AFC.add(t);
            if(t.getConfederation().equals("OFC")) //0.5 Spots
                OFC.add(t);
        }

        //Set each list to the qualified list that is produced from the helper method.
        UEFA = getQualified(UEFA, 13);
        Collections.sort(UEFA);
        CONMEBOL = getQualified(CONMEBOL, 5);
        Collections.sort(CONMEBOL);
        CONCACAF = getQualified(CONCACAF, 4);
        Collections.sort(CONCACAF);
        CAF = getQualified(CAF, 5);
        Collections.sort(CAF);
        AFC = getQualified(AFC, 5);
        Collections.sort(AFC);
        OFC = getQualified(OFC, 1);

        //Adding the extra teams to the extra list then removing them from their respective lists
        extras.add(CONMEBOL.get(4));
        CONMEBOL.remove(4);
        extras.add(CONCACAF.get(3));
        CONCACAF.remove(3);
        extras.add(AFC.get(4));
        AFC.remove(4);
        extras.add(OFC.get(0));
        OFC.remove(OFC.get(0));

        //Add all the teams to the output arraylist
        output.add(teamInfo.getTeam("ENG"));
        output.addAll(getQualified(extras,2));
        output.addAll(UEFA);
        output.addAll(CONMEBOL);
        output.addAll(CONCACAF);
        output.addAll(AFC);
        output.addAll(OFC);
        output.addAll(CAF);

        worldCupTeams = output;
        return output;
    }

    /**
     * @author Alexander and Michael
     * Helper function to the Qualifiers() method. This returns an arraylist that contains the teams that qualify to
     * group stage. Using the Java.util Random(), it will determine which team will qualify to the group stage with the
     * higher seeded team having a greater chance.
     * @param input Confederation team list
     * @param spots The number of spots that allocated to them in the group stage
     * @return An ArrayList<Team> that contains the qualified teams
     */

    private ArrayList<Team> getQualified(ArrayList<Team> input, int spots){
        Random random = new Random();
        Collections.sort(input);
        ArrayList<Team> output = new ArrayList<>();


        int indexTop = 0;
        int indexBottom = input.size() -1;

        //Loop that loops until the right amount of teams are selected to move on
        while(spots > 0){
            //Determine the range for Random()
            int sum = 0;

            //Select a random team from the input list of teams
            Team teamOne = input.get(indexTop);
            sum += teamOne.getTotalPoints();

            //Select the second team from the input list of teams
            Team teamTwo = input.get(indexBottom);
            sum += teamTwo.getTotalPoints();

            //Generate a number in between the range of 1 and the total number of points between team one and team two
            int randomNumber = random.nextInt(sum) + 1;
            //This is similar to the logic in simulateSection() in Game.java
            if(randomNumber <= teamOne.getTotalPoints()) {
                input.remove(teamOne);
                output.add(teamOne);
            }
            else {
                input.remove(teamTwo);
                output.add(teamTwo);
            }
            spots--;

        }
        return output;
    }




}
