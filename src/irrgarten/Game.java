/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

import java.util.ArrayList;

public class Game {
    private static final int MAX_ROUNDS = 10;
    
    private ArrayList<Player> players;
    private ArrayList<Monster> monsters;
    
    private Player currentPlayer;
    private Labyrinth labyrinth;
    
    private int currentPlayerIndex;
    private String log;
    
    public Game(int nPlayers) {
        players= new ArrayList<Player>();
        monsters = new ArrayList<Monster>();
        
        log = "";
        
        for (int i = 0; i<nPlayers; i++){
            Player p = new Player((char)(i + '0'), Dice.randomIntelligence(), 
                                    Dice.randomStrength());
            players.add(p);
        }
        
        currentPlayerIndex = Dice.whoStarts(nPlayers);
        currentPLayer = players.get(currentPlayerIndex);
        
        labyrinth = new Labyrinth(10, 10, 9, 9);
        
        configureLabyrinth();
        
        labyrinth.spreadPlayers(players);
    }
    
    public boolean finished(){
        return labyrinth.haveAWinner();
    }
    
    public GameState getGameState(){
        String stateLabyrinth = labyrinth.toString();
        String statePlayers = "";
        for (Player p : players){
            statePlayers += p.toString();
        }
        String stateMonsters = "";
        for (Monster m : monsters){
            stateMonsters += m.toString();
        }
        GameState gs = new GameState(stateLabyrinth, statePlayers, 
                                    stateMonsters, currentPlayerIndex, 
                                    finished(), log);
        return gs;
    }
    
    private void configureLabyrinth(){
        labyrinth.addBlock(Orientation.VERTICAL, 10, 9, 9);
        
        Monster topologia = new Monster("Topologia", 10.0f, 10.0f);
        monsters.add(topologia);
        labyrinth.addMonster(5, 5, topologia);
    }
    
    private void nextPLayer(){
        currentPLayer = players.get(currentPlayerIndex++);
        currentPlayerIndex++;
    }
    
    private Directions actualDirection(Direction preferredDirection){
        int currentRow = currentPlayer.getRow();
        int currentCol = currentPlayer.getCol();
        
        Directions[] validMoves = labyrinth.validMoves(currentRow, currentCol);
        
        
    }
    
    private void logPlayerWon(){
        log += "El jugador ha ganado el combate.\n";
    }
    
    private void logMonsterWon(){
        log += "El monstruo ha ganado el combate.\n";
    }
    
    private void logResurrected(){
        log += "El jugador ha resucitado.\n";
    }
    
    private void logPlayerSkipTurn(){
        log += "El jugador ha perdido el turno por estar muerto.\n";
    }
    
    private void logPlayerNoOrders(){
        log += "No se ha podido ejecutar la orden del jugador.\n";
    }
    
    private void logNoMonster(){
        log += "El jugador se ha movido a una celda vacia o no "
                + "le ha sido posible moverse.\n";
    }
    
    private void logRounds(){
        log += "Se han producido el maximo de rondas de combate de "
                    + MAX_ROUNDS;
                }
}
