/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author Víctor
 */
public class Monster {
    private static final int INITIAL_HEALTH = 5;
    private static final int OUT_OF_BOUNDS = -1;
    private String name;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;
    
    public Monster(String _name, float _intelligence, float _strength) {
        health = INITIAL_HEALTH;
        row = OUT_OF_BOUNDS;
        col = OUT_OF_BOUNDS;
        
        name = _name;
        intelligence = _intelligence;
        strength = _strength;
    }
    
    public boolean dead() {
        return health <= 0;
    }
    
    public float attack() {
        return Dice.intensity(strength);
    }
    
    public boolean defend(float receivedAttack) {
        boolean isDead = dead();
        if(!isDead) {
            if (Dice.intensity(intelligence) < receivedAttack) {
                gotWounded();
                isDead = dead();
            }
        }      
        
        return isDead;
    }
    
    public void setPos(int _row, int _col) {
        row = _row;
        col = _col;
    }
    
    public String toString() {
       return String.format("M[%s, %f, %f, %s, %d, %d]", name, intelligence,
            strength, health, row, col);
    }
    
    private void gotWounded() {
        --health;
    }
}
