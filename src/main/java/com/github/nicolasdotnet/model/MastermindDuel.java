/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.model;

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
public class MastermindDuel extends Mastermind {

    private MastermindAi inter = new MastermindDefenseur();

    public static void main(String[] args) {

        MastermindDuel run = new MastermindDuel();
        Tools tools = Tools.getInstance();

        String def = "22";
        int nbrDigits = 2;
        int nbrRange = 3;
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

    /**
     * Generator function of complete list of combination possible
     *
     * @param nbrDigits number of digts of the combination
     * @param nbrRange range of number for the combination
     * @return complete list of combination possible
     */
    public List<String> generateAllPossible(int nbrDigits, int nbrRange) {

        return inter.generateAllPossible(nbrDigits, nbrRange);
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

        return inter.getPossible(nbrTests, possible, nbrRange);

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

        return inter.generateBestPossible(possible, result, machine);
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

        // "null"
        if (sizure == "null") {

            // randomLimit
            int[][] randomLimit = new int[2][nbrDigits];

            for (int i = 0; i < 2; i++) {

                for (int j = 0; j < nbrDigits; j++) {

                    if (i == 0) {

                        // Min value limit
                        randomLimit[i][j] = 0;

                    } else {

                        // Max value limit
                        randomLimit[i][j] = nbrRange;

                    }

                }

            }

            // inputmachine
            List<Integer> inputMachine = new ArrayList<Integer>();

            for (int i = 0; i < nbrDigits; i++) {

                inputMachine.add((int) ((randomLimit[1][i] - randomLimit[0][i]) * Math.random()) + randomLimit[0][i]);

                System.out.println("test A  : " + inputMachine.get(i) + " ");

            }

            // convertListIntegerToString
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < inputMachine.size(); i++) {
                int num = inputMachine.get(i);
                sb.append(num);
            }

            sizure = sb.toString();
            System.out.println("sizure : " + sizure);
        }
        return sizure;
    }
}
