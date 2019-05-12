/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.model;

import java.util.ArrayList;

/**
 *
 * SearchMoreOrLessDefenseur est la classe du jeux Recherche +/- en mode
 * Defenseur
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class SearchMoreOrLessDefenseur extends SearchMoreOrLess {

    public static void main(String[] args) {

        String result = "===";
        System.out.println(result.indexOf("="));
        SearchMoreOrLessDefenseur run = new SearchMoreOrLessDefenseur();
        int counter = run.equalCounter(result);
        System.out.println("counter = " + counter);
    }

    // methodes Ai
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

        return sizure;

    }
}
