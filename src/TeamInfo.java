/**
 * @author Emolyn Tumwebaze
 * This class reads data from a text file and populates it in a hashmap
 * The methods in the class are;
 * @loadFromFile
 * @getTeam
 * @getTeamMap
**/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class TeamInfo {
    private HashMap<String, Team> teams; // creating a hashmap<String, Team>

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
        String countryFlagID;
        String fileName= "teamInfo.txt";

        try{
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            while((firstLine = br.readLine()) != null){ //reads first line given that it is not blank.
                ranking = Integer.parseInt(firstLine);
                country = br.readLine();
                totalPoints = Double.parseDouble(br.readLine());
                countryCode = br.readLine();
                confederation = br.readLine();
                //countryFlagID = countryCode.toLowerCase()+".png";


                Team newTeam = new Team(ranking, country, totalPoints, countryCode,
                        confederation);

                br.readLine(); //reads empty line between team info.

                teams.put(newTeam.getCountryCode(), newTeam);  //maps team country code with respective team object
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
