/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.view;

import com.github.nicolasdotnet.controller.ControllerMastermindDefenseur;
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
 * MastermindDefenseurLite est la classe qui représente la fenêtre de jeux
 * mastermind en mode Défenseur.
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class MastermindDefenseurLite extends JFrame {

    private int nbrDigits;
    private int nbrTours;
    private int nbrRange;
    private boolean modeDev;
    private ControllerMastermindDefenseur checkUserInput;

    /**
     *
     */
    public MastermindDefenseurLite(int nbrDigits, int nbrTours, int nbrRange, boolean modeDev) {

        this.nbrDigits = nbrDigits;
        this.nbrTours = nbrTours;
        this.nbrRange = nbrRange;
        this.modeDev = modeDev;

        checkUserInput = new ControllerMastermindDefenseur();

        this.setTitle("Mastermind Défenseur");
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
            HashMap<String, String> result = new HashMap<String, String>();
            List<Integer> machine = new ArrayList<Integer>();
            String valueInput;
            List<String> possible;
            List<String> bestPossible;

            int[][] randomRange;

            int nbrTests = 0;

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

                    if (inputUser) {

                        textAreaOut.append("Erreur de saisie, veuillez entrer un nombre positif,\nsans virgule et de " + nbrDigits + " chiffres\n");

                    } else {

                        randomRange = checkUserInput.getGenerateRandomRangeInitial(nbrDigits, nbrRange);

                        solution.setText(valueInput);
                        textAreaIn.setEditable(false);

                        do {

                            if (nbrTests == 0) {

                                nbrTours--;
                                nbrTests++;

                                possible = checkUserInput.getGenerateAllPossible(nbrDigits, nbrRange);
                                String machine2 = checkUserInput.getGetPossible(nbrTests, possible, nbrRange);

                                System.out.println("Attac machine : " + machine2);

                                machine = checkUserInput.getConvertStringToListInteger(machine2);

                                textAreaOut.append("Proposition de la machine : " + machine.toString() + "\n\n");

                            } else if (nbrTests == 1) {

                                nbrTours--;
                                nbrTests++;

                                bestPossible = checkUserInput.getGenerateBestPossible(possible, result, machine);
                                String machine2 = checkUserInput.getGetPossible(nbrTests, bestPossible, nbrRange);

                                System.out.println("Attac machine : " + machine2);

                                machine = checkUserInput.getConvertStringToListInteger(machine2);

                                textAreaOut.append("Proposition de la machine : " + machine.toString() + "\n\n");

                            } else {

                                nbrTours--;
                                nbrTests++;

                                bestPossible = checkUserInput.getGenerateBestPossible(bestPossible, result, machine);
                                String machine2 = checkUserInput.getGetPossible(nbrTests, bestPossible, nbrRange);

                                System.out.println("Attac machine : " + machine2);

                                machine = checkUserInput.getConvertStringToListInteger(machine2);

                                textAreaOut.append("la proposition de la machine : " + machine.toString() + "\n\n");

                            }

                            // Machine play
                            result.clear();
                            System.out.println("Machine play");
                            textAreaOut.append("La machine joue ! \n");

                            result = (checkUserInput.getComparison(valueInput, machine));

                            if (Integer.parseInt(result.get("place")) == nbrDigits) {

                                textAreaOut.append("Désolez ! mission accomplie en " + nbrTests + " tours :)\n");
                                textAreaOut.append("Son résulat est : " + checkUserInput.getDisplayResult(result) + "\n");
                                textAreaOut.append("Rappel, votre combinaison était : " + valueInput + "\n");
                                textAreaOut.append("La dernière proposition de la machine est : " + machine + "\n\n");
                                textAreaOut.append("Voulez-vous rejouer une nouvelle partie ?\n");
                                textAreaIn.setEditable(false);
                                reloadButton.setVisible(true);

                            } else {

                                textAreaOut.append(checkUserInput.getDisplayResult(result) + "\n");

                                if (nbrTours == 0) {

                                    textAreaOut.append("GAME OVER ! la machine est Out\n");
                                    textAreaOut.append("Rappel, votre combinaison était : " + valueInput + "\n");
                                    textAreaOut.append("La dernière proposition de la machine est : " + machine + "\n\n");
                                    textAreaOut.append("Voulez-vous rejouer une nouvelle partie ?\n");
                                    textAreaIn.setEditable(false);
                                    reloadButton.setVisible(true);

                                } else if (nbrTours == 1) {

                                    String message = "Félicitation ! La machine doit  essayer une nouvelle combinaison (dernier tour !)\n";

                                    textAreaOut.append(message);

                                } else {

                                    String message = "Félicitation ! La machine doit  essayer une nouvelle combinaison (Tour N°" + nbrTours + ") !\n";

                                    textAreaOut.append(message);

                                }
                            }

                        } while (Integer.parseInt(result.get("place")) != nbrDigits && nbrTours != 0);
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

    public JPanel reloadGame() {

        JPanel choiceButtons = new JPanel();
        choiceButtons.setLayout(new FlowLayout());

        JButton yes = new JButton("Oui");
        yes.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                MastermindDefenseurLite reload = new MastermindDefenseurLite(nbrDigits, nbrTours, nbrRange, modeDev);
                MastermindDefenseurLite.super.dispose();

            }
        });

        choiceButtons.add(yes);

        JButton no = new JButton("Non");
        no.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                MastermindDefenseurLite.super.dispose();
            }
        });

        choiceButtons.add(no);

        return choiceButtons;

    }
}
