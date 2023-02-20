import java.io.FileReader;
import java.util.Scanner;

public class Compilateur {
    public static final int LONG_MAX_IDENT = 20;
    public static final int LONG_MAX_CHAINE = 50;
    public static final int NB_MOTS_RESERVES = 7;
    public static String SOURCE = "code/exemple2";
    public static char CARLU;
    public static int NOMBRE;
    public static String CHAINE;
    public static int NUM_LIGNE;
    public static String[] TABLE_MOTS_RESERVES = {"DEBUT", "FIN", "VAR", "ECRIRE", "LIRE","SI", "CONST", "PROGRAMME"};
    private String data;

    public void compiler(){
        //Analyse Lexicale
        AnalyseurLexical analyseurLexical = new AnalyseurLexical(this);
        data = lireFichier(SOURCE);
        analyseurLexical.INITIALISER(data);
        analyseurLexical.ANALEX();

        //Table Identificateur
        TableIdentificateurs tableIdentificateurs2 = new TableIdentificateurs(analyseurLexical.TOKENS(), analyseurLexical.TOKENS_CAR());
        tableIdentificateurs2.INSERER_TOKENS();
        tableIdentificateurs2.AFFICHE_TABLE_IDENT();


        //Analyse Syntaxique
        AnalyseurSyntaxique analyseurSyntaxique = new AnalyseurSyntaxique(analyseurLexical.TOKENS(), analyseurLexical.TOKENS_CAR());
        if(analyseurSyntaxique.EST_CORECT()) {
            System.out.println("Programme Syntaxiquement Correct");
        }
        else {
            System.out.println("Programme Syntaxiquement INCORRECT !");
        }



        //Virtual Machine
        VirtualMachine virtualMachine = new VirtualMachine((T_UNILEX[])analyseurLexical.TOKENS().toArray(new T_UNILEX[0]),
                (String[])analyseurLexical.TOKENS_CAR().toArray(new String[0]),
                SOURCE, analyseurSyntaxique);
    }

    public String lireFichier(String filePath) {
        String data = "";
        try {
            Scanner scanner = new Scanner(new FileReader(filePath));
            while (scanner.hasNextLine()) {
                data += scanner.nextLine() + "\n";
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(data);
        return data;

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////

    public static boolean EST_UN_MOT_RESERVE() {
        for (String s : Compilateur.TABLE_MOTS_RESERVES) {
            if (Compilateur.CHAINE.equals(s.toUpperCase())) {
                return true;
            }
        }
        return false;
    }
}
