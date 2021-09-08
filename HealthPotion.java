package h;

import javafx.scene.paint.Color;

public class HealthPotion extends Potion {
    public HealthPotion() {
        super(1, Color.GOLD);
    }

    public void activate() {
        MainApp.player.setHealth(MainApp.player.getHealth() + 5);
    }

    public void undo() {
        //filler
    }
}
