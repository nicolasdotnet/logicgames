/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * SearchMoreOrLess is the business class of the Search +/- game.
 *
 * @author nicolasdotnet
 * @version Draft
 * @since 2019
 */
public abstract class SearchMoreOrLess {

    private static final Logger log = LogManager.getLogger(SearchMoreOrLess.class);

    /**
     * Count function the number of equals in the result string
     *
     * @param result result string of the comparison() function
     * @return number of equals to int
     */
    public int equalCounter(String result) {

        int counter = 0;

        int index = result.indexOf("=");

        while (index >= 0) {

            counter++;
            log.info("counter while : " + counter);
            index = result.indexOf("=", index + 1);

        }

        return counter;

    }

    /**
     * Comparison function of attac value and defenseur value
     *
     * @param nbrDigits number of digts of the combination
     * @param attac value table of the attacker
     * @param def value table of the defender
     * @param result result arrayList of the comparison() function for recursive
     * method
     * @return result of the comparison
     */
    public String comparison(int nbrDigits, String attac, String def, String result) {

        int index = nbrDigits - 1;

        log.info("Index : " + index);

        log.info("\n nbrDigits : " + nbrDigits);

        StringBuilder sb = new StringBuilder(result);

        if (index < 0) {

            result = sb.reverse().toString();
            log.info("Retour : " + result);

            return result;

        }

        if (attac.charAt(index) < def.charAt(index)) {

            sb.append("+");
            log.info("Résultat : " + sb.toString() + " car ");

            log.info("attac : " + attac.charAt(index) + " ");
            log.info("def : " + def.charAt(index));

        } else if (attac.charAt(index) > def.charAt(index)) {

            sb.append("-");
            log.info("Résultat : " + sb.toString() + " car ");

            log.info("attac : " + attac.charAt(index) + " ");
            log.info("def : " + def.charAt(index));

        } else {

            sb.append("=");
            log.info("Résultat : " + sb.toString() + " car ");

            log.info("attac : " + attac.charAt(index) + " ");
            log.info("def : " + def.charAt(index));

        }

        return comparison(index, attac, def, sb.toString());

    }

    /**
     * Generator function of solution combination
     *
     * @param nbrDigits number of digts of the combination
     * @param nbrRange range of number for the combinaison
     * @param sizure value input by user
     * @return solution combination generate
     */
    public abstract String getSolutionCombination(int nbrDigits, int nbrRange, String sizure);

    /**
     * Generator function of possible combination
     *
     * @param randomRange max number limit and mini number limit for generate
     * random number
     * @param nbrDigits number of digts of the combination
     * @param sizure value input by user
     * @return solution combination generate
     */
    public abstract String getPossible(int[][] randomRange, int nbrDigits, String result, String attac, String sizure);

}
