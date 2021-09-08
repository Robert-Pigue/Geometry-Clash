package h;

import javafx.scene.paint.Color;

public class RandomWalker extends Monster{
    private int randX;
    private int randY;

    public RandomWalker(int currentX, int currentY, int damage, int health, Color color) {
        super(currentX, currentY, damage, health, 3);
        this.getBody().setFill(color);
        this.randX = getRandom(-320, 320);
        this.randY = getRandom(-220, 220);
    }

    public void move() {
        Room currentRoom = MainApp.dungeon[MainApp.currentId / 10][MainApp.currentId % 10];
        if (!currentRoom.getIsMonsterPaused()) {
            if (getIsFrozen()) {
                setFreezeTimer(getFreezeTimer() - 1);
                if (getFreezeTimer() == 0) {
                    setIsFrozen(false);
                }
            } else {
                if (getCurrentX() == randX && getCurrentY() == randY) {
                    randX = getRandom(-320, 320);
                    randY = getRandom(-220, 220);
                }
    
                if (getCurrentX() > randX) {
                    setCurrentX(getCurrentX() - Math.min(getCurrentX() - randX, getSpeed()));
                } else if (getCurrentX() < randX) {
                    setCurrentX(getCurrentX() + Math.min(randX - getCurrentX(), getSpeed()));
                }
                if (getCurrentY() > randY) {
                    setCurrentY(getCurrentY() - Math.min(getCurrentY() - randY, getSpeed()));
                } else if (getCurrentY() < randY) {
                    setCurrentY(getCurrentY() + Math.min(randY - getCurrentY(), getSpeed()));
                }
    
                if (Math.abs(getCurrentX()) > 320) {
                    if (getCurrentX() < 0) {
                        setCurrentX(-320);
                    } else {
                        setCurrentX(320);
                    }
                    System.out.println("hi");
                }
                if (Math.abs(getCurrentY()) > 220) {
                    if (getCurrentY() < 0) {
                        setCurrentY(-220);
                    } else {
                        setCurrentY(220);
                    }
                    randX = getRandom(-320, 320);
                    randY = getRandom(-220, 220);
                    System.out.println(randX + " " + randY);
                }
    
                getBody().setTranslateX(getCurrentX());
                getBody().setTranslateY(getCurrentY());
            }
        }
    }
}
