public class Team {
    private int id;
    private String name;
    private int fifaRank;

    private int position;
    private String country;
    private double score;
    private String countryAbbr;
    private String confederation;
    private String countryFlagID;


    public Team(int id, String name, int fifaRank) {
        this.id = id;
        this.name = name;
        this.fifaRank = fifaRank;
    }

    public Team(int position, String country, double score, String countryAbbr, String confederation,
    String countryFlagID) {
        this.position = position;
        this.country = country;
        this.score = score;
        this.countryAbbr = countryAbbr;
        this.confederation = confederation;
        this.countryFlagID = countryFlagID;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getCountryAbbr() {
        return countryAbbr;
    }

    public void setCountryAbbr(String countryAbbr) {
        this.countryAbbr = countryAbbr;
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
}
