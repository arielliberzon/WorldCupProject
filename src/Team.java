public class Team {
    private int position;
    private String country;
    private double score;
    private String countryAbbr;
    private String confederation;
    private String countryFlag;

    public Team(int position, String country, double score, String countryAbbr, String confederation,
                String countryFlag) {
        this.position = position;
        this.country = country;
        this.score = score;
        this.countryAbbr = countryAbbr;
        this.confederation = confederation;
        this.countryFlag = countryFlag;
    }
}
