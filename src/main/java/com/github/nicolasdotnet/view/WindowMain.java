/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    private int searchMoreOrLess = 0;
    private int mastermind = 0;

    public WindowMain() {

        JPanel contentPanel = (JPanel) this.getContentPane();
        contentPanel.setLayout(new BorderLayout());

        contentPanel.add(gamesMenu(), BorderLayout.WEST);
        contentPanel.add(gamePanel(), BorderLayout.CENTER);
        contentPanel.add(optionsPanel(), BorderLayout.SOUTH);

        this.setVisible(true);

    }

    public void setSearchMoreOrLess(int searchMoreOrLess) {
        this.searchMoreOrLess = searchMoreOrLess;
    }

    public void setMastermind(int mastermind) {
        this.mastermind = mastermind;
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
     * Create game buttons panel.
     *
     * @return panel with game buttons.
     */
    private JPanel games() {

        JPanel games = new JPanel();

        games.setLayout(new GridLayout(2, 1, 40, 40));
        JButton mastermindB = new JButton("MasterMind");
        games.add(mastermindB);
        mastermindB.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

            searchMoreOrLess = 0;
            mastermind = 1;

            }
        });
        
        JButton searchMoreOrLessB = new JButton("Recherche +/-");
        games.add(searchMoreOrLessB);
        searchMoreOrLessB.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                searchMoreOrLess = 1;
                mastermind = 0;

            }
        });

        return games;

    }

    /**
     * Create game panel.
     *
     * @return panel.
     */
    private JPanel gamePanel() {

        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(2, 1, 40, 40));

        gamePanel.add(new JTextArea("Sélectionnez un mode jeux "));

        gamePanel.add(gamesOptions());

        return gamePanel;
    }

    /**
     * Create games options panel with description options panel.
     *
     * @return games options panel.
     */
    private JPanel gamesOptions() {

        JPanel gamesOptions = new JPanel();
        JLabel label = new JLabel("Sélectionnez un mode de jeux ");
        gamesOptions.setLayout(new GridLayout(1, 2, 40, 40));
        JPanel buttons = Options();
        gamesOptions.add(buttons);

        Component[] component = buttons.getComponents();
        for (int i = 0; i < component.length; i++) {

            JButton button = (JButton) component[i];

            if (button.getText().equals("Challenger")) {

                button.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        label.setText("régles challenger");

                        if (searchMoreOrLess == 1) {

                        } else {

                        }

                    }
                });

            } else if (button.getText().equals("Défenseur")) {

                button.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        label.setText("régles Défenseur");

                        if (searchMoreOrLess == 1) {

                        } else {

                        }
                    }
                });

            } else {

                button.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        label.setText("régles Duel");

                        if (searchMoreOrLess == 1) {

                        } else {

                        }
                    }
                });

            }

        }

        gamesOptions.add(label);
        return gamesOptions;
    }

    /**
     * Create button games options panel.
     *
     * @return panel with button games options.
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
