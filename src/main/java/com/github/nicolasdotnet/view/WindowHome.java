/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.view;

import com.github.nicolasdotnet.model.CheckUserInput;
import com.github.nicolasdotnet.model.Propertie;
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
    private int nbrTours;
    private int nbrRange;
    CheckUserInput checkUserInput = new CheckUserInput();
    Boolean inputUser;

    public WindowHome() {

        Propertie para = new Propertie();
        para.uploadProperties();

        this.nbrCombinaison = para.getNbrCombinaison();
        this.nbrRange = para.getNbrRange();
        this.nbrTours = para.getNbrTours();

        JPanel contentPanel = (JPanel) this.getContentPane();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(welcomeMessage(), BorderLayout.NORTH);
        contentPanel.add(choiceGames(), BorderLayout.CENTER);
        contentPanel.add(optionsPanel(), BorderLayout.SOUTH);
        this.setVisible(true);

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
        panel.setLayout(new GridLayout(2, 1, 10, 10));

        JPanel param = new JPanel();
        param.setLayout(new GridLayout(3, 2, 0, 0));

        // nbrCombinaison Label instructions
        JTextArea textAreaOut = new JTextArea("1) Tapez un N° entre 1 et 4 pour définir le N°\nde cases des combinaisons");
        textAreaOut.setEditable(false);
        param.add(textAreaOut);

        // Field input value nbrCombinaison
        JTextField textFieldIn = new JTextField();
        param.add(textFieldIn);

        textFieldIn.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent event) {

                if (event.getKeyCode() == KeyEvent.VK_ENTER) {

                    try {
                        setNbrCombinaison(Integer.valueOf(textFieldIn.getText()));
                    } catch (NumberFormatException nfe) {
                        System.out.println("valeur nbrCombinaison : " + textFieldIn.getText());
                    }

                }

            }

        });

        // nbrTours Label instructions
        JTextArea textAreaOut2 = new JTextArea("2) Tapez un N° entre 1 et 9 pour définir le N°\nde tours.");
        textAreaOut2.setEditable(false);
        param.add(textAreaOut2);

        // Field input value nbrTours
        JTextField textFieldIn2 = new JTextField();
        param.add(textFieldIn2);

        textFieldIn2.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent event) {

                if (event.getKeyCode() == KeyEvent.VK_ENTER) {

                    try {
                        setNbrTours(Integer.valueOf(textFieldIn2.getText()));
                    } catch (NumberFormatException nfe) {
                        System.out.println("valeur nbrTours : " + textFieldIn2.getText());
                    }

                }

            }

        });

        // nbrRange Label instructions
        JTextArea textAreaOut3 = new JTextArea("3) Tapez un N° entre 4 et 9 pour définir \nl'amplitude maximal des nombres.\nPuis sélectionnez le jeu");
        textAreaOut3.setEditable(false);
        param.add(textAreaOut3);

        // Field input value nbrRange
        JTextField textFieldIn3 = new JTextField();
        param.add(textFieldIn3);

        textFieldIn3.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent event) {

                if (event.getKeyCode() == KeyEvent.VK_ENTER) {

                    try {
                        setNbrRange(Integer.valueOf(textFieldIn3.getText()));
                    } catch (NumberFormatException nfe) {

                        System.out.println("valeur nbrRange : " + textFieldIn3.getText());

                    }

                }

            }

        });

        panel.add(param);

        // Choice games buttons
        JPanel choiceGames = new JPanel();

        choiceGames.setLayout(new GridLayout(1, 2, 10, 10));
        JButton mastermind = new JButton("MasterMind");

        mastermind.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                boolean inputCombinaison = checkUserInput.inputErrorHome(textFieldIn.getText(), 4);
                boolean inputTours = checkUserInput.inputErrorHome(textFieldIn2.getText(), 9);
                boolean inputRange = checkUserInput.inputErrorHome(textFieldIn3.getText(), 9);

                if (inputCombinaison || inputTours || inputRange) {
                    textFieldIn.setText("");
                    textFieldIn2.setText("");
                    textFieldIn3.setText("");

                    WindowMain windowMain = new WindowMain(0, 1, "Mastermind", nbrCombinaison, nbrTours, nbrRange);
                    WindowHome.super.dispose();

                } else {

                    setNbrRange(Integer.valueOf(textFieldIn3.getText()));

                    setNbrTours(Integer.valueOf(textFieldIn2.getText()));
                    setNbrCombinaison(Integer.valueOf(textFieldIn.getText()));
                    WindowMain windowMain = new WindowMain(0, 1, "Mastermind", nbrCombinaison, nbrTours, nbrRange);
                    WindowHome.super.dispose();
                }

            }
        });
        choiceGames.add(mastermind);

        JButton searchMoreOrless = new JButton("Recherche +/-");

        searchMoreOrless.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                boolean inputCombinaison = checkUserInput.inputErrorHome(textFieldIn.getText(), 4);
                boolean inputTours = checkUserInput.inputErrorHome(textFieldIn2.getText(), 9);
                boolean inputRange = checkUserInput.inputErrorHome(textFieldIn3.getText(), 9);

                if (inputCombinaison || inputTours || inputRange) {
                    textFieldIn.setText("");
                    textFieldIn2.setText("");
                    textFieldIn3.setText("");

                    WindowMain windowMain = new WindowMain(1, 0, "Recherche +/-", nbrCombinaison, nbrTours, nbrRange);
                    WindowHome.super.dispose();

                } else {

                    setNbrRange(Integer.valueOf(textFieldIn3.getText()));

                    setNbrTours(Integer.valueOf(textFieldIn2.getText()));
                    setNbrCombinaison(Integer.valueOf(textFieldIn.getText()));
                    WindowMain windowMain = new WindowMain(1, 0, "Recherche +/-", nbrCombinaison, nbrTours, nbrRange);
                    WindowHome.super.dispose();
                }

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
