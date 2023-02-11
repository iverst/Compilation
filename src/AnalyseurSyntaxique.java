
import java.util.ArrayList;

public class AnalyseurSyntaxique {
    private T_UNILEX[] TOKENS;
    private String[] TOKENS_CHAR;


    public AnalyseurSyntaxique(ArrayList<T_UNILEX> TOKENS, ArrayList<String> TOKENS_CHAR) {
        this.TOKENS = (T_UNILEX[]) TOKENS.toArray();
        this.TOKENS_CHAR = (String[]) TOKENS_CHAR.toArray();
    }


}

class LL1{

}

class Symboles {
    private T_UNILEX T;
    private SymbolesNonTerminaux N;
    private String MOT_CLE;
    private boolean isTerminal;

    public Symboles(T_UNILEX t) {
        T = t;
        isTerminal = true;
    }

    public Symboles(SymbolesNonTerminaux n) {
        N = n;
        isTerminal = false;
    }

    public Symboles(T_UNILEX t, String MOT_CLE) {
        T = t;
        if (t == T_UNILEX.MOTCLE) {
            this.MOT_CLE = MOT_CLE;
        }
        isTerminal = true;
    }

    public boolean isTerminal() {
        return isTerminal;
    }

    public SymbolesNonTerminaux getN() {
        return N;
    }

    public boolean isEqual(T_UNILEX T, String TCHAR) {
        if (this.T == T) {
            if (T == T_UNILEX.MOTCLE && TCHAR.equals(MOT_CLE)) {
                return true;
            }
            else if (T == T_UNILEX.MOTCLE) {
                return false;
            }
            else {
                return true;
            }
        }
        return false;
    }
}

enum SymbolesNonTerminaux {
    PROG, DECL_CONST, DECL_VAR, BLOC, INSTRUCTION, AFFECTATION, LECTURE, ECRITURE, ECR_EXP, EXP, TERME, OP_BIN
}
