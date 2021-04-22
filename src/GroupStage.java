
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

import java.util.ArrayList;


public class GroupStage extends GridPane {

    private ArrayList<Line> horizontal= new ArrayList<>();
    private ArrayList<Line> vertical=new ArrayList<>();
    private Pane pane=new Pane();



    public GroupStage(){
        this.stagemain();
    }
    public GridPane stagemain(){

        GridPane g = new GridPane();
        g.add(tableMaker(),1,0);
        this.getChildren().add(g);
        return g;
    }


    public Pane tableMaker(){
        int countY=100;
        int countYY=226;
        int countX=102;
        for (int i = 0; i < 6; i++) {
            horizontal.add(new Line(0, 0, 700,0));
            horizontal.get(i).setLayoutX(50);
            horizontal.get(i).setLayoutY(countY);
            pane.getChildren().add(horizontal.get(i));
            countY +=50;
        }


        for (int i = 0; i < 10; i++) {
            vertical.add(new Line(0, 0, 250,0));
            vertical.get(i).setRotate(90);
            //little conditional
            if (vertical.size()==1) {
                vertical.get(i).setLayoutY(countYY);
                vertical.get(i).setLayoutX(-75);
            }
            else {
                vertical.get(i).setLayoutY(countYY);
                vertical.get(i).setLayoutX(countX);
            }
            pane.getChildren().add(vertical.get(i));
            countX +=58;
        }
        this.getChildren().add(pane);
        return pane;
    }
}
