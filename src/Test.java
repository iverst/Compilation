
import java.util.ArrayList;
import java.util.LinkedList;


public class Test {

    public static void main(String[] args) {
        //testErreur();
        //testLangage();
        //testTools();
        //testMotReservé();
        //testTableIdentificateur();
        //testSymboles();
        //testLL1();
        //testPile();
        testArrayList();
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
        T_UNILEX[] TOKEN = {T_UNILEX.MOTCLE, T_UNILEX.IDENT, T_UNILEX.EG, T_UNILEX.CH, T_UNILEX.VIRG, T_UNILEX.PTVIRG};
        String[]  CHAINE = {"CONST"};

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

        //T_UNILEX[] TOKEN = {T_UNILEX.MOTCLE,T_UNILEX.IDENT, T_UNILEX.AFF, T_UNILEX.IDENT, T_UNILEX.MOTCLE };
        //String[]  CHAINE = {"DEBUT", "","", "", "FIN"};
        /*LL1 ll1 = new LL1(TOKEN, CHAINE);
        try {

            System.out.println(/ll1.DECL_CONST());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(ll1.TEST());

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


    public static void testPile() {
        LinkedList<String> list = new LinkedList<>();

        list.add("red");
        list.add("yellow");
        list.add("green");

        System.out.println(list);
        System.out.println(list.getLast());
        System.out.println(list.pollLast());
        System.out.println(list.getLast());
        System.out.println(list);

    }

    public static void testArrayList() {
        ArrayList<String> test = new ArrayList<>();
        test.add("1");
        test.add("2 4 5");
        test.set(0,"3");
        System.out.println(test);
        String[] array = test.get(1).split(" ");
        for (String s : array) {
            System.out.println(s);
        }
    }
}

