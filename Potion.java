package h;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Potion {
    private Rectangle body = new Rectangle(5, 5);
    private int countdownTimer;
    private boolean isActive;

    public Potion(int countdownTimer, Color color) {
        body.setFill(color);
        body.setVisible(false);
        this.isActive = false;
        this.countdownTimer = countdownTimer;
    }

    public void countdown() {
        countdownTimer -= 1;
        if (countdownTimer == 0) {
            this.isActive = false;
            undo();
        }
    }

    public abstract void activate();
    public abstract void undo();

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean getIsActive() {
        return this.isActive;
    }

    public Rectangle getBody() {
        return this.body;
    }
}
