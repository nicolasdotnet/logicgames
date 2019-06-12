/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.view;

import com.github.nicolasdotnet.controller.ControllerMastermind;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.List;

/**
 * MastermindDefender is the class that represents the mastermind game window in
 * Defender mode.
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class MastermindDefenseur extends WindowGame implements KeyListener, ActionListener {

    private int nbrDigits;
    private int nbrTours;
    private int nbrRange;
    private HashMap<String, String> result;
    private String machine;
    private String human;
    private String valueInput;
    private List<String> possible;
    private List<String> bestPossible;
    private int[][] randomRange;
    private int nbrTests;
    private int step;
    private Boolean inputUser;
    private HashMap<String, String> backup;
    private ControllerMastermind checkUserInput;

    public MastermindDefenseur(String title, int nbrDigits, int nbrTours, int nbrRange, boolean modeDev) {
        super(title, modeDev);
        this.nbrDigits = nbrDigits;
        this.nbrTours = nbrTours;
        this.nbrRange = nbrRange;

        checkUserInput = new ControllerMastermind("defenseur");
        backup = checkUserInput.getParameterBackup(title, nbrDigits, nbrTours, nbrRange, modeDev);
        result = new HashMap<String, String>();
        step = 0;
        nbrTests = 0;

        getTextAreaIn().addKeyListener(this);
        getYes().addActionListener(this);

        // Display first message :
        getTextAreaOut().setText("Entrer une combinaison secrète que doit trouver la machine -> ");

    }

    @Override
    public void keyReleased(KeyEvent event) {

        String message = "Entrer une combinaison secrète que doit trouver la machine -> ";

        if (event.getKeyCode() == KeyEvent.VK_ENTER) {

            if (step == 0) {

                valueInput = getTextAreaIn().getText();

                getTextAreaOut().insert(valueInput + " ", message.length());

                // user input cleared
                getTextAreaIn().setText("");

                getTextAreaOut().append("\n");

                inputUser = checkUserInput.inputError(valueInput, nbrDigits, nbrRange);

                // Initial phase
                if (inputUser) {

                    step--;
                    inputErrorMessage(nbrDigits, nbrRange);

                } else {

                    randomRange = checkUserInput.getGenerateRandomRangeInitial(nbrDigits, nbrRange);

                    human = checkUserInput.getSolutionCombination(nbrDigits, nbrRange, valueInput);

                    getSolution().setText("combinaison secrète -> " + human);
                    getTextAreaIn().setEditable(false);

                    step++;
                }
            }

            if (step > 0) {

                // Gaming machine
                // update nbrTours & nbrTest by round
                nbrTours--;
                nbrTests++;

                String sizure = "null";

                // Generate Possible
                switch (nbrTests) {
                    case 1: {

                        possible = checkUserInput.getGenerateAllPossible(nbrDigits, nbrRange);
                        String machine2 = checkUserInput.getGetPossible(nbrTests, possible, nbrRange, nbrDigits, sizure);
                        machine = machine2;

                        getTextAreaOut().append("\nLa proposition de la machine est : " + machine + "\n\n");
                        break;
                    }
                    case 2: {

                        bestPossible = checkUserInput.getGenerateBestPossible(possible, result, machine);
                        String machine2 = checkUserInput.getGetPossible(nbrTests, bestPossible, nbrRange, nbrDigits, sizure);
                        machine = machine2;

                        getTextAreaOut().append("\nLa proposition de la machine est : " + machine + "\n\n");
                        break;
                    }
                    default: {

                        bestPossible = checkUserInput.getGenerateBestPossible(bestPossible, result, machine);
                        String machine2 = checkUserInput.getGetPossible(nbrTests, bestPossible, nbrRange, nbrDigits, sizure);
                        machine = machine2;

                        getTextAreaOut().append("\nLa proposition de la machine est : " + machine + "\n\n");
                        break;
                    }
                }

                // Machine play
                result.clear();
                result = (checkUserInput.getComparison(human, machine));

                if (Integer.parseInt(result.get("place")) == nbrDigits) {

                    machineWinningMessageDisplay(nbrTests, checkUserInput.getDisplayResult(result), human);
                    getReloadButton().setVisible(true);

                } else {

                    if (nbrTours == 0) {

                        machineLoserMessageDisplay(human, checkUserInput.getDisplayResult(result));
                        getReloadButton().setVisible(true);

                    } else {

                        machineToBeToContinuedMessageDisplay(nbrTours, checkUserInput.getDisplayResult(result));
                        getTextAreaOut().append("\nTaper sur la touche Entrée pour continuer !!\n");
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

        MastermindDefenseur run = new MastermindDefenseur(titleBackup, nbrDigitsBackup, nbrToursBackup, nbrRangeBackup, modeDevBackup);
        MastermindDefenseur.super.dispose();

    }

}
