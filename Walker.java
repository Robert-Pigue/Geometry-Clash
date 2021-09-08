package h;

import javafx.scene.paint.Color;

public class Walker extends Monster {

    private int randTimer;
    private int randX;
    private int randY;

    public Walker(int currentX, int currentY, int damage, int health, Color color) {
        super(currentX, currentY, damage, health, 2);
        this.getBody().setFill(color);
        this.randTimer = 0;
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
                if (randTimer == 0) {
                    if (Math.random() < 0.02) {
                        randX = getRandom(-320, 320);
                        randY = getRandom(-220, 220);
                        randTimer = 30;
                    } else {
                        if (getCurrentX() > MainApp.player.getCurrentX()) {
                            setCurrentX(getCurrentX() - getSpeed());
                        } else if (getCurrentX() < MainApp.player.getCurrentX()) {
                            setCurrentX(getCurrentX() + getSpeed());
                        }
                        if (getCurrentY() > MainApp.player.getCurrentY()) {
                            setCurrentY(getCurrentY() - getSpeed());
                        } else if (getCurrentY() < MainApp.player.getCurrentY()) {
                            setCurrentY(getCurrentY() + getSpeed());
                        }
                
                        if (Math.abs(getCurrentX()) > 320) {
                            if (getCurrentX() < 0) {
                                setCurrentX(-320);
                            } else {
                                setCurrentX(320);
                            }
                        }
                        if (Math.abs(getCurrentY()) > 220) {
                            if (getCurrentY() < 0) {
                                setCurrentY(-220);
                            } else {
                                setCurrentY(220);
                            }
                        }
                    }
                }
    
                if (randTimer > 0) {
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
                    randTimer -= 1;
                }
                getBody().setTranslateX(getCurrentX());
                getBody().setTranslateY(getCurrentY());
            }
        }
    }
}
