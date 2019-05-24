/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.view;

import com.github.nicolasdotnet.controller.ControllerMastermindChallenger;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * MasterminChallenger est la classe qui représente la fenêtre de jeux
 * Mastermind en mode Challenger.
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class MastermindChallenger extends WindowGame implements KeyListener, ActionListener {

    private int nbrDigits;
    private int nbrTours;
    private int nbrRange;
    private HashMap<String, String> result = new HashMap<String, String>();
    private List<Integer> machine = new ArrayList<Integer>();
    private String valueInput;
    private int step;
    private int nbrTests;
    private Boolean inputUser;
    private HashMap<String, String> backup;
    private ControllerMastermindChallenger checkUserInput;

    public MastermindChallenger(String title, int nbrDigits, int nbrTours, int nbrRange, boolean modeDev) {
        super(title, modeDev);
        this.nbrDigits = nbrDigits;
        this.nbrTours = nbrTours;
        this.nbrRange = nbrRange;

        checkUserInput = new ControllerMastermindChallenger();
        backup = checkUserInput.getParameterBackup(title, nbrDigits, nbrTours, nbrRange, modeDev);

        result = new HashMap<String, String>();
        machine = new ArrayList<Integer>();
        step = 0;
        nbrTests = 0;

        getTextAreaIn().addKeyListener(this);
        getYes().addActionListener(this);

        // Display first message :
        getTextAreaOut().setText("Entrer une combinaison -> ");

    }

    @Override
    public void keyReleased(KeyEvent event) {

        if (event.getKeyCode() == KeyEvent.VK_ENTER) {

            String message = "Entrer une combinaison -> ";

            valueInput = getTextAreaIn().getText();

            getTextAreaOut().insert(valueInput + " ", message.length());

            // user input cleared
            getTextAreaIn().setText("");

            getTextAreaOut().append("\n");

            inputUser = checkUserInput.inputError(valueInput, nbrDigits);

            // Initial phase
            if (step == 0) {

                machine = checkUserInput.getSolutionCombination(nbrDigits, nbrRange, valueInput);
                getSolution().setText(checkUserInput.getConvertListIntegerToString(machine));

            }

            if (inputUser) {

                nbrTours--;
                getTextAreaOut().append("Erreur de saisie, veuillez entrer un nombre positif,\nsans virgule et de " + nbrDigits + " chiffres\n");
                getTextAreaOut().append("Attention, il vous reste " + nbrTours + " tours\n");

            } else {

                // update nbrTours & nbrTest by round
                nbrTours--;
                nbrTests++;

                result.clear();
                result = (checkUserInput.getComparison(valueInput, machine));

                if (Integer.parseInt(result.get("place")) == nbrDigits) {

                    humanWinningMessageDisplay(nbrTests, checkUserInput.getDisplayResult(result));
                    getReloadButton().setVisible(true);

                } else {

                    if (nbrTours == 0) {

                        humanLoserMessageDisplay(machine.toString(), checkUserInput.getDisplayResult(result));
                        getReloadButton().setVisible(true);

                    } else {

                        humanToBeToContinuedMessageDisplay(nbrTours, checkUserInput.getDisplayResult(result));
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

        MastermindChallenger run = new MastermindChallenger(titleBackup, nbrDigitsBackup, nbrToursBackup, nbrRangeBackup, modeDevBackup);
        MastermindChallenger.super.dispose();

    }

}
