import java.util.ArrayList;

public class Erreur {
    private int numErreur = -1;
    private String descriptionErreur;
    private static boolean afficher = true;
    private static ArrayList<Integer> numero_ligne_code = new ArrayList<>();

    public Erreur(int numErreur, String descriptionErreur) {
        this.numErreur = numErreur;
        this.descriptionErreur = descriptionErreur;
    }

    public Erreur(String descriptionErreur) {
        this.descriptionErreur = descriptionErreur;

    }

    public String afficherErreur() {


        if (numErreur != -1) {
            if(afficher) {
                System.out.println("Erreur n°" + numErreur + ", message '" + descriptionErreur + "'");
            }

            return "Erreur n°" + numErreur + ", message '" + descriptionErreur + "'";
        }
        else {
            if (afficher) {
                System.out.println("Erreur : '" + descriptionErreur + "'");
            }

            return "Erreur : '" + descriptionErreur + "'";
        }

    }

    public void creerException() throws Exception {

        throw new Exception(afficherErreur());
    }

    public void leverException () {
        try {
            creerException();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String afficherErreurLigne(int index) {
        return "Erreur n°" + numErreur + " à la ligne " + numero_ligne_code.get(index) + " , message '" + descriptionErreur + "'";

    }

    public static void ajouterLigneCode(int i) {
        numero_ligne_code.add(i);
    }

    public static int getLigneCode(int index) {
        return numero_ligne_code.get(index);
    }
}
