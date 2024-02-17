package sae125.dijkstra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import sae125.graphe.Lier;

/**
 * Cette classe implémente l'algorithme de Dijkstra pour trouver le chemin le
 * plus court entre deux sommets dans un graphe pondéré.
 *
 * @author Clement
 * @author Yanis
 * @version v1.0
 */
public class Dijkstra {

    /**
     * Représente les distances entre les sommets du graphe.
     */
    private static Map<String, Double> distances;

    /**
     * Représente les fiabilités des chemins entre les sommets du graphe.
     */
    private static Map<String, Double> fiabilites;

    /**
     * Représente les temps totaux pour atteindre les sommets du graphe.
     */
    private static Map<String, Double> tempsTotal;

    /**
     * Représente les prédécesseurs des sommets dans le chemin le plus court.
     */
    private static Map<String, String> predecesseurs;

    /**
     * Représente l'ensemble des sommets visités lors de l'algorithme de
     * Dijkstra.
     */
    private static Set<String> visite;

    /**
     * Représente le chemin le plus court entre un sommet de départ et un sommet
     * d'arrivée.
     */
    private static List<String> chemin;

    /**
     * Applique l'algorithme de Dijkstra pour trouver le chemin le plus court
     * entre un sommet de départ et un sommet d'arrivée dans un graphe pondéré.
     *
     * @param depart  le sommet de départ
     * @param arrivee le sommet d'arrivée
     * @param liens   la liste des liens représentant les arêtes du graphe
     * @param critere le critère à utiliser pour déterminer le chemin le plus
     *                court ("Fiabilite", "Distance" ou "Temps")
     * @return le chemin le plus court sous la forme d'une liste ordonnée des
     *         sommets
     */
    public static List<String> dijkstra(String depart, String arrivee, List<Lier> liens, String critere) {
        distances = new HashMap<>();
        fiabilites = new HashMap<>();
        tempsTotal = new HashMap<>();
        predecesseurs = new HashMap<>();
        visite = new HashSet<>();

        // Initialisation des structures de données
        for (Lier lien : liens) {
            String sommetOrigine = lien.getOrigine();
            String sommetDestination = lien.getDestination();
            distances.put(sommetOrigine, Double.POSITIVE_INFINITY);
            fiabilites.put(sommetOrigine, 0.0);
            tempsTotal.put(sommetOrigine, Double.POSITIVE_INFINITY);
            predecesseurs.put(sommetOrigine, null);
            distances.put(sommetDestination, Double.POSITIVE_INFINITY);
            fiabilites.put(sommetDestination, 0.0);
            tempsTotal.put(sommetDestination, Double.POSITIVE_INFINITY);
            predecesseurs.put(sommetDestination, null);
        }

        distances.put(depart, 0.0);
        fiabilites.put(depart, 1.0);
        tempsTotal.put(depart, 0.0);

        // Recherche du chemin le plus court
        while (!visite.contains(arrivee)) {
            String sommetCourant = getSommetNonVisitePlusProche(distances, visite, critere, fiabilites, tempsTotal);

            visite.add(sommetCourant);

            for (Lier lien : liens) {
                String lienOrigine = lien.getOrigine();
                String lienDestination = lien.getDestination();

                if (sommetCourant.equals(lienOrigine)) {
                    double nouvelleDistance = distances.get(sommetCourant) + lien.getDistance();
                    double nouvelleFiabilite = fiabilites.get(sommetCourant) * lien.getFiabilite();
                    double nouveauTemps = tempsTotal.get(sommetCourant) + lien.getTemps();

                    if (nouvelleDistance < distances.get(lienDestination)) {
                        distances.put(lienDestination, nouvelleDistance);
                        fiabilites.put(lienDestination, nouvelleFiabilite);
                        tempsTotal.put(lienDestination, nouveauTemps);
                        predecesseurs.put(lienDestination, sommetCourant);
                    } else if (nouvelleDistance == distances.get(lienDestination)) {
                        if (critere.equals("Fiabilite") && nouvelleFiabilite > fiabilites.get(lienDestination)) {
                            distances.put(lienDestination, nouvelleDistance);
                            fiabilites.put(lienDestination, nouvelleFiabilite);
                            tempsTotal.put(lienDestination, nouveauTemps);
                            predecesseurs.put(lienDestination, sommetCourant);
                        } else if (critere.equals("Temps") && nouveauTemps < tempsTotal.get(lienDestination)) {
                            distances.put(lienDestination, nouvelleDistance);
                            fiabilites.put(lienDestination, nouvelleFiabilite);
                            tempsTotal.put(lienDestination, nouveauTemps);
                            predecesseurs.put(lienDestination, sommetCourant);
                        }
                    }
                }
            }
        }

        // Reconstruction du chemin le plus court
        chemin = new ArrayList<>();
        String sommet = arrivee;
        while (sommet != null) {
            chemin.add(0, sommet);
            sommet = predecesseurs.get(sommet);
        }
        return chemin;
    }

    /**
     * Renvoie le sommet non visité le plus proche selon le critère spécifié.
     *
     * @param distances  les distances des sommets par rapport au sommet de
     *                   départ
     * @param visite     l'ensemble des sommets déjà visités
     * @param critere    le critère utilisé pour déterminer le sommet le plus
     *                   proche ("Fiabilite", "Distance" ou "Temps")
     * @param fiabilites les fiabilités des sommets par rapport au sommet de
     *                   départ
     * @param tempsTotal les temps totaux des sommets par rapport au sommet de
     *                   départ
     * @return le sommet non visité le plus proche
     */
    private static String getSommetNonVisitePlusProche(Map<String, Double> distances, Set<String> visite,
            String critere, Map<String, Double> fiabilites, Map<String, Double> tempsTotal) {
        double valeurMin = Double.POSITIVE_INFINITY;
        String sommetPlusProche = null;

        for (Map.Entry<String, Double> entry : distances.entrySet()) {
            String sommet = entry.getKey();
            double distance = entry.getValue();
            double critereValeur = Double.POSITIVE_INFINITY;

            if (critere.equals("Fiabilite")) {
                double fiabilite = fiabilites.get(sommet);
                double temps = tempsTotal.get(sommet);
                critereValeur = fiabilite;
                if (fiabilite == 0) { // Gérer les cas où la fiabilité est nulle pour éviter une division par zéro
                    critereValeur = Double.POSITIVE_INFINITY;
                } else {
                    critereValeur = temps / fiabilite;
                }
            } else if (critere.equals("Distance")) {
                critereValeur = distance;
            } else if (critere.equals("Temps")) {
                critereValeur = tempsTotal.get(sommet);
            }

            if (!visite.contains(sommet) && critereValeur < valeurMin) {
                valeurMin = critereValeur;
                sommetPlusProche = sommet;
            }
        }

        return sommetPlusProche;
    }

}
