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
 * ControllerSearchMoreOrLess est la classe controller entre la vue et
 le métier du jeu SearchMoreOrLess en mode Challenger
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class ControllerSearchMoreOrLess extends Controller {

    private static final Logger log = LogManager.getLogger(ControllerSearchMoreOrLess.class);
    private SearchMoreOrLess game;
    private String mode;

    public ControllerSearchMoreOrLess(String mode) {
        this.mode = mode;

        switch (mode) {
            case "challenger":
                game = new SearchMoreOrLessChallenger();
                break;
            case "defenseur":
                game = new SearchMoreOrLessDefenseur();
                break;
            case "duel":
                game = new SearchMoreOrLessDuel();
        }
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
    public String getGetPossible(int[][] randomRange, int nbrDigits, String sizure) {
        return game.getPossible(randomRange, nbrDigits, sizure);
    }

    /**
     * Controller method that calls the model comparison method
     *
     * @param nbrDigits number of digts of the combination
     * @param humain value of the attacker
     * @param machine value of the defender
     * @param result result of the comparison() function for recursive method
     * @return result of the comparison
     */
    public String getComparison(int nbrDigits, String humain, String machine, String result) {
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
