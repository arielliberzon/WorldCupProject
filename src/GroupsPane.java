import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.*;

import java.util.ArrayList;


/**
 * @author John Youte, Harjit Singh
 * A class that extends gridpane
 * This class is displaying informations for the eight groups in the group
 * stage tab. it take information from the similator class and put these informations in a tableView.
 */
public class GroupsPane extends GridPane {
        private Simulator simulator;
        private GridPane centerPane = new GridPane();
        ScrollPane scrollPane;
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
         */
        public void createMainStage(){

                ArrayList<Group> groupList = simulator.getGroups();
                int evenCount = 0;
                for (int i = 0; i < groupList.size(); i++) {
                        Group group = groupList.get(i);
                        TableView<Team> groupTable = createGroupTable(group, i);
                        VBox vBox = new VBox(createButtonBar(group) , groupTable);
                        // calls the ColorArrayList method to color the back group of the Vbox.
                        vBox.setStyle(oddColorArrayList().get(i));
                        //Conditional statement to give the index for the gridPane, if GridPane have 2 tableViews, it change to second index.
                        if (i % 2 == 0) {
                                centerPane.add(vBox, 1, evenCount);
                        } else {
                                centerPane.add(vBox, 2, evenCount);
                                evenCount++;
                        }
                }
                centerPane.setHgap(9); //horizontal gap in pixels => that's what you are asking for
                centerPane.setVgap(7); //vertical gap in pixels
                centerPane.setPadding(new Insets(10, 10, 10, 10));
                scrollPane = new ScrollPane(centerPane);//User friendly pane, for the users with a small screen resolution.
                this.setAlignment(Pos.CENTER);
                this.getChildren().addAll(scrollPane);
        }

        /**
         * @author John Youte, Harjit Singh
         * This method is made to give different background color for the vBox,
         * ArrayList of String which adds different colors to the Arraylist.
         * @return oddColorArrayList
         */
        public ArrayList<String> oddColorArrayList(){
                ArrayList<String> colorArrayList= new ArrayList<>();
                colorArrayList.add("-fx-background-color: #fdb092");
                colorArrayList.add("-fx-background-color: LIGHTYELLOW");
                colorArrayList.add("-fx-background-color: #fdc5cf");
                colorArrayList.add("-fx-background-color: LIGHTCYAN");
                colorArrayList.add("-fx-background-color: #e79e9e");
                colorArrayList.add("-fx-background-color: #C1DE94");
                colorArrayList.add("-fx-background-color: #9cc9fc");
                colorArrayList.add("-fx-background-color: #eae7a6");
                return colorArrayList;
        }

        /**
         * @author John Youte, Harjit Singh
         * This method is made to give different background color for the vBox,
         * ArrayList of String which adds different colors to the Arraylist.
         * @return oddColorArrayList
         */
        public ArrayList<String> evenColorArrayList(){
                ArrayList<String> colorArrayList= new ArrayList<>();
                colorArrayList.add("-fx-background-color: LIGHTSALMON");
                colorArrayList.add("-fx-background-color: #ffffcc");
                colorArrayList.add("-fx-background-color: LIGHTPINK");
                colorArrayList.add("-fx-background-color: #d1ffff");
                colorArrayList.add("-fx-background-color: LIGHTCORAL");
                colorArrayList.add("-fx-background-color: C1DE82");
                colorArrayList.add("-fx-background-color: LIGHTSKYBLUE");
                colorArrayList.add("-fx-background-color: KHAKI");
                return colorArrayList;
        }

        /**
         * @author John Youte
         * This method is  taking a group and a tableView as parameter.
         * creating buttons and putting them on top of the tableView.
         * @return hBox
         */
        private HBox createButtonBar(Group group){
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
         * Create the tableveiw for groups each time methods is called.
         * and methods gets the info from the Team class.(country name, points,etc).
         * Tableview column are been created in TableViewHelper Class.
         * @param group Any Group in the groups Stage
         * @param index index for each group (ex: 0 - 7)
         * @return table
         */
        private TableView<Team> createGroupTable(Group group, int index) {
                TableView<Team> table = new TableView<>();
                TableColumn<Team, String> groupHeader = new TableColumn<>(group.toString());//Header of the Group on the Table

                table.getItems().addAll(TableViewHelper.getGroupList(group));

                groupHeader.getColumns().addAll(TableViewHelper.getGroupCountry(),//Tableview  use the columns from the TableViewHelper class
                        TableViewHelper.getGroupWinsColumn(), TableViewHelper.getGroupDrawsColumn(),
                        TableViewHelper.getGroupLossesColumn(), TableViewHelper.getGAColumn(),
                        TableViewHelper.getGFColumn(), TableViewHelper.getGDColumn(),
                        TableViewHelper.getPointsColumn());

                table.getColumns().addAll(groupHeader);
                 table.setFixedCellSize(25);//cell size
                table.prefHeightProperty().bind(
                        Bindings.size(table.getItems()).multiply(table.getFixedCellSize()).add(55));
                table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                table.setEditable(false);

                table.setRowFactory(tableView -> new TableRow<Team>() {
                        @Override
                        public void updateItem(Team item, boolean empty) {
                                super.updateItem(item, empty);
                                if (getIndex() % 2 == 0) {
                                        setStyle(evenColorArrayList().get(index));
                                } else {
                                        setStyle(oddColorArrayList().get(index));
                                }
                        }
                        @Override
                        public void updateSelected(boolean empty) {
                                super.updateSelected(empty);
                                if (isSelected()) {
                                        updateSelected(false);
                                }
                        }
                });

                return table;
        }

}