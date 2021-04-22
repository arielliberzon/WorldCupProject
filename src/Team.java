import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Team {

    private String name;
    private int fifaRank;

    private int ranking;
    private String country;
    private double totalPoints;
    private String countryCode;
    private String confederation;
    private String countryFlagID;
    // The order of games matters, first 3 are group stage, followed by 16,quarters,semis,final/3rdPlace.
    private ArrayList<Game> games;

    public Team(int ranking, String country,double totalPoints, String countryCode, String confederation,
                String countryFlagID){
            this.ranking = ranking;
            this.country = country;
            this.totalPoints = totalPoints;
            this.countryCode = countryCode;
            this.confederation = confederation;
            this.countryFlagID = countryFlagID;
            games = new ArrayList<>();
        }

        public int getFifaRank () {
            return fifaRank;
        }

        public void addGame (Game game){
            games.add(game);
        }

        /**
         * @return Gets the number of wins in the group stage.
         */
        public int groupWins () {
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
        public int groupDraws () {
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
        public int groupLosses () {
            int losses = 0;
            for (int i = 0; i < 3; i++) {
                Game curGroupGame = games.get(i);
                Team winner = curGroupGame.getWinner();
                if (winner != this && winner != null) losses += 1;
            }
            return losses;
        }
        public int groupPoints () {
            return 3 * groupWins() + 1 * groupDraws();
        }

        public String getName () {
            return name;
        }

        public int getRanking () {
            return ranking;
        }

        public void setRanking ( int ranking){
            this.ranking = ranking;
        }

        public String getCountry () {
            return country;
        }

        public void setCountry (String country){
            this.country = country;
        }

        public double getTotalPoints () {
            return totalPoints;
        }

        public void setTotalPoints ( double totalPoints){
            this.totalPoints = totalPoints;
        }

        public String getCountryCode () {
            return countryCode;
        }

        public void setCountryCode (String countryCode){
            this.countryCode = countryCode;
        }

        public String getConfederation () {
            return confederation;
        }

        public void setConfederation (String confederation){
            this.confederation = confederation;
        }

        public String getCountryFlagID () {
            return countryFlagID;
        }

        public void setCountryFlagID (String countryFlagID){
            this.countryFlagID = countryFlagID;
        }

        @Override
        public String toString () {
            return "Country: " + country + "\nRNK: " + ranking + "\nTotal Points: "
                    + totalPoints + "\nConfederation " + confederation + "\nCountry Code: "
                    + countryCode;
        }
}

