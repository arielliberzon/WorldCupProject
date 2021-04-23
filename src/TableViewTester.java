import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TableViewTester extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Load all the teams by creating a TeamInfo object
        TeamInfo teamInfo = new TeamInfo();

        // Create a TableView with a list of teams
        TableView<Team> table = new TableView<>();
        // Add rows to the TableView
        table.getItems().addAll(TableViewHelper.getFullTeamList(teamInfo.getTeamMap()));
        // Add columns to the TableView
        table.getColumns().addAll(TableViewHelper.getFlagColumn(),
                TableViewHelper.getTeamRankingColumn(), TableViewHelper.getCountryNameColumn(),
                TableViewHelper.getTotalPointsColumn(), TableViewHelper.getCodeColumn());

        // Set the column resize policy to constrained resize policy
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        // Set the Placeholder for an empty table
        table.setPlaceholder(new Label("No visible columns and/or data exist."));

        // Create the VBox
        VBox root = new VBox();
        // Add the Table to the VBox
        root.getChildren().add(table);

        // Create the Scene
        Scene scene = new Scene(root);
        // Add the Scene to the Stage
        stage.setScene(scene);
        // Set the Title of the Stage
        stage.setTitle("A simple TableView Example");
        // Display the Stage
        stage.show();
    }
}
