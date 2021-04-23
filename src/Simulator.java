import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Simulator {

    private ArrayList<Group> groups; // 8 groups with 4 teams each.
    private int curGameId; // Incremented after every game? Used to access each specific game.

    public ArrayList<Game> RoundOfSixteen() {
        // TODO:
        ArrayList<Game> games = new ArrayList<>();
        for (int i=0; i<16; i++) {
            games.add(Game.dummyGame(false));
        }
        return games;
    }
    public ArrayList<Game> Quarters() {
        // TODO:
        ArrayList<Game> games = new ArrayList<>();
        for (int i=0; i<8; i++) {
            games.add(Game.dummyGame(false));
        }
        return games;
    }
    public ArrayList<Game> Semis() {
        // TODO:
        ArrayList<Game> games = new ArrayList<>();
        for (int i=0; i<4; i++) {
            games.add(Game.dummyGame(false));
        }
        return games;
    }
    public ArrayList<Game> FinalAndThirdPlace() {
        // TODO:
        ArrayList<Game> games = new ArrayList<>();
        for (int i=0; i<2; i++) {
            games.add(Game.dummyGame(false));
        }
        return games;
    }

    /**
     * @author Alexander and Michael
     * This method is used to simulate the Qualifiers. Each confederation will have their respective number of group
     * stage slots. Each arraylist is filled up by the method getQualified().
     * @return An ArrayList<Team> that contains 32 teams that will move on to the group stage.
     * @throws IOException If the teaminfo.txt file does not exist then this Exception will be thrown
     */
    public ArrayList<Team> Qualifiers() throws IOException{

            ArrayList<Team> output = new ArrayList<>();
            //Load the team info from teaminfo.txt and throw and execption if it does not exist
            TeamInfo teamInfo = new TeamInfo();

            //Create a list for each confederation
            ArrayList<Team> UEFA = new ArrayList<>();
            ArrayList<Team> CONMEBOL = new ArrayList<>();
            ArrayList<Team> CONCACAF = new ArrayList<>();
            ArrayList<Team> CAF = new ArrayList<>();
            ArrayList<Team> AFC = new ArrayList<>();
            ArrayList<Team> OFC = new ArrayList<>();

            /* For the confederations that are allocated a different number of teams every year, they are added to a
             * list that 'simulate' games between those teams in order to determine who makes it out of qualifiers
            */
            ArrayList<Team> extras = new ArrayList<>();

            //Looping through the teams and assigning them to their respective confederation team lists
            for(Team t : teamInfo.getTeams().values()){
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
        ArrayList<Team> output = new ArrayList<>();

        //Loop that loops until the right amount of teams are selected to move on
        while(spots > 0){
            //Determine the range for Random()
            int sum = 0;

            //Select a random team from the input list of teams
            int indexOfTeam = random.nextInt(input.size());
            Team teamOne = input.get(indexOfTeam);
            sum += teamOne.getTotalPoints();
            indexOfTeam = random.nextInt(input.size());

            //Select the second team from the input list of teams
            Team teamTwo = input.get(indexOfTeam);
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

    public ArrayList<Group> getGroups() {
        return groups;
    }
}
