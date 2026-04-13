/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package irrgarten;

import irrgarten.UI.TextUI;
import irrgarten.controller.Controller;

/**
 *
 * @author usuario
 */
public class TestP1 {
    
    public static void main(String[] args) {
       TextUI view = new TextUI();
       Game game = new Game(1);
       Controller controller = new Controller(game, view);
       controller.play();
    }
    
}
