/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author usuario
 */
public class Shield extends CombatElement {
    Shield(float _protection, int _uses) {
        super(_protection, _uses);
    }
    
    public double protect() {
        return produceEffect();
    }
    
    @Override 
    public String toString(){
        return "S" + super.toString();
    }
}
