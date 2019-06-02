/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
     * Test of generateAllPossible method, of class MastermindDefenseur.
     */
    @Test
    public void testGenerateAllPossible() {
        int nbrDigits = 2;
        int nbrRange = 2;
        MastermindDefenseur instance = new MastermindDefenseur();
        List<String> expResult = new ArrayList<>();
        expResult.add("00");
        expResult.add("01");
        expResult.add("10");
        expResult.add("11");
        List<String> result = instance.generateAllPossible(nbrDigits, nbrRange);
        assertEquals(expResult, result);
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
        List<Integer> solution = new ArrayList<>();
        solution.add(0);
        solution.add(0);
        solution.add(0);
        solution.add(0);
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
        List<Integer> solution = new ArrayList<>();
        solution.add(4);
        solution.add(3);
        solution.add(2);
        solution.add(1);
        MastermindDefenseur instance = new MastermindDefenseur();
        HashMap<String, String> expResult = new HashMap<String, String>();
        expResult.put("place", "0");
        expResult.put("present", "4");
        HashMap<String, String> result = instance.comparison(attac, solution);
        assertEquals(expResult, result);

    }

}
