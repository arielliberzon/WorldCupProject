import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;

public class TournamentInfo {

    private HashMap<String, Team> teams = new HashMap<>();
    private String url = "https://www.fifa.com/fifa-world-ranking/ranking-table/men/#UEFA";

    private Elements teamRank;
    private Elements teamNames;
    private Elements teamConf;
    private Elements teamPoints;

    public TournamentInfo() {
        loadFromWebsite();
        setTeams();
    }

    private void loadFromWebsite(){
        try {
            Document document = Jsoup.connect(url).get();
            teamRank = document.body().select("td.fi-table__td.fi-table__rank");
            teamNames = document.body().select("div.fi-t__n");
            teamConf = document.body().select("td.fi-table__td.fi-table__confederation.hidden");
            teamPoints = document.body().select("td.fi-table__td.fi-table__points");
        } catch(IOException e) {
            System.out.println("Error! " + url + " could not be found");
            System.exit(1);
        }
    }

    private void setTeams() {
        for (int i = 0; i < teamRank.size(); i++) {
            int ranking = Integer.parseInt(teamRank.get(i).text());
            String country = teamNames.get(i).child(0).text();
            String countryCode = teamNames.get(i).child(1).text();
            String confederation = teamConf.get(i).text();

            confederation = confederation.substring(1, confederation.length() - 1);
            //Trim since trialing #'s
            double totalPoints = Double.parseDouble(teamPoints.get(i).text());

            Team team = new Team(ranking, country, totalPoints, countryCode, confederation);
            teams.put(team.getCountryCode(), team);

        }
    }

    public Team getTeam(String countryCode){
        return teams.get(countryCode);
    }


    public HashMap<String, Team> getTeamMap() {
        return teams;
    }



}
