/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.view;

import com.github.nicolasdotnet.model.MastermindChallenger;
import com.github.nicolasdotnet.model.RandomList;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * SearchMoreOrLessChallengerLite est la classe qui représente la fenêtre de
 * jeux Recherche +/- en mode Challenger.
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class MastermindChallengerLite extends JFrame {

    private int nbrCombinaison;

    private int nbrTours;

    private int nbrRange;

    private MastermindChallenger game;

    private ArrayList<Integer> machine;

    private RandomList random;

    /**
     *
     */
    public MastermindChallengerLite(int nbrCombinaison, int nbrTours, int nbrRange) {

        this.setTitle("MastermindChallenger");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(450, 450);
        this.setLocationRelativeTo(null);
        add(JScrollTextArea(), BorderLayout.CENTER);

        this.nbrCombinaison = nbrCombinaison;
        this.nbrTours = nbrTours;
        this.nbrRange = nbrRange;
        System.out.println("nbrtOurs Lite : " + nbrTours);
        game = new MastermindChallenger();
        random = new RandomList();
        machine = new ArrayList<Integer>();
        int[][] randomLimit;

        // param to enable Window visibility 
        this.setVisible(true);

        randomLimit = random.randomLimitIni(nbrCombinaison, nbrRange);
        machine = random.inputMachine(randomLimit, nbrCombinaison);

    }

    private JPanel JScrollTextArea() {

        JPanel textArea = new JPanel();

        textArea.setLayout(new GridLayout(2, 1, 0, 0));

        // Display first message : 
        String message = "Entrer une combinaison -> ";

        JTextArea textAreaOut = new JTextArea(message);
        textAreaOut.setEditable(false);

        // Display message and Output traitements : 
        JScrollPane JScrollTextAreaOut = new JScrollPane(textAreaOut);
        JScrollTextAreaOut.setVerticalScrollBarPolicy(JScrollTextAreaOut.VERTICAL_SCROLLBAR_ALWAYS);
        JScrollTextAreaOut.setHorizontalScrollBarPolicy(JScrollTextAreaOut.HORIZONTAL_SCROLLBAR_NEVER);
        textArea.add(JScrollTextAreaOut);

        // Input module : 
        JTextField textAreaIn = new JTextField("");
        textArea.add(textAreaIn);

        textAreaIn.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent event) {

                // Déclarations :
                int counter = 0;
                ArrayList<Integer> humain = new ArrayList<Integer>();
                HashMap<String, String> result = new HashMap<String, String>();
                String valueInput = textAreaIn.getText();

                if (event.getKeyCode() == KeyEvent.VK_ENTER) {

                    textAreaOut.insert(valueInput + " ", message.length());

                    textAreaIn.setText("");

                    textAreaOut.append("\n");

                    humain = game.convertStringToArrayList(valueInput);

                    result = (game.comparaisonChallenger(nbrCombinaison, humain, machine));

                    if (Integer.parseInt(result.get("place")) == nbrCombinaison) {

                        textAreaOut.append("Félicitation ! mission accomplie en " + nbrTours + " tours :)\n");
                        textAreaOut.append(game.displayResult(result));

                    } else if (nbrTours == 1) {

                        textAreaOut.append("GAME OVER !\n");
                        textAreaOut.append("Solution : " + machine + "\n");

                    } else {

                        nbrTours--;

                        textAreaOut.append(game.displayResult(result) + "\n");

                        String message = "Désolez ! il faut essayer une nouvelle combinaison (il reste " + nbrTours + " tours) !\n (" + machine + ")";

                        textAreaOut.append(message);

                    }
                }

            }

        });

        return textArea;
    }

}
