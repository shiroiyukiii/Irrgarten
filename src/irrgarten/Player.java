/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

import java.util.ArrayList;

/**
 *
 * @author usuario
 */
public class Player {
    private static final int MAX_WEAPONS = 2;
    private static final int MAX_SHIELDS = 3;
    private static final int INITIAL_HEALTH = 10;
    private static final int HITS2LOSE = 3;
    
    private ArrayList<Weapon> weapons;
    private ArrayList<Shield> shields;
    
    private String name;
    private char number;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;
    private int consecutiveHits = 0;
    
    Player(char number, float intelligence, float strength){
        number = number;
        intelligence = intelligence;
        strength = strength;        
    }
    
    public void resurrect(){
        weapons.clear();
        shields.clear();
        health = INITIAL_HEALTH;
        consecutiveHits = 0;
    }
    
    // La clase Labyrinth necesita saber varios atributos de Player
    public char getNumber() {
        return number;
    }
    
    public int getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
    }
    
    public void setPos(int row, int col){
        row = row;
        col = col;
    }
    
    public boolean dead(){
        return (health <= 0);
    }
    
    public float attack(){
        return strength + sumWeapons();
    }
    
    public float defend(float receivedAttack){
        return manageHit();
    }
    
    public String toString(){
        String playerWeapons = "";
        for (Weapon w : weapons){
            playerWeapons += w.toString();
        }
        
        String playerShields = "";
        for(Shield s : shields){
            playerShields += s.toString();
        }
        
        return row + "Player Status: " +
                "\nName: " + name +
                "\nNumber: " + number + 
                "\nIntelligence: " + intelligence +
                "\nStrength: " + strength +
                "\nRow: " + row +
                "\nCol: " + col + 
                "\nConsecutive Hits: " + consecutiveHits +
                "\nWeapons: " + playerWeapons + 
                "\nShields: " + playerShields;
                
    }
    
    private Weapon newWeapon(){ 
        Weapon w = new Weapon(Dice.weaponPower(), Dice.usesLeft());
        return w;
    }
    
    private Shield newShield(){ 
        Shield s = new Shield(Dice.shieldPower(), Dice.usesLeft());
        return s;
    }
    
    
    private float sumWeapons(){
        float sum = 0;
        for (int i=0; i<weapons.size(); i++){
            sum += weapons.get(i).attack();
        }
        return sum;
    }
    
    private float sumShields(){
        float sum = 0;
        for (int i=0; i<shields.size(); i++){
            sum += shields.get(i).protect();
        }
        return sum;
    }
    
    private float defensiveEnergy(){
        return intelligence + sumShields();
    }
    
    private void resetHits(){
        consecutiveHits = 0;
    }
    
    private void gotWounded(){
        health--;
    }
    
    private void incConsecutiveHits(){
        consecutiveHits++;
    }
    
    private boolean manageHit(){
        //TODAVIA NO SE HACE
    }
}