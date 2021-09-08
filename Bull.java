package h;

import javafx.scene.paint.Color;

public class Bull extends Monster {
    private int dir;
    private int moveTimer;
    private int randX;
    private int randY;

    public Bull(int currentX, int currentY, int damage, int health, Color color) {
        super(currentX, currentY, damage, health, 2);
        this.dir = 0;
        this.moveTimer = 30;
        this.getBody().setFill(color);
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
                if (dir == 0) {
                    if (moveTimer == 30) {
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
    
                    moveTimer -= 1;
    
                    if (moveTimer == 0) {
                        if (Math.abs(getCurrentX() - MainApp.player.getCurrentX()) >
                        Math.abs(getCurrentY() - MainApp.player.getCurrentY())) {
                            if (getCurrentX() > MainApp.player.getCurrentX()) {
                                dir = 4;
                            } else {
                                dir = 2;
                            }
                        } else {
                            if (getCurrentY() > MainApp.player.getCurrentY()) {
                                dir = 1;
                            } else {
                                dir = 3;
                            }
                        }
                        setSpeed(5);
                    }
                } else {
                    if (dir == 1) {
                        setCurrentY(getCurrentY() - getSpeed());
                    } else if (dir == 2) {
                        setCurrentX(getCurrentX() + getSpeed());
                    } else if (dir == 3) {
                        setCurrentY(getCurrentY() + getSpeed());
                    } else {
                        setCurrentX(getCurrentX() - getSpeed());
                    }
    
                    if (Math.abs(getCurrentX()) > 320) {
                        dir = 0;
                        setSpeed(2);
                        moveTimer = 30;
                        if (getCurrentX() < 0) {
                            setCurrentX(-320);
                        } else {
                            setCurrentX(320);
                        }
                    }
                    if (Math.abs(getCurrentY()) > 220) {
                        dir = 0;
                        setSpeed(2);
                        moveTimer = 30;
                        if (getCurrentY() < 0) {
                            setCurrentY(-220);
                        } else {
                            setCurrentY(220);
                        }
                    }
                }
    
                getBody().setTranslateX(getCurrentX());
                getBody().setTranslateY(getCurrentY());
            }
        }
    }
}
