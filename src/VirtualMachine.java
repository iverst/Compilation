import org.omg.CORBA.OBJ_ADAPTER;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class VirtualMachine {
    private T_UNILEX[] TOKENS;
    private String[] TOKENS_CHAR;
    private LinkedList<Integer> PILEX = new LinkedList<>(), PILOP = new LinkedList<>();
    private String SOURCE;
    private ArrayList<String> fichierCode = new ArrayList<>();
    private TableIdent tableIdent;
    private Object[] MEMVAR;

    public VirtualMachine(T_UNILEX[] TOKENS, String[] TOKENS_CHAR, String codeSourceChemin, AnalyseurSyntaxique anaSyntaxique) {
        this.TOKENS = TOKENS;
        this.TOKENS_CHAR = TOKENS_CHAR;
        this.SOURCE = codeSourceChemin;
        tableIdent = new TableIdent(TOKENS, TOKENS_CHAR);
        fichierCode = anaSyntaxique.getCode();
    }

    public void CREER_FICHIER_CODE() {
        fichierCode.add("STOP");
        ArrayList<String> nouveauFichierCode = new ArrayList<>();
        nouveauFichierCode.add(String.valueOf(tableIdent.nombreVariableGlobales()) + " mot(s) réservé(s) pour les variables globales");

        //changement nom variable en adresse
        for (String s : fichierCode) {
            String[] mots = s.split(" ");
            if (mots.length > 1) {
                if (mots[1].charAt(0) == '@') {
                    String ident = mots[1].substring(1);
                    nouveauFichierCode.add("EMPI " + tableIdent.obtenirAdresse(ident));

                } else {
                    nouveauFichierCode.add(s);
                }

            } else {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void INTERPRETER() {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("-----------------------------------------------------------------");
        System.out.println();

        MEMVAR = tableIdent.MEMVAR();
        for (String ligne : fichierCode) {
            String[] mots = ligne.split(" ");
            switch (mots[0]) {
                case "ADDI":
                    ADDI(mots);
                    break;
                case "SOUS":
                    SOUS(mots);
                    break;
                case "MULT":
                    MULT(mots);
                    break;
                case "DIVI":
                    DIVI(mots);
                    break;
                case "MOIN":
                    MOIN(mots);
                    break;
                case "AFFE":
                    AFFE(mots);
                    break;
                case "LIRE":
                    LIRE(mots);
                    break;
                case "ECRL":
                    ECRL(mots);
                    break;
                case "ECRE":
                    ECRE(mots);
                    break;
                case "ECRC":
                    ECRC(mots, ligne);
                    break;
                case "EMPI":
                    EMPI(mots);
                    break;
                case "CONT":
                    CONT(mots);
                    break;
                case "STOP":
                    return;

            }
        }
    }
    private void ADDI(String[] CODE) {
        int v1 = PILEX.pollLast();
        int v2 = PILEX.pollLast();
        PILEX.addLast(v1 + v2);

    }
    private void SOUS(String[] CODE) {
        int v1 = PILEX.pollLast();
        int v2 = PILEX.pollLast();
        PILEX.addLast(v1 - v2);
    }
    private void MULT(String[] CODE) {
        int v1 = PILEX.pollLast();
        int v2 = PILEX.pollLast();
        PILEX.addLast(v1 * v2);
    }
    private void DIVI(String[] CODE) {
        int v1 = PILEX.pollLast();
        int v2 = PILEX.pollLast();
        PILEX.addLast(v1 / v2);
    }
    private void MOIN(String[] CODE) {
        PILEX.addLast(- PILEX.pollLast());
    }
    private void AFFE(String[] CODE) {
        int value = PILEX.pollLast();

        int address = PILEX.pollLast();
        //System.out.println("Address :" + address + " Value :" + value);
        MEMVAR[address] = value;
    }
    private void LIRE(String[] CODE) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int valeur = Integer.parseInt(reader.readLine());
            int address = PILEX.pollLast();
            MEMVAR[address] = valeur;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void ECRL(String[] CODE) {
        System.out.println();
    }
    private void ECRE(String[] CODE) {
        int valeur = PILEX.pollLast();
        System.out.print(valeur);
    }
    private void ECRC(String[] CODE, String ligne) {

            char[] chaine = ligne.toCharArray();
            String chaineReconstitue = "";
            for (int i = 6; i < ligne.length() - 5;) {
                chaineReconstitue = chaineReconstitue + chaine[i];
                i += 3;
            }

            System.out.print(chaineReconstitue + " ");

    }
    private void EMPI(String[] CODE) {
        PILEX.addLast(Integer.parseInt(CODE[1]));
    }
    private void CONT(String[] CODE) {
        int address = PILEX.pollLast();
        PILEX.addLast((Integer) MEMVAR[address]);
    }
}


class TableIdent {
    private HashMap<String, Integer> orderAddress = new HashMap<>();
    private ArrayList<Object> MEMVAR = new ArrayList<>();

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
                            i+=2;
                            if(TOKENS[i] == T_UNILEX.ENT) {
                                MEMVAR.add(Integer.parseInt(TOKENS_CHAR[i]));
                            }
                            else if (TOKENS[i] == T_UNILEX.CH) {
                                MEMVAR.add(TOKENS_CHAR[i]);
                            }
                        }
                    }
                }

                else if (TOKENS[i] == T_UNILEX.MOTCLE && TOKENS_CHAR[i].equals("VAR")) {
                    while (TOKENS[i] != T_UNILEX.PTVIRG) {
                        i++;
                        if (TOKENS[i] == T_UNILEX.IDENT) {
                            orderAddress.put(TOKENS_CHAR[i], order);
                            order++;
                            MEMVAR.add(null);
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

    public Object[] MEMVAR() {
        return MEMVAR.toArray();
    }
}