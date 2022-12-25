package Board;

import Ship.Ship;

import java.util.ArrayList;
import java.util.Random;

import static Utilities.Constants.BOARD_SIZE;
import static Utilities.Constants.HORIZONTAL;

/*
    Contains logic related to game grid and turn-logic re
 */
public class Board {
    private Spot[][] grid;
    private final int[] SHIP_SIZES = new int[]{5, 4, 3, 2};
    private ArrayList<Ship> ships = new ArrayList<>();
    private final int boardSize;

    public Board(){
        boardSize = BOARD_SIZE;
        grid = createGrid();

        for (int ship : SHIP_SIZES){
            randomlyPlaceShip(ship);
        }
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }

    /**
     * Gets the Spot object at the given grid indices
     *
     * @param  row  0-based int for the row
     * @param  column  0-based int for the column
     */
    public Spot getSpot(int row, int column){
        return grid[row][column];
    }

    public void printBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                Spot spot = getSpot(i, j);

                if (spot.isHit() && spot.isOccupied()) {
                    System.out.print("🟥");
                } else if (spot.isOccupied()) {
                    System.out.print("⚓️");
                } else if (spot.isHit()) {
                    System.out.print("⬜️");
                } else {
                    System.out.print("🟦");
                }
            }
            System.out.print("\n");
        }
    }

    /**
     * Checks if a Spot at the given coordinate can be hit on this board.
     * A Spot cannot be hit more than once.
     *
     * @param  coordinate  array that contains 0-based indices of the row and column
     * @return      true if a spot can be hit, false otherwise
     */
    public boolean isValidHit(int[] coordinate){
        try {
            Spot spot = getSpot(coordinate[0], coordinate[1]);
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

    /**
     * Marks a spot in this board as hit.
     *
     * @param  coordinate  array that contains 0-based indices of the row and column
     */
    public void handleHit(int[] coordinate) {
        Spot target = getSpot(coordinate[0], coordinate[1]);

        target.setHit(true);
    }

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

    /**
     * Initializes one Ship on the board at a random position
     *
     * @param  shipSize  number of spots a ship occupies
     */
    private void randomlyPlaceShip(int shipSize){
        // while not valid
        boolean foundValidShipPlacement = false;
        int[] coordinate = new int[2];
        int direction = 0;

        // keep placing the ship
        while(!foundValidShipPlacement){
            Random r = new Random();
            direction = r.nextInt(2);
            coordinate = new int[]{r.nextInt(BOARD_SIZE), r.nextInt(BOARD_SIZE)};
            foundValidShipPlacement = isValidShipPlacement(coordinate, direction, shipSize);
        }
        setShipOnBoard(coordinate, direction, shipSize);
        System.out.printf("Generated ship with size %d coordinate at: row=%d, col=%d, direction=%d%n", shipSize, coordinate[0], coordinate[1], direction);
    }

    /**
     * Checks if a Ship can be placed in Spots on the Board
     * A Ship can only be placed within the board and
     * not occupy the same spot another Ship is occupying.
     *
     * @param  coordinate  array that contains 0-based indices of the row and column
     * @param  direction  if the ship is horizontal or vertical
     * @param  shipSize  number of spots a ship occupies
     * @return      true if ship can be placed, false otherwise
     */
    private boolean isValidShipPlacement(int[]coordinate, int direction, int shipSize){
        int[] currentCoord;
        try {
            for (int i = 0; i < shipSize; i++) {
                if (direction == HORIZONTAL) { // horizontal
                    currentCoord = new int[]{coordinate[0], coordinate[1] + i};
                } else { //vertical
                    currentCoord = new int[]{coordinate[0] + i, coordinate[1]};
                }
                if (!validSpot(currentCoord) && !grid[currentCoord[0]][currentCoord[1]].isOccupied()) {
                    return false;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
        System.out.printf("Found valid place with size %d coordinate at: row=%d, col=%d, direction=%d%n", shipSize, coordinate[0], coordinate[1], direction);
        return true;
    }

    /**
     * Creates the Spot objects a Ship will be placed on. Then it
     * initializes a Ship object onto the player's Board in the spots' locations.
     *
     * @param  shipSize  number of spots a ship occupies
     * @param  coordinate  array that contains 0-based indices of the row and column
     * @param  direction  if the ship will be placed horizontally or vertically
     */
    private void setShipOnBoard(int[] coordinate, int direction, int shipSize){
        ArrayList<Spot> shipSpots = new ArrayList<>();
        for (int i = 0; i < shipSize; i++){
            Spot currentSpot;
            if (direction == HORIZONTAL) {
                grid[coordinate[0]][coordinate[1] + i].setOccupied(true);
                currentSpot = grid[coordinate[0]][coordinate[1] + i];
            } else {
                grid[coordinate[0] + i][coordinate[1]].setOccupied(true);
                currentSpot = grid[coordinate[0] + i][coordinate[1]];
            }
            shipSpots.add(currentSpot);
        }

        Ship ship = new Ship(shipSpots);
        ships.add(ship);
    }


    /**
     * Returns if the coordinate is within the bounds of the board.
     *
     * @param  coordinate  array that contains 0-based indices of the row and column
     * @return      true if coordinate is within the board, false otherwise
     */
    private boolean validSpot(int[] coordinate){
        int row = coordinate[0];
        int col = coordinate[1];
        return (row >= 0 && row < BOARD_SIZE
                && col >= 0 && col < BOARD_SIZE);
    }
}
