/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.controller;

import com.github.nicolasdotnet.model.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * ControllerMastermindChallenger est la classe controller entre la vue et le
 * métier du jeu Mastermind en mode Challenger
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class ControllerMastermindChallenger extends Controller {

    private static final Logger log = LogManager.getLogger(ControllerMastermindChallenger.class);
    private MastermindChallenger game = new MastermindChallenger();

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
    public List<Integer> getSolutionCombination(int nbrDigits, int nbrRange, String sizure) {

        List<Integer> machine = new ArrayList<Integer>();
        Tools tools = new Tools();
        String temp;

        temp = game.getSolutionCombination(nbrDigits, nbrRange, sizure);
        machine = tools.convertStringToListInteger(temp);

        return machine;
    }

}
