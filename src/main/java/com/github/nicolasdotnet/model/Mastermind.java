/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * Mastermind est la classe métier du jeux Mastermind.
 *
 * @author nicolasdotnet
 * @version Draft
 * @since 2019
 */
public abstract class Mastermind {
    
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

    /**
     * comparison function of attac value and defenseur value :
     * 
     * @param solution solution value of the defenseur
     * @param attac value of the attaquant
     * @return result hasmap with number place and number present
     */
    public HashMap<String, String> comparaison(String attac, ArrayList<Integer> solution) {

        String machine = convertArrayListIntegerToString(solution);
        char charDef[] = machine.toCharArray();
        char charAttac[] = attac.toCharArray();

        HashMap<String, String> result = new HashMap<String, String>();
        result.put("place", "0");
        result.put("present", "0");
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
     * display result according to place value and present value
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
     * convert function solution list to String type :
     * @param solution solution value
     * @return solution value to String
     */
    public String convertArrayListIntegerToString(ArrayList<Integer> solution) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < solution.size(); i++) {
            int num = solution.get(i);
            sb.append(num);
        }

        return sb.toString();

    }

    
}
