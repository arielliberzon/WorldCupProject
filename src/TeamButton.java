<<<<<<< Updated upstream
import javafx.scene.control.Button;

public class TeamButton extends Button{
    int xCord;
    int yCord;

    public TeamButton(){
        super();
        xCord = 0;
        yCord = 0;
    }

    public TeamButton(String teamName, int xCord, int yCord){
        super(teamName);
        this.xCord = xCord;
        this.yCord = yCord;
=======
import javafx.scene.Node;
import javafx.scene.control.Button;

public class TeamButton extends Button{
    private Team team;
    private Game game; //the point of this class, to store the information that the button is going to display

    public TeamButton(){
        super();
    }
    public TeamButton(String buttonName){
        super(buttonName);
    }

    public TeamButton(String buttonName, Node node){
        super(buttonName, node);
    }

    public void setTeam(Team team) {
        this.team = team;
    }
    public void setGame(Game game) {
        this.game = game;
    }
    public Team getTeam() {
        return team;
    }
    public Game getGame() {
        return game;
>>>>>>> Stashed changes
    }
}
