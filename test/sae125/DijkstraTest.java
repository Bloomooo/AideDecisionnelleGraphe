/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package sae125;

import sae125.graphe.Lier;
import sae125.graphe.Chemin;
import sae125.dijkstra.Dijkstra;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour la classe Dijkstra.
 * 
 * Cette classe teste la méthode dijkstra de la classe Dijkstra.
 * 
 * @author Clement et Yanis
 * @version v1.0
 */
public class DijkstraTest {
    
    /**
     * Constructeur par défaut de la classe DijkstraTest.
     */
    public DijkstraTest() {
    }
    
    /**
     * Méthode exécutée une fois avant tous les tests de la classe.
     */
    @BeforeAll
    public static void setUpClass() {
    }
    
    /**
     * Méthode exécutée une fois après tous les tests de la classe.
     */
    @AfterAll
    public static void tearDownClass() {
    }
    
    /**
     * Méthode exécutée avant chaque test.
     */
    @BeforeEach
    public void setUp() {
    }
    
    /**
     * Méthode exécutée après chaque test.
     */
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test de la méthode dijkstra de la classe Dijkstra.
     * 
     * Ce test vérifie le bon fonctionnement de la méthode dijkstra en utilisant des données de test spécifiques.
     */
    @Test
    public void testDijkstra() {
        System.out.println("dijkstra");

        // Données de test
        String depart = "A";
        String arrivee = "F";
        List<Lier> liens = new ArrayList<>();
        liens.add(new Lier(new Chemin("A", "B", 10.0, 0.9, 1.5)));
        liens.add(new Lier(new Chemin("A", "C", 5.0, 0.8, 2.0)));
        liens.add(new Lier(new Chemin("B", "D", 7.0, 0.7, 1.0)));
        liens.add(new Lier(new Chemin("C", "D", 3.0, 0.6, 2.5)));
        liens.add(new Lier(new Chemin("C", "E", 2.0, 0.5, 0.5)));
        liens.add(new Lier(new Chemin("D", "F", 6.0, 0.9, 2.0)));
        liens.add(new Lier(new Chemin("E", "F", 8.0, 0.8, 1.5)));
        String critere = "Distance";

        // Résultat attendu
        List<String> expResult = new ArrayList<>();
        expResult.add("A");
        expResult.add("C");
        expResult.add("E");
        expResult.add("F");

        // Appel de la méthode à tester
        List<String> result = Dijkstra.dijkstra(depart, arrivee, liens, critere);

        // Vérification du résultat
        assertEquals(expResult, result);
    }
    
}
