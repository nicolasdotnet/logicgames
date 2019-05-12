/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.model;

import java.util.ArrayList;

/**
 * Tools est la classe utilitaire des jeux SearchMoreOrless et Mastermind.
 *
 * @author nicolasdotnet
 * @version Draft
 * @since 2019
 */
public class Tools {

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

    // SearchMoreOrless
    /**
     * convert function input user to Integer ArrayList :
     *
     * @param inputUser Number value input by user
     * @return Number value to Integer ArrayList
     */
    public ArrayList<Integer> convertStringToArrayListInteger(String inputUser) {

        ArrayList<Integer> convert = new ArrayList<Integer>();

        int length = inputUser.length();

        // Cast String to Int
        for (int i = 0; i <= length - 1; i++) {

            char car = inputUser.charAt(i);

            convert.add(Character.getNumericValue(inputUser.charAt(i)));
        }

        return convert;

    }

    /**
     * convert function result list of the comparison to String type :
     *
     * @param result Result arrayList of the comparison() function
     * @return result to String
     */
    public String convertArrayListToString(ArrayList<String> result) {

        String convert = String.join(" ", result);

        return convert;

    }

    /**
     * convert function machine value (Integer ArrayList) to String type :
     *
     * @param machine Integer ArrayList
     * @return machine value to String
     */
    public String convertArrayListIntegerToString(ArrayList<Integer> machine) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < machine.size(); i++) {
            int num = machine.get(i);
            sb.append(num);
        }

        return sb.toString();

    }

    // Mastermind
    /**
     * convert function machive value to Integer ArrayList :
     *
     * @param machine machive value
     * @return String value to Integer ArrayList
     */
    public ArrayList<Integer> convertStringToArrayList(String machine) {

        ArrayList<Integer> convert = new ArrayList<Integer>();

        int length = machine.length();

        // Cast String to Int
        for (int i = 0; i <= length - 1; i++) {

            char car = machine.charAt(i);

            convert.add(Character.getNumericValue(machine.charAt(i)));
        }

        return convert;

    }

}
