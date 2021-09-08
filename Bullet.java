package h;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Bullet extends Weapon {
    private int speed;

    public Bullet(int damage) {
        super(MainApp.player.getCurrentX(), MainApp.player.getCurrentY(), damage, 1);
        this.speed = 7;
        Rectangle bulletBody = new Rectangle(6, 6);
        bulletBody.setFill(Color.BLACK);
        bulletBody.setTranslateX(getCurrentX());
        bulletBody.setTranslateY(getCurrentY());
        bulletBody.setVisible(false);
        setBody(bulletBody);
    }

    public void reset() {
        setCurrentX(MainApp.player.getCurrentX());
        setCurrentY(MainApp.player.getCurrentY());
        getBody().setTranslateX(getCurrentX());
        getBody().setTranslateY(getCurrentY());
        getBody().setVisible(false);
        setCanAttack(true);
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void move() {
        if (getCanAttack()) {
            setCurrentX(MainApp.player.getCurrentX());
            setCurrentY(MainApp.player.getCurrentY());
            getBody().setTranslateX(getCurrentX());
            getBody().setTranslateY(getCurrentY());
        } else {
            if (getDirection() == 1) {
                setCurrentY(getCurrentY() - this.speed);
            } else if (getDirection() == 3) {
                setCurrentY(getCurrentY() + this.speed);
            } else if (getDirection() == 2) {
                setCurrentX(getCurrentX() + this.speed);
            } else {
                setCurrentX(getCurrentX() - this.speed);
            }
            if (Math.abs(getCurrentX()) > 322 || Math.abs(getCurrentY()) > 222) {
                reset();
            } else {
                getBody().setTranslateX(getCurrentX());
                getBody().setTranslateY(getCurrentY());
            }
        }
    }

    public void attack(int dir) {
        setDirection(dir);
        setCanAttack(false);
        getBody().setVisible(true);
        if (getDirection() == 1) {
            setCurrentY(getCurrentY() - 13);
        } else if (getDirection() == 3) {
            setCurrentY(getCurrentY() + 13);
        } else if (getDirection() == 2) {
            setCurrentX(getCurrentX() + 13);
        } else {
            setCurrentX(getCurrentX() - 13);
        }
        getBody().setTranslateX(getCurrentX());
        getBody().setTranslateY(getCurrentY());
    }

}
