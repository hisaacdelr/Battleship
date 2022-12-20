package Player;

import Board.Board;
import Ship.Ship;

import java.util.ArrayList;

public abstract class Player {

    protected Board board;

    protected ArrayList<Ship> ships;

    public abstract void handleTurn();

    public abstract boolean isCpu();

    public boolean hasShips() {
        for (Ship ship : this.ships){
            if (!ship.isSunk()) {
                return true;
            }
        }
        return false;
    }
}
