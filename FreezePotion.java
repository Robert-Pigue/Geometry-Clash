package h;

import javafx.scene.paint.Color;

public class FreezePotion extends Potion {
    public FreezePotion() {
        super(90, Color.AZURE);
    }

    public void activate() {
        Room currentRoom = MainApp.dungeon[MainApp.currentId / 10][MainApp.currentId % 10];
        for (Monster m : currentRoom.getEnemyList()) {
            m.setIsFrozen(true);
            m.setFreezeTimer(91);
        }
    }

    public void undo() {
        Room currentRoom = MainApp.dungeon[MainApp.currentId / 10][MainApp.currentId % 10];
        for (Monster m : currentRoom.getEnemyList()) {
            m.setIsFrozen(false);
            m.setFreezeTimer(0);
        }
    }
}
