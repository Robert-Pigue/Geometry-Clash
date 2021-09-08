package h;

import java.util.Random;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Class that handles global data and starts the welcome screen.
 * @author Olivia, Dan, Robert
 * @version 1.0
 */
public class MainApp extends Application {

    public static String name;
    public static int difficulty = 1;
    public static int weapon = 1;
    public static Stage currStage;
    public static Room[][] dungeon;
    public static int currentId = 11;
    public static Player player = new Player();
    public static MyTimer theTimer = new MyTimer();
    public static Random rand = new Random();

    /**
     * Method that creates the stage and calls the welcome screen.
     * @param stage used by javafx to create the window
     */
    @Override
    public void start(Stage stage) throws Exception {
        currStage = stage;
        new WelcomeScreen();
    }

    public static void reset() {
        currentId = 11;
        player = new Player();
        theTimer = new MyTimer();
    }
    /**
     * Method that prints out data and shuts down the game.
     */
    @Override
    public void stop() throws Exception {
        System.out.println(name + "\n" + difficulty + "\n" + weapon);
    }
}