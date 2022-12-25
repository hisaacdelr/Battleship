package Player;

import Board.Board;
import Ship.Ship;

public abstract class Player {

    protected Board board;

    public Board getBoard() {
        return board;
    }

    public abstract boolean isCpu();

    public boolean hasShips() {
        for (Ship ship : this.board.getShips()){
            if (!ship.isSunk()) {
                return true;
            }
        }
        return false;
    }
}
