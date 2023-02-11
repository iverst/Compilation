import org.omg.CORBA.ARG_IN;
import sun.tools.jstat.Token;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class TableIdentificateur {
    private final int NB_IDENT_MAX =  100;
    private HashMap<String,Integer> indexes = new HashMap<>();
    private Identificateur[] identificateurs = new Identificateur[NB_IDENT_MAX];
    private int position =  0;

    public void INSERER_TOKENS(ArrayList<T_UNILEX> TOKENS, ArrayList<String> TOKENS_CAR) {
        for (int i = 0; i < TOKENS.size(); i++) {
            if (TOKENS.get(i) == T_UNILEX.MOTCLE && Identificateur.getType(TOKENS_CAR.get(i)) != null) {
                String type = TOKENS_CAR.get(i);
                i++;
                if (TOKENS.get(i) == T_UNILEX.IDENT) {
                    INSERER(TOKENS_CAR.get(i), type);
                } else {
                    new Erreur(43, "Tout identificateur doit posséder un nom !").leverException();
                }
            }
        }
    }


    public int INSERER(String name, String type) {
        int index = CHERCHER(name);
        //cas identificateur pas présent dans la table
        if (index == -1) {
            if (type == null) {
                try {
                    Erreur erreur = new Erreur(42,"Erreur  identificateurs non existant!");
                    erreur.creerException();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (position < 100) {
                indexes.put(name, position);
                identificateurs[position] = new Identificateur(Identificateur.getType(type));
                int indentPosition = position;
                position++;
                return indentPosition;
            }
            else {
                try {
                    Erreur erreur = new Erreur(40,"Erreur trop d'identificateurs !");
                    erreur.creerException();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        //cas identificateur deja present
        return index;

    }

    public int CHERCHER(String ident) {
        if (indexes.get(ident) !=  null) {

            return indexes.get(ident);
        }
        return -1;
    }

    public void AFFICHER_TABLE_IDENT() {
        //recuperer toutes les données de la table des identificateru
        Set<String> idents = indexes.keySet();
        for (String element : idents) {
            Identificateur identificateur = identificateurs[indexes.get(element)];
            System.out.print("[");
            System.out.print(identificateur.getType());
            System.out.print(" , ");
            System.out.print(element);
            System.out.print(" , ");
            System.out.print(identificateur.getAttribut());
            System.out.println("]");

        }
    }

}

/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////

class Identificateur{
    private TYPE_IDENT type;
    private Object attribut;


    public Identificateur(TYPE_IDENT type, Object attribut) {
        this.type = type;
        this.attribut = attribut;
    }

    public Identificateur(TYPE_IDENT type) {
        this.type = type;
    }

    public Object getAttribut() {
        return attribut;
    }

    public TYPE_IDENT getType() {
        return type;
    }

    public static TYPE_IDENT getType(String type) {
        switch (type) {
            case "VAR":
                return TYPE_IDENT.VAR;
            case "CONST":
                return TYPE_IDENT.CONST;
            default:
                return null;
        }
    }
}

enum TYPE_IDENT { CONST, VAR }
