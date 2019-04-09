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
 *
 * MastermindChallenger est la classe du jeux Mastermind en mode Challenger
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class MastermindChallenger {

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
     *
     * @param nbrCombinaison number of digts of the combination
     * @param attac Value table of the Attaquant
     * @param def Value table of the Defenseur
     * @return Result hasmap with number place and number present
     */
    public HashMap<String, String> comparaisonChallenger(int nbrCombinaison, ArrayList<Integer> attac, ArrayList<Integer> def) {

        HashMap<String, String> result = new HashMap<String, String>();
        result.put("place", "0");
        result.put("present", "0");

        int place = 0;

        for (int i = 0; i < nbrCombinaison; i++) {

            System.out.println("TOUR ATTAC: " + i);

            for (int j = 0; j < nbrCombinaison; j++) {

                System.out.println("TOUR DEF: " + j);

                if (attac.get(i) == def.get(j)) {

                    if (i == j) {

                        place++;

                        result.replace("place", Integer.toString(place));

                    }

                }
            }

        }

        return result;
    }

    public HashMap<String, String> comparaisonChallengerS(String saisie, ArrayList<Integer> solution) {

        String machine = convertArrayListIntegerToString(solution);
        char def[] = machine.toCharArray();
        char attac[] = saisie.toCharArray();

        HashMap<String, String> result = new HashMap<String, String>();
        result.put("place", "0");
        result.put("present", "0");
        int place = 0;
        int present = 0;

        // place
        System.out.println("def[] : " + def.toString() + " taille : " + def.length);
        System.out.println("attac[] : " + attac.toString() + " taille : " + attac.length);

        for (int i = 0; i < def.length; i++) {

            if (def[i] == attac[i]) {

                place++;
                System.out.println("place : " + place);
                result.replace("place", Integer.toString(place));
                def[i] = ' ';
                attac[i] = ' ';

            }

        }

        // present
        for (int i = 0; i < attac.length; i++) {

            for (int j = 0; j < def.length; j++) {

                if (i != j && def[j] != ' ' && def[j] == attac[i]) {

                    present++;
                    System.out.println("present : " + present);
                    result.replace("present", Integer.toString(present));
                    System.out.println("valeur Attac : " + attac[i] + " ; index : " + i);
                    System.out.println("valeur Def : " + def[j] + " ; index : " + j);

                    def[j] = ' ';

                    break;
                }

            }
        }
        return result;

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

        String convert = String.join(" ", result);

        return convert;

    }

    /**
     * display result according to place value and present value
     *
     * @param result Result hasmap of the comparison() function
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

    public String convertArrayListIntegerToString(ArrayList<Integer> result) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.size(); i++) {
            int num = result.get(i);
            sb.append(num);
        }

        return sb.toString();

    }

}
