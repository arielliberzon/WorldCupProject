public class Team {
    private int id;
    private String name;
    private int fifaRank;

    private int ranking;
    private String country;
    private double totalPoints;
    private String countryCode;
    private String confederation;
    private String countryFlagID;


    public Team(int id, String name, int fifaRank) {
        this.id = id;
        this.name = name;
        this.fifaRank = fifaRank;
    }

    public Team(int ranking, String country, double totalPoints, String countryCode, String confederation,
    String countryFlagID) {
        this.ranking = ranking;
        this.country = country;
        this.totalPoints = totalPoints;
        this.countryCode = countryCode;
        this.confederation = confederation;
        this.countryFlagID = countryFlagID;
    }

    public int getRanking() {
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

    public String getCountryFlagID() {
        return countryFlagID;
    }

    public void setCountryFlagID(String countryFlagID) {
        this.countryFlagID = countryFlagID;
    }

    @Override
    public String toString() {
        return "Country: " + country + "\nRNK: " + ranking + "\nTotal Points: "
                + totalPoints + "\nConfederation " + confederation + "\nCountry Code: "
                + countryCode;
    }
}
