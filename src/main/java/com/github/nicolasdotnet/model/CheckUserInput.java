/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * CheckUserInput est la classe qui vérifie la sizure du joueur humain
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class CheckUserInput {
    
    private static final Logger log = LogManager.getLogger(CheckUserInput.class);

    /**
     * Number value input by user : test the sizure of the user for nbrCombinaison-> ToDo
     *
     * @param sizure input clavier user
     * @param nbrCombinaison number of digts of the combination
     * @return valid or invalid the sizure by a bolean
     */
    public Boolean inputError(String sizure, int nbrCombinaison) {

        boolean inputUser = false;
        int nbrTrue = 0;

        if (sizure.length() != nbrCombinaison) {

            nbrTrue++;
            System.out.print("Attention taille incorrecte !");
            log.error("Attention taille incorrecte !");

        } else {

            for (int i = 0; i < sizure.length(); i++) {

                char c = sizure.charAt(i);

                if (c >= '0' && c <= '9') {

                } else {

                    System.out.print("Attention saisi Alpha !! saisir un nombre !");

                    nbrTrue++;
                }

            }
        }

        if (nbrTrue > 0) {

            inputUser = true;
            log.info("Saisie invalide / FALSE !");

        }else{

        log.info("Saisie validée / TRUE !");}
        
                System.out.println("false : " + inputUser);
        System.out.println("nbrTrue++ : " + nbrTrue);

        return inputUser;

    }

    /**
     * Number value input by user : test the sizure of the user for nbrRange
     * @param sizure input clavier user
     * @param nbrRange range of number for the combinaison
     * @return valid or invalid the sizure by a bolean
     */
    public boolean inputErrorHome(String sizure, int nbrRange) {
        
        boolean inputUser = false;
        int nbrTrue = 0;

        if (sizure.length() != 1) {

            nbrTrue++;
            System.out.print("Attention taille incorrecte !");

        } else {

            for (int i = 0; i < sizure.length(); i++) {

                char c = sizure.charAt(i);

                if (c >= '0' && c <= (char)('0'+nbrRange)) {

                } else {

                    System.out.print("Attention saisi Alpha !! saisir un nombre !");
                    System.out.print("nbrRange value : "+(char)('0'+nbrRange));

                    nbrTrue++;
                }

            }
        }

        if (nbrTrue > 0) {

            inputUser = true;

        }
        
        System.out.println("false : " + inputUser);
        System.out.println("nbrTrue++ : " + nbrTrue);

        return inputUser;

    }
}
