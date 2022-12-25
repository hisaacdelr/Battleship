package Game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Player.CpuPlayer;
import Player.HumanPlayer;
import Player.Player;
import Utilities.InputHelper;

import static Utilities.Constants.BOARD_SIZE;

/**
 * Contains logic related to conducting a game.
 */
public class Game {

    Scanner cli = new Scanner(System.in);
    InputHelper inputHelper = new InputHelper();

    private ArrayList<Player> playerList = new ArrayList<>();

    /**
     * Initializes the players and their dependencies participating in the game.
     */
    public void setupPlayers(){
        System.out.println("Setting up players in the game...");

        Player humanPlayer = new HumanPlayer();
        Player cpuPlayer = new CpuPlayer();


        playerList.add(humanPlayer);
        playerList.add(cpuPlayer);
    }

    /**
     * Runs a single game with 2 players until a winner is declared.
     */
    public void runGame(){
        System.out.println("Starting game!");
        int currentPlayer = 0;
        int otherPlayer = 1;

        while (isContinueGame()) {
            Player player = playerList.get(currentPlayer);
            Player enemy = playerList.get(otherPlayer);

            boolean isValidHit = false;
            int[] coordinate = new int[2];
            while (!isValidHit) {
                String playerInput = !player.isCpu() ? getCoordinateFromPrompt() : generateRandomCoordinate();
                coordinate = inputHelper.convertInputToCoordinate(playerInput);
                isValidHit = enemy.getBoard().isValidHit(coordinate);
            }

            enemy.getBoard().handleHit(coordinate);

            if (currentPlayer == 0) {
                currentPlayer = 1;
                otherPlayer = 0;
            } else {
                currentPlayer = 0;
                otherPlayer = 1;
            }

            System.out.println("Enemy board: ");
            enemy.getBoard().printBoard();
        }
        declareWinner();
    }

    /**
     * Checks whether a win-condition has been met.
     * A win-condition is met when one player's ships have all sunk.
     *
     * @return  True if game continues, false otherwise.
     */
    public boolean isContinueGame(){
        for (Player player : playerList) {
            if (!player.hasShips()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Prints out the name of the winner of the game.
     */
    private void declareWinner(){
        for (Player player : playerList) {
            if (player.hasShips()) {
                if (player.isCpu()) {
                    System.out.println("CPU has won :(");
                } else {
                    System.out.println("The player has won!!");
                }
            }
        }
        System.out.println("Thanks for playing!");
    }

    /**
     * Prompts the user on console to type a coordinate.
     * Returns the coordinate the user types in.
     *
     * @return      A String of user-legible coordinate
     */
    public String getCoordinateFromPrompt(){
        System.out.println("Please type a coordinate | rows: A-J | col: 1-10 ");
        System.out.print("Ex. A4, J10: ");
        String coordinate = cli.next();

        System.out.printf("\nUser selected coordinate %s\n", coordinate);
        return coordinate;
    }

    /**
     * Generates a random user-legible coordinate on the board
     *
     * @return      A String of a user-legible coordinate
     */
    public String generateRandomCoordinate(){
        Random r = new Random();
        char row = (char) (r.nextInt(BOARD_SIZE) + 'A');
        int col = r.nextInt(BOARD_SIZE) + 1;
        System.out.printf("\nCPU selected coordinate %s%d%n", row, col);
        return String.format("%c%d", row, col);
    }
}
