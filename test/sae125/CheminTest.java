/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package sae125;

import sae125.graphe.Chemin;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Cette classe contient des tests JUnit pour la classe `Chemin`. Elle teste les
 * méthodes de manipulation des chemins.
 *
 * @author Clément et Yanis
 * @version v1.0
 */
public class CheminTest {

    public CheminTest() {
    }

    /**
     * Effectue les étapes de configuration avant tous les cas de test.
     */
    @BeforeAll
    public static void setUpClass() {
    }

    /**
     * Effectue les étapes de nettoyage après tous les cas de test.
     */
    @AfterAll
    public static void tearDownClass() {
    }

    /**
     * Effectue les étapes de configuration avant chaque cas de test.
     */
    @BeforeEach
    public void setUp() {
    }

    /**
     * Effectue les étapes de nettoyage après chaque cas de test.
     */
    @AfterEach
    public void tearDown() {
    }

    /**
     * Teste la méthode `getOrigine` de la classe `Chemin`. Vérifie si la valeur
     * de l'origine est correcte.
     */
    @Test
    public void testGetOrigine() {
        Chemin chemin = new Chemin("A", "B", 0.9, 10.0, 5.0);

        String expectedOrigine = "A";
        String actualOrigine = chemin.getOrigine();

        assertEquals(expectedOrigine, actualOrigine);
    }

    /**
     * Teste la méthode `setOrigine` de la classe `Chemin`. Vérifie si la
     * modification de l'origine se déroule correctement.
     */
    @Test
    public void testSetOrigine() {
        Chemin chemin = new Chemin("A", "B", 0.9, 10.0, 5.0);

        String expectedOrigine = "C";
        chemin.setOrigine(expectedOrigine);
        String actualOrigine = chemin.getOrigine();

        assertEquals(expectedOrigine, actualOrigine);
    }

    /**
     * Teste la méthode `getDestination` de la classe `Chemin`. Vérifie si la
     * valeur de la destination est correcte.
     */
    @Test
    public void testGetDestination() {
        Chemin chemin = new Chemin("A", "B", 0.9, 10.0, 5.0);

        String expectedDestination = "B";
        String actualDestination = chemin.getDestination();

        assertEquals(expectedDestination, actualDestination);
    }

    /**
     * Teste la méthode `setDestination` de la classe `Chemin`. Vérifie si la
     * modification de la destination se déroule correctement.
     */
    @Test
    public void testSetDestination() {
        Chemin chemin = new Chemin("A", "B", 0.9, 10.0, 5.0);

        String expectedDestination = "C";
        chemin.setDestination(expectedDestination);
        String actualDestination = chemin.getDestination();

        assertEquals(expectedDestination, actualDestination);
    }

    /**
     * Teste la méthode `getFiabilite` de la classe `Chemin`. Vérifie si la
     * valeur de fiabilité est correcte.
     */
    @Test
    public void testGetFiabilite() {
        Chemin chemin = new Chemin("A", "B", 0.9, 10.0, 5.0);

        double expectedFiabilite = 0.9;
        double actualFiabilite = chemin.getFiabilite();

        assertEquals(expectedFiabilite, actualFiabilite, 0.001);
    }

    /**
     * Teste la méthode `setFiabilite` de la classe `Chemin`. Vérifie si la
     * modification de la fiabilité se déroule correctement.
     */
    @Test
    public void testSetFiabilite() {
        Chemin chemin = new Chemin("A", "B", 0.9, 10.0, 5.0);

        double expectedFiabilite = 0.8;
        chemin.setFiabilite(expectedFiabilite);
        double actualFiabilite = chemin.getFiabilite();

        assertEquals(expectedFiabilite, actualFiabilite, 0.001);
    }

    /**
     * Teste la méthode `getDistance` de la classe `Chemin`. Vérifie si la
     * valeur de la distance est correcte.
     */
    @Test
    public void testGetDistance() {
        Chemin chemin = new Chemin("A", "B", 0.9, 10.0, 5.0);

        double expectedDistance = 10.0;
        double actualDistance = chemin.getDistance();

        assertEquals(expectedDistance, actualDistance, 0.001);
    }

    /**
     * Teste la méthode `setDistance` de la classe `Chemin`. Vérifie si la
     * modification de la distance se déroule correctement.
     */
    @Test
    public void testSetDistance() {
        Chemin chemin = new Chemin("A", "B", 0.9, 10.0, 5.0);

        double expectedDistance = 15.0;
        chemin.setDistance(expectedDistance);
        double actualDistance = chemin.getDistance();

        assertEquals(expectedDistance, actualDistance, 0.001);
    }

    /**
     * Teste la méthode `getTemps` de la classe `Chemin`. Vérifie si la valeur
     * du temps est correcte.
     */
    @Test
    public void testGetTemps() {
        Chemin chemin = new Chemin("A", "B", 0.9, 10.0, 5.0);

        double expectedTemps = 5.0;
        double actualTemps = chemin.getTemps();

        assertEquals(expectedTemps, actualTemps, 0.001);
    }

    /**
     * Teste la méthode `setTemps` de la classe `Chemin`. Vérifie si la
     * modification du temps se déroule correctement.
     */
    @Test
    public void testSetTemps() {
        Chemin chemin = new Chemin("A", "B", 0.9, 10.0, 5.0);

        double expectedTemps = 8.0;
        chemin.setTemps(expectedTemps);
        double actualTemps = chemin.getTemps();

        assertEquals(expectedTemps, actualTemps, 0.001);
    }

    /**
     * Teste la méthode `getSuivC` de la classe `Chemin`. Vérifie si la liste
     * des chemins suivants est correcte.
     */
    @Test
    public void testGetSuivC() {
        Chemin chemin = new Chemin("A", "B", 0.9, 10.0, 5.0);
        List<Chemin> suivC = new ArrayList<>();
        suivC.add(new Chemin("B", "C", 0.8, 15.0, 8.0));
        chemin.addSuivC(new Chemin("B", "C", 0.8, 15.0, 8.0));

        List<Chemin> expectedSuivC = suivC;
        List<Chemin> actualSuivC = chemin.getSuivC();

        assertEquals(expectedSuivC, actualSuivC);
    }

    /**
     * Teste la méthode `addSuivC` de la classe `Chemin`. Vérifie si l'ajout
     * d'un chemin suivant se déroule correctement.
     */
    @Test
    public void testAddSuivC() {
        Chemin chemin = new Chemin("A", "B", 0.9, 10.0, 5.0);
        List<Chemin> suivC = new ArrayList<>();
        suivC.add(new Chemin("B", "C", 0.8, 15.0, 8.0));
        chemin.addSuivC(new Chemin("B", "C", 0.8, 15.0, 8.0));

        List<Chemin> expectedSuivC = suivC;
        List<Chemin> actualSuivC = chemin.getSuivC();

        assertEquals(expectedSuivC, actualSuivC);
    }

    /**
     * Teste la méthode `getColor` de la classe `Chemin`. Vérifie si la couleur
     * est correcte.
     */
    @Test
    public void testGetColor() {
        Chemin chemin = new Chemin("A", "B", 0.9, 10.0, 5.0);

        Color expectedColor = Color.BLACK;
        Color actualColor = chemin.getColor();

        assertEquals(expectedColor, actualColor);
    }

    /**
     * Teste la méthode `setColor` de la classe `Chemin`. Vérifie si la
     * modification de la couleur se déroule correctement.
     */
    @Test
    public void testSetColor() {
        Chemin chemin = new Chemin("A", "B", 0.9, 10.0, 5.0);

        Color expectedColor = Color.RED;
        chemin.setColor(expectedColor);
        Color actualColor = chemin.getColor();

        assertEquals(expectedColor, actualColor);
    }

    /**
     * Teste la méthode `equals` de la classe `Chemin`. Vérifie si deux chemins
     * sont égaux.
     */
    @Test
    public void testEquals() {
        Chemin chemin1 = new Chemin("A", "B", 0.9, 10.0, 5.0);
        Chemin chemin2 = new Chemin("A", "B", 0.9, 10.0, 5.0);

        assertTrue(chemin1.equals(chemin2));
    }

    /**
     * Teste la méthode `equals` de la classe `Chemin` pour des chemins
     * différents. Vérifie si deux chemins sont différents.
     */
    @Test
    public void testNotEquals() {
        Chemin chemin1 = new Chemin("A", "B", 0.9, 10.0, 5.0);
        Chemin chemin2 = new Chemin("B", "C", 0.8, 15.0, 8.0);

        assertFalse(chemin1.equals(chemin2));
    }

    /**
     * Teste la méthode toString de la classe Chemin. Cette méthode permet de
     * vérifier si la représentation textuelle d'un objet Chemin est correcte.
     */
    @Test
    public void testToString() {
        Chemin chemin = new Chemin("A", "B", 0.9, 10.0, 5.0);

        String expectedString = "chemin : \n"
                + "origine : A, destination : B\n"
                + "fiabilité : 0.9\n"
                + "distance : 10.0\n"
                + "temps : 5.0\n";
        String actualString = chemin.toString();

        assertEquals(expectedString, actualString);
    }

}
