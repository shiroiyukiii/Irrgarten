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
    private boolean winner;
    private String log;
    
    
    GameState(String _labyrinth, String _players, String _monsters, 
                int _currentPlayer, boolean _winner, String _log) {
        labyrinth = _labyrinth;
        players = _players;
        monsters = _monsters;
        currentPlayer = _currentPlayer;
        winner = _winner;
        log = _log;
    }
    
    public String getLabyrinth(){
        return labyrinth;
    }
    
    public String getPlayers(){
        return players;
    }
    
    public String getMonsters(){
        return monsters;
    }
    
    public int getCurrentPLayer(){
        return currentPlayer;
    }
    
    public boolean getWinner(){
        return winner;
    }
    
    public String getLog(){
        return log;
    }
}
