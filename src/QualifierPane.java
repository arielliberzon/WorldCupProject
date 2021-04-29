import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.Map;


public class QualifierPane extends BorderPane {

    private Map<String, Team> teamMap;
    private Simulator simulator;
    private TableView table;
    private HBox topButtonBar;
    private Button allButton;
    private Button uefaButton;
    private Button conmebolButton;
    private Button concacafButton;
    private Button cafButton;
    private Button afcButton;
    private Button ofcButton;
    private Button qualifiedButton;
    private Button searchButton;
    private TextField searchField;


    public QualifierPane(Simulator simulator, Double height, Double width) {
        this.simulator = simulator;
        teamMap = simulator.getTeamMap();
        updateTeamMap(simulator.getQualifiedTeams());

        createTable();
        //table.setMaxSize(width- 50, height);
        setCenter(table);

        createButtonBar();
        setTop(topButtonBar);

        this.setMinHeight(height);
        this.setMinWidth(width);
    }



    private void createButtonBar() {
        allButton = new Button("All confederations");
        allButton.setOnMouseClicked(mouseEvent -> {
            table.getItems().clear();
            searchField.setText("");
            table.getItems().addAll(TableViewHelper.getFullTeamList(teamMap));
        });

        uefaButton = new Button("UEFA");
        uefaButton.setOnMouseClicked(mouseEvent -> {
            table.getItems().clear();
            searchField.setText("");
            table.getItems().addAll(TableViewHelper.getConfTeamList(teamMap, "UEFA"));
        });

        conmebolButton = new Button("CONMEBOL");
        conmebolButton.setOnAction(mouseEvent -> {
            table.getItems().clear();
            searchField.setText("");
            table.getItems().addAll(TableViewHelper.getConfTeamList(teamMap, "CONMEBOL"));
            
        });

        concacafButton = new Button("CONCACAF");
        concacafButton.setOnAction(mouseEvent -> {
            table.getItems().clear();
            searchField.setText("");
            table.getItems().addAll(TableViewHelper.getConfTeamList(teamMap, "CONCACAF"));

        });

        cafButton = new Button("CAF");
        cafButton.setOnAction(mouseEvent -> {
            table.getItems().clear();
            searchField.setText("");
            table.getItems().addAll(TableViewHelper.getConfTeamList(teamMap, "CAF"));
            
        });

        afcButton = new Button("AFC");
        afcButton.setOnAction(mouseEvent -> {
            table.getItems().clear();
            searchField.setText("");
            table.getItems().addAll(TableViewHelper.getConfTeamList(teamMap, "AFC"));
            
        });

        ofcButton = new Button("OFC");
        ofcButton.setOnAction(mouseEvent -> {
            table.getItems().clear();
            searchField.setText("");
            table.getItems().addAll(TableViewHelper.getConfTeamList(teamMap, "OFC"));
            
        });

        qualifiedButton = new Button("Qualified Teams");
        qualifiedButton.setOnAction(mouseEvent -> {
            table.getItems().clear();
            searchField.setText("");
            table.getItems().addAll(TableViewHelper.getQualifiedTeamList(teamMap));
            
        });

        searchField = new TextField();
        searchField.setPromptText("Search");
        searchButton = new Button("Search");
        searchButton.setOnAction(mouseEvent -> {
            table.getItems().clear();
            table.getItems().addAll(TableViewHelper.getSearchTeamList(teamMap, searchField.getText()));

        });

        topButtonBar = new HBox();
        topButtonBar.setPadding(new Insets(10, 10, 10, 10));
        topButtonBar.setSpacing(10);

        topButtonBar.getChildren().addAll(allButton, uefaButton, conmebolButton,
               concacafButton, cafButton, afcButton, ofcButton, qualifiedButton, searchField,searchButton);


    }

    private void createTable() {
        // Create a TableView with a list of teams
        table = new TableView<>();
        //table.setPadding(new Insets(0, 0, 50, 0));

        // Add rows to the TableView
        table.getItems().addAll(TableViewHelper.getFullTeamList(teamMap));

        // Add columns to the TableView
        table.getColumns().addAll(TableViewHelper.getFlagColumn(),
                TableViewHelper.getTeamRankingColumn(), TableViewHelper.getCountryNameColumn(),
                TableViewHelper.getTotalPointsColumn(), TableViewHelper.getCodeColumn(),
                TableViewHelper.getTeamConfColumn());

        // Set the column resize policy to constrained resize policy
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        // Set the Placeholder for an empty table
        table.setPlaceholder(new Label("No visible columns and/or data exist."));

        table.setEditable(false);

        // TODO: this SHOULD WORK but it doesn't :(
        ((TableColumn) table.getColumns().get(1)).setSortType(TableColumn.SortType.ASCENDING);

    }

    public void updateTeamMap(ArrayList<Team> updatedTeamMap) {
        for (int i = 0; i < updatedTeamMap.size(); i++) {
            Team temp = updatedTeamMap.get(i);
            teamMap.get(temp.getCountryCode()).setQualified(true);
        }
    }
}
