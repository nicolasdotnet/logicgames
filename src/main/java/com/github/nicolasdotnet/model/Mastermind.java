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

/**
 * Mastermind est la classe métier du jeux Mastermind.
 *
 * @author nicolasdotnet
 * @version Draft
 * @since 2019
 */
public abstract class Mastermind {

    private String resultKeyOne = "place";
    private String resultkeyTwo = "present";

    /**
     * Comparison function of attac value and defenseur value
     *
     * @param solution solution value of the defenseur
     * @param attac value of the attaquant
     * @return result hasmap with number place and number present
     */
    public HashMap<String, String> comparison(String attac, List<Integer> solution) {

        Tools tools = Tools.getInstance();
        String machine = tools.convertListIntegerToString(solution);
        char charDef[] = machine.toCharArray();
        char charAttac[] = attac.toCharArray();

        HashMap<String, String> result = new HashMap<String, String>();
        result.put(resultKeyOne, "0");
        result.put(resultkeyTwo, "0");
        int place = 0;
        int present = 0;

        // place
        System.out.println("def[] : " + charDef.toString() + " taille : " + charDef.length);
        System.out.println("attac[] : " + attac.toString() + " taille : " + charAttac.length);

        for (int i = 0; i < charDef.length; i++) {

            if (charDef[i] == charAttac[i]) {

                place++;
                System.out.println("place : " + place);
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
                    System.out.println("present : " + present);
                    result.replace("present", Integer.toString(present));
                    System.out.println("valeur Attac : " + charAttac[i] + " ; index : " + i);
                    System.out.println("valeur Def : " + charDef[j] + " ; index : " + j);

                    charDef[j] = ' ';

                    break;
                }

            }
        }

        System.out.println("retour compraison");
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

}
