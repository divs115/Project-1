package sample;

import java.util.ArrayList;

/**
 * Created by Divya on 8/22/2017.
 */


public class Player {
    private ArrayList<Domino> dominos = new ArrayList<>();

    public Player(ArrayList<Domino> dominos) {
        this.dominos = dominos;
    }

    public ArrayList<Domino> getDominoArray(){
        return dominos;
    }

    public void printPlayer(){
        for (Domino d: dominos) {
            d.printDomino();
        }
        System.out.println();
    }

}
