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
 * MastermindDefenseur est la classe du jeux Mastermind en mode Defenseur
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class MastermindDefenseur {

    public static void main(String[] args) {

        MastermindDefenseur run = new MastermindDefenseur();

        String def = "03";
        int nbrCombinaison = 2;
        int nbrRange = 3;
        int nbrTests = 1;
        String machine2;

        // tour 1 : 
        ArrayList<String> possible = run.generatAllPossible(nbrCombinaison, nbrRange);
        System.out.println("taille possible : " + possible.size());

        machine2 = run.getCombinaison(nbrTests, possible, nbrRange);
        System.out.println("machine2 : " + machine2);
        ArrayList<Integer> machine = run.convertStringToArrayList(machine2);

        HashMap<String, String> result = run.comparaisonChallengerS(def, machine);
        System.out.println("result: " + run.displayResult(result) + "\n");

        // tour 2 : 
        ArrayList<String> bestPossible = run.generatBestPossible(possible, result, machine);
        System.out.println("\n taille bestPossible 1 : " + bestPossible.size());

        System.out.println("\n Bilan -> taille possible : " + possible.size() + " ; taille bestPossible : " + bestPossible.size());

        machine2 = run.getCombinaison(nbrTests, bestPossible, nbrRange);
        System.out.println("machine2 : " + machine2);
        machine = run.convertStringToArrayList(machine2);

        result = run.comparaisonChallengerS(def, machine);
        System.out.println("result 2 : " + run.displayResult(result) + "\n");

        // tour 3 : 
        bestPossible = run.generatBestPossible(bestPossible, result, machine);
        System.out.println("\n taille bestPossible 2 : " + bestPossible.size());

        System.out.println("\n Bilan -> taille possible : " + possible.size() + " ; taille bestPossible 2 : " + bestPossible.size());

        machine2 = run.getCombinaison(nbrTests, bestPossible, nbrRange);
        System.out.println("machine2 : " + machine2);
        machine = run.convertStringToArrayList(machine2);

        result = run.comparaisonChallengerS(def, machine);
        System.out.println("result 3 : " + run.displayResult(result) + "\n");

        // tour 4 :
        if (Integer.parseInt(result.get("place")) != nbrCombinaison) {

            bestPossible = run.generatBestPossible(bestPossible, result, machine);
            System.out.println("\n taille bestPossible 3 : " + bestPossible.size());

            System.out.println("\n Bilan -> taille possible : " + possible.size() + " ; taille bestPossible 3 : " + bestPossible.size());

            machine2 = run.getCombinaison(nbrTests, bestPossible, nbrRange);
            System.out.println("machine2 : " + machine2);
            machine = run.convertStringToArrayList(machine2);

            result = run.comparaisonChallengerS(def, machine);
            System.out.println("result 4 : " + run.displayResult(result) + "\n");

        }

    }

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

        System.out.println("retour compraison");
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

    public int convertArrayListIntegerToInt(ArrayList<Integer> result) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.size(); i++) {
            int num = result.get(i);
            sb.append(num);
        }

        return Integer.valueOf(sb.toString());

    }

    public ArrayList<String> generatAllPossible(int nbrCombinaison, int nbrRange) {

        ArrayList<String> possible = new ArrayList<String>();

//        String combinaisonFormat = "";
//
//        for (int i = 0; i < nbrCombinaison; i++) {
//
//            combinaisonFormat += 0;
//            System.out.println("combinaisonFormat : " + combinaisonFormat);
//        }
//
//        DecimalFormat format = new DecimalFormat(combinaisonFormat);
//
//        for (int i = 0; i <= Math.pow(nbrRange, nbrCombinaison); i++) {
//
//            String combinaison = format.format(i);
//
//            possible.add(combinaison);
//
//        }
        for (int i = 0; i <= nbrRange; i++) {

            for (int j = 0; j <= nbrRange; j++) {

                if (nbrCombinaison == 2) {

                    String str = (String.valueOf(i) + String.valueOf(j));
                    possible.add(str);

                } else {
                    for (int k = 0; k <= nbrRange; k++) {

                        for (int l = 0; l <= nbrRange; l++) {

                            if (nbrCombinaison == 4) {

                                String str = (String.valueOf(i) + String.valueOf(j) + String.valueOf(k) + String.valueOf(l));
                                possible.add(str);

                            } else {

                                for (int m = 0; m <= nbrRange; m++) {

                                    if (nbrCombinaison == 5) {

                                        String str = (String.valueOf(i) + String.valueOf(j) + String.valueOf(k) + String.valueOf(l) + String.valueOf(m));
                                        possible.add(str);

                                    } else {

                                        for (int n = 0; n <= nbrRange; n++) {

                                            if (nbrCombinaison == 6) {

                                                String str = (String.valueOf(i) + String.valueOf(j) + String.valueOf(k) + String.valueOf(l) + String.valueOf(m) + String.valueOf(n));
                                                possible.add(str);
                                            }

                                        }
                                    }

                                }
                            }

                        }

                    }
                }

            }
        }

        for (String combinaison : possible) {
            System.out.println("combinaison : " + combinaison);
        }

        return possible;

    }

    public String getCombinaison(int nbrTests, ArrayList<String> possible, int nbrRange) {

        String combinaison;

        if (nbrTests == 1 && nbrRange == 6) {

            combinaison = possible.get(1122);

        } else {

            int id = 0 + (int) (Math.random() * (possible.size() - 0)); // +1 ? ou -1 ?
            System.out.println("id : " + id);
            combinaison = possible.get(id);
        }
        System.out.println("retour get combinaison");
        return combinaison;

    }

    public ArrayList<String> generatBestPossible(ArrayList<String> possible, HashMap<String, String> result, ArrayList<Integer> machine) {

        HashMap<String, String> ri = new HashMap<String, String>();
        ArrayList<String> bestPossible = new ArrayList<String>();

        for (int i = 0; i < possible.size(); i++) {

            ri = comparaisonChallengerS(possible.get(i), machine);

            System.out.println("display resultat -> place : " + result.get("place") + " ; present : " + result.get("present"));
            System.out.println("display ri -> place : " + ri.get("place") + " ; present : " + ri.get("present"));

            if (result.get("place").equals(ri.get("place")) && result.get("present").equals(ri.get("present"))) {

                System.out.println("possible.get(i) : " + possible.get(i));
                bestPossible.add(possible.get(i));

            }

        }
        System.out.println("retour bestpossible");
        return bestPossible;

    }
}
