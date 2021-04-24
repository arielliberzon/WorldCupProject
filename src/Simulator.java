import java.io.IOException;
import java.util.ArrayList;

public class Simulator {
    private ArrayList<Group> groups; // 8 groups with 4 teams each.
    private ArrayList<Team> worldCupTeams; // The 32 participating teams.
    private TeamInfo teamInfo;

    public Simulator() throws IOException {
        teamInfo = new TeamInfo();
        worldCupTeams = new ArrayList<>();
        loadWorldCupTeams();
        simulateGroups();
    }

    private void loadWorldCupTeams() {
        // This loads an arbitrary selection of 32 teams.
        // TODO: consider a method to select the 32 teams more realistically.
        int i=0;
        for (Team team : teamInfo.getTeamMap().values()) {
            worldCupTeams.add(team);
            i += 1;
            if (i==32) break;
        }
    }

    /**
     * Creates & simulates the 8 groups of the group-stage.
     */
    private void simulateGroups() {
        groups = new ArrayList<>();
        int nGroups = 8;
        int nTeams = worldCupTeams.size();
        int teamsPerGroup = nTeams / nGroups;

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
    public ArrayList<Group> getGroups() {
        return groups;
    }
}
