package Player;

import Board.Board;

public class CpuPlayer extends Player {

    public CpuPlayer(){
        this.board = new Board();
    }


    public void handleTurn(){
        System.out.println("CPU Player shoots, this is automated.");
    }

    public boolean isCpu(){
        return true;
    }
}
