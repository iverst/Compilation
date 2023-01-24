public class Test {
    public static void main(String[] args) {
        testErreur();
    }
    public static void testErreur() {
        Erreur erreur = new Erreur(1, "fin de fichier atteinte");
        System.out.println(erreur.afficherErreur());
    }
}


