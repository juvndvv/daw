public class Dni {
    private int numDNI;

    public int getDNI() {
        return numDNI;
    }

    public String getNIF() {
        return String.format("%d%s", numDNI, String.valueOf(calcularLetraNIF(numDNI)));
    }

    public void set(String nif) throws IllegalArgumentException {
        if (!validarNIF(nif))
            throw new IllegalArgumentException("El NIF no es valido");

        this.numDNI = extraerNumeroNIF(nif);
    }

    public void set(int dni) throws IllegalArgumentException {
        int cifras = calcularLongitud(dni);
        if (cifras != 7 && cifras != 8)
            throw new IllegalArgumentException("El DNI debe tener 7 u 8 cifras");

        this.numDNI = dni;
    }

    private boolean validarNIF (String nif) {
        return calcularLetraNIF(extraerNumeroNIF(nif)) == extraerLetraNIF(nif);
    }

    private static char calcularLetraNIF(int dni) {
        String caracteres = "TRWAGMYFPDXBNJZSQVHLCKE";
        return caracteres.charAt(dni % 23);
    }

    private static char extraerLetraNIF (String nif) {
        return nif.charAt(nif.length()-1);
    }

    private static int extraerNumeroNIF (String nif) {
        return Integer.parseInt(nif.substring(0, nif.length()-1));
    }

    private static int calcularLongitud(int dni) {
        return (int)(Math.log10(dni)+1);
    }

    public static void main(String[] args) {
        Dni dni = new Dni();
        dni.set(6077483);
        System.out.println(dni.getDNI());
        System.out.println(dni.getNIF());
    }
}
