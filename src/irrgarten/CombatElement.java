/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author Víctor
 */
abstract public class CombatElement {
    private float effect;
    private int uses;
    
    public CombatElement(float _effect, int _uses) {
        effect = _effect;
        uses = _uses;
    }
    
    protected double produceEffect() {
        if (uses > 0) {
            uses--;
            return effect;
        }
        
        return 0;
    }
    
    public boolean discard() {
        return Dice.discardElement(uses);
    }
    
    @Override
    public String toString() {
        return "[" + effect + "," + uses + "]"; 
    }
}
