package Board;

/*

 */

import static Utilities.Constants.BOARD_SIZE;

public class Board {
    private Spot[][] grid;
    private final int boardSize;

    public Board(){
        boardSize = BOARD_SIZE;
        grid = new Spot[boardSize][boardSize]; // [row][col]

        setup();
    }

    // Move to utility function
    private void setup(){
        // initialize spot objects
        for(int row = 0; row < BOARD_SIZE; row++){
            for(int col = 0; col < BOARD_SIZE; col++){
                grid[row][col] = new Spot(row, col);
            }
        }

        int[] shipSizes = new int[]{2, 3, 4, 5};

//      TEST: initialize size 2 ship at 0,0 and 0,1
        // placeShipsOnBoard()
        grid[0][0].setOccupied(true);
        grid[0][1].setOccupied(true);

    }

    /*

     */
    private int convertLetterToIndex(char letter){
        // a = 97 | ASCII
        int A_ASCII = 97;
        if ((int) letter < A_ASCII || (int) letter > A_ASCII + boardSize) {
            throw new IllegalArgumentException("Row provided is not a valid value. " +
                    "Please enter a letter from A to J (case insensitive)");
        }
        return (int) letter - A_ASCII;


    }
}
