class Ejemplo {
    private int numero1;
    private int numero2;

    public Ejemplo(int n1, int n2) {
        this.numero1 = n1;
        this.numero2 = n2;
    }

    public int suma() {
        int resultado = this.numero1 + this.numero2;
        return resultado;
    }

    public int suma(int n1, int n2) {
        this.numero1 = n1;
        this.numero2 = n2;
        int resultado = this.suma();
        return resultado;
    }

    public int suma(int n1, int n2, int n3) {
        int resultado = n1 + n2 + n3;
        return resultado;
    }

    public static void main(String[] args) {
        Ejemplo ejemplo = new Ejemplo(3, 5);
        System.out.println("Suma 1: " + ejemplo.suma());
        System.out.println("Suma 2: " + ejemplo.suma(2, 4));
        System.out.println("Suma 3: " + ejemplo.suma(1, 2, 3));
    }
}
