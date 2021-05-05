import java.util.*;

// TODO: Rearrange methods so they make sense chronologically (ex: qualifiers, group, knockout)
// TODO: Add @author at the top of each comment that relates to YOU
/**
 * Simulator class is designed to simulate the world cup in phases so that they can be simulated by steps by the GUI
 * Upon simulator being constructed the group stage is simulated. After that, individuals methods should be called to
 * simulate each round in this order:
 * {@link #simulateRoundOfSixteen()}
 * {@link #simulateQuarters()}
 * {@link #simulateSemis()}
 * {@link #simulateFinalAndThirdPlace()}
 * @author Saif Masoud, Michael Skuncik, Ariel Liberzon, Alexader Tang, and Samuel Hernandez
 */
public class Simulator {

    //The 8 groups with 4 teams each.
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
     * Default constructor: Constructs a simulator, gets the world cup teams, and simulates the group stage
     */
    public Simulator() {
        teamInfo = new TeamInfo();
        worldCupTeams = new ArrayList<>();
        setQualifiedTeams(teamInfo);                    // This loads  worldCupTeams field.
        simulateGroups();
    }

    /**
     * Parameter constructor: Builds a simulator with given qualified teams
     * @param qualifiedTeams the teams that qualified to thw world cup
     */
    public Simulator(ArrayList<Team> qualifiedTeams){
        worldCupTeams = qualifiedTeams;
        simulateGroups();
    }

    /**
     * Creates & simulates the 8 groups of the group-stage.
     * @author Saif Masoud
     */
    public void simulateGroups() {
        groups = new ArrayList<>();
        int nGroups = 8;
        int nTeams = worldCupTeams.size();
        int teamsPerGroup = nTeams / nGroups;
        worldCupTeams = sortTeamsIntoGroups(worldCupTeams, nTeams, nGroups);

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
        int index = 0;
        for (int i = 0; i < 4; i++) {
            Team a = roundOf16Games.get(index).getWinner();
            Team b = roundOf16Games.get(index + 2).getWinner();
            quartersGames.add(new Game(a, b, false));
            if(index == 1)
                index = 4;
            else
                index++;
        }
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
        Game game1 = new Game(quartersGames.get(0).getWinner(), quartersGames.get(2).getWinner(), false);
        Game game2 = new Game(quartersGames.get(1).getWinner(), quartersGames.get(3).getWinner(), false);
        semisGames.add(game1);
        semisGames.add(game2);
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
        return finalAndThirdGames;
    }

    /**
     * Method sorts qualified teams into groups like in the real world cup:
     * 32 teams sorted into 4 level bowls depending on their ranking.
     * So bowl one has top 1-8 teams, bowl two 9-16 and so on.
     * Then randomly teams are placed from each bowl into one group.
     * Capability to change number of groups, or number of teams if it were to change.
     * @author Samuel Hernandez
     */
    private ArrayList<Team> sortTeamsIntoGroups(ArrayList<Team> worldCupTeams, int nTeams, int nGroups){
        Collections.sort(worldCupTeams);                            //Make sure world cup teams are sorted by rankings
        ArrayList<Team> orderedList = new ArrayList<>();            //This array list will have the ordered list
        ArrayList<ArrayList<Team>> bowls = new ArrayList();         //Holds all the bowls

        //Create the right amount of bowls: : For 32 teams and 8 groups = 4 bowls
        int numBowls = nTeams/nGroups;
        for(int i = 0; i < numBowls; i++)
            bowls.add(new ArrayList<>());

        //Divide all the teams into bowls according to their ranking
        int currentBowl = 0;
        for(int i = 0; i < nTeams; i++){
            Team team = worldCupTeams.get(i);
            bowls.get(currentBowl).add(team);
            if((i+1) % nGroups == 0)                                //Change bowl when it is complete
                currentBowl++;
        }

        //Now all bowls have ranked teams. Randomly select one and add it to ordered list
        Random rand = new Random();
        for(int i = 0; i < nGroups; i++) {                          //Pick teams for each group
            for(int j = 0; j < bowls.size(); j++) {                 //Pick a team from each bowl
                int randomNum = rand.nextInt(bowls.get(j).size());
                orderedList.add(bowls.get(j).remove(randomNum));
            }
        }
        return orderedList;
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

            //Get runner up team from next group for A, C, E, G. OR from previous group for B, D, F, H
            Team runnerUp;
            if(i % 2 == 0)
                runnerUp = groups.get(i+1).getTeams().get(1);
            else
                runnerUp = groups.get(i-1).getTeams().get(1);

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
     * Gets the team map
     * @return the map containing the teams
     */
    public HashMap<String, Team> getTeamMap() {
        return teamInfo.getTeamMap();
    }


    //Qualifier methods

    /**
     * Method gets the qualified teams for a confederation.
     * Calls recursive method {@link #getQualifiedRec(ArrayList, int, int, int)} to deal with the logic
     * @param confTeams the confederation to get the qualified teams for
     * @param spots the number of available spots
     * @return the qualified list of teams
     * @author Samuel Hernandez
     */
    private ArrayList<Team>  setQualifiedTeams(ArrayList<Team> confTeams , int spots){
        return getQualifiedRec(confTeams,  spots, 0, (confTeams.size()-1));
    }

    /**
     * Recursive method gets the qualified teams for a given confederation
     * Keeps shrinking the list matching the teams in the top vs the ones in the bottom until
     * the size fits the spots
     * @param confTeams the teams in the confederation
     * @param spots the number of spots in the team
     * @return the qualified teams
     * @author Samuel Hernandez
     */
    private ArrayList<Team> getQualifiedRec(ArrayList<Team> confTeams , int spots, int beginning, int end){
        //Base case: When list fits the spots
        if(confTeams.size() == spots){
            return confTeams;
        }

        //Base case 2: Begging passes end
        else if(beginning >= end) {
            beginning = 0;
        }

        //Match the top vs the bottom (1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        Team loser = getQualifierGameLoser(confTeams.get(beginning), confTeams.get(end));
        confTeams.remove(loser);
        beginning++;
        end --;
        return getQualifiedRec(confTeams, spots, beginning, end);
    }

    /**
     * @author Alexander and Michael
     * This method is used to simulate the Qualifiers. Each confederation will have their respective number of group
     * stage slots. Each arraylist is filled up by the method getQualified().
     * @return An ArrayList<Team> that contains 32 teams that will move on to the group stage.
     */
    public void setQualifiedTeams(TeamInfo teamInfo) {

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
        ArrayList<Team> NAvAsia = new ArrayList<>();
        ArrayList<Team> OCvSA = new ArrayList<>();

        //Looping through the teams and assigning them to their respective confederation team lists
        for(Team t : teamInfo.getTeamMap().values()){
            //Since England is the host country, they are later added in automatically
            if(t.getConfederation().equals("UEFA")) //13 Spots
                UEFA.add(t);
            if(t.getConfederation().equals("CONMEBOL")) //4.5 Spots
                CONMEBOL.add(t);
            if(t.getConfederation().equals("CONCACAF")) //3.5 Spots
                CONCACAF.add(t);
            if(t.getConfederation().equals("CAF")) //5 Spots
                CAF.add(t);
            if(t.getConfederation().equals("AFC") && !t.getCountry().equals("Qatar")) //4.5 Spots
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
        OCvSA.add(CONMEBOL.get(4));
        CONMEBOL.remove(4);
        NAvAsia.add(CONCACAF.get(3));
        CONCACAF.remove(3);
        NAvAsia.add(AFC.get(4));
        AFC.remove(4);
        OCvSA.add(OFC.get(0));
        OFC.remove(OFC.get(0));

        //Add all the teams to the output arraylist
        output.add(teamInfo.getTeam("QAT"));

        OCvSA.remove(getQualifierGameLoser(OCvSA.get(0), OCvSA.get(1)));
        output.addAll(OCvSA);
        NAvAsia.remove(getQualifierGameLoser(NAvAsia.get(0), NAvAsia.get(1)));
        output.addAll(NAvAsia);
        output.addAll(UEFA);
        output.addAll(CONMEBOL);
        output.addAll(CONCACAF);
        output.addAll(AFC);
        output.addAll(OFC);
        output.addAll(CAF);

        worldCupTeams = output;
    }

    /**
     * @author Alexander and Michael
     * Helper function to the setQualifiedTeams() method. This returns an arraylist that contains the teams that qualify to
     * group stage. Using the Java.util Random(), it will determine which team will qualify to the group stage with the
     * higher seeded team having a greater chance.
     * @param input Confederation team list
     * @param spots The number of spots that allocated to them in the group stage
     * @return An ArrayList<Team> that contains the qualified teams
     */
    private ArrayList<Team> getQualified(ArrayList<Team> input, int spots){
        Collections.sort(input);
        ArrayList<Team> output = new ArrayList<>();
        Team teamOne, teamTwo;
        int evenSpots = spots;
        if (evenSpots % 2 == 1)
            evenSpots++;

        if (input.size() % 2 == 1) {
            teamOne = input.get(0);
            teamTwo = input.get(input.size() - 1);
            input.remove(getQualifierGameLoser(teamOne, teamTwo));
            Collections.sort(input);
        }

        int indexTop = 0;
        int indexBottom = input.size() -1;

        setQualifiedTeams(input, evenSpots);

        if (spots % 2 == 1) {
            teamOne = input.get(0);
            teamTwo = input.get(input.size() - 1);
            input.remove(getQualifierGameLoser(teamOne, teamTwo));
            Collections.sort(input);
        }
        return input;
    }

    /**
     * ALEX's method
     * @param tOne The first team to play
     * @param tTwo The second team to play
     * @return The team that loses three games out of five games. (This helps with our algorithm).
     */
    private Team getQualifierGameLoser(Team tOne, Team tTwo) {
        Random random = new Random();
        int tOneWins = 0;
        int tTwoWins = 0;

        //Determine the range for Random()
        int sum = 0;
        sum += tOne.getTotalPoints();
        sum += tTwo.getTotalPoints();

        //Loop through five games to determine the winner of the qualifier game
        for(int i = 0; i < 200; i++) {
            //Generate a number in between the range of 1 and the total number of points between team one and team two
            int randomNumber = random.nextInt(sum) + 1;
            //This is similar to the logic in simulateSection() in Game.java
            if(randomNumber <= tOne.getTotalPoints()) {
                tOneWins++;
            }
            else {
                tTwoWins++;
            }
        }
        //This would return the losing team
        if(tOneWins < tTwoWins)
            return tOne;
        else
            return tTwo;
    }

    /**
     * Gets the teams that qualified to the world cup
     * @return the list of qualified teams
     */
    public ArrayList<Team> getQualifiedTeams() {
        return worldCupTeams;
    }

    /**
     * Gets the string representation of object
     * @return the string with information about simualtion
     */
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

    //Equals and rest of getters/setters do not make sense so they are not implemented on purpose
}
