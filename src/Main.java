
import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    private final StackPane root = new StackPane();
    private final BorderPane r = new BorderPane();
    private Scene scene = new Scene(root);
    private Button play = new Button("play");



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

    private void showIntroScene(Stage window) {
        GridPane masterpane = new GridPane();
        Image img = new Image("sc.jpg");
        masterpane.setBackground(new Background(new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT)));
        masterpane.add(play, 0, 1);
        play.setTranslateX(700);
        play.setTranslateY(500);
        r.setTop(topping());
        r.setTranslateY(50);
        root.getChildren().add(r);
        play.setOnAction(e -> window.setScene(scene));
        window.setScene(new Scene(masterpane));
        window.show();
    }


}
