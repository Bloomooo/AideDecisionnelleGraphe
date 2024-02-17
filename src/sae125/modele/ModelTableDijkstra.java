package sae125.modele;

import java.util.List;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 * Un modèle de table utilisé pour afficher les résultats de l'algorithme de
 * Dijkstra.
 * Il étend la classe `AbstractTableModel` et fournit des méthodes pour ajouter
 * des chemins
 * et des valeurs à la table, ainsi que pour effacer les données de la table.
 * 
 * @author Clement
 * @author Yanis
 * @version v1.0
 */
public class ModelTableDijkstra extends AbstractTableModel {

    /**
     * Les titres des colonnes de la table.
     */
    private final String[] titles = { "Chemin", "Fiabilité", "Distance", "Temps" };

    /**
     * La liste des chemins de la table.
     */
    private final List<String> chemin = new ArrayList<>();

    /**
     * La liste des valeurs de la table.
     */
    private final List<Double> listeValeurs = new ArrayList<>();

    /**
     * Ajoute un chemin à la table.
     *
     * @param chemin le chemin à ajouter
     */
    public void addChemin(List<String> chemin) {
        this.chemin.addAll(chemin);
        fireTableRowsInserted(0, this.chemin.size() + 1);
    }

    /**
     * Ajoute les valeurs à la table.
     *
     * @param listeValeurs la liste des valeurs à ajouter
     */
    public void addValeurs(List<Double> listeValeurs) {
        this.listeValeurs.clear();
        this.listeValeurs.addAll(listeValeurs);
    }

    /**
     * Retourne la liste des chemins de la table.
     *
     * @return la liste des chemins
     */
    public List<String> getListChemin() {
        return this.chemin;
    }

    /**
     * Efface les données de la table.
     */
    public void clearData() {
        if (!chemin.isEmpty()) {
            int rowCount = chemin.size();
            chemin.clear();
            fireTableRowsDeleted(0, rowCount - 1);
        }
    }

    /**
     * Retourne le nom de la colonne spécifiée.
     *
     * @param column l'index de la colonne
     * @return le nom de la colonne
     */
    @Override
    public String getColumnName(int column) {
        return titles[column];
    }

    /**
     * Retourne le nombre de lignes de la table.
     *
     * @return le nombre de lignes
     */
    @Override
    public int getRowCount() {
        return this.chemin.size() + 1;
    }

    /**
     * Retourne le nombre de colonnes de la table.
     *
     * @return le nombre de colonnes
     */
    @Override
    public int getColumnCount() {
        return titles.length;
    }

    /**
     * Retourne la valeur à la position spécifiée dans la table.
     *
     * @param rowIndex    l'index de la ligne
     * @param columnIndex l'index de la colonne
     * @return la valeur à la position spécifiée
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex < chemin.size() - 1) {
            String sommetOrigine = chemin.get(rowIndex);
            String sommetDestination = chemin.get(rowIndex + 1);

            switch (columnIndex) {
                case 0:
                    return sommetOrigine + " --> " + sommetDestination;
                case 1:
                    return (listeValeurs.get(rowIndex * 3)) * 100 + "%"; // Get the fiabilité value
                case 2:
                    return listeValeurs.get(rowIndex * 3 + 1); // Get the distance value
                case 3:
                    return listeValeurs.get(rowIndex * 3 + 2);
                default:
                    return null;
            }

        } else if (rowIndex == chemin.size()) {
            switch (columnIndex) {
                case 0:
                    return "Total";
                case 1:
                    double totalFiabilite = 1;
                    for (int i = 0; i < chemin.size() - 1; i++) {
                        totalFiabilite *= listeValeurs.get(i * 3);
                    }
                    return String.format("%.2f%%", totalFiabilite * 100);

                case 2:
                    double totalDistance = 0;
                    for (int i = 0; i < chemin.size() - 1; i++) {
                        totalDistance += listeValeurs.get(i * 3 + 1);
                    }
                    return String.format("%.2f", totalDistance);

                case 3:
                    double totalTemps = 0;
                    for (int i = 0; i < chemin.size() - 1; i++) {
                        totalTemps += listeValeurs.get(i * 3 + 2);
                    }
                    return String.format("%.2f", totalTemps);

                default:
                    return null;
            }
        }

        return null;

    }
}
