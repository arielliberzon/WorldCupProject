import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

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
                int evenCount = 0;
                for (int i = 0; i < groupList.size(); i++) {
                        Group group = groupList.get(i);
                        //TableView groupTable = GroupPane.groupTable(group, "Group " + groupChar );
                        TableView table = new TableView<>();
                        TableColumn<Team, String> groupHeader = new TableColumn<>(group.toString());

                        table.getItems().addAll(TableViewHelper.getGroupList(group));

                        groupHeader.getColumns().addAll(TableViewHelper.getGroupCountry(),
                                TableViewHelper.getGroupWinsColumn(), TableViewHelper.getGroupDrawsColumn(),
                                TableViewHelper.getGroupLossesColumn(), TableViewHelper.getGAColumn(),
                                TableViewHelper.getGFColumn(), TableViewHelper.getGDColumn(),
                                TableViewHelper.getPointsColumn());

                        table.getColumns().addAll(groupHeader);

                        table.setFixedCellSize(25);
                        table.prefHeightProperty().bind(
                                Bindings.size(table.getItems()).multiply(table.getFixedCellSize()).add(55));

                        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

                        VBox vBox = new VBox(createButtonBar(group , table) ,table);
                        vBox.setStyle("-fx-background-color: #FFFFFF;");
                        if (i % 2 == 0) {
                                center.add(vBox, 1, evenCount);
                        }else {
                                center.add(vBox, 2, evenCount);
                                evenCount++;

                        }
                }
                center.setHgap(5); //horizontal gap in pixels => that's what you are asking for
                center.setVgap(5); //vertical gap in pixels
                center.setPadding(new Insets(10, 10, 10, 10));
                ScrollPane sp = new ScrollPane(center);
                this.setAlignment(Pos.CENTER);
                this.getChildren().addAll(sp);
                return a;
        }

        private HBox createButtonBar(Group group,TableView table){
            HBox hBox=new HBox();
            for (int i = 0; i < group.getTeams().size(); i++) {
                    TeamButton teamButton = new TeamButton();
                    teamButton.setTeam(group.getTeams().get(i));
                    hBox.getChildren().add(teamButton);
                    hBox.setPrefWidth(5);
            }
            hBox.setSpacing(50);
            hBox.setAlignment(Pos.CENTER);

            return hBox;
        }

        public static TableView groupTable(Group group,String c) {
                TableView tableView = new TableView<>();
                // Add the columns:
                TableColumn<Team, String> groupname   = new TableColumn<>(c);
                groupname.getColumns().addAll(TableViewHelper.getGroupCountry(),TableViewHelper.getGroupWinsColumn(),
                        TableViewHelper.getGroupDrawsColumn(), TableViewHelper.getGroupLossesColumn(), TableViewHelper.getGAColumn()
                        ,TableViewHelper.getGFColumn(),TableViewHelper.getGDColumn(),TableViewHelper.getPointsColumn());
                tableView.getColumns().addAll(groupname);
                tableView.setFixedCellSize(20);
                tableView.prefHeightProperty().bind(Bindings.size(tableView.getItems()).multiply(tableView.getFixedCellSize()).add(52));
                tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                // Add the teams
                BackgroundFill background_fill = new BackgroundFill(Color.TRANSPARENT.invert(),
                        CornerRadii.EMPTY, Insets.EMPTY);
                Background background = new Background(background_fill);
                tableView.setBackground(background);

                for (Team team : group.getTeams())
                        tableView.getItems().add(team);
                return tableView;

        }
        }