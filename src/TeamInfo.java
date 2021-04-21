import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class TeamInfo {
    HashMap<String, Team> teams;

    public TeamInfo() throws IOException{
        teams = new HashMap<>();
        loadFromFile();
    }
    private void loadFromFile() throws IOException{
        String firstLine;
        int position;
        String country;
        double score;
        String countryAbbr;
        String confederation;
        String countryFlagID;

        try{
            BufferedReader br = new BufferedReader(new FileReader("teamInfo.txt"));

            while((firstLine = br.readLine()) != null){
                position = Integer.parseInt(firstLine);
                country = br.readLine();
                score = Double.parseDouble(br.readLine());
                countryAbbr = br.readLine();
                confederation = br.readLine();
                countryFlagID = countryAbbr.toLowerCase()+".png";


                Team newTeam = new Team(position, country, score, countryAbbr,
                        confederation, countryFlagID);

                br.readLine();

                teams.put(newTeam.getCountryAbbr(), newTeam);
            }

            br.close();

        }
        catch(IOException e) {
            e.printStackTrace();
        }

    }

    public Team getTeam(String countryAbbr){
        return teams.get(countryAbbr);
    }

}
