package sae125.graphe;

import sae125.dijkstra.Dijkstra;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import sae125.chargement.ChargeFile;
import sae125.DrawGraphe;
import sae125.chargement.File;
import sae125.modele.ModelTableDijkstra;
import sae125.modele.ModelTableVoisin;

/**
 * GraphPanel est une classe qui hérite de JPanel et représente un panneau
 * graphique permettant de visualiser et de manipuler un graphe.
 *
 * @author Clement
 * @author Yanis
 * @version v1.0
 */
public class GraphPanel extends JPanel {

    /**
     * Cette classe représente une liste de graphes.
     */
    private ListeGraphe listeGraphe;

    /**
     * Liste des sommets du graphe.
     */
    private java.util.List<Sommet> listeSommets = new ArrayList<>();

    /**
     * Liste des chemins du graphe.
     */
    private java.util.List<Chemin> listeChemins = new ArrayList<>();

    /**
     * Liste des chemins utilisés.
     */
    private List<String> chemin;

    /**
     * Sommet en cours de déplacement.
     */
    private Sommet sommetEnDeplacement;

    /**
     * Premier sommet sélectionné.
     */
    private Sommet sommetSelectionne1;

    /**
     * Premier sommet sélectionné pour l'algorithme de Dijkstra.
     */
    private Sommet sommetSelectionneForDijkstra1;

    /**
     * Deuxième sommet sélectionné pour l'algorithme de Dijkstra.
     */
    private Sommet sommetSelectionneForDijkstra2;

    /**
     * Label d'information.
     */
    private JLabel infoJLabel = new JLabel();

    /**
     * Label de type.
     */
    private JLabel typeLabel = new JLabel();

    /**
     * Indique si un déplacement est actif.
     */
    private boolean deplacementActif = false;

    /**
     * Indique si le premier voisin pour le calcul de distance est sélectionné.
     */
    private boolean voisin1DistanceSelected = false;

    /**
     * Indique si le deuxième voisin pour le calcul de distance est sélectionné.
     */
    private boolean voisin2DistanceSelected = false;

    /**
     * Indique si le voisin N pour le calcul de distance est sélectionné.
     */
    private boolean voisinNDistanceSelected = false;

    /**
     * Ensemble de voisins pour le premier voisinage.
     */
    private Set<String> voisinFor1Distance = new HashSet<>();

    /**
     * Ensemble de voisins pour le deuxième voisinage.
     */
    private Set<String> voisinFor2Distance = new HashSet<>();

    /**
     * Ensemble de voisins pour le voisinage N.
     */
    private Set<String> voisinForNDistance = new HashSet<>();

    /**
     * Liste des voisins.
     */
    private final List<Sommet> voisins = new ArrayList<>();

    /**
     * Liste des liens entre les sommets.
     */
    private List<Lier> lLiaison;

    /**
     * Liste des sommets de maternité.
     */
    private List<Sommet> materniteList = new ArrayList<>();

    /**
     * Liste des sommets de bloc.
     */
    private List<Sommet> blocList = new ArrayList<>();

    /**
     * Liste des sommets de nutrition.
     */
    private List<Sommet> nutritionList = new ArrayList<>();

    /**
     * Composant d'affichage du graphe.
     */
    private final DrawGraphe drawGraphe;

    /**
     * Critère de recherche utilisé (Distance ou autre).
     */
    private String critere = "Distance";

    /**
     * Modèle de table pour l'algorithme de Dijkstra.
     */
    private final ModelTableDijkstra modelTableDijkstra;

    /**
     * Fiabilité du graphe.
     */
    private double fiabilite;

    /**
     * Distance du graphe.
     */
    private double distance;

    /**
     * Temps du graphe.
     */
    private double temps;

    /**
     * Liste des valeurs.
     */
    private final List<Double> listeValeurs = new ArrayList<>();

    /**
     * Modèle de table pour les voisins.
     */
    private final ModelTableVoisin modelTableVoisin;

    /**
     * Constructeur de la classe GraphPanel.
     *
     * @param drawGraphe         le composant d'affichage du graphe.
     * @param modelTableDijkstra le modèle de table pour l'algorithme de
     *                           Dijkstra.
     * @param modelTableVoisin   le modèle de table pour les voisins.
     */
    public GraphPanel(DrawGraphe drawGraphe, ModelTableDijkstra modelTableDijkstra, ModelTableVoisin modelTableVoisin) {
        this.drawGraphe = drawGraphe;
        this.modelTableDijkstra = modelTableDijkstra;
        this.modelTableVoisin = modelTableVoisin;
    }

    /**
     * Initialise le graphe en chargeant les sommets et les arêtes à partir d'un
     * fichier.
     *
     * @param filePath le chemin du fichier contenant les informations du
     *                 graphe.
     * @throws IOException si une erreur d'entrée/sortie se produit lors de la
     *                     lecture du fichier.
     */
    public void initializeGraph(String filePath) throws IOException {
        listeGraphe = new ListeGraphe(30);
        this.setBackground(Color.white);
        listeSommets = listeGraphe.getSommets();
        listeChemins = listeGraphe.getChemins();
        int numSommet = 1;
        File fichierFile = new File();
        fichierFile.readMatrice(filePath);
        List<String> types = new ArrayList<>();
        types.add("Maternite");
        types.add("Maternite");
        types.add("Maternite");
        types.add("Bloc");
        types.add("Nutrition");
        Random random = new Random();

        int[][] matriceadj = ChargeFile.readFile();

        lLiaison = new ArrayList<>();

        listeGraphe.loadLiaisons(lLiaison);

        int minDistance = 50; // Distance minimale entre les sommets

        for (int i = 0; i < 30; i++) {
            String sommet = "S" + numSommet;
            String type = types.get(random.nextInt(types.size()));
            int x = random.nextInt(400);
            int y = random.nextInt(400);

            boolean tooClose = true;
            while (tooClose) {
                tooClose = false;

                for (Sommet existingSommet : listeSommets) {
                    int distance = (int) Math
                            .sqrt(Math.pow(x - existingSommet.getX(), 2) + Math.pow(y - existingSommet.getY(), 2));
                    if (distance < minDistance) {
                        tooClose = true;
                    }
                }

                if (tooClose) {
                    x = random.nextInt(780);
                    y = random.nextInt(550);
                }
            }

            listeGraphe.addSommet(sommet, type, x, y, 30, 30, numSommet);

            numSommet++;
        }

        int numDest = 1;
        int comp = 0;

        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                if (matriceadj[i][j] != 0) {
                    String origine = "S" + (i + 1);
                    String destination = "S" + numDest;
                    double fiabilite = fichierFile.getFiabilite()[comp];
                    double distance = fichierFile.getDistance()[comp];
                    double temps = fichierFile.getTemps()[comp];

                    listeGraphe.addEdge(origine, destination, fiabilite, distance, temps);

                    comp++;
                }
                numDest++;
            }
            numDest = 1;
        }
        listeGraphe.loadLiaisons(lLiaison);
    }

    /**
     * Affiche les sommets de type "Nutrition" en les colorant.
     */
    public void afficherNutri() {
        colorerNutri();
    }

    /**
     * Affiche les sommets de type "Maternite" en les colorant.
     */
    public void afficherMaternite() {
        colorerMaternite();
    }

    /**
     * Affiche les sommets de type "Bloc" en les colorant.
     */
    public void afficherBloc() {
        colorerBloc();
    }

    /**
     * Lance l'algorithme pour afficher les voisins à distance 1 d'un sommet de
     * type "Nutrition". Met à jour l'affichage et la table des voisins.
     */
    public void lancerNutri1Distance() {
        nutri1Distance();
        repaint();
    }

    /**
     * Lance l'algorithme pour afficher les voisins à distance 1 d'un sommet de
     * type "Maternite". Met à jour l'affichage et la table des voisins.
     */
    public void lancerMat1Distance() {
        mat1Distance();
        repaint();
    }

    /**
     * Lance l'algorithme pour afficher les voisins à distance 1 d'un sommet de
     * type "Bloc". Met à jour l'affichage et la table des voisins.
     */
    public void lancerBloc1Distance() {
        bloc1Distance();
        repaint();
    }

    /**
     * Lance l'algorithme pour afficher les voisins à distance 2 d'un sommet de
     * type "Nutrition". Met à jour l'affichage et la table des voisins.
     */
    public void lancerNutri2Distance() {
        nutri2Distance();
        repaint();
    }

    /**
     * Lance l'algorithme pour afficher les voisins à distance N d'un sommet de
     * type "Nutrition". Met à jour l'affichage et la table des voisins.
     *
     * @param n la distance souhaitée.
     */
    public void lancerNutriNDistance(int n) {
        nutriNDistance(n);
        repaint();
    }

    /**
     * Lance l'algorithme pour afficher les voisins à distance 2 d'un sommet de
     * type "Maternite". Met à jour l'affichage et la table des voisins.
     */
    public void lancerMat2Distance() {
        mat2Distance();
        repaint();
    }

    /**
     * Lance l'algorithme pour afficher les voisins à distance N d'un sommet de
     * type "Maternite". Met à jour l'affichage et la table des voisins.
     *
     * @param n la distance souhaitée.
     */
    public void lancerMatNDistance(int n) {
        matNDistance(n);
        repaint();
    }

    /**
     * Lance l'algorithme pour afficher les voisins à distance 2 d'un sommet de
     * type "Bloc". Met à jour l'affichage et la table des voisins.
     */
    public void lancerBloc2Distance() {
        bloc2Distance();
        repaint();
    }

    /**
     * Lance l'algorithme pour afficher les voisins à distance N d'un sommet de
     * type "Bloc". Met à jour l'affichage et la table des voisins.
     *
     * @param n la distance souhaitée.
     */
    public void lancerBlocNDistance(int n) {
        blocNDistance(n);
        repaint();
    }

    /**
     * Algorithme pour afficher les voisins à distance 1 d'un sommet de type
     * "Nutrition". Met à jour la couleur des sommets, la table des voisins et
     * les variables de sélection.
     */
    private void nutri1Distance() {
        modelTableVoisin.clearData();
        voisins.clear();
        resetColorAll();
        voisinFor1Distance = listeGraphe.oneDistNeighbors(getSommetSelected(), 1);
        nutritionList = listeGraphe.printNutri();
        voisin1DistanceSelected = true;
        voisin2DistanceSelected = false;
        voisinNDistanceSelected = false;

        Set<Sommet> sommetsAjoutes = new HashSet<>();

        for (String nomSommetsommet : voisinFor1Distance) {
            Sommet sommet = listeGraphe.searchSommet(nomSommetsommet);
            for (Sommet nutri : nutritionList) {
                if (sommet.getOrigine().equals(nutri.getOrigine())) {
                    sommet.setColor(Color.GREEN);
                    sommetsAjoutes.add(sommet);
                }
            }
        }

        voisins.addAll(sommetsAjoutes);
        modelTableVoisin.addVoisin(voisins);
    }

    /**
     * Algorithme pour afficher les voisins à distance 2 d'un sommet de type
     * "Nutrition". Met à jour la couleur des sommets, la table des voisins et
     * les variables de sélection.
     */
    private void nutri2Distance() {
        modelTableVoisin.clearData();
        voisins.clear();
        resetColorAll();
        voisinFor2Distance = listeGraphe.oneDistNeighbors(getSommetSelected(), 2);
        nutritionList = listeGraphe.printNutri();
        voisin1DistanceSelected = false;
        voisin2DistanceSelected = true;
        voisinNDistanceSelected = false;

        Set<Sommet> sommetsAjoutes = new HashSet<>();

        for (String nomSommetsommet : voisinFor2Distance) {
            Sommet sommet = listeGraphe.searchSommet(nomSommetsommet);
            for (Sommet nutri : nutritionList) {
                if (sommet.getOrigine().equals(nutri.getOrigine())) {
                    sommet.setColor(Color.GREEN);
                    sommetsAjoutes.add(sommet);
                }
            }
        }

        voisins.addAll(sommetsAjoutes);
        modelTableVoisin.addVoisin(voisins);
    }

    /**
     * Algorithme pour afficher les voisins à distance N d'un sommet de type
     * "Nutrition". Met à jour la couleur des sommets, la table des voisins et
     * les variables de sélection.
     *
     * @param n la distance souhaitée.
     */
    private void nutriNDistance(int n) {
        modelTableVoisin.clearData();
        voisins.clear();
        resetColorAll();
        voisinForNDistance = listeGraphe.oneDistNeighbors(getSommetSelected(), n);
        nutritionList = listeGraphe.printNutri();
        voisin1DistanceSelected = false;
        voisin2DistanceSelected = false;
        voisinNDistanceSelected = true;

        Set<Sommet> sommetsAjoutes = new HashSet<>();

        for (String nomSommetsommet : voisinForNDistance) {
            Sommet sommet = listeGraphe.searchSommet(nomSommetsommet);
            for (Sommet nutri : nutritionList) {
                if (sommet.getOrigine().equals(nutri.getOrigine())) {
                    sommet.setColor(Color.GREEN);
                    sommetsAjoutes.add(sommet);
                }
            }
        }

        voisins.addAll(sommetsAjoutes);
        modelTableVoisin.addVoisin(voisins);
    }

    /**
     * Algorithme pour afficher les voisins à distance 1 d'un sommet de type
     * "Maternite". Met à jour la couleur des sommets, la table des voisins et
     * les variables de sélection.
     */
    private void mat1Distance() {
        modelTableVoisin.clearData();
        voisins.clear();
        resetColorAll();
        voisinFor1Distance = listeGraphe.oneDistNeighbors(getSommetSelected(), 1);
        materniteList = listeGraphe.printMat();
        voisin1DistanceSelected = true;
        voisin2DistanceSelected = false;
        voisinNDistanceSelected = false;

        Set<Sommet> sommetsAjoutes = new HashSet<>();

        for (String nomSommetsommet : voisinFor1Distance) {
            Sommet sommet = listeGraphe.searchSommet(nomSommetsommet);
            for (Sommet mat : materniteList) {
                if (sommet.getOrigine().equals(mat.getOrigine())) {
                    sommet.setColor(Color.GREEN);
                    sommetsAjoutes.add(sommet);
                }
            }
        }

        voisins.addAll(sommetsAjoutes);
        modelTableVoisin.addVoisin(voisins);
    }

    /**
     * Algorithme pour afficher les voisins à distance 2 d'un sommet de type
     * "Maternite". Met à jour la couleur des sommets, la table des voisins et
     * les variables de sélection.
     */
    private void mat2Distance() {
        modelTableVoisin.clearData();
        voisins.clear();
        resetColorAll();
        voisinFor2Distance = listeGraphe.oneDistNeighbors(getSommetSelected(), 2);
        materniteList = listeGraphe.printMat();
        voisin1DistanceSelected = false;
        voisin2DistanceSelected = true;
        voisinNDistanceSelected = false;

        Set<Sommet> sommetsAjoutes = new HashSet<>();

        for (String nomSommetsommet : voisinFor2Distance) {
            Sommet sommet = listeGraphe.searchSommet(nomSommetsommet);
            for (Sommet mat : materniteList) {
                if (sommet.getOrigine().equals(mat.getOrigine())) {
                    sommet.setColor(Color.GREEN);
                    sommetsAjoutes.add(sommet);
                }
            }
        }

        voisins.addAll(sommetsAjoutes);
        modelTableVoisin.addVoisin(voisins);
    }

    /**
     * Algorithme pour afficher les voisins à distance N d'un sommet de type
     * "Maternite". Met à jour la couleur des sommets, la table des voisins et
     * les variables de sélection.
     *
     * @param n la distance souhaitée.
     */
    private void matNDistance(int n) {
        modelTableVoisin.clearData();
        voisins.clear();
        resetColorAll();
        voisinForNDistance = listeGraphe.oneDistNeighbors(getSommetSelected(), n);
        materniteList = listeGraphe.printMat();
        voisin1DistanceSelected = false;
        voisin2DistanceSelected = false;
        voisinNDistanceSelected = true;

        Set<Sommet> sommetsAjoutes = new HashSet<>();

        for (String nomSommetsommet : voisinForNDistance) {
            Sommet sommet = listeGraphe.searchSommet(nomSommetsommet);
            for (Sommet mat : materniteList) {
                if (sommet.getOrigine().equals(mat.getOrigine())) {
                    sommet.setColor(Color.GREEN);
                    sommetsAjoutes.add(sommet);
                }
            }
        }

        voisins.addAll(sommetsAjoutes);
        modelTableVoisin.addVoisin(voisins);
    }

    /**
     * Algorithme pour afficher les voisins à distance 1 d'un sommet de type
     * "Bloc". Met à jour la couleur des sommets, la table des voisins et les
     * variables de sélection.
     */
    private void bloc1Distance() {
        modelTableVoisin.clearData();
        voisins.clear();
        resetColorAll();
        voisinFor1Distance = listeGraphe.oneDistNeighbors(getSommetSelected(), 1);
        blocList = listeGraphe.printBloc();
        voisin1DistanceSelected = true;
        voisin2DistanceSelected = false;
        voisinNDistanceSelected = false;

        Set<Sommet> sommetsAjoutes = new HashSet<>();

        for (String nomSommetsommet : voisinFor1Distance) {
            Sommet sommet = listeGraphe.searchSommet(nomSommetsommet);
            for (Sommet bloc : blocList) {
                if (sommet.getOrigine().equals(bloc.getOrigine())) {
                    sommet.setColor(Color.GREEN);
                    sommetsAjoutes.add(sommet);
                }
            }
        }

        voisins.addAll(sommetsAjoutes);
        modelTableVoisin.addVoisin(voisins);
    }

    /**
     * Algorithme pour afficher les voisins à distance 2 d'un sommet de type
     * "Bloc". Met à jour la couleur des sommets, la table des voisins et les
     * variables de sélection.
     */
    private void bloc2Distance() {
        modelTableVoisin.clearData();
        voisins.clear();
        resetColorAll();
        voisinFor2Distance = listeGraphe.oneDistNeighbors(getSommetSelected(), 2);
        blocList = listeGraphe.printBloc();
        voisin1DistanceSelected = false;
        voisin2DistanceSelected = true;
        voisinNDistanceSelected = false;

        Set<Sommet> sommetsAjoutes = new HashSet<>();

        for (String nomSommetsommet : voisinFor2Distance) {
            Sommet sommet = listeGraphe.searchSommet(nomSommetsommet);
            for (Sommet bloc : blocList) {
                if (sommet.getOrigine().equals(bloc.getOrigine())) {
                    sommet.setColor(Color.GREEN);
                    sommetsAjoutes.add(sommet);
                }
            }
        }

        voisins.addAll(sommetsAjoutes);
        modelTableVoisin.addVoisin(voisins);
    }

    /**
     * Algorithme pour afficher les voisins à distance N d'un sommet de type
     * "Bloc". Met à jour la couleur des sommets, la table des voisins et les
     * variables de sélection.
     *
     * @param n la distance souhaitée.
     */
    private void blocNDistance(int n) {
        modelTableVoisin.clearData();
        voisins.clear();
        resetColorAll();
        voisinForNDistance = listeGraphe.oneDistNeighbors(getSommetSelected(), n);
        blocList = listeGraphe.printBloc();
        voisin1DistanceSelected = false;
        voisin2DistanceSelected = false;
        voisinNDistanceSelected = true;

        Set<Sommet> sommetsAjoutes = new HashSet<>();

        for (String nomSommetsommet : voisinForNDistance) {
            Sommet sommet = listeGraphe.searchSommet(nomSommetsommet);
            for (Sommet bloc : blocList) {
                if (sommet.getOrigine().equals(bloc.getOrigine())) {
                    sommet.setColor(Color.GREEN);
                    sommetsAjoutes.add(sommet);
                }
            }
        }

        voisins.addAll(sommetsAjoutes);
        modelTableVoisin.addVoisin(voisins);
    }

    /**
     * Algorithme pour colorer les sommets de type "Nutrition" en magenta. Met à
     * jour la couleur des sommets, la table des voisins et redessine la
     * fenêtre.
     */
    private void colorerNutri() {
        modelTableVoisin.clearData();
        voisins.clear();
        resetColorAll();
        nutritionList.clear();
        nutritionList = listeGraphe.printNutri();

        for (Sommet sommet : nutritionList) {
            sommet.setColor(Color.MAGENTA);
        }

        modelTableVoisin.addVoisin(nutritionList);
        repaint();
    }

    /**
     * Algorithme pour colorer les sommets de type "Maternite" en magenta. Met à
     * jour la couleur des sommets, la table des voisins et redessine la
     * fenêtre.
     */
    private void colorerMaternite() {
        modelTableVoisin.clearData();
        voisins.clear();
        resetColorAll();
        materniteList.clear();
        materniteList = listeGraphe.printMat();

        for (Sommet sommet : materniteList) {
            sommet.setColor(Color.MAGENTA);
        }

        modelTableVoisin.addVoisin(materniteList);
        repaint();
    }

    /**
     * Algorithme pour colorer les sommets de type "Bloc" en magenta. Met à jour
     * la couleur des sommets, la table des voisins et redessine la fenêtre.
     */
    private void colorerBloc() {
        modelTableVoisin.clearData();
        voisins.clear();
        resetColorAll();
        blocList.clear();
        blocList = listeGraphe.printBloc();

        for (Sommet sommet : blocList) {
            sommet.setColor(Color.MAGENTA);
        }

        modelTableVoisin.addVoisin(blocList);
        repaint();
    }

    /**
     * Réinitialise la couleur de tous les sommets et chemins à la couleur par
     * défaut (gris et noir).
     */
    private void resetColorAll() {
        for (Sommet sommet : listeSommets) {
            sommet.setColor(Color.gray);
        }
        for (Chemin chemin : listeChemins) {
            chemin.setColor(Color.black);
        }
    }

    /**
     * Algorithme de Dijkstra pour trouver le plus court chemin entre deux
     * sommets en fonction d'un critère donné. Met à jour la couleur des sommets
     * et chemins en fonction du chemin calculé.
     *
     * @param critere le critère utilisé pour le calcul du chemin (par exemple,
     *                "fiabilité", "distance" ou "temps").
     */
    private void dijkstra(String critere) {
        resetColorAll();

        if (sommetSelectionneForDijkstra1 != null && sommetSelectionneForDijkstra2 != null) {
            chemin = algoDijkstra(sommetSelectionneForDijkstra1.getOrigine(),
                    sommetSelectionneForDijkstra2.getOrigine(), lLiaison, critere);
            colorerSommet();
            colorerChemin();
            resetSelectionForDijkstra();
        }
        repaint();
    }

    /**
     * Met à jour la couleur des chemins du plus court chemin en rouge. Met à
     * jour la liste des valeurs du chemin (par exemple, fiabilité, distance,
     * temps).
     */
    private void colorerChemin() {
        listeChemins.forEach(chemin -> chemin.setColor(Color.white));
        listeValeurs.clear();
        for (int i = 0; i < chemin.size() - 1; i++) {
            String sommetOrigine = chemin.get(i);
            String sommetDestination = chemin.get(i + 1);

            Sommet sommetOrigineObj = listeGraphe.searchSommet(sommetOrigine);
            Sommet sommetDestinationObj = listeGraphe.searchSommet(sommetDestination);

            Chemin lien = listeGraphe.searchChemin(sommetOrigineObj, sommetDestinationObj);

            if (lien != null) {
                lien.setColor(Color.RED);
                fiabilite = lien.getFiabilite();
                distance = lien.getDistance();
                temps = lien.getTemps();

                listeValeurs.add(fiabilite);
                listeValeurs.add(distance);
                listeValeurs.add(temps);

                Iterator<Chemin> iterator = listeChemins.iterator();
                while (iterator.hasNext()) {
                    Chemin cheminListe = iterator.next();
                    if (cheminListe.equals(lien)) {
                        iterator.remove();

                    }
                }
                listeChemins.add(lien);
            }
        }
        modelTableDijkstra.addValeurs(listeValeurs);

    }

    /**
     * Met à jour la couleur des sommets du plus court chemin en vert. Met en
     * évidence les sommets de départ et d'arrivée en bleu.
     */
    private void colorerSommet() {
        listeSommets.forEach(sommet -> sommet.setColor(Color.white)); // Réinitialiser toutes les couleurs des sommets
        modelTableDijkstra.addChemin(chemin);
        for (String sommetNom : chemin) {
            Sommet sommet = listeGraphe.searchSommet(sommetNom);
            sommet.setColor(Color.GREEN);
        }

        sommetSelectionneForDijkstra1.setColor(Color.BLUE);
        sommetSelectionneForDijkstra2.setColor(Color.BLUE);

    }

    /**
     * Redessine la fenêtre en mettant en évidence les sommets voisins en jaune.
     */
    private void repaintVoisin() {
        resetColorAll();
        if (voisin1DistanceSelected) {
            if (voisinFor1Distance != null) {
                for (String nomSommet : voisinFor1Distance) {
                    Sommet sommet = listeGraphe.searchSommet(nomSommet);
                    sommet.setColor(Color.yellow);
                }
            }
        }
        if (voisin2DistanceSelected) {
            for (String nomSommet : voisinFor2Distance) {
                Sommet sommet = listeGraphe.searchSommet(nomSommet);
                sommet.setColor(Color.yellow);

            }
            for (String nomSommet : voisinFor1Distance) {
                Sommet sommet = listeGraphe.searchSommet(nomSommet);
                sommet.setColor(Color.gray);
            }

        }
        if (voisinNDistanceSelected) {
            for (String nomSommet : voisinForNDistance) {
                Sommet sommet = listeGraphe.searchSommet(nomSommet);
                sommet.setColor(Color.yellow);

            }
            for (String nomSommet : voisinFor1Distance) {
                Sommet sommet = listeGraphe.searchSommet(nomSommet);
                sommet.setColor(Color.gray);
            }

            for (String nomSommet : voisinFor2Distance) {
                Sommet sommet = listeGraphe.searchSommet(nomSommet);
                sommet.setColor(Color.gray);
            }

        }
        repaint();
    }

    /**
     * Efface les données du modèle de tableau et récupère les voisins à une
     * distance de 1 du nœud sélectionné. Met à jour la liste des voisins, le
     * modèle de tableau et déclenche une réimpression de la visualisation.
     */
    private void voisinFor1Distance() {
        modelTableVoisin.clearData();
        voisins.clear();
        voisinFor1Distance = listeGraphe.oneDistNeighbors(getSommetSelected(), 1);
        for (String nomSommet : voisinFor1Distance) {
            Sommet sommet = listeGraphe.searchSommet(nomSommet);
            voisins.add(sommet);
        }

        modelTableVoisin.addVoisin(voisins);
        voisin1DistanceSelected = true;
        voisin2DistanceSelected = false;
        voisinNDistanceSelected = false;
        repaintVoisin();
    }

    /**
     * Efface les données du modèle de tableau et récupère les voisins à une
     * distance de 2 du nœud sélectionné. Met à jour la liste des voisins, le
     * modèle de tableau et déclenche une réimpression de la visualisation.
     */
    private void voisinFor2Distance() {
        voisins.clear();
        modelTableVoisin.clearData();
        voisinFor2Distance = listeGraphe.oneDistNeighbors(getSommetSelected(), 2);
        voisin2DistanceSelected = true;
        voisin1DistanceSelected = false;
        voisinNDistanceSelected = false;
        repaintVoisin();

        for (String nomSommet : voisinFor2Distance) {
            Sommet sommet = listeGraphe.searchSommet(nomSommet);
            if (sommet.getColor().equals(Color.yellow)) {
                voisins.add(sommet);
            }
        }

        Iterator<Sommet> iterator = voisins.iterator();

        while (iterator.hasNext()) {
            Sommet sommet = iterator.next();
            if (sommet.getOrigine().equals(sommetSelectionne1.getOrigine())) {
                iterator.remove();
            }
        }

        modelTableVoisin.addVoisin(voisins);
    }

    /**
     * Récupère les voisins à une distance spécifiée du nœud sélectionné. Met à
     * jour la liste des voisins, le modèle de tableau et déclenche une
     * réimpression de la visualisation.
     *
     * @param n La distance à prendre en compte lors de la recherche des
     *          voisins.
     */
    private void voisinForNDistance(int n) {
        voisins.clear();
        voisinForNDistance = listeGraphe.oneDistNeighbors(getSommetSelected(), n);

        voisinNDistanceSelected = true;
        voisin1DistanceSelected = false;
        voisin2DistanceSelected = false;
        repaintVoisin();

        for (String nomSommet : voisinForNDistance) {
            Sommet sommet = listeGraphe.searchSommet(nomSommet);
            if (sommet.getColor().equals(Color.yellow)) {
                voisins.add(sommet);
            }
        }

        Iterator<Sommet> iterator = voisins.iterator();

        while (iterator.hasNext()) {
            Sommet sommet = iterator.next();
            if (sommet.getOrigine().equals(sommetSelectionne1.getOrigine())) {
                iterator.remove();
            }
        }

        modelTableVoisin.addVoisin(voisins);
    }

    /**
     * Initialise l'écouteur pour l'algorithme de Dijkstra avec les critères
     * donnés.
     *
     * @param critere Le critère à utiliser pour l'algorithme de Dijkstra.
     */
    public void lancerDijkstra(String critere) {
        initializeListenerForDijkstra(critere);

    }

    /**
     * Lance la recherche des voisins à une distance de 1 du nœud sélectionné.
     */
    public void lancerVoisinFor1Distance() {
        voisinFor1Distance();
    }

    /**
     * Lance la recherche des voisins à une distance de 2 du nœud sélectionné.
     */
    public void lancerVoisinFor2Distance() {
        voisinFor2Distance();
    }

    /**
     * Lance la recherche des voisins à une distance donnée du nœud sélectionné.
     *
     * @param n La distance à prendre en compte lors de la recherche des
     *          voisins.
     */
    public void lancerVoisinForNDistance(int n) {
        if (n == 1) {
            voisinFor1Distance();
        } else if (n == 2) {
            voisinFor2Distance();
        } else {
            voisinForNDistance(n);
        }

    }

    /**
     * Redéfinition de la méthode paintComponent de la classe parente. Dessine
     * les sommets et les chemins du graphe, ainsi que les informations sur le
     * sommet sélectionné.
     *
     * @param g L'objet Graphics utilisé pour dessiner les composants
     *          graphiques.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (listeChemins != null) {
            Iterator<Chemin> it = listeChemins.iterator();
            while (it.hasNext()) {
                Chemin chemin = it.next();
                g.setColor(chemin.getColor());
                Sommet sommetOri = listeGraphe.searchSommet(chemin.getOrigine());
                Sommet sommetDest = listeGraphe.searchSommet(chemin.getDestination());
                int x1 = sommetOri.getX() + sommetOri.getWidth() / 2;
                int y1 = sommetOri.getY() + sommetOri.getHeight() / 2;
                int x2 = sommetDest.getX() + sommetDest.getWidth() / 2;
                int y2 = sommetDest.getY() + sommetDest.getHeight() / 2;
                // Gestion des coordonnées hors limites
                if (x1 < 0) {
                    x1 = 0;
                }
                if (y1 < 0) {
                    y1 = 0;
                }
                if (x2 < 0) {
                    x2 = 0;
                }
                if (y2 < 0) {
                    y2 = 0;
                }
                if (x1 > getWidth()) {
                    x1 = getWidth();
                }
                if (y1 > getHeight()) {
                    y1 = getHeight();
                }
                if (x2 > getWidth()) {
                    x2 = getWidth();
                }
                if (y2 > getHeight()) {
                    y2 = getHeight();
                }

                g.drawLine(x1, y1, x2, y2);

            }
        }
        // Dessin des sommets du graphe
        for (Sommet sommet : listeSommets) {
            int x = Math.max(sommet.getX(), 0);
            int y = Math.max(sommet.getY(), 0);
            x = Math.min(x, getWidth() - sommet.getWidth());
            y = Math.min(y, getHeight() - sommet.getHeight());
            g.setColor(sommet.getColor());
            g.fillOval(x, y, sommet.getWidth(), sommet.getHeight());
            if (sommet == sommetSelectionne1) {
                g.setColor(Color.BLUE);
                g.fillOval(x, y, sommet.getWidth(), sommet.getHeight());
            }
            g.setColor(Color.black);
            g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
            FontMetrics fm = g.getFontMetrics();
            int numberWidth = fm.stringWidth(Integer.toString(sommet.getNumber()));
            int numberHeight = fm.getHeight();
            int numberX = x - numberWidth / 2 + 15;
            int numberY = y + numberHeight / 2 + 10;
            g.drawString(Integer.toString(sommet.getNumber()), numberX, numberY);

        }

        // Affichage des informations sur le sommet sélectionné
        if (sommetSelectionne1 != null && sommetSelectionne1.isSelectionne()) {
            infoJLabel.setText("Nom sommet : " + sommetSelectionne1.getOrigine());
            infoJLabel.setFont(infoJLabel.getFont().deriveFont(Font.BOLD));
            infoJLabel.setBounds(sommetSelectionne1.getX() + sommetSelectionne1.getWidth() + 10,
                    sommetSelectionne1.getY(), 150, 20);
            add(infoJLabel);

            typeLabel.setText("Type du sommet : " + sommetSelectionne1.getTypeS());
            typeLabel.setFont(typeLabel.getFont().deriveFont(Font.BOLD));
            typeLabel.setBounds(sommetSelectionne1.getX() + sommetSelectionne1.getWidth() + 10,
                    sommetSelectionne1.getY() + 15, 300, 20);
            add(typeLabel);
        } else {
            resetColorAll();
            infoJLabel.setText("");
            typeLabel.setText("");
        }
        drawGraphe.changeInfoSommet();

    }

    /**
     * Renvoie l'ensemble des voisins à une distance de 1.
     *
     * @return L'ensemble des voisins à une distance de 1.
     */
    public Set<String> getvoisinFor1Distance() {
        return voisinFor1Distance;
    }

    /**
     * Renvoie l'ensemble des voisins à une distance de 2.
     *
     * @return L'ensemble des voisins à une distance de 2.
     */
    public Set<String> getvoisinFor2Distance() {
        return voisinFor2Distance;
    }

    /**
     * Renvoie l'ensemble des voisins à une distance spécifiée.
     *
     * @return L'ensemble des voisins à une distance spécifiée.
     */
    public Set<String> getvoisinForNDistance() {
        return voisinForNDistance;
    }

    /**
     * Initialise les écouteurs pour le déplacement des sommets.
     */
    public void initializeListenerForDeplacement() {
        resetColorAll();
        if (sommetSelectionne1 != null) {
            resetSelection();
            sommetSelectionne1.setSelectionne(false);
            sommetSelectionne1.setColor(Color.gray);

            sommetSelectionne1 = null;
        }

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (deplacementActif) {
                    Sommet clickedSommet = getClickedSommet(e.getX(), e.getY());
                    if (sommetSelectionne1 == clickedSommet) {
                        sommetSelectionne1 = null;
                    }
                    sommetEnDeplacement = clickedSommet;
                    repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (deplacementActif && sommetEnDeplacement != null) {
                    sommetEnDeplacement = null;
                    repaint();
                }
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (deplacementActif && sommetEnDeplacement != null) {
                    int sommetWidth = sommetEnDeplacement.getWidth();
                    int sommetHeight = sommetEnDeplacement.getHeight();
                    int sommetX = e.getX() - sommetWidth / 2;
                    int sommetY = e.getY() - sommetHeight / 2;
                    sommetEnDeplacement.setX(sommetX);
                    sommetEnDeplacement.setY(sommetY);
                    repaint();
                }
            }
        });
    }

    /**
     * Initialise les écouteurs pour la sélection des sommets.
     */
    public void initializeListenerForSelection() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!deplacementActif) {
                    Sommet clickedSommet = getClickedSommet(e.getX(), e.getY());

                    if (clickedSommet != null) {
                        boolean isSelectedSommet = clickedSommet.isSelectionne();

                        if (!isSelectedSommet) {
                            resetSelection();
                            clickedSommet.setSelectionne(true);
                            clickedSommet.setColor(Color.blue);
                            Sommet.setSelectedCount(1);
                            sommetSelectionne1 = clickedSommet;
                        } else {
                            resetSelection();
                            clickedSommet.setSelectionne(false);
                            clickedSommet.setColor(Color.gray);
                            Sommet.setSelectedCount(0);
                            sommetSelectionne1 = null;
                        }
                    } else {
                        resetSelection();
                        sommetSelectionne1 = null;
                    }

                    repaint();
                }
            }
        });
    }

    /**
     * Initialise l'écouteur pour l'algorithme de Dijkstra. Lorsqu'un sommet est
     * cliqué, l'algorithme de Dijkstra est exécuté en utilisant le sommet
     * sélectionné comme point de départ. Les résultats de l'algorithme sont
     * affichés dans un modèle de tableau.
     *
     * @param critere Le critère à utiliser dans l'algorithme de Dijkstra.
     */
    private void initializeListenerForDijkstra(String critere) {
        if (sommetSelectionne1 != null) {
            resetSelection();
            sommetSelectionne1.setSelectionne(false);
            sommetSelectionne1.setColor(Color.gray);

            sommetSelectionne1 = null;
        }
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                modelTableDijkstra.clearData();
                if (!deplacementActif) {
                    Sommet clickedSommet = getClickedSommet(e.getX(), e.getY());

                    if (clickedSommet != null) {
                        boolean isSelectedSommet = clickedSommet.isSelectionne();

                        if (!isSelectedSommet) {
                            if (sommetSelectionneForDijkstra1 == null) {
                                sommetSelectionneForDijkstra1 = clickedSommet;
                                sommetSelectionneForDijkstra1.setSelectionne(true);
                                sommetSelectionneForDijkstra1.setColor(Color.blue);
                                Sommet.setSelectedCount(1);
                            } else if (sommetSelectionneForDijkstra2 == null
                                    && sommetSelectionneForDijkstra1 != clickedSommet) {
                                sommetSelectionneForDijkstra2 = clickedSommet;
                                sommetSelectionneForDijkstra2.setSelectionne(true);
                                sommetSelectionneForDijkstra2.setColor(Color.blue);
                                Sommet.setSelectedCount(2);
                                dijkstra(critere);

                            }
                        } else {
                            resetSelection();
                            Sommet.setSelectedCount(0);
                        }
                    } else {
                        resetSelectionForDijkstra();
                    }

                    repaint();
                }
            }
        });
    }

    /**
     * Réinitialise la sélection des sommets pour l'algorithme de Dijkstra.
     * Remet les sommets sélectionnés à leur état initial.
     */
    private void resetSelectionForDijkstra() {
        if (sommetSelectionneForDijkstra1 != null) {
            sommetSelectionneForDijkstra1.setSelectionne(false);
            sommetSelectionneForDijkstra1.setColor(Color.pink);
            sommetSelectionneForDijkstra1 = null;
        }
        if (sommetSelectionneForDijkstra2 != null) {
            sommetSelectionneForDijkstra2.setSelectionne(false);
            sommetSelectionneForDijkstra2.setColor(Color.pink);
            sommetSelectionneForDijkstra2 = null;
        }
        Sommet.setSelectedCount(0);
    }

    /**
     * Renvoie le sommet sélectionné.
     *
     * @return Le sommet sélectionné.
     */
    public String getSommetSelected() {
        return sommetSelectionne1.getOrigine();
    }

    /**
     * Réinitialise la sélection des sommets. Remet tous les sommets à leur état
     * initial.
     */
    private void resetSelection() {
        for (Sommet sommet : listeSommets) {
            sommet.setSelectionne(false);
            sommet.setColor(Color.gray);
        }
        Sommet.setSelectedCount(0);
    }

    /**
     * Active le mode de déplacement. Permet de déplacer les sommets en cliquant
     * et en les faisant glisser.
     */
    public void activerModeDeplacement() {
        deplacementActif = true;
    }

    /**
     * Active le mode de sélection. Permet de sélectionner des sommets en
     * cliquant dessus.
     */
    public void activerModeSelection() {
        deplacementActif = false;
    }

    /**
     * Renvoie le sommet sur lequel l'utilisateur a cliqué.
     *
     * @param mouseX La coordonnée X du point de clic.
     * @param mouseY La coordonnée Y du point de clic.
     * @return Le sommet cliqué ou null s'il n'y en a pas.
     */
    private Sommet getClickedSommet(int mouseX, int mouseY) {
        for (Sommet sommet : listeSommets) {
            int sommetX = sommet.getX() + sommet.getWidth() / 2;
            int sommetY = sommet.getY() + sommet.getHeight() / 2;
            int radius = sommet.getWidth() / 2;

            int distance = (int) Math.sqrt(Math.pow(mouseX - sommetX, 2) + Math.pow(mouseY - sommetY, 2));
            if (distance <= radius) {
                return sommet;
            }
        }

        return null;
    }

    /**
     * Supprime tous les écouteurs de souris associés au composant.
     */
    public void removeMouseListeners() {
        for (MouseListener listener : getMouseListeners()) {
            if (listener instanceof MouseAdapter) {
                removeMouseListener(listener);
            }
        }

        for (MouseMotionListener listener : getMouseMotionListeners()) {
            if (listener instanceof MouseMotionAdapter) {
                removeMouseMotionListener(listener);
            }
        }
    }

    /**
     * Gère l'événement lorsque l'option "Ouvrir le fichier" du menu est
     * sélectionnée. Ouvre une boîte de dialogue pour permettre à l'utilisateur
     * de sélectionner un fichier à charger. Le fichier est ensuite utilisé pour
     * initialiser le graphique.
     *
     * @param evt L'événement de l'action.
     */
    public void openFileMenuItemActionPerformed(ActionEvent evt) {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt");

        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(this);
        listeChemins = new ArrayList<>();
        if (result == JFileChooser.APPROVE_OPTION) {
            java.io.File file = fileChooser.getSelectedFile();

            try {
                String filePath = file.getAbsolutePath();
                initializeGraph(filePath);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Exécute l'algorithme de Dijkstra pour trouver le chemin le plus court
     * entre un sommet de départ et un sommet d'arrivée.
     *
     * @param depart   Le sommet de départ.
     * @param arrivee  Le sommet d'arrivée.
     * @param lLiaison La liste des liens entre les sommets.
     * @param critere  Le critère à utiliser dans l'algorithme de Dijkstra.
     * @return La liste des sommets formant le chemin le plus court, ou null
     *         s'il n'existe aucun chemin.
     */
    private List<String> algoDijkstra(String depart, String arrivee, List<Lier> lLiaison, String critere) {

        List<String> shortestPath = Dijkstra.dijkstra(depart, arrivee, lLiaison, critere);

        if (shortestPath == null) {
            System.out.println("Aucun chemin trouvé de " + depart + " à " + arrivee);
        } else {
            // Affichage du chemin et des informations
            if (shortestPath.isEmpty()) {
                System.out.println("Aucun chemin trouvé de " + depart + " à " + arrivee);
            } else {

                Map<String, Double> fiabilitesMap = new HashMap<>();
                Map<String, Double> distancesMap = new HashMap<>();
                Map<String, Double> tempsMap = new HashMap<>();

                for (Lier lien : lLiaison) {
                    String sommetCourant = lien.getOrigine();
                    String sommetSuivant = lien.getDestination();
                    int indice = -1;
                    for (int i = 0; i < shortestPath.size() - 1; i++) {
                        if (shortestPath.get(i).equals(sommetCourant)
                                && shortestPath.get(i + 1).equals(sommetSuivant)) {
                            indice = i;

                        }
                    }
                    if (indice != -1) {
                        fiabilitesMap.put(sommetCourant, lien.getFiabilite());
                        distancesMap.put(sommetCourant, lien.getDistance());
                        tempsMap.put(sommetCourant, lien.getTemps());
                    }
                }

                for (String sommet : shortestPath) {
                    Double fiabiliteSommet = fiabilitesMap.get(sommet);
                    Double distanceSommet = distancesMap.get(sommet);
                    Double tempsSommet = tempsMap.get(sommet);
                    /*
                     * if (fiabiliteSommet != null && distanceSommet != null && tempsSommet != null)
                     * {
                     * System.out.println(sommet + "\t\t" + fiabiliteSommet + "\t\t" +
                     * distanceSommet + "\t\t"
                     * + tempsSommet);
                     * }
                     */
                }

                // Calcul des totaux
                double fiabiliteTotale = 1.0;
                double distanceTotale = 0.0;
                double tempsTotal = 0.0;

                for (int i = 0; i < shortestPath.size() - 1; i++) {
                    String sommetCourant = shortestPath.get(i);
                    String sommetSuivant = shortestPath.get(i + 1);
                    Lier lien = getLien(lLiaison, sommetCourant, sommetSuivant);
                    if (lien != null) {
                        fiabiliteTotale *= lien.getFiabilite();
                        distanceTotale += lien.getDistance();
                        tempsTotal += lien.getTemps();
                    }
                }

            }
        }
        return shortestPath;
    }

    /**
     * Réinitialise la couleur des sommets. Remet la couleur de tous les sommets
     * à leur état initial.
     */
    public void resetColor() {
        resetColorAll();
    }

    /**
     * Obtient le lien correspondant à l'origine et à la destination spécifiées.
     *
     * @param liens       La liste des liens.
     * @param origine     L'origine du lien.
     * @param destination La destination du lien.
     * @return Le lien correspondant, ou null s'il n'existe pas.
     */
    private static Lier getLien(List<Lier> liens, String origine, String destination) {
        for (Lier lien : liens) {
            if (lien.getOrigine().equals(origine) && lien.getDestination().equals(destination)) {
                return lien;
            }
        }
        return null;
    }

    /**
     * Obtient le JLabel d'information.
     *
     * @return Le JLabel d'information.
     */
    public JLabel getInfoJLabel() {
        if (infoJLabel == null) {
            infoJLabel = new JLabel();
        }
        return infoJLabel;
    }

    /**
     * Obtient le JLabel de type.
     *
     * @return Le JLabel de type.
     */
    public JLabel getTypeJLabel() {
        if (typeLabel == null) {
            typeLabel = new JLabel();
        }
        return typeLabel;
    }

    /**
     * Vérifie si un sommet est sélectionné.
     *
     * @return true si un sommet est sélectionné, false sinon.
     */
    public boolean isSelectedSommet() {
        return selectedSommet();
    }

    /**
     * Vérifie si un sommet est sélectionné.
     *
     * @return {@code true} si un sommet est sélectionné, {@code false} sinon.
     */
    private boolean selectedSommet() {
        if (sommetSelectionne1 != null && sommetSelectionne1.isSelectionne()) {
            return true;
        }
        return false;
    }

    /**
     * Obtient le critère utilisé dans l'algorithme de Dijkstra.
     *
     * @return Le critère utilisé dans l'algorithme de Dijkstra.
     */
    public String getCritere() {
        return this.critere;
    }

    /**
     * Définit le critère à utiliser dans l'algorithme de Dijkstra.
     *
     * @param critere Le critère à utiliser.
     */
    public void setCritere(String critere) {
        this.critere = critere;
    }

    /**
     * Obtient la liste des chemins.
     *
     * @return La liste des chemins.
     */
    public List<String> getListChemin() {
        return this.chemin;
    }

    /**
     * Obtient la fiabilité du chemin le plus court trouvé par l'algorithme de
     * Dijkstra.
     *
     * @return La fiabilité du chemin le plus court.
     */
    public double getFiabilite() {
        return fiabilite;
    }

    /**
     * Obtient la distance du chemin le plus court trouvé par l'algorithme de
     * Dijkstra.
     *
     * @return La distance du chemin le plus court.
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Obtient le temps du chemin le plus court trouvé par l'algorithme de
     * Dijkstra.
     *
     * @return Le temps du chemin le plus court.
     */
    public double getTemps() {
        return temps;
    }

    /**
     * Obtient le nombre d'arêtes présentes dans le graphique.
     *
     * @return Le nombre d'arêtes.
     */
    public int infoArrete() {
        int i = 0;
        for (Chemin chemin : listeChemins) {
            i++;
        }
        return (i / 2);
    }

    /**
     * Obtient le nombre de sommets présents dans le graphique.
     *
     * @return Le nombre de sommets.
     */
    public int infoSommet() {
        int i = 0;
        for (Sommet sommet : listeSommets) {
            i++;
        }
        return i;
    }
}
