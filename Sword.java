package h;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Sword extends Weapon{
    public Sword(int damage, int direction) {
        super(MainApp.player.getCurrentX(), MainApp.player.getCurrentY(), damage, direction);
        Rectangle swordBody;
        if (direction == 1) {
            swordBody = new Rectangle(7, 24);
            setCurrentY(getCurrentY() - 22);
        } else if (direction == 3) {
            swordBody = new Rectangle(7, 24);
            setCurrentY(getCurrentY() + 22);
        } else if (direction== 2) {
            swordBody = new Rectangle(24, 7);
            setCurrentX(getCurrentX() + 22);
        } else {
            swordBody = new Rectangle(24, 7);
            setCurrentX(getCurrentX() - 22);
        }
        swordBody.setFill(Color.BLACK);
        swordBody.setVisible(false);
        swordBody.setTranslateX(getCurrentX());
        swordBody.setTranslateY(getCurrentY());
        setBody(swordBody);
    }

    public void reset() {
        setCurrentX(MainApp.player.getCurrentX());
        setCurrentY(MainApp.player.getCurrentY());
        if (getDirection() == 1) {
            setCurrentY(getCurrentY() - 22);
        } else if (getDirection() == 3) {
            setCurrentY(getCurrentY() + 22);
        } else if (getDirection()== 2) {
            setCurrentX(getCurrentX() + 22);
        } else {
            setCurrentX(getCurrentX() - 22);
        }
        getBody().setTranslateX(getCurrentX());
        getBody().setTranslateY(getCurrentY());
        getBody().setVisible(false);
        setCanAttack(true);
    }

    public void move() {
        setCurrentX(MainApp.player.getCurrentX());
        setCurrentY(MainApp.player.getCurrentY());
        if (getDirection() == 1) {
            setCurrentY(getCurrentY() - 22);
        } else if (getDirection() == 3) {
            setCurrentY(getCurrentY() + 22);
        } else if (getDirection()== 2) {
            setCurrentX(getCurrentX() + 22);
        } else {
            setCurrentX(getCurrentX() - 22);
        }
        getBody().setTranslateX(getCurrentX());
        getBody().setTranslateY(getCurrentY());
        if (!getCanAttack()) {
            setAttackTimer(getAttackTimer() - 1);
            if (getAttackTimer() == 0) {
                getBody().setVisible(false);
                setCanAttack(true);
            }
        }
    }

    public void attack(int dir) {
        getBody().setVisible(true);
        setCanAttack(false);
        setAttackTimer(7);
    }
}
