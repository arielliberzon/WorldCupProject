import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TeamInfo {
    ArrayList<String> teams;

    public TeamInfo() throws IOException{
        teams = new ArrayList<>();
        readFile();
    }
    private void readFile() throws IOException{
        String position;
        String country;
        double score;
        String countryAbbr;
        String confederation;
        String countryFlag;

        try{
            BufferedReader br = new BufferedReader(new FileReader("teamInfo.txt"));

            while((position = br.readLine()) != null){
                country = br.readLine();
                score = Double.parseDouble(br.readLine());
                countryAbbr = br.readLine();
                confederation = br.readLine();
                countryFlag = br.readLine();


                //Team newTeam = new Team(position, country, score, countryAbbr, confederation,
                       // countryFlag);

                br.readLine();

                teams.add("newTeam");
            }

            br.close();

        }
        catch(IOException e) {
            e.printStackTrace();
        }

    }

}
