import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


// TODO: Change name to TeamsPane
public class QualifierPane extends BorderPane {

    private Map<String, Team> teamMap;
    private Map<String, String> confInfo;
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


    public QualifierPane(Double height, Double width,Simulator simulator) {
        confInfo = new HashMap<>();

        teamMap = simulator.getTeamMap();
        updateTeamMap(simulator.getQualifiedTeams());

        createTable();
        setCenter(table);

        createConfMap();
        createButtonBar();
        setTop(topButtonBar);

        this.setMinHeight(height);
        this.setMinWidth(width);
    }

    private void createConfMap() {
        //HashMap<String,String> confInfo = new HashMap<>();
        confInfo.put("UEFA", "EUROPE\n\n55 TEAMS\n\n13 PLACES");
        confInfo.put("CONMEBOL", "SOUTH AMERICA\n\n10 TEAMS\n\n4.5 PLACES");
        confInfo.put("CONCACAF", "NORTH AMERICA, CENTRAL AMERICA, CARIBBEAN\n\n35 TEAMS\n\n3.5 PLACES");
        confInfo.put("CAF", "AFRICA\n\n53 TEAMS\n\n5 PLACES");
        confInfo.put("AFC", "ASIA\n\n46 TEAMS\n\n4.5 PLACES");
        confInfo.put("OFC", "OCEANIA\n\n9 TEAMS\n\n0.5 PLACES");
    }


    private void createButtonBar() {
        allButton = new Button("All confederations");
        allButton.setOnMouseClicked(mouseEvent -> {
            table.getItems().clear();
            searchField.setText("");
            table.getItems().addAll(TableViewHelper.getFullTeamList(teamMap));
            table.sort();
        });

        uefaButton = new Button("UEFA");
        uefaButton.setTooltip(createToolTip("UEFA"));
        uefaButton.setOnMouseClicked(mouseEvent -> {
            table.getItems().clear();
            searchField.setText("");
            table.getItems().addAll(TableViewHelper.getConfTeamList(teamMap, "UEFA"));
            table.sort();
        });

        conmebolButton = new Button("CONMEBOL");
        conmebolButton.setTooltip(createToolTip("CONMEBOL"));
        conmebolButton.setOnAction(mouseEvent -> {
            table.getItems().clear();
            searchField.setText("");
            table.getItems().addAll(TableViewHelper.getConfTeamList(teamMap, "CONMEBOL"));
            table.sort();
        });

        concacafButton = new Button("CONCACAF");
        concacafButton.setTooltip(createToolTip("CONCACAF"));
        concacafButton.setOnAction(mouseEvent -> {
            table.getItems().clear();
            searchField.setText("");
            table.getItems().addAll(TableViewHelper.getConfTeamList(teamMap, "CONCACAF"));
            table.sort();
        });

        cafButton = new Button("CAF");
        cafButton.setTooltip(createToolTip("CAF"));
        cafButton.setOnAction(mouseEvent -> {
            table.getItems().clear();
            searchField.setText("");
            table.getItems().addAll(TableViewHelper.getConfTeamList(teamMap, "CAF"));
            table.sort();
        });

        afcButton = new Button("AFC");
        afcButton.setTooltip(createToolTip("AFC"));
        afcButton.setOnAction(mouseEvent -> {
            table.getItems().clear();
            searchField.setText("");
            table.getItems().addAll(TableViewHelper.getConfTeamList(teamMap, "AFC"));
            table.sort();
        });

        ofcButton = new Button("OFC");
        ofcButton.setTooltip(createToolTip("OFC"));
        ofcButton.setOnAction(mouseEvent -> {
            table.getItems().clear();
            searchField.setText("");
            table.getItems().addAll(TableViewHelper.getConfTeamList(teamMap, "OFC"));
            table.sort();
        });

        qualifiedButton = new Button("Qualified Teams");
        qualifiedButton.setOnAction(mouseEvent -> {
            table.getItems().clear();
            searchField.setText("");
            table.getItems().addAll(TableViewHelper.getQualifiedTeamList(teamMap));
            table.sort();
        });

        searchField = new TextField();
        searchField.setPromptText("Search");
        searchButton = new Button("Search");
        searchButton.setOnAction(mouseEvent -> {
            table.getItems().clear();
            table.getItems().addAll(TableViewHelper.getSearchTeamList(teamMap, searchField.getText()));
            table.sort();
        });

        topButtonBar = new HBox();
        topButtonBar.setPadding(new Insets(10, 10, 10, 10));
        topButtonBar.setSpacing(10);

        topButtonBar.getChildren().addAll(allButton, uefaButton, conmebolButton,
               concacafButton, cafButton, afcButton, ofcButton, qualifiedButton, searchField,searchButton);


    }

    private Tooltip createToolTip(String confederation) {
        Tooltip tooltip = new Tooltip();
        tooltip.setText(confInfo.get(confederation));
        try {
            tooltip.setGraphic(new ImageView(
                    this.getClass().getResource("Images/"+confederation+".png"
                    ).toString()));
        } catch (Exception e) {
            tooltip.setText("Missing Logo!");
        }
        return tooltip;
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

        table.getSortOrder().add(table.getColumns().get(1));

        // Set the column resize policy to constrained resize policy
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        // Set the Placeholder for an empty table
        table.setPlaceholder(new Label("No visible columns and/or data exist."));

        table.setEditable(false);
    }

    public void updateTeamMap(ArrayList<Team> updatedTeamMap) {
        for (int i = 0; i < updatedTeamMap.size(); i++) {
            Team temp = updatedTeamMap.get(i);
            teamMap.get(temp.getCountryCode()).setQualified(true);
        }
    }
}
