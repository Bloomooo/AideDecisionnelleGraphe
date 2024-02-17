/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sae125.graphe;

import sae125.graphe.Lier;
import sae125.graphe.Chemin;
import sae125.graphe.Sommet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Représente un graphe sous forme de liste d'adjacence. Cette classe permet de
 * gérer les sommets, les chemins et les liaisons du graphe. Elle offre des
 * méthodes pour ajouter des sommets, des chemins et des liaisons, ainsi que
 * pour charger les éléments du graphe dans des listes.
 *
 * @author Clement
 * @author Yanis
 */
public class ListeGraphe {

    /**
     * Nombre total de sommets dans le graphe.
     */
    private final int Vnum;

    /**
     * Le premier sommet du graphe.
     */
    private Sommet premier;

    /**
     * La liste des liaisons entre les sommets du graphe.
     */
    private final List<Lier> liaisons;

    /**
     * La liste des sommets du graphe.
     */
    private final List<Sommet> sommets;

    /**
     * La liste des chemins du graphe.
     */
    private final List<Chemin> chemins;

    /**
     * La liste des sommets de type "Maternite" dans le graphe.
     */
    private final List<Sommet> materniteList;

    /**
     * La liste des sommets de type "Bloc" dans le graphe.
     */
    private final List<Sommet> blocList;

    /**
     * La liste des sommets de type "Nutrition" dans le graphe.
     */
    private final List<Sommet> nutritionList;

    /**
     * Constructeur de la classe ListeGraphe.
     *
     * @param num le nombre de sommets dans le graphe
     */
    public ListeGraphe(int num) {
        Vnum = num;
        premier = null;
        chemins = new ArrayList<>();
        sommets = new ArrayList<>();
        liaisons = new ArrayList<>();
        materniteList = new ArrayList<>();
        blocList = new ArrayList<>();
        nutritionList = new ArrayList<>();
    }

    /**
     * Obtient le nombre de sommets dans le graphe.
     *
     * @return le nombre de sommets
     */
    public int getVnum() {
        return this.Vnum;
    }

    /**
     * Charge les liaisons du graphe dans une liste spécifiée.
     *
     * @param list la liste à charger avec les liaisons
     * @return la liste contenant les liaisons du graphe
     */
    public List<Lier> loadLiaisons(List<Lier> list) {
        list.addAll(liaisons);
        return list;
    }

    /**
     * Charge les chemins du graphe dans une liste spécifiée.
     *
     * @param list la liste à charger avec les chemins
     * @return la liste contenant les chemins du graphe
     */
    public List<Chemin> loadChemins(List<Chemin> list) {
        list.addAll(chemins);
        return list;
    }

    /**
     * Charge les sommets du graphe dans une liste spécifiée.
     *
     * @param list la liste à charger avec les sommets
     * @return la liste contenant les sommets du graphe
     */
    public List<Sommet> loadSommets(List<Sommet> list) {
        list.addAll(sommets);
        return list;
    }

    /**
     * Obtient la liste des sommets du graphe.
     *
     * @return la liste des sommets du graphe
     */
    public List<Sommet> getSommets() {
        return this.sommets;
    }

    /**
     * Obtient la liste des chemins du graphe.
     *
     * @return la liste des chemins du graphe
     */
    public List<Chemin> getChemins() {
        return chemins;
    }

    /**
     * Recherche un chemin entre deux sommets spécifiés.
     *
     * @param sommetOri  le sommet d'origine
     * @param sommetDest le sommet de destination
     * @return le chemin entre les deux sommets, ou null s'il n'existe pas
     */
    public Chemin searchChemin(Sommet sommetOri, Sommet sommetDest) {
        for (Chemin chemin : chemins) {
            if (chemin.getOrigine().equals(sommetOri.getOrigine())
                    && chemin.getDestination().equals(sommetDest.getOrigine())) {
                return chemin;
            }
        }
        return null;
    }

    /**
     * Ajoute un sommet au graphe avec les spécifications données.
     *
     * @param origine l'origine du sommet
     * @param type    le type du sommet
     * @param x       la position en x du sommet
     * @param y       la position en y du sommet
     * @param height  la hauteur du sommet
     * @param width   la largeur du sommet
     * @param number  le numéro du sommet
     */
    public void addSommet(String origine, String type, int x, int y, int height, int width, int number) {
        Sommet nouv = new Sommet(origine, type, x, y, height, width, number);
        nouv.setSuivS(premier);
        premier = nouv;
        sommets.add(nouv);
        nouv.addListe(nouv);
        for (Sommet sommet : sommets) {
            if (!sommet.equals(nouv)) {
                sommet.addPrecedents(nouv);
            }
        }

    }

    /**
     * Ajoute une liaison entre deux sommets avec les spécifications données.
     *
     * @param origine     l'origine de la liaison
     * @param destination la destination de la liaison
     * @param fiabilite   la fiabilité de la liaison
     * @param distance    la distance de la liaison
     * @param temps       le temps de parcours de la liaison
     */
    public void addEdge(String origine, String destination, double fiabilite, double distance, double temps) {
        Chemin nouvOrigine = new Chemin(origine, destination, fiabilite, distance, temps);
        chemins.add(nouvOrigine);
        Lier lier = new Lier(nouvOrigine);
        liaisons.add(lier);
        Sommet sommetOrigine = getSommetByOrigine(origine);
        Sommet sommetDestination = getSommetByOrigine(destination);

        sommetOrigine.addCheminSortant(nouvOrigine);
        sommetDestination.addCheminEntrant(nouvOrigine);

    }

    /**
     * Retourne une liste des sommets de type "Maternite" dans le graphe.
     *
     * @return Une liste des sommets de type "Maternite"
     */
    public List<Sommet> printMat() {
        Sommet cour = premier;
        while (cour != null) {
            if (cour.getTypeS().equals("Maternite")) {
                materniteList.add(cour);
            }
            cour = cour.getSuivS();
        }
        return materniteList;
    }

    /**
     * Affiche les chemins pour chaque sommet du graphe.
     */
    public void printWays() {
        Sommet cour = premier;
        while (cour != null) {
            System.out.println("Trajet de " + cour.getOrigine() + " à : ");
            Chemin cour2 = (Chemin) cour.getListe();
            while (cour2 != null) {
                System.out.println("   " + cour2.getDestination() + " - Fiabilité : " + cour2.getFiabilite()
                        + ", Distance : " + cour2.getDistance() + ", Durée : " + cour2.getTemps());
                cour2 = (Chemin) cour2.getSuivC();
            }
            cour = cour.getSuivS();
        }
    }

    /**
     * Renvoie un ensemble de noms de sommets qui sont à une distance donnée du
     * sommet spécifié.
     *
     * @param disp     Le nom du sommet de départ
     * @param distance La distance maximale
     * @return Un ensemble de noms de sommets à la distance spécifiée du sommet
     *         de départ
     */
    public Set<String> oneDistNeighbors(String disp, int distance) {
        Set<String> voisins = new HashSet<>();

        Sommet cour = premier;
        while (cour != null && !cour.getOrigine().equals(disp)) {
            cour = cour.getSuivS();
        }

        if (cour != null) {
            findNeighbors(cour, distance, voisins);
        }

        return voisins;
    }

    /**
     * Récupère les voisins d'un sommet jusqu'à une certaine distance dans le
     * graphe.
     *
     * @param sommet   Le sommet à partir duquel rechercher les voisins
     * @param distance La distance maximale pour récupérer les voisins
     * @param voisins  L'ensemble des voisins trouvés jusqu'à présent
     */
    private void findNeighbors(Sommet sommet, int distance, Set<String> voisins) {
        if (distance == 0) {
            // Ajouter le sommet courant aux voisins
            voisins.add(sommet.getOrigine());
        } else if (distance > 0) {
            // Parcours des chemins sortants du sommet courant
            for (Chemin chemin : sommet.getCheminsSortants()) {
                Sommet sommetDestination = getSommetByOrigine(chemin.getDestination());
                if (sommetDestination != null) {
                    // Appel récursif pour chaque sommet destination avec distance - 1
                    findNeighbors(sommetDestination, distance - 1, voisins);
                }
            }
        }
    }

    /**
     * Affiche le graphe sous forme de liste d'adjacence.
     */
    public void printGraphe() {
        Sommet cour = premier;
        while (cour != null) {
            System.out.print(cour.getOrigine() + " : ");
            Chemin cour2 = (Chemin) cour.getListe();
            while (cour2 != null) {
                System.out.print(cour2.getDestination() + ", ");
                cour2 = (Chemin) cour2.getSuivC();
            }
            System.out.println();
            cour = cour.getSuivS();
        }
    }

    /**
     * Recherche et renvoie le sommet correspondant au nom spécifié.
     *
     * @param nom Le nom du sommet à rechercher
     * @return Le sommet correspondant, ou null si non trouvé
     */
    public Sommet getSommet(String nom) {
        for (Sommet sommet : sommets) {
            if (sommet.getOrigine().equals(nom)) {
                return sommet;
            }
        }
        return null;
    }

    /**
     * Retourne une liste des sommets de type "Bloc" dans le graphe.
     *
     * @return Une liste des sommets de type "Bloc"
     */
    public List<Sommet> printBloc() {
        Sommet cour = premier;
        while (cour != null) {
            if (cour.getTypeS().equals("Bloc")) {
                blocList.add(cour);
            }
            cour = cour.getSuivS();
        }
        return blocList;
    }

    /**
     * Retourne une liste des sommets de type "Nutrition" dans le graphe.
     *
     * @return Une liste des sommets de type "Nutrition"
     */
    public List<Sommet> printNutri() {
        Sommet cour = premier;
        while (cour != null) {
            if (cour.getTypeS().equals("Nutrition")) {
                nutritionList.add(cour);
            }
            cour = cour.getSuivS();
        }
        return nutritionList;
    }

    /**
     * Recherche et renvoie le sommet correspondant à l'origine spécifiée.
     *
     * @param origine L'origine du sommet à rechercher
     * @return Le sommet correspondant, ou null si non trouvé
     */
    public Sommet getSommetByOrigine(String origine) {
        for (Sommet sommet : sommets) {
            if (sommet.getOrigine().equals(origine)) {
                return sommet;
            }
        }
        return null;
    }

    /**
     * Compte le nombre de chemins entrants pour un sommet spécifié.
     *
     * @param sommet Le sommet pour lequel compter les chemins entrants
     * @return Le nombre de chemins entrants
     */
    public int cheminEntrant(Sommet sommet) {
        int count = 0;
        for (Lier liaison : liaisons) {
            Chemin chemin = liaison.getChemin();
            if (chemin.getDestination().equals(sommet.getOrigine())) {
                count++;
            }
        }
        return count;
    }

    /**
     * Ajoute un chemin sortant à un sommet spécifié.
     *
     * @param sommet Le sommet auquel ajouter le chemin sortant
     * @param chemin Le chemin sortant à ajouter
     */
    public void addFirst(Sommet sommet, Chemin chemin) {
        sommet.addCheminSortant(chemin);

        Sommet sommetDistance = searchSommet(chemin.getDestination());
        sommetDistance.addCheminSortant(chemin);
    }

    /**
     * Recherche et renvoie le sommet correspondant au nom spécifié.
     *
     * @param sommet Le nom du sommet à rechercher
     * @return Le sommet correspondant, ou null si non trouvé
     */
    public Sommet searchSommet(String sommet) {
        for (Sommet item : sommets) {
            if (item.getOrigine().equals(sommet)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Convertit le graphe en une représentation sous forme de carte (Map).
     *
     * @return Une carte représentant le graphe
     */
    public Map<String, Map<String, Double>> convertToGraphMap() {

        Map<String, Map<String, Double>> graph = new HashMap<>();

        // Parcours des chemins
        for (Sommet sommet : sommets) {
            String sommetNom = sommet.getOrigine();
            Map<String, Double> neighbors = new HashMap<>();

            // Parcours des liens sortants du sommet
            for (Chemin lien : sommet.getCheminsSortants()) {
                String destination = lien.getDestination();
                double fiabilite = lien.getFiabilite();
                double distance = lien.getDistance();
                double temps = lien.getTemps();

                // Vous pouvez choisir quelle valeur attribuer au poids en fonction de vos
                // besoins
                // Par exemple, vous pouvez utiliser la fiabilité, la distance ou le temps
                // Ici, j'utilise la fiabilité comme poids
                double poids = fiabilite;

                // Si vous souhaitez utiliser la distance ou le temps comme poids, décommentez
                // la ligne correspondante :
                // double poids = distance;
                // double poids = temps;
                neighbors.put(destination, poids);
            }

            graph.put(sommetNom, neighbors);
        }

        return graph;
    }

}
