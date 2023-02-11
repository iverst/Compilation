import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        //testErreur();
        //testLangage();
        //testTools();
        //testMotReservé();
        testTableIdentificateur();
    }
    public static void testMotReservé() {
        Compilateur compilateur = new Compilateur();

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

    public static void testLangage() {

        Transition[] transitions = {new Transition(0, new char[]{'a'},1, false),
                new Transition(1, new char[]{'b'},1, true),
                //new Transition(1, new char[]{'c'},2)

        };
        Langage l = new Langage(0, new int[]{1}, transitions);
        /*
        System.out.println(l.parcourir('a'));
        System.out.println(l.parcourir('b'));
        System.out.println(l.estAccepte());

         */
        System.out.println(l.reconnaitMot("bbbbbb"));
        //System.out.println(l.reconnaitMot("aaaabc"));
    }
}


