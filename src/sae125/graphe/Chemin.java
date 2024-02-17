/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sae125.graphe;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente un chemin reliant deux sommets dans un graphe. Contient des
 * informations telles que l'origine, la destination, la fiabilité, la distance
 * et le temps. Permet également d'ajouter des chemins suivants et de définir la
 * couleur du chemin. Implémente les méthodes equals et toString.
 *
 * @author Clement
 * @author Yanis
 * @version v1.0
 */
public class Chemin {

    /**
     * L'origine du chemin.
     */
    private String origine;

    /**
     * L'origine du chemin.
     */
    private String destination;

    /**
     * La fiabilité du chemin.
     */
    private double fiabilite;

    /**
     * La distance du chemin.
     */
    private double distance;

    /**
     * Le temps du chemin.
     */
    private double temps;

    /**
     * La liste des chemins suivants.
     */
    private List<Chemin> suivC;

    /**
     * La couleur du chemin.
     */
    private java.awt.Color color;

    /**
     * Constructeur de la classe Chemin.
     *
     * @param origine     L'origine du chemin.
     * @param destination La destination du chemin.
     * @param fiabilite   La fiabilité du chemin.
     * @param distance    La distance du chemin.
     * @param temps       Le temps du chemin.
     */
    public Chemin(String origine, String destination, double fiabilite, double distance, double temps) {
        this.origine = origine;
        this.destination = destination;
        this.fiabilite = fiabilite;
        this.distance = distance;
        this.temps = temps;
        this.suivC = null;
        this.color = java.awt.Color.black;
        this.suivC = new ArrayList<>();
    }

    /**
     * Retourne l'origine du chemin.
     *
     * @return L'origine du chemin.
     */
    public String getOrigine() {
        return this.origine;
    }

    /**
     * Définit l'origine du chemin.
     *
     * @param origine La nouvelle origine du chemin.
     */
    public void setOrigine(String origine) {
        this.origine = origine;
    }

    /**
     * Retourne la destination du chemin.
     *
     * @return La destination du chemin.
     */
    public String getDestination() {
        return this.destination;
    }

    /**
     * Retourne la fiabilité du chemin.
     *
     * @return La fiabilité du chemin.
     */
    public double getFiabilite() {
        return this.fiabilite;
    }

    /**
     * Retourne la liste des chemins suivants.
     *
     * @return La liste des chemins suivants.
     */
    public List<Chemin> getSuivC() {
        return suivC;
    }

    /**
     * Retourne la distance du chemin.
     *
     * @return La distance du chemin.
     */
    public double getDistance() {
        return this.distance;
    }

    /**
     * Retourne le temps du chemin.
     *
     * @return Le temps du chemin.
     */
    public double getTemps() {
        return this.temps;
    }

    /**
     * Définit la destination du chemin.
     *
     * @param destination La nouvelle destination du chemin.
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * Définit la fiabilité du chemin.
     *
     * @param fiabilite La nouvelle fiabilité du chemin.
     */
    public void setFiabilite(double fiabilite) {
        this.fiabilite = fiabilite;
    }

    /**
     * Définit la distance du chemin.
     *
     * @param distance La nouvelle distance du chemin.
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * Définit le temps du chemin.
     *
     * @param temps Le nouveau temps du chemin.
     */
    public void setTemps(double temps) {
        this.temps = temps;
    }

    /**
     * Ajoute un chemin suivant à la liste des chemins suivants.
     *
     * @param suivant Le chemin suivant à ajouter.
     */
    public void addSuivC(Chemin suivant) {
        this.suivC.add(suivant);
    }

    /**
     * Retourne la couleur du chemin.
     *
     * @return La couleur du chemin.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Définit la couleur du chemin.
     *
     * @param color La nouvelle couleur du chemin.
     */
    public void setColor(java.awt.Color color) {
        this.color = color;
    }

    /**
     * Vérifie si le chemin est égal à un autre objet. Deux chemins sont
     * considérés égaux s'ils ont la même origine et la même destination.
     *
     * @param obj L'objet à comparer.
     * @return true si les chemins sont égaux, false sinon.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Chemin other = (Chemin) obj;
        return origine.equals(other.origine) && destination.equals(other.destination);
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères du chemin.
     *
     * @return La représentation du chemin en tant que chaîne de caractères.
     */
    @Override
    public String toString() {
        return "chemin : " + "\n"
                + "origine : " + getOrigine() + ", "
                + "destination : " + getDestination() + "\n"
                + "fiabilité : " + getFiabilite() + "\n"
                + "distance : " + getDistance() + "\n"
                + "temps : " + getTemps() + "\n";
    }
}
