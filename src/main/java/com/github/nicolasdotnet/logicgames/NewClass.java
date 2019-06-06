/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.logicgames;

import com.github.nicolasdotnet.model.Tools;

/**
 *
 * @author pi
 */
public class NewClass {

    public static void main(String[] args) {

        // String en entrée et en sortie car counter est en string
        String a = "1234";
        boolean r = a.charAt(1) < a.charAt(2);

        System.out.println("r : " + r);
        System.out.println("char : " + a.charAt(1) + " " + a.charAt(2));

        NewClass root = new NewClass();
        Tools tools = Tools.getInstance();
        int[][] randomRange = tools.generateRandomRangeInitial(4, 9);
        String possible = root.getPossible(randomRange, 4, a);
        System.out.println("possible : " + possible);

    }

    public String getPossible(int[][] randomRange, int nbrDigits, String sizure) {

        StringBuilder inputMachine = new StringBuilder();

        for (int i = 0; i < nbrDigits; i++) {

            inputMachine.append((int) ((randomRange[1][i] - randomRange[0][i]) * Math.random()) + randomRange[0][i]);

        }

        return String.join("", inputMachine);
    }

    public boolean inputErrorOptions(int nbrDigits, int nbrRange, int nbrTours) {
        
        boolean inputUser = false;
        int nbrTrue = 0;

        if (nbrDigits < 2 || nbrDigits > 9) {

            nbrTrue++;
            //            log.info("Taille invalide / FALSE !");
        } else if (nbrRange < 4 || nbrRange > 10) {

            nbrTrue++;
            //            log.info("Nombre de couleur invalide / FALSE !");

        } else if (nbrTours < 1) {

            nbrTrue++;
            //            log.info("Nombre de Tours invalide / FALSE !");

        }

        if (nbrTrue == 0) {

            inputUser = true;

        } else {

//            log.info("Saisie validée / TRUE !");
        }

        return inputUser;

    }

}
