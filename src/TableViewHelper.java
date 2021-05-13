import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.text.html.ImageView;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author Ariel Liberzon, Harjit Singh
 * A static class that helps create a TableView object through the use of
 * various static methods. Some methods are in charge of creating an
 * ObservableList of teams and other methods are used to create columns.
 */
public class TableViewHelper {

    /**
     * @author Ariel Liberzon
     * Returns an observable list of teams from the given Map
     * @param teamMap Map of teams
     * @return ObservableList of teams
     */
    public static ObservableList<Team> getFullTeamList(Map<String, Team> teamMap) {
        ArrayList<Team> teamArrayList = new ArrayList<>(teamMap.values());
        return FXCollections.observableArrayList(teamArrayList);
    }

    /**
     * @author Ariel Liberzon
     * Returns an observable list of teams from the given Map.
     * Only returns teams that match the given confederation
     * @param teamMap Map of teams
     * @param conf Confederation String (ex: "UEFA")
     * @return ObservableList of teams
     */
    public static ObservableList<Team> getConfTeamList(Map<String, Team> teamMap, String conf) {
        ArrayList<Team> teamArrayList = new ArrayList<>();
        for (Map.Entry<String, Team> entry : teamMap.entrySet()) {
            Team temp = entry.getValue();
            if(temp.getConfederation().equals(conf))
                teamArrayList.add(temp);
        }
        return FXCollections.observableArrayList(teamArrayList);
    }

    /**
     * @author Ariel Liberzon
     * Returns an observable list of teams from the given Map.
     * Only returns teams that have are marked as "qualified"
     * @param teamMap Map of teams
     * @return ObservableList of teams
     */
    public static ObservableList<Team> getQualifiedTeamList(Map<String, Team> teamMap) {
        ArrayList<Team> teamArrayList = new ArrayList<>();
        for (Map.Entry<String, Team> entry : teamMap.entrySet()) {
            Team temp = entry.getValue();
            if(temp.isQualified())
                teamArrayList.add(temp);
        }
        return FXCollections.observableArrayList(teamArrayList);
    }

    /**
     * @author Ariel Liberzon, Harjit Singh
     * Returns an observable list of teams from the given Map.
     * Only returns teams that contain the search parameter in
     * either their team name or country code.
     * @param teamMap Map of teams
     * @param search Search parameter (ex: "Island")
     * @return ObservableList containing only teams that meet the search parameter
     */
    public static ObservableList<Team> getSearchTeamList(Map<String, Team> teamMap, String search) {
        ArrayList<Team> teamArrayList = new ArrayList<>();
        for (Map.Entry<String, Team> entry : teamMap.entrySet()) {
            Team temp = entry.getValue();
            String lowerCaseFilterString = search.toLowerCase();

            if (temp.getCountry().toLowerCase().contains(lowerCaseFilterString)) {
                teamArrayList.add(temp); // Filter matches first name.
            } else {
                if (temp.getCountryCode().toLowerCase().contains(lowerCaseFilterString)) {
                    teamArrayList.add(temp);
                }
            }

        }
        return FXCollections.observableArrayList(teamArrayList);
    }

    /**
     * @author Ariel Liberzon
     * Returns an observable list of teams from the given group.
     * @param group Group of teams
     * @return ObservableList of teams
     */
    public static ObservableList<Team> getGroupList(Group group) {
        ArrayList<Team> groupList = group.getTeams();
        return FXCollections.observableArrayList(groupList);
    }

    /**
     * @author Ariel Liberzon
     * @return "Rank" TableColumn
     */
    public static TableColumn<Team, Integer> getTeamRankingColumn() {
        TableColumn<Team, Integer> rankingColumn = new TableColumn<>("Rank");
        PropertyValueFactory<Team, Integer> rankingCellValueFactory = new PropertyValueFactory<>("ranking");
        rankingColumn.setCellValueFactory(rankingCellValueFactory);
        rankingColumn.setMinWidth(75);
        rankingColumn.setMaxWidth(75);
        rankingColumn.setSortType(TableColumn.SortType.ASCENDING);
        return rankingColumn;
    }

    /**
     * @author Ariel Liberzon
     * @return "Team" TableColumn
     */
    public static TableColumn<Team, String> getCountryNameColumn() {
        TableColumn<Team, String> countryCol = new TableColumn<>("Team");
        PropertyValueFactory<Team, String> countryCellValueFactory = new PropertyValueFactory<>("country");
        countryCol.setCellValueFactory(countryCellValueFactory);
        countryCol.setMaxWidth(200);
        countryCol.setMinWidth(200);
        return countryCol;
    }

    /**
     * @author Ariel Liberzon
     * @return "Total Points" TableColumn
     */
    public static TableColumn<Team, Double> getTotalPointsColumn() {
        TableColumn<Team, Double> pointsCol = new TableColumn<>("Total Points");
        PropertyValueFactory<Team, Double> pointsCellValueFactory = new PropertyValueFactory<>("totalPoints");
        pointsCol.setCellValueFactory(pointsCellValueFactory);
        pointsCol.setMaxWidth(200);
        pointsCol.setMinWidth(200);
        return pointsCol;
    }

    /**
     * @author Ariel Liberzon
     * @return "Country Code" TableColumn
     */
    public static TableColumn<Team, String> getCodeColumn() {
        TableColumn<Team, String> codeCol = new TableColumn<>("Country Code");
        PropertyValueFactory<Team, String> countryCellValueFactory = new PropertyValueFactory<>("countryCode");
        codeCol.setCellValueFactory(countryCellValueFactory);
        codeCol.setMinWidth(100);
        codeCol.setMaxWidth(100);
        return codeCol;
    }

    /**
     * @author Ariel Liberzon
     * @return "Flag" TableColumn
     */
    public static TableColumn<Team, ImageView> getFlagColumn() {
        TableColumn<Team, ImageView> flagCol = new TableColumn<>("Flag");
        PropertyValueFactory<Team, ImageView> flagCellValueFactory = new PropertyValueFactory<>("flag");
        flagCol.setCellValueFactory(flagCellValueFactory);
        flagCol.setMinWidth(50);
        flagCol.setMaxWidth(50);
        flagCol.setSortable(false);
        return flagCol;
    }

    /**
     * @author Ariel Liberzon
     * @return "Confederation" TableColumn
     */
    public static TableColumn<Team, String> getTeamConfColumn() {
        TableColumn<Team, String> confColumn = new TableColumn<>("Confederation");
        PropertyValueFactory<Team, String> confCellValueFactory = new PropertyValueFactory<>("confederation");
        confColumn.setCellValueFactory(confCellValueFactory);
        confColumn.setMinWidth(100);
        confColumn.setMaxWidth(100);
        return confColumn;
    }

    /**
     * @author Harjit Singh
     * Method to create Country Name column in the GroupsPane class
     * @return countryCol
     */
    public static TableColumn<Team, String> getGroupCountry() {
    TableColumn<Team, String> countryCol = new TableColumn<>("Country");
                countryCol.setCellValueFactory(data ->
            new SimpleStringProperty(data.getValue().getCountry()));
        return countryCol;
    }
    /**
     * @author Harjit Singh
     * Method to create wins column in the GroupsPane class
     * @return groupWinsColumn
     */
    public static TableColumn<Team, String> getGroupWinsColumn() {
        TableColumn<Team, String> groupWinsColumn = new TableColumn<>("Wins");
        groupWinsColumn.setCellValueFactory(data ->
                new SimpleStringProperty(Integer.toString(data.getValue().getGroupWins())));
        return groupWinsColumn;
    }

    /**
     * @author Harjit Singh
     * Method to create Draws column in the GroupsPane class
     * @return groupDrawsColumn
     */
    public static TableColumn<Team, String> getGroupDrawsColumn() {
        TableColumn<Team, String> groupDrawsColumn = new TableColumn<>("Draws");
        groupDrawsColumn.setCellValueFactory(data ->
                new SimpleStringProperty(Integer.toString(data.getValue().getGroupDraws())));
        return groupDrawsColumn;
    }

    /**
     * @author Harjit Singh
     * Method to create Losses column in the GroupsPane class
     * @return groupLossesColumn
     */
    public static TableColumn<Team, String> getGroupLossesColumn() {
        TableColumn<Team, String> groupLossesColumn = new TableColumn<>("Losses");
        groupLossesColumn.setCellValueFactory(data ->
                new SimpleStringProperty(Integer.toString(data.getValue().getGroupLosses())));
        return groupLossesColumn;
    }

    /**
     * @author Harjit Singh
     * Method to create Goal For column in the GroupsPane class
     * @return goalsForColumn
     */
    public static TableColumn<Team, String> getGFColumn() {
        TableColumn<Team, String> goalsForColumn = new TableColumn<>("GF");
        goalsForColumn.setCellValueFactory(data ->
                new SimpleStringProperty(Integer.toString(data.getValue().groupGoalsFor())));
        return goalsForColumn;
    }

    /**
     * @author Harjit Singh
     * Method to create Goal Differnce column in the GroupsPane class
     * @return goalDifferenceColumn
     */
    public static TableColumn<Team, String> getGDColumn() {
        TableColumn<Team, String> goalDifferenceColumn = new TableColumn<>("GD");
        goalDifferenceColumn.setCellValueFactory(data ->
                new SimpleStringProperty(Integer.toString(data.getValue().groupGoalsDifference())));
        return goalDifferenceColumn;
    }

    /**
     * @author Harjit Singh
     * Method to create Goalagainst column in the GroupsPane class
     * @return goalsAgainstColumn
     */
    public static TableColumn<Team, String> getGAColumn() {
        TableColumn<Team, String> goalsAgainstColumn = new TableColumn<>("GA");
        goalsAgainstColumn.setCellValueFactory(data ->
                new SimpleStringProperty(Integer.toString(data.getValue().groupGoalsAgainst())));
        return goalsAgainstColumn;
    }

    /**
     * @author Harjit Singh
     * Method to create points column in the GroupsPane class
     * @return totalPointsColumn
     */
    public static TableColumn<Team, String> getPointsColumn() {
        TableColumn<Team, String> totalPointsColumn = new TableColumn<>("Points");
        totalPointsColumn.setCellValueFactory(data ->
                new SimpleStringProperty(Integer.toString(data.getValue().groupPoints())));
        return totalPointsColumn;
    }

    public static TableColumn<Team, TeamButton> getButtonColumn() {
        TableColumn<Team, TeamButton> buttonColumn = new TableColumn<>("Country");
        buttonColumn.setCellValueFactory(data -> {
            TeamButton teamButton = new TeamButton();
            teamButton.setTeam(data.getValue());
            teamButton.changeToGroupButton();
            return new SimpleObjectProperty<TeamButton>(teamButton);
        }
                );
        buttonColumn.setMinWidth(100);
        return buttonColumn;
    }
}
