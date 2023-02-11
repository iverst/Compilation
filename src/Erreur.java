public class Erreur {
    private int numErreur;
    private String descriptionErreur;

    public Erreur(int numErreur, String descriptionErreur) {
        this.numErreur = numErreur;
        this.descriptionErreur = descriptionErreur;
    }

    public String afficherErreur() {
        return "Erreur n°" + numErreur + ", message '" + descriptionErreur + "'";
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
