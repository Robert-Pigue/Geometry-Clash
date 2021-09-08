package h;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class FinalBoss extends Monster {
    public FinalBoss() {
        super(0, 0, 1, 50, 1);
        setBody(new Circle(125));
        setWidth(250);
        this.getBody().setFill(Color.YELLOW);
    }

    public void move() {
        if (getIsFrozen()) {
            setFreezeTimer(getFreezeTimer() - 1);
            if (getFreezeTimer() == 0) {
                setIsFrozen(false);
            }
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
    
            if (Math.abs(getCurrentX()) > 200) {
                if (getCurrentX() < 0) {
                    setCurrentX(-200);
                } else {
                    setCurrentX(200);
                }
            }
            if (Math.abs(getCurrentY()) > 100) {
                if (getCurrentY() < 0) {
                    setCurrentY(-100);
                } else {
                    setCurrentY(100);
                }
            }
            getBody().setTranslateX(getCurrentX());
            getBody().setTranslateY(getCurrentY());
        }
    }
}
