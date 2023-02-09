import java.util.ArrayList;


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
    private final char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private final char[] alphabetMaj = "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();

    //Unités lexicales
    ArrayList<T_UNILEX> unitesLexicales = new ArrayList<>();
    //caractères correspondant
    ArrayList<String> unitesLexicalesCaracteres = new ArrayList<>();

    public AnalyseurLexical(Compilateur compilateur) {
        this.compilateur = compilateur;
    }


    public void INITIALISER(String data) {
        this.chars = data.toCharArray();

    }

    public void ANALEX() {
        do {


            T_UNILEX uniteLexicale = null;

            if (Compilateur.CARLU == '\'') {
                uniteLexicale = RECO_CHAINE();
            }

            else if (Tools.getIntance().contains(chiffres, Compilateur.CARLU)) {
                uniteLexicale = RECO_ENTIER();
            }
            else if (Tools.getIntance().contains(alphabet, Compilateur.CARLU) || Tools.getIntance().contains(alphabetMaj, Compilateur.CARLU) ||
                    Compilateur.CARLU == '_' || Tools.getIntance().contains(chiffres, Compilateur.CARLU)) {
                uniteLexicale = RECO_IDENT_OU_MOT_RESERVE();
            }
            else if (Tools.getIntance().contains(symbolesSimples, Compilateur.CARLU)) {
                uniteLexicale = RECO_SYMB();
            }
            else if (Compilateur.CARLU == ' ' || Compilateur.CARLU == '\t' || Compilateur.CARLU == '{'){
                SAUTER_SEPARATEURS();
            }

            else {
                LIRE_CHAR();
            }
            if (uniteLexicale != null) {
                AFFICHER(uniteLexicale);
            }
        }
        while (! FIN_DE_FICHIER());
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    public void AFFICHER(T_UNILEX uniteLexical) {
        unitesLexicales.add(uniteLexical);
        switch (uniteLexical) {
            case CH:
            case MOTCLE:
            case IDENT:
                unitesLexicalesCaracteres.add(Compilateur.CHAINE);
                break;
            case ENT:
                unitesLexicalesCaracteres.add(String.valueOf(Compilateur.NOMBRE));
                break;
            case EG:
                unitesLexicalesCaracteres.add("=");
                break;
            case AFF:
                unitesLexicalesCaracteres.add(":=");
                break;
            case INF:
                unitesLexicalesCaracteres.add("<");
                break;
            case SUP:
                unitesLexicalesCaracteres.add(">");
                break;
            case DIFF:
                unitesLexicalesCaracteres.add("<>");
                break;
            case DIVI:
                unitesLexicalesCaracteres.add("/");
                break;
            case INFE:
                unitesLexicalesCaracteres.add("<=");
                break;
            case MULT:
                unitesLexicalesCaracteres.add("*");
                break;
            case PLUS:
                unitesLexicalesCaracteres.add("+");
                break;
            case SUPE:
                unitesLexicalesCaracteres.add(">=");
                break;
            case MOINS:
                unitesLexicalesCaracteres.add("-");
                break;
            case POINT:
                unitesLexicalesCaracteres.add(".");
                break;
            case PARFER:
                unitesLexicalesCaracteres.add(")");
                break;
            case PAROUV:
                unitesLexicalesCaracteres.add("(");
                break;
            case PTVIRG:
                unitesLexicalesCaracteres.add(";");
                break;
            case DEUXPTS:
                unitesLexicalesCaracteres.add(":");
                break;
            case VIRG:
                unitesLexicalesCaracteres.add(",");
                break;

        }
        System.out.print(unitesLexicales.get(unitesLexicales.size() - 1));
        System.out.print("  ||  ");
        System.out.println(unitesLexicalesCaracteres.get(unitesLexicalesCaracteres.size() - 1));

        return;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////
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
    private boolean FIN_DE_FICHIER() {
        if (Compilateur.NUM_LIGNE < chars.length) {
            return false;
        }
        else {
            System.out.println(new Erreur(1, "fin de fichier atteinte").afficherErreur());
            return true;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    private void SAUTER_SEPARATEURS() {
        System.out.println("SAUT");
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
    private T_UNILEX RECO_ENTIER() {
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


    private T_UNILEX RECO_CHAINE() {
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

        Compilateur.CHAINE = chaine;
        return T_UNILEX.CH;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////

    private T_UNILEX RECO_IDENT_OU_MOT_RESERVE() {
        String ident = "";
        while (Tools.getIntance().contains(alphabet, Compilateur.CARLU) || Tools.getIntance().contains(alphabetMaj, Compilateur.CARLU) || Compilateur.CARLU == '_' || Tools.getIntance().contains(chiffres, Compilateur.CARLU)) {
            ident += Compilateur.CARLU;
            if (! LIRE_CHAR() || ident.length() >= Compilateur.LONG_MAX_IDENT) {
                break;
            }
        }

        Compilateur.CHAINE = ident.toUpperCase();

        if(Compilateur.EST_UN_MOT_RESERVE()) {
            return T_UNILEX.MOTCLE;
        }
        return T_UNILEX.IDENT;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////

    private T_UNILEX RECO_SYMB() {
        char c = Compilateur.CARLU;
        LIRE_CHAR();
        switch (c) {
            //cas simple
            case ',':
                return T_UNILEX.VIRG;
            case ';':
                return T_UNILEX.PTVIRG;
            case '.':
                return T_UNILEX.POINT;
            case '=':
                return T_UNILEX.EG;
            case '+':
                return T_UNILEX.PLUS;
            case '-':
                return T_UNILEX.MOINS;
            case '*':
                return T_UNILEX.MULT;
            case '/':
                return T_UNILEX.DIVI;
            case '(':
                return T_UNILEX.PAROUV;
            case ')':
                return T_UNILEX.PARFER;
                //cas complexes

            case '<':
                if(Compilateur.CARLU == '>') {
                    LIRE_CHAR();
                    return T_UNILEX.DIFF;
                }
                else if (Compilateur.CARLU == '=') {
                    LIRE_CHAR();
                    return T_UNILEX.INFE;
                }
                else {
                    return T_UNILEX.INF;
                }
            case '>':
                if(Compilateur.CARLU == '=') {
                    LIRE_CHAR();
                    return T_UNILEX.SUPE;
                }
                else {
                    return T_UNILEX.SUP;
                }
            case ':':
                if(Compilateur.CARLU == '=') {
                    LIRE_CHAR();
                    return T_UNILEX.AFF;
                }
                else {
                    return T_UNILEX.DEUXPTS;
                }
            default:
                return null;

        }
    }

}
