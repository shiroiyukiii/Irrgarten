/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package irrgarten;

import irrgarten.UI.MainWindow;
import irrgarten.controller.Controller;

/**
 *
 * @author usuario
 */
public class TestP1 {
    
    public static void main(String[] args) {
       MainWindow view = new MainWindow();
       Game game = new Game(2);
       Controller controller = new Controller(game, view);
       controller.play();
    }
    
}
