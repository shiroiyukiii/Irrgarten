/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author Víctor
 */
abstract public class LabyrinthCharacter {
    private static final int OUT_OF_BOUNDS = -1;
    private String name;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;
    
    
    public LabyrinthCharacter(String _name, float _intelligence, 
            float _strength, float _health) {
        name = _name;
        intelligence = _intelligence;
        strength = _strength;
        health = _health;
        row = OUT_OF_BOUNDS;
        col = OUT_OF_BOUNDS;
    }
    
    public LabyrinthCharacter(LabyrinthCharacter _other) {
        // A implementar para fuzzyPlayer
    }
    
    public boolean dead() {
        return health <= 0;
    }
    
    public int getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
    }
    
    protected float getIntelligence() {
        return intelligence;
    }
    
    protected float getStrength() {
        return strength;
    }
    
    protected float getHealth() {
        return health;
    }
    
    protected void setHealth(float _health) {
        health = _health;
    }
    
    public void setPos(int _row, int _col) {
        row = _row;
        col = _col;
    }
    
    @Override
    public String toString() {
        return String.format("[%s, %f, %f, %s, %d, %d]", name, intelligence,
            strength, health, row, col);
    }
    
    protected void gotWounded() {
        health--;
    }
    
    abstract float attack();
    
    abstract boolean defend(float attack);
}
