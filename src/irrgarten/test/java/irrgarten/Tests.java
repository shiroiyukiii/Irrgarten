/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten.test.java.irrgarten;

import irrgarten.Dice;
import irrgarten.Monster;
/**
 *
 * @author Víctor
 */
public class Tests {
    static String monsterTests() {
        float intelligence = Dice.randomIntelligence();
        float strength = Dice.randomStrength();
        Monster monster = new Monster("pepe", intelligence, strength);
        String monster_s = monster.toString();
        
        System.out.println(monster_s);
        System.out.println(String.format("M[pepe, %f, %f, 5.0, -1, -1]", 
                intelligence, strength));
        if (!(monster_s.equals(String.format("M[pepe, %f, %f, 5.0, -1, -1]", 
                intelligence, strength))))
            return "monsterTests FAILED: toString() method";
        
        monster.defend(1.0f);
        if (!(monster.toString().equals(String.format("M[pepe, %f, %f, 4.0, -1, -1]", 
                intelligence, strength))))
            return "monsterTests FAILED: gotWounded() method";
        
        
        return "monsterTests PASSED";
    }
    static String diceTests() {
        for (int i = 0; i < 100; i++) {
            if (!Dice.discardElement(0)) 
                return "diceTests test FAILED: discardElement(0) method";
        }
        return "diceTests PASSED";
    }
    
    public static void main(String[] args) {
        System.out.println(diceTests());
        System.out.println(monsterTests());
    }
}
