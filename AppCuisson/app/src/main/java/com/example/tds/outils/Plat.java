package com.example.tds.outils;

import android.text.Editable;

import com.example.tds.outils.OutilCuisson;

public class Plat {

    /** Attribut correspondant à l'intitulé du plat*/
    private String nom;

    /** Attribut correspondant au nombre d'heures (restante?) de cuisson */
    private int heuresCuisson;

    /** Attribut correspondnat au nombre de minutes (restante?) de cuisson */
    private int minutesCuisson;

    /** Attribut correspondnat à la température de cuisson */
    private int degre;

    private static final int LG_MAX_PLAT = 18;

    /**
     * Constructeur de plat avec gestion d'erreurs
     * Il servira pour stocker des plats dans un fichier texte (améliorer le stockage en sérilisant)
     *  @param nom intitulé du plat
     * @param heureCuisson nombre d'heures de cuisson
     * @param minutesCuisson nombre de minutes de cuisson
     * @param degre température de cuisson
     */
    public Plat(String nom, int heureCuisson, int minutesCuisson, int degre) {

        if(
            OutilCuisson.platValide(nom)
            && !(heureCuisson == 0 && minutesCuisson == 0)
            && !(heureCuisson == 9 && minutesCuisson != 0)
            && degre != 0
            && OutilCuisson.heureCuissonValide(heureCuisson)
            && OutilCuisson.minuteCuissonValide(minutesCuisson)
            && OutilCuisson.temperatureValide(degre)
            ) {

            this.nom = nom;
            this.heuresCuisson = heureCuisson;
            this.minutesCuisson = minutesCuisson;
            this.degre = degre;
        } else {
            this.nom = null;
        }
    }

    public String getNom(){
        return nom;
    }

    public String toString(){
        StringBuilder aRenvoyer = new StringBuilder();

        // on insère le nom du plat
        aRenvoyer.append(nom);
        aRenvoyer.append(chaineEspace(LG_MAX_PLAT - nom.length()));
        aRenvoyer.append(" | ");

        // on insère la durée
        aRenvoyer.append(String.valueOf(heuresCuisson));
        aRenvoyer.append(" h ");
        if (minutesCuisson < 10) {
            aRenvoyer.append("0");
        }
        aRenvoyer.append(String.valueOf(minutesCuisson));
        aRenvoyer.append(" | ");

        // on insère la température
        aRenvoyer.append(String.format("%3d", degre));

        return aRenvoyer.toString();
    }

    /**
     * Renvoie une chaîne constituée d'espaces. Le nombre d'espaces est indiqué
     * par le paramètre.
     * @param nbEspace      nombre d'espaces à placer dans la chaîne
     * @return  une chaîne constituée du caractère espace
     */
    private static String chaineEspace(int nbEspace) {
        StringBuilder aRenvoyer = new StringBuilder();
        for (int i = 1; i <= nbEspace; i++) {
            aRenvoyer.append(" ");
        }
        return aRenvoyer.toString();
    }
}
