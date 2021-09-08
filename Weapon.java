package h;

import javafx.scene.shape.Rectangle;

public abstract class Weapon {
    private int attackTimer;
    private boolean canAttack;
    private int currentX;
    private int currentY;
    private int damage;
    private int direction;
    private Rectangle body = new Rectangle();

    public Weapon(int currentX, int currentY, int damage, int direction) {
        this.attackTimer = 0;
        this.canAttack = true;
        this.currentX = currentX;
        this.currentY = currentY;
        this.damage = damage;
        this.direction = direction;
    }

    public abstract void attack(int dir);

    public abstract void move();

    public abstract void reset();

    public void setAttackTimer(int attackTimer) {
        this.attackTimer = attackTimer;
    }

    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
    }

    public int getAttackTimer() {
        return this.attackTimer;
    }

    public boolean getCanAttack() {
        return this.canAttack;
    }

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

    public Rectangle getBody() {
        return this.body;
    }

    public void setBody(Rectangle body) {
        this.body = body;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return this.damage;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return this.direction;
    }
}
