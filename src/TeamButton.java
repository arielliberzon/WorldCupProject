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
    }
}
