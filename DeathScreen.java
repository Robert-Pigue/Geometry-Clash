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

public class DeathScreen {
    public DeathScreen() {
        Label label = new Label("YOU DIED");
        label.setFont(new Font("Arial", 30));
        label.setTextFill(Color.BLACK);

        Label money = new Label("MONEY: " + MainApp.player.getMoney());
        money.setFont(new Font("Arial", 30));
        money.setTextFill(Color.BLACK);

        Button again = new Button("Play Again");
        again.setId("again");
        again.setFont(new Font("Arial", 25));
        again.setTextFill(Color.BLACK);

        again.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                MainApp.reset();
                new WelcomeScreen();
            }
        });

        VBox vBox = new VBox(label, again, money);
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vBox, 500, 350);
        
        Background background = new Background(new BackgroundFill(Color.PINK, 
                                    CornerRadii.EMPTY, Insets.EMPTY));
        vBox.setBackground(background);

        MainApp.currStage.setTitle("Win Screen");
        MainApp.currStage.setScene(scene);
        MainApp.currStage.show();
    }
}