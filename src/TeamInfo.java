import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author Emolyn Tumwebaze
 * This class reads data from a text file and populates it in a hashmap
 * The methods in the class are:
 * loadFromFile
 * getTeam
 * getTeamMap
 **/
public class TeamInfo {
    private final HashMap<String, Team> teams; // creating a hashmap<String, Team>

    // Default constructor
    public TeamInfo() {
        teams = new HashMap<>();
        loadFromFile();
    }
    /**
     * @author Emolyn Tumwebaze
     * This method will load all the team information from the teamInfo.txt file via a BufferedReader
     * into the teams HashMap.
     *
     */
    private void loadFromFile(){
        String firstLine; //creating variables to get specific data from the file
        int ranking;
        String country;
        double totalPoints;
        String countryCode;
        String confederation;
        String fileName= "teamInfo.txt";

        try{
            // Added memory leak fix - A.L
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                //reads first line given that it is not blank.
                while ((firstLine = br.readLine()) != null) {
                    ranking = Integer.parseInt(firstLine);
                    country = br.readLine();
                    totalPoints = Double.parseDouble(br.readLine());
                    countryCode = br.readLine();
                    confederation = br.readLine();

                    Team newTeam = new Team(ranking, country, totalPoints, countryCode,
                            confederation);

                    br.readLine(); //reads empty line between team info.

                    //maps team country code with respective team object
                    teams.put(newTeam.getCountryCode(), newTeam);
                }
            }
        }
        catch(IOException e) {
           System.out.println("Error "+ fileName +" could not be found");
           System.exit(1);
        }

    }
    /**
     *  @author Emolyn Tumwebaze
     * This method will take a parameter of a country code and return the team object corresponding to it.
     * @return team.get(countryCode)
     */
    public Team getTeam(String countryCode){
        return teams.get(countryCode);
    }

    /**
     *  @author Emolyn Tumwebaze
     *  This method is returning a hashmap of type team and string.
     *  Whenever this method is called, it takes team and returns a string of team.
     *  @return teams
     */
    public HashMap<String, Team> getTeamMap() {
        return teams;
    }
}
