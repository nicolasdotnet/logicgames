/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.model;

import java.util.HashMap;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * MastermindDefenseurTest est la classe de test du jeux Mastermind en mode
 * Defenseur
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class MastermindDefenseurTest {

    public MastermindDefenseurTest() {
    }

    /**
     * Test of getSolutionCombination method, of class MastermindDefenseur.
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
     * Test of comparison method, of class MastermindDefenseur.
     */
    @Test
    public void testComparisonPlace() {
        String attac = "0000";
        String solution = "0000";
        MastermindDefenseur instance = new MastermindDefenseur();
        HashMap<String, String> expResult = new HashMap<String, String>();
        expResult.put("place", "4");
        expResult.put("present", "0");
        HashMap<String, String> result = instance.comparison(attac, solution);
        assertEquals(expResult, result);

    }

    /**
     * Test of comparison method, of class MastermindDefenseur.
     */
    @Test
    public void testComparisonPresent() {
        String attac = "1234";
        String solution = "4321";
        MastermindDefenseur instance = new MastermindDefenseur();
        HashMap<String, String> expResult = new HashMap<String, String>();
        expResult.put("place", "0");
        expResult.put("present", "4");
        HashMap<String, String> result = instance.comparison(attac, solution);
        assertEquals(expResult, result);

    }

}
