/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.view;

import com.github.nicolasdotnet.controller.ControllerMastermindDuel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * MastermindDuel est la classe qui représente la fenêtre de jeux mastermind en
 * mode Duel.
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class MastermindDuelLite extends JFrame {

    private int nbrDigits;
    private int nbrTours;
    private int nbrRange;
    private boolean modeDev;
    private ControllerMastermindDuel checkUserInput;

    /**
     *
     */
    public MastermindDuelLite(int nbrDigits, int nbrTours, int nbrRange, boolean modeDev) {

        this.nbrDigits = nbrDigits;
        this.nbrTours = nbrTours;
        this.nbrRange = nbrRange;
        this.modeDev = modeDev;

        checkUserInput = new ControllerMastermindDuel();

        this.setTitle("Mastermind Duel");
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

        JPanel reloadButton = reloadGame();
        modeDevPanel.add(reloadButton, BorderLayout.SOUTH);
        reloadButton.setVisible(false);

        JPanel textArea = new JPanel();

        textArea.setLayout(new GridLayout(2, 1, 0, 0));

        // Display first message : 
        String message = "Entrer une combinaison secrète que doit trouver la machine : -> ";

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
            String valueInput;
            String humain;
            String humainIni;
            HashMap<String, String> resultH = new HashMap<String, String>();
            String toStringH = "null";

            List<Integer> machine = new ArrayList<Integer>();
            List<Integer> machineIni = new ArrayList<Integer>();
            HashMap<String, String> resultM = new HashMap<String, String>();
            String machine2;
            String toStringM = "null";

            int nbrTestsM = 0;
            int nbrTestsH = 0;

            int step = 0;

            int[][] randomRange;

            List<String> possible;
            List<String> bestPossible;

            Boolean inputUser;

            @Override
            public void keyReleased(KeyEvent event) {

                valueInput = textAreaIn.getText();

                if (event.getKeyCode() == KeyEvent.VK_ENTER) {

                    textAreaOut.insert(valueInput + " ", message.length());

                    textAreaIn.setText("");

                    textAreaOut.append("\n");

                    inputUser = checkUserInput.inputError(valueInput, nbrDigits);
                    System.out.println("inPut : " + inputUser);

                    if (step == 0) {

                        if (inputUser) {
                            step--;
                            textAreaOut.append("Erreur de saisie, veuillez entrer un nombre positif,\nsans virgule et de " + nbrDigits + " chiffres\n");
                        } else {

                            resultM.put("place", "0");
                            resultM.put("present", "0");

                            humainIni = checkUserInput.getSolutionCombination(nbrDigits, nbrRange, valueInput);

                            randomRange = checkUserInput.getGenerateRandomRangeInitial(nbrDigits, nbrRange);
                            valueInput = "null";
                            machineIni = checkUserInput.getConvertStringToListInteger(checkUserInput.getSolutionCombination(nbrDigits, nbrRange, valueInput));
                            textAreaOut.append("La machine a choisit sa combinaison secréte : " + machineIni.toString() + " \n");

                            solution.setText("votre combinaison : " + humainIni + " ; La combinaison de la machine : " + machineIni);

                            textAreaOut.append("Entrez une proposition : \n");
                        }

                    }

                    if (step > 0 && Integer.parseInt(resultM.get("place")) != nbrDigits) {

                        nbrTestsM++;
                        nbrTestsH++;
                        nbrTours--;

                        if (inputUser) {

                            textAreaOut.append("Erreur de saisie, veuillez entrer un nombre positif,\nsans virgule et de " + nbrDigits + " chiffres\n");
                            nbrTours++;

                        } else {

                            humain = valueInput;
                            textAreaOut.append("Votre proposition  : " + humain.toString() + "\n");

                            if (nbrTestsM == 1) {

                                possible = checkUserInput.getGenerateAllPossible(nbrDigits, nbrRange);
                                machine2 = checkUserInput.getGetPossible(nbrTestsM, possible, nbrRange);

                                System.out.println("Attac machine : " + machine2);

                                machine = checkUserInput.getConvertStringToListInteger(machine2);

                                textAreaOut.append("La proposition de la machine : " + machine.toString() + "\n");

                            } else if (nbrTestsM == 2) {

                                nbrTours--;
                                nbrTestsM++;

                                bestPossible = checkUserInput.getGenerateBestPossible(possible, resultM, machine);
                                machine2 = checkUserInput.getGetPossible(nbrTestsM, bestPossible, nbrRange);

                                System.out.println("Attac machine : " + machine2);

                                machine = checkUserInput.getConvertStringToListInteger(machine2);

                                textAreaOut.append("La proposition de la machine : " + machine.toString() + "\n");

                            } else {

                                nbrTours--;
                                nbrTestsM++;

                                textAreaOut.append("CAS X \n");
                                bestPossible = checkUserInput.getGenerateBestPossible(bestPossible, resultM, machine);

                                machine2 = checkUserInput.getGetPossible(nbrTestsM, bestPossible, nbrRange);

                                System.out.println("Attac machine : " + machine2);

                                machine = checkUserInput.getConvertStringToListInteger(machine2);

                                textAreaOut.append("La proposition de la machine : " + machine.toString() + "\n");

                            }

                            // Humain play
                            resultH.clear();
                            textAreaOut.append("Vous jouez ! \n");
                            System.out.println("Humain play");
                            System.out.println("machineIni " + machineIni.toString());
                            System.out.println("humain " + humain.toString());

                            resultH = (checkUserInput.getComparison(humain, machineIni));

                            toStringH = checkUserInput.getDisplayResult(resultH);

                            // Machine play
                            resultM.clear();
                            System.out.println("Machine play");
                            textAreaOut.append("La machine joue ! \n");
                            textAreaOut.append("La proposition de la machine : " + machine.toString() + "\n");

                            resultM = (checkUserInput.getComparison(humainIni, machine));

                            toStringM = checkUserInput.getDisplayResult(resultM);

                            if (Integer.parseInt(resultM.get("place")) == nbrDigits && Integer.parseInt(resultH.get("place")) == nbrDigits) {

                                // Humain
                                textAreaOut.append("Félicitation ! Votre mission est accomplie en " + nbrTestsH + " tours :)\n");
                                textAreaOut.append("Votre Résulat : " + toStringH + "\n");

                                // Machine
                                textAreaOut.append("Mais désolez ! la machine a également accomplie sa mission en " + nbrTestsM + " tours ;)\n");
                                textAreaOut.append("Son Résulat : " + toStringM + "\n\n");
                                textAreaOut.append("Voulez-vous rejouer une nouvelle partie ?\n");

                                textAreaIn.setEditable(false);
                                reloadButton.setVisible(true);

                            } else if (Integer.parseInt(resultH.get("place")) == nbrDigits) {

                                System.out.println("counterH == nbrDigits");

                                // Humain
                                textAreaOut.append("Félicitation ! Votre mission est accomplie en " + nbrTestsH + " tours :)\n");
                                textAreaOut.append("Votre Résulat : " + toStringH + "\n");
                                textAreaIn.setEditable(false);

                                // Machine
                                String message = "Et encore félicitation ! La machine doit essayer une nouvelle combinaison (Tour N°" + nbrTours + ").\n";
                                textAreaOut.append("Son Résulat : " + toStringM + "\n");
                                textAreaOut.append(message);
                                textAreaOut.append("La machine joue ! \n");

                                do {

                                    nbrTours--;
                                    nbrTestsM++;
                                    System.out.println("nbrTestsM : " + nbrTestsM);

                                    if (nbrTestsM == 2) {

                                        bestPossible = checkUserInput.getGenerateBestPossible(possible, resultM, machine);
                                        machine2 = checkUserInput.getGetPossible(nbrTestsM, bestPossible, nbrRange);

                                        System.out.println("Attac machine : " + machine2);

                                        machine = checkUserInput.getConvertStringToListInteger(machine2);

                                    } else {

                                        bestPossible = checkUserInput.getGenerateBestPossible(bestPossible, resultM, machine);

                                        machine2 = checkUserInput.getGetPossible(nbrTestsM, bestPossible, nbrRange);

                                        System.out.println("Attac machine : " + machine2);

                                        machine = checkUserInput.getConvertStringToListInteger(machine2);
                                    }

                                    // Machine play
                                    resultM.clear();
                                    System.out.println("Machine play");
                                    textAreaOut.append("La machine joue ! \n");
                                    textAreaOut.append("Proposition de la machine : " + machine.toString() + "\n");

                                    resultM = (checkUserInput.getComparison(humainIni, machine));

                                    toStringM = checkUserInput.getDisplayResult(resultM);

                                    if (Integer.parseInt(resultM.get("place")) == nbrDigits) {

                                        textAreaOut.append("Désolez ! mission accomplie en " + nbrTestsM + " tours ;)\n");
                                        textAreaOut.append("Résulat : " + toStringM + "\n");

                                    } else {

                                        textAreaOut.append("Résulat : " + toStringM + "\n");

                                        if (nbrTours == 0) {

                                            textAreaOut.append("GAME OVER ! la machine est Out\n\n");
                                            textAreaOut.append("Voulez-vous rejouer une nouvelle partie ?\n");
                                            textAreaIn.setEditable(false);
                                            reloadButton.setVisible(true);

                                        } else if (nbrTours == 1) {

                                            message = "Félicitation ! La machine doit  essayer une nouvelle combinaison (Dernier tour !)\n";

                                            textAreaOut.append(message);

                                        } else {

                                            message = "Félicitation ! La machine doit  essayer une nouvelle combinaison (Tour N°" + nbrTours + ")\n";

                                            textAreaOut.append(message);
                                        }
                                    }

                                } while (Integer.parseInt(resultM.get("place")) != nbrDigits && nbrTours != 0);

                            } else if (Integer.parseInt(resultM.get("place")) == nbrDigits) {

                                //Machine 
                                textAreaOut.append("Désolez ! la machine a accomplie sa mission en " + nbrTestsM + " tours ;)\n");
                                textAreaOut.append("Son Résulat : " + toStringM + "\n");

                                // Humain
                                textAreaOut.append("Votre Résulat : " + toStringH + "\n");

                                if (nbrTours == 0) {

                                    textAreaOut.append("GAME OVER !\n");
                                    textAreaOut.append("La solution était : " + machineIni + "\n\n");
                                    textAreaOut.append("Voulez-vous rejouer une nouvelle partie ?\n");
                                    textAreaIn.setEditable(false);
                                    reloadButton.setVisible(true);

                                } else if (nbrTours == 1) {

                                    String message = "Désolez ! il faut essayer une nouvelle combinaison (Attention dernier tour !)\n";

                                    textAreaOut.append(message);

                                } else {

                                    String message = "Désolez ! il faut essayer une nouvelle combinaison (Tour N°" + nbrTours + ") !\n";

                                    textAreaOut.append(message);
                                }

                            } else if (Integer.parseInt(resultM.get("place")) != nbrDigits && Integer.parseInt(resultH.get("place")) != nbrDigits) {

                                textAreaOut.append("Personne n'a gagné !\n");
                                textAreaOut.append("Son Résulat : " + toStringM + "\n");
                                textAreaOut.append("Votre Résulat : " + toStringH + "\n");

                                if (nbrTours == 0) {

                                    textAreaOut.append("GAME OVER for all !\n");
                                    textAreaOut.append("La combinaison de la machine : " + machineIni + "\n");
                                    textAreaOut.append("Machine est Out également\n\n");
                                    textAreaOut.append("Voulez-vous rejouer une nouvelle partie ?\n");

                                    textAreaIn.setEditable(false);
                                    reloadButton.setVisible(true);

                                } else if (nbrTours == 1) {

                                    String message = "Désolez ! il faut essayer une nouvelle combinaison (Attention dernier tour !)\n";

                                    textAreaOut.append(message);

                                } else {

                                    String message = "Désolez ! il faut essayer une nouvelle combinaison (Tour N°" + nbrTours + ") !\n";

                                    textAreaOut.append(message);
                                }

                            }
                        }

                    } else if (step > 0 && Integer.parseInt(resultM.get("place")) == nbrDigits) {

                        System.out.println("Step = " + step);

                        nbrTestsM++;
                        nbrTestsH++;
                        nbrTours--;

                        if (inputUser) {
                            textAreaOut.append("Erreur de saisie, veuillez entrer un nombre positif,\nsans virgule et de " + nbrDigits + " chiffres\n");
                            textAreaOut.append("Attention, il vous reste " + nbrTours + " tours\n");
                        } else {

                            humain = valueInput;
                            textAreaOut.append("Valeur Attac Humain : " + humain.toString() + "\n");

                            resultH.clear();
                            resultH = (checkUserInput.getComparison(humain, machineIni));

                            String toString = checkUserInput.getDisplayResult(resultH);

                            if (Integer.parseInt(resultM.get("place")) == nbrDigits) {

                                textAreaOut.append("Félicitation ! mission accomplie en " + nbrTestsH + " tours :)\n");
                                textAreaOut.append("Résulat : " + toString + "\n\n");
                                textAreaOut.append("Voulez-vous rejouer une nouvelle partie ?\n");
                                textAreaIn.setEditable(false);
                                reloadButton.setVisible(true);

                            } else {

                                textAreaOut.append("Résulat : " + toString + "\n");

                                if (nbrTours == 0) {

                                    textAreaOut.append("GAME OVER !\n");
                                    textAreaOut.append("la solution était : " + machineIni + "\n\n");
                                    textAreaOut.append("Voulez-vous rejouer une nouvelle partie ?\n");
                                    textAreaIn.setEditable(false);
                                    reloadButton.setVisible(true);

                                } else if (nbrTours == 1) {

                                    String message = "Désolez ! il faut essayer une nouvelle combinaison (Attention dernier tour !)\n";

                                    textAreaOut.append(message);

                                } else {

                                    String message = "Désolez ! il faut essayer une nouvelle combinaison (Tour N°" + nbrTours + ") !\n";

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

    public JPanel reloadGame() {

        JPanel choiceButtons = new JPanel();
        choiceButtons.setLayout(new FlowLayout());

        JButton yes = new JButton("Oui");
        yes.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                MastermindDuelLite reload = new MastermindDuelLite(nbrDigits, nbrTours, nbrRange, modeDev);
                MastermindDuelLite.super.dispose();

            }
        });

        choiceButtons.add(yes);

        JButton no = new JButton("Non");
        no.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                MastermindDuelLite.super.dispose();
            }
        });

        choiceButtons.add(no);

        return choiceButtons;

    }
}
