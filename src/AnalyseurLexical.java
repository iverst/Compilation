import javax.xml.transform.Source;
import java.io.File;
import java.util.Scanner;


public class AnalyseurLexical {
    private Compilateur compilateur;
    private char[] chars;
    public AnalyseurLexical(Compilateur compilateur) {
        this.compilateur = compilateur;
    }

    public void analyser(String data) {
        this.chars = data.toCharArray();
        while (LIRE_CHAR()) {
            SAUTER_SEPARATEURS();
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
        if (Compilateur.CARLU == ' ' || Compilateur.CARLU == '\t') {
            while (Compilateur.CARLU == ' ' || Compilateur.CARLU == '\t') {
                System.out.println("Esapce :" + "'" + Compilateur.CARLU + "',");
                LIRE_CHAR();
            }
        }
        if (Compilateur.CARLU == '{') {
            LIRE_CHAR();
            System.out.println("Commentaire :" + "'" + Compilateur.CARLU + "',");
            while (Compilateur.CARLU != '{' && Compilateur.CARLU != '}' ) {
                System.out.println("Commentaire :" + "'" + Compilateur.CARLU + "',");

                LIRE_CHAR();
            }
        }
    }


}
