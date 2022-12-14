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
