import java.io.*;

/**
 * Classe permettant d'ecrire et lire dans un fichier. Elle permet d'ecrire les
 * differents types primitifs (byte, short, ...) + des objets ( String, tableau
 * ou tout autre objet qui implemente l'interface java.io.Serializable). Les
 * methodes de cette classe peuvent lancer des exceptions. (fin de fichier,
 * erreur de lecture ou ecriture, etc.) Pour creer un fichier : il faut appeler
 * le constructeur en lui passant en parametre une String specifiant le chemin
 * d'acces et le nom du fichier. Ensuite, il faut l'ouvrir en ecriture. Enfin,
 * on peut y ecrire les donnees. Lors de l'ecriture dans un fichier existant, le
 * contenu est ecrase et remplace par la(es) donnee(s) ecrite(s). Pour lire un
 * fichier existant, il faut creer un objet de la classe Fichier, en lui
 * renseignant le chemin d'acces et le nom du fichier. Ensuite, il faut l'ouvrir
 * en lecture. Enfin, il peut être lu, enregistrement par enregistrement. Si la
 * fin du fichier est rencontree, une exception de type "java.io.EOFException"
 * est lancee. Le fichier doit être ferme en utilisant la methode "fermer()".
 *
 * @author O.Legrand
 * @version 1.0
 */
public class Fichier
{

    private ObjectOutputStream out; // objet servant à l'ecriture dans le stream

    private ObjectInputStream  in; // objet servant à la lecture dans le stream

    private String             nomFichier; // contient le nom complet du fichier. (
    // path+nom)

    /**
     * constructeur de la classe Fichier.
     *
     * @param nomFichier String specifiant le chemin d'acces et le nom du fichier.
     *                   Exemple : "C:/java/dossier/donnees.dat" ('/' obligatoire et
     *                   non '\')
     */
    public Fichier(String nomFichier)
    {
        this.nomFichier = nomFichier;
        in = null;
        out = null;
    }

    /**
     * ouverture du fichier en ecriture
     *
     * @throws java.io.IOException si erreur lors de l'ouverture en ecriture.
     */
    public void ouvrirEnEcriture() throws java.io.IOException
    {
        if (out != null || in != null) {
            throw new java.io.IOException("fichier dejà ouvert");
        }
        out = new ObjectOutputStream(new FileOutputStream(nomFichier));
    }

    /**
     * ouverture du fichier en lecture
     *
     * @throws java.io.IOException si erreur lors de l'ouverture en lecture.
     */
    public void ouvrirEnLecture() throws java.io.IOException
    {
        if (out != null || in != null) {
            throw new java.io.IOException("fichier dejà ouvert");
        }
        in = new ObjectInputStream(new FileInputStream(nomFichier));
    }

    /**
     * ecriture d'un reel de type float dans le fichier.
     *
     * @param reel le nombre reel à ecrire dans le fichier.
     * @throws java.io.IOException si erreur lors de l'ecriture.
     */
    public void ecrireFloat(float reel) throws java.io.IOException
    {
        out.writeFloat(reel);
    }

    /**
     * lecture d'un reel de type float dans le fichier.
     *
     * @return le nombre reel lu dans le fichier.
     * @throws java.io.EOFException si fin de fichier rencontree.
     * @throws java.io.IOException  si erreur lors de la lecture.
     */
    public float lireFloat() throws java.io.EOFException, java.io.IOException
    {
        return in.readFloat();
    }

    /**
     * ecriture d'un reel de type double dans le fichier.
     *
     * @param reel le nombre reel à ecrire dans le fichier.
     * @throws java.io.IOException si erreur lors de l'ecriture.
     */
    public void ecrireDouble(double reel) throws java.io.IOException
    {
        out.writeDouble(reel);
    }

    /**
     * lecture d'un reel de type double dans le fichier.
     *
     * @return le nombre reel lu dans le fichier.
     * @throws java.io.EOFException si fin de fichier rencontree.
     * @throws java.io.IOException  si erreur lors de la lecture.
     */
    public double lireDouble() throws java.io.EOFException, java.io.IOException
    {
        return in.readDouble();
    }

    /**
     * ecriture d'un entier de type byte dans le fichier.
     *
     * @param entier le nombre entier à ecrire dans le fichier.
     * @throws java.io.IOException si erreur lors de l'ecriture.
     */
    public void ecrireByte(byte entier) throws java.io.IOException
    {
        out.writeByte(entier);
    }

    /**
     * lecture d'un entier de type byte dans le fichier.
     *
     * @return le nombre entier lu dans le fichier.
     * @throws java.io.EOFException si fin de fichier rencontree.
     * @throws java.io.IOException  si erreur lors de la lecture.
     */
    public byte lireByte() throws java.io.EOFException, java.io.IOException
    {
        return in.readByte();
    }

    /**
     * ecriture d'un entier de type short dans le fichier.
     *
     * @param entier le nombre entier à ecrire dans le fichier.
     * @throws java.io.IOException si erreur lors de l'ecriture.
     */
    public void ecrireShort(short entier) throws java.io.IOException
    {
        out.writeShort(entier);
    }

    /**
     * lecture d'un entier de type short dans le fichier.
     *
     * @return le nombre entier lu dans le fichier.
     * @throws java.io.EOFException si fin de fichier rencontree.
     * @throws java.io.IOException  si erreur lors de la lecture.
     */
    public short lireShort() throws java.io.EOFException, java.io.IOException
    {
        return in.readShort();
    }

    /**
     * ecriture d'un entier de type int dans le fichier.
     *
     * @param entier le nombre entier à ecrire dans le fichier.
     * @throws java.io.IOException si erreur lors de l'ecriture.
     */
    public void ecrireInt(int entier) throws java.io.IOException
    {
        out.writeInt(entier);
    }

    /**
     * lecture d'un entier de type int dans le fichier.
     *
     * @return le nombre entier lu dans le fichier.
     * @throws java.io.EOFException si fin de fichier rencontree.
     * @throws java.io.IOException  si erreur lors de la lecture.
     */
    public int lireInt() throws java.io.EOFException, java.io.IOException
    {
        return in.readInt();
    }

    /**
     * ecriture d'un entier de type long dans le fichier.
     *
     * @param entier le nombre entier à ecrire dans le fichier.
     * @throws java.io.IOException si erreur lors de l'ecriture.
     */
    public void ecrireLong(long entier) throws java.io.IOException
    {
        out.writeLong(entier);
    }

    /**
     * lecture d'un entier de type long dans le fichier.
     *
     * @return le nombre entier lu dans le fichier.
     * @throws java.io.EOFException si fin de fichier rencontree.
     * @throws java.io.IOException  si erreur lors de la lecture.
     */
    public long lireLong() throws java.io.EOFException, java.io.IOException
    {
        return in.readLong();
    }

    /**
     * ecriture d'un booleen dans le fichier.
     *
     * @param booleen le booleen à ecrire dans le fichier.
     * @throws java.io.IOException si erreur lors de l'ecriture.
     */
    public void ecrireBoolean(boolean booleen) throws java.io.IOException
    {
        out.writeBoolean(booleen);
    }

    /**
     * lecture d'un booleen dans le fichier.
     *
     * @return le booleen lu dans le fichier.
     * @throws java.io.EOFException si fin de fichier rencontree.
     * @throws java.io.IOException  si erreur lors de la lecture.
     */
    public boolean lireBoolean() throws java.io.EOFException, java.io.IOException
    {
        return in.readBoolean();
    }

    /**
     * ecriture d'un objet dans le fichier. Rem.: la classe de cet objet doit
     * implementer l'interface java.io.Serializable (cette interface ne possede
     * aucun champ, ni aucune methode)
     *
     * @param objet l'objet à ecrire dans le fichier.
     * @throws java.io.IOException si erreur lors de l'ecriture.
     */
    public void ecrireObjet(Object objet) throws java.io.IOException
    {
        out.writeObject(objet);
    }

    /**
     * lecture d'un objet dans le fichier. Exemple : Etudiant etudiant =
     * (Etudiant) fichier.lireObjet();
     *
     * @return l'objet lu dans le fichier.
     * @throws java.io.EOFException si fin de fichier rencontree.
     * @throws java.io.IOException  si erreur lors de la lecture.
     * @throws Exception            si autre erreur.
     */
    public Object lireObjet() throws java.io.EOFException, java.io.IOException, Exception
    {
        return in.readObject();
    }

    /**
     * ecriture d'un caractere dans le fichier.
     *
     * @param caractere le caractere à ecrire dans le fichier.
     * @throws java.io.IOException si erreur lors de l'ecriture.
     */
    public void ecrireChar(char caractere) throws java.io.IOException
    {
        out.writeChar(caractere);
    }

    /**
     * lecture d'un caractere dans le fichier.
     *
     * @return le caractere lu dans le fichier.
     * @throws java.io.EOFException si fin de fichier rencontree.
     * @throws java.io.IOException  si erreur lors de la lecture.
     */
    public char lireChar() throws java.io.EOFException, java.io.IOException
    {
        return in.readChar();
    }

    /**
     * fermeture du fichier.
     *
     * @throws java.io.IOException si erreur lors de la fermeture.
     */
    public void fermer() throws java.io.IOException
    {
        if (out != null) fermerOut();
        if (in != null) fermerIn();
    }

    /**
     * suppression du fichier.
     *
     * @throws java.io.IOException si erreur lors de la suppression.
     */
    public void supprimer() throws java.io.IOException
    {
        fermer();
        File file = null;
        try {
            file = new File(nomFichier);
            file.delete();
        } catch (NullPointerException ex) {
            /*
			 * afin que toutes les methodes renvoient une exception du même type
			 * : java.io.IOException.
			 */
            throw new java.io.IOException(ex.getMessage());
        }
    }

    // fermeture de l'input stream.
    private void fermerIn() throws java.io.IOException
    {
        in.close();
        in = null;
    }

    // fermeture de l'output stream
    private void fermerOut() throws java.io.IOException
    {
        out.flush();
        out.close();
        out = null;
    }

} // fin de la classe