import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;

public class TeamButton extends Button{
    private Team team;
    private Game game; //the point of this class, to store the information that the button is going to display
    private int gameOrder; //If it's 1, it's a game in the eight, 2 if quaterly, 3 if semi, or third place, 4 if winner or third place winner

    public TeamButton(){
        super();
    }
    public TeamButton(String buttonName){
        super(buttonName);
    }

    public TeamButton(String buttonName, Node node){
        super(buttonName, node);
    }

    private Tooltip createToolTip(TeamButton button) {
        Tooltip tooltip = new Tooltip();
        if(button.getTeam() != null){
            tooltip.setText(button.getTeam().toString());
        }
        else if(button.getGame() != null){
            tooltip.setText(button.getGame().getWinner().toString());
        }
        return tooltip;
    }

    private Tooltip createToolTip(TeamButton button, Boolean thirdPlaceWinner) {
        Tooltip tooltip = new Tooltip();
        if(button.getTeam() != null){
            tooltip.setText(button.getTeam().toString());
        }
        else if(button.getGame() != null){
            if(thirdPlaceWinner){
            tooltip.setText(button.getGame().getWinner().toString());
        }
            else{
                tooltip.setText(button.getGame().getLoser().toString()); 
            }
        }
        return tooltip;
    }

    public void setTeam(Team team) {
        this.team = team;
        //buttonList.get(counter).setText(sixteenTeams.get(i).getCountry());
        //buttonList.get(counter).setTooltip(createToolTip(buttonList.get(counter)));
        this.setTooltip(createToolTip(this));
        this.setText(team.getCountry());
    }
    public void setGame(Game game) {
        this.game = game;
        //buttonList.get(i).setText(eightGames.get(counter).getWinner().getCountry())
        this.setTooltip(createToolTip(this));
        this.setText(game.getWinner().getCountry());
    }
    public void setGame(Game game, Boolean thirdPlaceWinner){//Special case for third place
        if(thirdPlaceWinner){
        this.game = game;
        //buttonList.get(i).setText(eightGames.get(counter).getWinner().getCountry())
        this.setTooltip(createToolTip(this,true));
        this.setText(game.getWinner().getCountry());
        }
        else{
            this.game = game;
            //buttonList.get(i).setText(eightGames.get(counter).getWinner().getCountry())
            this.setTooltip(createToolTip(this,false));
            this.setText(game.getLoser().getCountry()); 
        }
    }
    public void setGameOrder(int gameOrder) {
        this.gameOrder = gameOrder;
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
    
}
