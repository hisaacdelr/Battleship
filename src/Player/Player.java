package Player;

import Board.Board;

public abstract class Player {

    protected Board board;

    public abstract void handleTurn();

    public abstract boolean isCpu();
}
