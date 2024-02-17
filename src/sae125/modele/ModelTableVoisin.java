package sae125.modele;

import sae125.graphe.Sommet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 * Modèle de table pour afficher les voisins d'un sommet.
 *
 * @author Clement
 * @author Yanis
 * @version v1.0
 */
public class ModelTableVoisin extends AbstractTableModel {

    /**
     * Les noms des colonnes de la table.
     */
    private final String[] titles = { "Sommet", "Type" };

    /**
     * La liste des voisins.
     */
    private final List<Sommet> voisins = new ArrayList<>();

    /**
     * Ajoute une liste de voisins à la table.
     *
     * @param voisins la liste des voisins à ajouter
     */
    public void addVoisin(List<Sommet> voisins) {
        this.voisins.addAll(voisins);
        fireTableRowsInserted(0, this.voisins.size() + 1);

    }

    /**
     * Efface les données de la table.
     */
    public void clearData() {
        if (!voisins.isEmpty()) {
            int rowCount = voisins.size();
            voisins.clear();
            fireTableRowsDeleted(0, rowCount - 1);
        }
    }

    /**
     * Retourne le nombre de lignes de la table.
     *
     * @return le nombre de lignes de la table
     */
    @Override
    public int getRowCount() {
        return this.voisins.size() + 2;
    }

    /**
     * Retourne le nom de la colonne spécifiée.
     *
     * @param column l'index de la colonne
     * @return le nom de la colonne spécifiée
     */
    @Override
    public String getColumnName(int column) {
        return titles[column];
    }

    /**
     * Retourne le nombre de colonnes de la table.
     *
     * @return le nombre de colonnes de la table
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
        if (rowIndex >= 0 && rowIndex < voisins.size()) {
            Sommet sommet = voisins.get(rowIndex);

            switch (columnIndex) {
                case 0:
                    return sommet.getOrigine();
                case 1:
                    return sommet.getTypeS();
            }
        } else if (rowIndex == voisins.size() + 1) {

            switch (columnIndex) {
                case 0:
                    return "Total";
                case 1:
                    return voisins.size();
            }
        }

        return null;
    }

}
