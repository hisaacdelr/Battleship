package Player;

import Board.Board;

/*
    Split this out to:
        - abstract
        - humanPlayer
        - CPU
 */

public abstract class Player {

    protected Board board;
    public boolean isCpu;

    public abstract void handleTurn();

    public abstract boolean isCpu();
}
