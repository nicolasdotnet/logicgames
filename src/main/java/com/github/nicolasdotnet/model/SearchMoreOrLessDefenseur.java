/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.model;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * SearchMoreOrLessDefenseur est la classe du jeux Recherche +/- en mode
 * Defenseur
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class SearchMoreOrLessDefenseur extends SearchMoreOrLess{

    /**
     * comparison function of attac value and defenseur value :
     *
     * @param nbrCombinaison number of digts of the combination
     * @param attac Value table of the Attaquant
     * @param def Value table of the Defenseur
     * @param randomLimit Value limit for number random generator (machine value) 
     * @param result Result arrayList of the comparison() function for recursive
     * method
     * @return result of the comparaison.
     */
    public ArrayList<String> comparaison(int nbrCombinaison, ArrayList<Integer> attac, ArrayList<Integer> def, int[][] randomLimit, ArrayList<String> result) {

        int nbr;

        int index = nbrCombinaison - 1;

        System.out.println("Index : " + index);

        System.out.println("\n nbrCombinaison : " + nbrCombinaison);

        if (index < 0) {

            Collections.reverse(result);

            return result;

        }

        if (attac.get(index) < def.get(index)) {

            result.add("+");
            System.out.print("Résultat : " + result.get(result.size() - 1) + " car ");

            System.out.print("attac : " + attac.get(index) + " ");
            System.out.println("def : " + def.get(index));

            nbr = 0;

            randomLimit[0][index] = attac.get(index);
            System.out.println("min + : "+randomLimit[0][index]);
//            randomLimit[1][index] = 9;
            System.out.println("max + : "+randomLimit[1][index]);

        } else if (attac.get(index) > def.get(index)) {

            result.add("-");
            System.out.print("Résultat : " + result.get(result.size() - 1) + " car ");

            System.out.println("attac : " + attac.get(index) + " ");
            System.out.println("def : " + def.get(index));

            nbr = 0;

//            randomLimit[0][index] = 0;
            System.out.println("min - : "+randomLimit[0][index]);
            randomLimit[1][index] = attac.get(index);
            System.out.println("max - : "+randomLimit[1][index]);

        } else {

            result.add("=");
            System.out.print("Résultat : " + result.get(result.size() - 1) + " car ");

            System.out.print("attac : " + attac.get(index) + " ");
            System.out.println("def : " + def.get(index));

            nbr = 1;

            randomLimit[0][index] = attac.get(index);
            System.out.println("min = : "+randomLimit[0][index]);
            randomLimit[1][index] = attac.get(index);
            System.out.println("max = : "+randomLimit[1][index]);

        }

        comparaison(index, attac, def,randomLimit,result);

        return result;
    }

}
