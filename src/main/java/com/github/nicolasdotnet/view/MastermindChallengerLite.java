/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.view;

import com.github.nicolasdotnet.model.Controller;
import com.github.nicolasdotnet.model.MastermindChallenger;
import com.github.nicolasdotnet.model.RandomList_;
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
 * MasterminChallengerLite est la classe qui représente la fenêtre de jeux
 * Mastermind en mode Challenger.
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class MastermindChallengerLite extends JFrame {

    private int nbrCombinaison;
    private int nbrTours;
    private int nbrRange;
    private boolean modeDev;
    private MastermindChallenger game;

    /**
     *
     */
    public MastermindChallengerLite(int nbrCombinaison, int nbrTours, int nbrRange, boolean modeDev) {

        this.nbrCombinaison = nbrCombinaison;
        this.nbrTours = nbrTours;
        this.nbrRange = nbrRange;
        this.modeDev = modeDev;

        game = new MastermindChallenger();

        this.setTitle("MastermindChallenger");
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
            int[][] randomLimit;
            RandomList_ random = new RandomList_();
            int step = 0;
            int nbrTests = 0;
            Controller checkUserInput = new Controller();
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

                    if (step == 0) {

                        randomLimit = random.randomLimitIni(nbrCombinaison, nbrRange);
                        machine = random.inputMachine(randomLimit, nbrCombinaison);
                        solution.setText(game.convertArrayListIntegerToString(machine));

                    }

                    if (inputUser) {
                        nbrTours--;
                        textAreaOut.append("Erreur de saisie, veuillez entrer un nombre positif,\nsans virgule et de " + nbrCombinaison + " chiffres\n");
                        textAreaOut.append("Attention, il vous reste " + nbrTours + " tours\n");
                    } else {

                        nbrTours--;
                        nbrTests++;

                        result.clear();
                        result = (game.comparaison(valueInput, machine));

                        if (Integer.parseInt(result.get("place")) == nbrCombinaison) {

                            textAreaOut.append("Félicitation ! mission accomplie en " + nbrTests + " tours :)\n");
                            textAreaOut.append(game.displayResult(result));
                            textAreaIn.setEditable(false);

                        } else {

                            textAreaOut.append(game.displayResult(result) + "\n");

                            if (nbrTours == 0) {

                                textAreaOut.append("GAME OVER !\n");
                                textAreaOut.append("Solution : " + machine + "\n");
                                textAreaIn.setEditable(false);

                            } else if (nbrTours == 1) {

                                String message = "Désolez ! il faut essayer une nouvelle combinaison (Attention dernier tour !)\n\n";

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
}
