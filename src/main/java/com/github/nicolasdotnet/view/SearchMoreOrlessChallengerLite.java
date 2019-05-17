/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.view;

import com.github.nicolasdotnet.controller.ControllerSearchMoreOrLessChallenger;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
public class SearchMoreOrlessChallengerLite extends JFrame {

    private int nbrDigits;
    private int nbrTours;
    private int nbrRange;
    private boolean modeDev;
    private ControllerSearchMoreOrLessChallenger checkUserInput;

    /**
     *
     */
    public SearchMoreOrlessChallengerLite(int nbrDigits, int nbrTours, int nbrRange, boolean modeDev) {

        this.nbrDigits = nbrDigits;
        this.nbrTours = nbrTours;
        this.nbrRange = nbrRange;
        this.modeDev = modeDev;

        checkUserInput = new ControllerSearchMoreOrLessChallenger();

        this.setTitle("Recherche +/- Challenger");
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

        JPanel reloadButton = reloadGame();
        modeDevPanel.add(reloadButton, BorderLayout.SOUTH);
        reloadButton.setVisible(false);

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

            // Déclarations :
            int counter = 0;
            List<Integer> humain = new ArrayList<Integer>();
            List<String> result = new ArrayList<String>();
            List<Integer> machine = new ArrayList<Integer>();
            String valueInput;
            int nbrTests = 0;

            int step = 0;

            Boolean inputUser;

            @Override
            public void keyReleased(KeyEvent event) {

                if (event.getKeyCode() == KeyEvent.VK_ENTER) {

                    valueInput = textAreaIn.getText();

                    textAreaOut.insert(valueInput + " ", message.length());

                    textAreaIn.setText("");

                    textAreaOut.append("\n");

                    inputUser = checkUserInput.inputError(valueInput, nbrDigits);
                    System.out.println("inPut : " + inputUser);

                    if (step == 0) {

                        //randomRange = random.randomRangeIni(nbrDigits, nbrRange);
                        //machine = random.inputMachine(randomRange, nbrDigits);
                        machine = checkUserInput.getSolutionCombination(nbrDigits, nbrRange, valueInput);
                        solution.setText(checkUserInput.getConvertListIntegerToString(machine));

                    }

                    if (inputUser) {
                        nbrTours--;
                        textAreaOut.append("Erreur de saisie, veuillez entrer un nombre positif,\nsans virgule et de " + nbrDigits + " chiffres\n");
                        textAreaOut.append("Attention, il vous reste " + nbrTours + " tours\n");
                    } else {

                        humain = checkUserInput.getConvertStringToListInteger(valueInput);

                        System.out.println("humain : " + humain);
                        System.out.println("machine : " + machine);

                        nbrTours--;
                        nbrTests++;

                        result.clear();
                        result = (checkUserInput.getComparison(nbrDigits, humain, machine, result));
                        counter = checkUserInput.getEqualCounter(checkUserInput.getConvertListToString(result));

                        String toString = checkUserInput.getConvertListToString(result);
                        textAreaOut.append("counter : " + counter + " \n");

                        if (counter == nbrDigits) {

                            textAreaOut.append("Félicitation ! mission accomplie en " + nbrTests + " tours.\n");
                            textAreaOut.append("Le résulat est : " + toString + "\n\n");
                            textAreaOut.append("Voulez-vous rejouer une nouvelle partie ?\n");
                            textAreaIn.setEditable(false);
                            reloadButton.setVisible(true);

                        } else {

                            textAreaOut.append("Résulat : " + toString + "\n");

                            if (nbrTours == 0) {

                                textAreaOut.append("GAME OVER !\n");
                                textAreaOut.append("La solution est : " + machine + "\n\n");
                                textAreaOut.append("Voulez-vous rejouer une nouvelle partie ?\n");
                                textAreaIn.setEditable(false);
                                reloadButton.setVisible(true);

                                // voulez vous rejouer ?
                                //si oui : 
                            } else if (nbrTours == 1) {

                                String message = "Désolez ! il faut essayer une nouvelle combinaison (Attention dernier tour !)\n";

                                textAreaOut.append(message);

                            } else {

                                String message = "Désolez ! il faut essayer une nouvelle combinaison (Tour N°" + nbrTours + ") !\n";

                                textAreaOut.append(message);
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

        SearchMoreOrlessChallengerLite reload = new SearchMoreOrlessChallengerLite(nbrDigits, nbrTours, nbrRange, modeDev);
        SearchMoreOrlessChallengerLite.super.dispose();

            }
        });

        choiceButtons.add(yes);

        JButton no = new JButton("Non");
        no.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                SearchMoreOrlessChallengerLite.super.dispose();
            }
        });

        choiceButtons.add(no);

        return choiceButtons;

    }
}
