/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.controller;

import com.github.nicolasdotnet.model.*;
import java.util.HashMap;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * Controller est la super classe controlleur
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class Controller {

    private static final Logger log = LogManager.getLogger(Controller.class);
    private Tools tools = Tools.getInstance();

    /**
     * Number value input by user : test the sizure of the user for nbrDigits->
     * ToDo
     *
     * @param sizure input clavier user
     * @param nbrDigits number of digts of the combination
     * @return valid or invalid the sizure by a bolean
     */
    public Boolean inputError(String sizure, int nbrDigits) {

        boolean inputUser = false;
        int nbrTrue = 0;

        if (sizure.length() != nbrDigits) {

            nbrTrue++;
            log.error("Attention taille incorrecte !");

        } else {

            for (int i = 0; i < sizure.length(); i++) {

                char c = sizure.charAt(i);

                if (c >= '0' && c <= '9') {

                } else {

                    log.error("Attention saisi Alpha !! il faut saisir un nombre !");

                    nbrTrue++;
                }

            }
        }

        if (nbrTrue > 0) {

            inputUser = true;
            log.info("Saisie invalide / FALSE !");

        } else {

            log.info("Saisie validée / TRUE !");
        }

        return inputUser;

    }

    /**
     * Number value input by user : test the sizure of the user for nbrRange
     *
     * @param sizure input clavier user
     * @param nbrRange range of number for the combinaison
     * @return valid or invalid the sizure by a bolean
     */
    public boolean inputErrorHome(String sizure, int nbrRange) {

        boolean inputUser = false;
        int nbrTrue = 0;

        if (sizure.length() != 1) {

            nbrTrue++;
            log.error("Attention taille incorrecte !");

        } else {

            for (int i = 0; i < sizure.length(); i++) {

                char c = sizure.charAt(i);

                if (c >= '0' && c <= (char) ('0' + nbrRange)) {

                } else {

                    log.error("Attention saisi Alpha !! saisir un nombre !");
                    log.error("nbrRange value : " + (char) ('0' + nbrRange));

                    nbrTrue++;
                }

            }
        }

        if (nbrTrue > 0) {

            inputUser = true;
            log.info("Saisie invalide / FALSE !");

        } else {

            log.info("Saisie validée / TRUE !");
        }

        return inputUser;

    }

    /**
     * Controller method that calls the model new random range generation method
     *
     * @param result result of the comparison function
     * @param machine value table of the machine attaquant
     * @param randomRange
     * @return new random range
     */
    public int[][] getGenerateRandomRangeNew(List<String> result, List<Integer> machine, int[][] randomRange) {
        return tools.generateRandomRangeNew(result, machine, randomRange);
    }

    /**
     * Controller method that calls the model initial random range generation
     * method
     *
     * @param nbrDigits number of digts of the combination
     * @param nbrRange range of number for the combination
     * @return initial random range
     */
    public int[][] getGenerateRandomRangeInitial(int nbrDigits, int nbrRange) {
        return tools.generateRandomRangeInitial(nbrDigits, nbrRange);
    }

    /**
     * Controller method that calls the model convert input String to List
     * Integer method
     *
     * @param value in String
     * @return value in List Integer
     */
    public List<Integer> getConvertStringToListInteger(String value) {
        return tools.convertStringToListInteger(value);
    }

    /**
     * Controller method that calls the model convert input List String to
     * String method
     *
     * @param value in List String
     * @return value in String
     */
    public String getConvertListToString(List<String> value) {
        return tools.convertListToString(value);
    }

    /**
     * Controller method that calls the model convert input List Integer to
     * String method
     *
     * @param value in List Integer
     * @return value in String
     */
    public String getConvertListIntegerToString(List<Integer> value) {
        return tools.convertListIntegerToString(value);
    }

    /**
     * Controller method that calls the model instance propertie generation
     * method
     *
     * @return propertie objet
     */
    public Propertie getInstancePropertie() {
        Propertie propertie = new Propertie();
        return propertie;

    }

    /**
     * Controller method that calls the model parameter backup generation method
     *
     * @param title
     * @param nbrDigits number of digts of the combination
     * @param nbrTours
     * @param nbrRange range of number for the combination
     * @param modeDev
     * @return parameter backup
     */
    public HashMap<String, String> getParameterBackup(String title, int nbrDigits, int nbrTours, int nbrRange, boolean modeDev) {

        return tools.parameterBackup(title, nbrDigits, nbrTours, nbrRange, modeDev);

    }

}
