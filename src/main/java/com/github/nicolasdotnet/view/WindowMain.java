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

    private int searchMoreOrLess;
    private int mastermind;
    private String breadcrumb;
    private int nbrDigits;
    private int nbrTours;
    private int nbrRange;
    private boolean modeDev;

    public int getNbrRange() {
        return nbrRange;
    }

    public void setNbrRange(int nbrRange) {
        this.nbrRange = nbrRange;
    }

    public int getNbrDigits() {
        return nbrDigits;
    }

    public int getNbrTours() {
        return nbrTours;
    }

    public void setNbrTours(int nbrTours) {
        this.nbrTours = nbrTours;
    }

    public void setNbrDigits(int nbrcombinaison) {
        this.nbrDigits = nbrcombinaison;
    }

    public int getSearchMoreOrLess() {
        return searchMoreOrLess;
    }

    public void setSearchMoreOrLess(int searchMoreOrLess) {
        this.searchMoreOrLess = searchMoreOrLess;
    }

    public int getMastermind() {
        return mastermind;
    }

    public void setMastermind(int mastermind) {
        this.mastermind = mastermind;
    }

    public String getBreadcrumb() {
        return breadcrumb;
    }

    public void setBreadcrumb(String breadcrumb) {
        this.breadcrumb = breadcrumb;
    }

    public boolean isModeDev() {
        return modeDev;
    }

    public void setModeDev(boolean modeDev) {
        this.modeDev = modeDev;
    }

    public WindowMain(int searchMoreOrLess, int mastermind, String breadcrumb, int nbrDigits, int nbrTours, int nbrRange, boolean modeDev) {

        this.searchMoreOrLess = searchMoreOrLess;
        this.mastermind = mastermind;
        this.breadcrumb = breadcrumb;
        this.nbrDigits = nbrDigits;
        this.nbrTours = nbrTours;
        this.nbrRange = nbrRange;
        this.modeDev = modeDev;
        this.setTitle("Jeux de logique -> " + breadcrumb);

        JPanel contentPanel = (JPanel) this.getContentPane();
        contentPanel.setLayout(new BorderLayout());

//        contentPanel.add(gamesMenu(), BorderLayout.WEST);
        contentPanel.add(breadcrumbPanel(), BorderLayout.NORTH);
        contentPanel.add(gamePanel(), BorderLayout.CENTER);
//        contentPanel.add(optionsPanel(), BorderLayout.SOUTH);

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
        gamePanel.setLayout(new GridLayout(2, 1, 5, 5));

        JTextArea instructional = new JTextArea("Sélectionnez un mode de jeu parmis les 3 modes proposés ci-dessous : ");
        instructional.setEditable(false);
        gamePanel.add(instructional);

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
        gamesOptions.setLayout(new GridLayout(1, 2, 10, 0));

        JPanel buttons = options();
        gamesOptions.add(buttons);

        JPanel optionsDescription = optionsDescription();
        gamesOptions.add(optionsDescription);

        Component[] component = buttons.getComponents();
        for (int i = 0; i < component.length; i++) {

            JButton button = (JButton) component[i];

            if (button.getText().equals("Challenger")) {

                button.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent ae) {

                        if (searchMoreOrLess == 1) {

                            System.out.println("nbrtour Main : " + getNbrTours());
                            System.out.println("Modedev Main : " + isModeDev());
                            SearchMoreOrLessChallenger run = new SearchMoreOrLessChallenger("Recherche +/- Challenger",getNbrDigits(), getNbrTours(), getNbrRange(), isModeDev());

                        } else {
                            System.out.println("nbrtour Main : " + getNbrTours());
                            System.out.println("Modedev Main : " + isModeDev());
                            MastermindChallenger run = new MastermindChallenger("Mastermind Challenger",getNbrDigits(), getNbrTours(), getNbrRange(), isModeDev());

                        }

                    }
                });

            } else if (button.getText().equals("Défenseur")) {

                button.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent ae) {

                        if (searchMoreOrLess == 1) {

                            System.out.println("nbrtour Main : " + getNbrTours());
                            System.out.println("Modedev Main : " + isModeDev());
                            SearchMoreOrLessDefenseur run = new SearchMoreOrLessDefenseur("Recherche +/- Défenseur",getNbrDigits(), getNbrTours(), getNbrRange(), isModeDev());

                        } else {

                            System.out.println("nbrtour Main : " + getNbrTours());
                            System.out.println("Modedev Main : " + isModeDev());
                            MastermindDefenseur run = new MastermindDefenseur("Mastermind Défenseur",getNbrDigits(), getNbrTours(), getNbrRange(), isModeDev());

                        }
                    }
                });

            } else {

                button.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent ae) {

                        if (searchMoreOrLess == 1) {
                            System.out.println("nbrtour Main : " + getNbrTours());
                            System.out.println("Modedev Main : " + isModeDev());
                            SearchMoreOrLessDuel run = new SearchMoreOrLessDuel("Recherche +/- Duel",getNbrDigits(), getNbrTours(), getNbrRange(), isModeDev());

                        } else {
                            System.out.println("nbrtour Main : " + getNbrTours());
                            System.out.println("Modedev Main : " + isModeDev());
                            MastermindDuel run = new MastermindDuel("Mastermind Duel",getNbrDigits(), getNbrTours(), getNbrRange(), isModeDev());

                        }
                    }
                });

            }

        }

        return gamesOptions;
    }

    /**
     * Create button games options panel.
     *
     * @return panel with button games options.
     */
    private JPanel options() {

        JPanel options = new JPanel();
        options.setLayout(new GridLayout(3, 1, 20, 20));
        options.add(new JButton("Challenger"));
        options.add(new JButton("Défenseur"));
        options.add(new JButton("Duel"));

        return options;
    }

    /**
     * Create options panel.
     *
     * @return panel with Options Button.
     */
//    private JPanel optionsPanel() {
//
//        JPanel optionPanel = new JPanel();
//        optionPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
//        optionPanel.add(new JButton("Options"));
//
//        return optionPanel;
//
//    }

    /**
     * Create games options description panel.
     *
     * @return panel with games options description.
     */
    private JPanel optionsDescription() {

        JPanel optionsDescription = new JPanel();
        optionsDescription.setLayout(new GridLayout(3, 1, 5, 5));

//        Option description challenger
        optionsDescription.add(new JTextArea("En mode challenger, vous devez trouver\nla combinaison secrète de l'ordinateur."));
//        Option description Défenseur
        optionsDescription.add(new JTextArea("En mode défenseur, c'est à l'ordinateur\nde trouver votre combinaison secrète."));
//        Option description Duel
        optionsDescription.add(new JTextArea("En mode duel, l'ordinateur et vous jouez\ntour à tour. Le premier à trouver la\ncombinaison secrète de l'autre a gagné !"));

        return optionsDescription;

    }

    /**
     * Create breadcrumb description panel.
     *
     * @return panel with return button to WhindowHome.
     */
    private JPanel breadcrumbPanel() {

        JPanel breadcrumbPanel = new JPanel();
        breadcrumbPanel.setLayout(new FlowLayout());
        JButton returnButton = new JButton("Retour au choix de jeux logique");
        returnButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                WindowHome windowHome = new WindowHome();
                WindowMain.super.dispose();

            }
        });

        breadcrumbPanel.add(returnButton);

        return breadcrumbPanel;

    }
}
