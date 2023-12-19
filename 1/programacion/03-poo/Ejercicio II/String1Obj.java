public class String1Obj {

    public String value;

    public String1Obj(String value) {
        this.value = value;
    }

    public int length() {
        return value.length();
    }

    public char charAt(int index) {
        if (index < 0 || index > value.length())
            throw new StringIndexOutOfBoundsException();
        return value.charAt(index);
    }

    public int beginIndex(String subStr) {
        int max = value.length();
        char first = subStr.charAt(0);
        for (int i = 0; i < max; i++) {

            // Buscar el primer caracter
            if (value.charAt(i) != first) {
                while (++i < max && value.charAt(i) != first);
            }

            // Encontrado el primer caracter, buscar el resto
            if (i < max) {
                int j = i + 1;
                int end = i + subStr.length() - 1;
                
                int k = 1;
                while (j < max && j < end && value.charAt(j) == subStr.charAt(k)) {
                    j++; k++;
                }

                if (j >= end) {
                    // Toda la cadena encontrada
                    return i;
                }
            }
        }

        return -1;
    }

    public void showIndexOfCharacter(char c) {
        for (int i = 0; i < value.length(); i++) {
            if (value.charAt(i) == c)
                System.out.println(i);
        }
    }

    public String toUpperCase() {
        String upperCaseStr = "";

        for (int i = 0; i < value.length(); i++) {
            
            char actualChar = value.charAt(i);
            
            if (Character.isAlphabetic(actualChar)) 
                actualChar -= 32;

            upperCaseStr += String.valueOf(actualChar);

        }

        return upperCaseStr;
    }

}
