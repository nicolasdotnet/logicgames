/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.model;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * SearchMoreOrLessDuel est la classe du jeux Recherche +/- en mode
 * Duel
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class SearchMoreOrLessDuel {

    /**
     * Number value input by user : test the sizure of the user -> ToDo
     *
     * @param saisie input clavier user
     * @return number user to String
     */
    public String inputUser(String saisie) {

        String inputUser = saisie;

        return inputUser;

    }

    /**
     * function random for generate number for user Machine :
     *
     * @param randomLimit max number limit and mini number limit for generate
     * random number
     * @param nbrCombinaison number of digts of the combination
     * @return random number int 
     */
    public ArrayList<Integer> inputMachine(int[][] randomLimit, int nbrCombinaison) {

        ArrayList<Integer> inputMachine = new ArrayList<Integer>();

        for (int i = 0; i < nbrCombinaison; i++) {
            inputMachine.add((int) ((randomLimit[1][i] - randomLimit[0][i]) * Math.random()) + randomLimit[0][i]);

            System.out.println("test A  : " + inputMachine.get(i) + " ");

        };

        return inputMachine;
    }

    /**
     * convert function input user to Integer ArrayList :
     *
     * @param inputUser Number value input by user
     * @return Number value to Integer ArrayList
     */
    public ArrayList<Integer> convertStringToArrayList(String inputUser) {

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
     * count function the number of equals in the result table :
     *
     * @param result Result arrayList of the comparison() function
     * @return number of equals to int
     */
    public int counter(ArrayList<String> result) {

        int counter = 0;

        for (int i = 0; i < result.size(); i++) {

            if (result.get(i) == "=") {

                counter++;
            }

        }

        return counter;

    }

    /**
     * convert function result table of the comparison to String :
     *
     * @param result Result arrayList of the comparison() function
     * @return result to String
     */
    public String convertArrayListToString(ArrayList<String> result) {
        
        String convert = String.join(" ",result);

        return convert;

    }

    /**
     * comparison function of attac value and defenseur value :
     *
     * @param nbrCombinaison number of digts of the combination
     * @param attac Value table of the Attaquant
     * @param def Value table of the Defenseur
     * @param result Result arrayList of the comparison() function for recursive
     * method
     * @return result of the comparaison.
     */
    public ArrayList<String> comparaisonDefenseur(int nbrCombinaison, ArrayList<Integer> attac, ArrayList<Integer> def, int[][] randomLimit, ArrayList<String> result) {

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

            randomLimit[0][index] = attac.get(index);
//            randomLimit[1][index] = 9;

        } else if (attac.get(index) > def.get(index)) {

            result.add("-");
            System.out.print("Résultat : " + result.get(result.size() - 1) + " car ");

            System.out.print("attac : " + attac.get(index) + " ");
            System.out.println("def : " + def.get(index));

            nbr = 0;

//            randomLimit[0][index] = 0;
            randomLimit[1][index] = attac.get(index);

        } else {

            result.add("=");
            System.out.print("Résultat : " + result.get(result.size() - 1) + " car ");

            System.out.print("attac : " + attac.get(index) + " ");
            System.out.println("def : " + def.get(index));

            nbr = 1;

            randomLimit[0][index] = attac.get(index);
            randomLimit[1][index] = attac.get(index);

        }

        comparaisonDefenseur(index, attac, def,randomLimit,result);

        return result;
    }
    
    /**
     * comparison function of attac value and defenseur value :
     *
     * @param nbrCombinaison number of digts of the combination
     * @param attac Value table of the Attaquant
     * @param def Value table of the Defenseur
     * @param result Result arrayList of the comparison() function for recursive
     * method
     * @return result of the comparaison.
     */
    public ArrayList<String> comparaisonChallenger(int nbrCombinaison, ArrayList<Integer> attac, ArrayList<Integer> def, ArrayList<String> result) {

        int nbr;
        
        int index = nbrCombinaison -1;
        
        System.out.println("Index : "+index);

        System.out.println("\n nbrCombinaison : " + nbrCombinaison);

        if (index < 0) {

            Collections.reverse(result);

            return result;

        }

        if (attac.get(index) < def.get(index)) {

            result.add("+");
            System.out.print("Résultat : " + result.get(result.size()-1) + " car ");

            System.out.print("attac : " + attac.get(index) + " ");
            System.out.println("def : " + def.get(index));

            nbr = 0;

        } else if (attac.get(index) > def.get(index)) {

            result.add("-");
            System.out.print("Résultat : " + result.get(result.size()-1) + " car ");

            System.out.print("attac : " + attac.get(index) + " ");
            System.out.println("def : " + def.get(index));

            nbr = 0;

        } else {

            result.add("=");
            System.out.print("Résultat : " + result.get(result.size()-1) + " car ");

            System.out.print("attac : " + attac.get(index) + " ");
            System.out.println("def : " + def.get(index));

            nbr = 1;

        }

        comparaisonChallenger(index, attac, def, result);

        return result;

    }


}
