package h;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/**
 * Class that represents a player.
 * @author Olivia, Dan, Robert
 * @version 1.0
 */
public class Player {
    private boolean goNorth;
    private boolean goSouth;
    private boolean goWest;
    private boolean goEast;

    private int money;
    private int currentX;
    private int currentY;
    private ArrayList<Weapon> weaponList = new ArrayList<>();
    private int health;
    private boolean isInvincible;
    private int invincibilityTimer;

    private ArrayList<Integer> weaponInventory = new ArrayList<>();
    private ArrayList<FreezePotion> freezePotions = new ArrayList<>();
    private ArrayList<HealthPotion> healthPotions = new ArrayList<>();
    private ArrayList<AttackPotion> attackPotions = new ArrayList<>();

    private ArrayList<Potion> inUsePotions = new ArrayList<>();
    /**
     * Constructor for Player class.
     */
    public Player() {
        this.money = 0;
        this.goNorth = false;
        this.goEast = false;
        this.goSouth = false;
        this.goWest = false;
        this.health = 10;
        this.currentX = 0;
        this.currentY = 0;
        this.isInvincible = false;
        this.invincibilityTimer = 0;
        this.healthPotions.add(new HealthPotion());
    }

    public void useFreeze() {
        if (freezePotions.size() > 0) {
            Potion p = freezePotions.remove(0);
            p.activate();
            inUsePotions.add(p);
        }
    }
    public void useHealth() {
        if (healthPotions.size() > 0) {
            Potion p = healthPotions.remove(0);
            p.activate();
            inUsePotions.add(p);
        }
    }
    public void useAttack() {
        if (attackPotions.size() > 0) {
            Potion p = attackPotions.remove(0);
            p.activate();
            inUsePotions.add(p);
        }
    }
    /**
     * Getter for isGoingNorth variable.
     * @return returns the variable.
     */
    public boolean isGoingNorth() {
        return goNorth;
    }
    /**
     * Getter for isGoingSouth variable.
     * @return returns the variable.
     */
    public boolean isGoingSouth() {
        return goSouth;
    }
    /**
     * Getter for isGoingWest variable.
     * @return returns the variable.
     */
    public boolean isGoingWest() {
        return goWest;
    }
    /**
     * Getter for isGoingEast variable.
     * @return returns the variable.
     */
    public boolean isGoingEast() {
        return goEast;
    }
    /**
     * Setter for goNorth variable.
     * @param goNorth new value for variable
     */
    public void setNorth(boolean goNorth) {
        this.goNorth = goNorth;
    }
    /**
     * Setter for goWest variable.
     * @param goWest new value for variable
     */
    public void setWest(boolean goWest) {
        this.goWest = goWest;
    }
    /**
     * Setter for goSouth variable.
     * @param goSouth new value for variable
     */
    public void setSouth(boolean goSouth) {
        this.goSouth = goSouth;
    }
    /**
     * Setter for goEast variable.
     * @param goEast new value for variable
     */
    public void setEast(boolean goEast) {
        this.goEast = goEast;
    }

    /**
     * Method for withdrawing money.
     * @param amount amount to withdraw
     * @return returns 0 if they can pay, returns amount otherwise
     */
    public int withdrawMoney(int amount) {
        if (money >= amount) {
            money -= amount;
            return amount;
        } else {
            return 0;
        }
    }

    /**
     * Method for getting money
     * @return money
     */
    public int getMoney() {
        return money;
    }

    /**
     * Method for setting money
     */
    public void setMoney(int val) {
        money = val;
    }

    /**
     * Method for adding money.
     * @param amount amount to add
     */
    public void addMoney(int amount) {
        money += amount;
    }
    /**
     * Method for returning the representation of the player.
     * @return returns the rectangle/player
     */
    public Rectangle getCharacter() {
        Rectangle theCharacter = new Rectangle(20, 20);
        theCharacter.setFill(Color.RED);
        return theCharacter;
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

    public void setWeaponList() {
        int choice = MainApp.weapon;
        if (choice == 1) {
            for (int i = 0; i < 6; i++) {
                weaponList.add(new Bullet(1));
            }
        } else if (choice == 2) {
            for (int i = 1; i <= 4; i ++) {
                weaponList.add(new Ax(5,i));
            }
        } else {
            for (int i = 1; i <= 4; i ++) {
                weaponList.add(new Sword(4,i));
            }
        }
    }

    public ArrayList<Weapon> getWeaponList() {
        return this.weaponList;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        if (health <= 0) {
            new DeathScreen();
            MainApp.theTimer.stop();
        }
        this.health = health;
    }

    public void setInvincibilityTimer(int time) {
        if (time > 0) {
            this.isInvincible = true;
            this.invincibilityTimer = time;
        } else {
            this.isInvincible = false;
        }
    }

    public int getInvincibilityTimer() {
        return this.invincibilityTimer;
    }

    public boolean getIsInvincible() {
        return this.isInvincible;
    }

    public ArrayList<Integer> getWeaponInventory() {
        return this.weaponInventory;
    }

    public ArrayList<FreezePotion> getFreezePotions() {
        return this.freezePotions;
    }

    public ArrayList<HealthPotion> getHealthPotions() {
        return this.healthPotions;
    }

    public ArrayList<AttackPotion> getAttackPotions() {
        return this.attackPotions;
    }

    public ArrayList<Potion> getInUsePotions() {
        return this.inUsePotions;
    }
}