package h;

import java.util.Random;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public abstract class Monster {
    private int currentX;
    private int currentY;
    private int prevX;
    private int prevY;
    private int width;
    private int damage;
    private int health;
    private int freezeTimer;
    private Shape body = new Rectangle(10, 10);
    private int speed;
    private boolean isFrozen;
    private boolean hasPotion;
    private Potion potion = new HealthPotion();
    private boolean paused;

    public Monster(int currentX, int currentY, int damage, int health, int speed) {
        this.currentX = currentX;
        this.currentY = currentY;
        this.damage = damage;
        this.health = health;
        this.freezeTimer = 30;
        this.width = 10;
        this.speed = speed;
        this.isFrozen = true;
        this.prevX = currentX;
        this.prevY = currentY;
        this.paused = false;

        int choice = MainApp.rand.nextInt(20);
        if (choice < 10) {
            this.hasPotion = true;
            if (choice == 0) {
                this.potion = new WeaponPotion();
            } else if (choice % 3 == 0) {
                this.potion = new HealthPotion();
            } else if (choice % 3 == 1) {
                this.potion = new FreezePotion();
            } else {
                this.potion = new AttackPotion();
            }
        } else {
            this.hasPotion = false;
        }

    }

    public abstract void move();

    /**
     * Setter for currentX variable.
     * @param currentX the new value for the variable
     */
    public void setCurrentX(int currentX) {
        this.currentX = currentX;
    }
    /**
     * Setter for currentY variable.
     * @param currentY the new value for the variable
     */
    public void setCurrentY(int currentY) {
        this.currentY = currentY;
    }
    /**
     * Getter for currentX variable.
     * @return returns the variable.
     */
    public int getCurrentX() {
        return currentX;
    }
    /**
     * Getter for currentY variable.
     * @return returns the variable.
     */
    public int getCurrentY() {
        return currentY;
    }

    public Shape getBody() {
        return this.body;
    }

    public void setBody(Shape body) {
        this.body = body;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return this.damage;
    }

    public int getFreezeTimer() {
        return this.freezeTimer;
    }

    public void setFreezeTimer(int time) {
        this.freezeTimer = time;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean getIsFrozen() {
        return this.isFrozen;
    }

    public void setIsFrozen(boolean isFrozen) {
        this.isFrozen = isFrozen;
    }

    public boolean getHasPotion() {
        return this.hasPotion;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return this.width;
    }

    public void setPrevY(int prevY) {
        this.prevY = prevY;
    }

    public int getPrevY() {
        return this.prevY;
    }

    public void setPrevX(int prevX) {
        this.prevX = prevX;
    }

    public int getPrevX() {
        return this.prevX;
    }

    public Potion getPotion() {
        return this.potion;
    }

    public boolean getPaused() {
        return this.paused;
    }

    public void setIsPaused(boolean paused) {
        this.paused = paused;
    }

    /**
     * Gets a random integer between the minimum and maixmum integers, inclusive.
     * @param min The minimum integer.
     * @param max The maximimum integer.
     * @return A random integer between minimum and maximum integer
     */
    public int getRandom(int min, int max) {
        Random rand = MainApp.rand;
        int x = rand.nextInt(max - min + 1);
        return min + x;
    }
}
