package irrgarten;

/**
 *
 * @author usuario
 */
public class Weapon extends CombatElement {  
    Weapon(float _power, int _uses) {
       super(_power, _uses);
    }
    
    public double attack() {
       return produceEffect();
    }
    
    @Override 
    public String toString(){
        return "W" + super.toString();
    }
}