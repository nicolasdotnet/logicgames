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
 * SearchMoreOrLessChallenger is the class that represents the Search +/- games
 * window in Challenger mode.
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class SearchMoreOrLessChallenger extends WindowGame implements KeyListener, ActionListener {

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
    private HashMap<String, String> backup;
    private ControllerSearchMoreOrLess checkUserInput;

    public SearchMoreOrLessChallenger(String title, int nbrDigits, int nbrTours, int nbrRange, boolean modeDev) {

        super(title, modeDev);
        this.nbrDigits = nbrDigits;
        this.nbrTours = nbrTours;
        this.nbrRange = nbrRange;

        checkUserInput = new ControllerSearchMoreOrLess("challenger");
        backup = checkUserInput.getParameterBackup(title, nbrDigits, nbrTours, nbrRange, modeDev);

        counter = 0;
        nbrTests = 0;
        step = 0;

        getTextAreaIn().addKeyListener(this);
        getYes().addActionListener(this);

        // Display first message : 
        getTextAreaOut().setText("Entrer une combinaison -> ");

    }

    @Override
    public void keyReleased(KeyEvent event) {

        String message = "Entrer une combinaison -> ";

        if (event.getKeyCode() == KeyEvent.VK_ENTER) {

            valueInput = getTextAreaIn().getText();

            getTextAreaOut().insert(valueInput + " ", message.length());

            // user input cleared
            getTextAreaIn().setText("");

            getTextAreaOut().append("\n");

            inputUser = checkUserInput.inputError(valueInput, nbrDigits, nbrRange);

            // Initial phase
            if (step == 0) {

                machine = checkUserInput.getSolutionCombination(nbrDigits, nbrRange, valueInput);
                getSolution().setText(machine);

            }

            if (inputUser) {

                nbrTours--;
                getTextAreaOut().append("Erreur de saisie, veuillez entrer un nombre positif,\nsans virgule et de " + nbrDigits + " chiffres\n");
                getTextAreaOut().append("Attention, il vous reste " + nbrTours + " tours\n");

            } else {

                // update nbrTours & nbrTest by round
                nbrTours--;
                nbrTests++;

                human = valueInput;

                result = "";
                result = (checkUserInput.getComparison(nbrDigits, human, machine, result));
                counter = checkUserInput.getEqualCounter(result);

                if (counter == nbrDigits) {

                    humanWinningMessageDisplay(nbrTests, result);
                    getReloadButton().setVisible(true);

                } else {

                    if (nbrTours == 0) {

                        humanLoserMessageDisplay(machine.toString(), result);
                        getReloadButton().setVisible(true);

                    } else {

                        humanToBeToContinuedMessageDisplay(nbrTours, result);
                    }
                }
            }

            step++;

        }

    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {

    }

    @Override
    public void actionPerformed(ActionEvent event) {

        String titleBackup = backup.get("title");
        int nbrDigitsBackup = Integer.parseInt(backup.get("nbrDigits"));
        int nbrToursBackup = Integer.parseInt(backup.get("nbrTours"));
        int nbrRangeBackup = Integer.parseInt(backup.get("nbrRange"));
        boolean modeDevBackup = Boolean.parseBoolean(backup.get("modeDev"));

        SearchMoreOrLessChallenger run = new SearchMoreOrLessChallenger(titleBackup, nbrDigitsBackup, nbrToursBackup, nbrRangeBackup, modeDevBackup);
        SearchMoreOrLessChallenger.super.dispose();
    }

}
