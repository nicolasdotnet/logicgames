/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * WindowSource is the class that represents the skeleton of a window.
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public abstract class WindowSource extends JFrame {

    public WindowSource() {
        super("Jeux de logique");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(580, 500);
        // ImageIcon(URL)
        ImageIcon icon = new ImageIcon("./src/main/resources/fav-icon.png");
        this.setIconImage(icon.getImage());
        this.setLocationRelativeTo(null);

        // param to enable WindowSource visibility 
        // this.setVisible(true);
    }

}
