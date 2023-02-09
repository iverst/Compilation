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
    //chaines charactères
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
                System.out.println(RECO_ENTIER());

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
                System.out.println(LIRE_CHAR());
            }
        }
        //commentaire
        if (Compilateur.CARLU == '{') {
            //System.out.println("Commentaire :" + "'" + Compilateur.CARLU + "',");

            LIRE_CHAR();
            int nbCommentaire = 1;

            while (nbCommentaire != 0 ) {
                //System.out.println(Compilateur.CARLU + "  :  " + String.valueOf(nbCommentaire));
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
            return null;
        }
        Compilateur.NOMBRE = nombre;

        return T_UNILEX.ENT;
    }


}
