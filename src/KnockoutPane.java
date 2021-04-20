import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import java.util.ArrayList;

public class KnockoutPane extends Pane {

    ArrayList<Button> buttonList = new ArrayList<>();

    public KnockoutPane(){
        this.createBracket();
    }

    private void createBracket(){
        int x = 25;
        int y = 25;
        
        int yLength = 10;
        int horizontalLength = 100;
        int yCordsAtTierZero = 25;
        int yCordsAtTierOne = yCordsAtTierZero*2;
        int yIncrement = yCordsAtTierZero*2;
        int yCordsAtTierTwo = 25;
        int yCordsAtTierThree = 45;
        int yCordsAtFinal = 20;
        int yIncrementIncrease = 20;

        for(int i = 1; i < 32; i++){
            Button button = new Button();
            button.setMinSize(1, 1);
            if(i < 15){
                if(i % 2 == 1){
                    button.setLayoutX(x);
                    button.setLayoutY(y);
                    drawLines(x, y, false, false, yLength);
                    y += yIncrement;
                }
                else{
                    button.setLayoutX(x);
                    button.setLayoutY(y);
                    drawLines(x, y, false, true, yLength);
                    y += yIncrement;
                    if(i == 8){
                        x += horizontalLength;
                        y = yCordsAtTierOne; //sets up the yCords for the **NEXT** tier                                 //10
                        yLength += yIncrementIncrease/2;
                        yIncrement += yIncrementIncrease;
                    }
                    else if(i == 12){
                        x += horizontalLength;
                        y = yCordsAtTierTwo;                                //25
                        yLength += yIncrementIncrease/2;
                        yIncrement += yIncrementIncrease;
                    }
                    else if(i == 14){
                        x += horizontalLength; 
                        y = yCordsAtTierThree;                                //45
                        yLength += yIncrementIncrease/2;
                    }
                }
            }
            else if(i == 15){
                button.setLayoutX(x);
                button.setLayoutY(y);
                drawLines(x, y, false, true, yLength);
                x += horizontalLength;
                y = yCordsAtFinal;                                         //20
            }
            else if(i == 16){
                button.setLayoutX(x);
                button.setLayoutY(y);
                x +=horizontalLength;
                y = yCordsAtTierThree;                                         //45
            }
            else if(i == 17){
                button.setLayoutX(x);
                button.setLayoutY(y);
                drawLines(x, y, true, true, yLength);
                x += horizontalLength;
                y = yCordsAtTierTwo;                                         //25
                yLength -= yIncrementIncrease/2;
            }
            else if(i > 17){
                if(i % 2 == 1){
                    button.setLayoutX(x);
                    button.setLayoutY(y);
                    drawLines(x, y, true, true, yLength);
                    y += yIncrement;
                    if(i == 19){
                        x += horizontalLength;
                        y = yCordsAtTierOne;                                 //10
                        yLength -= yIncrementIncrease/2;
                        yIncrement -= yIncrementIncrease;
                    }
                    else if(i == 23){
                        x += horizontalLength;
                        y = yCordsAtTierZero;                                 //0
                        yLength -= yIncrementIncrease/2;
                        yIncrement -= yIncrementIncrease;
                    }
                }
                else{
                    button.setLayoutX(x);
                    button.setLayoutY(y);
                    drawLines(x, y, true, false, yLength);
                    y += yIncrement;
                }
            }
            buttonList.add(button);
            this.getChildren().add(button);
        }
    }

    private void drawLines(int x, int y, boolean isLeft, boolean isUp, int yLength){
        Line line1;
        Line line2;
        int x_length;
        int horizontalLength = 100;

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