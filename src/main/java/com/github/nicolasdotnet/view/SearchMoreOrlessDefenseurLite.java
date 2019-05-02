/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.view;

import com.github.nicolasdotnet.model.CheckUserInput;
import com.github.nicolasdotnet.model.RandomList;
import com.github.nicolasdotnet.model.SearchMoreOrLessDefenseur;
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
 * SearchMoreOrLessDefenseurLite est la classe qui représente la fenêtre de jeux
 * Recherche +/- en mode Défenseur.
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class SearchMoreOrlessDefenseurLite extends JFrame {

    private int nbrCombinaison;
    private int nbrTours;
    private int nbrRange;
    private boolean modeDev;
    private SearchMoreOrLessDefenseur game;

    /**
     *
     */
    public SearchMoreOrlessDefenseurLite(int nbrCombinaison, int nbrTours, int nbrRange, boolean modeDev) {

        this.nbrCombinaison = nbrCombinaison;
        this.nbrTours = nbrTours;
        this.nbrRange = nbrRange;
        this.modeDev = modeDev;

        game = new SearchMoreOrLessDefenseur();

        this.setTitle("SearchMoreOrlessDefenseur");
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
            int counter = 0;
            ArrayList<Integer> humain = new ArrayList<Integer>();
            ArrayList<String> result = new ArrayList<String>();
            ArrayList<Integer> machine = new ArrayList<Integer>();

            String valueInput;
            int nbrTests = 0;
            int step = 0;

            int[][] randomLimit;
            RandomList random = new RandomList();

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
                        humain = game.convertStringToArrayListInteger(valueInput);
                        solution.setText(valueInput);
                        textAreaIn.setEditable(false);

                    }

                    do {

                        nbrTours--;
                        nbrTests++;

                        counter = 0;
                        machine = random.inputMachine(randomLimit, nbrCombinaison);
                        textAreaOut.append("Proposition de la machine : " + machine.toString() + "\n\n");

                        result.clear();
                        result = (game.comparaison(nbrCombinaison, machine, humain, result));
                        counter = game.equalCounter(game.convertArrayListToString(result));
                        
                        randomLimit = game.generatNewRandomLimit(result, machine, randomLimit);

                        String toString = game.convertArrayListToString(result);

                        if (counter == nbrCombinaison) {

                            textAreaOut.append("Désolez ! mission accomplie en " + nbrTests + " tours ;)\n");
                            textAreaOut.append("Résulat : " + toString + "\n");
                            textAreaOut.append("Rappel, votre combinaison était : " + humain + "\n");
                            textAreaOut.append("Proposition de la machine : " + machine + "\n");
                            textAreaIn.setEditable(false);

                        } else {

                            textAreaOut.append("Résulat : " + toString + "\n");

                            if (nbrTours == 0) {

                                textAreaOut.append("GAME OVER ! la machine est Out\n");
                                textAreaOut.append("Rappel, votre combinaison était : " + humain + "\n");
                                textAreaIn.setEditable(false);

                            } else if (nbrTours == 1) {

                                String message = "Félicitation ! La machine doit  essayer une nouvelle combinaison (Dernier tour !)\n\n";

                                textAreaOut.append(message);

                            } else {

                                String message = "Félicitation ! La machine doit  essayer une nouvelle combinaison (Tour N°" + nbrTours + ")\n\n";

                                textAreaOut.append(message);
                            }
                        }

                    } while (counter != nbrCombinaison && nbrTours != 0);
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
