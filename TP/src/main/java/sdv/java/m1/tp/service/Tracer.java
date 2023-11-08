package sdv.java.m1.tp.service;

import sdv.java.m1.tp.bean.CritereFiltrage;
import sdv.java.m1.tp.enums.CriticiteAction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Tracer {
    public static void tracer(CriticiteAction criticite, String description) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.now();
            BufferedWriter writer = new BufferedWriter(new FileWriter("traces.txt", true));
            String traceLine = criticite.name() + " " + formatter.format(dateTime) + " " + description;
            writer.write(traceLine);
            writer.newLine();

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> lire() {
        List<String> lignes = new ArrayList<>();

        try {
            lignes = Files.readAllLines(Paths.get("traces.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lignes;
    }

    public static void main(String[] args) {
        CritereFiltrage filtre = new CritereFiltrage();
        filtre.getCriticites().add("INFO");
        filtre.getCriticites().add("DANGER");
        filtre.getCriticites().add("TRACE");
        filtre.setSousChaine("erreur");

        Path fichier = Path.of("traces.txt");

        try {
            List<String> lignesFiltrees = Filtrage.filtrer(filtre, fichier);

            // Vérifier que la liste des lignes filtrées est correcte
//            assertEquals(3, lignesFiltrees.size());

            for (String ligne : lignesFiltrees) {
                System.out.println(ligne);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Exemple d'utilisation pour lire les lignes du fichier
//        List<String> lignes = lire();
//        for (String ligne : lignes) {
//            System.out.println(ligne);
//        }
    }

//    public static void main(String[] args) {
//        // Exemple d'utilisation
//        tracer(CriticiteAction.TRACE, "Fermeture inatendue de l'application");
//        tracer(CriticiteAction.INFO, "Erreur de lecture du fichier");
//        tracer(CriticiteAction.TRACE, "Début de l'application");
//        tracer(CriticiteAction.DANGER, "Warning data");
//        tracer(CriticiteAction.ERREUR, "APP doooown");
//    }
}
