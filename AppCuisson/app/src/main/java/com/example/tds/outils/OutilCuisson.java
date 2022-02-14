/*   **************************************************************************** *
 *   IUT de Rodez - Département Informatique                                      *
 *   Semestre 4 - Applications mobiles                                            *
 *                                                                                *
 *                              SUJET CONTROLE MARS 2019                          *
 *                                       ANNEXES                                  *
 *                                                                                *
 *   **************************************************************************** */


/*
 *  Classe avec des méthodes outils pour gérer une chaîne de caractères contenant
 *  les caractéristiques d'une cuisson au four
 *  OutilCuisson.java                                                       12/21
 */

/*  A priori de fichier ne doit pas être modifié. Vous restez libre de le modifier si
    besoin
 */
package com.example.tds.outils;


/**
 * Cette classe est une classe utilitaire qui contient des méthodes outils pour gérer
 * une chaîne de caractères contenant le descriptif d'une cuisson au four.
 * Les caractéristiques d'une cuisson sont :
 *    - le nom du plat (une chaîne non vide)
 *    - les heures de la durée de la cuisson (un entier entre 0 et 9)
 *    - les minutes de la durée de la cuisson (un entier entre O et 50)
 *    - une température (un entier strictement positif et inférieur à TEMPERATURE_MAX)
 *
 *
 * @author INFO2 Semestre 4
 *
 */
public class OutilCuisson {

    /** Valeur de la chaîne qui représente une cuisson si l'une des caractéristiques
     *  de la cuisson est invalide 
     */
    private static final String CHAINE_DEFAUT = "Information incohérente";

    /**
     * Température maximale pour la cuisson
     */
    public static final int TEMPERATURE_MAX = 300;


    /** Valeur maximale pour le nombre d'heures d'une cuisson */
    public static final int HEURE_MAX = 9;

    /**
     * Nombre maximum de caractères pour le nom du plat
     */
    private static final int LG_MAX_PLAT = 18;


    /**
     * Détermine si un nom de plat est valide (non vide, au plus 20 caractères et ne
     * contient pas le caractère '|')
     * @param nomPlat     chaîne à testesr
     * @return  un booléen égal à vrai ssi la chaîne à tester est valide
     */
    public static boolean platValide(String nomPlat) {
        return nomPlat.length() > 0 && nomPlat.length() <= LG_MAX_PLAT
                && ! nomPlat.contains("|");
    }


    /**
     * Détermine si le nombre d'heures d'une durée de cuisson est valide
     *  (comprise entre 0 et 9)
     * @param heureCuisson     heure à tester
     * @return  un booléen égal à vrai ssi l'heure de la durée est valide
     */
    public static boolean heureCuissonValide(int heureCuisson) {
        return heureCuisson >= 0 && heureCuisson <= HEURE_MAX;
    }


    /**
     * Détermine si le nombre de minutes d'une durée de cuisson est valide
     *  (comprise entre 0 et 59)
     * @param minuteCuisson     minute à tester
     * @return  un booléen égal à vrai ssi le nombre de minutes de la durée est valide
     */
    public static boolean minuteCuissonValide(int minuteCuisson) {
        return minuteCuisson >= 0 && minuteCuisson <= 59;
    }


    /**
     * Détermine si une température de cuisson est valide
     *  (strictement positive et inférieure ou égale à TEMPERATURE_MAX)
     * @param temperature     temperature à tester
     * @return  un booléen égal à vrai ssi la température est valide
     */
    public static boolean temperatureValide(int temperature) {
        return temperature > 0 && temperature <= TEMPERATURE_MAX;
    }


    /**
     * Concatène les arguments dans une chaîne de caractères. Celle-ci aura le format
     * suivant : 
     *    nom du plat   |  temps de cuisson en heures et minutes | température
     * Si l'un des arguments est invalide, c'est la constante CHAINE_DEFAUT qui
     * est renvoyée
     * @param nomPlat           nom du plat. Doit être non vide, avec au plus 
     *                          LG_MAX_PLAT caractères, ne doit pas contenir |
     * @param heureCuisson      nombre d'heures de la durée de la cuisson
     *                          doit être compris entre 0 et 9
     * @param minuteCuisson     nombre de minutes de la durée de la cuisson
     *                          doit être compris entre 0 et 59
     * @param temperature       température de la cuisson. Doit être comprise entre
     *                          1 et TEMPERATURE_MAX
     * @return une chaîne contenant le descriptif de la cuisson si les paramètres
     *         sont valides. Sinon la chaîne  CHAINE_DEFAUT
     */
    public static String transformeEnChaine(String nomPlat, int heureCuisson,
                                            int minuteCuisson, int temperature) {
        StringBuilder aRenvoyer = new StringBuilder();

        if (platValide(nomPlat) && heureCuissonValide(heureCuisson)
                && minuteCuissonValide(minuteCuisson) && temperatureValide(temperature)) {

            // on insère le nom du plat
            aRenvoyer.append(nomPlat);
            aRenvoyer.append(chaineEspace(LG_MAX_PLAT - nomPlat.length()));
            aRenvoyer.append(" | ");

            // on insère la durée
            aRenvoyer.append(String.valueOf(heureCuisson));
            aRenvoyer.append(" h ");
            if (minuteCuisson < 10) {
                aRenvoyer.append("0");
            }
            aRenvoyer.append(String.valueOf(minuteCuisson));
            aRenvoyer.append(" | ");

            // on insère la température
            aRenvoyer.append(String.format("%3d", temperature));
        } else {

            // l'un des arguments est invalide
            aRenvoyer.append(CHAINE_DEFAUT);
        }
        return aRenvoyer.toString();
    }


    /**
     * Extrait d'une chaîne le nom du plat. On suppose que la chaîne est correctement
     * formatée dans le format de la description d'une cuisson gérée par cette classe
     * (les 20 premiers caractères de la chaîne sont extraits)
     * @param source   chaîne source de l'extraction
     * @return  une chaîne contenant le nom du plat ou CHAINE_DEFAUT si la chaîne 
     *          argument est trop courte
     */
    public static String extrairePlat(String source) {
        if (source.length() < LG_MAX_PLAT) {
            return CHAINE_DEFAUT;
        } else {
            return source.substring(0, LG_MAX_PLAT);
        }
    }


    /**
     * Extrait d'une chaîne la température de cuisson. On suppose que la chaîne 
     * est correctement formatée dans le format de la description d'une cuisson 
     * gérée par cette classe  (la température est présente sur les 3 derniers
     * caractères de la chaîne)
     * @param source   chaîne source de l'extraction
     * @return  un entier égal à la température extraite ou bien -1 si un
     *          problème inattendu a été rencontré avec le format de la chaîne
     */
    public static int extraireTemperature(String source) {
        int temperature;                // température extraite
        String chaineTemperature;       // température extraite en tant que chaîne


        try {
            chaineTemperature = source.substring(source.length() - 3, source.length());
            temperature = Integer.parseInt(chaineTemperature);
        } catch(NumberFormatException | IndexOutOfBoundsException erreur) {

            /*
             *  erreur lors de l'extraction des 3 derniers caractère
             *  ou lors de la transformation en entier
             */
            temperature = -1;
        }
        return temperature;
    }


    /**
     * Renvoie le thermostat correspondant à la temperature arguemnt
     * (celle-ci doit être inférieure à TEMPERATURE_MAX)
     * @param temperature    température à convertir
     * @return  l'entier égal au thermostat ou -1 si la température est invalide
     */
    public static int thermostat(int temperature) {
        int aRenvoyer;          // valeur du thermostat à renvoyer
        if (temperature <= 0 || temperature > TEMPERATURE_MAX) {
            aRenvoyer = -1;
        } else {
            aRenvoyer = temperature / 30;
            if (temperature % 30 > 15) {
                aRenvoyer++;
            }
        }
        return aRenvoyer;
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