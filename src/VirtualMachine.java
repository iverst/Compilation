import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class VirtualMachine {
    private T_UNILEX[] TOKENS;
    private String[] TOKENS_CHAR;
    private LinkedList<Integer> PILEX = new LinkedList<>();
    private LinkedList<INSTRUCTION_MEM> PILOP = new LinkedList<>();
    private String SOURCE;
    private ArrayList<String> fichierCode = new ArrayList<>();
    private TableIdent tableIdent;

    public VirtualMachine(T_UNILEX[] TOKENS, String[] TOKENS_CHAR, String codeSourceChemin, AnalyseurSyntaxique anaSyntaxique) {
        this.TOKENS = TOKENS;
        this.TOKENS_CHAR = TOKENS_CHAR;
        this.SOURCE = codeSourceChemin;
        tableIdent = new TableIdent(TOKENS, TOKENS_CHAR);
        fichierCode = anaSyntaxique.getCode();
        CREER_FICHIER_CODE();
    }

    private void CREER_FICHIER_CODE() {

        ArrayList<String> nouveauFichierCode = new ArrayList<>();
        nouveauFichierCode.add(String.valueOf(tableIdent.nombreVariableGlobales()) + " mot(s) réservé(s) pour les variables globales");

        //changement nom variable en adresse
        for (String s : fichierCode) {
            String[] mots = s.split(" ");
            if (mots.length > 1) {
                if(mots[1].charAt(0) == '@') {
                    String ident = mots[1].substring(1);
                    nouveauFichierCode.add("EMPI " + tableIdent.obtenirAdresse(ident));

                }
                else {
                    nouveauFichierCode.add(s);
                }

            }
            else {
                nouveauFichierCode.add(s);
            }
        }
        fichierCode = nouveauFichierCode;
        String SOURCECOD = SOURCE + ".COD";
        File fichier = new File(SOURCECOD);
        System.out.println(nouveauFichierCode);
        try {
            FileWriter fileWriter = new FileWriter(SOURCECOD);
            for (String s : fichierCode) {
                fileWriter.write(s + "\n");
            }
            fileWriter.close();
            System.out.println("Creation fichier .COD réussie");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void creerTableIdent() {

    }
}

enum INSTRUCTION_MEM {
    ADDI, SOUS, MULT, DIVI, MOIN, AFFE, LIRE, ECRL, ECRE, ECRC, FINC, EMPI, CONT, STOP
}

class TableIdent {
    private HashMap<String, Integer> orderAddress = new HashMap<>();

    public TableIdent(T_UNILEX[] TOKENS, String[] TOKENS_CHAR) {

        init(TOKENS, TOKENS_CHAR);
    }

        private void init(T_UNILEX[] TOKENS, String[] TOKENS_CHAR) {
            int order = 0;
            for (int i = 0; i < TOKENS.length; i++) {

                if (TOKENS[i] == T_UNILEX.MOTCLE && TOKENS_CHAR[i].equals("CONST")) {
                    while (TOKENS[i] != T_UNILEX.PTVIRG) {
                        i++;
                        if (TOKENS[i] == T_UNILEX.IDENT) {
                            orderAddress.put(TOKENS_CHAR[i], order);
                            order++;
                        }
                    }
                }

                else if (TOKENS[i] == T_UNILEX.MOTCLE && TOKENS_CHAR[i].equals("VAR")) {
                    while (TOKENS[i] != T_UNILEX.PTVIRG) {
                        i++;
                        if (TOKENS[i] == T_UNILEX.IDENT) {
                            orderAddress.put(TOKENS_CHAR[i], order);
                            order++;
                        }
                    }
                }

        }
    }
    public int obtenirAdresse(String nomIndent){
        return orderAddress.get(nomIndent);
    }

    public int nombreVariableGlobales() {
        return orderAddress.size();
    }
}