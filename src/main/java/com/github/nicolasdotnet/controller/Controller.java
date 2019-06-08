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
     * Number value input by user : test the sizure of the user
     *
     * @param sizure input clavier user
     * @param nbrDigits number of digts of the combination
     * @param nbrRange range of number for the combinaison
     * @return valid or invalid the sizure by a bolean
     */
    public Boolean inputError(String sizure, int nbrDigits, int nbrRange) {

        boolean inputUser = false;
        int nbrTrue = 0;

        if (sizure.length() != nbrDigits) {

            nbrTrue++;
            log.error("Attention taille incorrecte !/ TRUE");

        } else {

            for (int i = 0; i < sizure.length(); i++) {

                char c = sizure.charAt(i);

                if (c >= '0' && c <= (char) ('0' + nbrRange)) {

                } else {

                    log.error("Attention les caractères du nombre invalide !! / TRUE");
                    log.error("Valeur max des caractères permit : " + (char) ('0' + nbrRange));

                    nbrTrue++;
                }

            }
        }

        if (nbrTrue > 0) {

            inputUser = true;

        } else {
            
            log.info("Saisie validée / False !");

        }

        return inputUser;

    }

    /**
     * Number value input by user : test the sizure of the user for Options
     *
     * @param nbrDigits number of digts of the combination
     * @param nbrRange range of number for the combinaison
     * @param nbrTours
     * @return valid or invalid the sizure by a bolean
     */
    public boolean inputErrorOptions(int nbrDigits, int nbrRange, int nbrTours) {

        boolean inputUser = false;
        int nbrTrue = 0;

        if (nbrDigits < 2 || nbrDigits > 9) {

            nbrTrue++;
            log.info("Taille invalide / TRUE !");

        } else if (nbrRange < 3 || nbrRange > 9) {

            nbrTrue++;
            log.info("Nombre de couleur invalide / TRUE !");

        } else if (nbrTours < 1) {

            nbrTrue++;
            log.info("Nombre de Tours invalide / TRUE !");

        }

        if (nbrTrue > 0) {

            inputUser = true;

        } else {
            
            log.info("Saisie validée / False !");

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
    public int[][] getGenerateRandomRangeNew(String result, String machine, int[][] randomRange) {
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

    /**
     * Controller method that calls the model generate all possible method
     *
     * @param nbrDigits number of digts of the combination
     * @param nbrRange range of number for the combination
     * @return complete list of combination possible
     */
    public List<String> getGenerateAllPossible(int nbrDigits, int nbrRange) {
        return tools.generateAllPossible(nbrDigits, nbrRange);
    }

    /**
     * Controller method that calls the model generate best possible method
     *
     * @param possible complete list of combination possible
     * @param result result hashmap of the comparison() function
     * @param machine machine value
     * @return list of best combination possible
     */
    public List<String> getGenerateBestPossible(List<String> possible, HashMap<String, String> result, String machine) {
        return tools.generateBestPossible(possible, result, machine);

    }

}
