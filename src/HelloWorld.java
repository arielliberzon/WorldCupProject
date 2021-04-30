import java.io.IOException;

// TODO: Remove this class before final submission
public class HelloWorld {

    public static void main(String args[]) throws IOException {

        Team teamOne = new Team(1, "ARG", 1000, "ARG", "SA");
        Team teamTwo = new Team(1, "BRA", 800, "ARG", "SA");


        //Print results for 3 demonstrative games
        for(int i = 0 ; i < 3; i++) {
            Game test = new Game(teamOne, teamTwo, false);
            System.out.println("Game number: " + i);
            System.out.println("Half time scores:\n" + test.getFirst45ScoreString());
            System.out.println("Full 90 time scores:\n" +test.getSecond45ScoreString());
            if(test.wasOverTimeUsed()) {
                System.out.println("First 15 overtime:\n" + test.getFirst15ScoreString());
                System.out.println("Second 15 overtime:\n" + test.getSecond15ScoreString());
                if(test.werePenaltyKicksReached()) {
                    System.out.println("Game went to penalty kicks!");
                    System.out.println(test.getFinalScoreString());
                }
            }

            if(test.wasTiedGame())
                System.out.println("The game was a tie");
            else {
                System.out.println("Winner: " + test.getWinner().getCountry());
                System.out.println("Loser: " + test.getLoser().getCountry());
            }
            System.out.println();
        }

        //Just to show simulator
        Simulator simulator = new Simulator();
        simulator.simulateRoundOfSixteen();
        simulator.simulateQuarters();
        simulator.simulateSemis();
        simulator.simulateFinalAndThirdPlace();

    }
}