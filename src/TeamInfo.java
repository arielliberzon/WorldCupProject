import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

// TODO: Add authors! (first and last name add the top of the method)
// TODO: Add comments and description
public class TeamInfo {
    private HashMap<String, Team> teams;

    public TeamInfo() {
        teams = new HashMap<>();
        try {
            loadFromFile();
        } catch (Exception e) {
            // TODO: catch exception and do something with it
        }
    }
    private void loadFromFile() throws IOException {
        String firstLine;
        int ranking;
        String country;
        double totalPoints;
        String countryCode;
        String confederation;
        String countryFlagID;

        try{
            BufferedReader br = new BufferedReader(new FileReader("teamInfo.txt"));

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
            e.printStackTrace();
        }

    }

    public Team getTeam(String countryCode){
        return teams.get(countryCode);
    }

    public HashMap<String, Team> getTeamMap() {
        return teams;
    }
}
