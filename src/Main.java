
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.layout.*;

import javafx.stage.Stage;

public class Main extends Application {
    private final StackPane root = new StackPane();
    private final BorderPane r = new BorderPane();
    private GroupStage groupStage;


    @Override
    public void start(Stage primaryStage) {

        r.setTop(topping());
        r.setTranslateY(40);
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
        Tab stage = new Tab("   GroupStage   ",groupStage);
        Tab out = new Tab("   Knockout   ");
        Tab in = new Tab("   Qualifier   ");
        stage.setClosable(false);
        out.setClosable(false);
        in.setClosable(false);
        n.getTabs().addAll(stage,out,in);
        return n;
    }

}
