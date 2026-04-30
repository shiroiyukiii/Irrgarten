/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author Víctor
 */
public class WeaponCardDeck extends CardDeck<Weapon> {
    
    @Override
    protected void addCards() {
        for (int i = 0; i < STARTING_CARDS; i++) {
            addCard(new Weapon(Dice.weaponPower(), Dice.usesLeft()));
        }
    }
}
