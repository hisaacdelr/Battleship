package Player;

import Board.Board;
import Board.Spot;
import Ship.Ship;

public abstract class Player {

    protected Board board;

    public void handleHit(int[] coordinate, Player player) {
        Board enemyBoard = player.board;
        Spot target = enemyBoard.getSpot(coordinate[0], coordinate[1]); //TODO: remove hardcoded values .getSpot

        target.setHit(true);
    };

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
