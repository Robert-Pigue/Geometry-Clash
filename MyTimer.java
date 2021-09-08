package h;

import javafx.animation.AnimationTimer;
import javafx.scene.shape.Shape;
/**
 * Class that handles the movement of the player.
 * @author Olivia, Dan, Robert
 * @version 1.0
 */
public class MyTimer extends AnimationTimer {
    private volatile boolean running;

    /**
     * Overrides start to add update to running variable
     */
    @Override
    public void start() {
         super.start();
         running = true;
    }

    /**
     * Overrides stop to add update to running variable
     */
    @Override
    public void stop() {
        super.stop();
        running = false;
    }

    /**
     * Method for returning if timer is running
     * @return bool of running
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Method for determing which way the player is going.
     * @param num used for handling the event
     */
    public void handle(long num) {
        Room currentRoom = MainApp.dungeon[MainApp.currentId / 10][MainApp.currentId % 10];
        if (!currentRoom.getIsPaused()) {
            int dx = 0;
            int dy = 0;

            if (MainApp.player.isGoingNorth()) {
                dy -= 5;
            }
            if (MainApp.player.isGoingSouth()) {
                dy += 5;
            }
            if (MainApp.player.isGoingEast()) {
                dx += 5;
            }
            if (MainApp.player.isGoingWest()) {
                dx -= 5;
            }
            moveCharacterTo(dx, dy);
            if (MainApp.player.getIsInvincible()) {
                MainApp.player.setInvincibilityTimer(MainApp.player.getInvincibilityTimer() - 1);
            }

            //Move Weapons
            for (Weapon w : MainApp.player.getWeaponList()) {
                w.move();
            }

            //Move Monster
            for (Monster m : currentRoom.getEnemyList()) {
                m.setPrevX(m.getCurrentX());
                m.setPrevY(m.getCurrentY());
                m.move();
            }

            //Check weapon damage
            for (Weapon w : MainApp.player.getWeaponList()) {
                if (w.getBody().isVisible()) {
                    for (Monster m : currentRoom.getEnemyList()) {
                        if (!m.getIsFrozen()) {
                            if (isIntersecting(w.getBody(), m.getBody())) {
                                m.setHealth(m.getHealth() - w.getDamage());
                                w.reset();
                                if (m.getHealth() <= 0) {
                                    m.getBody().setVisible(false);
                                    if (m.getHasPotion()) {
                                        m.getPotion().getBody().setVisible(true);
                                        m.getPotion().getBody().setTranslateX(m.getCurrentX());
                                        m.getPotion().getBody().setTranslateY(m.getCurrentY());
                                        currentRoom.getActivePotions().add(m.getPotion());
                                    }
                                    currentRoom.getEnemyList().remove(m);
                                    if (currentRoom.getEnemyList().size() == 0) {
                                        currentRoom.setCleared(true);
                                        if (currentRoom.getIsChallengeRoom()) {
                                            currentRoom.showPrize();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            //Check if potions picked up
            for (Potion p : currentRoom.getActivePotions()) {
                if (isIntersecting(p.getBody(), currentRoom.getRect())) {
                    if (p instanceof HealthPotion) {
                        MainApp.player.getHealthPotions().add((HealthPotion) p);
                    } else if (p instanceof AttackPotion) {
                        MainApp.player.getAttackPotions().add((AttackPotion) p);
                    } else if (p instanceof FreezePotion) {
                        MainApp.player.getFreezePotions().add((FreezePotion) p);
                    } else {
                        p.activate();
                    }
                    p.getBody().setVisible(false);
                    currentRoom.getActivePotions().remove(p);
                    currentRoom.updateLabel();
                }
            }

            //Check for monster damage
            if (!MainApp.player.getIsInvincible()) {
                for (Monster m : currentRoom.getEnemyList()) {
                    if (!m.getIsFrozen()) {
                        if (isIntersecting(m.getBody(), currentRoom.getRect())) {
                            MainApp.player.setHealth(MainApp.player.getHealth() - m.getDamage());
                            MainApp.player.setInvincibilityTimer(30);
                            currentRoom.updateLabel();
                        }
                    }
                }
            }

            //Coutdown potions
            for (Potion p : MainApp.player.getInUsePotions()) {
                p.countdown();
                if (!p.getIsActive()) {
                    MainApp.player.getInUsePotions().remove(p);
                }
            }

            //Fix positioning
            if (!currentRoom.getIsPaused()) {
                for (Monster m : currentRoom.getEnemyList()) {
                    if (isIntersecting(m.getBody(), currentRoom.getRect())) {
                        if(Math.abs(m.getCurrentX() - MainApp.player.getCurrentX()) < 10) {
                            if (m.getPrevX() > m.getCurrentX()) {
                                m.setCurrentX(MainApp.player.getCurrentX() + 10 + (m.getWidth() / 2));
                            } else {
                                m.setCurrentX(MainApp.player.getCurrentX() - 10 - (m.getWidth() / 2));
                            }
                        }
                        if(Math.abs(m.getCurrentY() - MainApp.player.getCurrentY()) < 10) {
                            if (m.getPrevY() > m.getCurrentY()) {
                                m.setCurrentY(MainApp.player.getCurrentY() + 10 + (m.getWidth() / 2));
                            } else {
                                m.setCurrentY(MainApp.player.getCurrentY() - 10 - (m.getWidth() / 2));
                            }
                        }
                    }
                }
            }
        }
    }
    /**
     * Method that moves the player based on info from handle.
     * @param dx movement in x direction
     * @param dy movement in y direction
     */
    public void moveCharacterTo(int dx, int dy) {
        if (dx != 0 || dy != 0) {
            Room currentRoom = MainApp.dungeon[MainApp.currentId / 10][MainApp.currentId % 10];
            int prevXPos = MainApp.player.getCurrentX();
            int prevYPos = MainApp.player.getCurrentY();
            int newXPos = prevXPos + dx;
            int newYPos = prevYPos + dy;

            //check walls and exits
            if (newXPos < -315) {
                if (!(Math.abs(newYPos) <= 40)) {
                    if (prevXPos < -315) {
                        if (newYPos > 0) {
                            newYPos = 40;
                        } else {
                            newYPos = -40;
                        }
                    } else {
                        newXPos = -315;
                    }
                } else {
                    if (!(currentRoom.getExits().contains("4"))) {
                        newXPos = -315;
                    }
                }
            }
            if (newXPos > 315) {
                if (!(Math.abs(newYPos) <= 40)) {
                    if (prevXPos > 315) {
                        if (newYPos > 0) {
                            newYPos = 40;
                        } else {
                            newYPos = -40;
                        }
                    } else {
                        newXPos = 315;
                    }
                } else {
                    if (!(currentRoom.getExits().contains("2"))) {
                        newXPos = 315;
                    }
                }
            }
            if (newYPos < -215) {
                if (!(Math.abs(newXPos) <= 40)) {
                    if (prevYPos < -215) {
                        if (newXPos > 0) {
                            newXPos = 40;
                        } else {
                            newXPos = -40;
                        }
                    } else {
                        newYPos = -215;
                    }
                } else {
                    if (!(currentRoom.getExits().contains("1"))) {
                        newYPos = -215;
                    }
                }
            }
            if (newYPos > 215) {
                if (!(Math.abs(newXPos) <= 40)) {
                    if (prevYPos > 215) {
                        if (newXPos > 0) {
                            newXPos = 40;
                        } else {
                            newXPos = -40;
                        }
                    } else {
                        newYPos = 215;
                    }
                } else {
                    if (!(currentRoom.getExits().contains("3"))) {
                        newYPos = 215;
                    }
                }
            }

            //check exits
            if (MainApp.currentId == 44 && currentRoom.isCleared()) {
                MainApp.theTimer.stop();
                new WinScreen();
            } else {
                if (newXPos < -340) {
                    if (currentRoom.isCleared()) {
                        MainApp.currentId -= 1;
                        MainApp.player.setCurrentX(330);
                        MainApp.player.setCurrentY(newYPos);
                        newXPos = 330;
                        MainApp.currStage.setScene(MainApp.dungeon[MainApp.currentId / 10][MainApp.currentId % 10].getScene());
                    } else {
                        newXPos = -340;
                    }
                }
                if (newXPos > 340) {
                    if (currentRoom.isCleared()) {
                        MainApp.currentId += 1;
                        MainApp.player.setCurrentX(-330);
                        MainApp.player.setCurrentY(newYPos);
                        newXPos = -330;
                        MainApp.currStage.setScene(MainApp.dungeon[MainApp.currentId / 10][MainApp.currentId % 10].getScene());
                    } else {
                        newXPos = 340;
                    }
                }
                if (newYPos < -240) {
                    if (currentRoom.isCleared()) {
                        MainApp.currentId -= 10;
                        MainApp.player.setCurrentX(newXPos);
                        MainApp.player.setCurrentY(230);
                        newYPos = 230;
                        MainApp.currStage.setScene(MainApp.dungeon[MainApp.currentId / 10][MainApp.currentId % 10].getScene());
                    } else {
                        newYPos = -240;
                    }
                }
                if (newYPos > 240) {
                    if (currentRoom.isCleared()) {
                        MainApp.currentId += 10;
                        MainApp.player.setCurrentX(newXPos);
                        MainApp.player.setCurrentY(-230);
                        newYPos = -230;
                        MainApp.currStage.setScene(MainApp.dungeon[MainApp.currentId / 10][MainApp.currentId % 10].getScene());
                    } else {
                        newYPos = 240;
                    }
                }

                MainApp.player.setCurrentX(newXPos);
                MainApp.player.setCurrentY(newYPos);

                currentRoom.movePlayer(newXPos, newYPos);
            }
        }
    }

    public boolean isIntersecting(Shape one, Shape two) {
        return one.getBoundsInParent().intersects(two.getBoundsInParent());
    }

}