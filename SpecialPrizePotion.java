package h;

import javafx.scene.paint.Color;

public class SpecialPrizePotion extends Potion{
    public SpecialPrizePotion() {
        super(1, Color.SILVER);
    }

    public void activate() {
        int mod = 7;

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
