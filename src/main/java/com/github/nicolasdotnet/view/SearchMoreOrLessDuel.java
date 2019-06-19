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
 * SearchMoreOrLessDuel is the class that represents the Search +/- game window
 * in Duel mode.
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class SearchMoreOrLessDuel extends WindowGame implements KeyListener, ActionListener {

    private int nbrDigits;
    private int nbrTours;
    private int nbrRange;
    private int counterH;
    private int counterM;
    private String humanIni;
    private String machineIni;
    private String human;
    private String machine;
    private String resultM;
    private String resultH;
    private int nbrTestsM;
    private int nbrTestsH;
    private int step;
    private String valueInput;
    private int[][] randomRange;
    private Boolean inputUser;
    private HashMap<String, String> backup;
    private ControllerSearchMoreOrLess checkUserInput;

    public SearchMoreOrLessDuel(String title, int nbrDigits, int nbrTours, int nbrRange, boolean modeDev) {
        super(title, modeDev);
        this.nbrDigits = nbrDigits;
        this.nbrTours = nbrTours;
        this.nbrRange = nbrRange;

        checkUserInput = new ControllerSearchMoreOrLess("duel");
        backup = checkUserInput.getParameterBackup(title, nbrDigits, nbrTours, nbrRange, modeDev);

        counterH = 0;
        counterM = 0;

        nbrTestsM = 0;
        nbrTestsH = 0;
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

            inputUser = checkUserInput.inputError(valueInput, nbrDigits, nbrRange);

            // Initial phase
            if (step == 0) {

                if (inputUser) {

                    step--;
                    inputErrorMessage(nbrDigits, nbrRange);

                } else {

                    humanIni = checkUserInput.getSolutionCombination(nbrDigits, nbrRange, valueInput);

                    String sizureFake = "null";
                    randomRange = checkUserInput.getGenerateRandomRangeInitial(nbrDigits, nbrRange);
                    machineIni = checkUserInput.getSolutionCombination(nbrDigits, nbrRange, sizureFake);

                    getSolution().setText("Votre combinaison : " + humanIni + " ; La combinaison de la machine : " + machineIni);

                    getTextAreaOut().append("La machine a choisit sa combinaison secréte !\n");
                    getTextAreaOut().append("\nEntrez une proposition : \n");
                }

            }

            // Game phase
            if (step > 0 && counterM != nbrDigits) {

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

                    human = checkUserInput.getGetPossible(randomRange, nbrDigits, resultM, machine, valueInput);
                    getTextAreaOut().append("Votre proposition : " + human + "\n");

                    machine = checkUserInput.getGetPossible(randomRange, nbrDigits, resultM, machine, sizureFake);
                    getTextAreaOut().append("La proposition de la machine : " + machine + "\n");

                    // Human play
                    resultH = "";

                    resultH = (checkUserInput.getComparison(nbrDigits, human, machineIni, resultH));
                    counterH = checkUserInput.getEqualCounter(resultH);

                    // Machine play
                    resultM = "";

                    resultM = (checkUserInput.getComparison(nbrDigits, machine, humanIni, resultM));
                    counterM = checkUserInput.getEqualCounter(resultM);

                    randomRange = checkUserInput.getGenerateRandomRangeNew(resultM, machine, randomRange);

                    if (counterM == nbrDigits && counterH == nbrDigits) {

                        // machine and human winners
                        allWinning(nbrTestsH, resultH, nbrTestsM, resultM);
                        getTextAreaIn().setEditable(false);
                        getReloadButton().setVisible(true);

                    } else if (counterH == nbrDigits) {

                        // Human only winner
                        // Human
                        getTextAreaOut().append("Félicitation ! Votre mission est accomplie en " + nbrTestsH + " tours.\n");
                        getTextAreaOut().append("Votre résultat : " + resultH + "\n");
                        getTextAreaIn().setEditable(false);

                        // Machine
                        message = "Et encore félicitation ! La machine doit essayer une nouvelle combinaison (Tour N°" + nbrTours + ").\n\n";
                        getTextAreaOut().append("Son résultat : " + resultM + "\n");
                        getTextAreaOut().append(message);

                        // Machine only play
                        do {

                            // Update nbrTours & nbrTest machine by round
                            nbrTours--;
                            nbrTestsM++;

                            machine = checkUserInput.getGetPossible(randomRange, nbrDigits, resultM, machine, sizureFake);
                            getTextAreaOut().append("\nLa machine joue ! \n");
                            getTextAreaOut().append("La proposition de la machine : " + machine + "\n");

                            resultM = "";
                            resultM = (checkUserInput.getComparison(nbrDigits, machine, humanIni, resultM));
                            counterM = checkUserInput.getEqualCounter(resultM);

                            randomRange = checkUserInput.getGenerateRandomRangeNew(resultM, machine, randomRange);

                            if (counterM == nbrDigits) {

                                machineWinningMessageDisplay(nbrTestsM, resultM, humanIni);
                                getReloadButton().setVisible(true);

                            } else {

                                if (nbrTours == 0) {

                                    machineLoserMessageDisplay(humanIni, resultM);
                                    getReloadButton().setVisible(true);

                                } else {

                                    machineToBeToContinuedMessageDisplay(nbrTours, resultM);
                                }
                            }

                        } while (counterM != nbrDigits && nbrTours != 0);

                    } else if (counterM == nbrDigits) {

                        // Machine only winner 
                        //Machine 
                        getTextAreaOut().append("Désolez ! la machine a accomplie sa mission en " + nbrTestsM + " tours ;)\n");
                        getTextAreaOut().append("Son résultat : " + resultM + "\n");

                        // Human
                        if (nbrTours == 0) {

                            humanLoserMessageDisplay(machineIni, resultH);
                            getReloadButton().setVisible(true);

                        } else {

                            humanToBeToContinuedMessageDisplay(nbrTours, resultH);
                        }

                    } else if (counterM != nbrDigits && counterH != nbrDigits) {

                        // machine and human losers
                        if (nbrTours == 0) {

                            allLoser(resultM, resultH, humanIni, humanIni);
                            getTextAreaIn().setEditable(false);
                            getReloadButton().setVisible(true);

                        } else {

                            getTextAreaOut().append("Personne n'a gagné !\n");
                            getTextAreaOut().append("Son résultat : " + resultM + "\n");
                            humanToBeToContinuedMessageDisplay(nbrTours, resultH);
                        }

                    }
                }

            } else if (step > 0 && counterM == nbrDigits) {

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

                    human = checkUserInput.getGetPossible(randomRange, nbrDigits, resultM, machine, valueInput);
                    getTextAreaOut().append("Votre proposition : " + human + "\n");

                    resultH = "";
                    resultH = (checkUserInput.getComparison(nbrDigits, human, machineIni, resultH));
                    counterH = checkUserInput.getEqualCounter(resultH);

                    if (counterH == nbrDigits) {

                        humanWinningMessageDisplay(nbrTestsH, resultH);
                        getReloadButton().setVisible(true);

                    } else {

                        if (nbrTours == 0) {

                            humanLoserMessageDisplay(machineIni, resultH);
                            getReloadButton().setVisible(true);

                        } else {

                            humanToBeToContinuedMessageDisplay(nbrTours, resultH);

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

        SearchMoreOrLessDuel run = new SearchMoreOrLessDuel(titleBackup, nbrDigitsBackup, nbrToursBackup, nbrRangeBackup, modeDevBackup);
        SearchMoreOrLessDuel.super.dispose();
    }

}
