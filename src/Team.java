import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

/**
 * Team class represents each individual team from a country. The class main responsibility is holding the information
 * about each team and keeping track of the games that each team has played.
 */
// TODO: Add description and comments
// TODO: Add authors! (first and last name add the top of the method)
public class Team implements Comparable<Team> {

    //The teams ranking in the FIFA world ranking
    private Integer ranking;

    //The name of the country
    private String country;

    //The total points in the FIFA world ranking
    private double totalPoints;

    //The FIFA code for the country
    private String countryCode;

    //The FIFA confederation the team belongs to
    private String confederation;

    //The flag of the team's country
    private ImageView flag = new ImageView();

    //Whether or not the the team qualified to the world cup
    private boolean qualified;

    // The order of games matters, first 3 are group stage, followed by 16,quarters,semis,final/3rdPlace.
    private ArrayList<Game> games;

    private Image logo;

    /**
     * Constructs a team object with parameters passed
     * @param ranking the ranking of the team
     * @param country the country of the team
     * @param totalPoints the total amount of points
     * @param countryCode the code for the team
     * @param confederation the confederation it belongs to
     */
    public Team(int ranking, String country,double totalPoints, String countryCode, String confederation) {
        this.ranking = ranking;
        this.country = country;
        this.totalPoints = totalPoints;
        this.countryCode = countryCode;
        this.confederation = confederation;
        games = new ArrayList<>();
        qualified = false;

        //Set up image
        flag.setFitHeight(30);
        flag.setFitWidth(45);
        flag.setImage(new Image("TeamFlags/" +countryCode.toLowerCase()+".png"));
        logo = new Image("TeamLogos/" +countryCode.toLowerCase()+".png");
    }

    /**
     * Default constructor. Constructs a team with initial values for games and qualified.
     */
    public Team() {
        games = new ArrayList<>();
        qualified = false;
    }

    /**
     * Adds a game to the games the team has played
     * @param game the game to add
     */
    public void addGame (Game game) {
        games.add(game);
    }

    /**
     * @return Gets the number of wins in the group stage.
     * @author Saif Masoud
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
      @author Saif Masoud
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
      @author Saif Masoud
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
      @author Saif Masoud
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
      @author Saif Masoud
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
      @author Saif Masoud
     */
    public int groupGoalsDifference() {
        return groupGoalsFor() - groupGoalsAgainst();
    }

    /**
     * @return the points for this team in the group stage
     * @author Saif Masoud
     */
    public int groupPoints () {
        return 3 * getGroupWins() + 1 * getGroupDraws();
    }

    public Image getLogo() {
        return logo;
    }

    public void setLogo(Image logo) {
        this.logo = logo;
    }

    //Getters and setters

    /**
     * Method to get team's ranking
     * @return ranking of the team
     */
    public Integer getRanking() {
        return ranking;
    }

    /**
     * Method to set team's ranking
     * @param ranking the ranking of the team
     */
    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    /**
     * Method to get team's country
     * @return the name of the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Method to set team's country
     * @param country the name of the country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Method to get team's points
     * @return the total points for the team
     */
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

    /**
     * Compares ordering of teams in the group-stage. Breaking ties with
     * Goal-Difference, Goals-For, and finally goals against.
     * @author Saif Masoud
     */
    public static Comparator<Team> TeamGroupComparator
            = (team1, team2) -> {

        // Note: the order of team1,team2 is reversed in all the return statements
        // In order to reverse the sorting (Greatest->Lowest.)
        if (team1.groupPoints() != team2.groupPoints())
            return Integer.compare(team2.groupPoints(), team1.groupPoints());

        if (team1.groupGoalsDifference() != team2.groupGoalsDifference())
            return Integer.compare(team2.groupGoalsDifference(), team1.groupGoalsDifference());

        if (team1.groupGoalsFor() != team2.groupGoalsFor())
            return Integer.compare(team2.groupGoalsFor(), team1.groupGoalsFor());

        if (team1.groupGoalsAgainst() != team2.groupGoalsAgainst())
            return Integer.compare(team2.groupGoalsAgainst(), team1.groupGoalsAgainst());

        return 1;
    };
}

