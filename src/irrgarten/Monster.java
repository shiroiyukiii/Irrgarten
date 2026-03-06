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
    private String name;
    private float intelligence;
    private float health;
    private int row;
    private int col;
    
    public Monster(String name, float intelligence, float strength) {
        throw new UnsupportedOperationException();
    }
    
    public boolean dead() {
        throw new UnsupportedOperationException();
    }
    
    public float attack() {
        throw new UnsupportedOperationException();
    }
    
    public boolean defend(float receivedAttack) {
        throw new UnsupportedOperationException();
    }
    
    public void setPos(int row, int col) {
        throw new UnsupportedOperationException();
    }
    
    public String toString() {
        throw new UnsupportedOperationException();
    }
    
    public void gotWounded() {
        throw new UnsupportedOperationException();
    }
}
