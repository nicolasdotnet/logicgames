/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * MastermindDefenseur est la classe du jeux Mastermind en mode Defenseur
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class MastermindDefenseur extends Mastermind implements MastermindAi {

    public static void main(String[] args) {

        MastermindDefenseur run = new MastermindDefenseur();
        Tools tools = Tools.getInstance();

        String def = "11";
        int nbrDigits = 2; // de 0 a 2 inclu dc 0;1;2
        int nbrRange = 2;
        int nbrTests = 1;
        String machine2;

        // tour 1 : 
        List<String> possible = run.generateAllPossible(nbrDigits, nbrRange);
        System.out.println("taille possible : " + possible.size());

        machine2 = run.getPossible(nbrTests, possible, nbrRange);
        System.out.println("machine2 : " + machine2);
        List<Integer> machine = tools.convertStringToList(machine2);

        HashMap<String, String> result = run.comparison(def, machine);
        System.out.println("result: " + run.displayResult(result) + "\n");

        // tour 2 : 
        List<String> bestPossible = run.generateBestPossible(possible, result, machine);
        System.out.println("\n taille bestPossible 1 : " + bestPossible.size());

        System.out.println("\n Bilan -> taille possible : " + possible.size() + " ; taille bestPossible : " + bestPossible.size());

        machine2 = run.getPossible(nbrTests, bestPossible, nbrRange);
        System.out.println("machine2 : " + machine2);
        machine = tools.convertStringToList(machine2);

        result = run.comparison(def, machine);
        System.out.println("result 2 : " + run.displayResult(result) + "\n");

        // tour 3 : 
        bestPossible = run.generateBestPossible(bestPossible, result, machine);
        System.out.println("\n taille bestPossible 2 : " + bestPossible.size());

        System.out.println("\n Bilan -> taille possible : " + possible.size() + " ; taille bestPossible 2 : " + bestPossible.size());

        machine2 = run.getPossible(nbrTests, bestPossible, nbrRange);
        System.out.println("machine2 : " + machine2);
        machine = tools.convertStringToList(machine2);

        result = run.comparison(def, machine);
        System.out.println("result 3 : " + run.displayResult(result) + "\n");

        // tour 4 :
        if (Integer.parseInt(result.get("place")) != nbrDigits) {

            bestPossible = run.generateBestPossible(bestPossible, result, machine);
            System.out.println("\n taille bestPossible 3 : " + bestPossible.size());

            System.out.println("\n Bilan -> taille possible : " + possible.size() + " ; taille bestPossible 3 : " + bestPossible.size());

            machine2 = run.getPossible(nbrTests, bestPossible, nbrRange);
            System.out.println("machine2 : " + machine2);
            machine = tools.convertStringToList(machine2);

            result = run.comparison(def, machine);
            System.out.println("result 4 : " + run.displayResult(result) + "\n");

        }

    }

    // methodes AI
    /**
     * Generator function of complete list of combination possible
     *
     * @param nbrDigits number of digts of the combination
     * @param nbrRange range of number for the combination
     * @return complete list of combination possible
     */
    public List<String> generateAllPossible(int nbrDigits, int nbrRange) {
        List<String> possible = new ArrayList<String>();

        String combinationFormat = "";

        for (int i = 0; i < nbrDigits; i++) {

            combinationFormat += 0;
            System.out.println("combinationFormat : " + combinationFormat);
        }

        DecimalFormat format = new DecimalFormat(combinationFormat);

        for (int i = 0; i <= (Math.pow(nbrRange, nbrDigits) - 1); i++) {

            System.out.println("combinaison base 10 : " + i);

            int item = Integer.parseInt(Integer.toString(i, nbrRange));

            String combination = format.format(item);

            System.out.println("combination base " + nbrRange + " : " + combination);

            possible.add(combination);

        }

        return possible;
    }

    /**
     * Random selection of machine combination :
     *
     * @param nbrTests number of test of comparison
     * @param possible list of combination possible (complete or best)
     * @param nbrRange range of number for the combination
     * @return machine value
     */
    public String getPossible(int nbrTests, List<String> possible, int nbrRange) {

        String combination;

        if (nbrTests == 1 && nbrRange == 6) {

            combination = possible.get(1122);

        } else {

            int id = 0 + (int) (Math.random() * (possible.size() - 0)); // +1 ? ou -1 ?
            System.out.println("id : " + id);
            combination = possible.get(id);
        }
        System.out.println("retour get combinaison");
        return combination;

    }

    /**
     * Generator function of list of best combination possible
     *
     * @param possible complete list of combination possible
     * @param result result hashmap of the comparison() function
     * @param machine machine value
     * @return list of best combination possible
     */
    public List<String> generateBestPossible(List<String> possible, HashMap<String, String> result, List<Integer> machine) {

        HashMap<String, String> ri = new HashMap<String, String>();
        List<String> bestPossible = new ArrayList<String>();

        for (int i = 0; i < possible.size(); i++) {

            ri = comparison(possible.get(i), machine);

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

    /**
     * Generator function of solution combination
     *
     * @param nbrDigits number of digts of the combination
     * @param nbrRange range of number for the combinaison
     * @param sizure value input by user
     * @return solution combination generate
     */
    @Override
    public String getSolutionCombination(int nbrDigits, int nbrRange, String sizure) {
        return sizure;
    }
}
