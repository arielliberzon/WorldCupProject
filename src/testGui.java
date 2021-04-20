import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Point2D;
//import javafx.event.Event;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class testGui extends Application {

    private static Stage window;
    private static ArrayList<Button> nodes = new ArrayList<>();
    ArrayList<Button> buttonTeamList;
    private KnockoutPane knockoutPane = new KnockoutPane();


    @Override public void start(Stage stage) {
        //car1.carFire();
        Pane pane = new Pane();
        //pane.getChildren().add();

        Group root = new Group();
        Rectangle rect = new Rectangle(10,10);
        Scene scene = new Scene(root);

        rect.setFill(Color.GREEN);
        HBox box = new HBox();


        root.getChildren().add(knockoutPane);



        stage.setTitle("ImageView");

        stage.setScene(scene);
        stage.show();


    }



    public static void main(String[] args) {

        launch(args);

    }
}