package sdv.java.m1.tp.ihm;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import sdv.java.m1.tp.service.Filtrage;
import sdv.java.m1.tp.service.Tracer;
import sdv.java.m1.tp.bean.CritereFiltrage;


public class Fenetre extends JFrame {

    private JTextArea zoneTexte;
    private JTextField champChaine;

    public Fenetre() {
        this.setTitle("Logger");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.initContent();
    }

    private void initContent() {

        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        JLabel labelTitre = new JLabel("Rechercher dans les traces :");
        container.add(labelTitre, BorderLayout.NORTH);

        zoneTexte = new JTextArea();
        zoneTexte.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(zoneTexte);
        this.getContentPane().add(scrollPane, BorderLayout.CENTER);

        champChaine = new JTextField();
        this.getContentPane().add(champChaine, BorderLayout.NORTH);

        JButton filtrer = new JButton("Filtrer");
        filtrer.addActionListener(e -> {
            String sousChaine = champChaine.getText();

            CritereFiltrage critere = new CritereFiltrage();
            critere.setSousChaine(sousChaine);

            List<String> lignesFiltrees = null;
            try {
                lignesFiltrees = Filtrage.filtrer(critere, Path.of("traces.txt"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            zoneTexte.setText("");
            for (String ligne : lignesFiltrees) {
                zoneTexte.append(ligne + "\n");
            }
        });

        this.getContentPane().add(filtrer, BorderLayout.EAST);

        JButton actualiser = new JButton("Actualiser");
        actualiser.addActionListener(e -> {
            List<String> lignesActualiser = Tracer.lire();
            zoneTexte.setText("");
            for (String ligne : lignesActualiser) {
                zoneTexte.append(ligne + "\n");
            }
        });
        this.getContentPane().add(actualiser, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        var fenetre = new Fenetre();
        fenetre.setVisible(true);
    }
}
