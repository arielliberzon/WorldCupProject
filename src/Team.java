import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Objects;

// TODO: Add description and comments
// TODO: Add authors! (first and last name add the top of the method)
public class Team implements Comparable<Team> {

    private Integer ranking;
    private String country;
    private double totalPoints;
    private String countryCode;
    private String confederation;
    private String countryFlagID;
    private ImageView flag = new ImageView();
    private boolean qualified;

    // The order of games matters, first 3 are group stage, followed by 16,quarters,semis,final/3rdPlace.
    private ArrayList<Game> games;

    public Team(int ranking, String country,double totalPoints, String countryCode,
                String confederation) {
        this.ranking = ranking;
        this.country = country;
        this.totalPoints = totalPoints;
        this.countryCode = countryCode;
        this.confederation = confederation;
        flag.setFitHeight(30);
        flag.setFitWidth(45);
        flag.setImage(new Image("FlagIcons/"+countryCode.toLowerCase()+".png"));
        games = new ArrayList<>();
        qualified = false;
    }

    /**
     * Adds a game to the history of games played in the order it would be wanted to be displayed:
     * This team being listed first than the rival
     * @param game the game to add to history
     */
    public void addGame (Game game) {
        Team t2 = game.getTeamTwo();
        int[][] score = game.getScore();
        score = swapScores(score);

        //Swap it only if in "incorrect order"
        games.add(t2.equals(this) ?
                new Game(t2, game.getTeamOne(), score, game.wasOverTimeUsed(), game.penaltyKicksReached()) : game);
    }

    /**
     * Swaps the scores of team one to team two to create new game object with ordered information.
     * @param score the score of the game
     * @return the swapped score array
     */
    private int[][] swapScores(int[][] score) {
        int[][] swapped = new int[5][2];
        for(int i = 0; i < score.length; i++){
            swapped[i][0] = score[i][1];
            swapped[i][1] = score[i][0];
        }
        return swapped;
    }

    /**
     * @return Gets the number of wins in the group stage.
     */
    public int getGroupWins () {
        int wins = 0;
        for (int i = 0; i < 3; i++) {
            Game curGroupGame = games.get(i);
            if (curGroupGame.getWinner() == this) wins += 1;
        }
        return wins;
    }
    /**
     * @return Gets the number of wins in the group stage.
     */
    public int getGroupDraws () {
        int draws = 0;
        for (int i = 0; i < 3; i++) {
            Game curGroupGame = games.get(i);
            if (curGroupGame.getWinner() == null) draws += 1;
        }
        return draws;
    }
    /**
     * @return Gets the number of wins in the group stage.
     */
    public int getGroupLosses () {
        int losses = 0;
        for (int i = 0; i < 3; i++) {
            Game curGroupGame = games.get(i);
            Team winner = curGroupGame.getWinner();
            if (winner != this && winner != null) losses += 1;
        }
        return losses;
    }

    /**
     * @return goals scored during group-stage.
     */
    public int groupGoalsFor() {
        int goalsFor = 0;
        // First 3 are the group-stage games.
        for (Game game : games.subList(0, 3))
        {
            int teamIndex = 0;
            if (this != game.getTeamOne()) teamIndex=1;
            int curGameGoals = game.getScore()[1][teamIndex];
            goalsFor += curGameGoals;
        }
        return goalsFor;
    }
    /**
     * @return goals scored against this team during group-stage.
     */
    public int groupGoalsAgainst() {
        int goalsFor = 0;
        // First 3 are the group-stage games.
        for (Game game : games.subList(0, 3))
        {
            int teamIndex = 1;
            if (this != game.getTeamOne()) teamIndex=0;
            int curGameGoals = game.getScore()[1][teamIndex];
            goalsFor += curGameGoals;
        }
        return goalsFor;
    }

    /**
     * @return difference between goals-scored and goals-against in groups.
     */
    public int groupGoalsDifference() {
        return groupGoalsFor() - groupGoalsAgainst();
    }

    public int groupPoints () {
        return 3 * getGroupWins() + 1 * getGroupDraws();
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(double totalPoints) {
        this.totalPoints = totalPoints;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getConfederation() {
        return confederation;
    }

    public void setConfederation(String confederation) {
        this.confederation = confederation;
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }

    public String getCountryFlagID () {
            return countryFlagID;
        }

    public void setCountryFlagID (String countryFlagID){
            this.countryFlagID = countryFlagID;
        }

    public ImageView getFlag() {
        return flag;
    }

    public void setFlag(ImageView flag) {
        this.flag = flag;
    }

    public boolean isQualified() {
        return qualified;
    }

    public void setQualified(boolean qualified) {
        this.qualified = qualified;
    }

    /**
     * @author Alexander and Michael
     * @param o The team to compare to
     * @return return 1, -1, or zero according to ranking
     */
    @Override
    public int compareTo(Team o) {
        //return (int) (o.getTotalPoints() - this.getTotalPoints());
        return (this.getRanking().compareTo(o.getRanking()));
    }

    @Override
    public String toString () {
        return "Country: " + country + "\nRNK: " + ranking + "\nTotal Points: "
                + totalPoints + "\nConfederation " + confederation + "\nCountry Code: "
                + countryCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(country, team.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country);
    }
}

