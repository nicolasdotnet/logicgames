/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * MastermindDefenseur est la classe du jeux Mastermind en mode Defenseur
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class MastermindDefenseur extends Mastermind {

    public static void main(String[] args) {

        MastermindDefenseur run = new MastermindDefenseur();

        String def = "11";
        int nbrCombinaison = 2; // de 0 a 2 inclu dc 0;1;2
        int nbrRange = 2;
        int nbrTests = 1;
        String machine2;

        // tour 1 : 
        ArrayList<String> possible = run.generatAllPossible(nbrCombinaison, nbrRange);
        System.out.println("taille possible : " + possible.size());

        machine2 = run.getCombinaison(nbrTests, possible, nbrRange);
        System.out.println("machine2 : " + machine2);
        ArrayList<Integer> machine = run.convertStringToArrayList(machine2);

        HashMap<String, String> result = run.comparaison(def, machine);
        System.out.println("result: " + run.displayResult(result) + "\n");

        // tour 2 : 
        ArrayList<String> bestPossible = run.generatBestPossible(possible, result, machine);
        System.out.println("\n taille bestPossible 1 : " + bestPossible.size());

        System.out.println("\n Bilan -> taille possible : " + possible.size() + " ; taille bestPossible : " + bestPossible.size());

        machine2 = run.getCombinaison(nbrTests, bestPossible, nbrRange);
        System.out.println("machine2 : " + machine2);
        machine = run.convertStringToArrayList(machine2);

        result = run.comparaison(def, machine);
        System.out.println("result 2 : " + run.displayResult(result) + "\n");

        // tour 3 : 
        bestPossible = run.generatBestPossible(bestPossible, result, machine);
        System.out.println("\n taille bestPossible 2 : " + bestPossible.size());

        System.out.println("\n Bilan -> taille possible : " + possible.size() + " ; taille bestPossible 2 : " + bestPossible.size());

        machine2 = run.getCombinaison(nbrTests, bestPossible, nbrRange);
        System.out.println("machine2 : " + machine2);
        machine = run.convertStringToArrayList(machine2);

        result = run.comparaison(def, machine);
        System.out.println("result 3 : " + run.displayResult(result) + "\n");

        // tour 4 :
        if (Integer.parseInt(result.get("place")) != nbrCombinaison) {

            bestPossible = run.generatBestPossible(bestPossible, result, machine);
            System.out.println("\n taille bestPossible 3 : " + bestPossible.size());

            System.out.println("\n Bilan -> taille possible : " + possible.size() + " ; taille bestPossible 3 : " + bestPossible.size());

            machine2 = run.getCombinaison(nbrTests, bestPossible, nbrRange);
            System.out.println("machine2 : " + machine2);
            machine = run.convertStringToArrayList(machine2);

            result = run.comparaison(def, machine);
            System.out.println("result 4 : " + run.displayResult(result) + "\n");

        }

    }

    /**
     * Complete list of combinaison possible generator function :
     *
     * @param nbrCombinaison number of digts of the combination
     * @param nbrRange range of number for the combinaison
     * @return complete list of combinaison possible
     */
    public ArrayList<String> generatAllPossible(int nbrCombinaison, int nbrRange) {
        ArrayList<String> possible = new ArrayList<String>();

        String combinaisonFormat = "";

        for (int i = 0; i < nbrCombinaison; i++) {

            combinaisonFormat += 0;
            System.out.println("combinaisonFormat : " + combinaisonFormat);
        }

        DecimalFormat format = new DecimalFormat(combinaisonFormat);

        for (int i = 0; i <= (Math.pow(nbrRange, nbrCombinaison) - 1); i++) {

            System.out.println("combinaison base 10 : " + i);

            int item = Integer.parseInt(Integer.toString(i, nbrRange));

            String combinaison = format.format(item);

            System.out.println("combinaison base " + nbrRange + " : " + combinaison);

            possible.add(combinaison);

        }

        return possible;
    }

    /**
     * Random selection of machine combinaison :
     *
     * @param nbrTests number of test of comparison
     * @param possible list of combinaison possible (complete or best)
     * @param nbrRange range of number for the combinaison
     * @return machine value
     */
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

    /**
     * List of best combinaison possible generator function :
     *
     * @param possible complete list of combinaison possible
     * @param result result hashmap of the comparison() function
     * @param machine machine value
     * @return list of best combinaison possible
     */
    public ArrayList<String> generatBestPossible(ArrayList<String> possible, HashMap<String, String> result, ArrayList<Integer> machine) {

        HashMap<String, String> ri = new HashMap<String, String>();
        ArrayList<String> bestPossible = new ArrayList<String>();

        for (int i = 0; i < possible.size(); i++) {

            ri = comparaison(possible.get(i), machine);

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
