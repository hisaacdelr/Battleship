package Utilities;

import static Utilities.Constants.BOARD_SIZE;

public class InputHelper {

    public int[] convertInputToCoordinate(String coordinate){
        try{
            coordinate = coordinate.strip();

            if (isValidCoordinateFormat(coordinate)) {
                // check if valid letter A-J (or upper bound)
                char rowLetter = coordinate.substring(0, 1).toUpperCase().charAt(0);
                if (rowLetter < 'A' || rowLetter > 'A' + 26){
                    System.out.printf("Letter is not A-%c", getUpperBoundRowLetter());
                }
                // subtract 65 (int of capital A)
                int rowInt = rowLetter - 'A';

                // check if valid number 1-10 (or upper bound)
                String columnNumberString = coordinate.substring(1);
                int columnNumber = Integer.parseInt(columnNumberString) - 1;
                if (columnNumber < 0 || columnNumber > BOARD_SIZE - 1){
                    System.out.printf("Column is not 1-%d", BOARD_SIZE);
                }

                return new int[]{rowInt, columnNumber};
            } else {
                System.out.println("Must type coordinate in format such as: a10, B2, c1");
            }
        } catch (Exception e) {
            System.out.printf("Given an invalid format coodinate: %s", coordinate);
        }
        return null;
    }

    private boolean isValidCoordinateFormat(String str) {
        return str.matches("\\D([1-9]|10){1}");
    }
    private char getUpperBoundRowLetter(){
        return (char) ('A' + BOARD_SIZE);
    }
}
