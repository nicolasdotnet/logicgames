/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.controller;

import com.github.nicolasdotnet.model.Mastermind;
import com.github.nicolasdotnet.model.MastermindChallenger;
import com.github.nicolasdotnet.model.MastermindDefenseur;
import com.github.nicolasdotnet.model.MastermindDuel;
import java.util.HashMap;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author pi
 */
public class ControllerMastermind extends Controller {

    private static final Logger log = LogManager.getLogger(ControllerMastermind.class);
    private Mastermind game;
    private String mode;

    public ControllerMastermind(String mode) {
        this.mode = mode;

        switch (mode) {
            case "challenger":
                game = new MastermindChallenger();
                break;
            case "defenseur":
                game = new MastermindDefenseur();
                break;
            case "duel":
                game = new MastermindDuel();
        }
    }

    /**
     * Controller method that calls the model comparison method
     *
     * @param attac value of the attaquant
     * @param machine machive value
     * @return hasmap with number place and number present
     */
    public HashMap<String, String> getComparison(String attac, String machine) {
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
     * Controller method that calls the model get possible method
     *
     * @param nbrTests number of test of comparison
     * @param possible list of combination possible (complete or best)
     * @param nbrRange range of number for the combination
     * @param sizure value input by user
     * @return machine value
     */
    public String getGetPossible(int nbrTests, List<String> possible, int nbrRange, String sizure) {
        return game.getPossible(nbrTests, possible, nbrRange, sizure);

    }

}
