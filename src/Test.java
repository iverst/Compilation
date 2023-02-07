public class Test {
    public static void main(String[] args) {
        //testErreur();
        //testLangage();
        testTools();
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


