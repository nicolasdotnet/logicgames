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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * WindowHome est la classe qui représente la fenêtre d'ouverture
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class WindowHome extends WindowSource {
    
    private int nbrCombinaison;
    
    public WindowHome() {

        JPanel contentPanel = (JPanel) this.getContentPane();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(welcomeMessage(), BorderLayout.NORTH);
        contentPanel.add(choiceGames(), BorderLayout.CENTER);
        contentPanel.add(optionsPanel(), BorderLayout.SOUTH);
        this.setVisible(true);
        
        nbrCombinaison = 4;

    }

    public int getNbrCombinaison() {
        return nbrCombinaison;
    }

    public void setNbrCombinaison(int nbrCombinaison) {
        this.nbrCombinaison = nbrCombinaison;
    }

    /**
     * Create choice of games panel.
     *
     * @return panel with the buttons game.
     */
    private JPanel choiceGames() {

        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(3, 1, 10, 10));
        
       // Label instructions
        JTextArea textAreaOut = new JTextArea("Tapez un N° entre 1 et 4 pour définir le N° de cases des combinaisons\nPuis sélectionnez le jeu");
        textAreaOut.setEditable(false);
        panel.add(textAreaOut);

        // Field input value nbrCombinaison
        JTextField textFieldIn = new JTextField();
        panel.add(textFieldIn);

        textFieldIn.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent event) {
                
                if (event.getKeyCode() == KeyEvent.VK_ENTER) {

                    setNbrCombinaison(Integer.valueOf(textFieldIn.getText())); 

                }

            }

        });

        // Choice games buttons
        JPanel choiceGames = new JPanel();

        choiceGames.setLayout(new GridLayout(1, 2, 10, 10));
        JButton mastermind = new JButton("MasterMind");

        mastermind.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                    setNbrCombinaison(Integer.valueOf(textFieldIn.getText()));
                    WindowMain windowMain = new WindowMain(0, 1, "Mastermind", nbrCombinaison);
                    WindowHome.super.dispose();
            }
        });
        choiceGames.add(mastermind);

        JButton searchMoreOrless = new JButton("Recherche +/-");

        searchMoreOrless.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                setNbrCombinaison(Integer.valueOf(textFieldIn.getText()));
                WindowMain windowMain = new WindowMain(1, 0, "Recherche +/-",nbrCombinaison);
                WindowHome.super.dispose();

            }
        });
        
        choiceGames.add(searchMoreOrless);

        panel.add(choiceGames);
        

        return panel;
    }

    /**
     * Create welcome message panel.
     *
     * @return panel with welcome message.
     */
    private JLabel welcomeMessage() {

        JLabel welcomeMessage = new JLabel("Bienvenue dans les Jeux de logiques");
        Font police = new Font("Tahoma", Font.BOLD, 15);
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
