package com.example.tds.objets;

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

    /**
     * Constructeur de plat avec gestion d'erreurs
     * Il servira pour stocker des plats dans un fichier texte (améliorer le stockage en sérilisant)
     *
     * @param nom intitulé du plat
     * @param heureCuisson nombre d'heures de cuisson
     * @param minutesCuisson nombre de minutes de cuisson
     * @param degre température de cuisson
     */
    public Plat(String nom, int heureCuisson, int minutesCuisson, int degre){

        if( OutilCuisson.platValide(nom)
            && OutilCuisson.heureCuissonValide(heureCuisson)
            && OutilCuisson.minuteCuissonValide(minutesCuisson)
            && OutilCuisson.temperatureValide(degre)
            ) {

            this.nom = nom;
            this.heuresCuisson = heureCuisson;
            this.minutesCuisson = minutesCuisson;
            this.degre = degre;
        }else{
            this.nom = null;
        }
    }

    public String getNom(){
        return nom;
    }
}
