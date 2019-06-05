/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * ToolsTest est la classe de test utilitaire des jeux SearchMoreOrless et
 * Mastermind.
 *
 * @author nicolasdotnet
 * @version Draft
 * @since 2019
 */
public class ToolsTest {

    public ToolsTest() {
    }

    /**
     * Test of generateAllPossible method, of class Tools.
     */
    @Test
    public void testGenerateAllPossible() {
        int nbrDigits = 2;
        int nbrRange = 2;
        Tools instance = Tools.getInstance();
        List<String> expResult = new ArrayList<>();
        expResult.add("00");
        expResult.add("01");
        expResult.add("10");
        expResult.add("11");
        List<String> result = instance.generateAllPossible(nbrDigits, nbrRange);
        assertEquals(expResult, result);
    }
}
