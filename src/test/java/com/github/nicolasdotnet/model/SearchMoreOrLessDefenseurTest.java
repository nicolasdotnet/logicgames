/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * SearchMoreOrLessDefenseurTest est la classe de test du jeux Recherche +/- en
 * mode Defenseur
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class SearchMoreOrLessDefenseurTest {

    public SearchMoreOrLessDefenseurTest() {
    }

    /**
     * Test of getSolutionCombination method, of class
     * SearchMoreOrLessDefenseur.
     */
    @Test
    public void testGetSolutionCombination() {
        int nbrDigits = 2;
        int nbrRange = 2;
        String sizure = "99";
        MastermindDefenseur instance = new MastermindDefenseur();
        String expResult = "99";
        String result = instance.getSolutionCombination(nbrDigits, nbrRange, sizure);
        assertEquals(expResult, result);
    }

    /**
     * Test of equalCounter method, of class SearchMoreOrLessDefenseur.
     */
    @Test
    public void testEqualCounter() {
        String resultat = "==+==";
        SearchMoreOrLessDefenseur instance = new SearchMoreOrLessDefenseur();
        int expResult = 4;
        int result = instance.equalCounter(resultat);
        assertEquals(expResult, result);
    }

    /**
     * Test of comparison method, of class SearchMoreOrLessDefenseur.
     */
    @Test
    public void testComparisonAll() {
        int nbrDigits = 4;
        String attac = "0101";
        String def = "0110";
        String resultIni = "";
        String result;
        SearchMoreOrLessDefenseur instance = new SearchMoreOrLessDefenseur();
        String expResult = "==+-";
        result = instance.comparison(nbrDigits, attac, def, resultIni);
        assertEquals(expResult, result);

    }

}
