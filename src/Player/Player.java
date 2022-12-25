package Player;

import Board.Board;
import Board.Spot;
import Ship.Ship;

public abstract class Player {

    protected Board board;

    public Board getBoard() {
        return board;
    }

    // TODO : redudndant with Board's validSpot method maybe? refactor
    public boolean isValidHit(int[] coordinate, Player enemy){
        try {
            return !enemy.board.getSpot(coordinate[0], coordinate[1]).isHit();
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public void handleHit(int[] coordinate, Player enemy) {
        Board enemyBoard = enemy.board;
        Spot target = enemyBoard.getSpot(coordinate[0], coordinate[1]);

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
