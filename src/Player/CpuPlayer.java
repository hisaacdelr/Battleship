package Player;

import Board.Board;

import java.util.ArrayList;

public class CpuPlayer extends Player {

    public CpuPlayer(){
        this.board = new Board();
        this.ships = new ArrayList<>(); // TODO: extract ships out of Board set up function
    }

    public boolean isCpu(){
        return true;
    }
}
