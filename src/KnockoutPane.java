import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

import java.util.ArrayList;
/** note for Zach (delete later):
 *   this class will create the pane for the knockout tab,
 *   Main is composed of this (I'm pretty sure)
 *      - Justin V
 */
public class KnockoutPane extends Pane {

    private ArrayList<Button> buttonList = new ArrayList<>();

    private Label title = new Label("FIFA WORLD CUP BRACKET");
    
    

    public KnockoutPane(){
        this.setBackground(new Background(new BackgroundFill(Color.rgb(88,146,87), CornerRadii.EMPTY, Insets.EMPTY)));
        try {
            Simulator sim = new Simulator();
            //System.out.println(sim.getTeamsInOrderInRoundOfSixteen());
            this.createBracket(sim);
        } catch (Exception e) {
            //TODO: handle exception
        }
        

       
    }

    private void createBracket(Simulator sim){
        int x = 100;                              //The initial X cord of the top left bracket;
        int y = 50;                              //The initial y cord of the top left bracket;
        int scalingFactor = 50;                 //Scaling factor of the bracket, increase = bigger
        int yLength = scalingFactor/2;          //The length of the line going up and down on the L shape. **MIGHT GET REMOVED / TWEAKED since the scaling doesn't 100% fit with it.**
        int horizontalLength = scalingFactor*4;            //The length of the line going left and right on the L shape.  **MIGHT GET REMOVED // TWEAKED since the scaling isn't 100% with it.**
        int yIncrement = scalingFactor*2;       //How much will the next tier bracket be moved down. 
        int yCordsAtTierZero = y;               //Since we change the y cord, we're setting base y at tierZero          
        int yCordsAtTierOne = yCordsAtTierZero + scalingFactor; //Tier zero gets used by the scalingFactor to produce the proper y cords for the tier 
        int yCordsAtTierTwo = yCordsAtTierOne + scalingFactor*2;                         
        int yCordsAtTierThree = yCordsAtTierTwo + scalingFactor*4;
        int yIncrementIncrease = yIncrement;                    //Since we change the yIncrement, we use incrementIncrease to keep the base value; technically can be removed but eh
        double buttonSizeX = scalingFactor * 3;                                    //Button sizes, more convenient
        double buttonSizeY = scalingFactor / 1.25;
        int counter = 0;
        int counterOne = 0;
        ArrayList<Team> sixteenTeams = sim.getTeamsInOrderInRoundOfSixteen();
        ArrayList<Game> sixteenGames = sim.simulateRoundOfSixteen();
        ArrayList<Game> quarterGames = sim.simulateQuarters();
        ArrayList<Game> semiGames = sim.simulateSemis();
        ArrayList<Game> finalAndThirdPlaceGame = sim.simulateFinalAndThirdPlace();
        //scaling factor = 40, 650 = 13
        //scaling factor = 50, 800 = 16
        int paneHeight = (scalingFactor*14+y*2);
        this.setMinHeight(paneHeight);

        

        title.setFont(Font.font("Arial Black", 20));
        
        title.setLayoutY(60);
        this.getChildren().add(title);

        for(int i = 1; i < 32; i++){
            int buttonX = (int)(x - buttonSizeX/2);
            int buttonY = (int)(y - buttonSizeY/2);
            Button button = new Button();
            button.setMinSize(buttonSizeX, buttonSizeY);
            button.setMaxSize(buttonSizeX, buttonSizeY);
            //
            button.setFont(Font.font("Arial Condensed",scalingFactor * 4 / 10));
            button.setTextFill(Color.WHITE);
            button.setBackground(new Background(new BackgroundFill(Color.rgb(69,113,80), CornerRadii.EMPTY, Insets.EMPTY)));

            if(i < 15){
                if(i < 9){
                    button.setText(sixteenTeams.get(i-1).getCountry());
                    //System.out.println(placeHolder.get(i).getCountry());
                }
                else if(i < 13){
                    button.setText(sixteenGames.get(counter).getWinner().getCountry());
                    counter++;
                }
                else if(i < 15){
                    button.setText(quarterGames.get(counterOne).getWinner().getCountry());
                    counterOne++;
                }
                
                if(i % 2 == 1){//odd
                    button.setLayoutX(buttonX);
                    button.setLayoutY(buttonY);
                    drawLines(x, y, false, false, (int)(((yIncrement)/2)-(buttonSizeY)/2), horizontalLength);
                    y += yIncrement;
                }
                else{
                    button.setLayoutX(buttonX);//even
                    button.setLayoutY(buttonY);
                    drawLines(x, y, false, true, (int)(((yIncrement)/2)-(buttonSizeY)/2), horizontalLength);
                    y += yIncrement;
                    if(i == 8){
                        x += horizontalLength;
                        y = yCordsAtTierOne;                                //sets up the yCords for the **NEXT** tier
                        yLength += yIncrementIncrease/2;
                        yIncrement += yIncrementIncrease;
                    }
                    else if(i == 12){
                        x += horizontalLength;
                        y = yCordsAtTierTwo;                                
                        yLength += yIncrementIncrease/2;
                        yIncrement += yIncrementIncrease*2;
                    }
                    else if(i == 14){
                        x += horizontalLength; 
                        y = yCordsAtTierThree;                                
                        yLength += yIncrementIncrease/2;
                    }
                }
            }
            else if(i == 15){
                button.setLayoutX(buttonX);
                button.setLayoutY(buttonY);
                drawLines(x, y, false, true, (int)(((yIncrement)/2)-(buttonSizeY)/2), horizontalLength);
                x += horizontalLength;
                y = yCordsAtTierThree - yLength;
                button.setText(semiGames.get(0).getWinner().getCountry());                             //THIS IS THE SPECIAL CASE. since the button needs to be **UP**
            }
            else if(i == 16){
                button.setLayoutX(buttonX);
                button.setLayoutY(buttonY);
                title.setLayoutX(x-(312/2)); //312 is the rough pixel measurement #GimpForLife
                x += horizontalLength;
                y = yCordsAtTierThree;
                button.setText(finalAndThirdPlaceGame.get(0).getWinner().getCountry());                                      
            }
            else if(i == 17){
                button.setLayoutX(buttonX);
                button.setLayoutY(buttonY);
                drawLines(x, y, true, true,(int)((((yIncrement)/2)-(buttonSizeY))-(buttonSizeY/8)), horizontalLength);
                x += horizontalLength;
                y = yCordsAtTierTwo;                                    
                yLength -= yIncrementIncrease/2;
                button.setText(semiGames.get(1).getWinner().getCountry());
            }
            else if(i > 17){
                if(i < 20){
                    button.setText(quarterGames.get(counterOne).getWinner().getCountry());
                    counterOne++;
                }
                else if(i < 24){
                    button.setText(sixteenGames.get(counter).getWinner().getCountry());
                    counter++;
                }
                else if(i < 32){
                    button.setText(sixteenTeams.get(i-16).getCountry());
                }
                if(i % 2 == 1){
                    button.setLayoutX(buttonX);
                    button.setLayoutY(buttonY);
                    drawLines(x, y, true, true, (int)(((yIncrement)/2)-(buttonSizeY)/2), horizontalLength);
                    y += yIncrement;
                    if(i == 19){
                        x += horizontalLength;
                        y = yCordsAtTierOne;                               
                        yLength -= yIncrementIncrease/2;
                        yIncrement -= yIncrementIncrease*2;
                    }
                    else if(i == 23){
                        x += horizontalLength;
                        y = yCordsAtTierZero;                                
                        yLength -= yIncrementIncrease/2;
                        yIncrement -= yIncrementIncrease;
                    }
                }
                else{
                    button.setLayoutX(buttonX);
                    button.setLayoutY(buttonY);
                    drawLines(x, y, true, false, (int)(((yIncrement)/2)-(buttonSizeY)/2), horizontalLength);
                    y += yIncrement;
                }
            }
            buttonList.add(button);
            this.getChildren().add(button);
        }
    }

    private void drawLines(int x, int y, boolean isLeft, boolean isUp, int yLength, int horizontalLength){
        Line line1;
        Line line2;
        int x_length;

        if(isLeft){
            x_length = x - horizontalLength;
            line1 = new Line(x, y, x_length, y);
        }
        else{
            x_length = x + horizontalLength;
            line1 = new Line(x, y, x_length, y);
        }

        if(isUp){
            line2 = new Line(x_length, y, x_length, y - yLength);
        }
        else{
            line2 = new Line(x_length, y, x_length, y + yLength);
        }

        this.getChildren().addAll(line1, line2);
    }
}