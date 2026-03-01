/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

import java.util.Random;

public class Dice {
    static final private int MAX_USES = 5;
    static final private float MAX_INTELLIGENCE = 10.0f;
    static final private float MAX_STRENGTH = 10.0f;
    static final private float RESURRECT_PROB = 0.3f;
    static final private int WEAPONS_REWARD = 2;
    static final private int SHIELDS_REWARD = 3;
    static final private int HEALTH_REWARD = 5;
    static final private int MAX_ATTACK = 3;
    static final private int MAX_SHIELD = 2;
    static final private Random generator = new Random();
    
    static public int randomPos(int max) {
        return generator.nextInt(max);
    }
    
    static public int whoStarts(int nplayers) {
        return generator.nextInt(nplayers);
    }
    
    static public float randomIntelligence() {
        return generator.nextFloat() * MAX_INTELLIGENCE;
    }
    
    static public float randomStrength() {
        return generator.nextFloat() * MAX_STRENGTH;
    }
    
    static public boolean resurrectPlayer() {
        return generator.nextFloat() > RESURRECT_PROB;
    }
    
    static public int weaponsReward() {
        return generator.nextInt(WEAPONS_REWARD + 1);
    }
    
    static public int shieldsReward() {
        return generator.nextInt(SHIELDS_REWARD + 1);
    }
    
    static public int healthReward() {
        return generator.nextInt(HEALTH_REWARD + 1);
    }
    
    static public float weaponPower() {
        return generator.nextFloat(MAX_ATTACK);
    }
    
    static public float shieldPower() {
        return generator.nextFloat(MAX_SHIELD);
    }
    
    static public int usesLeft() {
        return generator.nextInt(MAX_USES + 1);
    }
    
    static public float intensity(float competence) {
        return generator.nextFloat(competence);
    }
    
    static public boolean discardElement(int usesLeft) {
        return generator.nextFloat() >= ((float)usesLeft / (float)MAX_USES);
    }
 
}
