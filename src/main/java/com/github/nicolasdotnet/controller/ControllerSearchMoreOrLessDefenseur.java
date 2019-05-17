/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.controller;

import com.github.nicolasdotnet.model.*;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * ControllerSearchMoreOrLessDefenseur est la classe controller entre la vue et
 * le métier du jeu SearchMoreOrLess en mode Défenseur
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class ControllerSearchMoreOrLessDefenseur extends Controller {

    private static final Logger log = LogManager.getLogger(ControllerSearchMoreOrLessDefenseur.class);
    private SearchMoreOrLessDefenseur game = new SearchMoreOrLessDefenseur();

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
     * Controller method that calls the model comparison method
     *
     * @param nbrDigits number of digts of the combination
     * @param humain value table of the attacker
     * @param machine value table of the defender
     * @param result result arrayList of the comparison() function for recursive
     * method
     * @return result of the comparison
     */
    public List<String> getComparison(int nbrDigits, List<Integer> humain, List<Integer> machine, List<String> result) {
        return (game.comparison(nbrDigits, humain, machine, result));
    }

    /**
     * Controller method that calls the model the equal count method
     *
     * @param result result string of the comparison() function
     * @return number of equals to int
     */
    public int getEqualCounter(String result) {
        return game.equalCounter(result);
    }

    /**
     * Controller method that calls the model generate possible combination for
     * the machine
     *
     * @param randomRange max number limit and mini number limit for generate
     * random number
     * @param nbrDigits number of digts of the combination
     * @return possible combination in List integer
     */
    public List<Integer> getGeneratePossible(int[][] randomRange, int nbrDigits) {
        return game.generatePossible(randomRange, nbrDigits);
    }

}
