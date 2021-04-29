import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    private final StackPane root = new StackPane();
    private final BorderPane r = new BorderPane();
    private final GridPane masterPane = new GridPane();
    private Scene scene = new Scene(root);
    private Button play;// spacing it out for now add image later
    //private TeamInfo info = new TeamInfo();

    private QualifierPane qualifierPane;
    private GroupStage groupStage;
    private KnockoutPane knockoutPane;
    private TabPane tabPane;

    private Simulator simulator = new Simulator();

    public Main() throws IOException {
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("World Cup");
        showIntroScene(primaryStage);
        primaryStage.setMaximized(true);
    }
    public static void main(String[] args) {
        launch(args);
    }

    private void setTopping(Double height, Double width){

        // HARDCODED QUALIFIERS//
        qualifierPane = new QualifierPane(simulator.getTeamMap(), height, width);
        groupStage = new GroupStage(height, width, simulator);
        knockoutPane = new KnockoutPane(simulator);

        qualifierPane.updateTeamMap(simulator.getQualifiedTeams());
        simulator.simulateGroups();
        // END OF HARDCODED TEST //

        tabPane = new TabPane();
        Tab groupStageTab = new Tab("   Group Stage   ", groupStage);
        Tab knockoutStageTab = new Tab("   Knockout Stage  ", knockoutPane);
        Tab qualifierStageTab = new Tab("   Teams   ", qualifierPane);


        groupStageTab.setClosable(false);
        knockoutStageTab.setClosable(false);
        qualifierStageTab.setClosable(false);
        tabPane.getTabs().addAll(qualifierStageTab,groupStageTab,knockoutStageTab);
    }

    private TabPane getTopping() {
        return tabPane;
    }

    private HBox getButtonBar() {
        HBox hBox = new HBox();
        Button simulateAll = new Button("Simulate Everything");
        hBox.getChildren().addAll(hBox);
        return hBox;
    }

    //all the scene and pane are base on this method add and remove as you like
    private void showIntroScene(Stage window) {
        Image img = new Image("Images/background.jpg");
        masterPane.setBackground(new Background(new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        play = new Button("Start>>");
        play.setStyle("-fx-background-color: LIGHTGREY");
        masterPane.add(play, 0, 1);
        play.setTranslateX(800);
        play.setTranslateY(500);
        window.setScene(new Scene(masterPane));
        window.setMaximized(true);
        window.show();

        setTopping(window.getHeight(), window.getWidth());
        r.setTop(getTopping());
        r.setTranslateY(50);
        root.getChildren().add(r);
        play.setOnAction(e -> window.setScene(scene));
        
    }
}
