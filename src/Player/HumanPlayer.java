package Player;

import Board.Board;

public class HumanPlayer extends Player {

    public HumanPlayer(){
        this.board = new Board();
    }

    public void handleTurn(){
        System.out.println("Human Player shoots using coordinate!");
    }

    public boolean isCpu(){
        return false;
    }
}
