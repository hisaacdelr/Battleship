package Ship;

import Board.Spot;

import java.util.List;

public class Ship {
    private List<Spot> spots;

    public boolean isSunk() {
        for (Spot spot : spots) {
            if (!spot.isHit()) {
                return false;
            }
        }
        return true;
    }

    public Ship(List<Spot> spots) {
        this.spots = spots;
    }
}
