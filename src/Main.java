import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * This is the main class which starts the application. It has a starterPane
 * that displays an image and has a "Start" button. It also creates a rootPane
 * which that contains tabs for each aspect of the World Cup (all national teams,
 * the tournament group stage, and the tournaments knock out stage). TODO: finish
 */

// TODO: Change class name to WorldCupGUI
public class Main extends Application {

    private BorderPane rootPane = new BorderPane();
    private GridPane starterPane = new GridPane();
    private VBox stackPane = new VBox();
    private Scene scene = new Scene(rootPane);
    private Simulator simulator = new Simulator();

    @Override
    public void start(Stage primaryStage) {
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
        stackPane.getChildren().addAll(createButtonBar(),
                createTabPane(window.getHeight() - 100, window.getWidth()));
        rootPane.setTop(stackPane);
        startButton.setOnAction(e -> window.setScene(scene));

    }

    /**
     * @author Ariel Liberzon
     * TODO: ADD description and use for buttons
     * @return HBox ButtonBar
     */
    private HBox createButtonBar() {
        HBox buttonBar = new HBox();
        buttonBar.setPadding(new Insets(5, 10, 5, 10));
        buttonBar.setSpacing(10);
        buttonBar.setStyle("-fx-background-color: #589257ff");
        Button helpButton = new Button("Help");
        helpButton.setDisable(true);
        Button resetButton = new Button("Reset");
        resetButton.setDisable(true);
        buttonBar.getChildren().addAll(helpButton, resetButton);
        return buttonBar;
    }

    /**
     * TODO: description
     * @author Harjit Singh
     * @param height
     * @param width
     * @return
     */
    private TabPane createTabPane(Double height, Double width){

        TabPane tabPane = new TabPane();
        Tab qualifierStageTab = new Tab("   Teams   ",new QualifierPane(height, width,simulator));
        Tab groupStageTab = new Tab("   Group Stage   ",new GroupStage(height, width, simulator));
        Tab knockoutStageTab = new Tab("   Knockout Stage  ",new KnockoutPane(simulator));
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
