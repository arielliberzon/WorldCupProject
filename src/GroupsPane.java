import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;


/**
 * @author John Youte, Harjit Singh
 * A class that extends gridpane
 * This class is displaying informations for the eight groups in the group
 * stage tab. it take informations from the similator class and put these informations in a tableView.
 */
        public class GroupsPane extends GridPane {
        private Simulator simulator;

        public GroupsPane(Double height, Double width, Simulator simulator) {
                this.simulator = simulator;
                this.createMainStage();
                this.setMaxHeight(height);
                this.setMaxWidth(width);
        }

        /**
         * @author John Youte, Harjit Singh
         * Calls getGroups() from the similator class
         * Creates eight tableView with the group information.
         * @return a
         */
        public GridPane createMainStage(){

                GridPane mainPane = new GridPane();
                GridPane centerPane = new GridPane();
                ArrayList<Group> groupList = simulator.getGroups();
                int evenCount = 0;
                for (int i = 0; i < groupList.size(); i++) {
                        Group group = groupList.get(i);
                        TableView groupTable = createGroupTable(group);
                        VBox vBox = new VBox(createButtonBar(group , groupTable) , groupTable);
                        vBox.setStyle(colorArrayList().get(i)); //calls the ColorArrayList method to color the backgroup of the Vbox.
                        if (i % 2 == 0) { //Conditional statement to give the index for the gridPane, if Gridpane have 2 tableViews, it change to second index.
                                centerPane.add(vBox, 1, evenCount);
                        } else {
                                centerPane.add(vBox, 2, evenCount);
                                evenCount++;
                        }
                }
                centerPane.setHgap(5); //horizontal gap in pixels => that's what you are asking for
                centerPane.setVgap(5); //vertical gap in pixels
                centerPane.setPadding(new Insets(10, 10, 10, 10));
                ScrollPane scrollPane = new ScrollPane(centerPane); //User friendly pane, for the users with a small screen resolution.
                this.setAlignment(Pos.CENTER);
                this.getChildren().addAll(scrollPane);
                return mainPane;
        }

        /**
         * @author John Youte, Harjit Singh
         * This method is made to give different background color for the vBox,
         * ArrayList of String which adds different colors to the Arraylist.
         * @return colorArrayList
         */
        public ArrayList<String> colorArrayList(){
                ArrayList<String> colorArrayList= new ArrayList<>();
                colorArrayList.add("-fx-background-color: #1687a7");
                colorArrayList.add("-fx-background-color: #b34180");
                colorArrayList.add("-fx-background-color: #af0069");
                colorArrayList.add("-fx-background-color: #16c79a");
                colorArrayList.add("-fx-background-color: #295939");
                colorArrayList.add("-fx-background-color: #ff577f");
                colorArrayList.add("-fx-background-color: #1687a7");
                colorArrayList.add("-fx-background-color: #ffe227");
                 return colorArrayList;
        }

        /**
         * @author John Youte
         * This method is  taking a group and a tableView as parameter.
         * creating buttons and putting them on top of the tableView.
         * @return hBox
         */
        private HBox createButtonBar(Group group,TableView table){
            HBox hBox=new HBox();
            for (int i = 0; i < group.getTeams().size(); i++) {
                    TeamButton teamButton = new TeamButton();
                    teamButton.setTeam(group.getTeams().get(i));
                    teamButton.removeName();
                    hBox.getChildren().add(teamButton);
                    hBox.setPrefWidth(5);
            }
            hBox.setSpacing(50);
            hBox.setAlignment(Pos.CENTER);

            return hBox;
        }




        /**
         * @author Harjit Singh
         * and we'll take it from there
         * @param group
         * @return table
         */
        private TableView createGroupTable(Group group) {
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
                BackgroundFill background_fill = new BackgroundFill(Color.TRANSPARENT,
                        CornerRadii.EMPTY, Insets.EMPTY);
                Background background = new Background(background_fill);
                table.setBackground(background);

                return table;
        }
}