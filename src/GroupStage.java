

import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;

import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.*;


public class GroupStage extends GridPane {
    public GroupStage(Double height, Double width){
        this.stagemain();
        this.setMaxHeight(height);
        this.setMaxWidth(width);
    }


    public GridPane stagemain(){


        Simulator simulator = new Simulator();
        Group group1 = simulator.getGroups().get(0);
        TableView groupView1 = GroupStage.groupTable(group1 );
        Group group2 = simulator.getGroups().get(1);
        TableView groupView2 = GroupStage.groupTable(group2 );
        Group group3 = simulator.getGroups().get(2);
        TableView groupView3 = GroupStage.groupTable(group3 );
        Group group4 = simulator.getGroups().get(3);
        TableView groupView4 = GroupStage.groupTable(group4 );
        Group group5 = simulator.getGroups().get(4);
        TableView groupView5 = GroupStage.groupTable(group5 );
        Group group6 = simulator.getGroups().get(5);
        TableView groupView6 = GroupStage.groupTable(group6 );
        Group group7 = simulator.getGroups().get(6);
        TableView groupView7 = GroupStage.groupTable(group7 );
        Group group8 = simulator.getGroups().get(7);
        TableView groupView8 = GroupStage.groupTable(group8 );

        GridPane a = new GridPane();
        GridPane center = new GridPane();

        VBox one1  = new VBox(groupView1);
        VBox two2  = new VBox(groupView2);
        VBox three3  = new VBox(groupView3);
        VBox four4  = new VBox(groupView4);
        VBox five5  = new VBox(groupView5);
        VBox six6  = new VBox(groupView6);
        VBox seven7  = new VBox(groupView7);
        VBox eight8  = new VBox(groupView8);
        center.add(one1,1,0);
        center.add(two2,2,0);
        center.add(three3,1,1);
        center.add(four4,2,1);
        center.add(five5,1,2);
        center.add(six6,2,2);
        center.add(seven7,1,3);
        center.add(eight8,2,3);
        this.setAlignment(Pos.CENTER);

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
                countryCol, winsCol, drawsCol, lossesCol,pointsCol
        );
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        tableView.setEditable(false);

        // Add the teams
        for (Team team : group.getTeams())
            tableView.getItems().add(team);


        return tableView;

    }




}
