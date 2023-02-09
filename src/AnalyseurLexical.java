import com.sun.tools.javac.util.ArrayUtils;

import javax.xml.transform.Source;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;


public class AnalyseurLexical {
    private Compilateur compilateur;
    private char[] chars;
    //nombre entiers
    private final int MAX_INT = 32767;
    private char[] chiffres = "0123456789".toCharArray();
    //chaines caractères
    private final int MAX_STRING = 50;
    //identificateurs
    private final int MAX_MOTCLE = 20;
    private String[] motsClés = {"DEBUT", "FIN", "VAR", "ECRIRE", "LIRE","SI"};
    //symboles simples
    private final char[] symbolesSimples = ",;.:()<>=+*-/".toCharArray();
    //symboles composes
    private final String[] symbolesComposes = {"<=", ">=", "<>", ":="};


    public AnalyseurLexical(Compilateur compilateur) {
        this.compilateur = compilateur;
    }

    public void analyser(String data) {
        this.chars = data.toCharArray();
        while (LIRE_CHAR()) {
            SAUTER_SEPARATEURS();
            if(Compilateur.CARLU == '1')
                RECO_ENTIER();
            else if (Compilateur.CARLU == '\'') {
                RECO_CHAINE();
            }
            else {
                System.out.println(RECO_IDENT_OU_MOT_RESERVE());
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    private boolean LIRE_CHAR() {
        if (Compilateur.NUM_LIGNE < chars.length) {
            Compilateur.CARLU = chars[Compilateur.NUM_LIGNE];
            Compilateur.NUM_LIGNE++;
            return true;
        }
        else {
            System.out.println(new Erreur(1, "fin de fichier atteinte").afficherErreur());
            return false;
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    private void SAUTER_SEPARATEURS() {
        //tabulation
        if (Compilateur.CARLU == ' ' || Compilateur.CARLU == '\t') {
            while (Compilateur.CARLU == ' ' || Compilateur.CARLU == '\t') {
                LIRE_CHAR();
            }
        }
        //commentaire
        if (Compilateur.CARLU == '{') {

            LIRE_CHAR();
            int nbCommentaire = 1;

            while (nbCommentaire != 0 ) {
                if (Compilateur.CARLU == '{') {
                    nbCommentaire++;
                }
                else if (Compilateur.CARLU == '}') {
                    nbCommentaire--;
                }
                LIRE_CHAR();

            }
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    public T_UNILEX RECO_ENTIER() {
        String nombreChaine = "";
        while (Tools.getIntance().contains(chiffres ,Compilateur.CARLU)) {
            nombreChaine += Compilateur.CARLU;
            if(! LIRE_CHAR()) {
                break;
            }
        }
        int nombre = Integer.parseInt(nombreChaine);

        if (MAX_INT < nombre) {
            Erreur erreur = new Erreur(2, "Int supérieur à la valeur maximale de 32767");
            erreur.afficherErreur();
            try {
                erreur.creerException();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        Compilateur.NOMBRE = nombre;

        return T_UNILEX.ENT;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////


    public T_UNILEX RECO_CHAINE() {
        boolean hasEnded;
        String chaine = "";
        while (hasEnded = LIRE_CHAR()  && Compilateur.CARLU != '\'') {
            chaine += Compilateur.CARLU;
        }
        if (!hasEnded) {
            LIRE_CHAR();
        }

        if (chaine.length() > Compilateur.LONG_MAX_CHAINE) {
            try {
                Erreur erreur = new Erreur(20, "Chaine de caractère trop longe !");
                erreur.creerException();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        return T_UNILEX.CH;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////

    public T_UNILEX RECO_IDENT_OU_MOT_RESERVE() {
        String ident = "";
        while ((Compilateur.CARLU >= 'A' && Compilateur.CARLU <= 'Z') || (Compilateur.CARLU >= 'a' && Compilateur.CARLU <= 'z') || Compilateur.CARLU == '_' || Tools.getIntance().contains(chiffres, Compilateur.CARLU)) {
            ident += Compilateur.CARLU;
            if (! LIRE_CHAR() || ident.length() >= Compilateur.LONG_MAX_IDENT) {
                break;
            }
            LIRE_CHAR();
        }
        
        Compilateur.CHAINE = ident.toUpperCase();
        if(Compilateur.EST_UN_MOT_RESERVE()) {
            return T_UNILEX.MOTCLE;
        }
        return T_UNILEX.IDENT;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////


}
