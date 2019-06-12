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
 * MastermindDuel is the class that represents the mastermind game window in
 * Duel mode.
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class MastermindDuel extends WindowGame implements KeyListener, ActionListener, Runnable {

    private int nbrDigits;
    private int nbrTours;
    private int nbrRange;
    private String valueInput;
    private String human;
    private String humanIni;
    private HashMap<String, String> resultH;
    private String toStringH;
    private String machine;
    private String machineIni;
    private HashMap<String, String> resultM;
    private String machine2;
    private String toStringM;
    private int nbrTestsM;
    private int nbrTestsH;
    private int step;
    private int[][] randomRange;
    private List<String> possible;
    private List<String> bestPossible;
    private Boolean inputUser;
    private HashMap<String, String> backup;
    private ControllerMastermind checkUserInput;

    /**
     *
     * run method for a new thread
     */
    @Override
    public void run() {

        possible = checkUserInput.getGenerateAllPossible(nbrDigits, nbrRange);
    }

    public MastermindDuel(String title, int nbrDigits, int nbrTours, int nbrRange, boolean modeDev) {
        super(title, modeDev);
        checkUserInput = new ControllerMastermind("duel");
        (new Thread(this)).start();
        this.nbrDigits = nbrDigits;
        this.nbrTours = nbrTours;
        this.nbrRange = nbrRange;

        backup = checkUserInput.getParameterBackup(title, nbrDigits, nbrTours, nbrRange, modeDev);
        resultH = new HashMap<String, String>();
        toStringH = "null";
        resultM = new HashMap<String, String>();
        resultM.put("place", "0");
        resultM.put("present", "0");
        toStringM = "null";
        nbrTestsM = 0;
        nbrTestsH = 0;
        step = 0;

        getTextAreaIn().addKeyListener(this);
        getYes().addActionListener(this);

        // Display first message :
        getTextAreaOut().setText("Entrer une combinaison secrète que doit trouver la machine -> ");

    }

    @Override
    public void keyReleased(KeyEvent event) {

        String message = "Entrer une combinaison secrète que doit trouver la machine -> ";

        if (event.getKeyCode() == KeyEvent.VK_ENTER) {

            valueInput = getTextAreaIn().getText();

            getTextAreaOut().insert(valueInput + " ", message.length());

            getTextAreaIn().setText("");

            getTextAreaOut().append("\n");

            inputUser = checkUserInput.inputError(valueInput, nbrDigits, nbrRange);

            // Initial phase
            if (step == 0) {

                if (inputUser) {

                    step--;
                    inputErrorMessage(nbrDigits, nbrRange);

                } else {

                    humanIni = checkUserInput.getSolutionCombination(nbrDigits, nbrRange, valueInput);

                    randomRange = checkUserInput.getGenerateRandomRangeInitial(nbrDigits, nbrRange);
                    valueInput = "null";
                    machineIni = checkUserInput.getSolutionCombination(nbrDigits, nbrRange, valueInput);

                    getSolution().setText("Votre combinaison : " + humanIni + " ; La combinaison de la machine : " + machineIni);

                    getTextAreaOut().append("La machine a choisit sa combinaison secréte !\n");
                    getTextAreaOut().append("\nEntrez une proposition : \n");
                }

            }

            // Game phase
            if (step > 0 && Integer.parseInt(resultM.get("place")) != nbrDigits) {

                // update nbrTours & nbrTest machine and human by round
                nbrTestsM++;
                nbrTestsH++;
                nbrTours--;

                if (inputUser) {

                    inputErrorMessage(nbrDigits, nbrRange);

                    // cancel update
                    nbrTours++;
                    nbrTestsM--;
                    nbrTestsH--;

                } else {

                    String sizureFake = "null";
                    List<String> possibleFake = null;

                    human = checkUserInput.getGetPossible(nbrTestsM, possibleFake, nbrRange, nbrDigits, valueInput);
                    getTextAreaOut().append("Votre proposition  : " + human + "\n");

                    // Generate possible for the machine
                    switch (nbrTestsM) {
                        case 1:

                            testGeneratePossible(bestPossible);
                            machine2 = checkUserInput.getGetPossible(nbrTestsM, possible, nbrRange, nbrDigits, sizureFake);
                            machine = machine2;
                            break;
                        case 2:

                            testGeneratePossible(bestPossible);
                            machine2 = checkUserInput.getGetPossible(nbrTestsM, bestPossible, nbrRange, nbrDigits, sizureFake);
                            machine = machine2;
                            break;
                        default:

                            testGeneratePossible(bestPossible);
                            bestPossible = checkUserInput.getGenerateBestPossible(bestPossible, resultM, machine);
                            machine2 = checkUserInput.getGetPossible(nbrTestsM, bestPossible, nbrRange, nbrDigits, sizureFake);
                            machine = machine2;
                            break;
                    }

                    // Human play
                    resultH.clear();
                    resultH = (checkUserInput.getComparison(human, machineIni));
                    toStringH = checkUserInput.getDisplayResult(resultH);

                    // Machine play
                    getTextAreaOut().append("La proposition de la machine : " + machine + "\n");
                    resultM.clear();
                    resultM = (checkUserInput.getComparison(humanIni, machine));
                    toStringM = checkUserInput.getDisplayResult(resultM);

                    // machine and human winners
                    if (Integer.parseInt(resultM.get("place")) == nbrDigits && Integer.parseInt(resultH.get("place")) == nbrDigits) {

                        allWinning(nbrTestsH, toStringH, nbrTestsM, toStringM);
                        getTextAreaIn().setEditable(false);
                        getReloadButton().setVisible(true);

                    } else if (Integer.parseInt(resultH.get("place")) == nbrDigits) {

                        // Human only winner
                        // Human
                        getTextAreaOut().append("Félicitation ! Votre mission est accomplie en " + nbrTestsH + " tours :)\n");
                        getTextAreaOut().append("Votre résultat : " + toStringH + "\n");
                        getTextAreaIn().setEditable(false);

                        // Machine
                        message = "Et encore félicitation ! La machine doit essayer une nouvelle combinaison (Tour N°" + nbrTours + ").\n";
                        getTextAreaOut().append("Son résultat : " + toStringM + "\n");
                        getTextAreaOut().append(message);

                        // Machine only play
                        do {

                            // Update nbrTours & nbrTest machine by round
                            nbrTours--;
                            nbrTestsM++;

                            // Generate possible for the machine
                            if (nbrTestsM == 2) {

                                bestPossible = checkUserInput.getGenerateBestPossible(possible, resultM, machine);
                                machine2 = checkUserInput.getGetPossible(nbrTestsM, bestPossible, nbrRange, nbrDigits, sizureFake);
                                machine = machine2;

                            } else {

                                bestPossible = checkUserInput.getGenerateBestPossible(bestPossible, resultM, machine);
                                machine2 = checkUserInput.getGetPossible(nbrTestsM, bestPossible, nbrRange, nbrDigits, sizureFake);
                                machine = machine2;
                            }

                            // Machine play
                            getTextAreaOut().append("\nLa machine joue ! \n");
                            getTextAreaOut().append("La proposition de la machine : " + machine + "\n");
                            resultM.clear();
                            resultM = (checkUserInput.getComparison(humanIni, machine));
                            toStringM = checkUserInput.getDisplayResult(resultM);

                            if (Integer.parseInt(resultM.get("place")) == nbrDigits) {

                                machineWinningMessageDisplay(nbrTestsM, toStringM, humanIni);
                                getReloadButton().setVisible(true);

                            } else {

                                if (nbrTours == 0) {

                                    machineLoserMessageDisplay(humanIni, toStringM);
                                    getReloadButton().setVisible(true);

                                } else {

                                    machineToBeToContinuedMessageDisplay(nbrTours, toStringM);

                                }
                            }

                        } while (Integer.parseInt(resultM.get("place")) != nbrDigits && nbrTours != 0);

                    } else if (Integer.parseInt(resultM.get("place")) == nbrDigits) {

                        // Machine only winner 
                        //Machine                         
                        getTextAreaOut().append("Désolez ! la machine a accomplie sa mission en " + nbrTestsM + " tours ;)\n");
                        getTextAreaOut().append("Son résultat : " + toStringM + "\n");

                        // Human
                        if (nbrTours == 0) {

                            humanLoserMessageDisplay(machineIni, toStringH);
                            getReloadButton().setVisible(true);

                        } else {

                            humanToBeToContinuedMessageDisplay(nbrTours, toStringH);
                        }

                    } else if (Integer.parseInt(resultM.get("place")) != nbrDigits && Integer.parseInt(resultH.get("place")) != nbrDigits) {

                        // machine and human losers
                        if (nbrTours == 0) {

                            allLoser(toStringM, toStringH, machineIni, humanIni);
                            getTextAreaIn().setEditable(false);
                            getReloadButton().setVisible(true);

                        } else {

                            if (nbrTestsM == 1) {
                                Thread thread = new Thread() {

                                    @Override
                                    public void run() {

                                        bestPossible = checkUserInput.getGenerateBestPossible(possible, resultM, machine);

                                    }
                                };

                                thread.start();
                            }

                            getTextAreaOut().append("Personne n'a gagné !\n");
                            getTextAreaOut().append("Son résultat : " + toStringM + "\n");
                            humanToBeToContinuedMessageDisplay(nbrTours, toStringH);
                        }

                    }
                }

            } else if (step > 0 && Integer.parseInt(resultM.get("place")) == nbrDigits) {

                // Human only play
                // Update nbrTours & nbrTest human by round
                nbrTestsH++;
                nbrTours--;

                if (inputUser) {

                    inputErrorMessage(nbrDigits, nbrRange);

                    // cancel update
                    nbrTours++;
                    nbrTestsH--;

                } else {

                    possible = null;

                    human = checkUserInput.getGetPossible(nbrTestsM, possible, nbrRange, nbrDigits, valueInput);
                    getTextAreaOut().append("Votre proposition : " + human + "\n");

                    resultH.clear();
                    resultH = (checkUserInput.getComparison(human, machineIni));
                    String toString = checkUserInput.getDisplayResult(resultH);

                    if (Integer.parseInt(resultM.get("place")) == nbrDigits) {

                        humanWinningMessageDisplay(nbrTestsH, toString);
                        getReloadButton().setVisible(true);

                    } else {

                        if (nbrTours == 0) {

                            humanLoserMessageDisplay(machineIni, toString);
                            getReloadButton().setVisible(true);

                        } else {

                            humanToBeToContinuedMessageDisplay(nbrTours, toString);
                        }

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

        MastermindDuel run = new MastermindDuel(titleBackup, nbrDigitsBackup, nbrToursBackup, nbrRangeBackup, modeDevBackup);
        MastermindDuel.super.dispose();
    }

}
