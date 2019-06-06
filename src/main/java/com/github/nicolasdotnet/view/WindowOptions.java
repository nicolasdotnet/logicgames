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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * WindowOptions est la classe qui représente la fenêtre d'options des jeux.
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

    public WindowOptions(int nbrDigits, int nbrTours, int nbrRange, boolean modeDev, WindowHome windowHome) {
        this.nbrDigits = nbrDigits;
        this.nbrTours = nbrTours;
        this.nbrRange = nbrRange;
        this.modeDev = modeDev;
        this.windowHome = windowHome;
        this.setTitle("Options");
        
        System.out.println("contruc : "+nbrDigits + modeDev);

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
        panel.setLayout(new GridLayout(2, 1, 10, 10));

        JPanel param = new JPanel();
        param.setLayout(new GridLayout(4, 2, 0, 0));

        // nbrDigits Label instructions
        JLabel textAreaOut = new JLabel("1) Tapez un N° entre 1 et 4 pour définir le N°\nde cases des combinaisons");
//        textAreaOut.setEditable(false);
        param.add(textAreaOut);

        // Field input value nbrDigits
        JTextField textFieldNbrDigits = new JTextField(Integer.toString(nbrDigits));
        param.add(textFieldNbrDigits);

        // nbrTours Label instructions
        JTextArea textAreaOut2 = new JTextArea("2) Tapez un N° entre 1 et 9 pour définir le N°\nde tours possibles.");
        textAreaOut2.setEditable(false);
        param.add(textAreaOut2);

        // Field input value nbrTours
        JTextField textFieldNbrTours = new JTextField(Integer.toString(nbrTours));
        param.add(textFieldNbrTours);

        // nbrRange Label instructions
        JTextArea textAreaOut3 = new JTextArea("3) Tapez un N° entre 4 et 9 pour définir \nl'amplitude maximal des nombres.");
        textAreaOut3.setEditable(false);
        param.add(textAreaOut3);

        // Field input value nbrRange
        JTextField textFieldNbrRange = new JTextField(Integer.toString(nbrRange));
        param.add(textFieldNbrRange);

        // nbrRange Label instructions
        JTextArea textAreaOut4 = new JTextArea("4) Activation de l'option Développeur \npour voir la solution.");
        textAreaOut3.setEditable(false);
        param.add(textAreaOut4);

        // Input modeDev option
        JCheckBox modeDevCheck = new JCheckBox("DevMode");
        
        if (modeDev) {

            log.info("coché !");

            modeDevCheck.isSelected();

        }
        
        param.add(modeDevCheck);

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

        panel.add(param);

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
                        System.out.println("check" + inputUser);

                        if (inputUser) {

                            textFieldNbrDigits.setText("");
                            textFieldNbrRange.setText("");
                            textFieldNbrTours.setText("");
                            JOptionPane.showMessageDialog(WindowOptions.this, "Valeurs non valide","Erreur",JOptionPane.ERROR_MESSAGE);
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

        panel.add(optionsChoice);

        return panel;
    }

}
