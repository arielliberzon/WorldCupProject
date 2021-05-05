import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ariel Liberzon
 * This is a custom BorderPane that contains an HBox for Buttons at the top
 * and a TableView in the center that displays teams. Each Button affects
 * what is displayed inside the table. Qualified teams are highlighted.
 */
public class TeamsPane extends BorderPane {

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


    /**
     * Constructor used by application.
     * All methods get called in the constructor itself
     * @param height set by WorldCupGUI
     * @param width set by WorldCupGUI
     * @param simulator
     */
    public TeamsPane(Double height, Double width, Simulator simulator) {

        // Map that has information for each confederation
        confInfo = new HashMap<>();

        // gets the map from simulator
        teamMap = simulator.getTeamMap();

        // updates the map used by table to show qualified teams
        updateTeamMap(simulator.getQualifiedTeams());


        createTable();
        setCenter(table);

        createConfMap();
        createButtonBar();
        setTop(topButtonBar);

        setMinHeight(height);
        setMinWidth(width);
    }


    /**
     * Adds data to the confInfo Map
     */
    private void createConfMap() {
        confInfo.put("UEFA", "EUROPE\n\n55 TEAMS\n\n13 PLACES");
        confInfo.put("CONMEBOL", "SOUTH AMERICA\n\n10 TEAMS\n\n4.5 PLACES");
        confInfo.put("CONCACAF", "NORTH AMERICA, CENTRAL AMERICA, CARIBBEAN\n\n35 TEAMS\n\n3.5 PLACES");
        confInfo.put("CAF", "AFRICA\n\n53 TEAMS\n\n5 PLACES");
        confInfo.put("AFC", "ASIA\n\n46 TEAMS\n\n4.5 PLACES");
        confInfo.put("OFC", "OCEANIA\n\n9 TEAMS\n\n0.5 PLACES");
    }


    /**
     * creates buttons and adds them to an HBox
     * Each button changes what's displayed in the table
     */
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

    /**
     * Creates a ToolTip with information and an icon
     * this data is based on the confederation and
     * relates directly to the confInfo map
     * @param confederation (ex: "UEFA")
     * @return Tooltip with custom text and graphic
     */
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

    /**
     * Creates a table by calling the TableViewHelper class
     */
    private void createTable() {
        // Create a TableView with a list of teams
        table = new TableView<>();

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

        // Change color of qualified teams rows (alternate colors)
        table.setRowFactory(tableView -> new TableRow<Team>() {
            @Override
            public void updateItem(Team item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null) {
                    setStyle("");
                } else if (item.isQualified()) {
                    if (getIndex() % 2 == 0) {
                        setStyle("-fx-background-color: #ffffd8;");
                    } else {
                        setStyle("-fx-background-color: #ffffc7;");
                    }
                } else {
                    setStyle("");
                }
            }
            @Override
            public void updateSelected(boolean empty) {
                super.updateSelected(empty);
                if (isSelected()) {
                    setStyle("");
                }
            }
        });

        // If a row is selected removes it's background so that it highlights properly
        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                table.refresh();
            }
        });

    }

    /**
     * Update teamMap by marking qualified teams
     * @param qualifiedTeams
     */
    private void updateTeamMap(ArrayList<Team> qualifiedTeams) {
        for (int i = 0; i < qualifiedTeams.size(); i++) {
            Team team = qualifiedTeams.get(i);
            teamMap.get(team.getCountryCode()).setQualified(true);
        }
    }
}
