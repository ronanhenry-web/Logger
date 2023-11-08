package sdv.java.m1.tp.service;

import sdv.java.m1.tp.bean.CritereFiltrage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Filtrage {
    public static List<String> filtrer(CritereFiltrage filtre, Path fichier) throws IOException {
        List<String> lignes = Files.lines(fichier).collect(Collectors.toList());
        List<String> resultat = new ArrayList<>();

        for (String ligne : lignes) {
            boolean criticiteAcceptee = true;
            for (String criticite : filtre.getCriticites()) {
                if (!ligne.startsWith(criticite)) {
                    criticiteAcceptee = false;
                    break;
                }
            }

            if (criticiteAcceptee) {
                boolean sousChainePresente = ligne.toLowerCase().contains(filtre.getSousChaine().toLowerCase());
                if (sousChainePresente) {
                    resultat.add(ligne);
                }
            }
        }

        return resultat;
    }
}
