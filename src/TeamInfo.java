import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class TeamInfo {

    private HashMap<String, Team> teams;

    public TeamInfo() throws IOException{
        teams = new HashMap<>();
        loadFromFile();
    }
    private void loadFromFile() throws IOException{
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
                countryFlagID = countryCode.toLowerCase()+".png";


                Team newTeam = new Team(ranking, country, totalPoints, countryCode,
                        confederation, countryFlagID);

                br.readLine();

                teams.put(newTeam.getCountryCode(), newTeam);
            }

            br.close();

        }
        catch(IOException e) {
            e.printStackTrace();
        }

    }

    public HashMap<String, Team> getTeams(){
        return teams;
    }

    public Team getTeam(String countryCode){
        return teams.get(countryCode);
    }

}
