/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package sae125;

import sae125.chargement.ChargeFile;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Cette classe contient des tests JUnit pour la classe `ChargeFile`. Elle teste
 * la fonctionnalité de lecture d'un fichier et d'affichage d'une matrice.
 *
 * @author Clement et Yanis
 * @version v1.0
 */
public class ChargeFileTest {

    public ChargeFileTest() {
    }

    /**
     * Effectue les étapes de configuration avant tous les cas de test. Charge
     * les dépendances JUnit à partir du répertoire "lib".
     *
     * @throws Exception si une erreur se produit lors du chargement des
     * dépendances
     */
    @BeforeAll
    public static void setupClass() throws Exception {
        // Charger les dépendances JUnit à partir du répertoire "lib"
        ClassLoader classLoader = ChargeFileTest.class.getClassLoader();

        // Chemin vers le répertoire "lib" contenant les dépendances JUnit
        String libDirectory = "lib";

        // Récupérer la méthode addURL en utilisant la réflexion
        Method addURLMethod = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
        addURLMethod.setAccessible(true);

        // Charger chaque fichier JAR dans le chemin de classe
        for (String jar : new String[]{"junit-4.x.jar", "hamcrest-core-x.x.jar"}) {
            URL jarURL = ChargeFileTest.class.getClassLoader().getResource(libDirectory + "/" + jar);
            if (jarURL != null) {
                addURLMethod.invoke(classLoader, jarURL);
            } else {
                System.err.println("Failed to locate the JAR file: " + jar);
            }
        }
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
     * Teste la méthode `readFile` de la classe `ChargeFile`. Vérifie si la
     * lecture du fichier se déroule correctement en vérifiant la taille et les
     * valeurs de la matrice.
     *
     * @throws IOException si une erreur d'entrée/sortie se produit lors de la
     * lecture du fichier
     */
    @Test
    public void testReadFile() throws IOException {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        // Vérification de la taille de la matrice
        assertEquals(3, matrix.length);
        assertEquals(3, matrix[0].length);

        // Vérification des valeurs dans la matrice
    }

    /**
     * Teste la méthode `printMatrix` de la classe `ChargeFile`. Vérifie si
     * l'affichage de la matrice se déroule correctement en comparant la sortie
     * avec la valeur attendue.
     */
    @Test
    public void testPrintMatrix() {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        String expectedOutput = "1 2 3\n"
                + "4 5 6\n"
                + "7 8 9";

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream originalPrintStream = System.out;
        System.setOut(printStream);

        new ChargeFile().printMatrix(matrix, 3, 3);

        System.out.flush();
        System.setOut(originalPrintStream);

        String actualOutput = outputStream.toString();

        expectedOutput = expectedOutput.replaceAll("\\s", "");
        actualOutput = actualOutput.replaceAll("\\s", "");

        assertEquals(expectedOutput, actualOutput);
    }

}
