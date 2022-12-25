package Game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Board.Board;
import Player.CpuPlayer;
import Player.HumanPlayer;
import Player.Player;


public class Game {

    Scanner cli = new Scanner(System.in);

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

        while (isContinueGame()){
            Player player = playerList.get(currentPlayer);
            String coordinate = !player.isCpu() ? displaySelectCoordinatePrompt() : generateRandomCoordinate();
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
        System.out.print("Ex. A4, J10");
        String coordinate = cli.next();
        // TODO: Check the coordinates, convert to ints for grid
        System.out.printf("User selected coordinate %s", coordinate);

        return coordinate;
    }

    public String generateRandomCoordinate(){
        Random r = new Random();
        // A = 65, a = 97, 32
        char row = (char) (r.nextInt(26) + 'A');
        int col = r.nextInt(10) + 1;
        System.out.printf("CPU selected coordinate %s%d%n", row, col);
        return String.format("%c%d", row, col);
    }
}
