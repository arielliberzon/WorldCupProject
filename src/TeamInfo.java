import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

// TODO: Add authors! (first and last name add the top of the method)
// TODO: Add comments and description
public class TeamInfo {
    private HashMap<String, Team> teams;

    public TeamInfo() {
        teams = new HashMap<>();
        loadFromFile();
    }

    private void loadFromFile(){
        String firstLine;
        int ranking;
        String country;
        double totalPoints;
        String countryCode;
        String confederation;
        String countryFlagID;
        String fileName= "teamInfo.txt";

        try{
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            while((firstLine = br.readLine()) != null){
                ranking = Integer.parseInt(firstLine);
                country = br.readLine();
                totalPoints = Double.parseDouble(br.readLine());
                countryCode = br.readLine();
                confederation = br.readLine();
                //countryFlagID = countryCode.toLowerCase()+".png";


                Team newTeam = new Team(ranking, country, totalPoints, countryCode,
                        confederation);

                br.readLine();

                teams.put(newTeam.getCountryCode(), newTeam);
            }

            br.close();

        }
        catch(IOException e) {
           System.out.println("Error file "+fileName +" could not be found");
           System.exit(1);
        }

    }

    public Team getTeam(String countryCode){
        return teams.get(countryCode);
    }

    public HashMap<String, Team> getTeamMap() {
        return teams;
    }
}
