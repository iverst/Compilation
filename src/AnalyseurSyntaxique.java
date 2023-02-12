
import sun.dc.pr.PRError;

import javax.print.DocFlavor;
import java.util.ArrayList;

public class AnalyseurSyntaxique {
    private T_UNILEX[] TOKENS;
    private String[] TOKENS_CHAR;

    public AnalyseurSyntaxique(ArrayList<T_UNILEX> TOKENS, ArrayList<String> TOKENS_CHAR) {
        this.TOKENS = (T_UNILEX[]) TOKENS.toArray(new T_UNILEX[0]);
        this.TOKENS_CHAR = (String[]) TOKENS_CHAR.toArray(new String[0]);
    }

    public boolean EST_CORECT() {
        LL1 ll1 = new LL1(TOKENS, TOKENS_CHAR);
        return ll1.EST_CORRECT();
    }

}

class LL1 {
    private T_UNILEX[] TOKENS;
    private String[]  TOKENSCHAR;
    private int index;
    private T_UNILEX UNILEX;

    public LL1(T_UNILEX[] TOKENS, String[] TOKENSCHAR) {
        this.TOKENS = TOKENS;
        this.TOKENSCHAR = TOKENSCHAR;
        UNILEX = TOKENS[0];
    }

    public LL1() {
    }

    public boolean EST_CORRECT() {
        try {
            if( PROG() && index + 1 == TOKENS.length) {
                return true;
            }
            else {
                return false;
            }
        }
        catch (Exception e) {
            return false;
        }

    }


    //////////////////////

    public T_UNILEX ANALEX() throws Exception {
        index++;
        if (index >= TOKENS.length){
            throw new Exception("FIN DE FICHIER ATTEINTE");
        }
        return TOKENS[index];
    }


    public String CHAINE() {
        return TOKENSCHAR[index];
    }

    //////////////////////
    //////////////////////
    //////////////////////
    public boolean TEST() throws Exception {
        return TERME();
    }

    public boolean PROG() throws Exception {
        if (UNILEX ==  T_UNILEX.MOTCLE && CHAINE().equals("PROGRAMME")) {
            UNILEX = ANALEX();

            if (UNILEX == T_UNILEX.IDENT) {
                UNILEX = ANALEX();

                if(UNILEX == T_UNILEX.PTVIRG) {
                    UNILEX = ANALEX();

                    if (DECL_CONST()) {

                    }
                    if (DECL_VAR()) {

                    }
                    if(BLOC()) {
                        if (UNILEX == T_UNILEX.POINT) {
                            return true;
                        }
                        else {
                            new Erreur("Erreur syntaxique dans une instruction de PROG: '.' attendu").afficherErreur();
                            return false;
                        }
                    }
                    else {
                        return false;
                    }

                }
                else {
                    new Erreur("Erreur syntaxique dans une instruction de PROG: ';' attendu").afficherErreur();
                    return false;
                }
            }
            else {
                new Erreur("Erreur syntaxique dans une instruction de PROG: IDENT attendu").afficherErreur();
                return false;
            }
        }
        else {
            new Erreur("Erreur syntaxique dans une instruction de PROG: mot clé PROGRAMME attendu").afficherErreur();
            return false;
        }
    }

    public boolean DECL_CONST() throws Exception {
        boolean fin = false, error = false;
        if (UNILEX == T_UNILEX.MOTCLE && CHAINE().equals("CONST")) {
            UNILEX = ANALEX();

            if (UNILEX == T_UNILEX.IDENT) {
                UNILEX = ANALEX();

                if(UNILEX == T_UNILEX.EG) {
                    UNILEX = ANALEX();

                    if (UNILEX == T_UNILEX.ENT || UNILEX == T_UNILEX.CH) {
                        UNILEX = ANALEX();
                        do {
                            if (UNILEX == T_UNILEX.VIRG) {
                                UNILEX = ANALEX();
                                if (UNILEX == T_UNILEX.IDENT) {
                                    UNILEX = ANALEX();
                                    if(UNILEX == T_UNILEX.EG) {
                                        UNILEX = ANALEX();
                                        if (UNILEX == T_UNILEX.CH || UNILEX == T_UNILEX.ENT) {
                                            UNILEX = ANALEX();
                                        }
                                        else {
                                            new Erreur("Erreur syntaxique dans une instruction de DECL_CONST: ENT ou CH attendu").afficherErreur();
                                            return false;
                                        }
                                    }
                                    else {
                                        new Erreur("Erreur syntaxique dans une instruction de DECL_CONST: '=' attendu").afficherErreur();
                                        return false;
                                    }
                                }
                                else {
                                    new Erreur("Erreur syntaxique dans une instruction de DECL_CONST: IDENT attendu").afficherErreur();
                                    return false;
                                }
                            }
                            else {
                                fin = true;
                            }

                        }
                        while (! fin);
                        if (UNILEX == T_UNILEX.PTVIRG) {
                            return true;
                        }
                        else {
                            new Erreur("Erreur syntaxique dans une instruction de DECL_CONST: ';' attendu").afficherErreur();
                            return false;
                        }


                    }

                    else {
                        new Erreur("Erreur syntaxique dans une instruction de DECL_CONST: CH ou ENT attendu").afficherErreur();
                        return false;
                    }
                }
                else {
                    new Erreur("Erreur syntaxique dans une instruction de DECL_CONST: '=' attendu").afficherErreur();
                    return false;
                }

            }
            else {
                new Erreur("Erreur syntaxique dans une instruction de DECL_CONST: IDENT attendu").afficherErreur();
                return false;
            }
        }
        else {
            new Erreur("Erreur syntaxique dans une instruction de DECL_CONST: MOTCLE 'CONST' attendu").afficherErreur();
            return false;
        }
    }

    public boolean DECL_VAR() throws Exception {
        boolean fin = false, error = false;
        if (UNILEX == T_UNILEX.MOTCLE && CHAINE().equals("VAR")) {
            UNILEX = ANALEX();

            if (UNILEX == T_UNILEX.IDENT) {
                UNILEX = ANALEX();
                do {
                    if (UNILEX == T_UNILEX.VIRG) {

                        UNILEX = ANALEX();
                        if (UNILEX == T_UNILEX.IDENT) {
                            UNILEX = ANALEX();
                        }
                        else {
                            error = true;
                        }
                    }
                    else {
                        fin = true;
                    }
                }
                while (! fin);
                if(error) {
                    new Erreur("Erreur syntaxique dans une instruction de DECL_VAR: INDENT attendu").afficherErreur();
                    return false;
                }
                else if (UNILEX == T_UNILEX.PTVIRG) {
                    UNILEX = ANALEX();
                    return true;
                }
                else {
                    new Erreur("Erreur syntaxique dans une instruction de DECL_VAR: ';' attendu").afficherErreur();
                    return false;
                }
            }
            else {
                new Erreur("Erreur syntaxique dans une instruction de DECL_VAR: INDENT attendu").afficherErreur();
                return false;
            }
        }
        else {
            new Erreur("Erreur syntaxique dans une instruction de DECL_VAR: mot clé 'VAR' attendu").afficherErreur();
            return false;
        }
    }

    public boolean BLOC() throws Exception {

        boolean fin = false, error = false;
        if (UNILEX == T_UNILEX.MOTCLE && CHAINE().equals("DEBUT")) {
            UNILEX = ANALEX();
            if (INSTRUCTION()) {
                if (UNILEX == T_UNILEX.PTVIRG) {
                    UNILEX = ANALEX();
                    do {
                        if (INSTRUCTION()) {
                            if (UNILEX == T_UNILEX.PTVIRG) {
                                UNILEX = ANALEX();

                            } else {
                                new Erreur("Erreur syntaxique dans une instruction de BLOC: ';' attendu").afficherErreur();
                                return false;
                            }
                        } else {
                            fin = true;
                        }
                    }
                    while (!fin);

                    if (UNILEX == T_UNILEX.MOTCLE && CHAINE().equals("FIN")) {
                        UNILEX = ANALEX();
                        return true;
                    } else {
                        new Erreur("Erreur syntaxique dans une instruction de BLOC: mot clé 'FIN' attendu").afficherErreur();
                        return false;
                    }

                } else {
                    new Erreur("Erreur syntaxique dans une instruction de BLOC: ';' attendu").afficherErreur();
                    return false;
                }
            }
            else {
                return false;
            }
        }
        else {
            new Erreur("Erreur syntaxique dans une instruction de BLOC: mot clé 'DEBUT' attendu").afficherErreur();
            return false;
        }
    }

    public boolean INSTRUCTION() throws Exception {
        return (AFFECTATION() || LECTURE() || ECRITURE() || BLOC());
    }

    //correct
    public boolean AFFECTATION() throws Exception {
        if (UNILEX == T_UNILEX.IDENT) {
            UNILEX = ANALEX();
            if (UNILEX == T_UNILEX.AFF) {
                UNILEX = ANALEX();
                return EXP();
            }
            else {
                new Erreur("Erreur syntaxique dans une instruction d'affectation: := attendu").afficherErreur();
                return false;
            }
        }
        else {
            new Erreur("Erreur syntaxique dans une instruction d'affectation: Indentificateur attendu").afficherErreur();
            return false;
        }
    }

    public boolean LECTURE() throws Exception {
        boolean erreur, fin;

        if(UNILEX == T_UNILEX.MOTCLE && CHAINE().equals("LIRE")) {

            UNILEX = ANALEX();
            if(UNILEX == T_UNILEX.PAROUV) {
                UNILEX = ANALEX();
                if(UNILEX == T_UNILEX.IDENT) {
                    UNILEX = ANALEX();
                    fin = false;
                    erreur = false;
                    do {
                        if(UNILEX == T_UNILEX.VIRG) {
                            UNILEX = ANALEX();
                            if (UNILEX == T_UNILEX.IDENT) {
                                UNILEX = ANALEX();
                            }
                            else {
                                fin = true;
                                erreur = true;
                            }
                        }
                        else {
                            fin = true;
                        }
                    }
                    while (! fin);
                    if (erreur) {
                        new Erreur("Erreur syntaxique dans l'instruction de lecure: Ident Incorrect").afficherErreur();
                        return false;
                    }
                    else if (UNILEX == T_UNILEX.PARFER) {
                        UNILEX = ANALEX();
                        return true;
                    }
                    else {
                        new Erreur("Erreur syntaxique dans l'instruction de lecure: ) attendu").afficherErreur();
                        return false;
                    }

                }
                else {
                    new Erreur("Erreur syntaxique dans l'instruction de lecure: identificateur attendu").afficherErreur();
                    return false;
                }
            }
            else {
                new Erreur("Erreur syntaxique dans l'instruction de lecure: ( attendu").afficherErreur();
                return false;
            }
        }
        else {
            new Erreur("Erreur syntaxique dans l'instruction de lecure: mot-clé LIRE attendu").afficherErreur();
            return false;
        }
    }


    public boolean ECRITURE() throws Exception {
        boolean fin = false, erreur = false;

        if (UNILEX == T_UNILEX.MOTCLE && CHAINE().equals("ECRIRE")) {
            UNILEX = ANALEX();

            if (UNILEX == T_UNILEX.PAROUV) {
                UNILEX = ANALEX();
                erreur = false;
                if (ECR_EXP()) {
                    fin = false;
                    do {
                        if (UNILEX == T_UNILEX.VIRG) {
                            UNILEX = ANALEX();
                            erreur = !ECR_EXP();
                            if (erreur) {
                                fin = true;
                            }
                        }
                        else {
                            fin = true;
                        }
                    }
                    while (! fin);
                }
                if (erreur) {
                        new Erreur("Erreur syntaxique dans l'instruction d'écriture: expression incorrecte").afficherErreur();
                        return false;
                }
                else if (UNILEX == T_UNILEX.PARFER) {
                    UNILEX = ANALEX();
                    return true;
                }
                else {
                    new Erreur("Erreur syntaxique dans l'instruction d'écriture: ')' attendu").afficherErreur();
                    return false;
                }
            }
            else {
                new Erreur("Erreur syntaxique dans l'instruction d'écriture: '(' attendu").afficherErreur();
                return false;
            }
        }
        else {
            new Erreur("Erreur syntaxique dans l'instruction d'écriture: mot-clé 'ECRIRE' attendu").afficherErreur();
            return false;
        }
    }

    public boolean ECR_EXP() throws Exception {
        if (UNILEX == T_UNILEX.CH) {
            UNILEX = ANALEX();
            return true;
        }
        else if (EXP()) {
            return true;
        }
        else {
            new Erreur("Erreur syntaxique dans l'instruction ECR_EXP: CH attendu ou EXP invalide").afficherErreur();
            return false;
        }
    }

    public boolean EXP() throws Exception {
        if (TERME()) {
            if (OP_BIN()) {
                if (EXP()) {
                    return true;
                }
                else {
                    return false;
                }
            }
            else if (SUITE_TERME()) {
                return true;
            }
            else {
                return true;
            }
        }
        else {
            return false;
        }
    }

    public boolean TERME() throws Exception {
        if (UNILEX == T_UNILEX.ENT) {
            UNILEX = ANALEX();
            return true;
        }
        else if(UNILEX == T_UNILEX.IDENT) {
            UNILEX = ANALEX();
            return true;
        }
        else if(UNILEX == T_UNILEX.PAROUV) {
            UNILEX = ANALEX();
            if(EXP()) {
                if (UNILEX == T_UNILEX.PARFER) {
                    UNILEX = ANALEX();
                    return true;
                }
                else {
                    new Erreur("Erreur syntaxique dans l'instruction TERME: ')' attendu").afficherErreur();
                    return false;
                }
            }
            else {
                return false;
            }
        }
        else if(UNILEX == T_UNILEX.MOINS) {
            UNILEX = ANALEX();
            return TERME();
        }
        else {
            new Erreur("Erreur syntaxique dans l'instruction TERME: Instruction invalide").afficherErreur();
            return false;
        }
    }

    public boolean OP_BIN() throws Exception {

        if (UNILEX == T_UNILEX.PLUS || UNILEX == T_UNILEX.MOINS || UNILEX == T_UNILEX.MULT || UNILEX == T_UNILEX.DIVI) {
            UNILEX = ANALEX();
            return true;

        }
        else {
            new Erreur("Erreur syntaxique dans l'instruction OP_BIN: '+' ou '-' ou '*' ou '/' attendu").afficherErreur();
            return false;
        }
    }
    public boolean SUITE_TERME() throws Exception {
        if (OP_BIN()) {
            return EXP();
        }
        else {
            return true;
        }
    }


}
