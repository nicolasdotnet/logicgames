/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.model;

import com.google.common.annotations.VisibleForTesting;
import java.util.Arrays;
import java.util.Scanner;

/**
 * SearchMoreOrLess est la classe métier du jeux Recherche +/-.
 *
 * @author nicolasdotnet
 * @version Draft (with again the tests by Println Sorry ...)
 * @since 2019
 */
public class SearchMoreOrLess {

    public static void main(String[] args) {

        SearchMoreOrLess a = new SearchMoreOrLess();

        String inputUser = a.inputUser();

        a.print(inputUser);

        a.runDefenseur(a.convertStringToTabINt(inputUser, inputUser.length()), inputUser.length());

    }

    /**
     * Number value input by user :
     *
     * @return number user to String
     */
    private String inputUser() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Entrer une combinaison secrète : ");

        String inputUser = sc.nextLine();

        return inputUser;

    }

    /**
     * function random for generate number for user Machine :
     *
     * @param randomLimit max number limit and mini number limit for generate
     * random number
     * @param nbrCombinaison number of digts of the combination generated
     * @return random number int
     */
    private int[] inputMachine(int[][] randomLimit, int nbrCombinaison) {

        int[] inputMachine = new int[nbrCombinaison];

        for (int i = 0; i <= nbrCombinaison; i++) {

            inputMachine[i] = (int) ((randomLimit[1][i] - randomLimit[0][i]) * Math.random()) + randomLimit[0][i];

            System.out.print(inputMachine[i] + " ");

        };

        return inputMachine;

    }

    /**
     * convert function input user to String :
     *
     * @param inputUser Number value input by user
     * @param length Number of digts of the number value
     * @return Number value to int table
     */
    @VisibleForTesting
    public int[] convertStringToTabINt(String inputUser, int length) {

        int[] convert = new int[length];

        // Cast String to Int
        for (int i = 0; i <= length - 1; i++) {

            char car = inputUser.charAt(i);

            convert[i] = Character.getNumericValue(inputUser.charAt(i));
        }

        return convert;

    }

    /**
     * convert function result table of the comparison to String :
     *
     * @param resultat Result table of the comparison() function
     * @return resultat to String
     */
    private String converTabStringToString(String[] resultat) {

        String convert = Arrays.toString(resultat);

        return convert;

    }

    /**
     * count function the number of equals in the result table :
     *
     * @param resultat Result table of the comparison() function
     * @return number of equals to int
     */
    @VisibleForTesting
    public int counterEqual(String[] resultat) {

        int counter = 0;

        for (int i = 0; i < resultat.length; i++) {

            if (resultat[i] == "=") {

                counter = +1;
            }

        }

        return counter;

    }

    /**
     * display function of user input value :
     *
     * @param resultat String value input by user
     */
    private void print(String inputUser) {

        System.out.println("Vous avez saisi la combinaison secrète -> " + inputUser);

    }

    /**
     * comparison function of attac value and defenseur value :
     *
     * @param nbrCombinaison number of digts of the combination generated
     * @param attac Value table of the Attaquant
     * @param def Value table of the Defenseur
     * @param result Result table of the comparison() function for recursive
     * method
     * @param randomLimit max number limit and mini number limit for generate
     * random number
     * @return
     */
    private String[] comparison(int nbrCombinaison, int[] attac, int[] def, String[] resultat, int[][] randomLimit) {

        System.out.println("\n nbrCombinaison : " + nbrCombinaison);

        if (nbrCombinaison < 0) {

            return resultat;

        };

        if (attac[nbrCombinaison] < def[nbrCombinaison]) {

            resultat[nbrCombinaison] = "+";
            System.out.print("Résultat : " + resultat[nbrCombinaison] + " ");

            System.out.print("attac : " + attac[nbrCombinaison] + " ");
            System.out.println("def : " + def[nbrCombinaison]);

            randomLimit[0][nbrCombinaison] = attac[nbrCombinaison];
            randomLimit[1][nbrCombinaison] = 9;

        } else if (attac[nbrCombinaison] > def[nbrCombinaison]) {

            resultat[nbrCombinaison] = "-";
            System.out.print("Résultat : " + resultat[nbrCombinaison] + " ");

            System.out.print("attac : " + attac[nbrCombinaison] + " ");
            System.out.println("def : " + def[nbrCombinaison]);

            randomLimit[0][nbrCombinaison] = 0;
            randomLimit[1][nbrCombinaison] = attac[nbrCombinaison];

        } else {

            resultat[nbrCombinaison] = "=";
            System.out.print("Résultat : " + resultat[nbrCombinaison] + " ");

            System.out.print("attac : " + attac[nbrCombinaison] + " ");
            System.out.println("def : " + def[nbrCombinaison]);

            randomLimit[0][nbrCombinaison] = attac[nbrCombinaison];
            randomLimit[1][nbrCombinaison] = attac[nbrCombinaison];
        }

        comparison(nbrCombinaison - 1, attac, def, resultat, randomLimit);

        return resultat;

    }

    /**
     * run system from option game Defenseur :
     *
     * @param def Value table of the Defenseur
     * @param length Number of digts of the number value
     */
    private void runDefenseur(int[] def, int length) {

        // Déclarations : 
        int[] attac = new int[length];
        String[] resultat = new String[length];
        int[][] randomLimit = {{0, 0}, {9, 9}};

        // Déclarations : 
        int nbrCombinaison = 1;
        int counter = 0;
        int nbrTours = 0;

        do {

            nbrTours += 1;

            System.out.print("Tour N°" + nbrTours + " ");
            System.out.print("Proposition -> ");

            attac = inputMachine(randomLimit, nbrCombinaison);

            resultat = comparison(nbrCombinaison, attac, def, resultat, randomLimit);

            counter += counterEqual(resultat);

            String resultatString = converTabStringToString(resultat);

            System.out.println("Counter : " + counter + " ");
            System.out.println("Résultat : " + resultatString + " ");

        } while (counter != (nbrCombinaison + 1));

    }

    //ToDo  
    private void runChallenger() {

    }

    //ToDo  
    private void runDuel() {

    }

}
