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
    public SearchMoreOrlessDefenseurLite(int nbrCombinaison) {

        this.setTitle("SearchMoreOrlessDefenseur");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(450, 450);
        this.setLocationRelativeTo(null);
        add(JScrollTextArea(), BorderLayout.CENTER);

        this.nbrCombinaison = nbrCombinaison;
        nbrTours = 1;
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

            @Override
            public void keyReleased(KeyEvent event) {

                // Déclarations :
                int counter;
                ArrayList<Integer> humain = new ArrayList<Integer>();
                ArrayList<String> result = new ArrayList<String>();

                int[][] randomLimit;
                randomLimit = random.randomLimitIni(nbrCombinaison);

                String valueInput = textAreaIn.getText();

                if (event.getKeyCode() == KeyEvent.VK_ENTER) {

                    textAreaOut.insert(valueInput + " ", message.length());

                    textAreaIn.setText("");

                    textAreaOut.append("\n");

                    humain = game.convertStringToArrayList(game.inputUser(valueInput));

                    do {

                        counter = 0;
                        machine = random.inputMachine(randomLimit, nbrCombinaison);
                        result.clear();

                        result = (game.comparaisonDefenseur(nbrCombinaison, machine, humain, randomLimit, result));
                        counter = game.counter(result);

                        String toString = game.convertArrayListToString(result);

                        if (counter != nbrCombinaison) {

                            nbrTours++;
                            textAreaOut.append("Résulat : " + toString + "\n");
                            String message = "Félicitation ! La machine doit  essayer une nouvelle combinaison (Tour N°" + nbrTours + ")\n\n";

                            textAreaOut.append(message);

                        } else {

                            textAreaOut.append("Désolez ! mission accomplie en " + nbrTours + " tours ;)\n");
                            textAreaOut.append("Résulat : " + toString + "\n");
                        }

                    } while (counter != nbrCombinaison);

                }

            }

        });

        return textArea;

    }

}
