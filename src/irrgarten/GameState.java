/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author usuario
 */
public class GameState {
    private String labyrinth;
    private String players;
    private String monsters;
    private int currentPlayer;
    private int winner;
    private String log;
    
    
    GameState(String _labyrinth, String _players, String _monsters, 
                int _currentPlayer, int _winner, String _log) {
        this.labyrinth = _labyrinth;
        this.players = _players;
        this.monsters = _monsters;
        this.currentPlayer = _currentPlayer;
        this.winner = _winner;
        this.log = _log;
    }
    
    public String getLabyrinth(){
        return this.labyrinth;
    }
    
    public String getPlayers(){
        return this.players;
    }
    
    public String getMonsters(){
        return this.monsters;
    }
    
    public int getCurrentPLayer(){
        return this.currentPlayer;
    }
    
    public int getWinner(){
        return this.winner;
    }
    
    public String getLog(){
        return this.log;
    }
}
