package h;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import javafx.scene.paint.Color;

/**
 * Class that sets up the maze of rooms and handles the number of exits.
 * @author Olivia, Dan
 * @version 1.0
 */
public class InitialGameScreen {
    /**
     * Method for creating the dungeon and initializing the player.
     */
    public InitialGameScreen() {
        Room[][] maze = new Room[5][5];
        Random rand = MainApp.rand;

        //fix player
        MainApp.player.setWeaponList();
        MainApp.player.getWeaponInventory().add(MainApp.player.getWeaponList().get(0).getDamage());

        //start room
        maze[1][1] = new Room(new ArrayList<Monster>(), Color.GREY, Color.GREEN, 11, "");
        maze[1][1].setCleared(true);
        //end room
        maze[4][4] = new Room(new ArrayList<Monster>(), Color.GREY, Color.GREEN, 44, "");
        maze[4][4].getEnemyList().add(new FinalBoss());

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == null) {
                    String exits = "";
                    if (!((i == 1 && j == 1) || (i == 4 && j ==4))) {
                        maze[i][j] = new Room(generateMonsters(3), Color.GREY, Color.GREEN, 10 * i + j, exits);
                    }
                }
            }
        }

        //Create Random Maze
        LinkedList<Room> cellStack = new LinkedList<>();
        int totalCells = 25;
        int firstVal = rand.nextInt(5);
        int secondVal = rand.nextInt(5);
        Room currentCell = maze[firstVal][secondVal];
        int visitedCells = 1;

        while(visitedCells < totalCells) {
            LinkedList<Room> intact = new LinkedList<>();
            if (firstVal > 0) {
                if(maze[firstVal - 1][secondVal].getExits().equals("")) {
                    intact.addLast(maze[firstVal - 1][secondVal]);
                }
            }
            if (firstVal < 4) {
                if(maze[firstVal + 1][secondVal].getExits().equals("")) {
                    intact.addLast(maze[firstVal + 1][secondVal]);
                }
            }
            if (secondVal > 0) {
                if(maze[firstVal][secondVal - 1].getExits().equals("")) {
                    intact.addLast(maze[firstVal][secondVal - 1]);
                }
            }
            if (secondVal < 4) {
                if(maze[firstVal][secondVal + 1].getExits().equals("")) {
                    intact.addLast(maze[firstVal][secondVal + 1]);
                }
            }

            if (intact.size() > 0) {
                Room chosenRoom = intact.get(rand.nextInt(intact.size()));
                int difference = chosenRoom.getId() - currentCell.getId();
                System.out.println(currentCell.getId() + ", " + chosenRoom.getId() + ", " + difference);
                if (difference == -10) {
                    currentCell.setExits(currentCell.getExits() + ",1");
                    chosenRoom.setExits(chosenRoom.getExits() + ",3");
                }
                if (difference == 10) {
                    currentCell.setExits(currentCell.getExits() + ",3");
                    chosenRoom.setExits(chosenRoom.getExits() + ",1");
                }
                if (difference == -1) {
                    currentCell.setExits(currentCell.getExits() + ",4");
                    chosenRoom.setExits(chosenRoom.getExits() + ",2");
                }
                if (difference == 1) {
                    currentCell.setExits(currentCell.getExits() + ",2");
                    chosenRoom.setExits(chosenRoom.getExits() + ",4");
                }
                cellStack.addLast(currentCell);
                currentCell = chosenRoom;
                firstVal = currentCell.getId() / 10;
                secondVal = currentCell.getId() % 10;
                visitedCells++;
            } else {
                if (cellStack.size() > 0) {
                    currentCell = cellStack.removeLast();
                    firstVal = currentCell.getId() / 10;
                    secondVal = currentCell.getId() % 10;
                }
            }
        }
        maze[1][1].setExits("1,2,3,4");
        maze[0][1].setExits(maze[0][1].getExits() + ",3");
        maze[2][1].setExits(maze[2][1].getExits() + ",1");
        maze[1][0].setExits(maze[1][0].getExits() + ",2");
        maze[1][2].setExits(maze[1][2].getExits() + ",4");

        maze[1][2].makeChallengeRoom();
        maze[2][1].makeChallengeRoom();


        MainApp.dungeon = maze;
        MainApp.player.setMoney(20 - 5 * (MainApp.difficulty));
        MainApp.theTimer.start();
        MainApp.currStage.setTitle("Start Room");
        MainApp.currStage.setScene(MainApp.dungeon[1][1].getScene());
    }

    public ArrayList<Monster> generateMonsters(int num) {
        ArrayList<Monster> monsters = new ArrayList<>();
        Random rand = MainApp.rand;

        for (int i = 0; i < num; i++) {
            int choice = rand.nextInt(3);
            if (choice == 0) {
                monsters.add(new Walker(0, 0, 1, MainApp.difficulty * 2 + 6, Color.ORANGE));
            } else if (choice == 1) {
                monsters.add(new Bull(0, 0, 1, MainApp.difficulty * 2 + 1, Color.LIGHTBLUE));
            } else {
                monsters.add(new RandomWalker(0, 0, 1, MainApp.difficulty * 2, Color.PINK));
            }
        }

        return monsters;
    }
}