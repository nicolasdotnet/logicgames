/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.view;

import com.github.nicolasdotnet.model.CheckUserInput;
import com.github.nicolasdotnet.model.MastermindDefenseur;
import com.github.nicolasdotnet.model.RandomList;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * MastermindDefenseurLite est la classe qui représente la fenêtre de jeux
 * mastermind en mode Défenseur.
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class MastermindDefenseurLite extends JFrame {

    private int nbrCombinaison;
    private int nbrTours;
    private int nbrRange;
    private boolean modeDev;
    private MastermindDefenseur game;

    /**
     *
     */
    public MastermindDefenseurLite(int nbrCombinaison, int nbrTours, int nbrRange, boolean modeDev) {

        this.nbrCombinaison = nbrCombinaison;
        this.nbrTours = nbrTours;
        this.nbrRange = nbrRange;
        this.modeDev = modeDev;

        game = new MastermindDefenseur();

        this.setTitle("MastermindDefenseur");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(450, 450);
        this.setLocationRelativeTo(null);
        add(JScrollTextArea(), BorderLayout.CENTER);

        // param to enable Window visibility 
        this.setVisible(true);

        System.out.println("this.modeDev Lite Ini : " + this.modeDev);
        System.out.println("nbrtOurs Lite : " + nbrTours);

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
        String message = "Entrer une combinaison -> ";

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
            HashMap<String, String> result = new HashMap<String, String>();
            ArrayList<Integer> machine = new ArrayList<Integer>();
            String valueInput;
            ArrayList<String> possible;
            ArrayList<String> bestPossible;

            int[][] randomLimit;
            RandomList random = new RandomList();

            int nbrTests = 1;
            
            CheckUserInput checkUserInput = new CheckUserInput();
            Boolean inputUser;

            @Override
            public void keyReleased(KeyEvent event) {

                if (event.getKeyCode() == KeyEvent.VK_ENTER) {

                    valueInput = textAreaIn.getText();

                    textAreaOut.insert(valueInput + " ", message.length());

                    textAreaIn.setText("");

                    textAreaOut.append("\n");

                    inputUser = checkUserInput.inputError(valueInput, nbrCombinaison);
                    System.out.println("inPut : " + inputUser);

                    if (inputUser) {

                        textAreaOut.append("Erreur de saisie, veuillez entrer un nombre positif,\nsans virgule et de " + nbrCombinaison + " chiffres\n");

                    } else {

                        randomLimit = random.randomLimitIni(nbrCombinaison, nbrRange);

                        solution.setText(valueInput);
                        textAreaIn.setEditable(false);

                        do {

                            if (nbrTests == 1) {

                                nbrTours--;
                                nbrTests++;

                                possible = game.generatAllPossible(nbrCombinaison, nbrRange);
                                String machine2 = game.getCombinaison(nbrTests, possible, nbrRange);

                                System.out.println("Attac machine : " + machine2);

                                machine = game.convertStringToArrayList(machine2);

                                textAreaOut.append("Proposition de la machine : " + machine.toString() + "\n\n");

                            } else if (nbrTests == 2) {

                                nbrTours--;
                                nbrTests++;

                                bestPossible = game.generatBestPossible(possible, result, machine);
                                String machine2 = game.getCombinaison(nbrTests, bestPossible, nbrRange);

                                System.out.println("Attac machine : " + machine2);

                                machine = game.convertStringToArrayList(machine2);

                                textAreaOut.append("Proposition de la machine : " + machine.toString() + "\n\n");

                            } else {

                                nbrTours--;
                                nbrTests++;

                                bestPossible = game.generatBestPossible(bestPossible, result, machine);
                                String machine2 = game.getCombinaison(nbrTests, bestPossible, nbrRange);

                                System.out.println("Attac machine : " + machine2);

                                machine = game.convertStringToArrayList(machine2);

                                textAreaOut.append("Proposition de la machine : " + machine.toString() + "\n\n");

                            }

                            // Machine play
                            result.clear();
                            System.out.println("Machine play");
                            textAreaOut.append("La machine joue ! \n");
                            textAreaOut.append("Proposition de la machine : " + machine.toString() + "\n\n");

                            result = (game.comparaison(valueInput, machine));

                            if (Integer.parseInt(result.get("place")) == nbrCombinaison) {

                                textAreaOut.append("Désolez ! mission accomplie en " + nbrTests + " tours :)\n");
                                textAreaOut.append("Résulat : " + game.displayResult(result) + "\n");
                                textAreaOut.append("Rappel, votre combinaison était : " + valueInput + "\n");
                                textAreaOut.append("Proposition de la machine : " + machine + "\n");
                                textAreaIn.setEditable(false);

                            } else {

                                textAreaOut.append(game.displayResult(result) + "\n");

                                if (nbrTours == 0) {

                                    textAreaOut.append("GAME OVER ! la machine est Out\n");
                                    textAreaOut.append("Rappel, votre combinaison était : " + valueInput + "\n");
                                    textAreaIn.setEditable(false);

                                } else if (nbrTours == 1) {

                                    String message = "Félicitation ! La machine doit  essayer une nouvelle combinaison (dernier tour !)\n\n";

                                    textAreaOut.append(message);

                                } else {

                                    String message = "Félicitation ! La machine doit  essayer une nouvelle combinaison (Tour N°" + nbrTours + ") !\n";

                                    textAreaOut.append(message);

                                }
                            }

                        } while (Integer.parseInt(result.get("place")) != nbrCombinaison && nbrTours != 0);
                    }

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
