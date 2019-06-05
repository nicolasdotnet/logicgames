/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * MastermindDefenseur est la classe du jeux Mastermind en mode Defenseur
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class MastermindDuel extends Mastermind {

    private static final Logger log = LogManager.getLogger(MastermindDuel.class);

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

            // randomLimit
            int[][] randomLimit = new int[2][nbrDigits];

            for (int i = 0; i < 2; i++) {

                for (int j = 0; j < nbrDigits; j++) {

                    if (i == 0) {

                        // Min value limit
                        randomLimit[i][j] = 0;

                    } else {

                        // Max value limit
                        randomLimit[i][j] = nbrRange;

                    }

                }

            }

            // inputmachine
            List<Integer> inputMachine = new ArrayList<Integer>();

            for (int i = 0; i < nbrDigits; i++) {

                inputMachine.add((int) ((randomLimit[1][i] - randomLimit[0][i]) * Math.random()) + randomLimit[0][i]);

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

    @Override
    public String getPossible(int nbrTests, List<String> possible, int nbrRange, String sizure) {
        
        if (sizure == "null") {
            
            if (nbrTests == 1 && nbrRange == 6) {

            sizure = possible.get(1122);

        } else {

            int id = 0 + (int) (Math.random() * (possible.size() - 0)); // +1 ? ou -1 ?
            log.info("id : " + id);
            sizure = possible.get(id);
        }
            
        }
        return sizure;
    }
}
