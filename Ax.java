package h;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Ax extends Weapon {

    public Ax(int damage, int direction) {
        super(MainApp.player.getCurrentX(), MainApp.player.getCurrentY(), damage, direction);
        Rectangle axBody;
        if (direction == 1) {
            axBody = new Rectangle(20, 16);
            setCurrentY(getCurrentY() - 18);
        } else if (direction == 3) {
            axBody = new Rectangle(20, 16);
            setCurrentY(getCurrentY() + 18);
        } else if (direction== 2) {
            axBody = new Rectangle(16, 20);
            setCurrentX(getCurrentX() + 18);
        } else {
            axBody = new Rectangle(16, 20);
            setCurrentX(getCurrentX() - 18);
        }
        axBody.setFill(Color.BLACK);
        axBody.setVisible(false);
        axBody.setTranslateX(getCurrentX());
        axBody.setTranslateY(getCurrentY());
        setBody(axBody);
    }

    public void reset() {
        setCurrentX(MainApp.player.getCurrentX());
        setCurrentY(MainApp.player.getCurrentY());
        if (getDirection() == 1) {
            setCurrentY(getCurrentY() - 18);
        } else if (getDirection() == 3) {
            setCurrentY(getCurrentY() + 18);
        } else if (getDirection()== 2) {
            setCurrentX(getCurrentX() + 18);
        } else {
            setCurrentX(getCurrentX() - 18);
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
            setCurrentY(getCurrentY() - 18);
        } else if (getDirection() == 3) {
            setCurrentY(getCurrentY() + 18);
        } else if (getDirection()== 2) {
            setCurrentX(getCurrentX() + 18);
        } else {
            setCurrentX(getCurrentX() - 18);
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
        setAttackTimer(11);
    }
}
