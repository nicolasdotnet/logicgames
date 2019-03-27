/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.view;

import com.github.nicolasdotnet.model.RandomList;
import com.github.nicolasdotnet.model.SearchMoreOrLessChallenger;
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
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class SearchMoreOrlessChallengerLite extends JFrame {

    private int nbrCombinaison;
    
    private int nbrTours;

    private SearchMoreOrLessChallenger game;

    private ArrayList<Integer> machine;

    private RandomList random;

    /**
     *
     */
    public SearchMoreOrlessChallengerLite(int nbrCombinaison) {

        this.setTitle("SearchMoreOrlessChallenger");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(450, 450);
        this.setLocationRelativeTo(null);
        add(JScrollTextArea(), BorderLayout.CENTER);

        this.nbrCombinaison = nbrCombinaison;
        nbrTours = 1;
        game = new SearchMoreOrLessChallenger();
        random = new RandomList();
        machine = new ArrayList<Integer>();
        int[][] randomLimit;

        // param to enable Window visibility 
        this.setVisible(true);

        randomLimit = random.randomLimitIni(nbrCombinaison);
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
                ArrayList<String> result = new ArrayList<String>();
                String valueInput = textAreaIn.getText();

                if (event.getKeyCode() == KeyEvent.VK_ENTER) {

                    textAreaOut.insert(valueInput+" ", message.length());

                    textAreaIn.setText("");

                    textAreaOut.append("\n");

                    humain = game.convertStringToArrayList(game.inputUser(valueInput));

                    result = (game.comparaisonChallenger(nbrCombinaison, humain, machine,result));
                    counter +=game.counter(result);

                    String toString = game.convertArrayListToString(result);

                    if (counter == nbrCombinaison) {

                        textAreaOut.append("Félicitation ! mission accomplie en "+nbrTours+" tours :)\n");
                        textAreaOut.append("Résulat : " + toString + "\n");

                    } else {

                        nbrTours ++;
                        textAreaOut.append("Résulat : " + toString + "\n");
                        String message = "Désolez ! il faut essayer une nouvelle combinaison (Tour N°" + nbrTours + ") !\n ("+machine+")";

                        textAreaOut.append(message);
                    }

                }

            }

        });

        return textArea;

    }

}
