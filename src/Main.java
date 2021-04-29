import javafx.application.Application;
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

public class Main extends Application {

    private BorderPane rootPane = new BorderPane();
    private GridPane starterPane = new GridPane();
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
     * @author Harjit Sighn
     * @param window
     */
    private void showIntroScene(Stage window) {
        Image img = new Image("Images/background.jpg");
        starterPane.setBackground(new Background(new BackgroundImage(img, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        Button startButton = new Button("Start");
        startButton.setStyle("-fx-background-color: LIGHTGREY");
        starterPane.add(startButton, 0, 1);
        startButton.setTranslateX(800);
        startButton.setTranslateY(500);
        window.setScene(new Scene(starterPane));
        window.setMaximized(true);
        window.show();

        rootPane.setTop(createTopping(window.getHeight() - 60, window.getWidth()));
        startButton.setOnAction(e -> window.setScene(scene));

    }

    /**
     * TODO: description
     * @param height
     * @param width
     * @return
     */
    private TabPane createTopping(Double height, Double width){

        TabPane tabPane = new TabPane();

        QualifierPane teamsPane = new QualifierPane(simulator.getTeamMap(), height, width);
        GroupStage groupPane = new GroupStage(height, width, simulator);
        KnockoutPane knockoutPane = new KnockoutPane(simulator);

        Tab qualifierStageTab = new Tab("   Teams   ", teamsPane);
        Tab groupStageTab = new Tab("   Group Stage   ", groupPane);
        Tab knockoutStageTab = new Tab("   Knockout Stage  ", knockoutPane);



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
