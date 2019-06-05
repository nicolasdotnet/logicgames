/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.view;

import com.github.nicolasdotnet.controller.Controller;
import com.github.nicolasdotnet.model.Propertie;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
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

    private int nbrDigits;
    private int nbrTours;
    private int nbrRange;
    private boolean modeDev;
    Controller checkUserInput = new Controller();
    Boolean inputUser;

    public WindowHome() {

//        Propertie para = new Propertie();
//        para.uploadProperties();
        Propertie para = checkUserInput.getInstancePropertie();

        this.nbrDigits = para.getNbrDigits();
        this.nbrRange = para.getNbrRange();
        this.nbrTours = para.getNbrTours();
        this.modeDev = para.isModeDev();

        JPanel contentPanel = (JPanel) this.getContentPane();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(welcomeMessage(), BorderLayout.NORTH);
        contentPanel.add(choiceGames(), BorderLayout.CENTER);
        contentPanel.add(optionsPanel(), BorderLayout.SOUTH);
        this.setVisible(true);

    }

    public void setModeDev(boolean modeDev) {
        this.modeDev = modeDev;
    }

    public int getNbrRange() {
        return nbrRange;
    }

    public void setNbrRange(int nbrRange) {
        this.nbrRange = nbrRange;
    }

    public int getNbrTours() {
        return nbrTours;
    }

    public void setNbrTours(int nbrTours) {
        this.nbrTours = nbrTours;
    }

    public int getNbrDigits() {
        return nbrDigits;
    }

    public void setNbrDigits(int nbrDigits) {
        this.nbrDigits = nbrDigits;
    }

    /**
     * Create choice of games panel.
     *
     * @return panel with the buttons game.
     */
    private JPanel choiceGames() {

        // Choice games buttons
        JPanel choiceGames = new JPanel();
//        choiceGames.setLayout(new GridLayout(1, 2, 10, 10));
        choiceGames.setLayout(new FlowLayout());

        JButton mastermind = new JButton("MasterMind");
        mastermind.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                WindowMain windowMain = new WindowMain(0, 1, "Mastermind", nbrDigits, nbrTours, nbrRange, modeDev);
                WindowHome.super.dispose();

            }
        });

        choiceGames.add(mastermind);

        JButton searchMoreOrless = new JButton("Recherche +/-");
        searchMoreOrless.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                
                // nbrRange value max : 9 (0 to 9)
                int smolNbrRange = 9;
                
                WindowMain windowMain = new WindowMain(1, 0, "Recherche +/-", nbrDigits, nbrTours, smolNbrRange, modeDev);
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
        JButton optionsB = new JButton("Options");

        optionsB.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                try {

                    WindowOptions windowOptions = new WindowOptions(WindowHome.this);

                } catch (NumberFormatException nfe) {


                }
            }
        });

        optionPanel.add(optionsB);

        return optionPanel;
    }

}
