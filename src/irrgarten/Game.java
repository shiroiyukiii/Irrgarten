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
        currentPlayer = players.get(currentPlayerIndex);
        
        labyrinth = new Labyrinth(10, 10, 9, 9);
        
        configureLabyrinth();
        
        labyrinth.spreadPlayers(players);
    }
    
    public boolean finished(){
        return labyrinth.haveAWinner();
    }
    
    public boolean nextStep(Directions preferredDirection){
        String log = "";
        
        boolean dead = currentPlayer.dead();
        
        if(!dead){
            Directions direction = actualDirection(preferredDirection);
            
            if(direction != preferredDirection){
                logPlayerNoOrders();
            }
            
            Monster monster = labyrinth.putPlayer(direction, currentPlayer);
            
            if(monster == null){
                logNoMonster();
            }
            
            else{
                GameCharacter winner = combat(monster);
                manageReward(winner);
            }
        }
        
        else {
            manageResurrection();
        }
        
        boolean endGame = finished();
        
        if(!endGame){
            nextPlayer();
        }
        
        return endGame;
    }
    
    public GameState getGameState(){
        String stateLabyrinth = labyrinth.toString();
        String statePlayers = "";
        for (Player p : players){
            statePlayers += p.toString() + '\n';
        }
        String stateMonsters = "";
        for (Monster m : monsters){
            stateMonsters += m.toString() + " ";
        }
        GameState gs = new GameState(stateLabyrinth, statePlayers, 
                                    stateMonsters, currentPlayerIndex, 
                                    finished(), log);
        return gs;
    }
    
    private void configureLabyrinth(){
        labyrinth.addBlock(Orientation.VERTICAL, 0, 9, 4);
        labyrinth.addBlock(Orientation.HORIZONTAL, 4, 2, 6);
        
        Monster topologia = new Monster("Topologia", 10.0f, 10.0f); 
        monsters.add(topologia); // El monstruo es T2 (?)
        labyrinth.addMonster(5, 5, topologia);
        
        Monster ec = new Monster("EC", Dice.randomIntelligence(), 
            Dice.randomStrength()); 
        monsters.add(ec);
        labyrinth.addMonster(6, 6, ec);
        
    }
    
    private void nextPlayer(){
        currentPlayerIndex++;
        if (currentPlayerIndex >= players.size())
            currentPlayerIndex = 0;
        currentPlayer = players.get(currentPlayerIndex);
    }
    
    private Directions actualDirection(Directions preferredDirection){
        int currentRow = currentPlayer.getRow();
        int currentCol = currentPlayer.getCol();
        
        ArrayList<Directions> validMoves = labyrinth.validMoves(currentRow, currentCol);
        
        Directions output = currentPlayer.move(preferredDirection, validMoves);
        
        return output;
    }
    
    private GameCharacter combat(Monster monster){
        int rounds = 0;
        GameCharacter winner = GameCharacter.PLAYER;
        
        float playerAttack = currentPlayer.attack();
        boolean lose = monster.defend(playerAttack);
        
        while((!lose)&&(rounds<MAX_ROUNDS)){
            winner = GameCharacter.MONSTER;
            rounds++;
            
            float monsterAttack = monster.attack();
            lose = currentPlayer.defend(monsterAttack);
            
            if(!lose){
                playerAttack = currentPlayer.attack();
                winner = GameCharacter.PLAYER;
                lose = monster.defend(playerAttack);
            }
        }
        
        logRounds(rounds, MAX_ROUNDS);
        
        return winner;
    }
    
    private void manageReward(GameCharacter winner){
        if(winner == GameCharacter.PLAYER){
            currentPlayer.receiveReward();
            logPlayerWon();
        }
        
        else {
            logMonsterWon();
        }
    }
    
    private void manageResurrection(){
        boolean resurrect = Dice.resurrectPlayer();
        
        if(resurrect) {
            currentPlayer.resurrect();
            logResurrected();
        }
        
        else {
            logPlayerSkipTurn();
        }
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
    
    private void logRounds(int rounds, int max){
        log += "Se han producido " + rounds 
                    + " rondas en el combate de un maximo de " + MAX_ROUNDS 
                    + ". ";
                }
}
