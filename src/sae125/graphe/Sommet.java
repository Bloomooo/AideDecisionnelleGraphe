/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sae125.graphe;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Représente un sommet dans un graphe. Les sommets sont utilisés pour modéliser
 * les nœuds d'un graphe. Chaque sommet possède un identifiant d'origine, un
 * type, des informations de position, une liste de chemins sortants, une
 * indication de visite, une distance, une liste de sommets précédents, et
 * d'autres attributs. Les sommets sont comparables en fonction de leur
 * distance.
 *
 * @author Clement
 * @author Yanis
 * @version v1.0
 */
public class Sommet implements Comparable<Sommet> {

    /**
     * L'origine du sommet.
     */
    private String origine;
    /**
     * Le type du sommet.
     */
    private String typeS;

    /**
     * Le sommet suivant.
     */
    private Sommet suivS;

    /**
     * La liste des sommets liés à ce sommet.
     */
    private java.util.List<Sommet> liste;

    /**
     * La destination du sommet.
     */
    private String destination;

    /**
     * La liste des chemins sortants du sommet.
     */
    private final java.util.List<Chemin> cheminsSortants;

    /**
     * Indique si le sommet a été visité lors d'un parcours.
     */
    private boolean visite;

    /**
     * La distance du sommet à un autre sommet dans un contexte spécifique.
     */
    private double distance;

    /**
     * La liste des sommets précédents dans un parcours.
     */
    private java.util.List<Sommet> precedents;

    /**
     * La coordonnée X du sommet dans un espace graphique.
     */
    private int x;

    /**
     * La coordonnée Y du sommet dans un espace graphique.
     */
    private int y;

    /**
     * La hauteur du sommet dans un espace graphique.
     */
    private int height;

    /**
     * La largeur du sommet dans un espace graphique.
     */
    private int width;

    /**
     * La couleur du sommet dans un espace graphique.
     */
    private Color color;

    /**
     * Indique si le sommet est sélectionné dans un contexte graphique.
     */
    private boolean selectionne;

    /**
     * Le nombre de sommets sélectionnés dans un contexte graphique.
     */
    private static int selectedCount = 0;

    /**
     * La couleur de la bordure du sommet dans un espace graphique.
     */
    private Color borderColor;

    /**
     * La liste des chemins entrants du sommet.
     */
    private final List<Chemin> cheminsEntrants;

    /**
     * Le numéro du sommet.
     */
    private final int number;

    /**
     * Constructeur de la classe Sommet.
     *
     * @param origine l'identifiant d'origine du sommet
     * @param type    le type du sommet
     * @param x       la coordonnée x du sommet
     * @param y       la coordonnée y du sommet
     * @param height  la hauteur du sommet
     * @param width   la largeur du sommet
     * @param number  le numéro du sommet
     */
    public Sommet(String origine, String type, int x, int y, int height, int width, int number) {
        this.origine = origine;
        this.typeS = type;
        this.suivS = null;
        this.precedents = new ArrayList<>();
        this.liste = new ArrayList<>();
        this.cheminsSortants = new ArrayList<>();
        this.destination = null;
        this.visite = false;
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.color = Color.gray;
        this.selectionne = false;
        this.borderColor = Color.gray;
        cheminsEntrants = new ArrayList<>();
        this.number = number;
    }

    /**
     * Retourne le numéro du sommet.
     *
     * @return le numéro du sommet
     */
    public int getNumber() {
        return this.number;
    }

    /**
     * Ajoute un chemin entrant au sommet.
     *
     * @param chemin le chemin entrant à ajouter
     */
    public void addCheminEntrant(Chemin chemin) {
        cheminsEntrants.add(chemin);
    }

    /**
     * Retourne la couleur de la bordure du sommet.
     *
     * @return la couleur de la bordure du sommet
     */
    public Color getBorderColor() {
        return this.borderColor;
    }

    /**
     * Définit la couleur de la bordure du sommet.
     *
     * @param borderColor la couleur de la bordure du sommet
     */
    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    /**
     * Retourne la liste des chemins sortants du sommet.
     *
     * @return la liste des chemins sortants du sommet
     */
    public java.util.List<Chemin> getCheminsSortants() {
        return this.cheminsSortants;
    }

    /**
     * Ajoute un chemin sortant au sommet.
     *
     * @param chemin le chemin sortant à ajouter
     */
    public void addCheminSortant(Chemin chemin) {
        cheminsSortants.add(chemin);
    }

    /**
     * Retourne l'identifiant d'origine du sommet.
     *
     * @return l'identifiant d'origine du sommet
     */
    public String getOrigine() {
        return this.origine;
    }

    /**
     * Définit l'identifiant d'origine du sommet.
     *
     * @param origine l'identifiant d'origine du sommet
     */
    public void setOrigine(String origine) {
        this.origine = origine;
    }

    /**
     * Indique si le sommet a été visité.
     *
     * @return true si le sommet a été visité, sinon false
     */
    public boolean isVisite() {
        return this.visite;
    }

    /**
     * Définit si le sommet a été visité.
     *
     * @param visite true si le sommet a été visité, sinon false
     */
    public void setVisite(boolean visite) {
        this.visite = visite;
    }

    /**
     * Retourne le type du sommet.
     *
     * @return le type du sommet
     */
    public String getTypeS() {
        return this.typeS;
    }

    /**
     * Définit le type du sommet.
     *
     * @param typeS le type du sommet
     */
    public void setTypeS(String typeS) {
        this.typeS = typeS;
    }

    /**
     * Retourne le sommet suivant.
     *
     * @return le sommet suivant
     */
    public Sommet getSuivS() {
        return this.suivS;
    }

    /**
     * Définit le sommet suivant.
     *
     * @param suivS le sommet suivant
     */
    public void setSuivS(Sommet suivS) {
        this.suivS = suivS;
    }

    /**
     * Retourne la liste de sommets adjacents.
     *
     * @return la liste de sommets adjacents
     */
    public java.util.List<Sommet> getListe() {
        return this.liste;
    }

    /**
     * Ajoute un sommet à la liste de sommets adjacents.
     *
     * @param sommet le sommet à ajouter
     */
    public void addListe(Sommet sommet) {
        this.liste.add(sommet);
    }

    /**
     * Retourne la destination du sommet.
     *
     * @return la destination du sommet
     */
    public String getDestination() {
        return this.destination;
    }

    /**
     * Définit la destination du sommet.
     *
     * @param destination la destination du sommet
     */
    public void setDestionation(String destination) {
        this.destination = destination;
    }

    /**
     * Retourne la distance du sommet.
     *
     * @return la distance du sommet
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Définit la distance du sommet.
     *
     * @param distance la distance du sommet
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * Retourne la couleur du sommet.
     *
     * @return la couleur du sommet
     */
    public Color getColor() {
        return color;
    }

    /**
     * Définit la couleur du sommet.
     *
     * @param color la couleur du sommet
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Indique si le sommet est sélectionné.
     *
     * @return true si le sommet est sélectionné, sinon false
     */
    public boolean isSelectionne() {
        return selectionne;
    }

    /**
     * Définit si le sommet est sélectionné.
     *
     * @param selectionne true si le sommet est sélectionné, sinon false
     */
    public void setSelectionne(boolean selectionne) {
        this.selectionne = selectionne;
    }

    /**
     * Retourne le nombre de sommets sélectionnés.
     *
     * @return le nombre de sommets sélectionnés
     */
    public static int getSelectedCount() {
        return selectedCount;
    }

    /**
     * Définit le nombre de sommets sélectionnés.
     *
     * @param count le nombre de sommets sélectionnés
     */
    public static void setSelectedCount(int count) {
        selectedCount = count;
    }

    /**
     * Retourne si le sommet est sélectionné.
     *
     * @return true si le sommet est sélectionné, sinon false
     */
    public boolean getSelectionne() {
        return this.selectionne;
    }

    /**
     * Retourne la liste des sommets précédents.
     *
     * @return la liste des sommets précédents
     */
    public java.util.List<Sommet> getPrec() {
        return this.precedents;
    }

    /**
     * Ajoute un sommet précédent à la liste des sommets précédents.
     *
     * @param precedent le sommet précédent à ajouter
     */
    public void addPrecedents(Sommet precedent) {
        this.precedents.add(precedent);
    }

    /**
     * Compare ce sommet avec un autre sommet en fonction de leur distance.
     *
     * @param autreSommet le sommet à comparer
     * @return un entier négatif si ce sommet a une distance plus petite que
     *         l'autreSommet, zéro si les distances des deux sommets sont égales, un
     *         entier positif si ce sommet a une distance plus grande que
     *         l'autreSommet
     */
    @Override
    public int compareTo(Sommet autreSommet) {
        // Comparaison basée sur la distance
        return Double.compare(this.distance, autreSommet.distance);
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères du sommet.
     * La représentation contient l'origine du sommet.
     *
     * @return la représentation du sommet en tant que chaîne de caractères
     */
    @Override
    public String toString() {
        return "origine : " + getOrigine();
    }

    /**
     * Définit la liste des sommets adjacents du sommet.
     *
     * @param liste la liste des sommets adjacents
     */
    public void setListe(java.util.List<Sommet> liste) {
        this.liste = liste;
    }

    /**
     * Définit la destination du sommet.
     *
     * @param destination la destination du sommet
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * Retourne l'état de visite du sommet.
     *
     * @return true si le sommet a été visité, sinon false
     */
    public boolean getVisite() {
        return this.visite;
    }

    /**
     * Retourne la liste des sommets précédents du sommet.
     *
     * @return la liste des sommets précédents
     */
    public java.util.List<Sommet> getPrecedents() {
        return this.precedents;
    }

    /**
     * Définit la liste des sommets précédents du sommet.
     *
     * @param precedents la liste des sommets précédents
     */
    public void setPrecedents(java.util.List<Sommet> precedents) {
        this.precedents = precedents;
    }

    /**
     * Retourne la coordonnée x du sommet.
     *
     * @return la coordonnée x du sommet
     */
    public int getX() {
        return this.x;
    }

    /**
     * Définit la coordonnée x du sommet.
     *
     * @param x la coordonnée x du sommet
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Retourne la coordonnée y du sommet.
     *
     * @return la coordonnée y du sommet
     */
    public int getY() {
        return this.y;
    }

    /**
     * Définit la coordonnée y du sommet.
     *
     * @param y la coordonnée y du sommet
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Retourne la hauteur du sommet.
     *
     * @return la hauteur du sommet
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Définit la hauteur du sommet.
     *
     * @param height la hauteur du sommet
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Retourne la largeur du sommet.
     *
     * @return la largeur du sommet
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Définit la largeur du sommet.
     *
     * @param width la largeur du sommet
     */
    public void setWidth(int width) {
        this.width = width;
    }

}
