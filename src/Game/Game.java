package Game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

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

        while (isContinueGame()){
            for(Player player : playerList){
                String result = !player.isCpu() ? displaySelectCoordinatePrompt() : generateRandomCoordinate();
                player.handleTurn();
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
    }

    /*
        User Prompt methods, move to print utility class
     */

    public String displaySelectCoordinatePrompt(){
        System.out.print("Select a row (A-J): ");
        String row = cli.next();
        System.out.print("Select a column (1-10): ");
        int col = cli.nextInt();
        System.out.printf("User selected coordinate %s%d%n", row, col);

        return String.format("%s%d", row, col);
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
