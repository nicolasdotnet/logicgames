/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.controller;

import com.github.nicolasdotnet.model.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * ControllerSearchMoreOrLessChallenger est la classe controller entre la vue et
 * le métier du jeu SearchMoreOrLess en mode Challenger
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class ControllerSearchMoreOrLessChallenger extends Controller {

    private static final Logger log = LogManager.getLogger(ControllerSearchMoreOrLessChallenger.class);
    private SearchMoreOrLessChallenger game = new SearchMoreOrLessChallenger();

    /**
     * Controller method that calls the model get solution combination method
     *
     * @param nbrDigits number of digts of the combination
     * @param nbrRange range of number for the combinaison
     * @param sizure value input by user
     * @return solution combination generate
     */
    public List<Integer> getSolutionCombination(int nbrDigits, int nbrRange, String sizure) {
        List<Integer> machine = new ArrayList<Integer>();
        Tools tools = new Tools();
        String temp;

        temp = game.getSolutionCombination(nbrDigits, nbrRange, sizure);
        machine = tools.convertStringToListInteger(temp);

        return machine;
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
        return game.comparison(nbrDigits, humain, machine, result);
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

}
