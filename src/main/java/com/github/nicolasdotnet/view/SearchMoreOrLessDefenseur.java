/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.view;

import com.github.nicolasdotnet.controller.ControllerSearchMoreOrLess;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

/**
 * SearchMoreOrLessDefenseur est la classe qui représente la fenêtre de jeux
 * Recherche +/- en mode Défenseur.
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class SearchMoreOrLessDefenseur extends WindowGame implements KeyListener, ActionListener {

    private int nbrDigits;
    private int nbrTours;
    private int nbrRange;
    private String human;
    private String result;
    private String machine;
    private String valueInput;
    private int nbrTests;
    private int step;
    private Boolean inputUser;
    private int counter;
    private int[][] randomRange;
    private HashMap<String, String> backup;
    private ControllerSearchMoreOrLess checkUserInput;

    public SearchMoreOrLessDefenseur(String title, int nbrDigits, int nbrTours, int nbrRange, boolean modeDev) {
        super(title, modeDev);
        this.nbrDigits = nbrDigits;
        this.nbrTours = nbrTours;
        this.nbrRange = nbrRange;

        checkUserInput = new ControllerSearchMoreOrLess("defenseur");
        backup = checkUserInput.getParameterBackup(title, nbrDigits, nbrTours, nbrRange, modeDev);

        nbrTests = 0;
        step = 0;

        getTextAreaIn().addKeyListener(this);
        getYes().addActionListener(this);

        // Display first message : 
        getTextAreaOut().setText("Entrer une combinaison secrète que doit trouver la machine : ");

    }

    @Override
    public void keyReleased(KeyEvent event) {

        String message = "Entrer une combinaison secrète que doit trouver la machine : ";

        if (event.getKeyCode() == KeyEvent.VK_ENTER) {

            valueInput = getTextAreaIn().getText();

            getTextAreaOut().insert(valueInput + " ", message.length());

            // user input cleared
            getTextAreaIn().setText("");

            getTextAreaOut().append("\n");

            inputUser = checkUserInput.inputError(valueInput, nbrDigits);

            // Initial phase
            if (inputUser) {

                getTextAreaOut().append("Erreur de saisie, veuillez entrer un nombre positif,\nsans virgule et de " + nbrDigits + " chiffres\n");

            } else {

                randomRange = checkUserInput.getGenerateRandomRangeInitial(nbrDigits, nbrRange);
                human = checkUserInput.getSolutionCombination(nbrDigits, nbrRange, valueInput);
                
                getSolution().setText(valueInput);
                getTextAreaIn().setEditable(false);

                // Gaming machine
                do {

                    // update nbrTours & nbrTest by round
                    nbrTours--;
                    nbrTests++;
                    
                    String sizureFake = "null";

                    machine = checkUserInput.getGetPossible(randomRange, nbrDigits, sizureFake);
                    getTextAreaOut().append("La proposition de la machine est : " + machine.toString() + "\n\n");

                    result = "";
                    result = (checkUserInput.getComparison(nbrDigits, machine, human, result));

                    counter = 0;
                    counter = checkUserInput.getEqualCounter(result);

                    randomRange = checkUserInput.getGenerateRandomRangeNew(result, machine, randomRange);

                    String toString = result;

                    if (counter == nbrDigits) {

                        machineWinningMessageDisplay(nbrTests, toString, human.toString(), machine.toString());
                        getReloadButton().setVisible(true);

                    } else {

                        if (nbrTours == 0) {

                            machineLoserMessageDisplay(human.toString(), toString, machine.toString());
                            getReloadButton().setVisible(true);

                        } else {

                            machineToBeToContinuedMessageDisplay(nbrTours, toString);

                        }
                    }

                } while (counter != nbrDigits && nbrTours != 0);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent ke
    ) {

    }

    @Override
    public void keyPressed(KeyEvent ke
    ) {

    }

    @Override
    public void actionPerformed(ActionEvent event) {

        String titleBackup = backup.get("title");
        int nbrDigitsBackup = Integer.parseInt(backup.get("nbrDigits"));
        int nbrToursBackup = Integer.parseInt(backup.get("nbrTours"));
        int nbrRangeBackup = Integer.parseInt(backup.get("nbrRange"));
        boolean modeDevBackup = Boolean.parseBoolean(backup.get("modeDev"));

        SearchMoreOrLessDefenseur run = new SearchMoreOrLessDefenseur(titleBackup, nbrDigitsBackup, nbrToursBackup, nbrRangeBackup, modeDevBackup);
        SearchMoreOrLessDefenseur.super.dispose();
    }

}
