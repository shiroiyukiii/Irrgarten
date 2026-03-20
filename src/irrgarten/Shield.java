/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author usuario
 */
public class Shield {
    private float protection;
    private int uses;
    
    Shield(float _protection, int _uses) {
        protection = _protection;
        uses = _uses;
    }
    
    public double protect() {
        if (uses > 0) {
            uses--;
            return protection;
        }
        
        return 0;
    }
    
    @Override 
    public String toString(){
        return "S[" + protection + "," + uses + "]";
    }
}
