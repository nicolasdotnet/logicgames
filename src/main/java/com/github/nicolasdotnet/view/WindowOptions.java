/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.view;

import com.github.nicolasdotnet.model.CheckUserInput;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * WindowOptions est la classe qui représente la fenêtre d'options des jeux.
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class WindowOptions extends WindowSource {

    private int nbrCombinaison;
    private int nbrTours;
    private int nbrRange;
    private boolean modeDev;

    CheckUserInput checkUserInput = new CheckUserInput();
    Boolean inputUser;
    WindowHome windowHome;

    public WindowOptions(WindowHome windowHome) {
        this.windowHome = windowHome;
        this.setTitle("Options");

        JPanel contentPanel = (JPanel) this.getContentPane();
        contentPanel.setLayout(new BorderLayout());

        contentPanel.add(choiceGames(), BorderLayout.CENTER);

        this.setVisible(true);
    }

    public int getNbrCombinaison() {
        return nbrCombinaison;
    }

    public void setNbrCombinaison(int nbrCombinaison) {
        this.nbrCombinaison = nbrCombinaison;
    }

    public int getNbrTours() {
        return nbrTours;
    }

    public void setNbrTours(int nbrTours) {
        this.nbrTours = nbrTours;
    }

    public int getNbrRange() {
        return nbrRange;
    }

    public void setNbrRange(int nbrRange) {
        this.nbrRange = nbrRange;
    }

    public boolean isModeDev() {
        return modeDev;
    }

    public void setModeDev(boolean modeDev) {
        this.modeDev = modeDev;
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
        param.setLayout(new GridLayout(4, 2, 0, 0));

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

        // nbrRange Label instructions
        JTextArea textAreaOut4 = new JTextArea("4) Activation de l'option Développeur \nl'amplitude maximal des nombres.\nPuis sélectionnez le jeu");
        textAreaOut3.setEditable(false);
        param.add(textAreaOut4);

        // Input modeDev option
        JCheckBox modeDevCheck = new JCheckBox("DevMode");
        param.add(modeDevCheck);

        modeDevCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (modeDevCheck.isSelected()) {

                    System.out.println("coché !");

                    windowHome.setModeDev(true);

                } else {

                    System.out.println("décoché !");
                    windowHome.setModeDev(false);
                }
            }
        });

        panel.add(param);

        // Choice games buttons
        JPanel choiceGames = new JPanel();

        choiceGames.setLayout(new GridLayout(1, 2, 10, 10));
        JButton mastermind = new JButton("Annuler");

        mastermind.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                
                WindowOptions.this.dispose();

            }
        });

        choiceGames.add(mastermind);

        JButton searchMoreOrless = new JButton("Valider");

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

                } else {

                    windowHome.setNbrRange(Integer.valueOf(textFieldIn3.getText()));
                    windowHome.setNbrTours(Integer.valueOf(textFieldIn2.getText()));
                    windowHome.setNbrCombinaison(Integer.valueOf(textFieldIn.getText()));
                    WindowOptions.this.dispose();

                }

            }
        });

        choiceGames.add(searchMoreOrless);

        panel.add(choiceGames);

        return panel;
    }

}
