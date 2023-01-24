public class Compilateur {
    public static final int LONG_MAX_IDENT = 20;
    public static final int LONG_MAX_CHAINE = 50;
    public static final int NB_MOTS_RESERVES = 7;
    public static String SOURCE = "test.txt";
    public static char CARLU;
    public static int NOMBRE;
    public static String CHAINE;
    public static int NUM_LIGNE;
    public static String[] TABLE_MOTS_RESERVES = new String[NB_MOTS_RESERVES];

    public void compiler(){
        AnalyseurLexical analyseurLexical = new AnalyseurLexical(this);
        analyseurLexical.analyser();

    }

}
