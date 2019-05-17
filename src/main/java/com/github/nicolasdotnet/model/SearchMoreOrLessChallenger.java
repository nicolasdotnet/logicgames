/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * SearchMoreOrLessChallenger est la classe du jeux Recherche +/- en mode
 * Challenger
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class SearchMoreOrLessChallenger extends SearchMoreOrLess {

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

            System.out.println("test A  : " + inputMachine.get(i) + " ");

        }

        // convertListIntegerToString
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < inputMachine.size(); i++) {
            int num = inputMachine.get(i);
            sb.append(num);
        }

        return sb.toString();

    }

}
