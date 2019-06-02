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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * MastermindDefenseur est la classe du jeux Mastermind en mode Defenseur
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class MastermindDefenseur extends Mastermind implements MastermindAi {

    private static final Logger log = LogManager.getLogger(MastermindDefenseur.class);
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
            log.info("combinationFormat : " + combinationFormat);
        }

        DecimalFormat format = new DecimalFormat(combinationFormat);

        for (int i = 0; i <= (Math.pow(nbrRange, nbrDigits) - 1); i++) {

            log.info("combinaison base 10 : " + i);

            int item = Integer.parseInt(Integer.toString(i, nbrRange));

            String combination = format.format(item);

            log.info("combination base " + nbrRange + " : " + combination);

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
            log.info("id : " + id);
            combination = possible.get(id);
        }
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

            log.info("display resultat -> place : " + result.get("place") + " ; present : " + result.get("present"));
            log.info("display ri -> place : " + ri.get("place") + " ; present : " + ri.get("present"));

            if (result.get("place").equals(ri.get("place")) && result.get("present").equals(ri.get("present"))) {

                log.info("possible.get(i) : " + possible.get(i));
                bestPossible.add(possible.get(i));

            }

        }

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
