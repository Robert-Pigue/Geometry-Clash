package h;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
/**
 * Class that controls the player inverntory screen and functionality.
 * @author Dan
 * @version 1.0
 */
public class Inventory {
    private int counter = 0;
    private Label infoLabel = new Label(getInfo());
    /**
     * Constructor for inventory class.
     */
    public Inventory() {
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Inventory");
        titleLabel.setFont(new Font(30));

        HBox moveBox = new HBox();
        moveBox.setSpacing(20);
        moveBox.setAlignment(Pos.CENTER);
        moveBox.setMinWidth(350);

        Button left = new Button("Previous");
        left.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                counter--;
                infoLabel.setText(getInfo());
            }
        });
        Button right = new Button("Right");
        right.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                counter++;
                infoLabel.setText(getInfo());
            }
        });
        moveBox.getChildren().addAll(left, right);

        HBox optionBox = new HBox();
        optionBox.setSpacing(20);
        optionBox.setAlignment(Pos.CENTER);

        Button back = new Button("Go Back");
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Room currentRoom = MainApp.dungeon[MainApp.currentId / 10][MainApp.currentId % 10];
                currentRoom.setIsPaused(false);
                MainApp.currStage.setTitle("Inventory");
                MainApp.currStage.setScene(currentRoom.getScene());
            }
        });
        Button use = new Button("Use Item");
        use.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (MainApp.player.getWeaponInventory().size() > 0) {
                    for (Weapon w : MainApp.player.getWeaponList()) {
                        w.setDamage(MainApp.player.getWeaponInventory().get(counter));
                    }
                }
            }
        });
        moveBox.getChildren().addAll(back, use);

        vBox.getChildren().addAll(titleLabel, infoLabel, moveBox, optionBox);

        vBox.setBackground(new Background(new BackgroundFill(Color.PINK, 
                                                                CornerRadii.EMPTY, Insets.EMPTY)));

        Scene scene = new Scene(vBox, 500, 350);

        MainApp.currStage.setScene(scene);
        MainApp.currStage.setTitle("Inventory");
    }
    /**
     * Class that displays weapon stats in inventory.
     * @return returns the string reprersenting the info.
     */
    public String getInfo() {
        if (MainApp.player.getWeaponInventory().size() == 0) {
            return "There are no weapons in the inventory.";
        } else {
            if (counter == MainApp.player.getWeaponInventory().size()) {
                counter = 0;
            }
            if (counter == -1) {
                counter = MainApp.player.getWeaponInventory().size() - 1;
            }
        }

        return "Damage: " + MainApp.player.getWeaponInventory().get(counter);
    }
}