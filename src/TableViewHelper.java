import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.text.html.ImageView;
import java.util.ArrayList;
import java.util.Map;

public class TableViewHelper {

    // Returns an observable list of teams based on the given map
    public static ObservableList<Team> getFullTeamList(Map<String, Team> teamMap) {
        ArrayList<Team> teamArrayList = new ArrayList<Team>(teamMap.values());
        return FXCollections.<Team>observableArrayList(teamArrayList);
    }

    public static ObservableList<Team> getConfTeamList(Map<String, Team> teamMap, String conf) {
        ArrayList<Team> teamArrayList = new ArrayList<>();
        for (Map.Entry<String, Team> entry : teamMap.entrySet()) {
            Team temp = entry.getValue();
            if(temp.getConfederation().equals(conf))
                teamArrayList.add(temp);
        }
        return FXCollections.<Team>observableArrayList(teamArrayList);
    }

    public static ObservableList<Team> getQualifiedTeamList(Map<String, Team> teamMap) {
        ArrayList<Team> teamArrayList = new ArrayList<>();
        for (Map.Entry<String, Team> entry : teamMap.entrySet()) {
            Team temp = entry.getValue();
            if(temp.isQualified())
                teamArrayList.add(temp);
        }
        return FXCollections.<Team>observableArrayList(teamArrayList);
    }

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

    // Returns "Rank" column
    public static TableColumn<Team, Integer> getTeamRankingColumn() {
        TableColumn<Team, Integer> rankingColumn = new TableColumn<>("Rank");
        PropertyValueFactory<Team, Integer> rankingCellValueFactory = new PropertyValueFactory<>("ranking");
        rankingColumn.setCellValueFactory(rankingCellValueFactory);
        rankingColumn.setMinWidth(75);
        rankingColumn.setMaxWidth(75);
        rankingColumn.setSortType(TableColumn.SortType.ASCENDING);
        return rankingColumn;
    }

    // Returns "Team" column
    public static TableColumn<Team, String> getCountryNameColumn() {
        TableColumn<Team, String> countryCol = new TableColumn<>("Team");
        PropertyValueFactory<Team, String> countryCellValueFactory = new PropertyValueFactory<>("country");
        countryCol.setCellValueFactory(countryCellValueFactory);
        countryCol.setMaxWidth(200);
        countryCol.setMinWidth(200);
        return countryCol;
    }

    // Returns "Total Points" column
    public static TableColumn<Team, Double> getTotalPointsColumn() {
        TableColumn<Team, Double> pointsCol = new TableColumn<>("Total Points");
        PropertyValueFactory<Team, Double> pointsCellValueFactory = new PropertyValueFactory<>("totalPoints");
        pointsCol.setCellValueFactory(pointsCellValueFactory);
        pointsCol.setMaxWidth(200);
        pointsCol.setMinWidth(200);
        return pointsCol;
    }

    // Returns "Country Code" column
    public static TableColumn<Team, String> getCodeColumn() {
        TableColumn<Team, String> codeCol = new TableColumn<>("Country Code");
        PropertyValueFactory<Team, String> countryCellValueFactory = new PropertyValueFactory<>("countryCode");
        codeCol.setCellValueFactory(countryCellValueFactory);
        codeCol.setMinWidth(100);
        codeCol.setMaxWidth(100);
        return codeCol;
    }

    public static TableColumn<Team, ImageView> getFlagColumn() {
        TableColumn<Team, ImageView> flagCol = new TableColumn<>("Flag");
        PropertyValueFactory<Team, ImageView> flagCellValueFactory = new PropertyValueFactory<>("flag");
        flagCol.setCellValueFactory(flagCellValueFactory);
        flagCol.setMinWidth(50);
        flagCol.setMaxWidth(50);
        flagCol.setSortable(false);
        return flagCol;
    }

    // Returns "Confederation" column
    public static TableColumn<Team, String> getTeamConfColumn() {
        TableColumn<Team, String> confColumn = new TableColumn<>("Confederation");
        PropertyValueFactory<Team, String> confCellValueFactory = new PropertyValueFactory<>("confederation");
        confColumn.setCellValueFactory(confCellValueFactory);
        confColumn.setMinWidth(100);
        confColumn.setMaxWidth(100);
        return confColumn;
    }

}
