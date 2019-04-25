/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nicolasdotnet.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * MastermindDefenseur est la classe du jeux Mastermind en mode Defenseur
 *
 * @author nicolasdotnet
 * @version Alpha
 * @since 2019
 */
public class MastermindDuel extends Mastermind {

    public static void main(String[] args) {

        MastermindDuel run = new MastermindDuel();

        String def = "22";
        int nbrCombinaison = 2;
        int nbrRange = 3;
        int nbrTests = 1;
        String machine2;

        // tour 1 : 
        ArrayList<String> possible = run.generatAllPossible(nbrCombinaison, nbrRange);
        System.out.println("taille possible : " + possible.size());

        machine2 = run.getCombinaison(nbrTests, possible, nbrRange);
        System.out.println("machine2 : " + machine2);
        ArrayList<Integer> machine = run.convertStringToArrayList(machine2);

        HashMap<String, String> result = run.comparaison(def, machine);
        System.out.println("result: " + run.displayResult(result) + "\n");

        // tour 2 : 
        ArrayList<String> bestPossible = run.generatBestPossible(possible, result, machine);
        System.out.println("\n taille bestPossible 1 : " + bestPossible.size());

        System.out.println("\n Bilan -> taille possible : " + possible.size() + " ; taille bestPossible : " + bestPossible.size());

        machine2 = run.getCombinaison(nbrTests, bestPossible, nbrRange);
        System.out.println("machine2 : " + machine2);
        machine = run.convertStringToArrayList(machine2);

        result = run.comparaison(def, machine);
        System.out.println("result 2 : " + run.displayResult(result) + "\n");

        // tour 3 : 
        bestPossible = run.generatBestPossible(bestPossible, result, machine);
        System.out.println("\n taille bestPossible 2 : " + bestPossible.size());

        System.out.println("\n Bilan -> taille possible : " + possible.size() + " ; taille bestPossible 2 : " + bestPossible.size());

        machine2 = run.getCombinaison(nbrTests, bestPossible, nbrRange);
        System.out.println("machine2 : " + machine2);
        machine = run.convertStringToArrayList(machine2);

        result = run.comparaison(def, machine);
        System.out.println("result 3 : " + run.displayResult(result) + "\n");

        // tour 4 :
        if (Integer.parseInt(result.get("place")) != nbrCombinaison) {

            bestPossible = run.generatBestPossible(bestPossible, result, machine);
            System.out.println("\n taille bestPossible 3 : " + bestPossible.size());

            System.out.println("\n Bilan -> taille possible : " + possible.size() + " ; taille bestPossible 3 : " + bestPossible.size());

            machine2 = run.getCombinaison(nbrTests, bestPossible, nbrRange);
            System.out.println("machine2 : " + machine2);
            machine = run.convertStringToArrayList(machine2);

            result = run.comparaison(def, machine);
            System.out.println("result 4 : " + run.displayResult(result) + "\n");

        }

    }

    /**
     * Complete list of combinaison possible generator function :
     *
     * @param nbrCombinaison number of digts of the combination
     * @param nbrRange range of number for the combinaison
     * @return complete list of combinaison possible
     */
    public ArrayList<String> generatAllPossible(int nbrCombinaison, int nbrRange) {

        ArrayList<String> possible = new ArrayList<String>();

//        String combinaisonFormat = "";
//
//        for (int i = 0; i < nbrCombinaison; i++) {
//
//            combinaisonFormat += 0;
//            System.out.println("combinaisonFormat : " + combinaisonFormat);
//        }
//
//        DecimalFormat format = new DecimalFormat(combinaisonFormat);
//
//        for (int i = 0; i <= Math.pow(nbrRange, nbrCombinaison); i++) {
//
//            String combinaison = format.format(i);
//
//            possible.add(combinaison);
//
//        }
        for (int i = 0; i <= nbrRange; i++) {

            for (int j = 0; j <= nbrRange; j++) {

                if (nbrCombinaison == 2) {

                    String str = (String.valueOf(i) + String.valueOf(j));
                    possible.add(str);

                } else {
                    for (int k = 0; k <= nbrRange; k++) {

                        if (nbrCombinaison == 3) {
                            String str = (String.valueOf(i) + String.valueOf(j) + String.valueOf(k));
                            possible.add(str);

                        } else {

                            for (int l = 0; l <= nbrRange; l++) {

                                if (nbrCombinaison == 4) {

                                    String str = (String.valueOf(i) + String.valueOf(j) + String.valueOf(k) + String.valueOf(l));
                                    possible.add(str);

                                } else {

                                    for (int m = 0; m <= nbrRange; m++) {

                                        if (nbrCombinaison == 5) {

                                            String str = (String.valueOf(i) + String.valueOf(j) + String.valueOf(k) + String.valueOf(l) + String.valueOf(m));
                                            possible.add(str);

                                        } else {

                                            for (int n = 0; n <= nbrRange; n++) {

                                                if (nbrCombinaison == 6) {

                                                    String str = (String.valueOf(i) + String.valueOf(j) + String.valueOf(k) + String.valueOf(l) + String.valueOf(m) + String.valueOf(n));
                                                    possible.add(str);
                                                } else {

                                                    for (int o = 0; o <= nbrRange; o++) {

                                                        if (nbrCombinaison == 7) {

                                                            String str = (String.valueOf(i) + String.valueOf(j) + String.valueOf(k) + String.valueOf(l) + String.valueOf(m) + String.valueOf(n) + String.valueOf(o));
                                                            possible.add(str);
                                                        } else {

                                                            for (int p = 0; p <= nbrRange; p++) {

                                                                if (nbrCombinaison == 8) {

                                                                    String str = (String.valueOf(i) + String.valueOf(j) + String.valueOf(k) + String.valueOf(l) + String.valueOf(m) + String.valueOf(n) + String.valueOf(o) + String.valueOf(p));
                                                                    possible.add(str);
                                                                } else {

                                                                    for (int q = 0; q <= nbrRange; q++) {

                                                                        if (nbrCombinaison == 9) {

                                                                            String str = (String.valueOf(i) + String.valueOf(j) + String.valueOf(k) + String.valueOf(l) + String.valueOf(m) + String.valueOf(n) + String.valueOf(o) + String.valueOf(p) + String.valueOf(q));
                                                                            possible.add(str);
                                                                        } else {
                                                                            for (int r = 0; r <= nbrRange; r++) {

                                                                                if (nbrCombinaison == 10) {

                                                                                    String str = (String.valueOf(i) + String.valueOf(j) + String.valueOf(k) + String.valueOf(l) + String.valueOf(m) + String.valueOf(n) + String.valueOf(o) + String.valueOf(p) + String.valueOf(q) + String.valueOf(r));
                                                                                    possible.add(str);
                                                                                }
                                                                            }

                                                                        }
                                                                    }

                                                                }
                                                            }
                                                        }
                                                    }

                                                }

                                            }
                                        }

                                    }
                                }

                            }

                        }
                    }
                }

            }
        }

        int x = 0;

        for (String combinaison : possible) {
            System.out.println("combinaison : " + combinaison);
            System.out.println("nbrPossible : " + x++);

        }

        return possible;

    }

    /**
     * Random selection of machine combinaison :
     *
     * @param nbrTests number of test of comparison
     * @param possible list of combinaison possible (complete or best)
     * @param nbrRange range of number for the combinaison
     * @return machine value
     */
    public String getCombinaison(int nbrTests, ArrayList<String> possible, int nbrRange) {

        String combinaison;

        if (nbrTests == 1 && nbrRange == 6) {

            combinaison = possible.get(1122);

        } else {

            int id = 0 + (int) (Math.random() * (possible.size() - 0)); // +1 ? ou -1 ?
            System.out.println("id : " + id);
            combinaison = possible.get(id);
        }
        System.out.println("retour get combinaison");
        return combinaison;

    }

    /**
     * List of best combinaison possible generator function :
     *
     * @param possible complete list of combinaison possible
     * @param result result hashmap of the comparison() function
     * @param machine machine value
     * @return list of best combinaison possible
     */
    public ArrayList<String> generatBestPossible(ArrayList<String> possible, HashMap<String, String> result, ArrayList<Integer> machine) {

        HashMap<String, String> ri = new HashMap<String, String>();
        ArrayList<String> bestPossible = new ArrayList<String>();

        for (int i = 0; i < possible.size(); i++) {

            ri = comparaison(possible.get(i), machine);

            System.out.println("display resultat -> place : " + result.get("place") + " ; present : " + result.get("present"));
            System.out.println("display ri -> place : " + ri.get("place") + " ; present : " + ri.get("present"));

            if (result.get("place").equals(ri.get("place")) && result.get("present").equals(ri.get("present"))) {

                System.out.println("possible.get(i) : " + possible.get(i));
                bestPossible.add(possible.get(i));

            }

        }
        System.out.println("retour bestpossible");
        return bestPossible;

    }
}
