
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;

import javax.swing.table.TableColumn;
import java.util.ArrayList;


public class GroupStage extends GridPane {
    public GroupStage(){
        this.stagemain();
    }
    public GridPane stagemain(){


        ArrayList<String> list=new ArrayList<String>();
        for (int i = 0; i < 45; i++) {
            list.add("boom1");
        }

        GroupTable one=new GroupTable(9, 5, list,"-fx-background-color: green","A");
        GroupTable two=new GroupTable(9, 5, list,"-fx-background-color: red","B");
        GroupTable three=new GroupTable(9, 5, list,"-fx-background-color: white","C");
        GroupTable four=new GroupTable(9, 5, list,"-fx-background-color: blue","D");
        GroupTable five=new GroupTable(9, 5, list,"-fx-background-color: orange","E");
        GroupTable six=new GroupTable(9, 5, list,"-fx-background-color: yellow","F");
        GroupTable seven=new GroupTable(9, 5, list,"-fx-background-color: green","G");

        GridPane a = new GridPane();
        SplitPane s = new SplitPane();
        s.setOrientation(Orientation.VERTICAL);
        s.setPrefSize(200, 200);
        VBox one1  = new VBox(one.tablePainting());
        VBox two2  = new VBox(two.tablePainting());
        VBox three3  = new VBox(three.tablePainting());
        VBox four4  = new VBox(four.tablePainting());
        VBox five5  = new VBox(five.tablePainting());
        VBox six6  = new VBox(six.tablePainting());
        VBox seven7  = new VBox(seven.tablePainting());
        s.getItems().addAll(one1,two2,three3,four4,five5,six6,seven7);
         this.setAlignment(Pos.CENTER);
         VBox n = new VBox(s);
        this.getChildren().addAll(n);
        return a;
    }



}
