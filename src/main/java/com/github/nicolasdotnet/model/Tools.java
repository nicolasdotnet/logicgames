/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Tools est la classe utilitaire des jeux SearchMoreOrless et Mastermind.
 *
 * @author nicolasdotnet
 * @version Draft
 * @since 2019
 */
public class Tools {

    private static final Logger log = LogManager.getLogger(Tools.class);

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

    public HashMap<String, String> parameterBackup(String title, int nbrDigits, int nbrTours, int nbrRange, boolean modeDev) {

        HashMap<String, String> backup = new HashMap<String, String>();
        backup.put("title", title);
        backup.put("nbrDigits", String.valueOf(nbrDigits));
        backup.put("nbrTours", String.valueOf(nbrTours));
        backup.put("nbrRange", String.valueOf(nbrRange));
        backup.put("modeDev", String.valueOf(modeDev));

        return backup;

    }

    // SearchMoreOrless
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
    public int[][] generateRandomRangeNew(String result, String attac, int[][] randomRange) {

        for (int i = 0; i < result.length(); i++) {
            char icon = result.charAt(i);

            switch (icon) {

                case '+':
                    randomRange[0][i] = Character.getNumericValue(attac.charAt(i));
                    log.info("min + : " + randomRange[0][i]);
//            randomRange[1][i] = 9;
                    log.info("max + : " + randomRange[1][i]);
                    break;
                case '-':
                    log.info("min - : " + randomRange[0][i]);
                    randomRange[1][i] = Character.getNumericValue(attac.charAt(i));
                    log.info("max - : " + randomRange[1][i]);
                    break;
                case '=':
                    randomRange[0][i] = Character.getNumericValue(attac.charAt(i));
                    log.info("min = : " + randomRange[0][i]);
                    randomRange[1][i] = Character.getNumericValue(attac.charAt(i));
                    log.info("max = : " + randomRange[1][i]);

                    break;

            }

        }

        return randomRange;

    }

    // Mastermind
    /**
     * Generator function of list of best combination possible
     *
     * @param possible complete list of combination possible
     * @param result result hashmap of the comparison() function
     * @param machine machine value
     * @return list of best combination possible
     */
    public List<String> generateBestPossible(List<String> possible, HashMap<String, String> result, String machine) {

        HashMap<String, String> ri = new HashMap<String, String>();
        List<String> bestPossible = new ArrayList<String>();

        for (int i = 0; i < possible.size(); i++) {

            ri = Mastermind.comparison(possible.get(i), machine);

            log.info("display resultat -> place : " + result.get("place") + " ; present : " + result.get("present"));
            log.info("display ri -> place : " + ri.get("place") + " ; present : " + ri.get("present"));

            if (result.get("place").equals(ri.get("place")) && result.get("present").equals(ri.get("present"))) {

                log.info("possible.get(i) : " + possible.get(i));
                bestPossible.add(possible.get(i));

            }

        }

        return bestPossible;

    }

    /**
     * Generator function of complete list of combination possible
     *
     * @param nbrDigits number of digts of the combination
     * @param nbrRange range of number for the combination
     * @return complete list of combination possible
     */
    public List<String> generateAllPossible(int nbrDigits, int nbrRange) {
        List<String> possible = new ArrayList<String>();

        String combinationFormat = "";

        for (int i = 0; i < nbrDigits; i++) {

            combinationFormat += 0;
            log.info("combinationFormat : " + combinationFormat);
        }

        DecimalFormat format = new DecimalFormat(combinationFormat);

        for (int i = 0; i <= (Math.pow(nbrRange, nbrDigits) - 1); i++) {

            log.info("combinaison base 10 : " + i);

            int item = Integer.parseInt(Integer.toString(i, nbrRange));

            String combination = format.format(item);

            log.info("combination base " + nbrRange + " : " + combination);

            possible.add(combination);

        }

        return possible;
    }

}
