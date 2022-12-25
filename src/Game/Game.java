package Game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Board.Board;
import Player.CpuPlayer;
import Player.HumanPlayer;
import Player.Player;
import Utilities.InputHelper;

import static Utilities.Constants.BOARD_SIZE;


public class Game {

    Scanner cli = new Scanner(System.in);
    InputHelper inputHelper = new InputHelper();

    private ArrayList<Player> playerList = new ArrayList<>();
    public void setup(){
        System.out.println("Setting up players in the game...");

        Player humanPlayer = new HumanPlayer();
        Player cpuPlayer = new CpuPlayer();


        playerList.add(humanPlayer);
        playerList.add(cpuPlayer);
    }

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
                String playerInput = !player.isCpu() ? displaySelectCoordinatePrompt() : generateRandomCoordinate();
                coordinate = inputHelper.convertInputToCoordinate(playerInput);
                isValidHit = player.isValidHit(coordinate, enemy);
            }

            player.handleHit(coordinate, playerList.get(otherPlayer));

            if (currentPlayer == 0) {
                currentPlayer = 1;
                otherPlayer = 0;
            } else {
                currentPlayer = 0;
                otherPlayer = 1;
            }

        }

        declareWinner();
    }

    public boolean isContinueGame(){
        for (Player player : playerList) {
            if (!player.hasShips()) {
                return false;
            }
        }
        return true;
    }

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
        System.out.println("It's a tie!");
    }

    /*
        User Prompt methods, move to print utility class
     */

    public String displaySelectCoordinatePrompt(){
        System.out.println("Please type a coordinate | rows: A-J | col: 1-10 ");
        System.out.print("Ex. A4, J10: ");
        String coordinate = cli.next();

        System.out.printf("User selected coordinate %s", coordinate);
        return coordinate;
    }

    public String generateRandomCoordinate(){
        Random r = new Random();
        // A = 65, a = 97, 32
        // 26 letters in alphabet
        char row = (char) (r.nextInt(26) + 'A');
        int col = r.nextInt(BOARD_SIZE) + 1;
        System.out.printf("CPU selected coordinate %s%d%n", row, col);
        return String.format("%c%d", row, col);
    }
}
