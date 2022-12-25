package Board;

/*

 */

import Ship.Ship;

import java.util.ArrayList;
import java.util.Random;

import static Utilities.Constants.BOARD_SIZE;

public class Board {
    private Spot[][] grid;
    private final int[] SHIP_SIZES = new int[]{5, 4, 3, 2};

    private ArrayList<Ship> ships = new ArrayList<>();
    private final int boardSize;

    public Board(){
        boardSize = BOARD_SIZE;
        grid = createGrid();
        placeShipsOnBoard();
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }

    public Spot getSpot(int x, int y){
        return grid[x][y];
    }

    // Move to utility function
    private Spot[][] createGrid(){
        Spot[][] grid = new Spot[boardSize][boardSize];
        // initialize spot objects
        for(int row = 0; row < BOARD_SIZE; row++){
            for(int col = 0; col < BOARD_SIZE; col++){
                grid[row][col] = new Spot(row, col);
            }
        }
        return grid;
    }

    private void placeShipsOnBoard(){
        for (int ship : SHIP_SIZES){
            placeShip(ship);
        }
    }

    private void placeShip(int shipSize){
        // while not valid
        boolean foundValidShipPlacement = false;
        int[] coordinate = new int[2];
        int direction = 0;

        // keep placing the ship
        while(!foundValidShipPlacement){
            Random r = new Random();
            // 0 = horizontal, 1 = vertical
            direction = r.nextInt(2);

            // create starting coordinate
            coordinate = new int[]{r.nextInt(BOARD_SIZE), r.nextInt(BOARD_SIZE)};
            // with ship size, do for loop and check if the rest of the coordinates are also valid
            foundValidShipPlacement = isValidShipPlacement(coordinate, shipSize, direction);
        }
        setShipOnBoard(shipSize, coordinate, direction);
        System.out.printf("Generated ship with size %d coordinate at: row=%d, col=%d, direction=%d%n", shipSize, coordinate[0], coordinate[1], direction);
    }

    private boolean isValidShipPlacement(int[]coordinate, int shipSize, int direction){
        int[] currentCoord;
        for (int i = 0; i < shipSize; i++){
            if (direction == 0) { // horizontal
                currentCoord = new int[]{coordinate[0], coordinate[1] + i};
            } else { //vertical
                currentCoord = new int[]{coordinate[0]+  i, coordinate[1]};
            }
            if (!validSpot(currentCoord) && !grid[coordinate[0]][coordinate[1]].isOccupied()){
                return false;
            }
        }
        System.out.printf("Found valid place with size %d coordinate at: row=%d, col=%d, direction=%d%n", shipSize, coordinate[0], coordinate[1], direction);
        return true;
    }

    private void setShipOnBoard(int shipSize, int[] coordinate, int direction){
        ArrayList<Spot> shipSpots = new ArrayList<>();
        for (int i = 0; i < shipSize; i++){
            Spot currentSpot;
            if (direction == 0) { // horizontal
                grid[coordinate[0]][coordinate[1] + i].setOccupied(true);
                currentSpot = grid[coordinate[0]][coordinate[1] + i];
            } else { //vertical
                grid[coordinate[0] + i][coordinate[1]].setOccupied(true);
                currentSpot = grid[coordinate[0] + i][coordinate[1]];
            }
            shipSpots.add(currentSpot);
        }

        Ship ship = new Ship(shipSpots);
        ships.add(ship);
    }

    /*
        input: int[] coordinates, [row, col], 0-based
        output: returns if the coordinate is a spot within the board
     */
    private boolean validSpot(int[] coordinate){
        int row = coordinate[0];
        int col = coordinate[1];
        return (row >= 0 && row < BOARD_SIZE
                && col >= 0 && col < BOARD_SIZE);
    }


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
