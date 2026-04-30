/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Víctor
 */
abstract public class CardDeck<T extends CombatElement> {
    private ArrayList<T> cardDeck;
    protected static final int STARTING_CARDS = 10;
    
    public CardDeck() {
        cardDeck = new ArrayList<T>();
    }
    
    abstract protected void addCards();
    
    protected void addCard(T card) {
        cardDeck.add(card);
    }
    
    public T nextCard() {
        if (cardDeck.isEmpty()) {
            addCards();
            Collections.shuffle(cardDeck);
        }
        T next = cardDeck.getFirst();
        cardDeck.remove(next);
        return next;
    }
}
