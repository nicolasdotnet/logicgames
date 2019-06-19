/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.controller;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author pi
 */
public class ControllerTest {

    public ControllerTest() {
    }

    /**
     * Test of inputError method, of class Controller.
     */
    @Test
    public void testInputErrorTrue() {
        String sizure = "00";
        int nbrDigits = 4;
        int nbrRange = 6;
        Controller instance = new Controller();
        Boolean expResult = true;
        Boolean result = instance.inputError(sizure, nbrDigits, nbrRange);
        assertEquals(expResult, result);
    }

    /**
     * Test of inputError method, of class Controller.
     */
    @Test
    public void testInputErrorFalse() {
        String sizure = "9999";
        int nbrDigits = 4;
        int nbrRange = 9;
        Controller instance = new Controller();
        Boolean expResult = false;
        Boolean result = instance.inputError(sizure, nbrDigits, nbrRange);
        assertEquals(expResult, result);
    }

    /**
     * Test of inputErrorOptions method, of class Controller.
     */
    @Test
    public void testInputErrorOptionsTrue() {
        int nbrDigits = 1;
        int nbrRange = 2;
        int nbrTours = 2;
        Controller instance = new Controller();
        boolean expResult = true;
        boolean result = instance.inputErrorOptions(nbrDigits, nbrRange, nbrTours);
        assertEquals(expResult, result);
    }

    /**
     * Test of inputErrorOptions method, of class Controller.
     */
    @Test
    public void testInputErrorOptionsFalse() {
        int nbrDigits = 4;
        int nbrRange = 6;
        int nbrTours = 9;
        Controller instance = new Controller();
        boolean expResult = false;
        boolean result = instance.inputErrorOptions(nbrDigits, nbrRange, nbrTours);
        assertEquals(expResult, result);
    }

}
