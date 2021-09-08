package h;


import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
/**
 * Class that sets up the welcome screen that pops up when you start the game.
 * @author Olivia, Dan, Robert
 * @version 1.0
 */
public class WelcomeScreen {
    /**
     * Method that creates the welcome screen and switches to initial config screen
     * upon the start button being clicked.
     */
    public WelcomeScreen() {
        Label label = new Label("Geometry Clash");
        label.setFont(new Font("Arial", 30));
        label.setTextFill(Color.BLACK);

        Button start = new Button("Enter Game");
        start.setId("start");
        start.setFont(new Font("Arial", 25));
        start.setTextFill(Color.BLACK);

        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                new InitialConfigScreen();
            }
        });

        VBox vBox = new VBox(label, start);
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vBox, 500, 350);
        Background background = new Background(new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY));
        vBox.setBackground(background);

        MainApp.currStage.setTitle("Welcome Screen");
        MainApp.currStage.setScene(scene);
        MainApp.currStage.show();
    }
}
