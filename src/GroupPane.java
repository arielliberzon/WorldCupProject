import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.*;

import java.util.ArrayList;


        // TODO: Change class name to GroupPane
        // TODO: Add description and comments
        public class GroupPane extends GridPane {
        private Simulator simulator;
        public GroupPane(Double height, Double width, Simulator simulator){
                this.simulator = simulator;
                this.stagemain();
                this.setMaxHeight(height);
                this.setMaxWidth(width);
        }


        public GridPane stagemain(){
                GridPane a = new GridPane();
                GridPane center = new GridPane();
                ArrayList<Group> groupList = simulator.getGroups();
                char groupChar = 'A';
                int count = 0;
                int evenCount = 0;
                for (int i = 0; i < groupList.size(); i++) {
                        Group group = groupList.get(i);
                        TableView groupTable = GroupPane.groupTable(group, "Group " + groupChar );
                        VBox vBox = new VBox(createButtonBar(group , groupTable) , groupTable);
                        if (count % 2 == 0) {
                                center.add(vBox, 1, evenCount);
                        }else {
                                center.add(vBox, 2, evenCount);
                                evenCount++;

                        }
                        count++;
                        groupChar++;
                }
                center.setHgap(10); //horizontal gap in pixels => that's what you are asking for
                center.setVgap(10); //vertical gap in pixels
                center.setPadding(new Insets(10, 10, 10, 10));
                center.setTranslateY(-55);
                this.setAlignment(Pos.CENTER);
                this.getChildren().addAll(center);
                return a;
        }

        private HBox createButtonBar(Group group,TableView table){
            HBox hBox=new HBox();
            for (int i = 0; i < group.getTeams().size(); i++) {
                    TeamButton teamButton = new TeamButton();
                    teamButton.setTeam(group.getTeams().get(i));
                    hBox.getChildren().add(teamButton);
            }
            hBox.setSpacing(50);
            hBox.setAlignment(Pos.CENTER);

            return hBox;
        }

        public static TableView groupTable(Group group,String c ) {
                TableView tableView = new TableView<>();
                TeamButton buttons=new TeamButton();

                // Add the columns:
                TableColumn<Team, String> groupname   = new TableColumn<>(c);
                TableColumn<Team, String> countryCol = new TableColumn<>("Country");
                countryCol.setCellValueFactory(data ->
                        new SimpleStringProperty(data.getValue().getCountry()));


                TableColumn<Team, String> pointsCol = new TableColumn<>("Points");
                pointsCol.setCellValueFactory(data ->
                        new SimpleStringProperty(Integer.toString(data.getValue().groupPoints())));

                TableColumn<Team, String> winsCol = new TableColumn<>("Wins");
                winsCol.setCellValueFactory(data ->
                        new SimpleStringProperty(Integer.toString(data.getValue().getGroupWins())));

                TableColumn<Team, String> drawsCol = new TableColumn<>("Draws");
                drawsCol.setCellValueFactory(data ->
                        new SimpleStringProperty(Integer.toString(data.getValue().getGroupDraws())));

                TableColumn<Team, String> lossesCol = new TableColumn<>("Losses");
                lossesCol.setCellValueFactory(data ->
                        new SimpleStringProperty(Integer.toString(data.getValue().getGroupLosses())));

                TableColumn<Team, String> gf = new TableColumn<>("GF");
                gf.setCellValueFactory(data ->
                        new SimpleStringProperty(Integer.toString(data.getValue().getGroupWins())));

                TableColumn<Team, String> ga = new TableColumn<>("GD");
                ga.setCellValueFactory(data ->
                        new SimpleStringProperty(Integer.toString(data.getValue().getGroupDraws())));

                TableColumn<Team, String> gd = new TableColumn<>("GA");
                gd.setCellValueFactory(data ->
                        new SimpleStringProperty(Integer.toString(data.getValue().getGroupLosses())));



                groupname.getColumns().addAll(countryCol, winsCol, drawsCol, lossesCol,gf,ga,gd,pointsCol);
                tableView.getColumns().addAll(groupname);
                tableView.setFixedCellSize(25);
                tableView.prefHeightProperty().bind(Bindings.size(tableView.getItems()).multiply(tableView.getFixedCellSize()).add(55));
                tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                // Add the teams
                for (Team team : group.getTeams())
                        tableView.getItems().add(team);


                return tableView;

        }
        }