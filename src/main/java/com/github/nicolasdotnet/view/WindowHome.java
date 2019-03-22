/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * WindowHome est la classe qui représente la fenêtre d'ouverture
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class WindowHome extends WindowSource {
    
    public WindowHome() {

        JPanel contentPanel = (JPanel) this.getContentPane();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(welcomeMessage(), BorderLayout.NORTH);
        contentPanel.add(choiceGames(), BorderLayout.CENTER);
        contentPanel.add(optionsPanel(), BorderLayout.SOUTH);
        this.setVisible(true);

    }

    /**
     * Create choice of games panel.
     *
     * @return panel with the buttons game.
     */
    private JPanel choiceGames() {

        JPanel choiceGames = new JPanel();

        choiceGames.setLayout(new GridLayout(1, 2, 40, 40));
        JButton mastermind = new JButton("MasterMind");
        
        mastermind.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                WindowMain windowMain = new WindowMain();
                windowMain.setMastermind(1);
                windowMain.setSearchMoreOrLess(0);
                WindowHome.super.dispose();
                

            }
        });
        choiceGames.add(mastermind);

        JButton searchMoreOrless = new JButton("Recherche +/-");

        searchMoreOrless.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                WindowMain windowMain = new WindowMain();
                windowMain.setSearchMoreOrLess(1);
                windowMain.setMastermind(0);
                WindowHome.super.dispose();

            }
        });
        choiceGames.add(searchMoreOrless);
       

        return choiceGames;
    }

    /**
     * Create welcome message panel.
     *
     * @return panel with welcome message.
     */
    private JLabel welcomeMessage() {

        JLabel welcomeMessage = new JLabel("Welcome");
        Font police = new Font("Tahoma", Font.BOLD, 25);
        welcomeMessage.setFont(police);
        welcomeMessage.setHorizontalAlignment(JLabel.CENTER);

        return welcomeMessage;
    }
    
    /**
     * Create options panel with Options Button to enable Developper Mode.
     *
     * @return panel with Options Button.
     */

    private JPanel optionsPanel() {

        JPanel optionPanel = new JPanel();
        optionPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        optionPanel.add(new JButton("Options"));

        return optionPanel;
    }

}
