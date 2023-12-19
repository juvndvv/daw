public class Articulo {
    
    private String nombre;
    private float precioNeto;
    private int cuantosQuedan;
    private int iva;

    /* Constructores */
    public Articulo(String nombre, float precioNeto, int cuantosQuedan, int iva) {
        boolean valido = true;
        if (nombre.length() > 20) {
            System.err.println("La longitud del nombre no debe ser mayor a 20");
            valido = false;
        }
        if (precioNeto <= 0){
            System.err.println("El precio ha de ser mayor a cero");
            valido = false;
        }
        if (cuantosQuedan < 0 || cuantosQuedan > 100) {
            System.err.println("El numero de productos que quedan ha de ser 0 o positivo");
            valido = false;
        }
        if (iva != 21) {
            System.err.println("El IVA ha de ser 21");
            valido = false;
        }

        if (valido) {
            this.nombre = nombre;
            this.precioNeto = precioNeto;
            this.cuantosQuedan = cuantosQuedan;
            this.iva = iva;
        }
    }

    /* Getters y setters */
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecioNeto() {
        return precioNeto;
    }

    public void setPrecioNeto(float precioNeto) {
        this.precioNeto = precioNeto;
    }

    public int getCuantosQuedan() {
        return cuantosQuedan;
    }

    public void setCuantosQuedan(int cuantosQuedan) {
        this.cuantosQuedan = cuantosQuedan;
    }

    public int getIva() {
        return iva;
    }

    /* Metodos publicos */
    public float getPVP() {
        return this.precioNeto * iva / 100;
    }

    public float getPVPDescuento(int descuento) {
        float pvp = getPVP();
        return pvp * descuento / 100;
        }

    public boolean vender(int x) {
        if ((cuantosQuedan - x) > 0) {
            cuantosQuedan -= x;
            return true;
        }
        return false;
    }

    public boolean almacenar(int x) {
        if ((this.cuantosQuedan + x) < 101) {
            this.cuantosQuedan += x;
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Articulo a = new Articulo("a", 100, 12, 21);

        float pre = a.getPVPDescuento(25);
        System.out.println(pre);
    }
}
