/**
 * Dossier algo
 * Programme principal a completer
 *
 * @author ...                <-----  !!!!!!! renseignez vos noms ici  !!!!!
 **/
public class TraitementVol
{

    private static final String REPERTOIRE = "data/";

    public static java.util.Scanner scanner = new java.util.Scanner(System.in);

    private static Vol vol;

    public static void main(String[] args)
    {
        System.out.println("\nTraitement d'un vol\n*******************");

        while (true) {

            // charger le vol et verifier si il n'est pas null
            if ((vol = chargerVol()) == null) System.err.print("Une erreur est survenue, veuillez reessayer.\n");
            else break;

        }

        int choix;
        do {
            choix = lireChoix();
            switch (choix) {
                case 1:
                    statistique1();
                    break;
                case 2:
                    statistique2();
                    break;
                case 3:
                    statistique3();
                    break;
                case 4:
                    statistique4();
                    break;
                case 5:
                    statistique5();
                    break;
                case 6:
                    statistique6();
                    break;
                case 7:
                    statistique7();
                    break;
                case 8:
                    statistique8();
                    break;
                case 9:
                    statistique9();
                    break;
                case 10:
                    statistique10();
                    break;

            }
        } while (choix != 0);
        System.out.println("Au revoir!\n");
    }

    private static int lireChoix()
    {
        System.out.println("\n******** Menu du vol de " + vol.getPilote() + " ********");
        System.out.println("|--------------------------------------|");
        System.out.println("| Date du vol: " + vol.getDate().toString() + "              |");
        System.out.println("|--------------------------------------|");
        System.out.println("|             Statistiques             |");
        System.out.println("|--------------------------------------|");
        System.out.println("10 > Nombre de coordonnees du parcours");
        System.out.println("9 > Nombre de cibles atteintes lors d'un parcours");
        System.out.println("8 > Cibles atteintes");
        System.out.println("7 > Nombre de croisements");
        System.out.println("6 > Distance maximale");
        System.out.println("5 > Distance parcourue");
        System.out.println("4 > Lieu le plus proche d'une cible");
        System.out.println("3 > Les 4 lieux extremes");
        System.out.println("2 > Lieu le plus eloigne du depart");
        System.out.println("1 > Duree du vol");
        System.out.println("0 > Quitter");
        System.out.print("\nTon choix : ");
        int choix = Utilitaires.lireUnEntierComprisEntre(0, 10);
        return choix;
    }

    /**
     * Lire des coordonnées
     *
     * @return les coordonnées lues
     */

    public static Coordonnees lireCoordonnees()
    {
        System.out.println("Entrer la latitude :");
        long latitude = Utilitaires.scanner.nextLong();
        System.out.println("Entrer la longitude :");
        long longitude = Utilitaires.scanner.nextLong();
        return new Coordonnees(latitude, longitude);
    }

    /**
     * affiche toutes les coordonnées d'un parcours
     *
     * @param parcours le parcours à afficher
     */

    public static void afficherParcours(Coordonnees[] parcours)
    {

        if (parcours.length == 0) {
            System.out.println("Aucune coordonnees");
            return;
        }

        for (int i = parcours.length - 1; i > 0; i--) {
            Coordonnees coordonnees = parcours[i];
            System.out.println("^ (" + i + "): " + coordonnees.getLatitude() + " | " + coordonnees.getLongitude());
        }
    }

    /**
     * Créer un parcours avec des coordonnées données
     *
     * @return Le parcours crée
     */

    public static Coordonnees[] creerParcours()
    {
        Coordonnees[] coordonnees = new Coordonnees[1000];
        int nombre = 0;
        boolean verifier = true;

        do {
            System.out.println("Ajouter des coordonnees :");
            coordonnees[nombre++] = lireCoordonnees();
            System.out.println("\nAjouter des autres coordonnees? (O/N)\n");
            if (Utilitaires.lireOouN() == 'N') verifier = false;
        } while (verifier);

        Coordonnees[] parcours = new Coordonnees[nombre];

        for (int i = 0; i < nombre; i++)
            parcours[i] = coordonnees[i];

        return parcours;
    }

    /**
     * Envoie la durée du vol
     */
    public static void statistique1()
    {
        System.out.println("\nTon vol a dure " + vol.duree() + " unites temps.");
    }

    /**
     * Envoie le lieu le plus éloigné à vol d'oiseau du départ
     */
    public static void statistique2()
    {
        System.out.println("\nLe lieu le plus eloigne du depart: " + vol.lieuPlusEloigne().toString());
    }

    /**
     * Envoie les quatres lieux extrêmes du vol
     */
    public static void statistique3()
    {

        try {
            Coordonnees[] lieuxextremes = vol.quatresLieuxExtremes();
            System.out.println("\nLes 4 lieux extremes:");
            System.out.println("\nNord: " + lieuxextremes[0].toString());
            System.out.println("\nSud: " + lieuxextremes[1].toString());
            System.out.println("\nEst: " + lieuxextremes[2].toString());
            System.out.println("\nOuest: " + lieuxextremes[3].toString());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

    }

    /**
     * Envoie le lieu le plus proche d'une cible donnée
     */
    public static void statistique4()
    {
        System.out.println("\nEntrer les coordonnees de la cible: ");
        Coordonnees coordonnees = lireCoordonnees();
        System.out.println("\nLe lieu le plus de la cible: " + vol.lieuLePlusProche(coordonnees));
    }

    /**
     * Envoie la distance parcourue pendant le vol
     */
    public static void statistique5()
    {
        System.out.println("\nTu as parcouru: " + vol.distanceParcourue() + " unite distance");
    }

    /**
     * Envoie la distance maximale en fonction d'un nombre de contournements donnés
     */
    public static void statistique6()
    {
        try {
            System.out.println("Entrer le nombre de points de contournements:");
            System.out.println("\nLa distance maximale totale de ton vol est de " + vol.distanceMaximaleTotale(Utilitaires.lireUnEntierStrictementPositif()) + " unites temps.");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

    }

    /**
     * Envoie le nombre de croisements du vol
     */
    public static void statistique7()
    {
        try {
            System.out.println("\nNombre de croisements: " + vol.nombreDeCroisements());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Envoie le nombre de cibles atteintes parmi une liste donnée
     */
    public static void statistique8()
    {
        try {
            System.out.println("\nDonnez les cibles à atteindre");
            Coordonnees[] cibles = creerParcours();
            System.out.println("\nCibles atteintes: \n");
            afficherParcours(vol.ciblesAtteintes(cibles));
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Envoie le nombre de cibles atteintes lors d'un parcours impose
     */
    public static void statistique9()
    {
        try {
            System.out.println("\nDonnez les coordonnées formants le parcours imposé");
            Coordonnees[] parcoursImpose = creerParcours();
            System.out.println("\nNombre de cibles atteintes dans parcours imposé: " + vol.nombreDeCiblesAtteintes(parcoursImpose));
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Envoie le nombre de ccordonnées formants le vol
     */
    public static void statistique10()
    {
        System.out.println("\nNombre de coordonnees: " + vol.nombreDeCoordonnees());
    }

    /**
     * Construit un vol sur base des donnees du fichier dont le nom est
     * encode au clavier
     *
     * @return parcours cree sur base des donnees du fichier
     */
    private static Vol chargerVol()
    {
        System.out.print("Introduis le nom du fichier : \n");
        String nomFichier = scanner.next();
        Coordonnees[] tableCoordonnees = new Coordonnees[4];
        Fichier fichier = new Fichier(REPERTOIRE + nomFichier);
        int nombreCoordonnees = 0;
        Date date = null;
        String pilote = null;
        try {
            // ouverture fichier
            fichier.ouvrirEnLecture();

            // lecture Date, Pilote
            date = (Date) fichier.lireObjet();
            pilote = (String) fichier.lireObjet();

            // lecture des Coordonnees
            while (true) { // on quitte cette repetitive lorsque EOF rencontree
                Coordonnees coordonnee = (Coordonnees) fichier.lireObjet();
                if (nombreCoordonnees == tableCoordonnees.length) {
                    Coordonnees[] temp = new Coordonnees[tableCoordonnees.length * 2];
                    for (int i = 0; i < tableCoordonnees.length; i++) {
                        temp[i] = tableCoordonnees[i];
                    }
                    tableCoordonnees = temp;
                }
                tableCoordonnees[nombreCoordonnees] = coordonnee;
                nombreCoordonnees++;
            }
        } catch (java.io.EOFException ex) { // fin du fichier rencontree
            Coordonnees[] tableCoordonnees2 = new Coordonnees[nombreCoordonnees];
            for (int i = 0; i < nombreCoordonnees; i++) {
                tableCoordonnees2[i] = tableCoordonnees[i];
            }
            return new Vol(date, pilote, tableCoordonnees2);
        } catch (Exception e) {
        } finally { // quoi qu'il arrive, il doit essayer de fermer le fichier.
            try {
                fichier.fermer();
            } catch (java.io.IOException ex) { // si erreur lors de la fermeture
                System.out.println(ex.getMessage());
            }
        }
        return null;
    }

} // fin pgm
