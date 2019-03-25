/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.view;

import javax.swing.JFrame;

/**
 * WindowSource est la classe qui représente le squelette d'un fenêtre
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class WindowSource extends JFrame {

    public WindowSource() {
        super("Jeux de logique");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(580, 500);
        this.setLocationRelativeTo(null);
        
        // param to enable WindowSource visibility 
//        this.setVisible(true);
        
    }

}
