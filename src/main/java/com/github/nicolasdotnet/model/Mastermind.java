/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.model;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Mastermind is the mastermind game model class.
 *
 * @author nicolasdotnet
 * @version Draft
 * @since 2019
 */
public abstract class Mastermind {

    private static final Logger log = LogManager.getLogger(Mastermind.class);

    /**
     * Comparison function of attac value and defenseur value
     *
     * @param solution solution value of the defenseur
     * @param attac value of the attaquant
     * @return result hasmap with number place and number present
     */
    public static HashMap<String, String> comparison(String attac, String solution) {

        char charDef[] = solution.toCharArray();
        char charAttac[] = attac.toCharArray();

        String resultKeyOne = "place";
        String resultkeyTwo = "present";

        HashMap<String, String> result = new HashMap<String, String>();
        result.put(resultKeyOne, "0");
        result.put(resultkeyTwo, "0");
        int place = 0;
        int present = 0;

        // place
        log.info("def[] : " + charDef.toString() + " taille : " + charDef.length);
        log.info("attac[] : " + attac.toString() + " taille : " + charAttac.length);

        for (int i = 0; i < charDef.length; i++) {

            if (charDef[i] == charAttac[i]) {

                place++;
                log.info("place : " + place);
                result.replace("place", Integer.toString(place));
                charDef[i] = ' ';
                charAttac[i] = ' ';

            }

        }

        // present
        for (int i = 0; i < charAttac.length; i++) {

            for (int j = 0; j < charDef.length; j++) {

                if (i != j && charDef[j] != ' ' && charDef[j] == charAttac[i]) {

                    present++;
                    log.info("present : " + present);
                    result.replace("present", Integer.toString(present));
                    log.info("valeur Attac : " + charAttac[i] + " ; index : " + i);
                    log.info("valeur Def : " + charDef[j] + " ; index : " + j);

                    charDef[j] = ' ';

                    break;
                }

            }
        }

        return result;

    }

    /**
     * Display result according to place value and present value
     *
     * @param result result hasmap of the comparison() function
     * @return a string for display
     */
    public String displayResult(HashMap<String, String> result) {

        StringBuilder sb = new StringBuilder();

        Enumeration<String> toStringKey = Collections.enumeration(result.keySet());
        Enumeration<String> toStringValue = Collections.enumeration(result.values());

        while (toStringKey.hasMoreElements() && toStringValue.hasMoreElements()) {

            String key = toStringKey.nextElement();

            String value = toStringValue.nextElement();

            if (key == "present" && value == "0") {

                sb.append("");

            } else if (key == "present" && value != "0") {

                sb.append(value + " présent ");

            } else if (key == "place" && value == "0") {

                sb.append("");

            } else if (key == "place" && value != "0") {

                sb.append(value + " bien placés ");

            }

        }

        return sb.toString();
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
     * @param nbrTests number of test of comparison
     * @param possible complete list of combination possible
     * @param nbrDigits number of digts of the combination
     * @param nbrRange range of number for the combinaison
     * @param sizure value input by user
     * @return solution combination generate
     */
    public abstract String getPossible(int nbrTests, List<String> possible, int nbrRange, int nbrDigits, String sizure);

}
