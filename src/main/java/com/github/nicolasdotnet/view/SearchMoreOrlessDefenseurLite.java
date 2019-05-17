/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.view;

import com.github.nicolasdotnet.controller.ControllerSearchMoreOrLessDefenseur;
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
 * SearchMoreOrLessDefenseurLite est la classe qui représente la fenêtre de jeux
 * Recherche +/- en mode Défenseur.
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class SearchMoreOrlessDefenseurLite extends JFrame {

    private int nbrDigits;
    private int nbrTours;
    private int nbrRange;
    private boolean modeDev;
    private ControllerSearchMoreOrLessDefenseur checkUserInput;

    /**
     *
     */
    public SearchMoreOrlessDefenseurLite(int nbrDigits, int nbrTours, int nbrRange, boolean modeDev) {

        this.nbrDigits = nbrDigits;
        this.nbrTours = nbrTours;
        this.nbrRange = nbrRange;
        this.modeDev = modeDev;

        checkUserInput = new ControllerSearchMoreOrLessDefenseur();

        this.setTitle("Recherche +/- Défenseur");
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

        JPanel reloadButton = reloadGame();
        modeDevPanel.add(reloadButton, BorderLayout.SOUTH);
        reloadButton.setVisible(false);

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
            List<Integer> humain = new ArrayList<Integer>();
            List<String> result = new ArrayList<String>();
            List<Integer> machine = new ArrayList<Integer>();

            String valueInput;
            int nbrTests = 0;
            int step = 0;

            int[][] randomRange;

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

                    // Initial phase
                    if (inputUser) {

                        textAreaOut.append("Erreur de saisie, veuillez entrer un nombre positif,\nsans virgule et de " + nbrDigits + " chiffres\n");

                    } else {

                        randomRange = checkUserInput.getGenerateRandomRangeInitial(nbrDigits, nbrRange);
                        humain = checkUserInput.getConvertStringToListInteger(checkUserInput.getSolutionCombination(nbrDigits, nbrRange, valueInput));
                        solution.setText(valueInput);
                        textAreaIn.setEditable(false);

                    }

                    // Gaming machine
                    do {

                        nbrTours--;
                        nbrTests++;

                        counter = 0;
                        machine = checkUserInput.getGeneratePossible(randomRange, nbrDigits);
                        textAreaOut.append("La proposition de la machine est : " + machine.toString() + "\n\n");

                        result.clear();
                        result = (checkUserInput.getComparison(nbrDigits, machine, humain, result));
                        counter = checkUserInput.getEqualCounter(checkUserInput.getConvertListToString(result));

                        randomRange = checkUserInput.getGenerateRandomRangeNew(result, machine, randomRange);

                        String toString = checkUserInput.getConvertListToString(result);

                        if (counter == nbrDigits) {

                            textAreaOut.append("Désolez ! mission accomplie en " + nbrTests + " tours ;)\n");
                            textAreaOut.append("Son résulat est : " + toString + "\n");
                            textAreaOut.append("Rappel, votre combinaison était : " + humain + "\n");
                            textAreaOut.append("La dernière proposition de la machine est : " + machine + "\n\n");
                            textAreaIn.setEditable(false);
                            reloadButton.setVisible(true);

                        } else {

                            textAreaOut.append("Résulat : " + toString + "\n");

                            if (nbrTours == 0) {

                                textAreaOut.append("GAME OVER ! la machine est Out\n");
                                textAreaOut.append("Rappel, votre combinaison était : " + humain + "\n");
                                textAreaOut.append("La dernière proposition de la machine est : " + machine + "\n\n");
                                textAreaOut.append("Voulez-vous rejouer une nouvelle partie ?\n");
                                textAreaIn.setEditable(false);
                                reloadButton.setVisible(true);

                            } else if (nbrTours == 1) {

                                String message = "Félicitation ! La machine doit  essayer une nouvelle combinaison (Dernier tour !)\n";

                                textAreaOut.append(message);

                            } else {

                                String message = "Félicitation ! La machine doit  essayer une nouvelle combinaison (Tour N°" + nbrTours + ")\n\n";

                                textAreaOut.append(message);
                            }
                        }

                    } while (counter != nbrDigits && nbrTours != 0);
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

                SearchMoreOrlessDefenseurLite reload = new SearchMoreOrlessDefenseurLite(nbrDigits, nbrTours, nbrRange, modeDev);
                SearchMoreOrlessDefenseurLite.super.dispose();

            }
        });

        choiceButtons.add(yes);

        JButton no = new JButton("Non");
        no.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                SearchMoreOrlessDefenseurLite.super.dispose();
            }
        });

        choiceButtons.add(no);

        return choiceButtons;

    }
}
