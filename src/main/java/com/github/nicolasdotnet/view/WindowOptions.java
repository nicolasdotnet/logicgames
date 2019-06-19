/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.view;

import com.github.nicolasdotnet.controller.Controller;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * WindowOptions is the class that represents the game options window.
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class WindowOptions extends WindowSource {

    private int nbrDigits;
    private int nbrTours;
    private int nbrRange;
    private boolean modeDev;
    private static final Logger log = LogManager.getLogger(WindowOptions.class);
    private Controller checkUserInput = new Controller();
    private Boolean inputUser;
    private WindowHome windowHome;

    public WindowOptions(WindowHome windowHome) {
        this.windowHome = windowHome;
        this.nbrDigits = windowHome.getNbrDigits();
        this.nbrTours = windowHome.getNbrTours();
        this.nbrRange = windowHome.getNbrRange();
        this.modeDev = windowHome.isModeDev();
        this.setTitle("Options");
        this.setSize(500, 500);
        this.setLocationRelativeTo(this.windowHome);

        JPanel contentPanel = (JPanel) this.getContentPane();
        contentPanel.setLayout(new BorderLayout());

        contentPanel.add(optionsChoice(), BorderLayout.CENTER);

        this.setVisible(true);
    }

    public void setNbrDigits(int nbrDigits) {
        this.nbrDigits = nbrDigits;
    }

    public void setNbrTours(int nbrTours) {
        this.nbrTours = nbrTours;
    }

    public void setNbrRange(int nbrRange) {
        this.nbrRange = nbrRange;
    }

    public void setModeDev(boolean modeDev) {
        this.modeDev = modeDev;
    }

    /**
     * Create choice of games panel.
     *
     * @return panel with the buttons game.
     */
    private JPanel optionsChoice() {

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel param = new JPanel();
        param.setLayout(new GridLayout(5, 2, 0, 10));

        // nbrDigits Label instructions
        JTextArea textAreaOut = new JTextArea("Entrez un N° entre 2 et 9 pour définir\nle N° de chiffre de la combinaison");
        textAreaOut.setEditable(false);
        textAreaOut.setFocusable(false);
        param.add(textAreaOut);

        // Field input value nbrDigits
        JTextField textFieldNbrDigits = new JTextField(Integer.toString(nbrDigits));
        textFieldNbrDigits.setFocusable(true);
        param.add(textFieldNbrDigits);

        // nbrTours Label instructions
        JTextArea textAreaOut2 = new JTextArea("Entrez un N° entre 1 et 9 pour définir\nle N° de tours possibles");
        textAreaOut2.setEditable(false);
        textAreaOut.setFocusable(false);
        param.add(textAreaOut2);

        // Field input value nbrTours
        JTextField textFieldNbrTours = new JTextField(Integer.toString(nbrTours));
        param.add(textFieldNbrTours);

        // nbrRange Label instructions
        JTextArea textAreaOut3 = new JTextArea("Entrez un N° entre 3 et 9 pour définir \nl'amplitude maximal des nombres");
        textAreaOut3.setEditable(false);
        textAreaOut.setFocusable(false);
        param.add(textAreaOut3);

        // Field input value nbrRange
        JTextField textFieldNbrRange = new JTextField(Integer.toString(nbrRange));
        param.add(textFieldNbrRange);

        // nbrRange Label instructions
        JTextArea textAreaOut4 = new JTextArea("Cliquez pour activer l'option\nDéveloppeur. Permet de voir la\ncombinaison secrète");
        textAreaOut3.setEditable(false);
        textAreaOut.setFocusable(false);
        param.add(textAreaOut4);

        // Input modeDev option
        JCheckBox modeDevCheck = new JCheckBox("Mode Développeur");

        param.add(modeDevCheck);

        if (modeDev) {

            log.info("coché !");

            modeDevCheck.setSelected(true);

        }

        modeDevCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (modeDevCheck.isSelected()) {

                    log.info("coché !");

                    windowHome.setModeDev(true);

                } else {

                    log.info("décoché !");
                    windowHome.setModeDev(false);
                }
            }
        });

        panel.add(param, BorderLayout.CENTER);

        // Options choice buttons
        JPanel optionsChoice = new JPanel();

        optionsChoice.setLayout(new GridLayout(1, 2, 10, 10));
        JButton cancel = new JButton("Annuler");

        cancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                WindowOptions.this.dispose();

            }
        });

        optionsChoice.add(cancel);

        JButton save = new JButton("Valider");

        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                // Exception if JtextField = "" because no user sizure""
                try {

                    nbrDigits = Integer.valueOf(textFieldNbrDigits.getText());
                    nbrRange = Integer.valueOf(textFieldNbrRange.getText());
                    nbrTours = Integer.valueOf(textFieldNbrTours.getText());

                } catch (NumberFormatException nfe) {

                    log.info("valeur Digit, Range & Tours non modifiées");

                } finally {

                    if (nbrDigits == 0 || nbrRange == 0 || nbrTours == 0) {

                        WindowOptions.this.dispose();

                    } else {

                        inputUser = checkUserInput.inputErrorOptions(nbrDigits, nbrRange, nbrTours);

                        if (inputUser) {

                            textFieldNbrDigits.setText(Integer.toString(windowHome.getNbrDigits()));
                            textFieldNbrRange.setText(Integer.toString(windowHome.getNbrRange()));
                            textFieldNbrTours.setText(Integer.toString(windowHome.getNbrTours()));
                            Alert.error(WindowOptions.this, "Ooops les valeurs ne sont pas valide ! Veuillez les vérifier.");

                        } else {

                            windowHome.setNbrRange(nbrRange);
                            windowHome.setNbrTours(nbrTours);
                            windowHome.setNbrDigits(nbrDigits);

                            WindowOptions.this.dispose();

                        }

                    }

                }

            }
        });

        optionsChoice.add(save);

        panel.add(optionsChoice, BorderLayout.SOUTH);

        return panel;
    }

}
