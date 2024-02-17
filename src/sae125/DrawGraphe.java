/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sae125;

import sae125.modele.ModelTableVoisin;
import sae125.modele.ModelTableDijkstra;
import sae125.graphe.GraphPanel;
import sae125.utilitaires.LookAndFeel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JFrame;
import sae125.interfacegraphique.NewDialog;

/**
 * Cette classe représente la fenêtre principale de l'application de dessin de
 * graphe.
 *
 * @author Clement
 * @author Yanis
 * @version v1.0
 */
public final class DrawGraphe extends javax.swing.JFrame {

    /**
     * Le panneau de dessin du graphe.
     */
    private final GraphPanel graphePanel;

    /**
     * Le modèle de table pour l'affichage des résultats de l'algorithme de
     * Dijkstra.
     */
    private final ModelTableDijkstra modelTableDijkstra;

    /**
     * Le modèle de table pour l'affichage des voisins d'un sommet.
     */
    private final ModelTableVoisin modelTableVoisin;

    /**
     * Constructeur de la classe DrawGraphe.
     *
     * @throws IOException si une erreur de lecture se produit lors de
     *                     l'initialisation.
     */
    public DrawGraphe() throws IOException {
        initComponents();
        jPanel2.setVisible(false);
        jPanel2.setBackground(Color.white);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Graphe");
        modelTableDijkstra = new ModelTableDijkstra();
        modelTableVoisin = new ModelTableVoisin();
        graphePanel = new GraphPanel(this, modelTableDijkstra, modelTableVoisin);
        jPanel4.setVisible(false);
        jLabel5.setVisible(false);
        jPanel1.add(graphePanel, BorderLayout.CENTER);

        graphePanel.repaint();
        tailleFrame();
        changeInfoSommet();

    }

    /**
     * Ajuste la taille de la fenêtre pour la centrer sur l'écran.
     */
    private void tailleFrame() {

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);

        setLocation(x, y);
    }

    /**
     * Définit le modèle de tableau pour l'algorithme de Dijkstra.
     */
    private void ModelForDijkstra() {
        jTable1.setModel(modelTableDijkstra);
    }

    /**
     * Active tous les modes de l'application.
     */
    private void enableAllModes() {
        deplacerJMenuItem.setEnabled(true);
        infoJMenuItem.setEnabled(true);
        cheminJMenuItem.setEnabled(true);
        jMenuItem5.setEnabled(true);
        jMenuItem4.setEnabled(true);
        jMenuItem1.setEnabled(true);
    }

    /**
     * Active le mode voisin.
     */
    private void voisinEnable() {
        jMenu3.setEnabled(true);
        jMenu4.setEnabled(true);
        jMenu5.setEnabled(true);

    }

    /**
     * Désactive le mode voisin.
     */
    private void voisinDesable() {
        jMenu3.setEnabled(false);
        jMenu4.setEnabled(false);
        jMenu5.setEnabled(false);
    }

    /**
     * Met à jour le texte affiché dans le JLabel d'informations.
     */
    private void infoJLabel() {
        String texte = "<html>Veuillez selectionner 1 sommet, et les informations </br> du sommet apparaitrons ici.</br> Si vous souhaitez voir les voisins du sommet selectionner </br>choisissez dans le menu 'edit'</html> ";
        afficherJLabel
                .setText(texte);

    }

    /**
     * Efface les JLabel d'informations.
     */
    private void removeLabel() {
        jLabel3.setText("");
        jLabel4.setText("");

    }

    /**
     * Définit le modèle de tableau pour les voisins d'un sommet.
     */
    private void modelForVoisin() {
        jTable1.setModel(modelTableVoisin);
        jPanel4.setVisible(true);
    }

    /**
     * Ajoute les informations du sommet sélectionné dans les JLabel
     * correspondants.
     */
    private void addInfoSommet() {
        jLabel3.setText(graphePanel.getInfoJLabel().getText());
        jLabel4.setText(graphePanel.getTypeJLabel().getText());

    }

    /**
     * Récupère le critère de sélection.
     *
     * @return Le critère de sélection choisi.
     */
    private String recupererCritere() {
        String critere = (String) jComboBox1.getSelectedItem();
        return critere;
    }

    /**
     * Change la disposition des composants pour le mode voisin avec distance.
     */
    private void changeLayout() {

        jPanel5.remove(jLabel3);
        jPanel5.remove(jLabel4);
        jPanel5.setLayout(new GridLayout(4, 1));
        jPanel4.setVisible(true);

    }

    /**
     * Change le panel pour le mode d'affichage des informations du sommet
     * sélectionné.
     */
    private void changePanel() {
        jPanel5.setLayout(new GridLayout(6, 1));
        jPanel4.setVisible(false);
        jPanel5.remove(jComboBox1);
        jPanel5.add(jLabel3);
        jPanel5.add(jLabel4);

    }

    /**
     * Affiche les informations générales du graphe.
     */
    private void infoGraphe() {
        jLabel6.setText("Arrete : " + graphePanel.infoArrete());
        jLabel7.setText("Sommet : " + graphePanel.infoSommet());
    }

    /**
     * Méthode appelée lorsqu'il y a un changement dans les informations d'un
     * sommet. Met à jour l'affichage des informations du sommet.
     */
    public void changeInfoSommet() {
        addInfoSommet();
    }

    /**
     * Active le mode voisin avec distance.
     */
    public void voisinDistanceEnable() {
        voisinEnable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     *
     * @throws IOException
     */
    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem6 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        afficherJLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        deplacerJMenuItem = new javax.swing.JMenuItem();
        infoJMenuItem = new javax.swing.JMenuItem();
        cheminJMenuItem = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        voisin1JMenuItem = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        voisin2JMenuItem = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        voisinNJMenuItem = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();

        jMenuItem6.setText("jMenuItem6");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setPreferredSize(new java.awt.Dimension(100, 160));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null }
                },
                new String[] {
                        "Title 1", "Title 2", "Title 3", "Title 4"
                }));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 567,
                                        Short.MAX_VALUE)));
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 142,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sae125/logoiut/logoIUTL1rvb72dpi.png"))); // NOI18N

        jLabel5.setText("Info Graphe :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addGap(0, 36, Short.MAX_VALUE))
                                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)));
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 169,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel5)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel6)
                                                .addGap(32, 32, 32)
                                                .addComponent(jLabel7)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        jPanel1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setPreferredSize(new java.awt.Dimension(290, 473));
        jPanel5.setLayout(new java.awt.GridLayout(6, 1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Info Graphe");
        jPanel5.add(jLabel1);

        afficherJLabel.setText("<html><p>Veuillez chosir un mode à utiliser</br> dans le menu \"edit\"</p></html>");
        jPanel5.add(afficherJLabel);
        jPanel5.add(jLabel3);
        jPanel5.add(jLabel4);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Distance", "Fiabilite", "Temps" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 114,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(154, Short.MAX_VALUE)));
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 33,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        jPanel5.add(jPanel2);

        jPanel1.add(jPanel5, java.awt.BorderLayout.LINE_END);

        jMenu1.setText("File");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N,
                java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem2.setText("Ouvrir");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O,
                java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem3.setText("Quitter");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        deplacerJMenuItem.setText("Deplacer sommet");
        deplacerJMenuItem.setEnabled(false);
        deplacerJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deplacerJMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(deplacerJMenuItem);

        infoJMenuItem.setText("Infos sommet");
        infoJMenuItem.setEnabled(false);
        infoJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoJMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(infoJMenuItem);

        cheminJMenuItem.setText("Plus court chemin");
        cheminJMenuItem.setEnabled(false);
        cheminJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cheminJMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(cheminJMenuItem);

        jMenu3.setText("Voisin à 1 distance");
        jMenu3.setEnabled(false);

        voisin1JMenuItem.setText("Voisin sommet à 1 distance");
        voisin1JMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voisin1JMenuItemActionPerformed(evt);
            }
        });
        jMenu3.add(voisin1JMenuItem);

        jMenuItem7.setText("Maternités");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenuItem8.setText("Nutritions");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem8);

        jMenuItem9.setText("Blocs");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem9);

        jMenu2.add(jMenu3);

        jMenu4.setText("Voisin à 2 distances");
        jMenu4.setEnabled(false);

        voisin2JMenuItem.setText("Voisin sommet à 2 distance");
        voisin2JMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voisin2JMenuItemActionPerformed(evt);
            }
        });
        jMenu4.add(voisin2JMenuItem);

        jMenuItem10.setText("Maternites");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem10);

        jMenuItem11.setText("Nutritions");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem11);

        jMenuItem12.setText("Blocs");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem12);

        jMenu2.add(jMenu4);

        jMenu5.setText("Voisin à N distances");
        jMenu5.setEnabled(false);

        voisinNJMenuItem.setText("Voisin sommet à n-distance");
        voisinNJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voisinNJMenuItemActionPerformed(evt);
            }
        });
        jMenu5.add(voisinNJMenuItem);

        jMenuItem13.setText("Maternites");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem13);

        jMenuItem14.setText("Nutritions");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem14);

        jMenuItem15.setText("Blocs");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem15);

        jMenu2.add(jMenu5);

        jMenuItem1.setText("Afficher les maternités");
        jMenuItem1.setEnabled(false);
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem4.setText("Afficher les blocs");
        jMenuItem4.setEnabled(false);
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem5.setText("Afficher les nutritions");
        jMenuItem5.setEnabled(false);
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1078, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 762, Short.MAX_VALUE));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Méthode appelée lors de la sélection de l'élément de menu "jMenuItem14".
     * Elle crée une nouvelle instance de la classe "NewDialog", affiche une
     * boîte de dialogue modale pour obtenir un entier "n", efface les JLabel
     * d'informations, puis appelle la méthode "lancerNutriNDistance(n)" du
     * panneau de graphe pour lancer le calcul de la distance de nutriment pour
     * "n". Enfin, elle rend le jPanel2 visible.
     *
     * @param evt l'événement déclencheur
     */
    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItem14ActionPerformed
        NewDialog newDialog = new NewDialog(this, true);
        int n = newDialog.showDialog();
        removeLabel();
        graphePanel.lancerNutriNDistance(n);

        jPanel2.setVisible(true);
    }// GEN-LAST:event_jMenuItem14ActionPerformed

    /**
     * Méthode appelée lors de la sélection de l'élément de menu "jMenuItem13".
     * Elle crée une nouvelle instance de la classe "NewDialog", affiche une
     * boîte de dialogue modale pour obtenir un entier "n", efface les JLabel
     * d'informations, puis appelle la méthode "lancerMatNDistance(n)" du
     * panneau de graphe pour lancer le calcul de la distance de maternité pour
     * "n". Enfin, elle rend le jPanel2 visible.
     *
     * @param evt l'événement déclencheur
     */
    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItem13ActionPerformed
        NewDialog newDialog = new NewDialog(this, true);
        int n = newDialog.showDialog();
        removeLabel();
        graphePanel.lancerMatNDistance(n);

        jPanel2.setVisible(true);
    }// GEN-LAST:event_jMenuItem13ActionPerformed

    /**
     * Méthode appelée lors de la sélection de l'élément de menu "jMenuItem15".
     * Elle crée une nouvelle instance de la classe "NewDialog", affiche une
     * boîte de dialogue modale pour obtenir un entier "n", efface les JLabel
     * d'informations, puis appelle la méthode "lancerBlocNDistance(n)" du
     * panneau de graphe pour lancer le calcul de la distance de bloc pour "n".
     * Enfin, elle rend le jPanel4 visible.
     *
     * @param evt l'événement déclencheur
     */
    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItem15ActionPerformed
        NewDialog newDialog = new NewDialog(this, true);
        int n = newDialog.showDialog();
        removeLabel();
        graphePanel.lancerBlocNDistance(n);

        jPanel4.setVisible(true);
    }// GEN-LAST:event_jMenuItem15ActionPerformed

    /**
     * Méthode appelée lors de la sélection de l'élément de menu "jMenuItem7".
     * Elle appelle la méthode "lancerMat1Distance()" du panneau de graphe pour
     * lancer le calcul de la distance de maternité pour 1. Enfin, elle rend le
     * jPanel4 visible.
     *
     * @param evt l'événement déclencheur
     */
    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItem7ActionPerformed
        graphePanel.lancerMat1Distance();
        jPanel4.setVisible(true);

    }// GEN-LAST:event_jMenuItem7ActionPerformed

    /**
     * Méthode appelée lors de la sélection de l'élément de menu "jMenuItem9".
     * Elle lance la méthode "lancerBloc1Distance()" du panneau de graphe pour
     * calculer la distance de bloc 1, puis rend le jPanel4 visible.
     *
     * @param evt l'événement déclencheur
     */
    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItem9ActionPerformed
        graphePanel.lancerBloc1Distance();
        jPanel4.setVisible(true);

    }// GEN-LAST:event_jMenuItem9ActionPerformed

    /**
     * Méthode appelée lors de la sélection de l'élément de menu "jMenuItem11".
     * Elle lance la méthode "lancerNutri2Distance()" du panneau de graphe pour
     * calculer la distance de nutri 2, puis rend le jPanel4 visible.
     *
     * @param evt l'événement déclencheur
     */
    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItem11ActionPerformed
        graphePanel.lancerNutri2Distance();
        jPanel4.setVisible(true);

    }// GEN-LAST:event_jMenuItem11ActionPerformed

    /**
     * Méthode appelée lors de la sélection de l'élément de menu "jMenuItem10".
     * Elle lance la méthode "lancerMat2Distance()" du panneau de graphe pour
     * calculer la distance de mat 2, puis rend le jPanel4 visible.
     *
     * @param evt l'événement déclencheur
     */
    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItem10ActionPerformed
        graphePanel.lancerMat2Distance();
        jPanel4.setVisible(true);

    }// GEN-LAST:event_jMenuItem10ActionPerformed

    /**
     * Méthode appelée lors de la sélection de l'élément de menu "jMenuItem12".
     * Elle lance la méthode "lancerBloc2Distance()" du panneau de graphe pour
     * calculer la distance de bloc 2, puis rend le jPanel4 visible.
     *
     * @param evt l'événement déclencheur
     */
    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItem12ActionPerformed
        graphePanel.lancerBloc2Distance();
        jPanel4.setVisible(true);

    }// GEN-LAST:event_jMenuItem12ActionPerformed

    /**
     * Méthode appelée lors de la sélection de l'élément de menu "jMenuItem8".
     * Elle lance la méthode "lancerNutri1Distance()" du panneau de graphe pour
     * calculer la distance de nutri 1, puis rend le jPanel4 visible.
     *
     * @param evt l'événement déclencheur
     */
    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItem8ActionPerformed
        graphePanel.lancerNutri1Distance();
        jPanel4.setVisible(true);

    }// GEN-LAST:event_jMenuItem8ActionPerformed

    /**
     * Méthode appelée lors de la sélection de l'élément de menu "jMenuItem1".
     * Elle affiche la maternité dans le panneau de graphe, met à jour le modèle
     * de table des voisins et rend le jPanel4 visible.
     *
     * @param evt l'événement déclencheur
     */
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItem1ActionPerformed
        graphePanel.afficherMaternite();
        modelForVoisin();
        jPanel4.setVisible(true);
    }// GEN-LAST:event_jMenuItem1ActionPerformed

    /**
     * Méthode appelée lors de la sélection de l'élément de menu "jMenuItem4".
     * Elle affiche le bloc dans le panneau de graphe, met à jour le modèle de
     * table des voisins et rend le jPanel4 visible.
     *
     * @param evt l'événement déclencheur
     */
    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItem4ActionPerformed
        graphePanel.afficherBloc();
        modelForVoisin();
        jPanel4.setVisible(true);
    }// GEN-LAST:event_jMenuItem4ActionPerformed

    /**
     * Méthode appelée lors de la sélection de l'élément de menu "jMenuItem5".
     * Elle affiche la nutri dans le panneau de graphe, met à jour le modèle de
     * table des voisins et rend le jPanel4 visible.
     *
     * @param evt l'événement déclencheur
     */
    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItem5ActionPerformed
        graphePanel.afficherNutri();
        modelForVoisin();
        jPanel4.setVisible(true);
    }// GEN-LAST:event_jMenuItem5ActionPerformed

    /**
     * Méthode appelée lors de la modification de l'élément de menu
     * "jComboBox1". Elle change la disposition, désactive les voisins, met à
     * jour le texte du jLabel, lance Dijkstra avec le critère récupéré, rend le
     * jPanel2 visible et met à jour le modèle de table pour Dijkstra.
     *
     * @param evt l'événement de modification
     */
    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {// GEN-FIRST:event_jComboBox1ItemStateChanged
        changeLayout();
        voisinDesable();
        afficherJLabel.setText("Veuillez selectionner 2 sommets ");
        graphePanel.removeMouseListeners();
        graphePanel.lancerDijkstra(recupererCritere());
        jPanel2.setVisible(true);
        ModelForDijkstra();
    }// GEN-LAST:event_jComboBox1ItemStateChanged

    /**
     * Méthode appelée lors de la sélection de l'élément de menu
     * "cheminJMenuItem". Elle change la disposition, désactive les voisins, met
     * à jour le texte du jLabel, lance Dijkstra avec le critère récupéré, rend
     * le jPanel2 visible et met à jour le modèle de table pour Dijkstra.
     *
     * @param evt l'événement déclencheur
     */
    private void cheminJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItem4ActionPerformed
        changeLayout();
        voisinDesable();
        afficherJLabel.setText("Veuillez selectionner 2 sommets ");
        graphePanel.removeMouseListeners();
        graphePanel.lancerDijkstra(recupererCritere());
        jPanel2.setVisible(true);
        ModelForDijkstra();
        graphePanel.activerModeSelection();

    }// GEN-LAST:event_jMenuItem4ActionPerformed

    /**
     * Méthode appelée lors de la sélection de l'élément de menu
     * "voisinNJMenuItem". Elle affiche la boîte de dialogue "NewDialog" pour
     * obtenir une valeur "n", supprime les labels, lance les voisins pour une
     * distance "n" dans le panneau de graphe et rend le jPanel2 invisible.
     *
     * @param evt l'événement déclencheur
     */
    private void voisinNJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItem8ActionPerformed
        NewDialog newDialog = new NewDialog(this, true);
        int n = newDialog.showDialog();
        removeLabel();
        graphePanel.lancerVoisinForNDistance(n);

        jPanel2.setVisible(false);
    }// GEN-LAST:event_jMenuItem8ActionPerformed

    /**
     * Méthode appelée lors de la sélection de l'élément de menu
     * "voisin2JMenuItem". Elle supprime les labels, lance les voisins pour une
     * distance de 2 dans le panneau de graphe et rend le jPanel2 invisible.
     *
     * @param evt l'événement déclencheur
     */
    private void voisin2JMenuItemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItem7ActionPerformed
        removeLabel();
        graphePanel.lancerVoisinFor2Distance();

        jPanel2.setVisible(false);
    }// GEN-LAST:event_jMenuItem7ActionPerformed

    /**
     * Méthode appelée lors de la sélection de l'élément de menu
     * "voisin1JMenuItem". Elle supprime les labels, lance les voisins pour une
     * distance de 1 dans le panneau de graphe et rend le jPanel2 invisible.
     *
     * @param evt l'événement déclencheur
     */
    private void voisin1JMenuItemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItem1ActionPerformed
        removeLabel();
        graphePanel.lancerVoisinFor1Distance();

        jPanel2.setVisible(false);
    }// GEN-LAST:event_jMenuItem1ActionPerformed

    /**
     * Méthode appelée lors de la sélection de l'élément de menu
     * "deplacerJMenuItem". Elle change le panneau, désactive les voisins, met à
     * jour le texte du jLabel, rend le jPanel2 invisible, supprime les
     * écouteurs de souris du panneau de graphe, active le mode de déplacement
     * et initialise l'écouteur pour le déplacement.
     *
     * @param evt l'événement déclencheur
     */
    private void deplacerJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItem5ActionPerformed
        changePanel();
        voisinDesable();
        afficherJLabel.setText("Vous pouvez maintenant deplacer les sommets");
        jPanel2.setVisible(false);
        graphePanel.removeMouseListeners();
        graphePanel.activerModeDeplacement();
        graphePanel.initializeListenerForDeplacement();

    }// GEN-LAST:event_jMenuItem5ActionPerformed

    /**
     * Méthode appelée lors de la sélection de l'élément de menu
     * "infoJMenuItem". Elle change le panneau, réinitialise les couleurs,
     * active les voisins, met à jour les informations du jLabel, rend le
     * jPanel2 invisible, rend le jPanel4 visible, supprime les écouteurs de
     * souris du panneau de graphe, active le mode de sélection et initialise
     * l'écouteur de sélection, réinitialise le modèle de table des voisins et
     * met à jour le modèle.
     *
     * @param evt l'événement déclencheur
     */
    private void infoJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItem6ActionPerformed
        changePanel();
        graphePanel.resetColor();
        voisinEnable();
        infoJLabel();
        jPanel2.setVisible(false);
        jPanel4.setVisible(true);
        graphePanel.removeMouseListeners();
        graphePanel.activerModeSelection();
        graphePanel.initializeListenerForSelection();
        modelTableVoisin.clearData();
        modelForVoisin();

    }// GEN-LAST:event_jMenuItem6ActionPerformed

    /**
     * Méthode appelée lors de la sélection de l'élément de menu "jMenuItem3".
     * Elle quitte l'application en fermant la fenêtre principale.
     *
     * @param evt l'événement déclencheur
     */
    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItem3ActionPerformed
        System.exit(0);
    }// GEN-LAST:event_jMenuItem3ActionPerformed

    /**
     * Méthode appelée lors de la sélection de l'élément de menu "jMenuItem2".
     * Elle appelle la méthode "openFileMenuItemActionPerformed()" du panneau de
     * graphe pour ouvrir un fichier, met à jour le panneau de graphe, active
     * tous les modes, rend le jLabel5 visible et affiche les informations du
     * graphe.
     *
     * @param evt l'événement déclencheur
     */
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItem2ActionPerformed
        graphePanel.openFileMenuItemActionPerformed(evt);
        graphePanel.repaint();
        enableAllModes();
        jLabel5.setVisible(true);
        infoGraphe();

    }// GEN-LAST:event_jMenuItem2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
                    .getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DrawGraphe.class.getName()).log(
                    java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DrawGraphe.class.getName()).log(
                    java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DrawGraphe.class.getName()).log(
                    java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DrawGraphe.class.getName()).log(
                    java.util.logging.Level.SEVERE,
                    null, ex);
        }

        LookAndFeel.init(LookAndFeel.WINDOWS);
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new DrawGraphe().setVisible(true);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel afficherJLabel;
    private javax.swing.JMenuItem cheminJMenuItem;
    private javax.swing.JMenuItem deplacerJMenuItem;
    private javax.swing.JMenuItem infoJMenuItem;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JMenuItem voisin1JMenuItem;
    private javax.swing.JMenuItem voisin2JMenuItem;
    private javax.swing.JMenuItem voisinNJMenuItem;
    // End of variables declaration//GEN-END:variables
}
