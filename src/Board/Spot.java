package Board;/*

 */

public class Spot {
    private final int row;
    private final int column;
    private boolean occupied = false;
    private boolean hit = false;

    public Spot(int row, int column){
        this.row = row;
        this.column = column;
    }

    public boolean isOccupied(){
        return occupied;
    }

    public void setOccupied(boolean state){
        occupied = state;
    }

    public boolean isHit(){
        return hit;
    }

    public void setHit(boolean state) {
        if (occupied) {
            hit = state;
        }
    }
}
