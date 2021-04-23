
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {
    private final StackPane root = new StackPane();
    private final BorderPane r = new BorderPane();
    private final GridPane masterpane = new GridPane();
    private Scene scene = new Scene(root);
    private Button play;
    private Image i;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("World Cup");
        showIntroScene(primaryStage);
        primaryStage.setMaximized(true);
    }
    public static void main(String[] args) {
        launch(args);
    }

    public TabPane topping(){
        TabPane n = new TabPane();
        Tab groupStageTab = new Tab("   GroupStage   ",new GroupStage());
        Tab knockoutStageTab = new Tab("   Knockout   ",new KnockoutPane());
        Tab qualifierStageTab = new Tab("   Qualifier   ");
        groupStageTab.setClosable(false);
        knockoutStageTab.setClosable(false);
        qualifierStageTab.setClosable(false);
        n.getTabs().addAll(qualifierStageTab,groupStageTab,knockoutStageTab);
        return n;
    }
//all the scene and pane are base on this method add and remove as you like
    private void showIntroScene(Stage window) {
        Image img = new Image("sc.jpg");
        masterpane.setBackground(new Background(new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        i = new Image("one.png");
        play = new Button("Play", new ImageView(i));
        play.setStyle("-fx-background-color: WHITE");
        masterpane.add(play, 0, 1);
        play.setTranslateX(1200);
        play.setTranslateY(800);
        r.setTop(topping());
        r.setTranslateY(50);
        root.getChildren().add(r);
        play.setOnAction(e -> window.setScene(scene));
        window.setScene(new Scene(masterpane));
        window.show();
    }
}
