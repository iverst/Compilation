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

    public boolean contains(char[] array, int e) {
        for (char i : array) {
            if(i == e) {
                return true;
            }
        }
        return false;
    }
}
