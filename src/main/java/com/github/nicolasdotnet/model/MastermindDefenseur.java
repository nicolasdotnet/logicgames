/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.model;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * MastermindDefender is the class of the Mastermind game in Defender mode.
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class MastermindDefenseur extends Mastermind {

    private static final Logger log = LogManager.getLogger(MastermindDefenseur.class);

    /**
     * Generator function of possible combination
     *
     * @param nbrTests number of test of comparison
     * @param possible list of combination possible (complete or best)
     * @param nbrRange range of number for the combination
     * @param nbrDigits number of digts of the combination
     * @param sizure value input by user
     * @return machine value
     */
    @Override
    public String getPossible(int nbrTests, List<String> possible, int nbrRange, int nbrDigits, String sizure) {

        String combination;

        if (nbrTests == 1 && nbrRange == 6 && nbrDigits == 4) {

            combination = Integer.toString(1122);

        } else {

            int id = 0 + (int) (Math.random() * (possible.size() - 0));
            log.info("id : " + id);
            combination = possible.get(id);
        }
        return combination;

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
