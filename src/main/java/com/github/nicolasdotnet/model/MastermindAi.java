/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.model;

import java.util.HashMap;
import java.util.List;

/**
 * Mastermind est l'interface AI du joueur machine du jeux Mastermind.
 *
 * @author nicolasdotnet
 * @version Draft
 * @since 2019
 */
public interface MastermindAi {
    
    /**
     * Generator function of complete list of combination possible
     *
     * @param nbrDigits number of digts of the combination
     * @param nbrRange range of number for the combination
     * @return complete list of combination possible
     */
    public List<String> generateAllPossible(int nbrDigits, int nbrRange);

    /**
     * Random selection of machine combination :
     *
     * @param nbrTests number of test of comparison
     * @param possible list of combination possible (complete or best)
     * @param nbrRange range of number for the combination
     * @return machine value
     */
    public String getPossible(int nbrTests, List<String> possible, int nbrRange);

    /**
     * Generator function of list of best combination possible
     *
     * @param possible complete list of combination possible
     * @param result result hashmap of the comparison() function
     * @param machine machine value
     * @return list of best combination possible
     */
    public List<String> generateBestPossible(List<String> possible, HashMap<String, String> result, List<Integer> machine);
    
}
