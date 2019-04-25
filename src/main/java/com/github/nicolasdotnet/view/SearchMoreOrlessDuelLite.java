/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.view;

import com.github.nicolasdotnet.model.CheckUserInput;
import com.github.nicolasdotnet.model.RandomList;
import com.github.nicolasdotnet.model.SearchMoreOrLessDuel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * SearchMoreOrLessDuel est la classe qui représente la fenêtre de jeux
 * Recherche +/- en mode Duel.
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class SearchMoreOrlessDuelLite extends JFrame {

    private int nbrCombinaison;
    private int nbrTours;
    private int nbrRange;
    private boolean modeDev;
    private SearchMoreOrLessDuel game;

    /**
     *
     */
    public SearchMoreOrlessDuelLite(int nbrCombinaison, int nbrTours, int nbrRange, boolean modeDev) {

        this.nbrCombinaison = nbrCombinaison;
        this.nbrTours = nbrTours;
        this.nbrRange = nbrRange;
        this.modeDev = modeDev;
        game = new SearchMoreOrLessDuel();

        this.setTitle("SearchMoreOrlessDuel");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(450, 450);
        this.setLocationRelativeTo(null);
        add(JScrollTextArea(), BorderLayout.CENTER);

        // param to enable Window visibility 
        this.setVisible(true);

    }

    private JPanel JScrollTextArea() {

        JPanel modeDevPanel = new JPanel();
        modeDevPanel.setLayout(new BorderLayout());

        JLabel solution = new JLabel();
        System.out.println("modeDev Lite : " + isModeDev());
        solution.setVisible(isModeDev());
        modeDevPanel.add(solution, BorderLayout.NORTH);

        JPanel textArea = new JPanel();
        textArea.setLayout(new GridLayout(2, 1, 0, 0));

        // Display first message : 
        String message = "Entrer une combinaison secrète que doit trouver la machine : ";

        JTextArea textAreaOut = new JTextArea(message);
        textAreaOut.setEditable(false);

        // Display message and Output traitements : 
        JScrollPane JScrollTextAreaOut = new JScrollPane(textAreaOut);
        JScrollTextAreaOut.setVerticalScrollBarPolicy(JScrollTextAreaOut.VERTICAL_SCROLLBAR_ALWAYS);
        JScrollTextAreaOut.setHorizontalScrollBarPolicy(JScrollTextAreaOut.HORIZONTAL_SCROLLBAR_ALWAYS);
        textArea.add(JScrollTextAreaOut);

        // Input module : 
        JTextField textAreaIn = new JTextField("");
        textArea.add(textAreaIn);

        textAreaIn.addKeyListener(new KeyAdapter() {

            // Déclarations :
            int counterH = 0;
            int counterM = 0;
            ArrayList<Integer> humainIni = new ArrayList<Integer>();
            ArrayList<Integer> machineIni = new ArrayList<Integer>();
            ArrayList<Integer> humain = new ArrayList<Integer>();
            ArrayList<Integer> machine = new ArrayList<Integer>();
            ArrayList<String> resultM = new ArrayList<String>();
            ArrayList<String> resultH = new ArrayList<String>();

            String toStringM = "null";
            String toStringH = "null";

            int nbrTestsM = 0;
            int nbrTestsH = 0;

            int step = 0;

            String valueInput;

            int[][] randomLimit;
            RandomList random = new RandomList();

            CheckUserInput checkUserInput = new CheckUserInput();
            Boolean inputUser;

            @Override
            public void keyReleased(KeyEvent event) {

                valueInput = textAreaIn.getText();

                if (event.getKeyCode() == KeyEvent.VK_ENTER) {

                    textAreaOut.insert(valueInput + " ", message.length());

                    textAreaIn.setText("");

                    textAreaOut.append("\n");

                    inputUser = checkUserInput.inputError(valueInput, nbrCombinaison);
                    System.out.println("inPut : " + inputUser);

                    if (step == 0) {

                        if (inputUser) {
                            step--;
                            textAreaOut.append("Erreur de saisie, veuillez entrer un nombre positif,\nsans virgule et de " + nbrCombinaison + " chiffres\n");
                        } else {

                            humainIni = game.convertStringToArrayListInteger(valueInput);

                            randomLimit = random.randomLimitIni(nbrCombinaison, nbrRange);
                            machineIni = random.inputMachine(randomLimit, nbrCombinaison);
                            textAreaOut.append("La machine a choisit sa combinaison secréte \n");

                            solution.setText("votre combinaison : " + valueInput + " ; La combinaison de la machine : " + game.convertArrayListIntegerToString(machineIni));

                            textAreaOut.append("Entrez une valeur Attac Humain : \n");
                        }

                    }

                    if (step > 0 && counterM != nbrCombinaison) {

                        nbrTestsM++;
                        nbrTestsH++;
                        nbrTours--;

                        if (inputUser) {
                            textAreaOut.append("Erreur de saisie, veuillez entrer un nombre positif,\nsans virgule et de " + nbrCombinaison + " chiffres\n");
                            nbrTours++;

                        } else {

                            humain = game.convertStringToArrayListInteger(valueInput);
                            textAreaOut.append("Valeur Attac Humain : " + humain.toString() + "\n");

                            machine = random.inputMachine(randomLimit, nbrCombinaison);
                            textAreaOut.append("Valeur Attac Machine : " + machine.toString() + "\n");

                            // Humain play
                            resultH.clear();
                            textAreaOut.append("Vous jouez ! \n");
                            System.out.println("Humain play");
                            System.out.println("machineIni " + machineIni.toString());
                            System.out.println("humain " + humain.toString());

                            resultH = (game.comparaisonChallenger(nbrCombinaison, humain, machineIni, resultH));
                            counterH = game.equalCounter(resultH);

                            toStringH = game.convertArrayListToString(resultH);

                            // Machine play
                            resultM.clear();
                            System.out.println("Machine play");
                            textAreaOut.append("La machine joue ! \n");

                            resultM = (game.comparaisonDefenseur(nbrCombinaison, machine, humainIni, randomLimit, resultM));
                            counterM = game.equalCounter(resultM);

                            toStringM = game.convertArrayListToString(resultM);

                            if (counterM == nbrCombinaison && counterH == nbrCombinaison) {

                                // Humain
                                textAreaOut.append("Félicitation ! Votre mission est accomplie en " + nbrTestsH + " tours :)\n");
                                textAreaOut.append("Votre Résulat : " + toStringH + "\n");

                                // Machine
                                textAreaOut.append("Mais désolez ! la machine a également accomplie sa mission en " + nbrTestsM + " tours ;)\n");
                                textAreaOut.append("Son Résulat : " + toStringM + "\n");

                                textAreaIn.setEditable(false);

                            } else if (counterH == nbrCombinaison) {

                                System.out.println("counterH == nbrCombinaison");

                                // Humain
                                textAreaOut.append("Félicitation ! Votre mission est accomplie en " + nbrTestsH + " tours :)\n");
                                textAreaOut.append("Votre Résulat : " + toStringH + "\n");
                                textAreaIn.setEditable(false);

                                // Machine
                                String message = "Et encore félicitation ! La machine doit essayer une nouvelle combinaison (Tour N°" + nbrTours + ").\n\n";
                                textAreaOut.append("Son Résulat : " + toStringM + "\n");
                                textAreaOut.append(message);
                                textAreaOut.append("La machine joue ! \n");

                                do {

                                    nbrTours--;
                                    nbrTestsM++;

                                    machine = random.inputMachine(randomLimit, nbrCombinaison);

                                    resultM.clear();
                                    resultM = (game.comparaisonDefenseur(nbrCombinaison, machine, humain, randomLimit, resultM));
                                    counterM = game.equalCounter(resultM);

                                    toStringM = game.convertArrayListToString(resultM);

                                    if (counterM == nbrCombinaison) {

                                        textAreaOut.append("Désolez ! mission accomplie en " + nbrTestsM + " tours ;)\n");
                                        textAreaOut.append("Résulat : " + toStringM + "\n");

                                    } else {

                                        textAreaOut.append("Résulat : " + toStringM + "\n");

                                        if (nbrTours == 0) {

                                            textAreaOut.append("GAME OVER ! la machine est Out\n");
                                            textAreaIn.setEditable(false);

                                        } else if (nbrTours == 1) {

                                            message = "Félicitation ! La machine doit  essayer une nouvelle combinaison (Dernier tour !)\n\n";

                                            textAreaOut.append(message);

                                        } else {

                                            message = "Félicitation ! La machine doit  essayer une nouvelle combinaison (Tour N°" + nbrTours + ")\n\n";

                                            textAreaOut.append(message);
                                        }
                                    }

                                } while (counterM != nbrCombinaison && nbrTours != 0);

                            } else if (counterM == nbrCombinaison) {

                                //Machine 
                                textAreaOut.append("Désolez ! la machine a accomplie sa mission en " + nbrTestsM + " tours ;)\n");
                                textAreaOut.append("Son Résulat : " + toStringM + "\n");

                                // Humain
                                textAreaOut.append("Votre Résulat : " + toStringH + "\n");

                                if (nbrTours == 0) {

                                    textAreaOut.append("GAME OVER !\n");
                                    textAreaOut.append("La solution était : " + machineIni + "\n");
                                    textAreaIn.setEditable(false);

                                } else if (nbrTours == 1) {

                                    String message = "Désolez ! il faut essayer une nouvelle combinaison (Attention dernier tour !)\n\n";

                                    textAreaOut.append(message);

                                } else {

                                    String message = "Désolez ! il faut essayer une nouvelle combinaison (Tour N°" + nbrTours + ") !\n";

                                    textAreaOut.append(message);
                                }

                            } else if (counterM != nbrCombinaison && counterH != nbrCombinaison) {

                                textAreaOut.append("Personne n'a gagné !\n");
                                textAreaOut.append("Son Résulat : " + toStringM + "\n");
                                textAreaOut.append("Votre Résulat : " + toStringH + "\n");

                                if (nbrTours == 0) {

                                    textAreaOut.append("GAME OVER for all !\n");
                                    textAreaOut.append("La combinaison de la machine : " + machineIni + "\n");
                                    textAreaOut.append("Machine est Out également\n");
                                    textAreaIn.setEditable(false);

                                } else if (nbrTours == 1) {

                                    String message = "Désolez ! il faut essayer une nouvelle combinaison (Attention dernier tour !)\n\n";

                                    textAreaOut.append(message);

                                } else {

                                    String message = "Désolez ! il faut essayer une nouvelle combinaison (Tour N°" + nbrTours + ") !\n";

                                    textAreaOut.append(message);
                                }

                            }
                        }

                    } else if (step > 0 && counterM == nbrCombinaison) {

                        System.out.println("Step = " + step);

                        nbrTestsM++;
                        nbrTestsH++;
                        nbrTours--;

                        if (inputUser) {
                            textAreaOut.append("Erreur de saisie, veuillez entrer un nombre positif,\nsans virgule et de " + nbrCombinaison + " chiffres\n");
                            textAreaOut.append("Attention, il vous reste " + nbrTours + " tours\n");
                        } else {

                            humain = game.convertStringToArrayListInteger(valueInput);
                            textAreaOut.append("Valeur Attac Humain : " + humain.toString() + "\n");

                            resultH.clear();
                            resultH = (game.comparaisonChallenger(nbrCombinaison, humain, machineIni, resultH));
                            counterH = game.equalCounter(resultH);

                            String toString = game.convertArrayListToString(resultH);

                            if (counterH == nbrCombinaison) {

                                textAreaOut.append("Félicitation ! mission accomplie en " + nbrTestsH + " tours :)\n");
                                textAreaOut.append("Résulat : " + toString + "\n");
                                textAreaIn.setEditable(false);

                            } else {

                                textAreaOut.append("Résulat : " + toString + "\n");

                                if (nbrTours == 0) {

                                    textAreaOut.append("GAME OVER !\n");
                                    textAreaOut.append("la solution était : " + machineIni + "\n");
                                    textAreaIn.setEditable(false);

                                } else if (nbrTours == 1) {

                                    String message = "Désolez ! il faut essayer une nouvelle combinaison (Attention dernier tour !)\n\n";

                                    textAreaOut.append(message);

                                } else {

                                    String message = "Désolez ! il faut essayer une nouvelle combinaison (Tour N°" + nbrTours + ") !\n (" + machineIni + ")";

                                    textAreaOut.append(message);
                                }

                            }

                        }
                    }
                    step++;
                }

            }

        });
        modeDevPanel.add(textArea, BorderLayout.CENTER);

        return modeDevPanel;
    }

    public boolean isModeDev() {
        return modeDev;
    }

    public void setModeDev(boolean modeDev) {
        this.modeDev = modeDev;
    }
}
