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
public class Player extends LabyrinthCharacter {
    private static final int MAX_WEAPONS = 2;
    private static final int MAX_SHIELDS = 3;
    private static final int INITIAL_HEALTH = 10;
    private static final int HITS2LOSE = 3;
    
    private ArrayList<Weapon> weapons;
    private ArrayList<Shield> shields;
    private WeaponCardDeck weaponCardDeck;
    private ShieldCardDeck shieldCardDeck;
    
    private char number;
    private int consecutiveHits = 0;
    
    Player(char _number, float _intelligence, float _strength){
        super(String.format("Player %s", _number),  _intelligence, _strength, 
                INITIAL_HEALTH);
        number = _number;
        
        // Initializing ArrayLists
        weapons = new ArrayList<Weapon>();
        shields = new ArrayList<Shield>();
        weaponCardDeck = new WeaponCardDeck();
        shieldCardDeck = new ShieldCardDeck();
    }
    
    Player(Player other) {
        super(other);
        // A implementar para FuzzyPlayer
        throw new UnsupportedOperationException();
    }
    
    public void resurrect(){
        weapons.clear();
        shields.clear();
        setHealth(INITIAL_HEALTH);
        consecutiveHits = 0;
    }

    public char getNumber() {
        return number;
    }
    
    public Directions move(Directions direction, ArrayList<Directions> validMoves){
        int size = validMoves.size();
        
        boolean contained = validMoves.contains(direction);
        
        if ((size>0)&&(!contained)) {
            Directions firstElement = validMoves.getFirst();
            return firstElement;
        }
        
        else {
            return direction;
        }
    }
    
    public float attack(){
        return getStrength() + sumWeapons();
    }
    
    public boolean defend(float receivedAttack){
        return manageHit(receivedAttack);
    }
    
    public void receiveReward(){
        int wReward = Dice.weaponsReward();
        int sReward = Dice.shieldsReward();
        
        for(int i = 0; i<wReward; i++){
            receiveWeapon(weaponCardDeck.nextCard());
        }
        
        for(int i = 0; i<sReward; i++){
            receiveShield(shieldCardDeck.nextCard());
        }
       
        int extraHealth = Dice.healthReward();
        setHealth(getHealth() + extraHealth);
    }
    
    @Override
    public String toString(){
        String playerWeapons = "";
        for (Weapon w : weapons){
            playerWeapons += w.toString() + " ";
        }
        
        String playerShields = "";
        for(Shield s : shields){
            playerShields += s.toString() + " ";
        }
      
        return "Player Status: " +
                "\nMain Stats: " + super.toString() +
                "\nConsecutive Hits: " + consecutiveHits +
                "\nWeapons: " + playerWeapons + 
                "\nShields: " + playerShields;
                
    }
    
    private void receiveWeapon(Weapon w){
        weapons.removeIf(wi -> wi.discard());
        
        int size = weapons.size();
        
        if(size < MAX_WEAPONS){
            weapons.add(w);
        }
    }
    
    private void receiveShield(Shield s){
        weapons.removeIf(si -> si.discard());
        
        int size = shields.size();
        
        if(size < MAX_SHIELDS){
            shields.add(s);
        }
    }
    
    private Weapon newWeapon(){ 
        Weapon w = new Weapon(Dice.weaponPower(), Dice.usesLeft());
        return w;
    }
    
    private Shield newShield(){ 
        Shield s = new Shield(Dice.shieldPower(), Dice.usesLeft());
        return s;
    }
    
    
    protected float sumWeapons(){
        float sum = 0;
        for (int i=0; i<weapons.size(); i++){
            sum += weapons.get(i).attack();
        }
        return sum;
    }
    
    protected float sumShields(){
        float sum = 0;
        for (int i=0; i<shields.size(); i++){
            sum += shields.get(i).protect();
        }
        return sum;
    }
    
    protected float defensiveEnergy(){
        return getIntelligence() + sumShields();
    }
    
    private void resetHits(){
        consecutiveHits = 0;
    }
    
    private void incConsecutiveHits(){
        consecutiveHits++;
    }
    
    private boolean manageHit(float receivedAttack){
        boolean lose;
        float defense = defensiveEnergy();
        
        if(defense < receivedAttack){
            gotWounded();
            incConsecutiveHits();
        }
        
        else {
            resetHits();
        }
        
        if ((consecutiveHits == HITS2LOSE) || ((dead()))){
            resetHits();
            lose = true;
        }
        
        else {
            lose = false;
        }
        
        return lose;
    }
}