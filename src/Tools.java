public class Tools {
    private static Tools intance;

    public static Tools getIntance() {
        if (intance == null) {
            intance = new Tools();
        }
        return intance;
    }

    public boolean contains(int[] array, int e) {
        for (int i : array) {
            if(i == e) {
                return true;
            }
        }
        return false;
    }

    public boolean contains(char[] array, char e) {
        for (char i : array) {
            if(i == e) {
                return true;
            }
        }
        return false;
    }

    public String decouperChaine(String chaine) {
        String chaineDecoupe = "";
        for (char c : chaine.toCharArray()) {
            chaineDecoupe =  chaineDecoupe + "'" + c + "'";
        }
        return chaineDecoupe;
    }
}
