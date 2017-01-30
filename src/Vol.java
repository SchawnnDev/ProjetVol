/**
 * Projet algo
 * <p>
 * Classe permettant de memoriser et traiter les coordonnees d'un parcours
 *
 * @author ... <----- !!!!!!! renseignez vos noms ici !!!!!
 **/
public class Vol
{

    private final Coordonnees[] tableCoordonnees; // les coordonnees du vol

    private Date date; // date du parcours

    private String pilote; // nom et prenom du pilote

    /**
     * Creer une instance de Vol
     *
     * @param date             La date du vol
     * @param pilote           Le pilote ayant effectué le vol
     * @param tableCoordonnees Les coordonnées enregistrées durant le vol
     */
    public Vol(Date date, String pilote, Coordonnees[] tableCoordonnees)
    {
        this.date = date;
        this.pilote = pilote;
        this.tableCoordonnees = tableCoordonnees;
    }

    /**
     * Cette methode renvoie la duree du vol
     * Une unite de temps correspond au temps ecoule entre 2 mesures de position du gps
     *
     * @return la duree
     */
    public int duree()
    {
        return this.tableCoordonnees.length - 1;
    }

    /**
     * calcule le lieu le plus eloigné du début du vol
     * <p>
     * nous n'avons pas besoin de verifier les paramètres étant donné qu'il y a
     * toujours au moins deux coordonnées dans le tableau
     *
     * @return Les coordonnées du lieu le plus éloigné
     */

    public Coordonnees lieuPlusEloigne()
    {
        // sachant que les premieres coordonnées sont au début du tableau
        final Coordonnees debut = tableCoordonnees[0];

        // lieu le plus eloigné
        Coordonnees max = debut;

        // on cherche le lieu le plus éloigné en comparant toutes les distances
        // on commence la boucle for à 1 car on utilise déjà les premières coordonnées (debut)
        for (int i = 1; i < tableCoordonnees.length; i++) {
            double distance = tableCoordonnees[i].distance(debut);
            if (distance != Double.NaN) {
                // si la distance est superieur au max
                if (distance > max.distance(debut)) max = tableCoordonnees[i];
                    // en cas d’ex-aequos on retourne le premier lieu
                else if (distance == max.distance(debut)) return max;
            }
        }

        return max;

    }


    /**
     * calcule les 4 lieux extremes
     * <p>
     * Le tableau contient :
     * index 0 : extreme Nord
     * index 1 : extreme Sud
     * index 2 : extreme Est
     * index 3 : extreme Ouest
     *
     * @return Les 4 coordonnées des 4 lieux extremes
     * @throws IllegalArgumentException si il n'y a pas au moins 4 valeurs enregistrées
     */
    public Coordonnees[] quatresLieuxExtremes()
    {
        // verifier
        if (tableCoordonnees.length < 4) throw new IllegalArgumentException("Il n'y a pas assez de points enregistrés");

        Coordonnees[] coords = new Coordonnees[4];

        // calculer les extremes
        for (Coordonnees max : tableCoordonnees) {

            // verifier les extremes en latitude: nord & sud
            if (coords[0] == null || max.getLatitude() > coords[0].getLatitude()) coords[0] = max;
            if (coords[1] == null || max.getLatitude() < coords[1].getLatitude()) coords[1] = max;

            // verifier les extremes en latitude: est & ouest
            if (coords[2] == null || max.getLongitude() > coords[2].getLongitude()) coords[2] = max;
            if (coords[3] == null || max.getLongitude() < coords[3].getLongitude()) coords[3] = max;

        }

        return coords;
    }

    /**
     * calcule le lieu le plus proche d'une cible
     * <p>
     * nous n'avons pas besoin de verifier les paramètres étant donné qu'il y a
     * toujours au moins deux coordonnées dans le tableau
     *
     * @param cible la cible
     * @return Le lieu le plus proche de la cible
     */

    public Coordonnees lieuLePlusProche(Coordonnees cible)
    {
        // lieu le plus proche du point
        Coordonnees min = tableCoordonnees[0];

        // on cherche le lieu le plus proche de la cible en comparant toutes les distances
        for (int i = 0; i < tableCoordonnees.length; i++) {
            Coordonnees point = tableCoordonnees[i];
            // inutile de calculer la distance de ce point étant donné qu'elle fera toujours 0
            if (!point.equals(cible)) {
                // on calcule la distance entre le point et la cible
                double distance = point.distance(cible);
                // verifier qu'il n'y ait pas un nombre NaN
                if (distance != Double.NaN) {
                    // si la distance est inferieur au min
                    if (distance < min.distance(cible)) min = point;
                        // en cas d’ex-aequos on retourne le premier lieu
                    else if (distance == min.distance(cible)) return min;

                }
            }

        }

        return min;

    }

    /**
     * Calculer la distance entre tout les points d'une liste de coordonnees
     *
     * @param coordonnees La liste
     * @return La distance
     */

    private double calculerDistance(Coordonnees[] coordonnees)
    {
        double distance = 0.0;

        // on calcule toutes les distances
        for (int i = 1; i < coordonnees.length; i++)
            distance += coordonnees[i - 1].distance(coordonnees[i]);

        return distance;

    }


    /**
     * calcule la distance totale parcourue durant le vol
     *
     * @return la distance totale
     */

    public double distanceParcourue()
    {
        return calculerDistance(tableCoordonnees);
    }

    /**
     * calculer la distance maximale totale
     * <p>
     * (pas sûr que ça soit juste étant donné qu'on ne trouve pas la même valeur pour 1 point de contournement dans le pdf, mais pour 2 points oui)
     *
     * @param nombredepointsaparcourrir nombre de points de contournements à parcourir
     * @return La distance maximale totale entre le début, les points de countournements à parcourir et l'arrivée
     */

    public double distanceMaximaleTotale(int nombredepointsaparcourrir)
    {
        // verifier
        if (nombredepointsaparcourrir < 0 || nombredepointsaparcourrir > tableCoordonnees.length)
            throw new IllegalArgumentException("Impossible de calculer la distance maximale totale avec moins d'un 1 point de contournement");
        if (tableCoordonnees.length <= 2) throw new IllegalArgumentException("Pas assez de coordonnées!");

        // 0 point de contournement : distance entre le lieu de départ et le lieu d’arrivée
        if (nombredepointsaparcourrir == 0)
            return tableCoordonnees[0].distance(tableCoordonnees[tableCoordonnees.length - 1]);

        Coordonnees[] points = new Coordonnees[nombredepointsaparcourrir + 2];
        // ajouter le point début & fin
        points[0] = tableCoordonnees[0];
        points[nombredepointsaparcourrir + 1] = tableCoordonnees[tableCoordonnees.length - 1];

        double add = 0.0;
        if (nombredepointsaparcourrir != 1) add = 1.0;

        double atteindre = distanceParcourue() / (nombredepointsaparcourrir + add);
        int actuel = 0;
        double atteint = 0; // valeur temporaire
        // dernier point de contournement
        Coordonnees last = points[0];

        // explications pour 1 point de contournement: distancemaximaletotale.jpg
        for (int j = 1; j < tableCoordonnees.length; j++) {

            atteint += tableCoordonnees[j].distance(last);

            if (atteindre - atteint <= 0) {
                actuel++;
                points[actuel] = tableCoordonnees[j];
                last = points[actuel];
                // remettre la valeur à ça valeur de base
                atteint = 0;
            }

            if (actuel >= nombredepointsaparcourrir) break;

        }

        return calculerDistance(points);
    }

    /**
     * calcule le nombre de fois que le parcours se croise
     *
     * @return le nombre de croisements
     */

    public int nombreDeCroisements()
    {
        if (tableCoordonnees.length <= 2) throw new IllegalArgumentException();

        // valeurs temporaires
        int nombre = 0;
        // lui donner une taille assez importante pour stocker des données temporaires
        Coordonnees[][] coords = new Coordonnees[tableCoordonnees.length * 2][4];

        for (int i = 1; i < tableCoordonnees.length; i++) {
            Coordonnees p1 = tableCoordonnees[i - 1];
            Coordonnees p2 = tableCoordonnees[i];
            for (int j = 1; j < tableCoordonnees.length; j++)
                if (i != j) {
                    Coordonnees q1 = tableCoordonnees[j - 1];
                    Coordonnees q2 = tableCoordonnees[j];
                    boolean fairebreak = false;

                    // eviter les doublets
                    for (Coordonnees[] save : coords) {
                        //Save[0] = p1 / Save[1] = p2 / Save[2] = q1 / Save[3] = q2
                        if (save != null && save.length == 4 && save[0] != null && save[1] != null && save[2] != null && save[3] != null && ((save[0].equals(p1) && save[1].equals(q2) && save[2].equals(q1) && save[3].equals(p2)) || (save[0].equals(q1) && save[1].equals(q2) && save[2].equals(p1) && save[3].equals(p2)))) {
                            fairebreak = true;
                            break;
                        }
                    }

                    // break si doublet
                    if (fairebreak) break;

                    // point égal
                    if (p1.equals(q1)) coords[nombre++] = new Coordonnees[]{p1, p2, q1, q2};
                        // on verifie si les points n'ont pas déjà été verifiés et so les points q1 et q2 ne sont pas égaux aux points p1 et p2
                    else if (!p1.equals(q2) && !p2.equals(q1) && !p2.equals(q2) && Coordonnees.segmentsCroises(p1, p2, q1, q2))
                        coords[nombre++] = new Coordonnees[]{p1, p2, q1, q2};

                }
        }

        return nombre;
    }

    /**
     * calculer le nombre de cibles qui on été atteintes pendant le parcours
     *
     * @param cibles La liste des cibles qu'il faut verifier
     * @return les cibles qui ont été atteintes
     */
    public Coordonnees[] ciblesAtteintes(Coordonnees[] cibles)
    {
        int values = 0;

        // chercher si une cible a atteint un point du parcours
        for (int i = 0; i < cibles.length; i++) {
            Coordonnees cible = cibles[i];
            boolean found = false;

            // on compare
            for (Coordonnees coord : tableCoordonnees) {
                if (cible.equals(coord)) {
                    found = true;
                    break;
                }
            }

            // supprimer la valeur
            if (!found) cibles[i] = null;
            else values++;

        }

        // créer la table
        Coordonnees[] coordonnees = new Coordonnees[values];

        // remettre à zero
        values = 0;

        // ajouter les valeurs valides
        for (Coordonnees cible : cibles)
            if (cible != null) coordonnees[values++] = cible;

        return coordonnees;
    }

    /**
     * calcule le nombre de cibles atteintes lors d’un parcours imposé
     *
     * @param parcourimpose Le parcours qui est imposé
     * @return Le nombre de cibles atteintes
     * @throws IllegalArgumentException Si le parcour imposé ne possède pas de coordonnées
     */

    public int nombreDeCiblesAtteintes(Coordonnees[] parcourimpose)
    {
        if (parcourimpose.length == 0) throw new IllegalArgumentException();
        int cibles = 0;
        Coordonnees cibleactuelle = parcourimpose[0];

        for (Coordonnees coords : tableCoordonnees) {

            // vérifier si la cible est atteinte
            if (cibleactuelle.equals(coords)) {
                cibles++;
                cibleactuelle = parcourimpose[cibles];

                // annuler
                if (cibles == parcourimpose.length) break;

            }

        }

        return cibles;
    }

    /**
     * @return le nombre de coordonnées qui forment le parcours
     */

    public int nombreDeCoordonnees()
    {
        return tableCoordonnees.length;
    }

    /**
     * @return le parcours du vol
     */

    public Coordonnees[] getParcours()
    {
        return tableCoordonnees;
    }

    /**
     * @return la date du vol
     */

    public Date getDate()
    {
        return date;
    }

    /**
     * @return le pilote
     */

    public String getPilote()
    {
        return pilote;
    }

} // fin classe
