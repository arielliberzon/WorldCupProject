import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

/**
 * Game class is the class in charge of simulating each individual game in between two teams.
 * A game is simulated by calculating the chance of scoring per minute. If a goal is to
 * happen in that given minute (using random) the game then decides who scores that goal depending
 * on a random number (but dividing the chance proportionally depending on FIFA rankings).
 * This calculation repeats for the first 90 minutes and if can not be drawn then in extra time,
 * and if needed it is guaranteed to finish in penalties.
 * @author Alexander Tang, Ariel Liberzon, Zachary Lavoie, Saif Masoud and Samuel Hernandez
 */
public class Game {

    //TODO: Add comment to Ariel's method, and delete this line.

    //Holds team one
    private Team teamOne;

    //Hold team two
    private Team teamTwo;

    //Stores whether the game can be drawn or not
    private boolean canBeDraw;

    //Stores the score of the game for each part of the game. Multidimensional array.
    private int[][] score = new int[5][2];

    //Chance to score per minute (Average goals per minute = 0.03) so 3%
    private int chancePerMinute = 3;

    //Holds the winner of the game
    private Team winner;

    //Holds the winner of the game
    private Team loser;

    //Holds if the game was tied or not
    private boolean tiedGame = false;

    //Has whether or not over time was used
    private boolean overTimeUsed = false;

    //Has whether or penalty kicks were reached
    private boolean penaltyKicksReached = false;

    //Chance of team one (used to calculate chance of scoring)
    private double teamOneChance;

    /**
     * Constructs  a game with given parameters
     * @param t1 team one to play
     * @param t2 rival team
     * @param canBeDraw whether or not a game can be drawn
     */
    public Game(Team t1, Team t2, boolean canBeDraw) {
        this.teamOne = t1;
        this.teamTwo = t2;
        this.score = new int[5][2];
        this.canBeDraw = canBeDraw;
        simulateGame();
        teamOne.addGame(this);
        teamTwo.addGame(this);
    }

    /**
     * Default constructor: Constructs a default game object.
     */
    public Game() {
        canBeDraw = true;
    }

    /**
     * Gets the way the game finished in a string
     * @return Regular Time  if game finished at 90minutes, Extra Time if 120, or Penalties if game
     * finished on penalties.
     * @author Saif Masoud
     */
    public String getFinishType() {
        if (!overTimeUsed)
            return "Regular Time";
        else if (overTimeUsed && !penaltyKicksReached)
            return "Extra Time";
        else
            return "Penalties";
    }

    /**
     * This method simulates the game in between two teams. If the game can be drawn the game will always
     * end after 90minutes. If the game can not be drawn the game will go to over time if it is drawn at the
     * end of the regular 90 minutes. If after the over time it is still tied it will go to penalties and
     * declare a winner.
     *
     * To make it as realistic as possible the chance to score refers to the average goals scored per minute in a
     * world cup. (.03). Then, the chance per minute is divided by the difference in scores given by the fifa.
     * This way it is random but rankings are considered.
     *
     * Method sets various fields so that methods can access information about the result of the game.
     * Sets up the winner, the loser, or if the game was tied set the game as tied. Also where the game ended.
     * @author Alexander Tang and Samuel Hernandez
     */
    public void simulateGame(){
        //Calculating chances a team has of scoring against each other
        double sum = teamOne.getTotalPoints() + teamTwo.getTotalPoints();       //Total amount of points
        teamOneChance = teamOne.getTotalPoints() * 100/ sum;                    //Chance of team one of scoring per

        //Update chance to show more realistic scores
        teamOneChance += teamOne.getTotalPoints() > teamTwo.getTotalPoints()? 25 : -25;

        //Simulate first 90 minutes divided in two halves
        simulateSection(45,0);
        simulateSection(45,1);

        //If game was not tied
        if(score[1][0] != score[1][1]){
            declareWinnerNLoser(score[1][0], score[1][1]);        //Declare winner
            return;                                                 //No need to go any further
        }

        //Method will reach here only if game was tied
        if(canBeDraw){                                          //If the game can be tied declare a tie
            tiedGame = true;
            return;                                             //No need to go any further
        }

        //If the game cannot be tied go to over time
        if(!canBeDraw){
            if(score[1][0] == score[1][1]) {                    //Overtime if the scores are tied after 90 minutes
                overTimeUsed = true;
                simulateSection(15,2);
                simulateSection(15,3);
                if(score[3][0] == score[3][1])                                 //If still tied go to penalty kicks
                    simulatePenaltyKicks();
                else                                                                   //Else: not tied anymore
                    declareWinnerNLoser(score[3][0], score[3][1]);                    //Declare winner and loser
            }
        }
    }

    /**
     * This is a helper method to simulate the time given by {@link #simulateGame()}
     * This method will first determine randomly, but taking chances into consideration, if a goal will happen
     * for every minute.
     * If so, it will randomly select a number in between 1 and 100 and depending on the scoring
     * chance of each team (given by the ranking difference) it will determine who scores.
     * It will keep track of scores and add them up to previous section score.
     *
     * @param time the time to simulate
     * @param section the section played (First half = 0, second half = 1, first over time = 2 last over time = 3)
     * @author Alexander Tang, Samuel Hernandez
     */
    private void simulateSection(int time, int section) {
        Random randomNum = new Random();
        //Keeping track of scores
        int teamOneScore = 0;
        int teamTwoScore = 0;

        for (int i = 0; i < time; i++) {                                    //Simulate time
            int chanceOfGoal = randomNum.nextInt(100) + 1;          //Chance of being a goal in current minute
            if (chanceOfGoal <= 3) {                                     //If goal will occur

                //Determine whose goal it will be
                int chance = randomNum.nextInt(100) + 1;          //Random from 1 to a 100
                if (chance <= teamOneChance)                           //If team one scores
                    teamOneScore++;
                else                                                  //Else team two scores
                    teamTwoScore++;
            }
        }

        //Storing the scores for the section
        score[section][0] = teamOneScore;
        score[section][1] = teamTwoScore;

        //Adding up the scores to the previous sections score (Except for first half)
        if (section > 0) {
            score[section][0] = score[section - 1][0] + score[section][0];
            score[section][1] = score[section - 1][1] + score[section][1];
        }
    }

    /**
     * This method determines who wins in the penalty kicks. The chance of one team winning is random but still
     * takes into consideration the team difference as follows:
     * The average success of a penalty kick is 70%
     * The difference is divided by a 100 and then added to the team with the highest raking and subtracted
     * from the team with the lowest ranking.
     * Example: T1 score = 1800. T2 score = 1600. Difference/100 = 2 -> So T1 chance = 72%. T2 chance = 68%
     * @author Ariel Liberzon, Samuel Hernandez
     */
    private void simulatePenaltyKicks() {
        penaltyKicksReached = true;                                             //Mark game reached penalty kicks
        int teamOneScoreChance = 70;                                            //Average real chance of scoring
        int teamTwoScoreChance = teamOneScoreChance;
        score[4][0] = 0;                                                        //Beginning scores
        score[4][1] = 0;

        //Calculate modifier to alter chances depending on FIFA scores
        double penaltyModifier = Math.abs(teamOne.getTotalPoints() - teamTwo.getTotalPoints()) / 100;

        //Determine chances for both teams depending on the score difference
        if (teamOne.getTotalPoints() >= teamTwo.getTotalPoints()) {
            teamOneScoreChance += penaltyModifier;
            teamTwoScoreChance -= penaltyModifier;
        } else {
            teamOneScoreChance -= penaltyModifier;
            teamTwoScoreChance += penaltyModifier;
        }

        //Shoot first 5 penalty kicks
        boolean resolved = shoot5PenaltyKicks(teamOneScoreChance, teamTwoScoreChance);

        if (!resolved)                          //If first 5 kicks were not enough go to sudden death to declare winner
            suddenDeathKicks(teamOneScoreChance, teamTwoScoreChance);

        //Record penalty kick score and declare a winner
        declareWinnerNLoser(score[4][0], score[4][1]);
    }


    /**
     * Method shoots the first 5 penalty kicks unless there is an unrecoverable difference in which case it
     * just stops at that point.
     * @return true if first 5 kicks solved tie (there is a winner), else false
     * @param teamOneScoreChance the chance of scoring for team one
     * @param teamTwoScoreChance the chance of scoring for team two
     * @author Ariel Liberzon, Samuel Hernandez
     */
    private boolean shoot5PenaltyKicks(int teamOneScoreChance, int teamTwoScoreChance) {
        for(int i = 0; i < 5; i++){
            boolean t1Scored = shootPenalty(teamOneScoreChance);       //Team one shoots
            boolean t2Scored = shootPenalty(teamTwoScoreChance);       //Team two shoots
            if(t1Scored)                                                        //Add to score
                score[4][0]++;
            if(unrecoverableDifference(i+1, i))                                 //Check if difference is unrecoverable
                return true;                                                    //If so, there is a winner
            if(t2Scored)                                                        //Add to score
                score[4][1]++;
            if(unrecoverableDifference(i+1, i+1))                               //Check if difference is unrecoverable
                return true;                                                    //If so, there is a winner
        }

        //After the first 5 shots.
        if(score[4][0] != score[4][1])                                          //If there is a winner
            return true;
        else                                                                    //There is no winner yet.
            return false;
    }

    /**
     * Method will keep shooting kicks until one team scores and the other team misses
     * @param teamOneScoreChance the chance of scoring for team one
     * @param teamTwoScoreChance the chance of scoring for team two
     * @author Ariel Liberzon, Samuel Hernandez
     */
    private void suddenDeathKicks(int teamOneScoreChance, int teamTwoScoreChance) {
        boolean notDone = true;
        while (notDone) {
            boolean t1Scored = shootPenalty(teamOneScoreChance);       //Team one shoots
            boolean t2Scored = shootPenalty(teamTwoScoreChance);       //Team two shoots
            if(t1Scored)                                                        //Add to score
                score[4][0]++;
            if(t2Scored)                                                        //Add to score
                score[4][1]++;
            if(t1Scored != t2Scored)                                            //If different result
                return;
        }
    }

    /**
     * This method checks if a team can still recover from the scoring difference
     * Example: Team 1 scored first 3 shots. Team two misses first 3 shots.
     * Even if team two scores the remaining 2 shots the difference is unrecoverable.
     * @param roundT1 rounds already shot by Team One
     * @param roundT2 rounds already shot by Team Two
     * @return true if difference is unrecoverable. Else false
     * @author Ariel Liberzon, Samuel Hernandez
     */
    private boolean unrecoverableDifference(int roundT1, int roundT2) {
        //Check if it is possible for a team to recover
        int roundsLeftT1 = 5 - roundT1;
        int roundsLeftT2 = 5 - roundT2;
        int maxScoreT1 = score[4][0] + roundsLeftT1;          //Current score team1 + rounds left
        int maxScoreT2 = score[4][1] + roundsLeftT2;          //Current score team2 + rounds left

        //If impossible to recover
        if(maxScoreT1 < score[4][1])                        //Maximum score < rival current score
            return true;
        else if(maxScoreT2 < score[4][0])                   //Maximum score < rival current score
            return true;

        //If possible to recover
        return false;
    }

    /**
     * Method simulates penalty kick and returns whether the penalty was scored or not.
     * Method takes into consideration the chance but is also random (if random <= team chance)
     * @param teamChance the chance of scoring (calculated by Fifa score differences)
     * @return true if goal scored else false
     * @author Samuel Hernandez
     */
    private boolean shootPenalty(int teamChance) {
        Random randomNum = new Random();
        int randomChance = randomNum.nextInt(100) + 1;        //Score chance will be number from 1 to a 100
        if (randomChance <= teamChance) {
            return true;
        }
        return false;
    }

    /**
     * Helper method to determine the winner and loser
     * Sets the winner and loser depending on the scores passed.
     * @param teamOneScore score of team 1
     * @param teamTwoScore score of team 2
     * @author Samuel Hernandez
     */
    private void declareWinnerNLoser(int teamOneScore, int teamTwoScore){
        if(teamOneScore > teamTwoScore){                           //If team one wins
            winner = teamOne;
            loser = teamTwo;
        }
        else {                                            //If team two wins
            winner = teamTwo;
            loser = teamOne;
        }
    }

    /**
     * Method gets the result as a string for the first 45 minutes
     * @return the score at 45 minutes
     * @author Samuel Hernandez
     */
    public String get45minScore(){
        return (teamOne.getCountry()+" "+score[0][0]+ "-" + score[0][1] +" " + teamTwo.getCountry());
    }

    /**
     * Method gets the result as a string for the second 45 minutes
     * @return the score at 90 minutes
     * @author Samuel Hernandez
     */
    public String get90minScore(){
        return (teamOne.getCountry()+" "+score[1][0]+ "-" + score[1][1] +" " + teamTwo.getCountry());
    }

    /**
     * Method gets the result as a string for the first over time 15 minutes
     * @return the score at 105 minutes or if not played specify why
     * @author Samuel Hernandez
     */
    public String get105minScore(){
        if(!overTimeUsed) {
            return "Overtime was not used ";
        }
        else
            return (teamOne.getCountry()+" "+score[2][0]+ "-" + score[2][1] +" " + teamTwo.getCountry());
    }

    /**
     * Method gets the result as a string for the second over time 15 minutes
     * @return the score at 120 minutes or if not played specify why
     * @author Samuel Hernandez
     */
    public String get120minScore(){
        if(!overTimeUsed) {
            return "Overtime was not used ";
        }
        else
            return (teamOne.getCountry()+" "+score[3][0]+ "-" + score[3][1] +" " + teamTwo.getCountry());
    }

    /**
     * Method gets the result of the penalty kicks
     * @return the scores of the penalty kicks
     * @author Samuel Hernandez
     */
    public String getPenaltiesScore(){
        if(!penaltyKicksReached) {
            return "Penalty kicks were not reached";
        }
        else
            return (teamOne.getCountry()+" "+score[4][0]+ "-" + score[4][1] +" " + teamTwo.getCountry());
    }

    /**
     * Returns whether or not the game was tied
     * @return true if game ended up in a tie, else false
     * @author Alexander Tang and Samuel Hernandez
     */
    public boolean wasTiedGame() {
        return tiedGame;
    }

    /**
     * Returns the team that lost
     * @return the team that lost, if any.
     * @throws UnsupportedOperationException if loser is null
     * @author Alexander Tang, Samuel Hernandez
     */
    public Team getLoser(){
        if(loser != null)
            return loser;
        else
            throw new UnsupportedOperationException("Game has no loser. Game was tied");
    }

    /**
     * Returns the team that won
     * @return the team that won, if any, otherwise null.
     * @author Alexander Tang, Samuel Hernandez
     */
    public Team getWinner() {
        return winner;
    }

    /**
     * Method returns the final score of the game regardless of the way it happened
     * @return the final score represented in a string
     * @author Samuel Hernandez
     */
    public String getFinalScoreString(){
        if(overTimeUsed){
            if(penaltyKicksReached) {
                return (teamOne.getCountry() + " " + score[3][0] + "(" +
                        score[4][0]+ ") - " +score[3][1] + "("+score[4][1]+") " + teamTwo.getCountry());
            }
            else
                return get120minScore();
        }
        else
            return get90minScore();
    }

    /**
     * Method gets whether or not over time was used
     * @return true if over time was used, else false
     * @author Samuel Hernandez
     */
    public boolean wasOverTimeUsed() {
        return overTimeUsed;
    }

    /**
     * Method gets whether or not penalty kicks were reached
     * @return true if reached, else false
     * @author Samuel Hernandez
     */
    public boolean penaltyKicksReached() {
        return penaltyKicksReached;
    }

    /**
     * Method retrieves the 2D array containing the scores at all points during the game
     * @return the array of scores
     */
    public int[][] getScore() {
        return score;
    }

    /**
     * TODO: Add comments
     * @return
     */
    public Pane getFinalScore() {
        BorderPane borderPane = new BorderPane();

        Label scoreLabel = new Label(toString());
        scoreLabel.setPadding(new Insets(0,10,0,10));

        Label finishTypeLabel = new Label(getFinishType());
        finishTypeLabel.setFont(new Font(finishTypeLabel.getFont().toString(), 8));

        ImageView teamOneFlag = new ImageView(teamOne.getFlag().getImage());
        teamOneFlag.setFitHeight(20);
        teamOneFlag.setFitWidth(30);

        ImageView teamTwoFlag = new ImageView(teamTwo.getFlag().getImage());
        teamTwoFlag.setFitHeight(20);
        teamTwoFlag.setFitWidth(30);

        borderPane.setLeft(teamOneFlag);
        borderPane.setRight(teamTwoFlag);
        borderPane.setCenter(scoreLabel);
        borderPane.setBottom(finishTypeLabel);
        borderPane.setPadding(new Insets(10, 10, 10, 10));

        BorderPane.setAlignment(borderPane.getBottom(), Pos.CENTER);

        return borderPane;
    }

    /**
     * Method gets team one
     * @return team number one
     */
    public Team getTeamOne() {
        return teamOne;
    }

    /**
     * Method gets team two
     * @return team number two
     */
    public Team getTeamTwo() {
        return teamTwo;
    }

    /**
     * Gets whether or not a game can be drawn
     * @return true if can be draw, else false
     */
    public boolean canBeDraw() {
        return canBeDraw;
    }
    /**
     * Sets whether or not a game can be drawn
     * @param canBeDraw true if can be draw, else false
     */
    public void setCanBeDraw(boolean canBeDraw) {
        this.canBeDraw = canBeDraw;
    }

    /**
     * Gets the chance of scoring per minute
     * @return the number representing the percentage of chance to score
     */
    public int getChancePerMinute() {
        return chancePerMinute;
    }

    /**
     * Sets the chance of scoring per minute
     * @param chancePerMinute the number representing the percentage of chance to score
     */
    public void setChancePerMinute(int chancePerMinute) {
        this.chancePerMinute = chancePerMinute;
    }

    /**
     * Sets team one
     * @param teamOne the team to set as team one
     */
    public void setTeamOne(Team teamOne) {
        this.teamOne = teamOne;
    }

    /**
     * Sets team two
     * @param teamTwo the team to set as team two
     */
    public void setTeamTwo(Team teamTwo) {
        this.teamTwo = teamTwo;
    }

    /**
     * Gets the game information as a string
     * @return string representation of the game
     * @author Samuel Hernandez
     */
    @Override
    public String toString() {
        return getFinalScoreString();
    }

    /**
     * Compares two games to see if they are equal
     * @param o object to test for equality
     * @return true if equal, else false.
     * @author Samuel Hernandez
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game)) return false;
        Game game = (Game) o;
        return  Objects.equals(teamOne, game.teamOne) &&
                Objects.equals(teamTwo, game.teamTwo) &&
                Arrays.equals(score, game.score);
    }

    //Rest of setters and getters do not make sense, so are not included
}
