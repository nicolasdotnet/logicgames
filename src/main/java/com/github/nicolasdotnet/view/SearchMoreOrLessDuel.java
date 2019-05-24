/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.view;

import com.github.nicolasdotnet.controller.ControllerSearchMoreOrLessDuel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * SearchMoreOrLessDuel est la classe qui représente la fenêtre de jeux
 * Recherche +/- en mode Duel.
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
    private List<Integer> humanIni;
    private List<Integer> machineIni;
    private List<Integer> human;
    private List<Integer> machine;
    private List<String> resultM;
    private List<String> resultH;
    private String toStringM;
    private String toStringH;
    private int nbrTestsM;
    private int nbrTestsH;
    private int step;
    private String valueInput;
    private int[][] randomRange;
    private Boolean inputUser;
    private HashMap<String, String> backup;
    private ControllerSearchMoreOrLessDuel checkUserInput;

    public SearchMoreOrLessDuel(String title, int nbrDigits, int nbrTours, int nbrRange, boolean modeDev) {
        super(title, modeDev);
        this.nbrDigits = nbrDigits;
        this.nbrTours = nbrTours;
        this.nbrRange = nbrRange;

        checkUserInput = new ControllerSearchMoreOrLessDuel();
        backup = checkUserInput.getParameterBackup(title, nbrDigits, nbrTours, nbrRange, modeDev);

        counterH = 0;
        counterM = 0;
        humanIni = new ArrayList<Integer>();
        machineIni = new ArrayList<Integer>();
        human = new ArrayList<Integer>();
        machine = new ArrayList<Integer>();
        resultM = new ArrayList<String>();
        resultH = new ArrayList<String>();
        toStringM = "null";
        toStringH = "null";
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

            inputUser = checkUserInput.inputError(valueInput, nbrDigits);

            // Initial phase
            if (step == 0) {

                if (inputUser) {

                    step--;
                    getTextAreaOut().append("Erreur de saisie, veuillez entrer un nombre positif,\nsans virgule et de " + nbrDigits + " chiffres\n");

                } else {

                    humanIni = checkUserInput.getConvertStringToListInteger(checkUserInput.getSolutionCombination(nbrDigits, nbrRange, valueInput));

                    randomRange = checkUserInput.getGenerateRandomRangeInitial(nbrDigits, nbrRange);
                    valueInput = "null";
                    machineIni = checkUserInput.getConvertStringToListInteger(checkUserInput.getSolutionCombination(nbrDigits, nbrRange, valueInput));

                    getSolution().setText("votre combinaison : " + checkUserInput.getConvertListIntegerToString(humanIni) + " ; La combinaison de la machine : " + checkUserInput.getConvertListIntegerToString(machineIni));

                    getTextAreaOut().append("La machine a choisit sa combinaison secréte \n");
                    getTextAreaOut().append("Entrez une proposition : \n");
                }

            }

            // Game phase
            if (step > 0 && counterM != nbrDigits) {

                // update nbrTours & nbrTest machine and human by round
                nbrTestsM++;
                nbrTestsH++;
                nbrTours--;

                if (inputUser) {
                    getTextAreaOut().append("Erreur de saisie, veuillez entrer un nombre positif,\nsans virgule et de " + nbrDigits + " chiffres\n");

                    // cancel update
                    nbrTours++;

                } else {

                    human = checkUserInput.getConvertStringToListInteger(valueInput);
                    getTextAreaOut().append("Votre proposition : " + human.toString() + "\n");

                    machine = checkUserInput.getGeneratePossible(randomRange, nbrDigits);
                    getTextAreaOut().append("La proposition de la machine : " + machine.toString() + "\n");

                    // Human play
                    resultH.clear();
                    getTextAreaOut().append("Vous jouez ! \n");

                    resultH = (checkUserInput.getComparison(nbrDigits, human, machineIni, resultH));
                    counterH = checkUserInput.getEqualCounter(checkUserInput.getConvertListToString(resultH));

                    toStringH = checkUserInput.getConvertListToString(resultH);

                    // Machine play
                    resultM.clear();
                    getTextAreaOut().append("La machine joue ! \n");

                    resultM = (checkUserInput.getComparison(nbrDigits, machine, humanIni, resultM));
                    counterM = checkUserInput.getEqualCounter(checkUserInput.getConvertListToString(resultM));

                    randomRange = checkUserInput.getGenerateRandomRangeNew(resultM, machine, randomRange);

                    toStringM = checkUserInput.getConvertListToString(resultM);

                    // machine and human winners
                    if (counterM == nbrDigits && counterH == nbrDigits) {

                        // Human
                        getTextAreaOut().append("Félicitation ! Votre mission est accomplie en " + nbrTestsH + " tours :)\n");
                        getTextAreaOut().append("Votre Résulat : " + toStringH + "\n");

                        // Machine
                        getTextAreaOut().append("Mais désolez ! la machine a également accomplie sa mission en " + nbrTestsM + " tours ;)\n");
                        getTextAreaOut().append("Son Résulat : " + toStringM + "\n\n");
                        getTextAreaOut().append("Voulez-vous rejouer une nouvelle partie ?\n");

                        getTextAreaIn().setEditable(false);
                        getReloadButton().setVisible(true);

                    } else if (counterH == nbrDigits) {

                        // Human only winner
                        
                        // Human
                        getTextAreaOut().append("Félicitation ! Votre mission est accomplie en " + nbrTestsH + " tours.\n");
                        getTextAreaOut().append("Votre Résulat : " + toStringH + "\n");
                        getTextAreaIn().setEditable(false);

                        // Machine
                        message = "Et encore félicitation ! La machine doit essayer une nouvelle combinaison (Tour N°" + nbrTours + ").\n\n";
                        getTextAreaOut().append("Son Résulat : " + toStringM + "\n");
                        getTextAreaOut().append(message);
                        getTextAreaOut().append("La machine joue ! \n");

                        // Machine play
                        do {

                            // Update nbrTours & nbrTest machine by round
                            nbrTours--;
                            nbrTestsM++;

                            machine = checkUserInput.getGeneratePossible(randomRange, nbrDigits);
                            getTextAreaOut().append("La proposition de la machine : " + machine.toString() + "\n");

                            resultM.clear();
                            resultM = (checkUserInput.getComparison(nbrDigits, machine, humanIni, resultM));
                            counterM = checkUserInput.getEqualCounter(checkUserInput.getConvertListToString(resultM));

                            randomRange = checkUserInput.getGenerateRandomRangeNew(resultM, machine, randomRange);

                            toStringM = checkUserInput.getConvertListToString(resultM);

                            if (counterM == nbrDigits) {

                                machineWinningMessageDisplay(nbrTestsM, toStringM, humanIni.toString(), machine.toString());
                                getReloadButton().setVisible(true);

                            } else {

                                if (nbrTours == 0) {

                                    machineLoserMessageDisplay(humanIni.toString(), toStringM, machine.toString());
                                    getReloadButton().setVisible(true);

                                } else {

                                    machineToBeToContinuedMessageDisplay(nbrTours, toStringM);
                                }
                            }

                        } while (counterM != nbrDigits && nbrTours != 0);

                    } else if (counterM == nbrDigits) {

                        // Machine only winner 
                        
                        //Machine 
                        getTextAreaOut().append("Désolez ! la machine a accomplie sa mission en " + nbrTestsM + " tours ;)\n");
                        getTextAreaOut().append("Son Résulat : " + toStringM + "\n");

                        if (nbrTours == 0) {

                            humanLoserMessageDisplay(machineIni.toString(), toStringH);
                            getReloadButton().setVisible(true);

                        } else {

                            humanToBeToContinuedMessageDisplay(nbrTours, toStringH);
                        }

                    } else if (counterM != nbrDigits && counterH != nbrDigits) {

                        // machine and human losers
                        
                        getTextAreaOut().append("Personne n'a gagné !\n");
                        getTextAreaOut().append("Son Résulat : " + toStringM + "\n");

                        if (nbrTours == 0) {

                            // revoir le message : ajout votre combi était : ....
//                            getTextAreaOut().append("GAME OVER for all !\n");
                            getTextAreaOut().append("La combinaison de la machine : " + machineIni + "\n");
//                            getTextAreaOut().append("Machine est Out également\n\n");
                            getTextAreaOut().append("Voulez-vous rejouer une nouvelle partie ?\n");
                            getTextAreaIn().setEditable(false);
                            getReloadButton().setVisible(true);

                        } else {

                            humanToBeToContinuedMessageDisplay(nbrTours, toStringH);
                        }

                    }
                }

            } else if (step > 0 && counterM == nbrDigits) {
                
                // Machine only winner

                // Update nbrTours & nbrTest human by round
                nbrTestsH++;
                nbrTours--;

                if (inputUser) {
                    getTextAreaOut().append("Erreur de saisie, veuillez entrer un nombre positif,\nsans virgule et de " + nbrDigits + " chiffres\n");
                    getTextAreaOut().append("Attention, il vous reste " + nbrTours + " tours\n");

                } else {

                    human = checkUserInput.getConvertStringToListInteger(valueInput);
                    getTextAreaOut().append("Votre proposition : " + human.toString() + "\n");

                    resultH.clear();
                    resultH = (checkUserInput.getComparison(nbrDigits, human, machineIni, resultH));
                    counterH = checkUserInput.getEqualCounter(checkUserInput.getConvertListToString(resultH));

                    String toString = checkUserInput.getConvertListToString(resultH);

                    if (counterH == nbrDigits) {

                        humanWinningMessageDisplay(nbrTestsH, toString);
                        getReloadButton().setVisible(true);

                    } else {

                        if (nbrTours == 0) {

                            humanLoserMessageDisplay(machineIni.toString(), toString);
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

        SearchMoreOrLessDuel run = new SearchMoreOrLessDuel(titleBackup, nbrDigitsBackup, nbrToursBackup, nbrRangeBackup, modeDevBackup);
        SearchMoreOrLessDuel.super.dispose();
    }

}
