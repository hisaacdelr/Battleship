package Player;

import Board.Board;
import Board.Spot;
import Ship.Ship;

import java.util.ArrayList;

public abstract class Player {

    protected Board board;

    protected ArrayList<Ship> ships;

    public void handleHit(String coordinate, Player player) {
        Board enemyBoard = player.board;
        Spot target = enemyBoard.getSpot(0, 0); //TODO: remove hardcoded values .getSpot

        target.setHit(true);
    };

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
