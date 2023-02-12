
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;


public class Test {

    public static void main(String[] args) {
        //testErreur();
        //testLangage();
        //testTools();
        //testMotReservé();
        //testTableIdentificateur();
        //testSymboles();
        testLL1();
    }

    public static void testLL1() {
        //AFFECTATION
        //T_UNILEX[] TOKEN = {T_UNILEX.IDENT, T_UNILEX.AFF, null};
        //OP_BIN
        //T_UNILEX[] TOKEN = {T_UNILEX.DIVI};
        //LECTURE
        //T_UNILEX[] TOKEN = {T_UNILEX.MOTCLE, T_UNILEX.PAROUV, T_UNILEX.IDENT, T_UNILEX.VIRG, T_UNILEX.IDENT, T_UNILEX.PARFER};
        //String[]  CHAINE = {"LIRE"};
        //ECRITURE
        //T_UNILEX[] TOKEN = {T_UNILEX.MOTCLE, T_UNILEX.PAROUV, T_UNILEX.CH, T_UNILEX.VIRG, T_UNILEX.CH, T_UNILEX.VIRG, T_UNILEX.CH, T_UNILEX.PARFER};
        // String[]  CHAINE = {"ECRIRE"};
        //
        //PROG
        //T_UNILEX[] TOKEN = {T_UNILEX.MOTCLE, T_UNILEX.IDENT, T_UNILEX.PTVIRG, T_UNILEX.POINT};
        //String[]  CHAINE = {"PROGRAMME", null, null, null};

        //DECL_VAR
        //T_UNILEX[] TOKEN = {T_UNILEX.MOTCLE, T_UNILEX.IDENT, T_UNILEX.VIRG, T_UNILEX.IDENT,  T_UNILEX.PTVIRG};
        //String[]  CHAINE = {"VAR"};

        //DECL_CONST
        //T_UNILEX[] TOKEN = {T_UNILEX.MOTCLE, T_UNILEX.IDENT, T_UNILEX.EG, T_UNILEX.ENT, T_UNILEX.VIRG,  T_UNILEX.IDENT, T_UNILEX.EG, T_UNILEX.ENT, T_UNILEX.PTVIRG};
        //String[]  CHAINE = {"CONST"};

        //BLOC
        //T_UNILEX[] TOKEN = {T_UNILEX.MOTCLE,  T_UNILEX.MOTCLE};
        //String[]  CHAINE = {"DEBUT", "", "FIN"};
        //ECR_EXP
        //T_UNILEX[] TOKEN = {T_UNILEX.ENT};
        //String[]  CHAINE = {"DEBUT", "", "FIN"};

        //EXP difficile à tester
        //T_UNILEX[] TOKEN = {T_UNILEX.ENT};
        //String[]  CHAINE = {"DEBUT", "", "FIN"};

        //TERME
        //T_UNILEX[] TOKEN = {T_UNILEX.ENT};
        //String[]  CHAINE = {"DEBUT", "", "FIN"};

        //OP_BIN
        //T_UNILEX[] TOKEN = {T_UNILEX.ENT };
        //String[]  CHAINE = {"DEBUT", "", "FIN"};


        //test affectation

        T_UNILEX[] TOKEN = {T_UNILEX.MOTCLE,T_UNILEX.IDENT, T_UNILEX.AFF, T_UNILEX.IDENT, T_UNILEX.MOTCLE };
        String[]  CHAINE = {"DEBUT", "","", "", "FIN"};
        LL1 ll1 = new LL1(TOKEN, CHAINE);
        try {

            System.out.println(ll1.BLOC());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(ll1.TEST());
    }

    public static void testTableIdentificateur() {
        TableIdentificateur tableIdentificateur = new TableIdentificateur();

        tableIdentificateur.INSERER("c1", "CONST");

        tableIdentificateur.INSERER("c2", "CONST");
        tableIdentificateur.INSERER("v1", "VAR");
        tableIdentificateur.INSERER("c2", "CONST");

        ArrayList<T_UNILEX> TOKENS = new ArrayList<>();
        ArrayList<String> TOKENS_CAR = new ArrayList<>();
        TOKENS.add(T_UNILEX.MOTCLE);
        TOKENS.add(T_UNILEX.IDENT);
        TOKENS.add(T_UNILEX.MOTCLE);
        TOKENS.add(T_UNILEX.IDENT);
        TOKENS.add(T_UNILEX.MOTCLE);
        TOKENS.add(T_UNILEX.IDENT);
        TOKENS_CAR.add("CONST");
        TOKENS_CAR.add("test1");

        TOKENS_CAR.add("VAR");
        TOKENS_CAR.add("test2");
        TOKENS_CAR.add("VARI");
        TOKENS_CAR.add("test3");

        tableIdentificateur.INSERER_TOKENS(TOKENS, TOKENS_CAR);
        tableIdentificateur.AFFICHER_TABLE_IDENT();

        /*
        int i = 0;
        while (true) {
            i++;
            tableIdentificateur.INSERER("v" + String.valueOf(i), "VAR");
        }

         */


    }

    public static void testTools() {
        char[] c = new char[]{'a', 'b', 'c'};
        System.out.println(Tools.getIntance().contains(c, 'a'));
        System.out.println(Tools.getIntance().contains(c, 'd'));
    }

    public static void testErreur() {
        Erreur erreur = new Erreur(1, "fin de fichier atteinte");
        System.out.println(erreur.afficherErreur());
    }

}

