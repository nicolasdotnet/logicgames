/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.model;

import java.util.ArrayList;

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

    @Override
    public String generatSolution(int nbrCombinaison, int nbrRange, String saissie) {

        // randomLimit
        int[][] randomLimit = new int[2][nbrCombinaison];

        for (int i = 0; i < 2; i++) {

            for (int j = 0; j < nbrCombinaison; j++) {

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
        ArrayList<Integer> inputMachine = new ArrayList<Integer>();

        for (int i = 0; i < nbrCombinaison; i++) {

            inputMachine.add((int) ((randomLimit[1][i] - randomLimit[0][i]) * Math.random()) + randomLimit[0][i]);

            System.out.println("test A  : " + inputMachine.get(i) + " ");

        }

        // convertArrayListIntegerToString
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < inputMachine.size(); i++) {
            int num = inputMachine.get(i);
            sb.append(num);
        }

        return sb.toString();

    }

}
