import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Predicate;


public class QualifierPane extends BorderPane {

    private Map<String, Team> teamMap;
    private TableView table;
    private HBox topbuttonBar;
    private Button allButton;
    private Button uefaButton;
    private Button conmebolButton;
    private Button concacafButton;
    private Button cafButton;
    private Button afcButton;
    private Button ofcButton;
    private Button qualifiedButton;
    private Button searchButton;
    private TextField search;


    public QualifierPane(Map<String, Team> teamMap) {
        // load the teamInfo object
        this.teamMap = teamMap;

        createTable();
        setCenter(table);

        createButtonBar();
        setTop(topbuttonBar);
    }



    private void createButtonBar() {
        allButton = new Button("All confederations");
        allButton.setOnMouseClicked(mouseEvent -> {
            table.getItems().clear();
            search.setText("");
            table.getItems().addAll(TableViewHelper.getFullTeamList(teamMap));
        });

        uefaButton = new Button("UEFA");
        uefaButton.setOnMouseClicked(mouseEvent -> {
            table.getItems().clear();
            search.setText("");
            table.getItems().addAll(TableViewHelper.getConfTeamList(teamMap, "UEFA"));
        });

        conmebolButton = new Button("CONMEBOL");
        conmebolButton.setOnAction(mouseEvent -> {
            table.getItems().clear();
            search.setText("");
            table.getItems().addAll(TableViewHelper.getConfTeamList(teamMap, "CONMEBOL"));
            
        });

        concacafButton = new Button("CONCACAF");
        concacafButton.setOnAction(mouseEvent -> {
            table.getItems().clear();
            search.setText("");
            table.getItems().addAll(TableViewHelper.getConfTeamList(teamMap, "CONCACAF"));

        });

        cafButton = new Button("CAF");
        cafButton.setOnAction(mouseEvent -> {
            table.getItems().clear();
            search.setText("");
            table.getItems().addAll(TableViewHelper.getConfTeamList(teamMap, "CAF"));
            
        });

        afcButton = new Button("AFC");
        afcButton.setOnAction(mouseEvent -> {
            table.getItems().clear();
            search.setText("");
            table.getItems().addAll(TableViewHelper.getConfTeamList(teamMap, "AFC"));
            
        });

        ofcButton = new Button("OFC");
        ofcButton.setOnAction(mouseEvent -> {
            table.getItems().clear();
            search.setText("");
            table.getItems().addAll(TableViewHelper.getConfTeamList(teamMap, "OFC"));
            
        });

        qualifiedButton = new Button("Qualified Teams");
        qualifiedButton.setOnAction(mouseEvent -> {
            table.getItems().clear();
            search.setText("");
            table.getItems().addAll(TableViewHelper.getQualifiedTeamList(teamMap));
            
        });

        search = new TextField();
        search.setPromptText("Search");
        //FilteredList<Team> filteredData = new FilteredList<>(TableViewHelper.getFullTeamList(teamMap));

        // 2. Set the filter Predicate whenever the filter changes.
        /*search.textProperty().addListener((observableValue, oldValue, newValue) -> {
                });*/

        searchButton = new Button("Search");
        searchButton.setOnAction(mouseEvent -> {
            table.getItems().clear();
            table.getItems().addAll(TableViewHelper.getSearchTeamList(teamMap, search.getText()));

        });



        topbuttonBar = new HBox();
        topbuttonBar.setPadding(new Insets(10, 10, 10, 10));
        topbuttonBar.setSpacing(10);

        topbuttonBar.getChildren().addAll(allButton, uefaButton, conmebolButton,
               concacafButton, cafButton, afcButton, ofcButton, qualifiedButton,search,searchButton);


    }

    private void createTable() {
        // Create a TableView with a list of teams
        table = new TableView<>();
        // Add rows to the TableView
        table.getItems().addAll(TableViewHelper.getFullTeamList(teamMap));

        // Add columns to the TableView
        table.getColumns().addAll(TableViewHelper.getFlagColumn(),
                TableViewHelper.getTeamRankingColumn(), TableViewHelper.getCountryNameColumn(),
                TableViewHelper.getTotalPointsColumn(), TableViewHelper.getCodeColumn());

        // Set the column resize policy to constrained resize policy
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        // Set the Placeholder for an empty table
        table.setPlaceholder(new Label("No visible columns and/or data exist."));

        // TODO: this SHOULD WORK but it doesn't :(
        ((TableColumn) table.getColumns().get(1)).setSortType(TableColumn.SortType.ASCENDING);

    }

    public void updateTeamMap(ArrayList<Team> qualifiedTeams) {
        for (int i = 0; i < qualifiedTeams.size(); i++) {
            Team temp = qualifiedTeams.get(i);
            teamMap.get(temp.getCountryCode()).setQualified(true);
        }
    }
}
