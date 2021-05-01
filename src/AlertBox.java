// Justin Valas
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

    public AlertBox() {
        // default constructor.
    }

    public AlertBox(Pane test){
        showMessageDialogue(test);
    }

    public static void showMessageDialogue(Pane test) {


        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle("Games"); // come back to

        Pane pane = new Pane();

        
        pane.getChildren().add(test);
        
        

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();

    }
}