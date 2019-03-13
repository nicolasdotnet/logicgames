/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * WindowMain est la classe qui représente la fenêtre principale
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class WindowMain extends WindowSource {

    public WindowMain() {

        JPanel contentPanel = (JPanel) this.getContentPane();
        contentPanel.setLayout(new BorderLayout());

        contentPanel.add(gamesMenu(), BorderLayout.WEST);
        contentPanel.add(gamesPanel(), BorderLayout.CENTER); // gameWindow ; gameOptions
        contentPanel.add(optionsPanel(), BorderLayout.SOUTH);

        this.setVisible(true);

    }

    /**
     * Create games menu panel.
     *
     * @return panel with the buttons game.
     */
    private JScrollPane gamesMenu() {

        JScrollPane gamesMenu = new JScrollPane(games());

        return gamesMenu;
    }

    /**
     * Create xxxx panel.
     *
     * @return panel with xxxxxx.
     */
    
    private JPanel games() {

        JPanel games = new JPanel();

        games.setLayout(new GridLayout(2, 1, 40, 40));
        games.add(new JButton("MasterMind"));
        games.add(new JButton("Recherche +/-"));

        return games;

    }

    /**
     * Create game panel.
     *
     * @return panel.
     */
    private JPanel gamesPanel() {

        JPanel gamesPanel = new JPanel();
        gamesPanel.setLayout(new GridLayout(2, 1, 40, 40));
        gamesPanel.add(new JTextArea());
        gamesPanel.add(gamesOptions());

        return gamesPanel;
    }
    
    /**
     * Create xxxx panel.
     *
     * @return panel with xxxxxx.
     */
    
    private JPanel gamesOptions() {

        JPanel gamesOptions = new JPanel();
        gamesOptions.setLayout(new GridLayout(1, 2, 40, 40));
        gamesOptions.add(Options());
        gamesOptions.add(new JLabel("Régles du jeux"));
        return gamesOptions;
    }
    
    /**
     * Create xxxx panel.
     *
     * @return panel with xxxxxx.
     */
    
    private JPanel Options() {

        JPanel Options = new JPanel();
        Options.setLayout(new GridLayout(3, 1, 40, 40));
        Options.add(new JButton("Challenger"));
        Options.add(new JButton("Défenseur"));
        Options.add(new JButton("Duel"));


        return Options;
    }
    
    
    /**
     * Create options panel.
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
