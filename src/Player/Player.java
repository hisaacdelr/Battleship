package Player;

import Board.Board;
import Board.Spot;
import Ship.Ship;

public abstract class Player {

    protected Board board;

    public Board getBoard() {
        return board;
    }

    public boolean isValidHit(int[] coordinate, Player enemy){
        try {
            Spot spot = enemy.board.getSpot(coordinate[0], coordinate[1]);
            if (spot.isHit()){
                System.out.println("Coordinate was already selected. Please choose another coordinate.");
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
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
