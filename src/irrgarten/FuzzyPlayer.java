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
public class FuzzyPlayer extends Player {
    
    public FuzzyPlayer(Player other) {
        super(other);
    }
    
    @Override
    public Directions move(Directions direction, ArrayList<Directions> validMoves) {
        Directions preferredDirection = super.move(direction, validMoves);
        
        return Dice.nextStep(preferredDirection, validMoves, this.getIntelligence());
    }
    
    @Override
    public float attack() {
        return super.sumWeapons() + Dice.intensity(this.getStrength());
    }
    
    @Override 
    public float defensiveEnergy() {
        return super.sumShields() + Dice.intensity(this.getIntelligence());
    }
    
    @Override 
    public String toString() {
        return "Fuzzy " + super.toString();
    }
}
