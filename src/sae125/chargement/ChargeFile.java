package sae125.chargement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Cette classe est utilisée pour charger et traiter un fichier contenant une
 * matrice.
 *
 * @author Clement
 * @author Yanis
 * @version v1.0
 */
public class ChargeFile {

    /**
     * Lit le fichier contenant la matrice et renvoie une représentation sous
     * forme de tableau d'entiers.
     *
     * @return Le tableau d'entiers représentant la matrice.
     * @throws IOException si une erreur de lecture du fichier se produit.
     */
    public static int[][] readFile() throws IOException {
        // Code pour lire et traiter le fichier
        BufferedReader br = new BufferedReader(new FileReader("matrice.txt"));
        String line;
        int row = 0;
        int col = 0;
        int matriceSize = 30;
        int[][] matrice = new int[matriceSize][matriceSize];

        while ((line = br.readLine()) != null && row < matriceSize) {
            String[] groups = line.trim().split("\\s+");

            for (int i = 0; i < groups.length && col < matriceSize; i++) {
                String[] values = groups[i].split(";");

                if (values.length == 3) {
                    double fiabilite = Double.parseDouble(values[0]);
                    double distance = Double.parseDouble(values[1]);
                    double temps = Double.parseDouble(values[2]);

                    if (fiabilite != 0.0 || distance != 0.0 || temps != 0.0) {
                        matrice[row][col] = 1;
                    }
                }

                col++;
            }

            row++;
            col = 0;
        }

        br.close();

        return matrice;
    }

    /**
     * Affiche la matrice donnée sur la sortie standard.
     *
     * @param matrix Le tableau d'entiers représentant la matrice.
     * @param rows   Le nombre de lignes de la matrice.
     * @param cols   Le nombre de colonnes de la matrice.
     */
    public void printMatrix(int[][] matrix, int rows, int cols) {
        // Code pour afficher la matrice
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

}
