/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.logicgames;

import com.github.nicolasdotnet.view.WindowHome;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class Run {
    
    /**
     * Run app LogicGames
     * 
     * @param args : default param
     */
    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        
        // Apply a look'n feel
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        
        // Strat Window Home
        WindowHome LogicGames = new WindowHome();
        
    }
    
}
