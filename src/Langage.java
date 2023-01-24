public class Langage {
    private int etat = 0, etatInitial;
    private int[] etatsAcceptes;
    private Transition[] transitions;

    public Langage(int etatInitial, int[] etatAccepte, Transition[] transitions) {
        this.etatInitial = etatInitial;
        this.etatsAcceptes = etatAccepte;
        this.transitions = transitions;
    }

    public void reinitialiser() {
        etat = 0;
    }

    //parcourt le langage avec c renvoie faux s'il n'existe aucune transition
    public boolean parcourir(char c) {
        boolean estAccepte = false;
        for (Transition t : transitions) {
            if (t.geteInitial() == etat && t.estAccepte(c)) {
                etat = t.geteFinale();
                estAccepte = true;
            }
        }
        return estAccepte;
    }
    //renvoie vrai si l'etat de l'automate equivaut à un etat final
    public boolean estAccepte() {
        for (int i : etatsAcceptes) {
            if (i == etat) {
                return true;
            }
        }
        return false;
    }

    public boolean reconnaitMot(String mot) {
        char[] motCharacteres = mot.toCharArray();
        for (char c : motCharacteres) {
            if (! parcourir(c)) {
                return false;
            }
        }
        return estAccepte();
    }
}
//represente une transition du langage
class Transition {
    private int eInitial, eFinale;
    private char[] motsAcceptes;

    public Transition(int eInitial,  char[] motsAcceptes, int eFinale) {
        this.eInitial = eInitial;
        this.eFinale = eFinale;
        this.motsAcceptes = motsAcceptes;
    }
    public boolean estAccepte(char c) {
        for (char mot : motsAcceptes) {
            if (mot == c) {
                return true;
            }
        }
        return false;
    }

    public int geteInitial() {
        return eInitial;
    }

    public int geteFinale() {
        return eFinale;
    }

    public char[] getMotsAcceptes() {
        return motsAcceptes;
    }
}


