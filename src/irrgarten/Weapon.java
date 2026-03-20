package irrgarten;

/**
 *
 * @author usuario
 */
public class Weapon {
    private float power;
    private int uses;
    
    Weapon(float _power, int _uses) {
        power = _power;
        uses = _uses;
    }
    
    public double attack() {
        if (uses > 0) {
            uses--;
            return power;
        }
        
        return 0;
    }
    
    @Override 
    public String toString(){
        return "W[" + power + "," + uses + "]";
    }
}