package sae125.chargement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * La classe File est utilisée pour lire une matrice à partir d'un fichier et
 * stocker les données dans des tableaux.
 *
 * @author Clement
 * @author Yanis
 * @version v1.0
 */
public class File {

    /**
     * Tableau des valeurs de fiabilité.
     */
    private double[] fiabilite;

    /**
     * Tableau des valeurs de distance.
     */
    private double[] distance;

    /**
     * Tableau des valeurs de temps.
     */
    private double[] temps;

    /**
     * Lit une matrice à partir d'un fichier et stocke les données dans des
     * tableaux.
     *
     * @param file le chemin du fichier contenant la matrice
     * @throws IOException si une erreur de lecture du fichier se produit
     */
    public void readMatrice(String file) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            java.util.List<Double> fiabiliteList = new java.util.ArrayList<>();
            java.util.List<Double> distanceList = new java.util.ArrayList<>();
            java.util.List<Double> tempsList = new java.util.ArrayList<>();

            while ((line = br.readLine()) != null) {
                String[] groups = line.split("\\s+");

                for (String group : groups) {
                    String[] values = group.split(";");
                    if (values.length == 3) {
                        double fiabilite = Double.parseDouble(values[0]);
                        double distance = Double.parseDouble(values[1]);
                        double temps = Double.parseDouble(values[2]);

                        if (fiabilite != 0.0 || distance != 0.0 || temps != 0.0) {
                            fiabiliteList.add(fiabilite);
                            distanceList.add(distance);
                            tempsList.add(temps);
                        }
                    }
                }
            }
            this.fiabilite = convertListToArray(fiabiliteList);
            this.distance = convertListToArray(distanceList);
            this.temps = convertListToArray(tempsList);
            /*
             * System.out.println("Fiabilité : " + java.util.Arrays.toString(fiabilite));
             * System.out.println("Distance : " + java.util.Arrays.toString(distance));
             * System.out.println("Temps : " + java.util.Arrays.toString(temps));
             */

            br.close();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retourne les valeurs de fiabilité sous forme de tableau.
     *
     * @return le tableau des valeurs de fiabilité
     */
    public double[] getFiabilite() {
        return fiabilite;
    }

    /**
     * Retourne les valeurs de distance sous forme de tableau.
     *
     * @return le tableau des valeurs de distance
     */
    public double[] getDistance() {
        return distance;
    }

    /**
     * Retourne les valeurs de temps sous forme de tableau.
     *
     * @return le tableau des valeurs de temps
     */
    public double[] getTemps() {
        return temps;
    }

    /**
     * Convertit une liste de nombres en un tableau de nombres.
     *
     * @param list la liste de nombres à convertir
     * @return le tableau de nombres converti
     */
    private static double[] convertListToArray(java.util.List<Double> list) {
        double[] array = new double[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }
}
