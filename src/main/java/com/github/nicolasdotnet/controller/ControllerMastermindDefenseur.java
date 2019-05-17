/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.controller;

import com.github.nicolasdotnet.model.*;
import java.util.HashMap;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * ControllerMastermindDefenseur est la classe controller entre la vue et le
 * métier du jeu Mastermind en mode Défenseur
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class ControllerMastermindDefenseur extends Controller {

    private static final Logger log = LogManager.getLogger(ControllerMastermindDefenseur.class);
    private MastermindDefenseur game = new MastermindDefenseur();

    /**
     * Controller method that calls the model comparison method
     *
     * @param attac value of the attaquant
     * @param machine machive value
     * @return hasmap with number place and number present
     */
    public HashMap<String, String> getComparison(String attac, List<Integer> machine) {
        return game.comparison(attac, machine);
    }

    /**
     * Controller method that calls the model display result method
     *
     * @param result hasmap with number place and number present
     * @return a string for display
     */
    public String getDisplayResult(HashMap<String, String> result) {
        return game.displayResult(result);
    }

    /**
     * Controller method that calls the model get solution combination method
     *
     * @param nbrDigits number of digts of the combination
     * @param nbrRange range of number for the combinaison
     * @param sizure value input by user
     * @return solution combination generate
     */
    public String getSolutionCombination(int nbrDigits, int nbrRange, String sizure) {

        return game.getSolutionCombination(nbrDigits, nbrRange, sizure);
    }

    /**
     * Controller method that calls the model generate best possible method
     *
     * @param possible complete list of combination possible
     * @param result result hashmap of the comparison() function
     * @param machine machine value
     * @return list of best combination possible
     */
    public List<String> getGenerateBestPossible(List<String> possible, HashMap<String, String> result, List<Integer> machine) {
        return game.generateBestPossible(possible, result, machine);

    }

    /**
     * Controller method that calls the model get possible method
     *
     * @param nbrTests number of test of comparison
     * @param possible list of combination possible (complete or best)
     * @param nbrRange range of number for the combination
     * @return machine value
     */
    public String getGetPossible(int nbrTests, List<String> possible, int nbrRange) {
        return game.getPossible(nbrTests, possible, nbrRange);

    }

    /**
     * Controller method that calls the model generate all possible method
     *
     * @param nbrDigits number of digts of the combination
     * @param nbrRange range of number for the combination
     * @return complete list of combination possible
     */
    public List<String> getGenerateAllPossible(int nbrDigits, int nbrRange) {
        return game.generateAllPossible(nbrDigits, nbrRange);
    }

}
