public class Nif {

    private final String caracteresCaps = "TRWAGMYFPDXBNJZSQVHLCKET";
    private final String caracteres     = "trwagmyfpdxbnjzsqvhlcket";
    
    private String value;

    public Nif(String number) {
        this.value = number;
    }

    public void setValue(String number) {
        this.value = number;
    }

    public String getValue() {
        return value;
    }

    public boolean esValido() {
        return formatoValido() && letraValida();
    }

    private boolean formatoValido() {
        return this.value.matches("(\\d{7}|\\d{8})([A-Z]|[a-z])");
    }

    private boolean letraValida() {
        int length = value.length();
        int nifNumber = Integer.parseInt(value.substring(0, length-1));
        int i = nifNumber % 23;

        if (this.value.charAt(length-1) == this.caracteres.charAt(i))
            return true;
        
        else if (this.value.charAt(length-1) == this.caracteresCaps.charAt(i))
            return true;
        
        else
            return false;
    }

    public static void main(String[] args) {
        Nif nif = new Nif("46077483l");
        System.out.println(nif.esValido());
    }
}
