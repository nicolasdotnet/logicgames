/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * SearchMoreOrLess est la classe métier du jeux Recherche +/-.
 *
 * @author nicolasdotnet
 * @version Draft
 * @since 2019
 */
public abstract class SearchMoreOrLess {
    
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
     * count function the number of equals in the result string :
     *
     * @param result Result string of the comparison() function
     * @return number of equals to int
     */
    public int equalCounter(String result) {

        int counter = 0;

        int index = result.indexOf("=");

        while (index >= 0) {
            
            counter++;
            System.out.println("counter while : "+counter);
            index = result.indexOf("=", index + 1);

        }

        return counter;

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

    /**
     * comparison function of attac value and defenseur value :
     *
     * @param nbrCombinaison number of digts of the combination
     * @param attac value table of the Attaquant
     * @param def value table of the Defenseur
     * @param result result arrayList of the comparison() function for recursive
     * method
     * @return result of the comparaison.
     */
    public ArrayList<String> comparaison(int nbrCombinaison, ArrayList<Integer> attac, ArrayList<Integer> def, ArrayList<String> result) {

        int nbr;

        int index = nbrCombinaison - 1;

        System.out.println("Index : " + index);

        System.out.println("\n nbrCombinaison : " + nbrCombinaison);

        if (index < 0) {

            Collections.reverse(result);

            return result;

        }

        if (attac.get(index) < def.get(index)) {

            result.add("+");
            System.out.print("Résultat : " + result.get(result.size() - 1) + " car ");

            System.out.print("attac : " + attac.get(index) + " ");
            System.out.println("def : " + def.get(index));

            nbr = 0;

        } else if (attac.get(index) > def.get(index)) {

            result.add("-");
            System.out.print("Résultat : " + result.get(result.size() - 1) + " car ");

            System.out.print("attac : " + attac.get(index) + " ");
            System.out.println("def : " + def.get(index));

            nbr = 0;

        } else {

            result.add("=");
            System.out.print("Résultat : " + result.get(result.size() - 1) + " car ");

            System.out.print("attac : " + attac.get(index) + " ");
            System.out.println("def : " + def.get(index));

            nbr = 1;

        }

        comparaison(index, attac, def, result);

        return result;

    }

}
