import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.StringTokenizer;

/**
 * Cette classe Date permet de construire une date et de la valider. Elle permet
 * aussi de lire la date system, de tester si une annee est bissextile.
 * Remarque: il faudrait completer cette classe en y ajoutant un nombre de jours
 * ecoules depuis une date de reference. Cela facilite les comparaisons, ...
 *
 * @author Olivier Legrand 01/2003.
 */
public class Date implements java.io.Serializable
{

    private static final long  serialVersionUID = 1L;

    // le 1er janvier 1980 est la date de reference, c'etait un mardi
    private static final int   JOUR_REF         = 2; // 2 car mardi (1: lundi, 2:

    // mardi,...)
    private static final int   ANNEE_MIN        = 1980; // pour la validation

    private static final int   ANNEE_MAX        = 2050; // pour la validation

    private static       int[] NB_JOURS_MOIS    = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    private int jour;

    private int mois;

    private int annee;

    /**
     * constructeur de la classe Date.
     *
     * @param jour  entier representant le jour.
     * @param mois  entier representant le mois.
     * @param annee entier representant l'annee.
     * @throws Exception si la date est invalide.
     */
    public Date(int jour, int mois, int annee) throws Exception
    {
        if (Date.estValide(jour, mois, annee)) {
            this.jour = jour;
            this.mois = mois;
            this.annee = annee;
        } else {
            throw new Exception("Date incorrecte : " + jour + " / " + mois + " / " + annee);
        }
    }

    /**
     * constructeur qui reçoit une chaîne de caractères contenant une date. Elle
     * permet de valider et de construire une date. Si la date extraite de cette
     * chaîne n'est pas valide, elle renvoie une exception.
     *
     * @param stringDate chaîne de caractères contenant la date à creer.
     * @throws Exception si la date est invalide.
     */
    public Date(String stringDate) throws Exception
    {

        // format valide : jj/mm/aaaa
        String s = stringDate.trim();

        // jours
        int jour = 0;
        StringTokenizer st = new StringTokenizer(s, "/", true);
        try {
            if (!st.hasMoreTokens()) throw new Exception("Date incorrecte. Veuillez verifier les " + " jours.");
            String sJours = (st.nextToken()).trim();
            jour = (new Integer(sJours)).intValue();
        } catch (Exception e) {
            throw new Exception("Date incorrecte. Veuillez verifier les " + " jours.");
        }

        // les mois
        int mois;
        try {
            if (!st.hasMoreTokens()) throw new Exception("Date incorrecte. Veuillez verifier le " + " mois.");
            st.nextToken(); // lecture du delimiteur
            String sMois = (st.nextToken()).trim();
            mois = (new Integer(sMois)).intValue();
        } catch (Exception e) {
            throw new Exception("Date incorrecte. Veuillez verifier le " + " mois.");
        }

        // les annees
        int annee;
        try {
            String sAnnees = s.substring(s.length() - 4, s.length());
            annee = (new Integer(sAnnees)).intValue();
        } catch (Exception e) {
            throw new Exception("Date incorrecte. Veuillez verifier l' " + " annee.");
        }
        if (Date.estValide(jour, mois, annee)) {
            this.jour = jour;
            this.mois = mois;
            this.annee = annee;
        } else {
            throw new Exception("Date incorrecte : " + jour + " / " + mois + " / " + annee);
        }
    }

    /**
     * constructeur de la classe Date.
     *
     * @param numJour entier representant le numero du jour dans l'annee. Attention,
     *                1er janvier => 0
     * @param annee   entier representant l'annee.
     * @throws Exception si la date est invalide.
     */
    public Date(int numJour, int annee) throws Exception
    {
        boolean anneeBis = Date.anneeBissextile(annee);
        int nbMaxJours = anneeBis ? 366 : 365;
        if (numJour < 0 || numJour >= nbMaxJours)
            throw new Exception("Impossible de creer une date." + " numJour incorrecte : " + numJour);
        int nbJours = numJour + 1;
        int mois;
        for (mois = 1; mois < 13; mois++) {
            int nbJoursMois;
            if (mois == 2 && anneeBis) nbJoursMois = 29;
            else nbJoursMois = NB_JOURS_MOIS[mois];
            if (nbJours <= nbJoursMois) break;
            nbJours -= nbJoursMois;
        }
        if (Date.estValide(nbJours, mois, annee)) {
            this.jour = nbJours;
            this.mois = mois;
            this.annee = annee;
        } else {
            throw new Exception("Date incorrecte : " + nbJours + " / " + mois + " / " + annee);
        }
    }

    /**
     * methode de lecture de la date système.
     *
     * @return elle retourne la date système.
     */
    public static Date lireDateSystem() throws Exception
    {
        long time = System.currentTimeMillis();
        GregorianCalendar date = new GregorianCalendar(Locale.FRANCE);
        date.setTime(new java.util.Date(time));
        int jour = date.get(Calendar.DAY_OF_MONTH);
        int mois = date.get(Calendar.MONTH) + 1;
        int annee = date.get(Calendar.YEAR);
        return new Date(jour, mois, annee);
    }

    /*
     * methode qui teste si annee bissextile.
     *
     * @param annee entier indicant l'annee à tester.
     */
    public static boolean anneeBissextile(int annee)
    {
        if (((annee % 100) == 0) && ((annee % 400) != 0)) return false;
        if ((annee % 4) == 0) return true;
        return false;
    }

    /*
     * methode qui teste la validite d'une date.
     *
     * @param jour entier representant le jour.
     *
     * @param mois entier representant le mois.
     *
     * @param annee entier representant l'annee.
     *
     * @return boolean signale si la date est valide ou pas.
     */
    public static boolean estValide(int jour, int mois, int annee)
    {
        if ((annee < ANNEE_MIN) || (annee > ANNEE_MAX)) return false;

        // validation des jours et des mois.
        switch (mois) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return !((jour < 1) || (jour > 31));
            case 4:
            case 6:
            case 9:
            case 11:
                return !((jour < 1) || (jour > 30));
            case 2: {
                return !((jour < 1) || (jour > 29) || jour == 29 && !Date.anneeBissextile(annee));
            }
            default:
                return false;
        } // fin switch
    }

    /**
     * methode de comparaison de 2 dates.
     *
     * @param date2 la date à comparer à la date de l'objet courant.
     * @return entier <0 si la date precède la date2. =0 si les dates sont
     * identiques. >0 si la date est posterieure à la date2.
     */
    public int compareTo(Date date2)
    {
        if (this.annee < date2.annee) return -1;
        if (this.annee > date2.annee) return 1;
        // même annee
        if (this.mois < date2.mois) return -1;
        if (this.mois > date2.mois) return 1;
        // même mois
        if (this.jour < date2.jour) return -1;
        if (this.jour > date2.jour) return 1;
        // même jour
        return 0;
    }

    /**
     * methode de formatage de la date en string.
     *
     * @return elle retourne une string contenant la date (format jj/mm/aa).
     */
    public String toString()
    {
        String sJours, sMois;
        if ((this.jour / 10) == 0) sJours = new String("0" + this.jour);
        else sJours = "" + this.jour;
        if ((this.mois / 10) == 0) sMois = "0" + this.mois;
        else sMois = "" + this.mois;
        return "" + sJours + "/" + sMois + "/" + annee;
    }

    /**
     * methode qui renvoie le numero du jour dans l'annee (en commençant à 0).
     * ex: 1 janvier => 0, 31 janvier => 30. Ce nombre indique aussi le nombre
     * de jours ecoules depuis le debut de l'annee.
     *
     * @return int nombre de jours ecoules depuis le debut de l'annee.
     */
    public int numJourDsAnnee()
    {
        int mois;
        int nbJours = 0;
        for (mois = 1; mois < this.getMois(); mois++) {
            if (mois == 2 && Date.anneeBissextile(this.annee)) {
                nbJours += 29;
            } else {
                nbJours += NB_JOURS_MOIS[mois];
            }
        }
        return nbJours + this.jour - 1;
    }

    public int getJour()
    {
        return this.jour;
    }

    public int getMois()
    {
        return this.mois;
    }

    public int getAnnee()
    {
        return this.annee;
    }

    /**
     * Methode qui renvoie un entier indiquant le numero du jour de la semaine.
     * 1 : lundi .. 7 : dimanche.
     *
     * @return entier indiquant le numero du jour de la semaine.
     */
    public int jourDeLaSemaine()
    {
        int annee1;
        int nbJours = 0;
        for (annee1 = Date.ANNEE_MIN; annee1 < this.annee; annee1++) {
            if (Date.anneeBissextile(annee1)) {
                nbJours += 366;
            } else {
                nbJours += 365;
            }
        }
        int mois;
        for (mois = 1; mois < this.getMois(); mois++) {
            if (mois == 2 && Date.anneeBissextile(this.annee)) {
                nbJours += 29;
            } else {
                nbJours += NB_JOURS_MOIS[mois];
            }
        }
        nbJours += this.jour;
        return nbJours % 7 + JOUR_REF - 1;
    }

} // fin de la classe