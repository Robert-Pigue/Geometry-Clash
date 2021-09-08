package h;

import javafx.scene.paint.Color;

public class AttackPotion extends Potion{
    public AttackPotion() {
        super(120, Color.MAROON);
    }

    public void activate() {
        for (Weapon w : MainApp.player.getWeaponList()) {
            w.setDamage(w.getDamage() + 2);
        }
    }

    public void undo() {
        for (Weapon w : MainApp.player.getWeaponList()) {
            w.setDamage(w.getDamage() - 2);
        }
    }
}
