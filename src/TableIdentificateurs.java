import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;

public class TableIdentificateurs {
    private HashSet<String> idents = new HashSet<>();
    private final int MAX_IDENT =  100;
    private int nbIdent = 0;
    private HashMap<String, EnregistrementIdent> enregistrementIdents = new HashMap<String, EnregistrementIdent>();
    private T_UNILEX[] TOKENS;
    private String[] TOKENS_CHAINE;

    public TableIdentificateurs(ArrayList<T_UNILEX> TOKENS, ArrayList<String> TOKENS_CHAINE) {
        this.TOKENS = (T_UNILEX[]) TOKENS.toArray(new T_UNILEX[0]);
        this.TOKENS_CHAINE = (String[]) TOKENS_CHAINE.toArray(new String[0]);
    }

    public void INSERER_TOKENS() {
        for (int i = 0; i < TOKENS.length; i++) {
            if (TOKENS[i] == T_UNILEX.IDENT) {
                INSERER(i);
            }
        }
    }

    private boolean INSERER(int index) {
        //cas existe deja un indent avec ce nom
        if(CHERCHER(TOKENS_CHAINE[index])) {
            return false;
        }
        else {
            if (nbIdent < MAX_IDENT) {
                String ident_name = TOKENS_CHAINE[index];
                idents.add(ident_name);
                enregistrementIdents.put(ident_name, new EnregistrementIdent(ident_name));
                nbIdent++;
                return true;
            }
            else {
                return false;
            }
        }
    }

    private boolean CHERCHER(String identName) {
        return idents.contains(identName);
    }

    public void AFFICHE_TABLE_IDENT() {
        System.out.println();
        System.out.println();
        System.out.println("TABLE DES IDENTIFICATEURS :");


        for (Object o : idents.toArray()) {
            System.out.println(o);
        }

        System.out.println();
        System.out.println();
    }
}


class EnregistrementIdent {
    public String name;
    public Object attribut;
    private TYPE type;

    public EnregistrementIdent(String name, TYPE type, Object attribut) {
        this.name = name;
        this.attribut = attribut;
        this.type = type;
    }

    public EnregistrementIdent(String name) {
        this.name = name;
        this.attribut = null;
        this.type = null;
    }
}
enum TYPE {
    CONST, VAR
}




