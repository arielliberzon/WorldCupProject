
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;

import java.nio.file.attribute.GroupPrincipal;
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

        GroupTable one=new GroupTable(9, 5, list,"-fx-background-color: LIGHTGREY","A");
        GroupTable two=new GroupTable(9, 5, list,"-fx-background-color: LIGHTSALMON","B");
        GroupTable three=new GroupTable(9, 5, list,"-fx-background-color: white","C");
        GroupTable four=new GroupTable(9, 5, list,"-fx-background-color: LIGHTPINK","D");
        GroupTable five=new GroupTable(9, 5, list,"-fx-background-color: LIGHTCORAL","E");
        GroupTable six=new GroupTable(9, 5, list,"-fx-background-color: LIGHTYELLOW","F");
        GroupTable seven=new GroupTable(9, 5, list,"-fx-background-color: LIGHTCYAN","G");
        GroupTable eight=new GroupTable(9, 5, list,"-fx-background-color: LIGHTSKYBLUE","H");

        GridPane a = new GridPane();
        SplitPane center = new SplitPane();
        SplitPane sone = new SplitPane();
        SplitPane stwo = new SplitPane();
        center.setOrientation(Orientation.HORIZONTAL);
        sone.setOrientation(Orientation.VERTICAL);
        sone.setPrefSize(200, 200);
        stwo.setOrientation(Orientation.VERTICAL);
        stwo.setPrefSize(200, 200);
        VBox one1  = new VBox(one.tablePainting());
        VBox two2  = new VBox(two.tablePainting());
        VBox three3  = new VBox(three.tablePainting());
        VBox four4  = new VBox(four.tablePainting());
        VBox five5  = new VBox(five.tablePainting());
        VBox six6  = new VBox(six.tablePainting());
        VBox seven7  = new VBox(seven.tablePainting());
        VBox eight8  = new VBox(eight.tablePainting());
        sone.getItems().addAll(one1,two2,three3,four4);
        stwo.getItems().addAll(five5,six6,seven7,eight8);
        this.setAlignment(Pos.CENTER);
        VBox n = new VBox();
        VBox o = new VBox();
        n.getChildren().add(sone);
        o.getChildren().add(stwo);
        center.getItems().addAll(sone,stwo);

        this.getChildren().addAll(center);
        return a;
    }

    public static TableView groupTable(Group group) {
        TableView tableView = new TableView<>();

        // Add the columns:
        TableColumn<Team, String> countryCol = new TableColumn<>("Country");
        countryCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getCountry()));

        TableColumn<Team, String> pointsCol = new TableColumn<>("Points");
        pointsCol.setCellValueFactory(data ->
                new SimpleStringProperty(Integer.toString(data.getValue().groupPoints())));

        TableColumn<Team, String> winsCol = new TableColumn<>("Wins");
        winsCol.setCellValueFactory(data ->
                new SimpleStringProperty(Integer.toString(data.getValue().groupWins())));

        TableColumn<Team, String> drawsCol = new TableColumn<>("Draws");
        drawsCol.setCellValueFactory(data ->
                new SimpleStringProperty(Integer.toString(data.getValue().groupDraws())));

        TableColumn<Team, String> lossesCol = new TableColumn<>("Losses");
        lossesCol.setCellValueFactory(data ->
                new SimpleStringProperty(Integer.toString(data.getValue().groupLosses())));

        tableView.getColumns().addAll(
                countryCol, winsCol, drawsCol, lossesCol
        );

        // Add the teams
        for (Team team : group.getTeams())
            tableView.getItems().add(team);


        return tableView;

    }



}
