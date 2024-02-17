package sae125.graphe;

import sae125.graphe.Chemin;

/**
 * Représente un lien entre deux sommets.
 * 
 * @author Clement
 * @author Yanis
 * @version v1.0
 */
public class Lier {

    /**
     * Le chemin liant les deux sommets.
     */
    private Chemin chemin;

    /**
     * Construit un objet Lier avec le chemin spécifié.
     *
     * @param chemin le chemin liant deux sommets
     */
    public Lier(Chemin chemin) {
        this.chemin = chemin;
    }

    /**
     * Retourne le chemin liant les deux sommets.
     *
     * @return le chemin liant les deux sommets
     */
    public Chemin getChemin() {
        return this.chemin;
    }

    /**
     * Définit le chemin liant les deux sommets.
     *
     * @param chemin le chemin liant les deux sommets
     */
    public void setChemin(Chemin chemin) {
        this.chemin = chemin;
    }

    /**
     * Retourne le sommet d'origine du lien.
     *
     * @return le sommet d'origine du lien
     */
    public String getOrigine() {
        return chemin.getOrigine();
    }

    /**
     * Retourne le sommet de destination du lien.
     *
     * @return le sommet de destination du lien
     */
    public String getDestination() {
        return chemin.getDestination();
    }

    /**
     * Retourne la fiabilité du lien.
     *
     * @return la fiabilité du lien
     */
    public double getFiabilite() {
        return chemin.getFiabilite();
    }

    /**
     * Retourne la distance du lien.
     *
     * @return la distance du lien
     */
    public double getDistance() {
        return chemin.getDistance();
    }

    /**
     * Retourne le temps du lien.
     *
     * @return le temps du lien
     */
    public double getTemps() {
        return chemin.getTemps();
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de l'objet
     * Lier.
     *
     * @return la représentation sous forme de chaîne de caractères de l'objet Lier
     */
    @Override
    public String toString() {
        return "chemin : " + "\n" +
                "origine : " + chemin.getOrigine() + ", " +
                "destination : " + chemin.getDestination() + "\n" +
                "fiabilité : " + chemin.getFiabilite() + "\n" +
                "distance : " + chemin.getDistance() + "\n" +
                "temps : " + chemin.getTemps() + "\n";
    }

}
