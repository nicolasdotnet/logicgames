/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Tools est la classe utilitaire des jeux SearchMoreOrless et Mastermind.
 *
 * @author nicolasdotnet
 * @version Draft
 * @since 2019
 */
public class Tools {

    /**
     * Constructeur privé
     */
    private Tools() {
    }

    /**
     * Instance unique pré-initialisée
     */
    private static Tools INSTANCE = new Tools();

    /**
     * Point d'accès pour l'instance unique du singleton
     */
    public static Tools getInstance() {
        return INSTANCE;
    }

    /**
     * Random range generator function initial
     *
     * @param nbrDigits number of digits of the combination
     * @param nbrRange range of number for the combinaison
     * @return max number range and mini number range for generate random number
     */
    public int[][] generateRandomRangeInitial(int nbrDigits, int nbrRange) {

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

        return randomRange;

    }

    /**
     * Random range generator function new
     *
     * @param result result of the comparaison function
     * @param attac value table of the Attaquant
     * @param randomRange random limit initial
     * @return new random range
     */
    public int[][] generateRandomRangeNew(List<String> result, List<Integer> attac, int[][] randomRange) {

        for (int i = 0; i < result.size(); i++) {
            String icon = result.get(i);

            switch (icon) {

                case "+":
                    randomRange[0][i] = attac.get(i);
                    System.out.println("min + : " + randomRange[0][i]);
//            randomRange[1][i] = 9;
                    System.out.println("max + : " + randomRange[1][i]);
                    break;
                case "-":
                    System.out.println("min - : " + randomRange[0][i]);
                    randomRange[1][i] = attac.get(i);
                    System.out.println("max - : " + randomRange[1][i]);
                    break;
                case "=":
                    randomRange[0][i] = attac.get(i);
                    System.out.println("min = : " + randomRange[0][i]);
                    randomRange[1][i] = attac.get(i);
                    System.out.println("max = : " + randomRange[1][i]);

                    break;

            }

        }

        return randomRange;

    }

    // SearchMoreOrless
    /**
     * Convert function input user to Integer List
     *
     * @param inputUser mumber value input by user
     * @return number value to Integer List
     */
    public List<Integer> convertStringToListInteger(String inputUser) {

        List<Integer> convert = new ArrayList<Integer>();

        int length = inputUser.length();

        // Cast String to Int
        for (int i = 0; i <= length - 1; i++) {

            char car = inputUser.charAt(i);

            convert.add(Character.getNumericValue(inputUser.charAt(i)));
        }

        return convert;

    }

    /**
     * Convert function result list of the comparison to String type
     *
     * @param result Result arrayList of the comparison() function
     * @return result to String
     */
    public String convertListToString(List<String> result) {

        String convert = String.join("", result);

        return convert;

    }

    /**
     * Convert function machine value (Integer List) to String type
     *
     * @param machine Integer List
     * @return machine value to String
     */
    public String convertListIntegerToString(List<Integer> machine) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < machine.size(); i++) {
            int num = machine.get(i);
            sb.append(num);
        }

        return sb.toString();

    }

    // Mastermind
    /**
     * Convert function machive value to Integer List
     *
     * @param machine machive value
     * @return String value to Integer List
     */
    public List<Integer> convertStringToList(String machine) {

        List<Integer> convert = new ArrayList<Integer>();

        int length = machine.length();

        // Cast String to Int
        for (int i = 0; i <= length - 1; i++) {

            char car = machine.charAt(i);

            convert.add(Character.getNumericValue(machine.charAt(i)));
        }

        return convert;

    }

    public HashMap<String, String> parameterBackup(String title, int nbrDigits, int nbrTours, int nbrRange, boolean modeDev) {
        
        HashMap<String, String> backup = new HashMap<String, String>();
        backup.put("title", title);
        backup.put("nbrDigits", String.valueOf(nbrDigits));
        backup.put("nbrTours", String.valueOf(nbrTours));
        backup.put("nbrRange", String.valueOf(nbrRange));
        backup.put("modeDev", String.valueOf(modeDev));
        
        System.out.println("modevback"+modeDev);

        return backup;

    }

}
