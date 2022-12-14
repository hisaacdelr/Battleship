package Player;

import Board.Board;
import Ship.Ship;

import static Utilities.Constants.DEFAULT_SHIP_COUNT;

/*
    Split this out
 */

public class Player {

    private Board board = new Board();
    private Ship[] ships;

    public Player() {
        ships = setupShips();
    }

    private Ship[] setupShips(){
        Ship[] ships = new Ship[DEFAULT_SHIP_COUNT];
        for (int i = 0; i < DEFAULT_SHIP_COUNT; i++){
            ships[i] = new Ship();
        }
        return ships;
    }
}
