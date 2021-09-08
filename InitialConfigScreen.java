package h;

import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
/**
 * Class that sets up the initial config screen for changin difficulty, name, and weapon.
 * @author Olivia, Dan, Robert
 * @version 1.0
 */
public class InitialConfigScreen {
    /**
     * Method that creates the initial config screen and switches to the game upon the
     * finished button being clicked.
     */
    public InitialConfigScreen() {
        VBox root = new VBox();
        Label userLabel = new Label("Name:");
        final TextField userField = new TextField();
        userField.setId("userField");
        Button continueButton = new Button("FINISHED");
        continueButton.setId("continueButton");
        Button saveNameButton = new Button("save name");
        saveNameButton.setId("saveNameButton");
        Label difficultyLabel = new Label("Select Difficulty");
        Button easyButton = new Button("Easy");
        easyButton.setId("easyButton");
        Button mediumButton = new Button("Medium");
        mediumButton.setId("mediumButton");
        Button hardButton = new Button("Hard");
        hardButton.setId("hardButton");
        Image weapon1Image = new Image("images/724-7243645_this-is-a-8-bit-weapon-design-for.png",
            20, 20, false, false);
        Image weapon2Image = new Image("images/ax.png", 20, 20, false, false);
        Image weapon3Image = new Image("images/sword.png", 20, 20, false, false);
        Button weapon1Button = new Button();
        Button weapon2Button = new Button();
        Button weapon3Button = new Button();
        weapon1Button.setGraphic(new ImageView(weapon1Image));
        weapon1Button.setId("WOne");
        weapon2Button.setGraphic(new ImageView(weapon2Image));
        weapon2Button.setId("WTwo");
        weapon3Button.setGraphic(new ImageView(weapon3Image));
        weapon3Button.setId("WThree");

        continueButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                new InitialGameScreen();
            }
        });
        saveNameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                if (userField.getText() == null
                    || userField.getText().length() == 0 || userField.getText().trim().length() == 0) {
                    Alert a = new Alert(AlertType.WARNING);
                    a.show();
                } else {
                    MainApp.name = userField.getText();
                }
            }
        });
        easyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                MainApp.difficulty = 1;
            }
        });
        mediumButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                MainApp.difficulty = 2;
            }
        });
        hardButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                MainApp.difficulty = 3;
            }
        });
        weapon1Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                MainApp.weapon = 1;
            }
        });
        weapon2Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                MainApp.weapon = 2;
            }
        });
        weapon3Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                MainApp.weapon = 3;
            }
        });

        root.getChildren().addAll(userLabel, userField, saveNameButton, difficultyLabel, easyButton, mediumButton, hardButton,
                                weapon1Button, weapon2Button, weapon3Button, continueButton);
        root.setBackground(new Background(new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY)));
        MainApp.currStage.setScene(new Scene(root, 500, 350));
        MainApp.currStage.setTitle("Initial Config");
    }
}
