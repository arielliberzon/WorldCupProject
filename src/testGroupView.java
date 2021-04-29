import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class testGroupView extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Simulator simulator = new Simulator();
        Group group = simulator.getGroups().get(0);
        TableView groupView = GroupStage.groupTable(group);
        primaryStage.setScene(new Scene(groupView));
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}