/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.view;

import com.github.nicolasdotnet.controller.Controller;
import com.github.nicolasdotnet.controller.ControllerSearchMoreOrLess;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Windows Game is the parent class that represents the Search +/- and
 * Mastermind games window.
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class WindowGame extends JFrame {

    private static final Logger log = LogManager.getLogger(Controller.class);
    private JTextField textAreaIn;
    private JLabel solution;
    private JTextArea textAreaOut;
    private boolean modeDev;
    private JPanel reloadButton = reloadGame();
    private ControllerSearchMoreOrLess checkUserInput;
    private JButton yes;

    public WindowGame(String title, boolean modeDev) {

        this.modeDev = modeDev;
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(600, 600);
        // ImageIcon(URL)
        ImageIcon icon = new ImageIcon("./src/main/resources/fav-icon.png");
        this.setIconImage(icon.getImage());
        this.setLocationRelativeTo(null);
        add(JScrollTextArea(), BorderLayout.CENTER);

        // param to enable Window visibility 
        this.setVisible(true);

    }

    public JTextField getTextAreaIn() {
        return textAreaIn;
    }

    public JLabel getSolution() {
        return solution;
    }

    public JTextArea getTextAreaOut() {
        return textAreaOut;
    }

    public JPanel getReloadButton() {
        return reloadButton;
    }

    public JButton getYes() {
        return yes;
    }

    public boolean isModeDev() {
        return modeDev;
    }

    private JPanel JScrollTextArea() {

        JPanel modeDevPanel = new JPanel();
        modeDevPanel.setLayout(new BorderLayout());

        solution = new JLabel();
        solution.setVisible(isModeDev());
        modeDevPanel.add(solution, BorderLayout.NORTH);

        modeDevPanel.add(reloadButton, BorderLayout.SOUTH);
        reloadButton.setVisible(false);

        JPanel textArea = new JPanel();
        textArea.setLayout(new GridLayout(2, 1, 0, 0));

        textAreaOut = new JTextArea();
        textAreaOut.setEditable(false);

        // Display message and Output traitements : 
        JScrollPane JScrollTextAreaOut = new JScrollPane(textAreaOut);
        JScrollTextAreaOut.setVerticalScrollBarPolicy(JScrollTextAreaOut.VERTICAL_SCROLLBAR_ALWAYS);
        JScrollTextAreaOut.setHorizontalScrollBarPolicy(JScrollTextAreaOut.HORIZONTAL_SCROLLBAR_ALWAYS);
        textArea.add(JScrollTextAreaOut);

        textAreaIn = new JTextField("");
        textArea.add(textAreaIn);

        modeDevPanel.add(textArea, BorderLayout.CENTER);

        return modeDevPanel;
    }

    public JPanel reloadGame() {

        JPanel choiceButtons = new JPanel();
        choiceButtons.setLayout(new FlowLayout());

        yes = new JButton("Oui");
        choiceButtons.add(yes);

        JButton no = new JButton("Non");
        no.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                WindowGame.super.dispose();
            }
        });

        choiceButtons.add(no);

        return choiceButtons;

    }

    public void testGeneratePossible(List<String> possible) {

        if (possible == null) {
            try {
                
                Thread.sleep(9000);

            } catch (InterruptedException ex) {
                log.debug("testGeneratePossible", ex);

            }
        }

    }

    public void humanWinningMessageDisplay(int nbrTests, String result) {

        getTextAreaOut().append("Félicitation ! mission accomplie en " + nbrTests + " tours.\n");
        getTextAreaOut().append("Votre résultat est : " + result + "\n");
        getTextAreaOut().append("\nVoulez-vous rejouer une nouvelle partie ?\n");
        getTextAreaIn().setEditable(false);
    }

    public void humanLoserMessageDisplay(String solution, String result) {

        getTextAreaOut().append("GAME OVER !\n");
        getTextAreaOut().append("Votre résultat est : " + result + "\n");
        getTextAreaOut().append("La solution est : " + solution + "\n");
        getTextAreaOut().append("\nVoulez-vous rejouer une nouvelle partie ?\n");
        getTextAreaIn().setEditable(false);
    }

    public void machineWinningMessageDisplay(int nbrTests, String result, String solution) {

        getTextAreaOut().append("Désolez ! mission accomplie en " + nbrTests + " tours ;)\n");
        getTextAreaOut().append("Son résultat est : " + result + "\n");
        getTextAreaOut().append("Rappel, votre combinaison secrète était : " + solution + "\n");
        getTextAreaOut().append("\nVoulez-vous rejouer une nouvelle partie ?\n");
        getTextAreaIn().setEditable(false);
    }

    public void machineLoserMessageDisplay(String solution, String result) {

        getTextAreaOut().append("GAME OVER ! la machine est Out\n");
        getTextAreaOut().append("Son résultat est : " + result + "\n");
        getTextAreaOut().append("Rappel, votre combinaison secrète était : " + solution + "\n");
        getTextAreaOut().append("\nVoulez-vous rejouer une nouvelle partie ?\n");
        getTextAreaIn().setEditable(false);
    }

    public void machineToBeToContinuedMessageDisplay(int nbrTours, String result) {

        getTextAreaOut().append("Le résultat de la machine : " + result + "\n");

        if (nbrTours == 1) {

            String message = "Félicitation ! La machine doit  essayer une nouvelle combinaison (Dernier tour !)\n";

            getTextAreaOut().append(message);

        } else {

            String message = "Félicitation ! La machine doit  essayer une nouvelle combinaison (Tour N°" + nbrTours + ")\n";

            getTextAreaOut().append(message);
        }

    }

    public void humanToBeToContinuedMessageDisplay(int nbrTours, String result) {

        getTextAreaOut().append("Votre résultat : " + result + "\n");

        if (nbrTours == 1) {

            String message = "Désolez ! il faut essayer une nouvelle combinaison (Attention dernier tour !)\n";

            getTextAreaOut().append(message);

        } else {

            String message = "Désolez ! il faut essayer une nouvelle combinaison (Tour N°" + nbrTours + ") !\n";

            getTextAreaOut().append(message);
        }

    }

    public void allLoser(String resultM, String resultH, String solutionM, String solutionH) {

        getTextAreaOut().append("GAME OVER for all !\n\n");
        getTextAreaOut().append("Son résultat : " + resultM + "\n");
        getTextAreaOut().append("Votre résultat : " + resultH + "\n");
        getTextAreaOut().append("La combinaison secrète de la machine était : " + solutionM + "\n");
        getTextAreaOut().append("La machine n'a pas trouvée également votre combinaison (" + solutionH + ")\n");
        getTextAreaOut().append("\nVoulez-vous rejouer une nouvelle partie ?\n");
    }

    public void allWinning(int nbrTestsH, String resultH, int nbrTestsM, String resultM) {

        // Human
        getTextAreaOut().append("Félicitation ! Votre mission est accomplie en " + nbrTestsH + " tours :)\n");
        getTextAreaOut().append("Votre résultat : " + resultH + "\n");

        // Machine
        getTextAreaOut().append("Mais désolez ! la machine a également accomplie sa mission en " + nbrTestsM + " tours ;)\n");
        getTextAreaOut().append("Son résultat : " + resultM + "\n");
        getTextAreaOut().append("\nVoulez-vous rejouer une nouvelle partie ?\n");
    }

    public void inputErrorMessage(int nbrDigits, int nbrRange) {

        getTextAreaOut().append("Erreur de saisie, veuillez entrer " + nbrDigits + " chiffres entre 0 et " + nbrRange + "\n");
    }
}
