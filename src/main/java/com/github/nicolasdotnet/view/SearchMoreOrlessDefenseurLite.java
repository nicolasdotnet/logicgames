/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.view;

import com.github.nicolasdotnet.model.RandomList;
import com.github.nicolasdotnet.model.SearchMoreOrLessDefenseur;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
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

    private SearchMoreOrLessDefenseur game;

    private ArrayList<Integer> machine;

    private RandomList random;

    /**
     *
     */
    public SearchMoreOrlessDefenseurLite(int nbrCombinaison, int nbrTours) {

        this.setTitle("SearchMoreOrlessDefenseur");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(450, 450);
        this.setLocationRelativeTo(null);
        add(JScrollTextArea(), BorderLayout.CENTER);

        this.nbrCombinaison = nbrCombinaison;
        this.nbrTours = nbrTours;
        game = new SearchMoreOrLessDefenseur();
        random = new RandomList();
        machine = new ArrayList<Integer>();

        // param to enable Window visibility 
        this.setVisible(true);

    }

    private JPanel JScrollTextArea() {

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
            int counter;
            ArrayList<Integer> humain = new ArrayList<Integer>();
            ArrayList<String> result = new ArrayList<String>();

            String valueInput;
            int nbrTests = 1;

            @Override
            public void keyReleased(KeyEvent event) {

                if (event.getKeyCode() == KeyEvent.VK_ENTER) {

                    valueInput = textAreaIn.getText();

                    int[][] randomLimit;
                    randomLimit = random.randomLimitIni(nbrCombinaison);

                    textAreaOut.insert(valueInput + " ", message.length());

                    textAreaIn.setText("");

                    textAreaOut.append("\n");

                    humain = game.convertStringToArrayList(game.inputUser(valueInput));
                    textAreaIn.setEditable(false);

                    do {

                        nbrTours--;
                        nbrTests++;

                        counter = 0;
                        machine = random.inputMachine(randomLimit, nbrCombinaison);
                        textAreaOut.append("Valeur Attac Machine : " + machine.toString() + "\n");

                        result.clear();
                        result = (game.comparaisonDefenseur(nbrCombinaison, machine, humain, randomLimit, result));
                        counter = game.counter(result);

                        String toString = game.convertArrayListToString(result);

                        if (counter == nbrCombinaison) {

                            textAreaOut.append("Désolez ! mission accomplie en " + nbrTests + " tours ;)\n");
                            textAreaOut.append("Résulat : " + toString + "\n");

                        } else {

                            textAreaOut.append("Résulat : " + toString + "\n");

                            if (nbrTours == 0) {

                                textAreaOut.append("GAME OVER ! la machine est Out\n");
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

        return textArea;

    }

}
