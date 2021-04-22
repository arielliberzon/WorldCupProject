import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.Map;

public class TableViewHelper {

    // Returns an observable list of teams based on the given map
    public static ObservableList<Team> getTeamList(Map<String, Team> teamMap) {
        ArrayList<Team> teamArrayList = new ArrayList<Team>(teamMap.values());
        return FXCollections.<Team>observableArrayList(teamArrayList);
    }

    // Returns "Rank" column
    public static TableColumn<Team, Integer> getTeamRankingColumn() {
        TableColumn<Team, Integer> rankingColumn = new TableColumn<>("Rank");
        PropertyValueFactory<Team, Integer> rankingCellValueFactory = new PropertyValueFactory<>("ranking");
        rankingColumn.setCellValueFactory(rankingCellValueFactory);
        rankingColumn.setMinWidth(100);
        rankingColumn.setMaxWidth(100);
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
}
