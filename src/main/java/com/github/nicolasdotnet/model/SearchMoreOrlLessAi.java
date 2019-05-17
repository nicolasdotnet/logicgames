/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.model;

import java.util.List;

/**
 * SearchMoreOrLessAi est l'interface métier de l'Ai du jeux Recherche +/-.
 *
 * @author nicolasdotnet
 * @version Draft
 * @since 2019
 */
public interface SearchMoreOrlLessAi {

    /**
     * Generator function of possible combination for the machine
     *
     * @param randomRange max number limit and mini number limit for generate
     * random number
     * @param nbrDigits number of digts of the combination
     * @return possible combination in List integer
     */
    public List<Integer> generatePossible(int[][] randomRange, int nbrDigits);

}
