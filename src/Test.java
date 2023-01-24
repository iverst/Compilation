public class Test {
    public static void main(String[] args) {
        testErreur();
        testLangage();
    }
    public static void testErreur() {
        Erreur erreur = new Erreur(1, "fin de fichier atteinte");
        System.out.println(erreur.afficherErreur());
    }

    public static void testLangage() {

        Transition[] transitions = {new Transition(0, new char[]{'a'},0),
                new Transition(0, new char[]{'b'},1),
                new Transition(1, new char[]{'c'},2)

        };
        Langage l = new Langage(0, new int[]{2}, transitions);
        /*
        System.out.println(l.parcourir('a'));
        System.out.println(l.parcourir('b'));
        System.out.println(l.estAccepte());

         */
        System.out.println(l.reconnaitMot("cc"));
        //System.out.println(l.reconnaitMot("aaaabc"));
    }
}


