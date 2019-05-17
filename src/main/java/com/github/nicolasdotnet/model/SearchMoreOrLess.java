/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.model;

import java.util.Collections;
import java.util.List;

/**
 * SearchMoreOrLess est la classe métier du jeux Recherche +/-.
 *
 * @author nicolasdotnet
 * @version Draft
 * @since 2019
 */
public abstract class SearchMoreOrLess {

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
            System.out.println("counter while : " + counter);
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
    public List<String> comparison(int nbrDigits, List<Integer> attac, List<Integer> def, List<String> result) {

        int nbr;

        int index = nbrDigits - 1;

        System.out.println("Index : " + index);

        System.out.println("\n nbrDigits : " + nbrDigits);

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

        comparison(index, attac, def, result);

        return result;

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
