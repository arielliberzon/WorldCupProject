
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;




public class welcomeGui extends Application {
    private GridPane o = new GridPane();
    private Scene s = new Scene(o);
    private Button one = new Button("start");

    private GridPane loginP;
    public void start(Stage primaryStage) {

        primaryStage.setMaximized(true);
        primaryStage.setTitle("World Cup");
        o.add(createLogin(),0,1);
        primaryStage.setScene(s);
        primaryStage.show();

    }

    /**
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private GridPane createLogin(){
        GridPane loginPane = new GridPane();

        loginPane.setAlignment(Pos.CENTER);
        loginPane.setHgap(10);
        loginPane.setVgap(10);
        loginPane.setPadding(new Insets(5, 5, 5, 5));

        Text welcomeMessage = new Text("World Cup ");
        welcomeMessage.setFill(Color.BLACK);
        loginPane.add(welcomeMessage, 0, 0, 2, 1);

        Label userName = new Label("Username: ");
        userName.setTextFill(Color.BLACK);
        loginPane.add(userName, 0, 1);

        TextField enterUser = new TextField();
        enterUser.setPromptText("Username");
        loginPane.add(enterUser, 1, 1);

        Label password = new Label("Password: ");
        password.setTextFill(Color.BLACK);
        loginPane.add(password, 0, 2);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        loginPane.add(passwordField, 1, 2);

        Label confirmPassword = new Label("Confirm Password:");
        confirmPassword.setTextFill(Color.BLACK);
        loginPane.add(confirmPassword, 0, 3);

        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Confirm Password");
        loginPane.add(confirmPasswordField, 1, 3);

        Button signButton = new Button("Sign in");
        loginPane.add(signButton, 1, 4);
        signButton.setDefaultButton(true);

        Label message = new Label();
        loginPane.add(message, 1, 5);


        signButton.setOnAction(event -> {

            // the username user entered
            String username = enterUser.getText().toLowerCase();
            // the password user enter
            String playerPass = passwordField.getText();



        });

        return loginPane;
    }
}
