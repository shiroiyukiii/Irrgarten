/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten.test.java.irrgarten;

import irrgarten.Dice;
/**
 *
 * @author Víctor
 */
public class Tests {
    static String diceTests() {
        for (int i = 0; i < 100; i++) {
            if (!Dice.discardElement(0)) 
                return "diceTests test FAILED: discardElement(0) method";
        }
        return "diceTest PASSED";
    }
    
    public static void main(String[] args) {
        System.out.println(diceTests());
    }
}
