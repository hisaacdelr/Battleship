package Game;

import java.util.ArrayList;
import Player.CpuPlayer;
import Player.HumanPlayer;
import Player.Player;


public class Game {

    private ArrayList<Player> playerList = new ArrayList<>();
    public void setup(){
        System.out.println("Setting up players in the game...");
        Player humanPlayer = new HumanPlayer();
        Player cpuPlayer = new CpuPlayer();


        playerList.add(humanPlayer);
        playerList.add(cpuPlayer);
    }

    public void startGame(){
        System.out.println("Starting game!");

        for(Player player : playerList){
            player.handleTurn();
        }

    }
}
