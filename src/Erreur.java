public class Erreur {
    private int numErreur = -1;
    private String descriptionErreur;
    private static boolean afficher = false;

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

}
