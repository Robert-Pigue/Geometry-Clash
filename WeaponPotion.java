package h;

import java.util.Random;

import javafx.scene.paint.Color;

public class WeaponPotion extends Potion{
    public WeaponPotion() {
        super(1, Color.BLACK);
    }

    public void activate() {
        Random rand = MainApp.rand;
        int mod = rand.nextInt(3) - 1;

        int max = Math.max(MainApp.currentId % 10, MainApp.currentId / 10);

        if (max == 4 || max == 3) {
            mod += max;
        } else {
            mod += 2;
        }

        if (MainApp.weapon == 1) {
            mod += 1;
        } else if (MainApp.weapon == 2) {
            mod += 5;
        } else {
            mod += 4;
        }

        MainApp.player.getWeaponInventory().add(mod);
    }

    public void undo() {
        //filler
    }
}
