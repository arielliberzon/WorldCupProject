import java.util.Objects;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * TeamButton creates a special button that holds the Team object. It holds a game object as well, but it's mostly used for team.
 * Sets the name and flag when setTeam() is called
 * 
 * @author Shane Callahan, Justin Valas, Ariel Liberzon
 */
public class TeamButton extends Button{
    private Team team;
    private Game game; //the point of this class, to store the information that the button is going to display
    private int gameOrder; //If it's 1, it's a game in the eight, 2 if quaterly, 3 if semi, or third place, 4 if winner or third place winner
    private ImageView flag;

    /**
     * @author Shane Callahan
     * the default constructor for the Button, and then sets the default click state.
     */
    public TeamButton(){
        super();
        this.setOnMouseClicked(displayTheGames);
    }

    /**
     * @author Shane Callahan
     * The constructor that Button has; also applying the default click state
     * @param buttonName a string that will be the name the button displays
     */
    public TeamButton(String buttonName){
        super(buttonName);
        this.setOnMouseClicked(displayTheGames);
    }

    /**
     * @author Shane Callahan
     * The constructor that Button has; also applying the default click state
     * @param buttonName a string that will be the name the button displays
     * @param node 
     */
    public TeamButton(String buttonName, Node node){
        super(buttonName, node);
        this.setOnMouseClicked(displayTheGames);
    }

    /**
     * @author Ariel Liberzon and Shane Callahan
     * The method to create the tooltip / information for the button
     * @param button The button this tooltip will be assigned too, because it sets information in the tooltip
     * @return Returns a custom tooltip to be  used in setTooltip()
     */
    private Tooltip createToolTip(TeamButton button) {
        Tooltip tooltip = new Tooltip();
        if(button.getTeam() != null){
            tooltip.setText(button.getTeam().toString());
            tooltip.setGraphic(button.getTeam().getLogo());           //THESE ARE REDUNDENT IF WE SHOW IT IN BUTTON NAME
        }
        return tooltip;
    }

    /**
     * @author Shane Callahan
     * This assigns the button to a team; and then also does the tooltip, text, flag
     * @param team the team that the button will hold. Drives a *lot* of the functionality of the TeamButton
     */
    public void setTeam(Team team) {
        this.team = team;
        this.setFlag(team.getFlag().getImage());
        this.setGraphic(flag);
        this.setTooltip(createToolTip(this));
        this.setText(team.getCountry());
        this.setTextAlignment(TextAlignment.CENTER);
    }

    /**
     * @author Shane Callahan
     * Since the game has the team, we can set the game and the wining team from there.  
     * @param game Game to get team to set it to the button
     */
    public void setGame(Game game) {
        this.game = game;
        this.team = game.getWinner();
        this.setFlag(game.getWinner().getFlag().getImage());
        this.setGraphic(flag);
        this.setTooltip(createToolTip(this));
        this.setText(game.getWinner().getCountry());
    }
    /**
     * @author Shane Callahan
     * Since the third place, one of them is a *loser* of a game, it's a special case, but functions the same as setGame(game) just if the third place is false, it will call the *loser* of the game
     * @param game Game to get the teams from
     * @param thirdPlaceWinner A boolean to check if the team is a winner, or loser; true = winner, false = loser
     */
    public void setGame(Game game, Boolean thirdPlaceWinner){//Special case for third place
        if(thirdPlaceWinner){
        this.game = game;
        this.team = game.getWinner();
        this.setFlag(game.getWinner().getFlag().getImage());
        this.setGraphic(flag);
        this.setTooltip(createToolTip(this));
        this.setText(game.getWinner().getCountry());
        }
        else if(!thirdPlaceWinner){
            this.game = game;
            this.team = game.getLoser();
            this.setFlag(game.getLoser().getFlag().getImage());
            this.setGraphic(flag);
            this.setTooltip(createToolTip(this));
            this.setText(game.getLoser().getCountry()); 
        }
    }
    /**
     * @author Shane Callahan
     * This sets the number of games the team has done, in order to be used in the displayTheGames event
     * @param gameOrder 
     */
    public void setGameOrder(int gameOrder) {
        this.gameOrder = gameOrder;
    }
    /**
     * @author Shane Callahan
     * A method to set the imageView flag takes in an image
     * @param flag sets the flag to be shown in the button; with a scale
     */
    public void setFlag(Image flag) {
       this.flag = new ImageView(flag);
       this.flag.setFitHeight(20);  //change these values to change the scaling
       this.flag.setFitWidth(30);
    }
    public Team getTeam() {
        return team;
    }
    public Game getGame() {
        return game;
    }
    public int getGameOrder() {
        return gameOrder;
    }

    public ImageView getFlag() {
        return flag;
    }

    public void removeName(){
        this.setText("");
    }
    

    /**
     * @author Shane Callahan
     * Displays the teams that the team has done, also uses getGameOrder, ticking down to display the teams in the proper order, top being "most recent" game
     * Displays it using the method showMessageDialog, which opens up another stage, displaying the beatiful Pane that Ariel made
     */
    private EventHandler<MouseEvent> displayTheGames = mouseEvent -> {
        TeamButton button = (TeamButton) mouseEvent.getSource();
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {//LEFT CLICK
            if(button.getGame() != null){
                VBox gameBox = new VBox();
                Label knockoutLabel = new Label("Knockout Games: ");
                knockoutLabel.setFont(new Font(knockoutLabel.getFont().toString(),16));
                gameBox.getChildren().addAll(knockoutLabel);
                gameBox.setAlignment(Pos.CENTER);//makes everything inside the VBox centered
                for (int i = button.getGameOrder(); i > 0; i--) {
                   //Gives an arrayList, call the array List from +3, 
                    gameBox.getChildren().addAll(button.getTeam().getGames().get(i+2).getScoreDisplay(button.getTeam()));
                }
               
                Label groupLabel = new Label("\n Group Stage Games: \n");
                groupLabel.setFont(new Font(groupLabel.getFont().toString(),16));
                gameBox.getChildren().addAll(groupLabel);
                for (int i = 2; i >= 0; i--) {
                    //Gives an arrayList, call the array List from 0 - 3,  
                    gameBox.getChildren().addAll(button.getTeam().getGames().get(i).getScoreDisplay(button.getTeam()));
                 }
                 this.showMessageDialogue(gameBox);
            }
            else if(button.getTeam() != null){
                VBox gameBox = new VBox();
                gameBox.setAlignment(Pos.CENTER);
                Label label = new Label("Group Stage Games: \n");
                label.setAlignment(Pos.CENTER);
                gameBox.getChildren().addAll(label);
                for (int i = 2; i >= 0; i--) {
                    //Gives an arrayList, call the array List from 0 - 3, 
                    gameBox.getChildren().addAll(button.getTeam().getGames().get(i).getScoreDisplay(button.getTeam()));
                 }
                 this.showMessageDialogue(gameBox);
            }
                
            }
        else if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {//RIGHT CLICK
            //useless but kept here incase we need it
            }
        };

        /**
         * @author Justin Valas and Shane Callahan
         * Takes a pane, and displays it in a stage as a pop up window
         * @param paneToDisplay The pane that the pop up window will come to
         */
    private void showMessageDialogue(Pane paneToDisplay) {

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle("Game History"); // come back to

        Pane pane = new Pane();


        pane.getChildren().add(paneToDisplay);
        stage.getIcons().add(new Image("Images/logo2.png"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Compares two TeamButtons to see if they are equal, I copied Samuels equals
     * @param o object to test for equality
     * @return true if equal, else false.
     * @author Samuel Hernandez, Shane Callahan
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TeamButton)) return false;
        TeamButton button = (TeamButton) o;
        return  Objects.equals(team, button.getTeam()) &&
                Objects.equals(game, button.getGame()) &&
                Objects.equals(gameOrder, button.getGameOrder()) &&
                Objects.equals(flag, button.getFlag());
    }

    /**
     * Typical to string, get's the information that the buttons will most likely have; a team and a team order
     * @author Shane Callahan
     */
    @Override
    public String toString() {
        String s;
        s = "This button's team is: " + this.getTeam() + " and it's Game Order is: " + this.getGameOrder();
        return s;
    }
}
