package sample;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Collections;

public class Main extends Application {

    //TODO
    // Remove dominos array and make boneyard main arraylist
    // Write getters for domino top and bottom variables

    /** MAX: The largest number that the dominos go to
     *  PLAYERS: Number of PLAYERS playing NOT including the computer
     *  FIRSTDRAW: The number of dominos each player picks before the game starts
     *  TOTAL: The TOTAL number of dominos used in the game. The formula for arithmetic sum used
     *  dominos: All the possible dominos as Domino objects in an array
     */
    private final int MAX = 6;
    private final int PLAYERS = 2;
    protected final int FIRSTDRAW = 7;
    protected final int TOTAL = ((MAX +1)*(MAX +2))/2;
    private ArrayList<Player> playerArr;
    private ArrayList<Domino> boneyard = new ArrayList<>();
    private Player computer;
    private Domino[] dominos = new Domino[TOTAL];
    private Domino[][] board = new Domino[TOTAL *2][2];

    /**
     * First method to execute when the program runs
     * @param args
     */
    public static void main(String[] args) {
        Main m = new Main();
        m.createBoard();
        m.setValues(m.MAX);
        m.createPlayers();
        m.createComplements();
        m.createBoneyard();
        m.loadImages();

        launch(args);
    }


    private void createBoneyard(){
        for(Domino domino : dominos){
            for(Player player : playerArr){
                for(Domino playerDomino : player.getDominoArray()){
                    if(!(domino == playerDomino)){
                        boneyard.add(domino);
                    }
                }
            }
        }
    }

    public void loadImages(){

//        d00 = new Image(getClass().getResourceAsStream("00.png"));
    }

    /**
     * Creates the game board that is filled with Domino objects
     */
    private void createBoard(){
        for (int row = 0; row < TOTAL *2; row++) {
            for (int col = 0; col < 2; col++) {
                board[row][col] = null;
            }
        }
    }

    /**
     * Create Domino objects for all possible dominos used in the game and
     * store them in the global dominos array
     * @param max
     */
    private void setValues(int max){
        int index = 0;

        for (int i = 0; i <= max; i++) {
            for (int j = 0; j < i+1; j++) {
                dominos[index] = new Domino(j, i);
                index++;
            }
        }
    }

    /**
     * Creates a domino with the reverse order of numbers for the computer's pile
     * in order to account for rotations of the dominos while playing
     */
    private void createComplements(){
        computer = new Player(playerArr.get(0).getDominoArray());
        playerArr.remove(0);
        ArrayList<Domino> dominoArray = computer.getDominoArray();
        ArrayList<Domino> newDominoArray = new ArrayList(dominoArray);
        for(Domino d: dominoArray){
            newDominoArray.add(new Domino(d.bottom, d.top));
        }
        computer = new Player(newDominoArray);

    }

    /**
     * Create the necessary amount of Player objects with their starting hand
     */
    private void createPlayers(){
        ArrayList<Domino> playerDominos = new ArrayList<>();
        playerArr = new ArrayList<>();
        ArrayList<Integer> randomNums = getRandomIndices();

        for (int i = 0; i <= FIRSTDRAW * PLAYERS; i++) {
            if ((i % FIRSTDRAW == 0) && (i != 0)) {
                playerArr.add(new Player(new ArrayList(playerDominos)));
                playerDominos.clear();
            }
            playerDominos.add(dominos[randomNums.get(i)]);
        }
        System.out.println("Computer");
        playerArr.get(0).printPlayer();
        System.out.println("Player");
        playerArr.get(1).printPlayer();

    }

    /**
     * Get random indices to use for the starting hand of the PLAYERS
     * @return the array of random numbers
     */
    private ArrayList<Integer> getRandomIndices(){
        ArrayList<Integer> indices = new ArrayList<>();
        for (int i = 0; i < TOTAL; i++) {
            indices.add(i);
        }
        Collections.shuffle(indices);

        return indices;
    }


    @Override
    public void start(Stage stage) throws Exception {
        ResizableCanvas canvas1 = new ResizableCanvas("Board");
        ResizableCanvas canvas2 = new ResizableCanvas("PlayerInfo");

        SplitPane split = new SplitPane();
        split.setOrientation(Orientation.HORIZONTAL);

//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });

        split.getItems().addAll(canvas1,canvas2);
        split.setDividerPositions(0.6f, 0.9f);
        stage.setScene(new Scene(split, 600,1000));
        stage.show();
    }
}
