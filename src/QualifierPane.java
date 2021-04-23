import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import java.util.Map;


public class QualifierPane extends BorderPane {

    private Map<String, Team> teamMap;
    private TableView table;
    private HBox topbuttonBar;
    private Button allButton;
    private Button uefaButton;
    private Button conmebolButton;
    private Button cafButton;
    private Button afcButton;
    private Button ofcButton;
    private Button qualifiedButton;

    public QualifierPane(Map<String, Team> teamMap) {
        // load the teamInfo object
        this.teamMap = teamMap;

        createTable();
        setCenter(table);

        createButtonBar();
        setTop(topbuttonBar);
    }

    public void setTeamMap(Map<String, Team> teamMap) {
        this.teamMap = teamMap;
    }

    private void createButtonBar() {
        allButton = new Button("All confederations");
        allButton.setOnMouseClicked(mouseEvent -> {
            table.getItems().clear();
            table.getItems().addAll(TableViewHelper.getFullTeamList(teamMap));
        });

        uefaButton = new Button("UEFA");
        uefaButton.setOnMouseClicked(mouseEvent -> {
            table.getItems().clear();
            table.getItems().addAll(TableViewHelper.getConfTeamList(teamMap, "UEFA"));
        });

        conmebolButton = new Button("CONMEBOL");
        conmebolButton.setOnAction(mouseEvent -> {
            table.getItems().clear();
            table.getItems().addAll(TableViewHelper.getConfTeamList(teamMap, "CONMEBOL"));
            
        });

        cafButton = new Button("CAF");
        cafButton.setOnAction(mouseEvent -> {
            table.getItems().clear();
            table.getItems().addAll(TableViewHelper.getConfTeamList(teamMap, "CAF"));
            
        });

        afcButton = new Button("AFC");
        afcButton.setOnAction(mouseEvent -> {
            table.getItems().clear();
            table.getItems().addAll(TableViewHelper.getConfTeamList(teamMap, "AFC"));
            
        });

        ofcButton = new Button("OFC");
        ofcButton.setOnAction(mouseEvent -> {
            table.getItems().clear();
            table.getItems().addAll(TableViewHelper.getConfTeamList(teamMap, "OFC"));
            
        });

        qualifiedButton = new Button("Qualified Teams");
        qualifiedButton.setOnAction(mouseEvent -> {
            table.getItems().clear();
            table.getItems().addAll(TableViewHelper.getQualifiedTeamList(teamMap));
            
        });

        topbuttonBar = new HBox();
        topbuttonBar.setPadding(new Insets(10, 10, 10, 10));
        topbuttonBar.setSpacing(10);

        topbuttonBar.getChildren().addAll(allButton, uefaButton, conmebolButton,
               cafButton, afcButton, ofcButton, qualifiedButton);


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
    }
}
