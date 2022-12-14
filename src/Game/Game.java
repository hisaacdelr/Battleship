package Game;


import Player.CpuPlayer;
import Player.HumanPlayer;
import Player.Player;


public class Game {
    public void startGame(){
        System.out.println("Inside game object");
        Player humanPlayer = new HumanPlayer();
        Player cpuPlayer = new CpuPlayer();
    }
}
