package Player;

import Board.Board;

public class CpuPlayer extends Player {

    public CpuPlayer() {
        this.board = new Board();
    }

    public boolean isCpu() {
        return true;
    }
}
