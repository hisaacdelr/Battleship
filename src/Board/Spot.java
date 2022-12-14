package Board;/*

 */

public class Spot {
    private final int row;
    private final int column;
    private boolean occupied = false;
    private boolean selected = false;

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

    public boolean isSelected(){
        return selected;
    }

    public void setSelected(boolean state){
        selected = state;
    }
}
