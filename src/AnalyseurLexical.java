import javax.xml.transform.Source;
import java.io.File;
import java.util.Scanner;

public class AnalyseurLexical {
    private Compilateur compilateur;

    public AnalyseurLexical(Compilateur compilateur) {
        this.compilateur = compilateur;
    }

    public void analyser() {

    }

    private void LIRE_CHAR() {
        File file = new File(Compilateur.SOURCE);
        try {
            String content = new Scanner(new File(Compilateur.SOURCE)).useDelimiter("\\Z").next();
            System.out.println(content);
        }
        catch (Exception e ) {
            e.printStackTrace();
        }
    }
}
