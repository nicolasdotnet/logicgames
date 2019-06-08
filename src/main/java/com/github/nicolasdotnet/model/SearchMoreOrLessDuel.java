/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.model;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * SearchMoreOrLessDuel est la classe du jeux Recherche +/- en mode Duel
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class SearchMoreOrLessDuel extends SearchMoreOrLess {

    private static final Logger log = LogManager.getLogger(SearchMoreOrLessDuel.class);

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

        // "null"
        if (sizure == "null") {

            // randomRange
            int[][] randomRange = new int[2][nbrDigits];

            for (int i = 0; i < 2; i++) {

                for (int j = 0; j < nbrDigits; j++) {

                    if (i == 0) {

                        // Min value limit
                        randomRange[i][j] = 0;

                    } else {

                        // Max value limit
                        randomRange[i][j] = nbrRange;

                    }

                }

            }

            // inputmachine
            List<Integer> inputMachine = new ArrayList<Integer>();

            for (int i = 0; i < nbrDigits; i++) {

                inputMachine.add((int) ((randomRange[1][i] - randomRange[0][i]) * Math.random()) + randomRange[0][i]);

                log.info("test A  : " + inputMachine.get(i) + " ");

            }

            // convertListIntegerToString
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < inputMachine.size(); i++) {
                int num = inputMachine.get(i);
                sb.append(num);
            }

            sizure = sb.toString();
            log.info("sizure : " + sizure);
        }
        return sizure;
    }

    /**
     * Generator function of possible combination for the machine
     *
     * @param randomRange max number limit and mini number limit for generate
     * random number
     * @param nbrDigits number of digts of the combination
     * @return possible combination in List integer
     */
    public String getPossible(int[][] randomRange, int nbrDigits, String sizure) {

        if (sizure == "null") {
            
            StringBuilder inputMachine = new StringBuilder();

            for (int i = 0; i < nbrDigits; i++) {

                inputMachine.append(randomRange[0][i]+(int) (Math.random()*((randomRange[1][i] - randomRange[0][i]) +1)));

            }

            log.info("getPossible : " + inputMachine + " ");

            sizure = String.join("", inputMachine);

        }
        return sizure;

    }

}
