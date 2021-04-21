
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



    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("World Cup");
       /**Image img = new Image("sc.jpg");
        root.setBackground(new Background(new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
        BackgroundSize.DEFAULT)));*/
        r.setTop(topping());
        r.setTranslateY(50);
        root.getChildren().add(r);
        Scene scene = new Scene(root);
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.show();


    }
    public static void main(String[] args) {
        launch(args);
    }

    public TabPane topping(){
        TabPane n = new TabPane();
        Tab groupStageTab = new Tab("   GroupStage   ",new GroupStage());
        Tab knockoutStageTab = new Tab("   Knockout   ",new KnockoutPane());
        Tab qualiferStageTab = new Tab("   Qualifier   ");
        groupStageTab.setClosable(false);
        knockoutStageTab.setClosable(false);
        qualiferStageTab.setClosable(false);
        n.getTabs().addAll(qualiferStageTab,groupStageTab,knockoutStageTab);
        return n;
    }




}