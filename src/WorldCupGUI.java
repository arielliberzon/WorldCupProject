import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * This is the application's main class, run it to view the simulator. It has a
 * starterPane that displays an image and has a "Start" button. It also creates a
 * rootPane which that contains tabs for each aspect of the World Cup (all national
 * teams, the tournament group stage, and the tournaments knock out stage). It also
 * has a "Help" button to quickly break down what each feature of the simulator. A
 * "Reset" button was added to relaunch the simulator without having to close the
 * application.
 */

// TODO: Change class name to WorldCupGUI
public class WorldCupGUI extends Application {

    private BorderPane rootPane = new BorderPane();
    private GridPane starterPane = new GridPane();
    private Scene scene = new Scene(rootPane);
    private Simulator simulator;
    private VBox stackPane;
    private Stage window;

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        primaryStage.setTitle("World Cup");
        showIntroScene(primaryStage);
        primaryStage.setMaximized(true);
    }

    /**
     * TODO: Add description
     * @author Harjit Singh
     * @param window
     */
    private void showIntroScene(Stage window) {
        Image img = new Image("Images/background.jpg");
        starterPane.setBackground(new Background(new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        Button startButton = new Button("Start");
        startButton.setStyle("-fx-background-color: LIGHTGREY");
        starterPane.add(startButton, 0, 1);
        startButton.setTranslateX(800);
        startButton.setTranslateY(500);
        window.setScene(new Scene(starterPane));
        window.setMaximized(true);
        window.show();
        initialize();
        startButton.setOnAction(e -> window.setScene(scene));
    }


    /**
     * @author Ariel Liberzon
     * A function which produces an HBox consisting of multple buttons
     * @return HBox ButtonBar
     */
    private HBox createButtonBar() {
        HBox buttonBar = new HBox();
        buttonBar.setPadding(new Insets(5, 10, 5, 10));
        buttonBar.setSpacing(10);
        //buttonBar.setStyle("-fx-background-color: #589257ff");
        buttonBar.setBackground(new Background(new BackgroundImage(new Image("Images/grass.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT))); //new BackgroundSize(width, height,true,true,true,true)

        Button helpButton = new Button("Help");;
        Button resetButton = new Button("Reset");
        //Added by Samuel Hernandez
        resetButton.setOnAction(e -> initialize());
        helpButton.setOnAction(e -> help());
        buttonBar.getChildren().addAll(helpButton, resetButton);
        return buttonBar;
    }
    /**
     * @author Samuel Hernandez
     * Method sets up and starts the buttons, tab and pane that holds and
     * shows the world cup tournament information It also creates the
     * simulator object and simulates the tournament. Method designed to
     * allow to reset the game without showing the welcome screen again.
     */
    private void initialize(){
        simulator = new Simulator();
        stackPane = new VBox();
        HBox buttonBar = createButtonBar();
        TabPane tabs = createTabPane(window.getHeight() - 100, window.getWidth());
        stackPane.getChildren().addAll(buttonBar, tabs);
        rootPane.setTop(stackPane);
    }

    /**
     * @author Harjit Singh
     * Displays Alert message to Help the user and them
     * user-friendly experience
     * @return alert
     */
    private Alert help(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText(null);
        alert.setContentText("WE ARE HERE TO HELP YOU" +
                "\n-Click on the \"Teams\" tab to see all confederations" +
                "\n-Search your team in the search-bar by country name or country code"+
                "\n-Click on the \"Group Stage\" tab to see all 8 groups" +
                "\n-Click on the \"Knockout Stage\" tab to simulate for the winner" +
                "\n-Click on the \"Reset\" button to reset the simulator");

        alert.show();
        return alert;
    }

    /**
     * TODO: description
     * @author Harjit Singh
     * @param height
     * @param width
     * @return tabPane
     */
    private TabPane createTabPane(Double height, Double width){

        TabPane tabPane = new TabPane();
        Tab qualifierStageTab = new Tab("   Teams   ",new TeamsPane(height, width,simulator));
        Tab groupStageTab = new Tab("   Group Stage   ",new GroupPane(height, width, simulator));
        Tab knockoutStageTab = new Tab("   Knockout Stage  ",new KnockoutPane(height, width, simulator));
        groupStageTab.setClosable(false);
        knockoutStageTab.setClosable(false);
        qualifierStageTab.setClosable(false);
        tabPane.getTabs().addAll(qualifierStageTab,groupStageTab,knockoutStageTab);
        return tabPane;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
