import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.text.html.ImageView;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author Ariel Liberzon
 * A static class that helps create a TableView object through the use of
 * various static methods. Some methods are in charge of creating an
 * ObservableList of teams and other methods are used to create columns.
 */
public class TableViewHelper {

    /**
     * Returns an observable list of teams from the given Map
     * @param teamMap Map of teams
     * @return ObservableList of teams
     */
    public static ObservableList<Team> getFullTeamList(Map<String, Team> teamMap) {
        ArrayList<Team> teamArrayList = new ArrayList<Team>(teamMap.values());
        return FXCollections.<Team>observableArrayList(teamArrayList);
    }

    /**
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
        return FXCollections.<Team>observableArrayList(teamArrayList);
    }

    /**
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
        return FXCollections.<Team>observableArrayList(teamArrayList);
    }

    /**
     * Returns an observable list of teams from the given Map.
     * Only returns teams that contain the search parameter in
     * either their team name or country code.
     * @param teamMap Map of teams
     * @param search Search parameter (ex: "Island")
     * @return
     */
    public static ObservableList<Team> getSearchTeamList(Map<String, Team> teamMap, String search) {
        ArrayList<Team> teamArrayList = new ArrayList<>();
        for (Map.Entry<String, Team> entry : teamMap.entrySet()) {
            Team temp = entry.getValue();
            String lowerCaseFilterString = search.toLowerCase();

            if (temp.getCountry().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
                teamArrayList.add(temp); // Filter matches first name.
            } else {
                if (temp.getCountryCode().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
                    teamArrayList.add(temp);
                }
            }

        }
        return FXCollections.<Team>observableArrayList(teamArrayList);
    }

    /**
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
     *
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
     *
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
     *
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
     *
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
     *
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
     * @return
     */
    public static TableColumn<Team, String> getGroupWinsColumn() {
        TableColumn<Team, String> groupWinsColumn = new TableColumn<>("Wins");
        groupWinsColumn.setCellValueFactory(data ->
                new SimpleStringProperty(Integer.toString(data.getValue().getGroupWins())));
        groupWinsColumn.setMinWidth(50);
        groupWinsColumn.setMaxWidth(50);
        return groupWinsColumn;
    }

    /**
     * @author Harjit Singh
     * @return
     */
    public static TableColumn<Team, String> getGroupDrawsColumn() {
        TableColumn<Team, String> groupDrawsColumn = new TableColumn<>("Draws");
        groupDrawsColumn.setCellValueFactory(data ->
                new SimpleStringProperty(Integer.toString(data.getValue().getGroupDraws())));
        groupDrawsColumn.setMinWidth(50);
        groupDrawsColumn.setMaxWidth(50);
        return groupDrawsColumn;
    }

    /**
     * @author Harjit Singh
     * @return
     */
    public static TableColumn<Team, String> getGroupLossesColumn() {
        TableColumn<Team, String> groupLossesColumn = new TableColumn<>("Losses");
        groupLossesColumn.setCellValueFactory(data ->
                new SimpleStringProperty(Integer.toString(data.getValue().getGroupLosses())));
        groupLossesColumn.setMinWidth(50);
        groupLossesColumn.setMaxWidth(50);
        return groupLossesColumn;
    }

    /**
     * @author Harjit Singh
     * @return
     */
    public static TableColumn<Team, String> getGFColumn() {
        TableColumn<Team, String> goalsForColumn = new TableColumn<>("GF");
        goalsForColumn.setCellValueFactory(data ->
                new SimpleStringProperty("FIX"));
        goalsForColumn.setMinWidth(50);
        goalsForColumn.setMaxWidth(50);
        return goalsForColumn;
    }

    /**
     * @author Harjit Singh
     * @return
     */
    public static TableColumn<Team, String> getGDColumn() {
        TableColumn<Team, String> goalDifferenceColumn = new TableColumn<>("GD");
        goalDifferenceColumn.setCellValueFactory(data ->
                new SimpleStringProperty("FIX"));
        goalDifferenceColumn.setMinWidth(50);
        goalDifferenceColumn.setMaxWidth(50);
        return goalDifferenceColumn;
    }

    /**
     * @author Harjit Singh
     * @return
     */
    public static TableColumn<Team, String> getGAColumn() {
        TableColumn<Team, String> goalsAgainstColumn = new TableColumn<>("GA");
        goalsAgainstColumn.setCellValueFactory(data ->
                new SimpleStringProperty("FIX"));
        goalsAgainstColumn.setMinWidth(50);
        goalsAgainstColumn.setMaxWidth(50);
        return goalsAgainstColumn;
    }

    /**
     * @author Harjit Singh
     * @return
     */
    public static TableColumn<Team, String> getPointsColumn() {
        TableColumn<Team, String> goalsAgainstColumn = new TableColumn<>("Points");
        goalsAgainstColumn.setCellValueFactory(data ->
                new SimpleStringProperty(Integer.toString(data.getValue().groupPoints())));
        goalsAgainstColumn.setMinWidth(50);
        goalsAgainstColumn.setMaxWidth(50);
        return goalsAgainstColumn;
    }

}
