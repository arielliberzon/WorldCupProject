import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;


/**
 * @author John Youte, Harjit Singh
 * A class that extends gridpane
 * This class is displaying informations for the eight groups in the group
 * stage tab. it take information from the similator class and put these informations in a tableView.
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
                        addTableColors(groupTable, i);
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
                colorArrayList.add("-fx-background-color: #fde9d9");
                colorArrayList.add("-fx-background-color: #e6b8b7");
                colorArrayList.add("-fx-background-color: #dce6f1");
                colorArrayList.add("-fx-background-color: #d8e4bc");
                colorArrayList.add("-fx-background-color: #fabf8f");
                colorArrayList.add("-fx-background-color: #6ba9ff");
                colorArrayList.add("-fx-background-color: #ddd9c4");
                colorArrayList.add("-fx-background-color: #e9c8fd");
                 return colorArrayList;
        }

        public ArrayList<String> colorArrayListEven(){
                ArrayList<String> colorArrayList= new ArrayList<>();
                //setStyle("-fx-background-color: #73affd");
                colorArrayList.add("-fx-background-color: #f4e0d1");
                colorArrayList.add("-fx-background-color: #deb2b1");
                colorArrayList.add("-fx-background-color: #d4dde8");
                colorArrayList.add("-fx-background-color: #d0dcb6");
                colorArrayList.add("-fx-background-color: #f0b98a");
                colorArrayList.add("-fx-background-color: #73affd");
                colorArrayList.add("-fx-background-color: #d4d1bd");
                colorArrayList.add("-fx-background-color: #e6bcff");
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

        private void addTableColors(TableView table, int index) {
                table.setRowFactory(tableView -> new TableRow<Team>() {
                        @Override
                        public void updateItem(Team item, boolean empty) {
                                super.updateItem(item, empty);
                                if (getIndex() % 2 == 0) {
                                       setStyle(colorArrayList().get(index));
                                }
                                else {
                                        setStyle(colorArrayListEven().get(index));
                                }
                        }
                        @Override
                        public void updateSelected(boolean empty) {
                                super.updateSelected(empty);
                                if (isSelected()) {
                                        setStyle("");
                                }
                        }
                });

                // If a row is selected removes it's background so that it highlights properly
                table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                        @Override
                        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                table.refresh();
                        }
                });
        }
}