import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Stores game data and simulates an individual match.
 */
public class Game {

    private int id;
    private Team t1;
    private Team t2;
    private static AtomicInteger idCounter = new AtomicInteger(); // generates IDs.

    //Stores whether the game can be drawn or not
    private boolean canBeDraw;

    //Stores the score of the game for each part of the game. Multidimensional array.
    private int[][] score;

    //Chance to score per minute (Average goals per minute = 0.03) so 3%
    private final int chancePerMinute = 3;

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

    public Game(int id, Team t1, Team t2, boolean canBeDraw) {
        this.id = id;
        this.t1 = t1;
        this.t2 = t2;
        this.score = new int[5][2];
        this.canBeDraw = canBeDraw;
        //simGame();
    }

    public FinishType getFinishType() {
        return FinishType.NORMAL_TIME;
    }

    enum FinishType {
        EXTRA_TIME, PENALTIES, NORMAL_TIME
    }

    public int getId() {
        return id;
    }

    //Just for testing purposes
    public static Game dummyGame(boolean canBeDraw) {
        Team t1 = new Team(0, "TeamA", 1, 1800);
        Team t2 = new Team(0, "TeamB", 2, 400);
        Game game = new Game(idCounter.getAndIncrement(), t1, t2, canBeDraw);
        return game;
    }

    //Just for testing purposes
    public static void simulateGame(boolean canBeDraw){
        Team t1 = new Team(0, "TeamA", 1, 1800);
        Team t2 = new Team(0, "TeamB", 2, 400);
        Game game = new Game(idCounter.getAndIncrement(), t1, t2, canBeDraw);
        game.simulateGame(t1, t2);
    }

    /**
     * @author Alexander Tang and Samuel Hernandez
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
     * @param teamOne one of the teams
     * @param teamTwo the rival team
     */
    public void simulateGame(Team teamOne, Team teamTwo){
        //Calculating chances a team has of scoring against each other
        double sum = teamOne.getPoints() + teamTwo.getPoints();                 //Total amount of points
        double teamOneChance = teamOne.getPoints() * 100/ sum;                  //Chance of team one of scoring per

        //Simulate first 90 minutes divided in two halves
        simulateSection(teamOne, teamTwo, 45,0, teamOneChance);
        simulateSection(teamOne, teamTwo, 45,1, teamOneChance);

        //If game was not tied
        if(score[1][0] != score[1][1]){
            declareWinnerAndLoser(score[1][0], score[1][1]);        //Declare winner
            return;                                         //No need to go any further
        }

        //Method will reach here only if game was tied
        if(canBeDraw){                                      //If the game can be tied declare a tie
            tiedGame = true;
            return;                                         //No need to go any further
        }

        //If the game cannot be tied go to over time
        if(!canBeDraw){
            //Overtime if the scores are tied after 90 minutes
            if(score[1][0] == score[1][1]) {
                overTimeUsed = true;
                simulateSection(teamOne, teamTwo, 15,2, teamOneChance);
                simulateSection(teamOne, teamTwo, 15,3, teamOneChance);

                if(score[3][0] == score[3][1]){                                 //If still tied go to penalty kicks
                    simPenaltyKicks(teamOne, teamTwo, teamOneChance);
                }

                else{                                                           //Else: not tied anymore
                    declareWinnerAndLoser(score[3][0], score[3][1]);                    //Declare winner and loser
                }
            }
        }
    }

    /**
     * @author Alexander Tang and Samuel Hernandez
     * This is a helper method to simulate the time given by {@link #simulateGame(Team, Team)}
     * This method will first determine randomly, but taking chances into consideration, if a goal will happen
     * for every minute.
     * If so, it will randomly select a number in between 1 and 100 and depending on the scoring
     * chance of each team (given by the ranking difference) it will determine who scores.
     * It will keep track of scores and add them up to previous section score.
     *
     * @param teamOne one of the teams
     * @param teamTwo the rival team
     * @param time the time to simulate
     * @param section the section played (First half = 0, second half = 1, first over time = 2 last over time = 3)
     * @param teamOneChance The chance of team one to win (team two has whatever is remaining)
     */
    private void simulateSection(Team teamOne, Team teamTwo, int time, int section, double teamOneChance) {
        Random randomNum = new Random();

        //Keeping track of scores
        int teamOneScore = 0;
        int teamTwoScore = 0;

        for (int i = 0; i < time; i++) {                                    //Simulate time
            int chanceOfGoal = randomNum.nextInt(99) + 1;          //Chance of being a goal in current minute
            if (chanceOfGoal <= 3) {                                     //If goal will occur

                //Determine whose goal it will be
                int chance = randomNum.nextInt(99) + 1;          //Random from 1 to a 100
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
     * @author Alexander Tang and Samuel Hernandez
     * This method will determine who wins the penalty kicks. It will just give a winner but do not report a
     * penalty score. The chances of winning the penalty kicks are still determined by the ranking difference.
     * @param teamOne the first team
     * @param teamTwo the rival team
     * @param teamOneChance the chance of team one of winning (Team two has the remaining chance out of a 100)
     */
    private void simPenaltyKicks(Team teamOne, Team teamTwo, double teamOneChance){
        penaltyKicksReached = true;
        Random randomNum = new Random();
        int number = randomNum.nextInt(99) + 1;         //Get random number between 1 to a 100
        if(number <= teamOneChance){                           //If team one wins
            winner = teamOne;
            loser = teamTwo;
        }
        else {                                                  //If team two wins
            winner = teamTwo;
            loser = teamOne;
        }
    }

    /**
     * Helper method to determine the winner and loser
     * Sets the winner and loser depending on the scores passed.
     * @param scoreT1 score of team 1
     * @param scoreT2 score of team 2
     * @author Samuel Hernandez
     */
    private void declareWinnerAndLoser(int scoreT1, int scoreT2){
        if(scoreT1 > scoreT2){                           //If team one wins
            winner = t1;
            loser = t2;
        }
        else {                                            //If team two wins
            winner = t2;
            loser = t1;
        }
    }

    /**
     * Method gets the result as a string for the first 45 minutes
     * @return the score at 45 minutes
     * @author Samuel Hernandez
     */
    public String getFirst45ScoreString(){
        return new String(t1.getName()+" "+score[0][0]+ "-" + score[0][1] +" " +t2.getName());
    }

    /**
     * Method gets the result as a string for the second 45 minutes
     * @return the score at 90 minutes
     * @author Samuel Hernandez
     */
    public String getSecond45ScoreString(){
        return new String(t1.getName()+" "+score[1][0]+ "-" + score[1][1] +" " +t2.getName());
    }

    /**
     * Method gets the result as a string for the first over time 15 minutes
     * @return the score at 105 minutes or if not played specify why
     * @author Samuel Hernandez
     */
    public String getFirst15ScoreString(){
        if(!overTimeUsed) {
            return "Overtime was not used ";
        }
        else
            return new String(t1.getName()+" "+score[2][0]+ "-" + score[2][1] +" " +t2.getName());
    }

    /**
     * Method gets the result as a string for the second over time 15 minutes
     * @return the score at 120 minutes or if not played specify why
     * @author Samuel Hernandez
     */
    public String getSecond15ScoreString(){
        if(!overTimeUsed) {
            return "Overtime was not used ";
        }
        else
            return new String(t1.getName()+" "+score[3][0]+ "-" + score[3][1] +" " +t2.getName());
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
     * @author Alexander Tang and Samuel Hernandez
     */
    public Team getLoser(){
        if(loser != null)
            return loser;
        else
            throw new UnsupportedOperationException("Game has no loser. Game was tied");
    }

    /**
     * Returns the team that won
     * @return the team that won, if any.
     * @throws UnsupportedOperationException if loser is null
     * @author Alexander Tang and Samuel Hernandez
     */
    public Team getWinner() {
        if(winner != null)
            return winner;
        else
            throw new UnsupportedOperationException("Game has no winner. Game was tied");
    }

    /**
     * Method gets whether or not over time was used
     * @return true if over time was used, else false
     */
    public boolean wasOverTimeUsed() {
        return overTimeUsed;
    }

    /**
     * Method gets whether or not penalty kicks were reached
     * @return true if reached, else false
     * @author Samuel Hernandez
     */
    public boolean werePenaltyKicksReached() {
        return penaltyKicksReached;
    }
}