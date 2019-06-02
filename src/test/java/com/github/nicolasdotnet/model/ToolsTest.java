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
     * Test of convertStringToListInteger method, of class Tools.
     */
    @Test
    public void testConvertStringToListInteger() {
        String inputUser = "1234";
        Tools instance = Tools.getInstance();
        List<Integer> expResult = new ArrayList<>();
        expResult.add(1);
        expResult.add(2);
        expResult.add(3);
        expResult.add(4);
        List<Integer> result = instance.convertStringToListInteger(inputUser);
        assertEquals(expResult, result);
    }

    /**
     * Test of convertListToString method, of class Tools.
     */
    @Test
    public void testConvertListToString() {
        List<String> result_2 = new ArrayList<>();
        result_2.add("1");
        result_2.add("2");
        result_2.add("3");
        result_2.add("4");
        Tools instance = Tools.getInstance();
        String expResult = "1234";
        String result = instance.convertListToString(result_2);
        assertEquals(expResult, result);
    }
}
