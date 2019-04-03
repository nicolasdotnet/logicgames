/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.view;

import com.github.nicolasdotnet.model.RandomList;
import com.github.nicolasdotnet.model.SearchMoreOrLessDuel;
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

    private SearchMoreOrLessDuel game;

    private ArrayList<Integer> machine;

    private RandomList random;

    /**
     *
     */
    public SearchMoreOrlessDuelLite(int nbrCombinaison) {

        this.setTitle("SearchMoreOrlessDuel");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(450, 450);
        this.setLocationRelativeTo(null);
        add(JScrollTextArea(), BorderLayout.CENTER);

        this.nbrCombinaison = nbrCombinaison;
        nbrTours = 0;
        game = new SearchMoreOrLessDuel();
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
            int counterH = 0;
            int counterM = 0;
            ArrayList<Integer> humain = new ArrayList<Integer>();
            ArrayList<String> resultM = new ArrayList<String>();
            ArrayList<String> resultH = new ArrayList<String>();

            ArrayList<Integer> humainIni = new ArrayList<Integer>();
            ArrayList<Integer> machineIni = new ArrayList<Integer>();

            String toStringM = "null";
            String toStringH = "null";

            @Override
            public void keyReleased(KeyEvent event) {

                int[][] randomLimit;
                randomLimit = random.randomLimitIni(nbrCombinaison);

                String valueInput = textAreaIn.getText();

                if (event.getKeyCode() == KeyEvent.VK_ENTER) {

                    textAreaOut.insert(valueInput + " ", message.length());

                    textAreaIn.setText("");

                    textAreaOut.append("\n");

                    if (nbrTours == 0) {

                        humainIni = game.convertStringToArrayList(game.inputUser(valueInput));

                        machineIni = random.inputMachine(randomLimit, nbrCombinaison);
                        textAreaOut.append("La machine a choisit sa combinaison : " + machineIni.toString() + " \n");

                        textAreaOut.append("Entrez une valeur Attac Humain : \n");

                    }

                    if (nbrTours > 0) {

                        humain = game.convertStringToArrayList(game.inputUser(valueInput));

                        machine = random.inputMachine(randomLimit, nbrCombinaison);
                        textAreaOut.append("Valeur Attac Machine : " + machine.toString() + "\n");

                        // Humain play
                        resultH.clear();
                        textAreaOut.append("Vous jouez ! \n");
                        System.out.println("Humain play");
                        System.out.println("machineIni " + machineIni.toString());
                        System.out.println("humain " + humain.toString());

                        resultH = (game.comparaisonChallenger(nbrCombinaison, humain, machineIni, resultH));
                        counterH += game.counter(resultH);

                        toStringH = game.convertArrayListToString(resultH);

                        // Machine play
                        resultM.clear();
                        System.out.println("Machine play");
                        textAreaOut.append("La machine joue ! \n");

                        resultM = (game.comparaisonDefenseur(nbrCombinaison, machine, humainIni, randomLimit, resultM));
                        counterM = game.counter(resultM);

                        toStringM = game.convertArrayListToString(resultM);//}

                        if (counterM == nbrCombinaison && counterH == nbrCombinaison) {
                            // Humain
                            textAreaOut.append("Félicitation ! Votre mission est accomplie en " + nbrTours + " tours :)\n");
                            textAreaOut.append("Votre Résulat : " + toStringH + "\n");

                            // Machine
                            textAreaOut.append("Mais désolez ! la machine a également accomplie sa mission en " + nbrTours + " tours ;)\n");
                            textAreaOut.append("Son Résulat : " + toStringM + "\n");

                        } else if (counterH == nbrCombinaison) {

                            System.out.println("counterH == nbrCombinaison");

                            // Humain
                            textAreaOut.append("Félicitation ! Votre mission est accomplie en " + nbrTours + " tours :)\n");
                            textAreaOut.append("Votre Résulat : " + toStringH + "\n");

                            // Machine
                            String message = "Et encore félicitation ! La machine doit essayer une nouvelle combinaison.\n\n";
                            textAreaOut.append("Son Résulat : " + toStringM + "\n");
                            textAreaOut.append(message);
                            textAreaOut.append("La machine joue ! \n");

                            do {

                                machine = random.inputMachine(randomLimit, nbrCombinaison);
                                resultM.clear();

                                resultM = (game.comparaisonDefenseur(nbrCombinaison, machine, humain, randomLimit, resultM));
                                counterM = game.counter(resultM);

                                toStringM = game.convertArrayListToString(resultM);

                                if (counterM != nbrCombinaison) {

                                    nbrTours++;
                                    textAreaOut.append("Son Résulat : " + toStringM + "\n");
                                    message = "Félicitation ! La machine doit essayer une nouvelle combinaison.\n\n";

                                    textAreaOut.append(message);
                                    textAreaOut.append("La machine joue ! \n");

                                } else {

                                    textAreaOut.append("Désolez ! la machine a accomplie sa mission en " + nbrTours + " tours ;)\n");
                                    textAreaOut.append("Son Résulat : " + toStringM + "\n");
                                }

                            } while (counterM != nbrCombinaison);

                        } else if (counterM == nbrCombinaison) {

                            //Machine 
                            textAreaOut.append("Désolez ! la machine a accomplie sa mission en " + nbrTours + " tours ;)\n");
                            textAreaOut.append("Son Résulat : " + toStringM + "\n");

                            // Humain
                            textAreaOut.append("Votre Résulat : " + toStringH + "\n");
                            String message = "Désolez ! il vous faut essayer une nouvelle combinaison !\n (" + machineIni + ")";

                            textAreaOut.append(message);

                        } else if (counterM != nbrCombinaison && counterH != nbrCombinaison) {

                            textAreaOut.append("Personne n'a gagné !\n");
                            textAreaOut.append("Son Résulat : " + toStringM + "\n");
                            textAreaOut.append("Votre Résulat : " + toStringH + "\n");
                            String message = "Désolez ! il vous faut essayer une nouvelle combinaison !\n (" + machineIni + ")";

                            textAreaOut.append(message);

                        }
                    }

                    nbrTours++;

                }

            }

        });

        return textArea;

    }

}
