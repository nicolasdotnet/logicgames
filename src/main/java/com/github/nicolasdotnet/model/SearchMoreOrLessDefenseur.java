/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.model;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * SearchMoreOrLessDefenseur est la classe du jeux Recherche +/- en mode
 * Defenseur
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class SearchMoreOrLessDefenseur extends SearchMoreOrLess implements SearchMoreOrlLessAi {

    private static final Logger log = LogManager.getLogger(SearchMoreOrLessDefenseur.class);

    // methodes Ai
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

    /**
     * Generator function of possible combination for the machine
     *
     * @param randomRange max number limit and mini number limit for generate
     * random number
     * @param nbrDigits number of digts of the combination
     * @return possible combination in List integer
     */
    public List<Integer> generatePossible(int[][] randomRange, int nbrDigits) {

        List<Integer> inputMachine = new ArrayList<Integer>();

        for (int i = 0; i < nbrDigits; i++) {

            inputMachine.add((int) ((randomRange[1][i] - randomRange[0][i]) * Math.random()) + randomRange[0][i]);

            log.info("test A  : " + inputMachine.get(i) + " ");

        }

        return inputMachine;
    }
}
