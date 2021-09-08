package h;


import javafx.scene.shape.Rectangle;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
/**
 * Class that represents a room in teh dungeon, including the walls, exits, etc.
 * @author Olivia, Dan, Robert
 * @version 1.0
 */
public class Room {
    private ArrayList<Monster> enemyList;
    private boolean cleared;
    private Color backgroundColor;
    private Color foregroundColor;
    private int id;
    private String exits;
    private Rectangle rect;
    private Label playerInfo = new Label("");
    private Label challengeLabel = new Label("");
    private boolean isChallengeRoom = false;
    private boolean isMonsterPaused = false;
    private ArrayList<Potion> activePotions = new ArrayList<>(); 
    private boolean isPaused = false;
    private SpecialPrizePotion prize = new SpecialPrizePotion();
    /**
     * Method that constructs an instance of the room.
     * @param enemyList list of enemies, not currently in use
     * @param backgroundColor determines background color
     * @param foregroundColor determines foreground color
     * @param id represents the place of the room in the dungeon
     * @param exits determines the number of exits and where they are
     */
    public Room(ArrayList<Monster> enemyList, Color backgroundColor, Color foregroundColor,
                int id, String exits) {
        this.enemyList = enemyList;
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;
        this.id = id;
        this.cleared = false;
        this.exits = exits;
        this.rect = new Rectangle();
        this.isPaused = false;
        this.isMonsterPaused = false;
    }
    /**
     * Accessor for id variable.
     * @return returns id
     */
    public int getId() {
        return id;
    }
    /**
     * Accessor for cleared variable.
     * @return returns cleared
     */
    public boolean isCleared() {
        return cleared;
    }
    /**
     * Setter for cleared variable.
     * @param cleared new value of variable
     */
    public void setCleared(boolean cleared) {
        this.cleared = cleared;
    }
    /**
     * Accessor for enemy list variable.
     * @return returns enemyList
     */
    public ArrayList<Monster> getEnemyList() {
        return enemyList;
    }
    /**
     * Accessor for exits variable.
     * @return returns exits
     */
    public String getExits() {
        return exits;
    }

    /**
     * Mutator for exits variable.
     * @param exits String representing the exits.
     */
    public void setExits(String exits) {
        this.exits = exits;
    }
    /**
     * Method that helps move the player.
     * @param x amount to move in the x direction
     * @param y amount to move in the y direction
     */
    public void movePlayer(int x, int y) {
        rect.setTranslateX(x);
        rect.setTranslateY(y);
    }

    public Rectangle getRect() {
        return this.rect;
    }

    public void setIsPaused(boolean isPaused) {
        this.isPaused = isPaused;
    }

    public boolean getIsPaused() {
        return this.isPaused;
    }

    public void setIsMonsterPaused(boolean isMonsterPaused) {
        this.isMonsterPaused = isMonsterPaused;
    }

    public boolean getIsMonsterPaused() {
        return this.isMonsterPaused;
    }

    public boolean getIsChallengeRoom() {
        return this.isChallengeRoom;
    }

    public void showPrize() {
        prize.getBody().setVisible(true);
        activePotions.add(prize);
    }

    public void makeChallengeRoom() {
        for (int i = 0; i < 2; i++) {
            int choice = MainApp.rand.nextInt(3);
            if (choice == 0) {
                enemyList.add(new Walker(0, 0, 1, MainApp.difficulty * 2 + 6, Color.ORANGE));
            } else if (choice == 1) {
                enemyList.add(new Bull(0, 0, 1, MainApp.difficulty * 2 + 1, Color.LIGHTBLUE));
            } else {
                enemyList.add(new RandomWalker(0, 0, 1, MainApp.difficulty * 2, Color.PINK));
            }
        }

        for (Monster m : enemyList) {
            m.getBody().setVisible(false);
        }

        this.isChallengeRoom = true;
        this.cleared = true;
        this.isMonsterPaused = true;
        this.challengeLabel.setText("This is a challenge.\nPress K to start.");
    }

    public void launchAttack(int dir) {
        int choice = MainApp.weapon;
        if (choice == 1) {
            int i = 0;
            boolean test = true;
            while (test && (i < MainApp.player.getWeaponList().size())) {
                if (MainApp.player.getWeaponList().get(i).getCanAttack()) {
                    MainApp.player.getWeaponList().get(i).attack(dir);
                    test = false;
                }
                i++;
            }
        } else {
            boolean test = true;
            for (Weapon w : MainApp.player.getWeaponList()) {
                test = test && w.getCanAttack();
            }
            if (test) {
                MainApp.player.getWeaponList().get(dir - 1).attack(dir);
            }
        }
    }

    public ArrayList<Potion> getActivePotions() {
        return this.activePotions;
    }

    public void updateLabel() {
        playerInfo.setText("Health: " + MainApp.player.getHealth()
        + "\nHealth Potions: " + MainApp.player.getHealthPotions().size()
        + "\nAttack Potions: " + MainApp.player.getAttackPotions().size()
        + "\nFreeze Charges: " + MainApp.player.getFreezePotions().size());
    }

    /**
     * Method that creates the scene for the room.
     * @return returns the scene of the room
     */
    public Scene getScene() {
        StackPane mainPane = new StackPane();
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setMaxSize(700, 500);
        mainPane.setMinSize(700, 500);

        mainPane.setBackground(new Background(
                new BackgroundFill(backgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));

        Rectangle foreground = new Rectangle(650, 450);
        Rectangle verticalExits = new Rectangle(100, 500);
        Rectangle horizontalExits = new Rectangle(700, 100);

        foreground.setFill(foregroundColor);
        verticalExits.setFill(foregroundColor);
        horizontalExits.setFill(foregroundColor);

        mainPane.getChildren().addAll(foreground, verticalExits, horizontalExits);

        if (!exits.contains("1")) {
            Rectangle top = new Rectangle(0, 0, 700, 25);
            top.setTranslateY(-250 + (25 / 2.0));
            top.setFill(backgroundColor);
            mainPane.getChildren().add(top);
        }

        if (!exits.contains("3")) {
            Rectangle bottom = new Rectangle(0, 0, 700, 25);
            bottom.setTranslateY(250 - (25 / 2.0));
            bottom.setFill(backgroundColor);
            mainPane.getChildren().add(bottom);
        }

        if (!exits.contains("2")) {
            Rectangle right = new Rectangle(0, 0, 25, 500);
            right.setTranslateX(350 - (25 / 2.0));
            right.setFill(backgroundColor);
            mainPane.getChildren().add(right);
        }

        if (!exits.contains("4")) {
            Rectangle left = new Rectangle(0, 0, 25, 500);
            left.setTranslateX(-350 + (25 / 2.0));
            left.setFill(backgroundColor);
            mainPane.getChildren().add(left);
        }

        updateLabel();

        VBox vBox = new VBox();
        vBox.getChildren().addAll(playerInfo);
        if (this.isChallengeRoom) {
            vBox.getChildren().add(challengeLabel);
        }
        vBox.setTranslateX(550);
        vBox.setTranslateY(100);
        mainPane.getChildren().add(vBox);

        rect = MainApp.player.getCharacter();
        movePlayer(MainApp.player.getCurrentX(), MainApp.player.getCurrentY());
        mainPane.getChildren().add(rect);

        for (Weapon w: MainApp.player.getWeaponList()) {
            mainPane.getChildren().add(w.getBody());
            w.reset();
        }

        if (!this.isCleared() || this.isChallengeRoom) {
            for (Monster m : enemyList) {
                mainPane.getChildren().add(m.getBody());
                if (m.getHasPotion()) {
                    mainPane.getChildren().add(m.getPotion().getBody());
                }
            }
        }

        if (this.isChallengeRoom) {
            mainPane.getChildren().add(prize.getBody());
        }

        Scene scene = new Scene(mainPane, 700, 500);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.UP) {
                    MainApp.player.setNorth(true);
                }
                if (keyEvent.getCode() == KeyCode.DOWN) {
                    MainApp.player.setSouth(true);
                }
                if (keyEvent.getCode() == KeyCode.RIGHT) {
                    MainApp.player.setEast(true);
                }
                if (keyEvent.getCode() == KeyCode.LEFT) {
                    MainApp.player.setWest(true);
                }
                if (keyEvent.getCode() == KeyCode.A) {
                    launchAttack(4);
                }
                if (keyEvent.getCode() == KeyCode.S) {
                    launchAttack(3);
                }
                if (keyEvent.getCode() == KeyCode.D) {
                    launchAttack(2);
                }
                if (keyEvent.getCode() == KeyCode.W) {
                    launchAttack(1);
                }
                if (keyEvent.getCode() == KeyCode.Z) {
                    MainApp.player.useHealth();
                    updateLabel();
                }
                if (keyEvent.getCode() == KeyCode.X) {
                    MainApp.player.useAttack();
                    updateLabel();
                }
                if (keyEvent.getCode() == KeyCode.C) {
                    MainApp.player.useFreeze();
                    updateLabel();
                }
                if (keyEvent.getCode() == KeyCode.I) {
                    setIsPaused(true);
                    new Inventory();
                }
                if (keyEvent.getCode() == KeyCode.K) {
                    if (getIsMonsterPaused()) {
                        for (Monster m : enemyList) {
                            m.getBody().setVisible(true);
                        }
                        setCleared(false);
                        setIsMonsterPaused(false);
                    }
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.UP) {
                    MainApp.player.setNorth(false);
                }
                if (keyEvent.getCode() == KeyCode.DOWN) {
                    MainApp.player.setSouth(false);
                }
                if (keyEvent.getCode() == KeyCode.RIGHT) {
                    MainApp.player.setEast(false);
                }
                if (keyEvent.getCode() == KeyCode.LEFT) {
                    MainApp.player.setWest(false);
                }
            }
        });

        return scene;
    }
}