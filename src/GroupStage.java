/** note for Zach (delete later):
 *   this class will create the pane for the group stage tab,
 *   Main is composed of this (I'm pretty sure)
 *      - Justin V
 */
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

import java.util.ArrayList;


public class GroupStage extends Pane {

    ArrayList<Line> Horizontal= new ArrayList<>();
    ArrayList<Line> vertical=new ArrayList<>();
    Pane pane=new Pane();

    public GroupStage(){
        this.tableMaker();
    }


    private Pane tableMaker(){
        int countY=100;
        int countYY=226;
        int countX=102;
        for (int i = 0; i < 6; i++) {
            Horizontal.add(new Line(0, 0, 700,0));
            Horizontal.get(i).setLayoutX(50);
            Horizontal.get(i).setLayoutY(countY);
            pane.getChildren().add(Horizontal.get(i));
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
