/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.model;

import java.util.ArrayList;

/**
 *
 * RandomList est la classe qui génére une liste de nombres aléatoire.
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class RandomList {

    /**
     *
     * @param nbrCombinaison number of digts of the combination
     * @param nbrRange range of number for the combinaison
     * @return max number limit and mini number limit for generate random number
     */
    public int[][] randomLimitIni(int nbrCombinaison, int nbrRange) {

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

        return randomLimit;

    }

    /**
     *
     * @param randomLimit max number limit and mini number limit for generate
     * random number
     * @param nbrCombinaison number of digts of the combination
     * @return
     */
    public ArrayList<Integer> inputMachine(int[][] randomLimit, int nbrCombinaison) {

        ArrayList<Integer> inputMachine = new ArrayList<Integer>();

        for (int i = 0; i < nbrCombinaison; i++) {

            inputMachine.add((int) ((randomLimit[1][i] - randomLimit[0][i]) * Math.random()) + randomLimit[0][i]);

            System.out.println("test A  : " + inputMachine.get(i) + " ");

        }

        return inputMachine;
    }

}
