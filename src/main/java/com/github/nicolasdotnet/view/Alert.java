/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.view;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * Alert est la classe qui représente le pop up d'alerte
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class Alert {

    public static void error(Component parentComponent, String message) {

        JOptionPane.showMessageDialog(parentComponent, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }

}
