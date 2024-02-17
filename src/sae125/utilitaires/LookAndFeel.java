/*
 * Copyright (C) 2022 IUT
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package sae125.utilitaires;

import sae125.DrawGraphe;

/**
 * Cette classe représente les différents "Look And Feel" Java installés
 * 
 * @author Joseph Briguet
 * @version 1.0
 */
public enum LookAndFeel {

    // <editor-fold defaultstate="collapsed" desc=" CONSTANTS">
    /**
     * Correspond au Look And Feel "Metal"
     */
    METAL("Metal"),

    /**
     * Correspond au Look And Feel "Nimbus"
     */
    NIMBUS("Nimbus"),

    /**
     * Correspond au Look And Feel "CDE/Motif"
     */
    CDE_MOTIF("CDE/Motif"),

    /**
     * Correspond au Look And Feel "Windows"
     */
    WINDOWS("Windows"),

    /**
     * Correspond au Look And Feel "Windows Classic"
     */
    WINDOWS_CLASSIC("Windows Classic");
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" ATTRIBUT">
    /**
     * Correspond au nom du look and feel
     */
    private final String name;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" CONSTRUCTOR">
    /**
     * Crée un look and feel
     * 
     * @param name Correspond au nom du look and feel
     */
    private LookAndFeel(String name) {
        this.name = name;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" METHODE PUBLIC">
    /**
     * Renvoie le look and feel sous la forme d'une chaîne de caractères
     * 
     * @return Retourne le look and feel sous la forme d'une chaîne de caractères
     */
    @Override
    public String toString() {
        return this.name;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" METHODE PUBLIC STATIC">
    /**
     * Initialise le Look And Feel (le type d'affichage des composants graphiques)
     * 
     * @param laf Correspond au Look And Feel utilisé
     */
    public static void init(LookAndFeel laf) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (laf.toString().equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DrawGraphe.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }
    }
    // </editor-fold>

}