/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.model;

import java.util.ArrayList;

/**
 *
 * SearchMoreOrLessDuel est la classe du jeux Recherche +/- en mode Duel
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class SearchMoreOrLessDuel extends SearchMoreOrLess {

    /**
     * new random limit generator function.
     *
     * @param result Result of the comparaison function
     * @param attac Value table of the Attaquant
     * @param randomLimit Random limit initial
     * @return new random limit
     */
    public int[][] generatNewRandomLimit(ArrayList<String> result, ArrayList<Integer> attac, int[][] randomLimit) {

        for (int i = 0; i < result.size(); i++) {
            String icon = result.get(i);

            switch (icon) {

                case "+":
                    randomLimit[0][i] = attac.get(i);
                    System.out.println("min + : " + randomLimit[0][i]);
//            randomLimit[1][i] = 9;
                    System.out.println("max + : " + randomLimit[1][i]);
                    break;
                case "-":
                    System.out.println("min - : " + randomLimit[0][i]);
                    randomLimit[1][i] = attac.get(i);
                    System.out.println("max - : " + randomLimit[1][i]);
                    break;
                case "=":
                    randomLimit[0][i] = attac.get(i);
                    System.out.println("min = : " + randomLimit[0][i]);
                    randomLimit[1][i] = attac.get(i);
                    System.out.println("max = : " + randomLimit[1][i]);

                    break;

            }

        }

        return randomLimit;

    }

    @Override
    public String generatSolution(int nbrCombinaison, int nbrRange, String sizure) {
        if (sizure == null) {

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

            sizure = sb.toString();

        }
        return sizure;
    }

}
