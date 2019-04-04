/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.model;

/**
 *
 * @author pi
 */
public class CheckUserInput {

    /**
     * Number value input by user : test the sizure of the user -> ToDo
     *
     * @param saisie input clavier user
     * @return number user to String
     */
    public Boolean inputError(String saisie, int nbrCombinaison) {

        boolean inputUser = false;
        int nbrTrue = 0;

        if (saisie.length() != nbrCombinaison) {

            nbrTrue++;
            System.out.print("Attention taille incorrecte !");

        } else {

            for (int i = 0; i < saisie.length(); i++) {

                char c = saisie.charAt(i);

                if (c >= '0' && c <= '9') {

                } else {

                    System.out.print("Attention saisi Alpha !! saisir un nombre !");

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

    public boolean inputErrorHome(String saisie, int range) {
        
        boolean inputUser = false;
        int nbrTrue = 0;

        if (saisie.length() != 1) {

            nbrTrue++;
            System.out.print("Attention taille incorrecte !");

        } else {

            for (int i = 0; i < saisie.length(); i++) {

                char c = saisie.charAt(i);

                if (c >= '0' && c <= (char)('0'+range)) {

                } else {

                    System.out.print("Attention saisi Alpha !! saisir un nombre !");
                    System.out.print("range value : "+(char)('0'+range));

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
