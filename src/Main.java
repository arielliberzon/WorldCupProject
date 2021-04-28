import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    private final StackPane root = new StackPane();
    private final BorderPane r = new BorderPane();
    private final GridPane masterpane = new GridPane();
    private Scene scene = new Scene(root);
    private Button play;// spacing it out for now add image later
    private TeamInfo info = new TeamInfo();
    // TODO: ADD buttons to simulate; currently hardcoded when running
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

    public TabPane topping(Double height, Double width){

        // HARDCODED QUALIFIERS//
        QualifierPane qualifierPane = new QualifierPane(info.getTeamMap(), height, width);
        qualifierPane.updateTeamMap(simulator.getQualifiedTeams(info));
        // END OF HARDCODED TEST //

        TabPane n = new TabPane();
        Tab groupStageTab = new Tab("   Group Stage   ", new GroupStage());
        Tab knockoutStageTab = new Tab("   Knockout Stage  ",new KnockoutPane(simulator));
        Tab qualifierStageTab = new Tab("   Teams   ", qualifierPane);


        groupStageTab.setClosable(false);
        knockoutStageTab.setClosable(false);
        qualifierStageTab.setClosable(false);
        n.getTabs().addAll(qualifierStageTab,groupStageTab,knockoutStageTab);
        return n;
    }
//all the scene and pane are base on this method add and remove as you like
    private void showIntroScene(Stage window) {
        Image img = new Image("Images/background.jpg");
        masterpane.setBackground(new Background(new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        play = new Button("Start>>");
        play.setStyle("-fx-background-color: LIGHTGREY");
        masterpane.add(play, 0, 1);
        play.setTranslateX(800);
        play.setTranslateY(500);
        window.setScene(new Scene(masterpane));
        window.setMaximized(true);
        window.show();

        r.setTop(topping(window.getHeight(), window.getWidth()));
        r.setTranslateY(50);
        root.getChildren().add(r);
        play.setOnAction(e -> window.setScene(scene));
        
    }
}
