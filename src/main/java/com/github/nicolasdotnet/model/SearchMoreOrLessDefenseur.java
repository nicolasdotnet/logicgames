/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * SearchMoreOrLessDefender is the class of the game Search +/- in Defender
 * mode.
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class SearchMoreOrLessDefenseur extends SearchMoreOrLess {

    private static final Logger log = LogManager.getLogger(SearchMoreOrLessDefenseur.class);

    /**
     * Generator function of solution combination
     *
     * @param nbrDigits number of digts of the combination
     * @param nbrRange range of number for the combinaison
     * @param sizure value input by user
     * @return solution combination generate
     */
    @Override
    public String getSolutionCombination(int nbrDigits, int nbrRange, String sizure) {

        return sizure;

    }

    /**
     * Generator function of possible combination for the machine
     *
     * @param randomRange max number limit and mini number limit for generate
     * random number
     * @param nbrDigits number of digts of the combination
     * @param result result of the comparison() function for recursive method
     * @param attac value of the attacker
     * @param sizure value input by user
     * @return solution combination generate
     */
    @Override
    public String getPossible(int[][] randomRange, int nbrDigits, String result, String attac, String sizure) {

        StringBuilder inputMachine = new StringBuilder();

        if (result == null) {

            for (int i = 0; i < nbrDigits; i++) {
                inputMachine.append(randomRange[0][i] + (int) (Math.random() * ((randomRange[1][i] - randomRange[0][i]) + 1)));
            }

        } else {

            for (int i = 0; i < nbrDigits; i++) {
                char icon = result.charAt(i);

                switch (icon) {

                    case '+':
                        if ((randomRange[1][i] + randomRange[0][i]) % 2 == 1) {

                            inputMachine.append(((randomRange[1][i] + randomRange[0][i]) / 2) + 1);
                        } else {
                            inputMachine.append((randomRange[1][i] + randomRange[0][i]) / 2);
                        }
                        break;
                    case '-':
                        inputMachine.append((randomRange[1][i] + randomRange[0][i]) / 2);
                        break;
                    case '=':
                        inputMachine.append(attac.charAt(i));

                        break;

                }

            }
        }
        
        log.info("getPossible : " + inputMachine + " ");

        return String.join("", inputMachine);
    }

}
